package com.ebanma.cloud.usertestall.config;

import com.ebanma.cloud.usertestall.redis.RedisExpiredKeyEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author : 连峰
 * @version $ Id: RedisListenerConfig, v 0.1 2023/05/08 10:17 banma- Exp $
 */
@Configuration
public class RedisListenerConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer con = new RedisMessageListenerContainer();
        con.setConnectionFactory(connectionFactory);
        return con;
    }
}
