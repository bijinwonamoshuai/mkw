package com.mkw.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 门店菜品组的菜品表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-11
 */
@TableName("room_food")
public class RoomFood extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 门店菜品组编号
     */
	@TableField("group_id")
	private String groupId;
    /**
     * 门店菜品组编号
     */
	@TableField("package_id")
	private String packageId;
    /**
     * 显示名称
     */
	@TableField("show_name")
	private String showName;
    /**
     * 显示价格
     */
	@TableField("show_price")
	private BigDecimal showPrice;
    /**
     * 显示库存
     */
	@TableField("show_stock")
	private Integer showStock;
	/**
     * 门店菜品组编号
     */
	public String getGroupId() {
		return groupId;
	}
	/**
     * 门店菜品组编号
     */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
     * 门店菜品组编号
     */
	public String getPackageId() {
		return packageId;
	}
	/**
     * 门店菜品组编号
     */
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	/**
     * 显示名称
     */
	public String getShowName() {
		return showName;
	}
	/**
     * 显示名称
     */
	public void setShowName(String showName) {
		this.showName = showName;
	}
	/**
     * 显示价格
     */
	public BigDecimal getShowPrice() {
		return showPrice;
	}
	/**
     * 显示价格
     */
	public void setShowPrice(BigDecimal showPrice) {
		this.showPrice = showPrice;
	}
    /**
     * 显示库存
     */
	public Integer getShowStock() {
		return showStock;
	}
    /**
     * 显示库存
     */
	public void setShowStock(Integer showStock) {
		this.showStock = showStock;
	}
}
