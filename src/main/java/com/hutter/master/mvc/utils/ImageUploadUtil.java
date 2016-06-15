package com.hutter.master.mvc.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传工具类
 * @author Administrator
 */
public class ImageUploadUtil {
	
	static Logger logger = LoggerFactory.getLogger(ImageUploadUtil.class);

	/**
	 * 上传
	 * @param file
	 * @param request
	 * @throws Exception 
	 */
	public static void upload(MultipartFile file, HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new Exception("上传文件为空");
		}
		
		byte[] bytes = file.getBytes();
		logger.info("###### getContentType: {}", file.getContentType());
		logger.info("###### getName: {}", file.getName());
		logger.info("###### getOriginalFilename: {}", file.getOriginalFilename());
		logger.info("###### getSize: {}", file.getSize());
		
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("D:/SBSProjects/Hutter/" + file.getOriginalFilename())));  
        stream.write(bytes);  
        stream.close();  
	}
}
