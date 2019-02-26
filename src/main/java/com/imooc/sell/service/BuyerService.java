package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;
import org.springframework.stereotype.Service;

public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);
    //取消订单
    OrderDTO cancelOrderOne(String openid,String orderId);
}
