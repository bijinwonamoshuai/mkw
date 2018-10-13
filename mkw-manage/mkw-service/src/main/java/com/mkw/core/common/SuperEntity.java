package com.mkw.core.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SuperEnity 
 * @Description: entity基类
 * @author xiaojiayi
 * @date 2018年3月12日 上午11:16:20 
 *
 */
public class SuperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
     * 编号
     */
	private String id;
    /**
     * 是否可用（1是、0否）
     */
	private Integer enable;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 创建时间
     */
	private Date ctime;
    /**
     * 创建人
     */
	private String creater;
    /**
     * 修改时间
     */
	private Date mtime;
    /**
     * 修改人
     */
	private String modifier;
    /**
     * 删除时间
     */
	private Date dtime;
    /**
     * 删除人
     */
	private String deleter;
    /**
     * 描述
     */
	private String remark;

	/**
     * 编号
     */
	public String getId() {
		return id;
	}
	/**
     * 编号
     */
	public void setId(String id) {
		this.id = id;
	}
	/**
     * 是否可用（1是、0否）
     */
	public Integer getEnable() {
		return enable;
	}
	/**
     * 是否可用（1是、0否）
     */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	/**
     * 状态
     */
	public Integer getStatus() {
		return status;
	}
	/**
     * 状态
     */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
     * 创建时间
     */
	public Date getCtime() {
		return ctime;
	}
	/**
     * 创建时间
     */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	/**
     * 创建人
     */
	public String getCreater() {
		return creater;
	}
	/**
     * 创建人
     */
	public void setCreater(String creater) {
		this.creater = creater;
	}
	/**
     * 修改时间
     */
	public Date getMtime() {
		return mtime;
	}
	/**
     * 修改时间
     */
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	/**
     * 修改人
     */
	public String getModifier() {
		return modifier;
	}
	/**
     * 修改人
     */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
     * 删除时间
     */
	public Date getDtime() {
		return dtime;
	}
	/**
     * 删除时间
     */
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	/**
     * 删除人
     */
	public String getDeleter() {
		return deleter;
	}
	/**
     * 删除人
     */
	public void setDeleter(String deleter) {
		this.deleter = deleter;
	}
	/**
     * 描述
     */
	public String getRemark() {
		return remark;
	}
	/**
     * 描述
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
