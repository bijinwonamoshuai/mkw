package com.mkw.constant;

public enum Operate {
	/**查询*/
	SELECT("查询"),
    /**更新*/
    UPDATE("更新"),
    /**删除*/
    DELETE("删除"),
    /**插入*/
    INSERT("插入"),
    /**其他*/
    OTHER("其他");

	private String operate;

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	private Operate(String operate) {
		this.operate = operate;
	}
}
