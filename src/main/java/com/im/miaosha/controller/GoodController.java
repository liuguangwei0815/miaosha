package com.im.miaosha.controller;

import com.im.miaosha.dto.GoodsVo;
import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.redis.RedisServer;
import com.im.miaosha.service.GoodsService;
import com.im.miaosha.service.MiaoshaUserServier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


    /**
     * 获取商品列表
     *
     * @param model 模型
     * @param user  用户
     * @return {@link String}
     */
    @GetMapping("/to_list")
    public String toList(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }


    /**
     * 细节 一般的uuid 不会用自增id的一般使用的是snowflake算法  这个有时间一定要实现下
     *
     * @param model 模型
     * @param user  用户
     * @return {@link String}
     */
    @GetMapping("/to_detail/{goodsId}")
    public String details(Model model, MiaoshaUser user, @PathVariable("goodsId") Long goodsId) {
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
            numberOfSecondsRemaining = (start - now)/1000;
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
        return  "goods_detail";
    }


}
