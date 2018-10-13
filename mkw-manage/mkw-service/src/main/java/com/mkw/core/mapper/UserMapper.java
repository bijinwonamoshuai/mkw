package com.mkw.core.mapper;

import java.util.List;
import java.util.Map;

import com.mkw.core.entity.User;
import com.mkw.core.common.SuperMapper;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;

/**
 * <p>
  * 用户表 Mapper 接口
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
public interface UserMapper extends SuperMapper<User> {

	static DataInfo login(DataInfo dataInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Map<String, Object>> queryByPageData(PageInfo pageInfo) throws Exception;

	public Integer queryByPageCount(PageInfo pageInfo) throws Exception;
}