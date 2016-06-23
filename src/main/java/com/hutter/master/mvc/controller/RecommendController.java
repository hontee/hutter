package com.hutter.master.mvc.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hutter.master.base.client.SpiderClient;
import com.hutter.master.base.client.SpiderInfo;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.base.properties.TitlePolicy;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;
import com.hutter.master.mvc.annotation.Token;
import com.hutter.master.mvc.base.BaseController;
import com.hutter.master.service.ProductService;

@Controller
@Scope("prototype")
@RequestMapping("recommend")
public class RecommendController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private SpiderClient client;
	
	@Autowired
	private TitlePolicy policy;
	
	/**
	 * 开发者推荐：填写推荐网址
	 * @return
	 */
	@RequiresRoles({"user", "admin"})
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String recommend(Model model) {
		addTitle(policy.getRecommend(), model);
		model.addAttribute("step", "1");
		return "recommend/new";
	}
	
	/**
	 * 开发者推荐：编辑推荐信息
	 * @return
	 */
	@Token(add = true)
	@RequiresRoles({"user", "admin"})
	@RequestMapping(value = "confirm", method=RequestMethod.GET)
	public String confirm(@RequestParam String url, Model model) {
		
		// 如果URL存在，则显示提示信息
		if (productS.checkIsExists(url)) {
			model.addAttribute("exists", true);
			model.addAttribute("url", url);
			return "recommend/new";
		}
		
		SpiderInfo spider = client.fetch(url);
		model.addAttribute("spider", spider);
		addTitle(policy.getRecommend(), model);
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
	@RequestMapping(value = "submit", method=RequestMethod.GET)
	public String submit(@Validated ProductForm form, Model model, BindingResult bindingResult) {
		logger.info("开发者推荐信息：{}", form.toJSONString());
		addTitle(policy.getRecommend(), model);
		checkAssert(bindingResult);
		Product record = productS.addProduct(form);
		model.addAttribute("record", record);
		model.addAttribute("step", "3");
		return "recommend/submit";
	}

}
