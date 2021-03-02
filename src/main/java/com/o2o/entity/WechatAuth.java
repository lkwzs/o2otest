package com.o2o.entity;

import com.o2o.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_wechat_auth
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WechatAuth extends BaseEntity<Integer> implements Serializable {
    private Integer id;

    private Integer userId;

    private String openId;

    private Date createTime;

    private static final long serialVersionUID = 1L;


}
