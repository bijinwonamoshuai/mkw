package com.mkw.core.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.mkw.annotation.Logs;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.component.UploadComponent;
import com.mkw.plugins.FileInfo;

/**
 * <p>
 * 文件上传前端控制器
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-04-14
 */
@Controller
@RequestMapping("file")
public class FileController extends SuperController {
	@Resource
	private UploadComponent uploadComponent;
	
	@Logs(module="文件上传控制器", operate=Operate.OTHER, remark="文件上传")
	@ResponseBody
	@RequestMapping("upload")
	public FileInfo upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
		FileInfo fileInfo = uploadComponent.upload(file);
		return fileInfo;
	}
	
	@Logs(module="文件上传控制器", operate=Operate.OTHER, remark="图片上传(编辑器专用)")
	@ResponseBody
	@RequestMapping("image")
	public JSONObject image(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
		FileInfo fileInfo = uploadComponent.upload(file);
		
		JSONObject json = new JSONObject();
		json.put("src", fileInfo.getFileUrl());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "上传成功");
		jsonObject.put("data", json);
		return jsonObject;
	}
}
