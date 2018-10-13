package com.mkw.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkw.core.common.SuperServiceImpl;
import com.mkw.core.entity.User;
import com.mkw.core.mapper.UserMapper;
import com.mkw.core.service.UserService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.EncryptUtil;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
@Service
@Transactional
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {
	
	@Override
	public DataInfo addModel(DataInfo dataInfo, String authorId) throws Exception {
		   User user=new User();
		if (StringUtils.isNotBlank(authorId)) {
			String username=dataInfo.getRequestData().getString("username");
			String phone=dataInfo.getRequestData().getString("phone");
			String password=dataInfo.getRequestData().getString("password");
			String city=dataInfo.getRequestData().getString("city");
			
			Date date=new Date();
			user.setCtime(date);
			user.setPhone(phone);
			user.setLoginPass(password);
			user.setLoginName(username);
			user.setCity(city);
			user.setMoney(new BigDecimal(0.0));
			
			mapper.insert(user);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editModelById(DataInfo dataInfo, String authorId) throws Exception {
		   User user=new User();
		if (StringUtils.isNotBlank(authorId)) {
			String username=dataInfo.getRequestData().getString("username");
			String phone=dataInfo.getRequestData().getString("phone");
			String password=dataInfo.getRequestData().getString("password");
			String city=dataInfo.getRequestData().getString("city");
			
			Date date=new Date();
			user.setMtime(date);//修改时间
			user.setPhone(phone);
			user.setLoginPass(password);
			user.setLoginName(username);
			user.setCity(city);
			
			mapper.updateById(user);
			return success(dataInfo, user);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo remoteModelById(DataInfo dataInfo, String authorId) throws Exception {
		if (StringUtils.isNotBlank(authorId)) {
			String sid=dataInfo.getRequestData().getString("id");
			Integer id=Integer.parseInt(sid);
			mapper.deleteById(id);
			return success(dataInfo, null);
		}
		return fail(dataInfo, SESSION_IS_EXCEPTION);
	}

	@Override
	public DataInfo editStatusById(DataInfo dataInfo, String authorId) throws Exception {
		User user=new User();
		if (StringUtils.isNotBlank(authorId)) {
			Integer status=Integer.parseInt(dataInfo.getRequestData().getString("status"));
			String id=dataInfo.getRequestData().getString("id");
			user.setId(id);
			user.setStatus(status);
			mapper.updateById(user);
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
		User user = dataInfo.getRequestData().toJavaObject(User.class);
		List<User> list = selectList(user);
		return success(dataInfo, list);
	}

	@Override
	public PageInfo findListByPage(PageInfo pageInfo) throws Exception {
		pageInfo.setData(mapper.queryByPageData(pageInfo));
		pageInfo.setCount(mapper.queryByPageCount(pageInfo));
		return pageInfo;
	}

	@Override
	public DataInfo login(DataInfo dataInfo) throws Exception {
		return UserMapper.login(dataInfo);
	}

	@Override
	public DataInfo checkLogin(DataInfo dataInfo) throws Exception {
		String loginName = dataInfo.getRequestData().getString("loginName");
		String loginPass = dataInfo.getRequestData().getString("loginPass");
		
		User user = new User();
		user.setLoginName(loginName);
		user.setLoginPass(EncryptUtil.MD5(loginPass));
		List<User> accounts = selectList(user);
		if (null != accounts && accounts.size() > 0) {
			return success(dataInfo, accounts.get(0));
		} else {
			return fail(dataInfo, ERROR_LOGIN);
		}
	}
}
