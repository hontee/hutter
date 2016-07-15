package com.hutter.front.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hutter.front.core.domain.Product;
import com.hutter.front.core.form.PageForm;
import com.hutter.front.core.service.ProductService;
import com.hutter.front.site.base.BaseController;

@Controller
@Scope("prototype")
public class HomeController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductService productS;

	/**
	 * 首页
	 * @return
	 * @throws BaseException 
	 */
	@GetMapping("/")
	public String home(PageForm page, @RequestParam(required = false) String q, Model model, HttpServletRequest request){
		Page<Product> pages = null;
		
		if (StringUtils.isEmpty(q)) {
			pages = productS.findAll(page.buildPageable());
		} else {
			logger.info("Search keywords: {}", q);
			model.addAttribute("q", q);
			pages= productS.findAll(q, page.buildPageable());
		}
		
		addRecordsAndPager(pages, request, model);
		return "home/index";
	}
	
	@GetMapping("{id}/hit")
	public String hit(@PathVariable Long id) {
		String url = productS.hit(id);
		logger.info("redirct url: {}", url);
		return "redirect:" + url;
	}
	
}
