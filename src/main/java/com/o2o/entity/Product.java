package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * tb_product
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Integer> implements Serializable {
    private Integer id;

    private String name;

    private String desc;

    private String imgAddress;

    private String normalPrice;

    private Integer point;

    private String promotionPrice;

    private Integer priority;

    private Date createTime;

    private Date updateTime;

    private Integer enableStatus;

    // 图片详情图列表，跟商品是多对一的关系
    private List<ProductImg> productImgList;
    // 商品类别，一件商品仅属于一个商品类别
    private ProductCategory productCategory;
    // 店铺实体类，标明商品属于哪个店铺
    private Shop shop;


//    private Integer productCategoryId;
//
//    private Integer shopId;

    private static final long serialVersionUID = 1L;


}
