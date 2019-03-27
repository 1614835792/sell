package com.imooc.sell.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /*公众平台id*/
    @Value("mpAppId")
    private String mpAppId;
    /*公众平台密钥*/
    @Value("mpAppSecret")
    private String mpAppSecret;
    /*开放平台id*/
    @Value("openAppId")
    private String openAppId;
    /*开放平台密钥*/
    @Value("openSecret")
    private String openSecret;
    @Value("openAppSecrect")
    private String openAppSecret;
    /*商户号*/
    @Value("mchId")
    private String mchId;
    /*商户密钥*/
    @Value("mchKey")
    private String mchKey;
    /*商户证书路径*/
    @Value("KeyPath")
    private String KeyPath;
    /*微信支付异步通知地址*/
    @Value("notifyUrl")
    private String notifyUrl;
    /*微信模板ID*/
    private Map<String,String> templateId;
}
