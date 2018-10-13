package com.mkw.core.mapper;

import java.util.List;
import java.util.Map;

import com.mkw.core.entity.Account;
import com.mkw.core.common.SuperMapper;
import com.mkw.plugins.PageInfo;

/**
 * <p>
  * 管理员表 Mapper 接口
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-06
 */
public interface AccountMapper extends SuperMapper<Account> {

	public List<Map<String, Object>> queryByPageData(PageInfo pageInfo) throws Exception;

	public Integer queryByPageCount(PageInfo pageInfo) throws Exception;

}