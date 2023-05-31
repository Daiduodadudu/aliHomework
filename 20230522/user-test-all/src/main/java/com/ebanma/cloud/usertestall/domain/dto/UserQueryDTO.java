package com.ebanma.cloud.usertestall.domain.dto;

import java.io.Serializable;

/**
 * @author : 连峰
 * @version $ Id: UserQueryDTO, v 0.1 2023/03/16 10:23 banma- Exp $
 */
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = 6367985269339465567L;
    /**
    *
    */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
