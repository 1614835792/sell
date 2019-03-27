package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {
    /*微信公众平台授权URL*/
     public String wechatMpAuthorize;
    /*微信授权平台URL*/
     public String wechatOpenAuthorize;
    /*点餐系统*/
    public String sell;
}
