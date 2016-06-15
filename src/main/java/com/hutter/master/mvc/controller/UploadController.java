package com.hutter.master.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hutter.master.mvc.utils.ImageUploadUtil;

@Controller
public class UploadController {

	@RequestMapping(value="/upload", method=RequestMethod.GET)  
    public String provideUploadInfo() {  
        return "upload";  
    }  
   
    @RequestMapping(value="/upload", method=RequestMethod.POST)  
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
