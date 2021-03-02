package com.o2o.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class BaseServiceImpl<T extends BaseEntity, PK> implements BaseService<T, PK> {

	@Autowired
	private BaseMapper<T, PK> baseMapper;
	
	/**
	 * 功能：根据主键ID值删除记录
	 * @param id
	 * @return boolean
	 */
	public boolean removeById(PK id) {
		return baseMapper.deleteByPrimaryKey(id) > 0;
	}

	/**
	 * 功能：根据主键ID值查询记录
	 * @param id
	 * @return T
	 */
	public T getById(PK id){
		return baseMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 功能：保存实体前的预处理，子类可覆盖此方法实现自己的主键生成策略
	 * 默认主键类型为字符串
	 * @param entity
	 */
	@Override
	public void preSave(T entity){
		if(ObjectUtils.isEmpty(entity.getId())) {
			entity.setId(ObjectId.get().toString());
		}
	}
	
	/**
	 * 功能：插入
	 * @param entity
	 * @return T
	 */
	public T save(T entity) {
		//设置主键
		preSave(entity);
		int count = baseMapper.insert(entity);
		return entity;
	}
	
	/**
	 * 功能：更新
	 * @param entity
	 * @return T
	 */
	public T updateById(T entity) {
		int count = baseMapper.updateByPrimaryKey(entity);
		return entity;
	}
	
	@Override
	public T saveSelective(T entity) {
		//设置主键
		preSave(entity);
		int count = baseMapper.insertSelective(entity);
		return entity;
	}

	@Override
	public T updateByIdSelective(T entity) {
		int count = baseMapper.updateByPrimaryKeySelective(entity);
		return entity;
	}

	/**
	 * 功能：根据主键ID值判断是否插入或是更新数据
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity) {
		if(ObjectUtils.isEmpty(entity.getId())) {
			save(entity);
		}else{
			updateById(entity);
		}
		return entity;
	}
	
	/**
	 * 功能：根据条件查询结果集封装成实体对象
	*/ 
	public List<T> list(Object filters) {
		return baseMapper.select(filters);
	}
	
}
