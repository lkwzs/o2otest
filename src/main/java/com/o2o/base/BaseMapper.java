package com.o2o.base;


import java.util.List;

public interface BaseMapper<Model, PK> {
	
    int deleteByPrimaryKey(PK id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(PK id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
    
	
	public List<Model> select(Object filters);
	
	
//	public List<Model> select(Object filters, PageRowBounds rowBounds);
	
}
