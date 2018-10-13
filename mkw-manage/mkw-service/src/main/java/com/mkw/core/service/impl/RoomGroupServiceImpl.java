package com.mkw.core.service.impl;

import java.util.List;

import com.mkw.core.entity.RoomGroup;
import com.mkw.core.mapper.RoomGroupMapper;
import com.mkw.core.service.RoomGroupService;
import com.mkw.core.common.SuperServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 门店菜品组表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
@Service
@Transactional
public class RoomGroupServiceImpl extends SuperServiceImpl<RoomGroupMapper, RoomGroup> implements RoomGroupService {
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String roomId, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String name = dataInfo.getRequestData().getString("name");
			Integer status = dataInfo.getRequestData().getInteger("status");
			RoomGroup roomGroup = new RoomGroup();
			roomGroup.setName(name);
			roomGroup.setRoomId(roomId);
			roomGroup.setStatus(status);
			List<RoomGroup> list = selectList(roomGroup);
			if (null != list && 0 < list.size()) {
				return fail(dataInfo, ERROR_GROUP_NAME);
			}
			
			insert(roomGroup, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String roomId, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String id = dataInfo.getRequestData().getString("id");
			String name = dataInfo.getRequestData().getString("name");
			Integer status = dataInfo.getRequestData().getInteger("status");
			RoomGroup roomGroup = new RoomGroup();
			roomGroup.setName(name);
			roomGroup.setRoomId(roomId);
			roomGroup.setStatus(status);
			List<RoomGroup> list = selectList(roomGroup);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && id.equals(list.get(0).getId())) {
					// 自己
				} else {
					return fail(dataInfo, ERROR_GROUP_NAME);
				}
			}
			
			roomGroup.setId(id);
			update(roomGroup, authorId);
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
