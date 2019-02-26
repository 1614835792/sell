package com.imooc.sell.controller;

import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.Impl.CategoryServiceImpl;
import com.imooc.sell.service.Impl.ProductServiceImpl;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*买家商品*/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        // 1.查询所有的上架商品
        // 2.查询类目（一次性查询）
        // 3.数据拼装
        List<ProductInfo>productInfoList= productService.findUpAll();
       // List<Integer>categoryTypeList=new ArrayList<>();
        //传统方法
     /*   for(ProductInfo list:productInfoList){
            categoryTypeList.add(list.getCategoryType());
        }*/
       //精简方法（java8，lambda）
        List<Integer>categoryTypeList=productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList= categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO>productVOList=new ArrayList<>();
        for(ProductCategory list:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(list.getCategoryName());
            productVO.setType(list.getCategoryType());
            List<ProductInfoVO>productInfolist=new ArrayList<>();
            for(ProductInfo list2:productInfoList){
                if(list.getCategoryType().equals(list2.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                   /* productInfoVO.setProductId(list2.getProductId());
                    productInfoVO.setProductName(list2.getProductName());
                    productInfoVO.setProductPrice(list2.getProductPrice());
                    productInfoVO.setProductDescription(list2.getProductDescription());
                    productInfoVO.setProductIcon(list2.getProductIcon());*/
                    BeanUtils.copyProperties(list2,productInfoVO);
                    productInfolist.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfolist);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
