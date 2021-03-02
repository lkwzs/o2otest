package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.Shop;
import org.springframework.stereotype.Repository;

/**
 * ShopMapper继承基类
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop, Integer> {

    /**
     * 新增店铺
     *
     * @param shop
     * @return effectedNum
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop
     * @return effectedNum
     */
    int updateShop(Shop shop);

}
