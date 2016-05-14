package com.zhn.mapper;

import com.zhn.model.UserLogin;
import com.zhn.model.UserLoginExample;
import com.zhn.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLoginMapper extends MyMapper<UserLogin> {
    int countByExample(UserLoginExample example);

    int deleteByExample(UserLoginExample example);

    List<UserLogin> selectByExample(UserLoginExample example);

    int updateByExampleSelective(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

    int updateByExample(@Param("record") UserLogin record, @Param("example") UserLoginExample example);
}