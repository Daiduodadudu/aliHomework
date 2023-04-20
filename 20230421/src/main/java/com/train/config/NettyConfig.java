package com.train.config;

/**
 * @author 倪健土
 * @version $ Id: NettyConfig, v 0.1 2023/04/19 16:25 banma- Exp $
 */
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {
    private int port;//netty监听的端口
    private String path;//websocket访问路径
}
