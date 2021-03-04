package com.o2o.service;

import com.o2o.base.BaseMapper;
import com.o2o.base.BaseService;
import com.o2o.entity.ShopCategory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;


public interface ShopCategoryService extends BaseService<ShopCategory, Integer> {



    List<ShopCategory> queryShopCategory(ShopCategory shopCategoryInfo);

    public List<ShopCategory> getAllSecondLevelShopCategory() throws IOException;
}
