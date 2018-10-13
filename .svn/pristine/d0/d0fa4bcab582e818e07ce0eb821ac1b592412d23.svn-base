package com.mkw.core.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mkw.core.entity.RolePermission;
import com.mkw.core.common.SuperMapper;
import com.mkw.plugins.PageInfo;

/**
 * <p>
  * 角色权限表 Mapper 接口
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
public interface RolePermissionMapper extends SuperMapper<RolePermission> {
	public List<Map<String, Object>> queryByPageData(PageInfo pageInfo) throws Exception;

	public Integer queryByPageCount(PageInfo pageInfo) throws Exception;
	
	public int deleteRolePermission(@Param(value="array") String[] permissionIds,
			@Param(value="roleId") String roleId,
			@Param(value="deleter") String deleter,
			@Param(value="dtime") Date dtime) throws Exception;
}