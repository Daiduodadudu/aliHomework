package com.ebanma.cloud.usertestall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;
import com.ebanma.cloud.usertestall.mapper.UserMapper;
import com.ebanma.cloud.usertestall.service.UserService;
import com.ebanma.cloud.usertestall.util.ValidatorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : 连峰
 * @version $ Id: UserServiceImpl, v 0.1 2023/03/16 11:20 banma- Exp $
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
//        userDO.setUsername(userDTO.getUsername());
//        userDO.setCreated(LocalDateTime.now());
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(id);
        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {
        //手动校验
        ValidatorUtils.validate(pageQuery);

        Page page = new Page(pageQuery.getPageNo(), pageQuery.getPageSize());

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), userDO);
        QueryWrapper<UserDO> userDOQueryWrapper = new QueryWrapper<>(userDO);
        Page<UserDO> userDOPage = userMapper.selectPage(page, userDOQueryWrapper);

        PageResult pageResult = new PageResult();
        pageResult.setPageNo((int) userDOPage.getCurrent());
        pageResult.setPageSize((int) userDOPage.getSize());
        pageResult.setTotal(userDOPage.getTotal());
        pageResult.setPageNum(userDOPage.getPages());

        List<UserDTO> userDTOList = Optional.ofNullable(userDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());
        pageResult.setData(userDTOList);

        return pageResult;
    }

//    @PreAuthorize("#username == authentication.principal.username")
//    @PostAuthorize("returnObject.username == authentication.principal.username") 对返回值限制
//    @PreFilter("filterObject.username == authentication.principal.username") 对输入参数过滤
//    @PreFilter(filterTarget = "username", value = "admin")   对输入参数过滤
    //@PostFilter("filterObject.name == authentication.principal.username") 对返回值过滤
    @Override
    public UserVO getUser(String username) {
        return new UserVO();
    }
}












