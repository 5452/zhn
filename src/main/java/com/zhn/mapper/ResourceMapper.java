package com.zhn.mapper;

import com.zhn.model.Resource;
import com.zhn.model.ResourceExample;
import com.zhn.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper extends MyMapper<Resource> {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    List<Resource> selectByExample(ResourceExample example);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);
    
    List<Resource> getUserResourcesByUserId(Long userId);
	
	List<Resource> getUserResourcesByEmail(String email);
	
	List<Resource> getRoleResourcesByRoleId(Long roleId);
	
}