package com.im.miaosha.controller;

import com.im.miaosha.dto.GoodsVo;
import com.im.miaosha.model.MiaoshaOrder;
import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.model.OrderInfo;
import com.im.miaosha.result.MsgCode;
import com.im.miaosha.service.GoodsService;
import com.im.miaosha.service.MiaoshaService;
import com.im.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: miaosha
 * @description: 秒杀controller
 * @author: liu.wei
 * @create: 2020-04-19 21:22
 **/
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVo gv = goodsService.getGoodsVoByGoodsId(goodsId);
        //判断库存是否足够
        Integer stockCount = gv.getStockCount();
        if (stockCount == 0) {
            model.addAttribute("errmsg", MsgCode.GOODSHAVEFINISHEDSECONDTOKILL.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (miaoshaOrder != null) {
            model.addAttribute("errmsg", MsgCode.CANTREPEAT.getMsg());
            return "miaosha_fail";
        }
        OrderInfo orderInfo = miaoshaService.miaosha(user, gv);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", gv);
        return "order_detail";
    }

    @RequestMapping("/do_order")
    public String doOrderDetails(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVo gv = goodsService.getGoodsVoByGoodsId(goodsId);
        //判断库存是否足够
        OrderInfo orderInfo = miaoshaService.miaosha(user, gv);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", gv);
        return "order_detail";
    }



}
