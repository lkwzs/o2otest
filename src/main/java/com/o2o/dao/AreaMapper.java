package com.o2o.dao;


import com.o2o.base.BaseMapper;
import com.o2o.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AreaMapper继承基类
 */
@Repository
public interface AreaMapper extends BaseMapper<Area, Integer> {

    /**
     * 列出地域列表
     *
     * @param
     * @return
     */
    List<Area> queryAreas();

}
