package com.example.demo.spring.demo1;

import java.math.BigDecimal;

/**
 * @author shiva   2020/6/2 20:26
 */
public class OrderService {
    private GoodsService goodsService;
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    public BigDecimal calc(String orderId){
//        根据订单ID获得货物列表...
//        根据货物ID查询货物，再进行计算
//        goodsService.getById("");
        return null;
    }
}
