package com.mkw.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-06
 */
@TableName("order_item_detail")
public class OrderItemDetail extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 菜品id
     */
	@TableField("food_id")
	private String foodId;
    /**
     * 菜品原价
     */
	@TableField("origin_price")
	private String originPrice;
    /**
     * 菜品编码
     */
	private String code;
    /**
     * 菜品名称
     */
	private BigDecimal name;
    /**
     * 菜品类型
     */
	@TableField("food_type")
	private String foodType;
    /**
     * 菜品单价
     */
	private BigDecimal price;
    /**
     * 菜品数量
     */
	private Integer count;
    /**
     * 菜品图片
     */
	@TableField("food_image")
	private String foodImage;
    /**
     * 规格（0-小 1-中 2-大）
     */
	private String sdandrd;
    /**
     * 备用字段1
     */
	private String fied1;
    /**
     * 备用字段2
     */
	private String fied2;
	/**
     * 订单编号
     */
	public String getOrderId() {
		return orderId;
	}
	/**
     * 订单编号
     */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
     * 菜品id
     */
	public String getFoodId() {
		return foodId;
	}
	/**
     * 菜品id
     */
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	/**
     * 菜品原价
     */
	public String getOriginPrice() {
		return originPrice;
	}
	/**
     * 菜品原价
     */
	public void setOriginPrice(String originPrice) {
		this.originPrice = originPrice;
	}
	/**
     * 菜品编码
     */
	public String getCode() {
		return code;
	}
	/**
     * 菜品编码
     */
	public void setCode(String code) {
		this.code = code;
	}
	/**
     * 菜品名称
     */
	public BigDecimal getName() {
		return name;
	}
	/**
     * 菜品名称
     */
	public void setName(BigDecimal name) {
		this.name = name;
	}
	/**
     * 菜品类型
     */
	public String getFoodType() {
		return foodType;
	}
	/**
     * 菜品类型
     */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	/**
     * 菜品单价
     */
	public BigDecimal getPrice() {
		return price;
	}
	/**
     * 菜品单价
     */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
     * 菜品数量
     */
	public Integer getCount() {
		return count;
	}
	/**
     * 菜品数量
     */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
     * 菜品图片
     */
	public String getFoodImage() {
		return foodImage;
	}
	/**
     * 菜品图片
     */
	public void setFoodImage(String foodImage) {
		this.foodImage = foodImage;
	}
	/**
     * 规格（0-小 1-中 2-大）
     */
	public String getSdandrd() {
		return sdandrd;
	}
	/**
     * 规格（0-小 1-中 2-大）
     */
	public void setSdandrd(String sdandrd) {
		this.sdandrd = sdandrd;
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
