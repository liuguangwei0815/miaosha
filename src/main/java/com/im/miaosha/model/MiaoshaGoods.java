package com.im.miaosha.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 *
 * @author liu.wei
 * @since 1.0  2020-4-19 13:03:13
 */
@Data
public class MiaoshaGoods extends BaseModel {

    private Long id; //

    private Long goodsId; //

    private BigDecimal miaoshaPrice; // 秒杀价

    private Long stockCount; // 库存

    private Date startDate; //

    private Date endDate; //

}
