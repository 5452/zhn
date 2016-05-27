package com.zhn.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 5452
 *	2016年5月17日
 */
public abstract class BaseService<T> implements IService<T> {

	@Autowired
    protected Mapper<T> mapper;
	
	@Override
	public T getById(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int save(T entity) {
		return mapper.insert(entity);
	}

	@Override
	public int saveNotNull(T entity) {
		return mapper.insertSelective(entity);
	}

	@Override
	public int deleteById(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateById(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByIdNotNull(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}

}
