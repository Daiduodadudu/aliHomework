package com.ebanma.cloud.usertestall.service;

import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;

import java.util.List;

/**
 * @author : 连峰
 * @version $ Id: UserService, v 0.1 2023/03/16 11:16 banma- Exp $
 */
public interface UserService {

    int save(UserDTO userDTO);
    int update(Long id, UserDTO userDTO);
    int delete(Long id);
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);

    UserVO getUser(String username);
}

