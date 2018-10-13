package com.mkw.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 菜品口味表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-10
 */
@TableName("food_package_taste")
public class FoodPackageTaste extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 菜品id
     */
	@TableField("package_id")
	private String packageId;
    /**
     * 口味id
     */
	@TableField("taste_id")
	private String tasteId;
	/**
     * 菜品id
     */
	public String getPackageId() {
		return packageId;
	}
	/**
     * 菜品id
     */
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	/**
     * 口味id
     */
	public String getTasteId() {
		return tasteId;
	}
	/**
     * 口味id
     */
	public void setTasteId(String tasteId) {
		this.tasteId = tasteId;
	}
}
