package com.o2o.dao;

import com.o2o.base.BaseMapper;
import com.o2o.entity.WechatAuth;
import org.springframework.stereotype.Repository;

/**
 * WechatAuthMapper继承基类
 */
@Repository
public interface WechatAuthMapper extends BaseMapper<WechatAuth, Integer> {
}
