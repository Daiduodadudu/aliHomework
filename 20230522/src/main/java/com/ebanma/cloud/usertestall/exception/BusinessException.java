package com.ebanma.cloud.usertestall.exception;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * @author : 连峰
 * @version $ Id: BusinessException, v 0.1 2023/03/29 13:25 banma- Exp $
 */
public class BusinessException extends RuntimeException{

    private final String code;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
























