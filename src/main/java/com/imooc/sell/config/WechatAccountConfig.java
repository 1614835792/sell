package com.imooc.sell.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    @Value("mpAppId")
    private String mpAppId;
    @Value("mpAppSecret")
    private String mpAppSecret;
}
