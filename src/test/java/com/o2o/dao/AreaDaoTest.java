package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest {

    @Autowired
    AreaDao areaDao;

    @Test
    public void  testQuery(){
        List<Area> areas = areaDao.queryAreas();
        System.out.println(areas);
    }


}
