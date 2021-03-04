package com.o2o.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.o2o.base.BaseService;
import com.o2o.base.BaseServiceImpl;
import com.o2o.dao.ShopCategoryMapper;
import com.o2o.dao.ShopMapper;
import com.o2o.entity.ShopCategory;
import com.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl  extends BaseServiceImpl<ShopCategory,Integer> implements ShopCategoryService {

    @Autowired
     private  ShopCategoryMapper shopCategoryMapper;


    @Override
    public List<ShopCategory> queryShopCategory(ShopCategory shopCategoryInfo) {
        return shopCategoryMapper.queryShopCategory(shopCategoryInfo);
    }

    @Override
    public List<ShopCategory> getAllSecondLevelShopCategory()
            throws IOException {
//        String key = SCLISTKEY + "ALLSECOND";
//        List<ShopCategory> shopCategoryList = null;
//        ObjectMapper mapper = new ObjectMapper();
//        if (!jedisKeys.exists(key)) {
//            ShopCategory shopCategoryCondition = new ShopCategory();
//            // 当shopCategoryDesc不为空的时候，查询的条件会变为 where parent_id is not null
//            shopCategoryCondition.setShopCategoryDesc("ALLSECOND");
//            shopCategoryList = shopCategoryDao
//                    .queryShopCategory(shopCategoryCondition);
//            String jsonString = mapper.writeValueAsString(shopCategoryList);
//            jedisStrings.set(key, jsonString);
//        } else {
//            String jsonString = jedisStrings.get(key);
//            JavaType javaType = mapper.getTypeFactory()
//                    .constructParametricType(ArrayList.class,
//                            ShopCategory.class);
//            shopCategoryList = mapper.readValue(jsonString, javaType);
//        }
//        return shopCategoryList;
        return  null;
    }
}
