package com.o2o.service;

import com.o2o.base.BaseService;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ShopService extends BaseService<Shop,Integer> {

	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);


	/**
	 * 创建商铺
	 *
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

	/**
	 * 更新店铺信息（从店家角度）
	 * 

	 * @param shopImg

	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

}
