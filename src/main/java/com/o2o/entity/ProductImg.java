package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_product_img
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImg extends BaseEntity<Integer> implements Serializable {
    private Integer id;

    private String url;

    private String desc;

    private Integer priority;

    private Date createTime;

    private Integer productId;

    private static final long serialVersionUID = 1L;


}
