package com.mkw.core.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.OrderItemDetail;
import com.mkw.core.mapper.OrderItemDetailMapper;
import com.mkw.core.service.OrderItemDetailService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-06
 */
@Service
@Transactional
public class OrderItemDetailServiceImpl extends SuperServiceImpl<OrderItemDetailMapper, OrderItemDetail> implements OrderItemDetailService {
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			OrderItemDetail orderItemDetail = dataInfo.getRequestData().toJavaObject(OrderItemDetail.class);
				List<OrderItemDetail> list = selectList(orderItemDetail);
				if (null != list && 0 < list.size()) {
					return fail(dataInfo, ERROR_ROOM_NAME);
				}
				insert(orderItemDetail, authorId);
				return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			OrderItemDetail orderItemDetail = dataInfo.getRequestData().toJavaObject(OrderItemDetail.class);
			OrderItemDetail temporderItemDetail = new OrderItemDetail();
			temporderItemDetail.setName(orderItemDetail.getName());
				List<OrderItemDetail> list = selectList(temporderItemDetail);
				if (null != list && 0 < list.size()) {
					if (1 == list.size() && list.get(0).getId().equals(orderItemDetail.getId())) {
						// 自己
					} else {
						return fail(dataInfo, ERROR_ROOM_NAME);
					}
				}
				update(orderItemDetail, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo remoteModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String fid=dataInfo.getRequestData().getString("id");
			Integer id=Integer.parseInt(fid);
			mapper.deleteById(id);			
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editStatusById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			OrderItemDetail orderItemDetail = dataInfo.getRequestData().toJavaObject(OrderItemDetail.class);
			OrderItemDetail temporderItemDetail = new OrderItemDetail();
			temporderItemDetail.setName(orderItemDetail.getName());
			List<OrderItemDetail> list = selectList(temporderItemDetail);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && list.get(0).getId().equals(orderItemDetail.getId())) {
					// 自己
				} else {
					return fail(dataInfo, ERROR_ROOM_NAME);
				}
			}
			update(orderItemDetail, authorId);	
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo findModelById(DataInfo dataInfo) throws Exception {
		String id = dataInfo.getRequestData().getString("id");
		return success(dataInfo, select(id));
	}

	@Override
	public DataInfo findListByModel(DataInfo dataInfo) throws Exception {
		OrderItemDetail orderItemDeatil = dataInfo.getRequestData().toJavaObject(OrderItemDetail.class);
		List<OrderItemDetail> selectList = selectList(orderItemDeatil);
		return success(dataInfo, selectList);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}
}
