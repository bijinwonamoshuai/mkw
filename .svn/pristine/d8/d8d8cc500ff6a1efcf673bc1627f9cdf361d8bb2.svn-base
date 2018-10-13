package com.mkw.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 菜品表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-10
 */
@TableName("food_package")
public class FoodPackage extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 所属菜单编号
     */
	@TableField("menu_id")
	private String menuId;
    /**
     * 图片
     */
	private String img;
    /**
     * 库存
     */
	@TableField("number_remaining")
	private Integer numberRemaining;
    /**
     * 原价
     */
	@TableField("original_price")
	private BigDecimal originalPrice;
    /**
     * 是否相同套餐，0不相同，1相同
     */
	@TableField("same_package")
	private Integer samePackage;
    /**
     * 类型
     */
	private String type;
    /**
     * 是否特价菜：0否，1是
     */
	@TableField("is_specials")
	private Integer isSpecials;
    /**
     * 现价
     */
	private BigDecimal price;
    /**
     * 套餐包含的单品名称
     */
	@TableField("dishes_names")
	private String dishesNames;
    /**
     * 名称
     */
	private String name;
	/**
     * 所属菜单编号
     */
	public String getMenuId() {
		return menuId;
	}
	/**
     * 所属菜单编号
     */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
     * 图片
     */
	public String getImg() {
		return img;
	}
	/**
     * 图片
     */
	public void setImg(String img) {
		this.img = img;
	}
	/**
     * 库存
     */
	public Integer getNumberRemaining() {
		return numberRemaining;
	}
	/**
     * 库存
     */
	public void setNumberRemaining(Integer numberRemaining) {
		this.numberRemaining = numberRemaining;
	}
	/**
     * 原价
     */
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	/**
     * 原价
     */
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	/**
     * 是否相同套餐，0不相同，1相同
     */
	public Integer getSamePackage() {
		return samePackage;
	}
	/**
     * 是否相同套餐，0不相同，1相同
     */
	public void setSamePackage(Integer samePackage) {
		this.samePackage = samePackage;
	}
	/**
     * 类型
     */
	public String getType() {
		return type;
	}
	/**
     * 类型
     */
	public void setType(String type) {
		this.type = type;
	}
	/**
     * 是否特价菜：0否，1是
     */
	public Integer getIsSpecials() {
		return isSpecials;
	}
	/**
     * 是否特价菜：0否，1是
     */
	public void setIsSpecials(Integer isSpecials) {
		this.isSpecials = isSpecials;
	}
	/**
     * 现价
     */
	public BigDecimal getPrice() {
		return price;
	}
	/**
     * 现价
     */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
     * 套餐包含的单品名称
     */
	public String getDishesNames() {
		return dishesNames;
	}
	/**
     * 套餐包含的单品名称
     */
	public void setDishesNames(String dishesNames) {
		this.dishesNames = dishesNames;
	}
	/**
     * 名称
     */
	public String getName() {
		return name;
	}
	/**
     * 名称
     */
	public void setName(String name) {
		this.name = name;
	}
}
