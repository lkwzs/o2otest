package com.o2o.service;

import com.o2o.base.BaseService;
import com.o2o.dto.ProductCategoryExecution;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.ProductCategory;
import com.o2o.entity.Shop;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;


public interface ProductCategoryService extends BaseService<ProductCategory,Integer> {


    ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws RuntimeException
     */
    ProductCategoryExecution deleteProductCategory(int productCategoryId, int shopId);

}
