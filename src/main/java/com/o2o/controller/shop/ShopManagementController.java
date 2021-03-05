package com.o2o.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Area;
import com.o2o.entity.PersonInfo;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopStateEnum;
import com.o2o.service.AreaService;
import com.o2o.service.ShopCategoryService;
import com.o2o.service.ShopService;
import com.o2o.util.CodeUtil;
import com.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int shopId = HttpServletRequestUtil.getInt(request, "shopId");
        if (shopId <= 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/o2o/shopadmin/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId", currentShop.getId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("shopId", shopId);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Shop> shopList = new ArrayList<Shop>();
//        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        PersonInfo user =PersonInfo.builder().id(1).name("test").build();
        try {
            Shop shop = Shop.builder().owner(user).build();
            shopList =  shopService.getShopList(shop,0,100).getShopList();
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        modelMap.put("shoplist", shopList);
        modelMap.put("user", user);
        modelMap.put("success", true);
        return modelMap;
    }

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService
//					.getAllSecondLevelShopCategory();
                    .queryShopCategory(ShopCategory.builder().desc("ALLPR").build());
            areaList = areaService.getAreaList();
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        modelMap.put("areaList", areaList);
        modelMap.put("success", true);
        return modelMap;
    }

    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int shopId = HttpServletRequestUtil.getInt(request, "shopId");
        if (shopId<=0){
            modelMap.put("success", false);
            modelMap.put("errMsg","参数错误");
            return modelMap;
        }
        List<Area> areaList = new ArrayList<Area>();
        Shop byId =null;
        try {
            byId = shopService.getById(shopId);
            areaList = areaService.getAreaList();
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        modelMap.put("shop", byId);
        modelMap.put("areaList", areaList);
        modelMap.put("success", true);
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        MultipartHttpServletRequest multipartRequest = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest
                    .getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        if (shop != null && shopImg != null) {
            try {
				PersonInfo user = (PersonInfo) request.getSession()
						.getAttribute("user");
				shop.setOwner(user);
                ShopExecution se = shopService.addShop(shop, shopImg);
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // 若shop创建成功，则加入session中，作为权限使用
					List<Shop> shopList = (List<Shop>) request.getSession()
							.getAttribute("shopList");
					if (shopList == null && shopList.size() == 0) {
                        shopList = new ArrayList<Shop>();
					}
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        MultipartHttpServletRequest multipartRequest = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest
                    .getFile("shopImg");
        }
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        if (shop != null && shop.getId() != null) {
            try {
                ShopExecution se = shopService.modifyShop(shop, shopImg);
                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        return modelMap;
    }


}
