package com.ebanma.cloud.usertestall.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author : 连峰
 * @version $ Id: ValidatorUtils, v 0.1 2023/03/30 9:57 banma- Exp $
 */
public class ValidatorUtils {
    //校验器
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    //参数校验
    public static <T> void validate(T object, Class... groups) {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        //如果校验结果不为空
        if (!CollectionUtils.isEmpty(validate)) {
            StringBuilder exceptionMessage = new StringBuilder();
            validate.forEach(result -> {
                exceptionMessage.append(result.getMessage());
            });
            throw new RuntimeException(exceptionMessage.toString());
        }
    }
}

















