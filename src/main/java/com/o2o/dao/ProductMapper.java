package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * ProductMapper继承基类
 */
@Repository
public interface ProductMapper extends BaseMapper<Product, Integer> {
}
