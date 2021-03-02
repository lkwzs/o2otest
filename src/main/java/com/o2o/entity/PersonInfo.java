package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_person_info
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo extends BaseEntity<Integer> implements Serializable {
    private Integer id;

    private String name;

    private String profileImg;

    private String email;

    private String gender;

    /**
     * 0:禁用状态；1：正常使用
     */
    private Integer enableStatus1;

    /**
     * 1:顾客；2:店家;3:超级管理员
     */
    private Integer userType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}
