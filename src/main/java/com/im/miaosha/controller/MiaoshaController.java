package com.im.miaosha.controller;

import com.im.miaosha.dto.GoodsVo;
import com.im.miaosha.exception.BusinessException;
import com.im.miaosha.model.MiaoshaOrder;
import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.model.OrderInfo;
import com.im.miaosha.redis.MiaoshaKeyPrefix;
import com.im.miaosha.redis.RedisServer;
import com.im.miaosha.result.MsgCode;
import com.im.miaosha.result.Result;
import com.im.miaosha.service.GoodsService;
import com.im.miaosha.service.MiaoshaService;
import com.im.miaosha.service.OrderService;
import com.im.miaosha.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private RedisServer redisServer;


    /**
     * 获取秒杀用户地址
     *
     * @param model   模型
     * @param user    用户
     * @param goodsId 商品id
     * @return {@link Result<String>}
     */
    @GetMapping("/path")
    @ResponseBody
    public Result<String> path(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            throw new BusinessException(MsgCode.USERDOESNOTEXIST);
        }
        //生成随机串
        String uuid = UUIDUtils.getUID();
        //存放到redis ,key 为前缀+用户名+商品id+uuid
        redisServer.setKey(MiaoshaKeyPrefix.MIAOSHAKEYPREFIX, user.getId() + "_" + String.valueOf(goodsId), uuid);
        return Result.success(uuid);
    }

    @PostMapping("/{path}/do_miaosha")
    @ResponseBody
    public Result doMiaosha(Model model, MiaoshaUser user, @PathVariable("path") String path, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            throw new BusinessException(MsgCode.USERDOESNOTEXIST);
        }
        //校验path
        checkpath(user, path, goodsId);
        //判断库存
        GoodsVo gv = goodsService.getGoodsVoByGoodsId(goodsId);
        //判断库存是否足够
        Integer stockCount = gv.getStockCount();
        if (stockCount == 0) {
            throw new BusinessException(MsgCode.GOODSHAVEFINISHEDSECONDTOKILL);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (miaoshaOrder != null) {
            throw new BusinessException(MsgCode.CANTREPEAT);
        }
        OrderInfo orderInfo = miaoshaService.miaosha(user, gv);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", gv);
        return Result.success(true);
    }

    /**
     * 校验路径
     *
     * @param user    用户
     * @param path    路径
     * @param goodsId 商品id
     */
    private void checkpath(MiaoshaUser user, @PathVariable("path") String path, @RequestParam("goodsId") long goodsId) {
        String pathkey = redisServer.getValue(MiaoshaKeyPrefix.MIAOSHAKEYPREFIX, user.getId() + "_" + String.valueOf(goodsId), String.class);
        if (StringUtils.isBlank(pathkey) || (!StringUtils.equals(pathkey, path)) || StringUtils.isBlank(path)) {
            throw new BusinessException(MsgCode.ILLEGALREQUEST);
        }
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
