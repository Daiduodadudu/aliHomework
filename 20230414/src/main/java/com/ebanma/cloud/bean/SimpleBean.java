package com.ebanma.cloud.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 倪健土
 * @version $ Id: SimpleBean, v 0.1 2023/04/14 9:17 banma- Exp $
 */
@EnableConfigurationProperties(SimpleBean.class)
@ConfigurationProperties(prefix = "simplebean")
public class SimpleBean {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
