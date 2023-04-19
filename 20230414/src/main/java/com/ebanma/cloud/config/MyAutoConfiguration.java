package com.ebanma.cloud.config;

import com.ebanma.cloud.bean.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 倪健土
 * @version $ Id: MyAutoConfiguration, v 0.1 2023/04/14 9:20 banma- Exp $
 */
@Configuration
@ConditionalOnBean(ConfigMarker.class)
public class MyAutoConfiguration {

    static {
        System.out.println("MyAutoConfiguration init....");
    }

    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }
}