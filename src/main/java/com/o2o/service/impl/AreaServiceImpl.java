package com.o2o.service.impl;

import com.o2o.dao.AreaMapper;
import com.o2o.entity.Area;
import com.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryAreas();
    }
}
