package com.ebanma.cloud.usertestall.interceptor;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.exception.BusinessException;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 连峰
 * @version $ Id: RateLimitInterceptor, v 0.1 2023/03/29 13:12 banma- Exp $
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor , ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);
    //速度限制器 QPS限制为1 每秒最多1次请求
    private static final RateLimiter rateLimiter = RateLimiter.create(2);

    private ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //尝试获取令牌
        if (!rateLimiter.tryAcquire()) {
            logger.error("系统已被限流了！");
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        System.out.println("Header/Location" + request.getHeader("Location"));
        System.out.println("Header/Authorization" + request.getHeader("Authorization"));
        System.out.println("Header/ContentType" + request.getContentType());
        System.out.println("RequestURI" + request.getRequestURI());
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}




























