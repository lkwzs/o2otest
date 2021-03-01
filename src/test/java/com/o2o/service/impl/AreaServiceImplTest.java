package com.o2o.service.impl;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.service.AreaService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceImplTest extends BaseTest {


    Logger logger = LoggerFactory.getLogger(AreaServiceImplTest.class);

    @Autowired
    AreaService areaService;

    @Test
    public void tsetGetList(){
        logger.info("==start==");
        logger.error("err___end");
        logger.info("==end==");
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList);


    }

}
