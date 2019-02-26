package com.imooc.sell.service.Impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PreInsertEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SellerServiceImplTest {
    private static final String openid="abc";
    @Autowired
    private SellerService sellerService;
    @Test
    public void findSellerInfoByOpenid() {
       SellerInfo sellerInfo= sellerService.findSellerInfoByOpenid(openid);
        Assert.assertNotNull(sellerInfo);
    }
}