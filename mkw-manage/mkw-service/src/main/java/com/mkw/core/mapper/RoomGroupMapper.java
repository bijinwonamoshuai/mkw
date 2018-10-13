package com.mkw.core.mapper;

import java.util.List;
import java.util.Map;

import com.mkw.core.entity.RoomGroup;
import com.mkw.core.common.SuperMapper;
import com.mkw.plugins.PageInfo;

/**
 * <p>
  * 门店菜品组表 Mapper 接口
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
public interface RoomGroupMapper extends SuperMapper<RoomGroup> {
	
	/**
	 * @Description: 分页数据
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午8:34:35 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryByPageData(PageInfo pageInfo) throws Exception;
	
	/**
	 * @Description: 分页数量
	 * @author xiaojiayi 
	 * @date 2018年4月30日 下午8:35:05 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public Integer queryByPageCount(PageInfo pageInfo) throws Exception;

}