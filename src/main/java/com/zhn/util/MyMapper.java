package com.zhn.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyBatis generator 生成Mapper时，继承用
 * @author 5452
 *	2016年5月12日
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
