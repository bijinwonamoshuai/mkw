package com.mkw.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.Account;
import com.mkw.core.entity.Permission;
import com.mkw.core.mapper.AccountMapper;
import com.mkw.core.service.AccountService;
import com.mkw.core.service.PermissionService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.EncryptUtil;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-06
 */
@Service
@Transactional
public class AccountServiceImpl extends SuperServiceImpl<AccountMapper, Account> implements AccountService {
	@Resource
	private PermissionService permissionService;
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			Account account = dataInfo.getRequestData().toJavaObject(Account.class);
			
			Account temp = new Account();
			temp.setLoginName(account.getLoginName());
			List<Account> list = selectList(temp);
			if (null != list && 0 < list.size()) {
				return fail(dataInfo, ERROR_ACCOUNT_NAME);
			}
			
			account.setType(1);
			account.setLoginPass(EncryptUtil.MD5(account.getLoginPass()));
			insert(account, authorId);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			Account account = dataInfo.getRequestData().toJavaObject(Account.class);
			
			Account temp = new Account();
			temp.setLoginName(account.getLoginName());
			List<Account> list = selectList(temp);
			if (null != list && 0 < list.size()) {
				if (1 == list.size() && account.getId().equals(list.get(0).getId())) {
					
				} else {
					return fail(dataInfo, ERROR_ACCOUNT_NAME);
				}
			}

			account.setType(1);
			account.setLoginPass(EncryptUtil.MD5(account.getLoginPass()));
			update(account, authorId);
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
		Account account=new Account();
		if (StringUtils.isNotBlank(authorId)) {
			Integer status=Integer.parseInt(dataInfo.getRequestData().getString("status"));
			String id=dataInfo.getRequestData().getString("id");
			account.setId(id);
			account.setStatus(status);
			mapper.updateById(account);
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
		Account account = dataInfo.getRequestData().toJavaObject(Account.class);
		List<Account> selectList = selectList(account);
		return success(dataInfo, selectList);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}

	@Override
	public DataInfo checkLogin(DataInfo dataInfo, int type) throws Exception {
		String loginName = dataInfo.getRequestData().getString("loginName");
		String loginPass = dataInfo.getRequestData().getString("loginPass");
		
		Account account = new Account();
		account.setLoginName(loginName);
		account.setLoginPass(EncryptUtil.MD5(loginPass));
		List<Account> accounts = selectList(account);
		
		if (null == accounts || accounts.size() == 0) {
			return fail(dataInfo, ERROR_LOGIN);
		} else if (type == 0 && accounts.get(0).getType() != 0) {
			return fail(dataInfo, ERROR_LOGIN);
		} else if ((type == 1 || type == 2) && accounts.get(0).getType() != 1) {
			return fail(dataInfo, ERROR_LOGIN);
		} else if (type == 1 || type == 2) {
			int status = type == 1 ? 0 : 1;
			List<Permission> list = permissionService.findByAccountId(accounts.get(0).getId(), status, null, null);
			if (null == list || 0 == list.size()) {
				return fail(dataInfo, ERROR_LOGIN);
			}
		}
		
		return success(dataInfo, accounts.get(0));
	}

	@Override
	public DataInfo findMenu(DataInfo dataInfo, String authorId) throws Exception {
		Integer status = dataInfo.getRequestData().getIntValue("status");
		Integer level = dataInfo.getRequestData().getIntValue("level");
		String parentId = dataInfo.getRequestData().getString("parentId");
		
		JSONArray jsonArray = new JSONArray();
		List<Permission> permisssions = permissionService.findByAccountId(authorId, status, level, parentId);
		if (null != permisssions && 0 < permisssions.size()) {
			for (Permission permission : permisssions) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", permission.getId());
				jsonObject.put("title", permission.getMenuName());
				jsonObject.put("icon", permission.getMenuIcon());
				jsonObject.put("href", permission.getMenuUrl());
				jsonArray.add(jsonObject);
			}
		}
		
		return success(dataInfo, jsonArray);
	}
	
}
