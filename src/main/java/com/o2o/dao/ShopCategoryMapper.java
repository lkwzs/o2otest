package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShopCategoryMapper继承基类
 */
@Repository
public interface ShopCategoryMapper extends BaseMapper<ShopCategory, Integer> {



    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryInfo);
}
