package com.o2o.service.impl;


import com.o2o.base.BaseServiceImpl;
import com.o2o.dao.ShopMapper;
import com.o2o.dto.ImageHolder;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopStateEnum;
import com.o2o.service.ShopService;
import com.o2o.util.FileUtil;
import com.o2o.util.ImageUtil;
import com.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop,Integer> implements ShopService {
	@Autowired
	private ShopMapper shopDao;

//	@Autowired
//	private ShopAuthMapDao shopAuthMapDao;
//	@Autowired
//	private ShopCategoryDao shopCategoryDao;

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex,
									 int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex,
				pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		} else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}

//	@Override
//	public ShopExecution getByEmployeeId(long employeeId)
//			throws RuntimeException {
//		List<Shop> shopList = shopDao.queryByEmployeeId(employeeId);
//		ShopExecution se = new ShopExecution();
//		se.setShopList(shopList);
//		return se;
//	}

//	@Override
//	public Shop getByShopId(long shopId) {
//		return shopDao.queryByShopId(shopId);
//	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public ShopExecution addShop(Shop shop, ImageHolder shopImg)
			throws RuntimeException {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setUpdateTime(new Date());
			if (shop.getShopCategory() != null) {
//				Long shopCategoryId = shop.getShopCategory()
//						.getShopCategoryId();
//				ShopCategory sc = new ShopCategory();
//				sc = shopCategoryDao.queryShopCategoryById(shopCategoryId);
//				ShopCategory parentCategory = new ShopCategory();
//				parentCategory.setShopCategoryId(sc.getParentId());
//				shop.setParentCategory(parentCategory);
			}
			int effectedNum = shopDao.insert(shop);
			if (effectedNum <= 0) {
				throw new RuntimeException("店铺创建失败");
			} else {
				try {
					if (shopImg != null) {
						addShopImg(shop, shopImg);
						effectedNum = shopDao.updateByPrimaryKeySelective(shop);
						if (effectedNum <= 0) {
							throw new RuntimeException("创建图片地址失败");
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("addShopImg error: "
							+ e.getMessage());
				}
				// 执行增加shopAuthMap操作
//				ShopAuthMap shopAuthMap = new ShopAuthMap();
//				shopAuthMap.setEmployeeId(shop.getOwnerId());
//				shopAuthMap.setShopId(shop.getShopId());
//				shopAuthMap.setName("");
//				shopAuthMap.setTitle("Owner");
//				shopAuthMap.setTitleFlag(1);
//				shopAuthMap.setCreateTime(new Date());
//				shopAuthMap.setLastEditTime(new Date());
//				shopAuthMap.setEnableStatus(1);
//				try {
//					effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
//					if (effectedNum <= 0) {
//						throw new RuntimeException("授权创建失败");
//					} else {// 创建成功
						return new ShopExecution(ShopStateEnum.CHECK, shop);
//					}
//				} catch (Exception e) {
//					throw new RuntimeException("insertShopAuthMap error: "
//							+ e.getMessage());
//				}

			}
		} catch (Exception e) {
			throw new RuntimeException("insertShop error: " + e.getMessage());
		}
	}




	@Override
	@Transactional
	public ShopExecution modifyShop(Shop shop, ImageHolder shopImg)
			throws RuntimeException {

		if (shop == null || shop.getId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		} else {
			try {
				if (shopImg != null) {
					Shop tempShop = shopDao.selectByPrimaryKey(shop.getId());
					/*如果数据中有图片则删除*/
					if (tempShop.getImage() != null) {
						FileUtil.deleteFile(tempShop.getImage());
					}
					/*生成图片*/
					addShopImg(shop, shopImg);
				}
				shop.setUpdateTime(new Date());
				int effectedNum = shopDao.updateByPrimaryKeySelective(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {// 创建成功 刷新shop数据
					shop = shopDao.selectByPrimaryKey(shop.getId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyShop error: "
						+ e.getMessage());
			}
		}
	}

	private void addShopImg(Shop shop, ImageHolder shopImg) {
		String dest = FileUtil.getShopImagePath(shop.getId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setImage(shopImgAddr);
	}

}
