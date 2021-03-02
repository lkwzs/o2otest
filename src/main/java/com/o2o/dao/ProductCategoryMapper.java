package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.ProductCategory;
import org.springframework.stereotype.Repository;

/**
 * ProductCategoryMapper继承基类
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory, Integer> {
}
