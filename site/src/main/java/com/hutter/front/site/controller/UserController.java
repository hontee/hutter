package com.hutter.front.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hutter.front.core.domain.Product;
import com.hutter.front.core.domain.User;
import com.hutter.front.core.form.PageForm;
import com.hutter.front.core.service.ProductService;
import com.hutter.front.core.service.UserService;
import com.hutter.front.site.base.BaseController;

@Controller
@Scope("prototype")
@RequestMapping("users")
public class UserController extends BaseController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userS;
	@Autowired
	private ProductService prodcutS;
	
	@RequiresRoles({"user", "admin"})
	@GetMapping("{name}/settings")
	public String settings(@PathVariable String name, Model model) {
		model.addAttribute("userInfo", getCurrentUser());
		return "users/settings";
	}
	
	/**
	 * 用户中心
	 * @return
	 */
	@GetMapping("{name}")
	public String dashbord(@PathVariable String name, PageForm page, Model model, HttpServletRequest request) {
		logger.info("进入用户主页：{}", name);
		User userInfo = new User();
		
		if (isAuthenticated() && name.equals(getUserName())) {
			userInfo = getCurrentUser();
		} else {
			userS.findOne(name);
		}
		model.addAttribute("userInfo", userInfo);
		Page<Product> pages = prodcutS.findAll(userInfo.getId(), page.buildPageable());
		addRecordsAndPager(pages, request, model);
		return "users/dashbord";
	}
	
}
