package com.imooc.sell.handler;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.ResponseBanKException;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
@Slf4j
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        log.info("[卖家登录验证]没有登录，即将跳转到登录验证："+projectUrlConfig.getWechatMpAuthorize()
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell()
                        .concat("/sell/seller/login")));
            return new ModelAndView("redirect:"
                    .concat(projectUrlConfig.getWechatMpAuthorize()
                    .concat("/sell/wechat/qrAuthorize")
                    .concat("?returnUrl=")
                    .concat(projectUrlConfig.getSell()
                    .concat("/sell/seller/login"))));
    }
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerExecption(SellException e){
         return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
    @ExceptionHandler(value = ResponseBanKException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){

    }

}
