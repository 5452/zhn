package com.zhn.service.base;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @author 5452
 *	2016年5月17日
 * @param <T>
 */
@Service
public interface IService<T> {

	T getById(Object key);

	int save(T entity);
	
	int saveNotNull(T entity);

	int deleteById(Object key);

	int updateById(T entity);

	int updateByIdNotNull(T entity);
	
	List<T> getByCondition(T object, int pageId, int pageSize);

	/**
	 * 注意：不应该给调用者暴露的方法，此方法应该在类内部使用
	 * @param example
	 * @return
	 */
	List<T> selectByExample(Object example);
}
