package com.mkw.core.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.mkw.core.common.SuperService;
import com.mkw.core.entity.Permission;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
public interface PermissionService extends SuperService<Permission> {
	
	/**
	 * @Description: 保存对象信息
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception;
	
	/**
	 * @Description: 根据id更新对象信息
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception;
	
	/**
	 * @Description: 根据id软删除对象信息
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public DataInfo remoteModelById(DataInfo dataInfo, String authorId) throws Exception;
	
	/**
	 * @Description: 根据id更新状态
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public DataInfo editStatusById(DataInfo dataInfo, String authorId) throws Exception;
	
	/**
	 * @Description: 根据id获取对象信息
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public DataInfo findModelById(DataInfo dataInfo) throws Exception;
	
	/**
	 * @Description: 根据实体获取列表数据
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public DataInfo findListByModel(DataInfo dataInfo) throws Exception;
	
	/**
	 * @Description: 分页获取数据
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午7:13:26 
	 * @param dataInfo
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception;
	
	/**
	 * @Description: 获取所有权限
	 * @author xiaojiayi 
	 * @date 2018年9月8日 上午11:21:18
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> findAllPermission(String roleId) throws Exception;
	
	/**
	 * @Description: 
	 * @author xiaojiayi 
	 * @date 2018年9月10日 上午10:11:09
	 * @param accountId	管理员
	 * @param status	(0是企业版的权限,1是个人版的权限)
	 * @param level		菜单的级别(非必须)
	 * @return
	 * @throws Exception
	 */
	public List<Permission> findByAccountId(String accountId, Integer status, Integer level, String parentId) throws Exception;
}
