package com.mkw.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.Role;
import com.mkw.core.entity.RolePermission;
import com.mkw.core.mapper.RoleMapper;
import com.mkw.core.service.RolePermissionService;
import com.mkw.core.service.RoleService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
@Service
@Transactional
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements RoleService {
	@Resource
	private RolePermissionService rolePermissionService;

	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			Role role = dataInfo.getRequestData().toJavaObject(Role.class);
			List<Role> list = selectList(role);
			if (null != list && 0 < list.size()) {
				return fail(dataInfo, ERROR_ROLE_NAME);
			}
			insert(role, authorId);
			
			JSONArray permissionIds = dataInfo.getRequestData().getJSONArray("permissionIds");
			if (null != permissionIds && 0 < permissionIds.size()) {
				int size = permissionIds.size();
				String[] ids = new String[size];
				for (int i = 0; i < size; i++) {
					String permissionId = String.valueOf(permissionIds.get(i));
					ids[i] = permissionId;
				}
				rolePermissionService.deleteRolePermission(ids, role.getId(), authorId, new Date());
				
				for (String permissionId : ids) {
					RolePermission rolePermission = new RolePermission();
					rolePermission.setRoleId(role.getId());
					rolePermission.setPermissionId(permissionId);
					rolePermissionService.insert(rolePermission, authorId);
				}
			}
			
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			Role role = dataInfo.getRequestData().toJavaObject(Role.class);
			Role tempRole = new Role();
			tempRole.setName(role.getName());
			List<Role> list = selectList(tempRole);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && list.get(0).getId().equals(role.getId())) {
					// 自己
				} else {
					return fail(dataInfo, ERROR_ROLE_NAME);
				}
			}

			update(role, authorId);
			
			JSONArray permissionIds = dataInfo.getRequestData().getJSONArray("permissionIds");
			if (null != permissionIds && 0 < permissionIds.size()) {
				int size = permissionIds.size();
				String[] ids = new String[size];
				for (int i = 0; i < size; i++) {
					String permissionId = String.valueOf(permissionIds.get(i));
					ids[i] = permissionId;
				}
				
				String roleId = role.getId();
				rolePermissionService.deleteRolePermission(ids, roleId, authorId, new Date());
				
				for (String permissionId : ids) {
					RolePermission rolePermission = new RolePermission();
					rolePermission.setRoleId(roleId);
					rolePermission.setPermissionId(permissionId);
					rolePermissionService.insert(rolePermission, authorId);
				}
			}
			
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo remoteModelById(DataInfo dataInfo, String authorId)
			throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String fid = dataInfo.getRequestData().getString("id");
			Integer id = Integer.parseInt(fid);
			mapper.deleteById(id);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editStatusById(DataInfo dataInfo, String authorId)
			throws Exception {
		Role role = new Role();
		if (StringUtils.isNotBlank(authorId)) {
			Integer status = Integer.parseInt(dataInfo.getRequestData()
					.getString("status"));
			String id = dataInfo.getRequestData().getString("id");
			role.setId(id);
			role.setStatus(status);
			mapper.updateById(role);
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
		Role role = dataInfo.getRequestData().toJavaObject(Role.class);
		List<Role> selectList = selectList(role);
		return success(dataInfo, selectList);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}

	@Override
	public JSONArray findAllRoleByStatus(int status) throws Exception {
		Role role = new Role();
		role.setStatus(status);
		List<Role> list = selectList(role);
		if (null != list && 0 < list.size()) {
			JSONArray jsonArray = new JSONArray();
			for (Role temp : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("label", temp.getName());
				jsonObject.put("value", temp.getId());
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		}
		return null;
	}
}
