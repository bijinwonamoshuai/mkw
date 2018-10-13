package com.mkw.core.service.impl;

import com.mkw.core.entity.FoodPackage;
import com.mkw.core.mapper.FoodPackageMapper;
import com.mkw.core.service.FoodPackageService;
import com.mkw.core.common.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 菜品表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-10
 */
@Service
@Transactional
public class FoodPackageServiceImpl extends SuperServiceImpl<FoodPackageMapper, FoodPackage> implements FoodPackageService {
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			// TODO Auto-generated method stub
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			// TODO Auto-generated method stub
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo remoteModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			// TODO Auto-generated method stub
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editStatusById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			// TODO Auto-generated method stub
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
