package com.mkw.core.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 菜品菜单表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-10
 */
@TableName("food_menu")
public class FoodMenu extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 图标
     */
	private String icon;
    /**
     * 名称
     */
	private String name;
	/**
     * 图标
     */
	public String getIcon() {
		return icon;
	}
	/**
     * 图标
     */
	public void setIcon(String icon) {
		this.icon = icon;
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
