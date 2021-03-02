package com.o2o.base;

import java.util.List;

public interface BaseService <T, PK> {
	/**
	 * 功能：根据主键ID值删除记录
	 * @param id
	 */
	public boolean removeById(PK id);
	
	/**
	 * 功能：根据主键ID值查询记录
	 * @param id
	 * @return T
	 */
	public T getById(PK id);
	
	/**
	 * 功能：保存
	 * @param entity
	 * @return T
	 */
	public T save(T entity);
	
	/**
	 * 功能：保存有值的字段
	 * @param entity
	 * @return T
	 */
	public T saveSelective(T entity);
	
	/**
	 * 功能：更新
	 * @param entity
	 * @return T
	 */
	public T updateById(T entity);
	
	/**
	 * 功能：更新有值的字段
	 * @param entity
	 * @return T
	 */
	public T updateByIdSelective(T entity);
	
	/**
	 * 功能：根据主键ID值判断是否插入或是更新数据
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity);
	
	/**
	 * 功能：保存实体前的预处理，子类实现该接口可定义自己的主键生成策略
	 * @param entity
	 */
	public void preSave(T entity);
	
	/**
	 * 功能：根据条件查询结果集封装成实体对象
	 */
	public List<T> list(Object filters);
	
}
