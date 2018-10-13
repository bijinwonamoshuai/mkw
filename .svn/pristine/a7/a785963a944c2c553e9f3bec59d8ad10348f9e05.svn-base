package com.mkw.core.common;

import java.util.List;

/**
 * @ClassName: SuperService 
 * @Description: service基类
 * @author xiaojiayi
 * @date 2018年3月12日 上午11:18:36 
 * 
 * @param <T>
 */
public interface SuperService<T extends SuperEntity> {

	/**
	 * @Description: 保存
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:39 
	 * @param t
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public int insert(T t, String authorId) throws Exception;
	
	/**
	 * @Description: 根据id选择性更新
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:43 
	 * @param t
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public int update(T t, String authorId) throws Exception;
	
	/**
	 * @Description: 根据id覆盖性更新
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:43 
	 * @param t
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public int updateColumns(T t, String authorId) throws Exception;
	
	/**
	 * @Description: 软删除
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:46 
	 * @param id
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public int delete(String id, String authorId) throws Exception;
	
	/**
	 * @Description: 软删除
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:49 
	 * @param ids
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public int delete(String[] ids, String authorId) throws Exception;
	
	/**
	 * @Description: 更新状态
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:53 
	 * @param id
	 * @param status
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public int updateStatus(String id, int status, String authorId) throws Exception;
	
	/**
	 * @Description: 查询
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:32:56 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T select(String id) throws Exception;
	
	/**
	 * @Description: 查询
	 * @author xiaojiayi 
	 * @date 2018年5月8日 下午7:33:00 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<T> selectList(T t) throws Exception;
}
