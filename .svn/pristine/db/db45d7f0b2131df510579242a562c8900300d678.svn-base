package com.mkw.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.FoodPackage;
import com.mkw.core.entity.RoomFood;
import com.mkw.core.mapper.RoomFoodMapper;
import com.mkw.core.service.FoodPackageService;
import com.mkw.core.service.RoomFoodService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 门店菜品组的菜品表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
@Service
@Transactional
public class RoomFoodServiceImpl extends SuperServiceImpl<RoomFoodMapper, RoomFood> implements RoomFoodService {
	@Resource
	private FoodPackageService foodPackageService;
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String groupId, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId) && StringUtils.isNotBlank(groupId)) {
			String packageId = dataInfo.getRequestData().getString("packageId");
			FoodPackage pk = foodPackageService.select(packageId);
			if (null != pk) {
				RoomFood roomFood = new RoomFood();
				roomFood.setGroupId(groupId);
				roomFood.setPackageId(packageId);
				List<RoomFood> list = selectList(roomFood);
				if (null == list || 0 == list.size()) {
					roomFood.setShowName(pk.getName());
					roomFood.setShowPrice(pk.getPrice());
					roomFood.setShowStock(pk.getNumberRemaining());
					insert(roomFood, authorId);
				}
			}
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			// id,showName,showPrice,showStock
			RoomFood roomFood = dataInfo.getRequestData().toJavaObject(RoomFood.class);
			update(roomFood, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo remoteModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String id = dataInfo.getRequestData().getString("id");
			delete(id, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editStatusById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String id = dataInfo.getRequestData().getString("id");
			int status = dataInfo.getRequestData().getIntValue("status");
			updateStatus(id, status, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo findModelById(DataInfo dataInfo) throws Exception {
		// TODO Auto-generated method stub
		return success(dataInfo, null);
	}

	@Override
	public DataInfo findListByModel(DataInfo dataInfo) throws Exception {
		// TODO Auto-generated method stub
		return success(dataInfo, null);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}
}
