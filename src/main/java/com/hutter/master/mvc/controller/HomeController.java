package com.hutter.master.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.base.properties.TitlePolicy;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.PageForm;
import com.hutter.master.service.ProductService;

@Controller
@Scope("prototype")
public class HomeController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private TitlePolicy policy;

	/**
	 * 首页
	 * @return
	 * @throws BaseException 
	 */
	@RequestMapping("/")
	public String home(PageForm page, @RequestParam(required = false) String q, Model model, HttpServletRequest request)
			throws BaseException {
		Page<Product> pages = null;
		
		if (StringUtils.isEmpty(q)) {
			pages = productS.findAll(page.buildPageable());
		} else {
			logger.info("Search keywords: {}", q);
			model.addAttribute("q", q);
			pages= productS.findAll(q, page.buildPageable());
		}
		
		setTitle(policy.getHome(), model);
		addRecordsAndPager(pages, request, model);
		return "home/index";
	}
	
	@RequestMapping("{id}/hit")
	public String hit(@PathVariable Long id) throws BaseException {
		String url = productS.hit(id);
		logger.info("redirct url: {}", url);
		return "redirect:" + url;
	}
	
}
