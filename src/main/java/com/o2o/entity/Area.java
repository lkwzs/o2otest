package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_area
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area extends BaseEntity<Integer> implements Serializable {
    private Integer id;

    private String name;

    private Integer priority;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}
