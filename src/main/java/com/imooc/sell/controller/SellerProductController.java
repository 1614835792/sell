package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.ProductForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value="size",defaultValue = "10")Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage=productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        ModelAndView modelAndView= new ModelAndView("product/list",map);
        log.info(modelAndView.getViewName());
        return modelAndView;
    }
    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId")String productId,Map<String,Object>map){
          try{
              map.put("url","/sell/seller/product/list");
              ProductInfo productInfo=productService.offSale(productId);
              map.put("msg", ResultEnum.SUCCESS.getMessage());
          }catch(SellException e){
              map.put("msg",e.getMessage());
              return new ModelAndView("common/error",map);
          }
          return new ModelAndView("common/success",map);
    }
    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId")String productId,Map<String,Object>map){
        try{
            map.put("url","/sell/seller/product/list");
            ProductInfo productInfo=productService.onSale(productId);
            map.put("msg", ResultEnum.SUCCESS.getMessage());
        }catch(SellException e){
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value="productId",required = false)String productId,Map<String,Object>map){
            if(!StringUtils.isEmpty(productId)){
                ProductInfo productInfo=productService.findOne(productId);
                map.put("productInfo",productInfo);
            }
            List<ProductCategory> categoryList= categoryService.findAll();
            map.put("categoryList",categoryList);
            return new ModelAndView("product/index",map);
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return  new ModelAndView("common/error",map);
        }
           try{
               ProductInfo productInfo=new ProductInfo();
               //如果productId为空，说明是新增
               if(!StringUtils.isEmpty(form.getProductId())){
                   productInfo=productService.findOne(form.getProductId());
               }else{
                   form.setProductId(KeyUtil.genUnique());
               }
               BeanUtils.copyProperties(form,productInfo);
               productService.save(productInfo);
           }catch(SellException e){
               map.put("msg",e.getMessage());
               map.put("url","/sell/seller/product/index");
               return new ModelAndView("common/error",map);
           }
           map.put("msg",ResultEnum.SUCCESS.getMessage());
           map.put("url","/sell/seller/product/list");
           return new ModelAndView("common/success",map);
    }
}
