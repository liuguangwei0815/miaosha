package com.im.miaosha.controller;

import com.im.miaosha.dto.GoodsVo;
import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.redis.GoodsPrefix;
import com.im.miaosha.redis.KeyPrefix;
import com.im.miaosha.redis.RedisServer;
import com.im.miaosha.service.GoodsService;
import com.im.miaosha.service.MiaoshaUserServier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: miaosha
 * @description: 商品
 * @author: liu.wei
 * @create: 2020-04-18 22:53
 **/
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodController {

    @Autowired
    private RedisServer redisServer;
    @Autowired
    private MiaoshaUserServier miaoshaUserServier;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;


    /**
     * 获取商品列表 进行页面缓存 一般要求缓存时间比较短
     * {produces = "text/html" + @ResponseBody 标识返回的直接是html的源码 }
     *
     * @param model 模型
     * @param user  用户
     * @return {@link String}
     */
    @GetMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user) {
        //先从缓存获取
        String html = getPageFormCache(GoodsPrefix.GOODLIST,"");
        if (StringUtils.isNotBlank(html)) {
            return html;
        }
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //获取渲染的html很存储在缓存
        return applyDrawing(request, response, model,"goods_list",GoodsPrefix.GOODLIST,"");
    }

    /**
     * 获得页面缓存
     *
     * @return {@link String}
     */
    private String getPageFormCache(KeyPrefix keyPrefix, String key) {
        return redisServer.getValue(keyPrefix, key, String.class);
    }
    /**
     * 手动渲染html
     *
     * @param request  请求
     * @param response 响应
     * @param model    模型
     * @return {@link String}
     */
    private String applyDrawing(HttpServletRequest request, HttpServletResponse response, Model model, String template, KeyPrefix keyPrefix,String key) {
        String html;
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process(template, webContext);
        redisServer.setKey(keyPrefix, key, html);
        return html;
    }


    /**
     * 细节 一般的uuid 不会用自增id的一般使用的是snowflake算法  这个有时间一定要实现下
     * URL缓存
     *
     * @param model 模型
     * @param user  用户
     * @return {@link String}
     */
    @GetMapping(value = "/to_detail/{goodsId}",produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String details(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser user, @PathVariable("goodsId") Long goodsId) {
        //先从缓存获取
        String html = getPageFormCache(GoodsPrefix.GDETAIL,String.valueOf(goodsId));
        if (StringUtils.isNotBlank(html)) {
            return html;
        }

        model.addAttribute("user", user);
        GoodsVo gvo = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", gvo);

        long start = gvo.getStartDate().getTime();
        long end = gvo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        long numberOfSecondsRemaining = 0;

        int state = 0;
        if (start > now) {
            //未开始
            state = 0;
            numberOfSecondsRemaining = (start - now) / 1000;
        } else if (now > end) {
            //秒杀结束
            state = 2;
            numberOfSecondsRemaining = -1;
        } else {
            //秒杀进行中
            state = 1;
            numberOfSecondsRemaining = 0;
        }
        model.addAttribute("miaoshaStatus", state);
        model.addAttribute("remainSeconds", numberOfSecondsRemaining);
        return applyDrawing(request, response, model,"goods_detail",GoodsPrefix.GDETAIL,String.valueOf(goodsId));
    }


}
