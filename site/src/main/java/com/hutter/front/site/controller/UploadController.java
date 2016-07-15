package com.hutter.front.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hutter.front.site.utils.ImageUploadUtil;

@Controller
public class UploadController {

	@GetMapping("/upload")  
    public String provideUploadInfo() {  
        return "upload";  
    }  
   
	@PostMapping("/upload")
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,  
            @RequestParam("file") MultipartFile file, HttpServletRequest request){  
    	try {
			ImageUploadUtil.upload(file, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return name;
    }  
}
