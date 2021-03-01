package com.o2o.dao;

import com.o2o.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaDao {

    List<Area> queryAreas();

}
