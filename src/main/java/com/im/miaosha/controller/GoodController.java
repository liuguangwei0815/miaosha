package com.im.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: miaosha
 * @description: 商品
 * @author: liu.wei
 * @create: 2020-04-18 22:53
 **/
@Controller
@RequestMapping("/good")
public class GoodController {

    @GetMapping("/to_list")
    public String toList() {
        return "goods_list";
    }

}
