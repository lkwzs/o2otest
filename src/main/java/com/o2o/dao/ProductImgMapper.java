package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.ProductImg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductImgMapper继承基类
 */
@Repository
public interface ProductImgMapper extends BaseMapper<ProductImg, Integer> {
    /**
     * 列出某个商品的详情图列表
     *
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品下的所有详情图
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);
}
