package com.hutter.master.mvc.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String home(@Validated PageForm page, @RequestParam(required = false) String q, Model model, BindingResult r) throws BaseException {
		setTitle(policy.getHome(), model);
		
		page.orderNewest();
		Page<Product> pages = null;
		String baseUri = "/";
		
		// 查询
		if (StringUtils.isEmpty(q)) {
			pages = productS.findAll(page.buildPageable());
		} else {
			pages= productS.findAll(q, page.buildPageable());
			baseUri = "/?q=" + q;
			model.addAttribute("q", q);
		}
		
		addRecords(pages.getContent(), model);
		addPager(pages, baseUri, model);
		return "home/index";
	}
	
}
