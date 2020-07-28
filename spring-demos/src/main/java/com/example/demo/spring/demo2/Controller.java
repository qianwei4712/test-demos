package com.example.demo.spring.demo2;

import java.math.BigDecimal;

/**
 * @author shiva   2020/6/2 20:37
 */
public class Controller {

    public static void main(String[] args) {
        //在 Controller中注入 OrderService
        OrderService orderService = new OrderService();
        BigDecimal orderCount = orderService.calc("orderId");
    }
}
