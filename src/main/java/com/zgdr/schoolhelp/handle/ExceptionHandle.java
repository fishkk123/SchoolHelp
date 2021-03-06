package com.zgdr.schoolhelp.handle;

import com.zgdr.schoolhelp.domain.Result;
import com.zgdr.schoolhelp.enums.GlobalResultEnum;
import com.zgdr.schoolhelp.exception.ExceptionParent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ExceptionHandel
 * 异常处理类
 *
 * @author hengyumo
 * @version 1.0
 * @since 2019/4/17
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class) //自定义处理异常
    @ResponseBody
    public Result handle(Exception e) throws Exception{
        if (e instanceof ExceptionParent) {
            // 系统内部异常
            ExceptionParent exceptionParent = (ExceptionParent) e;
            return new Result<Object>(exceptionParent.getResultEnum(), null);
        } else {
            logger.error("【系统异常】", e);
            throw e;
//            return new Result<Object>(GlobalResultEnum.UNKNOW_ERROR, null);
        }
    }
}
