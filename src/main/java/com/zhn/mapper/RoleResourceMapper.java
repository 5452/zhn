package com.zhn.mapper;

import com.zhn.model.RoleResource;
import com.zhn.model.RoleResourceExample;
import com.zhn.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleResourceMapper extends MyMapper<RoleResource> {
    int countByExample(RoleResourceExample example);

    int deleteByExample(RoleResourceExample example);

    List<RoleResource> selectByExample(RoleResourceExample example);

    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);
}