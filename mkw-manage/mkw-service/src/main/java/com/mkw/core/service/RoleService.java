package com.mkw.core.service;

import com.alibaba.fastjson.JSONArray;
import com.mkw.core.entity.Role;
import com.mkw.core.common.SuperService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
public interface RoleService extends SuperService<Role> {
	
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
	 * @Description: 根据状态获取角色
	 * @author xiaojiayi 
	 * @date 2018年9月8日 下午4:42:04
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public JSONArray findAllRoleByStatus(int status) throws Exception;
}
