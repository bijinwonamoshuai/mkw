package com.mkw.core.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
@TableName("order_item")
public class OrderItem extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 门店编号
     */
	@TableField("merchant_id")
	private String merchantId;
    /**
     * 用户id
     */
	private String userid;
    /**
     * 是否打包
     */
	@TableField("is_pack")
	private String isPack;
    /**
     * 订单总金额
     */
	@TableField("all_price")
	private BigDecimal allPrice;
    /**
     * 配送方式（0-自提 1-邮寄）
     */
	@TableField("send_type")
	private String sendType;
    /**
     * 联系方式
     */
	private BigDecimal phone;
    /**
     * 联系地址
     */
	private String address;
    /**
     * 备用字段1
     */
	private String fied1;
    /**
     * 备用字段2
     */
	private String fied2;
    /**
     * 微信账户
     */
	@TableField("wechat_id")
	private String wechatId;
    /**
     * 下单时间
     */
	@TableField("pay_time")
	private Date payTime;
    /**
     * 订单状态（0-已完成 1-待支付 2-已取消）
     */
	@TableField("order_status")
	private Integer orderStatus;
	/**
     * 门店编号
     */
	public String getMerchantId() {
		return merchantId;
	}
	/**
     * 门店编号
     */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	/**
     * 用户id
     */
	public String getUserid() {
		return userid;
	}
	/**
     * 用户id
     */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
     * 是否打包
     */
	public String getIsPack() {
		return isPack;
	}
	/**
     * 是否打包
     */
	public void setIsPack(String isPack) {
		this.isPack = isPack;
	}
	/**
     * 订单总金额
     */
	public BigDecimal getAllPrice() {
		return allPrice;
	}
	/**
     * 订单总金额
     */
	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}
	/**
     * 配送方式（0-自提 1-邮寄）
     */
	public String getSendType() {
		return sendType;
	}
	/**
     * 配送方式（0-自提 1-邮寄）
     */
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	/**
     * 联系方式
     */
	public BigDecimal getPhone() {
		return phone;
	}
	/**
     * 联系方式
     */
	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}
	/**
     * 联系地址
     */
	public String getAddress() {
		return address;
	}
	/**
     * 联系地址
     */
	public void setAddress(String address) {
		this.address = address;
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
	/**
     * 微信账户
     */
	public String getWechatId() {
		return wechatId;
	}
	/**
     * 微信账户
     */
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	/**
     * 下单时间
     */
	public Date getPayTime() {
		return payTime;
	}
	/**
     * 下单时间
     */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
     * 订单状态（0-已完成 1-待支付 2-已取消）
     */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
     * 订单状态（0-已完成 1-待支付 2-已取消）
     */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
}
