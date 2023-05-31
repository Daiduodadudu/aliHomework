package com.ebanma.cloud.usertestall.controller.data;

import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;

import java.time.LocalDateTime;

public class TestControllerData {

    public static UserDTO getUserDtoData() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(18);
        userDTO.setCreated(LocalDateTime.now());
        userDTO.setEmail("eeee");
        userDTO.setPassword("123");
        userDTO.setPhone("123456");
        userDTO.setUsername("testName0");
        userDTO.setVersion(1L);
        return userDTO;
    }

    public static UserQueryDTO getUserQueryDtoData() {
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        userQueryDTO.setUsername("testName");
        return userQueryDTO;
    }
}
