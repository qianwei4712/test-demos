package com.example.demo.spring.demo1;

import java.math.BigDecimal;

/**
 * @author shiva   2020/6/2 20:37
 */
public class Controller {

    public static void main(String[] args) {
        //假设需要完成一个计算订单价格的业务（假设哦，可能和实际业务不一样，栗子没举好）
        //假设这个main 方法是 controller 入口
        OrderService orderService = new OrderService();
        GoodsService goodsService = new GoodsService();
        orderService.setGoodsService(goodsService);
        BigDecimal orderCount = orderService.calc("orderId");
    }
}
