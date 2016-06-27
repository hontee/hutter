package com.hutter.master.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hutter.master.data.domain.Product;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.form.PageForm;
import com.hutter.master.mvc.base.BaseController;
import com.hutter.master.service.ProductService;
import com.hutter.master.service.UserService;

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
	@RequestMapping(value = "{name}/settings", method = RequestMethod.GET)
	public String settings(@PathVariable String name, Model model) {
		model.addAttribute("userInfo", getCurrentUser());
		return "users/settings";
	}
	
	/**
	 * 用户中心
	 * @return
	 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
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
