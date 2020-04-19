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


}
