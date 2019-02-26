package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController{
    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value="size",defaultValue = "10")Integer size,
                             Map<String,Object>map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(pageRequest);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
       ModelAndView modelAndView= new ModelAndView("order/list",map);
       log.info(modelAndView.getViewName());
       return modelAndView;
    }
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,Map<String,Object>map){
        try{
            map.put("url","/sell/seller/order/list");
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch(SellException e){
            log.error("[卖家端取消订单]查询不到订单");
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,Map<String,Object>map){
       try{
           OrderDTO orderDTO=orderService.findOne(orderId);
           map.put("orderDTO",orderDTO);
           return new ModelAndView("order/detail",map);
       }catch(SellException e){
           map.put("msg",e.getMessage());
           map.put("url","/sell/seller/order/list");
           return new ModelAndView("common/error",map);
       }
    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,Map<String,Object>map){
        try{
               OrderDTO orderDTO=orderService.findOne(orderId);
               orderService.finish(orderDTO);
               map.put("msg",ResultEnum.SUCCESS.getMessage());
               map.put("url","/sell/seller/order/list");
        }catch(SellException e){
               map.put("msg",e.getMessage());
               map.put("url","/sell/seller/order/detail");
               return new ModelAndView("common/error",map);
        }
        return new ModelAndView("common/success",map);
    }
}