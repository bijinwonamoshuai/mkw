package com.mkw.core.service.impl;

import java.util.List;
import com.mkw.core.entity.OrderItem;
import com.mkw.core.mapper.OrderItemMapper;
import com.mkw.core.service.OrderItemService;
import com.mkw.core.common.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
@Service
@Transactional
public class OrderItemServiceImpl extends SuperServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			OrderItem orderItem = dataInfo.getRequestData().toJavaObject(OrderItem.class);
			List<OrderItem> list = selectList(orderItem);
			if (null != list && 0 < list.size()) {
				return fail(dataInfo, ERROR_ROOM_NAME);
			}
			insert(orderItem, authorId);
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
			OrderItem orderItem = dataInfo.getRequestData().toJavaObject(OrderItem.class);
			OrderItem temporderItem = new OrderItem();
			temporderItem.setOrderStatus(orderItem.getOrderStatus());
			List<OrderItem> list = selectList(temporderItem);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && list.get(0).getId().equals(orderItem.getId())) {
					// 自己
				} else {
					return fail(dataInfo, ERROR_ROOM_NAME);
				}
			}
			update(orderItem, authorId);	
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
		OrderItem orderItem = dataInfo.getRequestData().toJavaObject(OrderItem.class);
		List<OrderItem> selectList = selectList(orderItem);
		return success(dataInfo, selectList);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}
}
