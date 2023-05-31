package com.ebanma.cloud.usertestall.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Validator;

/**
 * @author : 连峰
 * @version $ Id: UtilConfig, v 0.1 2023/04/03 10:50 banma- Exp $
 */
@Configuration
public class UtilConfig implements ApplicationContextAware {

    @Autowired
    private ApplicationContext context;

    //可以直接呼入validator 注入的类是
    //class org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
    @Autowired()
    private Validator validator;

    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        System.out.println(validator.getClass());
        System.out.println(environment.getProperty("simplebean.name"));
        return new RestTemplate();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context = applicationContext;
        System.out.println(context.getBean("restTemplate"));

    }
}
