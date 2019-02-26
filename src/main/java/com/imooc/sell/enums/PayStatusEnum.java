package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;
    private String msg;
    private Integer code;

    PayStatusEnum(Integer code,String msg) {
        this.msg = msg;
        this.code = code;
    }
}
