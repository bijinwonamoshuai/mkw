package com.mkw.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mkw.core.entity.Permission;
import com.mkw.core.common.SuperMapper;
import com.mkw.plugins.PageInfo;

/**
 * <p>
  * 权限表 Mapper 接口
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
public interface PermissionMapper extends SuperMapper<Permission> {
	public List<Map<String, Object>> queryByPageData(PageInfo pageInfo) throws Exception;

	public Integer queryByPageCount(PageInfo pageInfo) throws Exception;
	
	/**
	 * @Description: 
	 * @author xiaojiayi 
	 * @date 2018年9月10日 上午10:11:09
	 * @param accountId	管理员
	 * @param status	(0是企业版的权限,1是个人版的权限)
	 * @param level		菜单的级别
	 * @return
	 * @throws Exception
	 */
	public List<Permission> findByAccountId(@Param(value = "accountId") String accountId, 
			@Param(value = "status") Integer status, 
			@Param(value = "level") Integer level,
			@Param(value = "parentId") String parentId) throws Exception;
}