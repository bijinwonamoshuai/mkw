package com.mkw.core.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.RolePermission;
import com.mkw.core.mapper.RolePermissionMapper;
import com.mkw.core.service.RolePermissionService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends SuperServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
		RolePermission rolePermission = dataInfo.getRequestData().toJavaObject(RolePermission.class);
		List<RolePermission> list = selectList(rolePermission);
		if (null != list && 0 < list.size()) {
			return fail(dataInfo, ERROR_ROOM_NAME);
		}
		insert(rolePermission, authorId);
		return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			RolePermission rolePermission = dataInfo.getRequestData().toJavaObject(RolePermission.class);
			
			RolePermission tempRolePermission = new RolePermission();
			tempRolePermission.setId(rolePermission.getId());
			List<RolePermission> list = selectList(tempRolePermission);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && list.get(0).getId().equals(rolePermission.getId())) {
					// 自己
				} else {
					return fail(dataInfo, ERROR_ROOM_NAME);
				}
			}
			update(rolePermission, authorId);
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
		RolePermission rolePermission=new RolePermission();
		if (StringUtils.isNotBlank(authorId)) {
			Integer status=Integer.parseInt(dataInfo.getRequestData().getString("status"));
			String id=dataInfo.getRequestData().getString("id");
			rolePermission.setId(id);
			rolePermission.setStatus(status);
			mapper.updateById(rolePermission);
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
		RolePermission rolePermission = dataInfo.getRequestData().toJavaObject(RolePermission.class);
		List<RolePermission> selectList = selectList(rolePermission);
		return success(dataInfo, selectList);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}

	@Override
	public int deleteRolePermission(String[] permissionIds, String roleId,
			String deleter, Date dtime) throws Exception {
		return mapper.deleteRolePermission(permissionIds, roleId, deleter, dtime);
	}
}
