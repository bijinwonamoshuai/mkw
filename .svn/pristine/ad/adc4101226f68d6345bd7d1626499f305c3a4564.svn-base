package com.mkw.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.Permission;
import com.mkw.core.entity.RolePermission;
import com.mkw.core.mapper.PermissionMapper;
import com.mkw.core.service.PermissionService;
import com.mkw.core.service.RolePermissionService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
@Service
@Transactional
public class PermissionServiceImpl extends SuperServiceImpl<PermissionMapper, Permission> implements PermissionService {
	@Resource
	private RolePermissionService rolePermissionService;
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			Permission permission = dataInfo.getRequestData().toJavaObject(Permission.class);
			insert(permission, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
	        Permission permission = dataInfo.getRequestData().toJavaObject(Permission.class);
	        Permission temppermission = new Permission();
	        temppermission.setMenuName(permission.getMenuName());
			List<Permission> list = selectList(temppermission);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && list.get(0).getId().equals(permission.getId())) {
					// 自己
				} else {
					return fail(dataInfo, ERROR_ROOM_NAME);
				}
			}
			update(permission, authorId);
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
		 Permission permission = new Permission();
		if (StringUtils.isNotBlank(authorId)) {
			Integer status=Integer.parseInt(dataInfo.getRequestData().getString("status"));
			String id=dataInfo.getRequestData().getString("id");
			permission.setId(id);
			permission.setStatus(status);
			mapper.updateById(permission);
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
		Permission permission = dataInfo.getRequestData().toJavaObject(Permission.class);
		List<Permission> selectList = selectList(permission);
		return success(dataInfo, selectList);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}

	@Override
	public List<JSONObject> findAllPermission(String roleId) throws Exception {
		List<String> has = new ArrayList<String>();
		if (StringUtils.isNotBlank(roleId)) {
			RolePermission rolePermission = new RolePermission();
			rolePermission.setRoleId(roleId);
			List<RolePermission> hasPermisssion = rolePermissionService.selectList(rolePermission);
			if (null != hasPermisssion && 0 < hasPermisssion.size()) {
				for (RolePermission temp : hasPermisssion) {
					has.add(temp.getPermissionId());
				}
			}
		}	
		
		List<Permission> permissions = selectList(null);
		if (null != permissions && 0 < permissions.size()) {
			List<JSONObject> list = new ArrayList<>();
			for (Permission permission : permissions) {
				if (null == permission.getMenuParent()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("value", permission.getId());
					jsonObject.put("name", permission.getMenuName());
					jsonObject.put("checked", isChecked(has, permission.getId()));
					jsonObject.put("list", findPermissionJson(permissions, permission.getId(), has));
					list.add(jsonObject);
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * @Description: 递归获取子菜单
	 * @author xiaojiayi
	 * @date 2018年4月13日 下午3:13:09 
	 *
	 * @param permissions
	 * @param permissionId
	 * @return
	 */
	private List<JSONObject> findPermissionJson(List<Permission> permissions, String permissionId, List<String> has) {
		if (null != permissions && 0 < permissions.size()) {
			List<JSONObject> list = new ArrayList<>();
			for (Permission permission : permissions) {
				if (null != permission.getMenuParent() && permission.getMenuParent().equals(permissionId)) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("value", permission.getId());
					jsonObject.put("name", permission.getMenuName());
					jsonObject.put("checked", isChecked(has, permission.getId()));
					List<JSONObject> sun = findPermissionJson(permissions, permission.getId(), has);
					if (null != sun && 0 < sun.size()) {
						jsonObject.put("list", sun);
					}
					list.add(jsonObject);
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * @Description: 是否有包含
	 * @author xiaojiayi 
	 * @date 2018年9月8日 下午4:11:09
	 * @param permissions
	 * @param permissionId
	 * @return
	 */
	private boolean isChecked(List<String> permissions, String permissionId) {
		return permissions.contains(permissionId);
	}

	@Override
	public List<Permission> findByAccountId(String accountId, Integer status, Integer level, String parentId) throws Exception {
		return mapper.findByAccountId(accountId, status, level, parentId);
	}
}
