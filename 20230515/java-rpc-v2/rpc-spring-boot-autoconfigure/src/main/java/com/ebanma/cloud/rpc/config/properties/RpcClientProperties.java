package com.ebanma.cloud.rpc.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rpc.client")
@Data
public class RpcClientProperties {

    /**
     * 服务消费者名称
     */
    private String consumerName = "";

}