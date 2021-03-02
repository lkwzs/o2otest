package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_shop_category
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopCategory extends BaseEntity<Integer> implements Serializable {
    private Integer id;

    private String name;

    private String desc;

    private String image;

    private Integer priority;

    private Date createTime;

    private Date updateTime;

    private Integer parentId;

    private static final long serialVersionUID = 1L;

}
