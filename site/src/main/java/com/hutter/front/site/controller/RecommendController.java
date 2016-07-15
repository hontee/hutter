package com.hutter.front.site.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hutter.front.core.domain.Product;
import com.hutter.front.core.form.ProductForm;
import com.hutter.front.core.service.ProductService;
import com.hutter.front.site.annotation.Token;
import com.hutter.front.site.base.BaseController;
import com.hutter.front.toolkit.spider.SpiderClient;
import com.hutter.front.toolkit.spider.SpiderInfo;

@Controller
@Scope("prototype")
@RequestMapping("recommend")
public class RecommendController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private SpiderClient client;
	
	/**
	 * 开发者推荐：填写推荐网址
	 * @return
	 */
	@RequiresRoles({"user", "admin"})
	@GetMapping("")
	public String recommend(Model model) {
		model.addAttribute("step", "1");
		return "recommend/new";
	}
	
	/**
	 * 开发者推荐：编辑推荐信息
	 * @return
	 */
	@Token(add = true)
	@RequiresRoles({"user", "admin"})
	@GetMapping("confirm")
	public String confirm(@RequestParam String url, Model model) {
		
		// 如果URL存在，则显示提示信息
		if (productS.checkIsExists(url)) {
			model.addAttribute("exists", true);
			model.addAttribute("url", url);
			model.addAttribute("step", "1");
			return "recommend/new";
		}
		
		SpiderInfo spider = client.fetch(url);
		model.addAttribute("spider", spider);
		model.addAttribute("step", "2");
		return "recommend/confirm";
	}
	
	/**
	 * 开发者推荐：确认推荐结果
	 * @return
	 * @throws BaseException 
	 */
	@Token(delete = true)
	@RequiresRoles({"user", "admin"})
	@GetMapping("submit")
	public String submit(@Validated ProductForm form, Model model, BindingResult bindingResult) {
		logger.info("开发者推荐信息：{}", form.toJSONString());
		checkAssert(bindingResult);
		Product record = productS.addProduct(form);
		model.addAttribute("record", record);
		model.addAttribute("step", "3");
		return "recommend/submit";
	}

}
