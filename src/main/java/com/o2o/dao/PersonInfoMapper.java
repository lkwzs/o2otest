package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.PersonInfo;
import org.springframework.stereotype.Repository;

/**
 * PersonInfoMapper继承基类
 */
@Repository
public interface PersonInfoMapper extends BaseMapper<PersonInfo, Integer> {
}
