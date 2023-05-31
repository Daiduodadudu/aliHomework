package com.ebanma.cloud.usertestall.exception;

import com.ebanma.cloud.usertestall.domain.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 连峰
 * @version $ Id: GlobalExceptionHandler, v 0.1 2023/03/16 10:47 banma- Exp $
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(BusinessException e) {
        logger.error("捕捉到业务异常", e);
        return Result.fail(e);
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeExceptionHandle(RuntimeException e) {
        logger.error("捕捉到运行时异常：", e);
        return Result.fail(e);
    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Result throwableHandle(Throwable throwable) {
        logger.error("捕捉到Throwable异常：", throwable);
        return Result.fail(throwable);
    }

    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }
}
