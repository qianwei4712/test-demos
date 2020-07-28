package com.example.demo.spring.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author shiva   2020/6/2 20:26
 */
@Service
public class OrderService {
    @Autowired
    private GoodsService goodsService;
    public BigDecimal calc(String orderId){
//        根据订单ID获得货物列表...
//        根据货物ID查询货物，再进行计算
//        goodsService.getById("");
        return null;
    }
}
