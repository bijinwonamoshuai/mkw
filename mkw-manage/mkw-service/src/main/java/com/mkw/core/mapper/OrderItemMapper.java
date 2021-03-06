package com.mkw.core.mapper;

import java.util.List;
import java.util.Map;

import com.mkw.core.entity.OrderItem;
import com.mkw.core.common.SuperMapper;
import com.mkw.plugins.PageInfo;

/**
 * <p>
  * 订单表 Mapper 接口
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
public interface OrderItemMapper extends SuperMapper<OrderItem> {
	public List<Map<String, Object>> queryByPageData(PageInfo pageInfo) throws Exception;

	public Integer queryByPageCount(PageInfo pageInfo) throws Exception;

}