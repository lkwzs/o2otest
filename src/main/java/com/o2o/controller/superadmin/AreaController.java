package com.o2o.controller.superadmin;

import com.o2o.entity.Area;
import com.o2o.service.AreaService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {

    Logger logger = LoggerFactory.getLogger(AreaController.class);


    @Autowired
    AreaService areaService;

    @RequestMapping(value = "listarea",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        Map<String, Object> map = new HashMap<>();
        List<Area> areaList = areaService.getAreaList();
        try{
            map.put("data",areaList);
            map.put("total",areaList.size());
        }catch (Exception e){
            map.put("success",false);
            map.put("msg",e.toString());
        }
        return map;
    }


}
