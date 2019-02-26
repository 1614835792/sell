package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
           ProductCategory productCategory= repository.findOne(1);
           System.out.println(productCategory);
    }
    @Test
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("饮料");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }
    @Test
    public void updateTest(){
        ProductCategory productCategory=repository.findOne(4);;
        if(productCategory==null){
               return;
        }
        productCategory.setCategoryName("蛋糕");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }
    @Test
    public void findAllTset(){
        List<ProductCategory>list=new ArrayList<>();
        list=repository.findAll();
        System.out.println(list);
    }
    @Test
    public void deleteTest(){
        repository.delete(2);
    }
    @Test
    public void findByCategoryTypeTest(){
        List<Integer> list= Arrays.asList(2,3,4);
        List<ProductCategory>result=repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}