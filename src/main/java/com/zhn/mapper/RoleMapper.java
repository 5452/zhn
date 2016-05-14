package com.zhn.mapper;

import com.zhn.model.Role;
import com.zhn.model.RoleExample;
import com.zhn.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends MyMapper<Role> {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    List<Role> selectByExample(RoleExample example);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);
}