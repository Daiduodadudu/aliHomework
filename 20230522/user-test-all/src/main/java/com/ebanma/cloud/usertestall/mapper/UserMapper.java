package com.ebanma.cloud.usertestall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import com.ebanma.cloud.usertestall.domain.entity.gen.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : 连峰
 * @version $ Id: UserMapper, v 0.1 2023/03/16 11:14 banma- Exp $
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
