package com.imooc.sell.enums;

import com.imooc.sell.utils.EnumUtil;
import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;
    private String msg;
    private Integer code;

    OrderStatusEnum(Integer code,String msg) {
        this.msg = msg;
        this.code = code;
    }
}
