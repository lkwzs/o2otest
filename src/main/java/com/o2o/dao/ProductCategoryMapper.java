package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductCategoryMapper继承基类
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory, Integer> {

     int  batchInsertProductCategory(List<ProductCategory> productCategoryList);

     int  deleteByPrimaryKeyAndShopId(@Param("id") int id,@Param("shopId") int shopId);

}
