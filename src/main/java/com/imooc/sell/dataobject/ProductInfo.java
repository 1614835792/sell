package com.imooc.sell.dataobject;

import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.EnumUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus=ProductStatusEnum.up.getCode();
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
    @Ignore
    public ProductStatusEnum getProductStatusEnum(){
          return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
