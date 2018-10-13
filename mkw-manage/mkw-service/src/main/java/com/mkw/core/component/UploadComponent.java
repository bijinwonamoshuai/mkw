package com.mkw.core.component;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mkw.core.property.ConfigProperty;
import com.mkw.plugins.FileInfo;

@Component
public class UploadComponent {
	@Resource
	private ConfigProperty configProperty;

	public FileInfo upload(MultipartFile file) throws Exception {
		if (null != file) {
			Long size = file.getSize();
			// 原文件名称
			String oldFileName = file.getOriginalFilename();
			// 原文件后缀名
			String prefix = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
			// 新文件名
			String fileName = createFileName(prefix);
			// 创建文件
			FileInfo fileInfo = createFile(prefix, fileName);
			// 定义
			Integer status = null;
			File src = null;
			try {
				// 获取文件
				src = new File(fileInfo.getFilePath());
				//将上传文件保存到一个目标文件当中
	            file.transferTo(src);
	            // 成功
				status = 200;
			} catch (Exception e) {
				status = 500;
			} finally {
				src = null;
			}

			// 返回信息
			fileInfo.setStatus(status);
			fileInfo.setSize(size);
			fileInfo.setFileName(fileName);
			return fileInfo;
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: createFile
	 * @Description: 创建模块文件
	 * @author xiaojiayi
	 * @date 2018年3月1日 下午6:37:27
	 * 
	 * @param module
	 *            模块名称（支持多模块，格式：a/b/c）
	 * @param fileType
	 *            文件类型（支持归类）
	 * @param fileName
	 *            新文件名称
	 * @return 文件访问路径
	 */
	private FileInfo createFile(String prefix, String fileName) {
		Date date = new Date();
		Random random = new Random();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String sub = prefix.replace(".", "") + "/" + dateFormat.format(date) + "/" + (random.nextInt(899999)+ 100000) + "/";

		// 检查目录是否存在
		File savePath = new File(configProperty.getUploadDamainPath() + "/" + sub);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}

		// 生成文件
		/*File file = new File(path + "/" + sub + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}*/

		// 清理缓存
		date = null;
		dateFormat = null;
		savePath = null;
		random = null;

		// 返回信息
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFilePath(configProperty.getUploadDamainPath() + "/" + sub + fileName);
		fileInfo.setFileUrl(configProperty.getUploadDamainName() + "/" + sub + fileName);
		return fileInfo;
	}

	/**
	 * @Title: createFileName 
	 * @Description: 新建新文件名
	 * @author xiaojiayi
	 * @date 2018年3月15日 下午6:12:22 
	 * 
	 * @param prefix	
	 * @return
	 */
	private String createFileName(String prefix) throws Exception {
		Date date = new Date();
		Random random = new Random();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// 新文件名
		String fileName = dateFormat.format(date) + (random.nextInt(899999) + 100000) + prefix;

		// 清理缓存
		date = null;
		random = null;
		dateFormat = null;
		return fileName;
	}
}
