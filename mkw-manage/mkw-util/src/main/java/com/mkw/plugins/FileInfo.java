package com.mkw.plugins; 

public class FileInfo {
	/**
	 * 文件访问路径
	 */
	private String fileUrl;
	/**
	 * 文件保存路径
	 */
	private String filePath;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 当前文件操作状态（成功200、失败500）
	 */
	private Integer status;
	/**
	 * 文件大小
	 */
	private Long size;
	
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
  
