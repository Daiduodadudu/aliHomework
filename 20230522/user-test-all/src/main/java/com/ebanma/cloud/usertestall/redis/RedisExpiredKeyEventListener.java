package com.ebanma.cloud.usertestall.redis;

import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author : 连峰
 * @version $ Id: RedisExpiredKeyEventListenner, v 0.1 2023/05/08 10:24 banma- Exp $
 */
@Component
public class RedisExpiredKeyEventListener extends KeyExpirationEventMessageListener {
    public RedisExpiredKeyEventListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    //监听指定的库 不重写此方式默认监听所有的库 "__keyevent@*__:expired"
    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        PatternTopic patternTopic = new PatternTopic("__keyevent@0__:expired");
        listenerContainer.addMessageListener(this, patternTopic);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
//        String s = message.toString();
        DeserializingConverter deserializingConverter = new DeserializingConverter();
        Object convert = deserializingConverter.convert(message.getBody());
//        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
        System.out.println("expired key + " + convert + "------" + Thread.currentThread().getName());
        System.out.println(new String(pattern, StandardCharsets.UTF_8));
        try {
            Thread.sleep(5000);
            System.out.println("等待了5秒：" + convert);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
