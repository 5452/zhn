package com.zhn.mapper;

import com.zhn.model.UserRole;
import com.zhn.model.UserRoleExample;
import com.zhn.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends MyMapper<UserRole> {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    List<UserRole> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);
}