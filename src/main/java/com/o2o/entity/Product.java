package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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

    private String promotionPrice;

    private Integer priority;

    private Date createTime;

    private Date updateTime;

    private Integer enableStatus;

    private Integer productCategoryId;

    private Integer shopId;

    private static final long serialVersionUID = 1L;


}
