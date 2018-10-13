package com.mkw.core.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-07
 */
public class User extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 登录账号
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 登录密码
     */
	@TableField("login_pass")
	private String loginPass;
    /**
     * 登录时间
     */
	@TableField("login_time")
	private Date loginTime;
    /**
     * 登录次数
     */
	@TableField("login_count")
	private Integer loginCount;
    /**
     * 昵称
     */
	private String name;
    /**
     * 头像
     */
	private String url;
    /**
     * 所属城市
     */
	private String city;
    /**
     * 用户类型（0超级管理员、1普通用户）
     */
	private Integer type;
    /**
     * 角色id
     */
	@TableField("role_id")
	private String roleId;
    /**
     * 登陆ip
     */
	@TableField("login_ip")
	private String loginIp;
    /**
     * 微信账户
     */
	@TableField("wechat_user")
	private String wechatUser;
    /**
     * 微信头像
     */
	@TableField("wechat_image")
	private String wechatImage;
    /**
     * 账户余额
     */
	private BigDecimal money;
    /**
     * 手机号码
     */
	private String phone;
    /**
     * 备用字段1
     */
	private String fied1;
    /**
     * 备用字段2
     */
	private String fied2;
	/**
     * 登录账号
     */
	public String getLoginName() {
		return loginName;
	}
	/**
     * 登录账号
     */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
     * 登录密码
     */
	public String getLoginPass() {
		return loginPass;
	}
	/**
     * 登录密码
     */
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	/**
     * 登录时间
     */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
     * 登录时间
     */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
     * 登录次数
     */
	public Integer getLoginCount() {
		return loginCount;
	}
	/**
     * 登录次数
     */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	/**
     * 昵称
     */
	public String getName() {
		return name;
	}
	/**
     * 昵称
     */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * 头像
     */
	public String getUrl() {
		return url;
	}
	/**
     * 头像
     */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
     * 所属城市
     */
	public String getCity() {
		return city;
	}
	/**
     * 所属城市
     */
	public void setCity(String city) {
		this.city = city;
	}
	/**
     * 用户类型（0超级管理员、1普通用户）
     */
	public Integer getType() {
		return type;
	}
	/**
     * 用户类型（0超级管理员、1普通用户）
     */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * 角色id
     */
	public String getRoleId() {
		return roleId;
	}
	/**
     * 角色id
     */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
     * 登陆ip
     */
	public String getLoginIp() {
		return loginIp;
	}
	/**
     * 登陆ip
     */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
     * 微信账户
     */
	public String getWechatUser() {
		return wechatUser;
	}
	/**
     * 微信账户
     */
	public void setWechatUser(String wechatUser) {
		this.wechatUser = wechatUser;
	}
	/**
     * 微信头像
     */
	public String getWechatImage() {
		return wechatImage;
	}
	/**
     * 微信头像
     */
	public void setWechatImage(String wechatImage) {
		this.wechatImage = wechatImage;
	}
	/**
     * 账户余额
     */
	public BigDecimal getMoney() {
		return money;
	}
	/**
     * 账户余额
     */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
     * 手机号码
     */
	public String getPhone() {
		return phone;
	}
	/**
     * 手机号码
     */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
     * 备用字段1
     */
	public String getFied1() {
		return fied1;
	}
	/**
     * 备用字段1
     */
	public void setFied1(String fied1) {
		this.fied1 = fied1;
	}
	/**
     * 备用字段2
     */
	public String getFied2() {
		return fied2;
	}
	/**
     * 备用字段2
     */
	public void setFied2(String fied2) {
		this.fied2 = fied2;
	}
}
