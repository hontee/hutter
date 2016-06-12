package com.hutter.master.mvc.controller;

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

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.base.fetch.FetchClient;
import com.hutter.master.base.fetch.WebInfo;
import com.hutter.master.base.properties.TitlePolicy;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;
import com.hutter.master.mvc.annotation.Token;
import com.hutter.master.service.ProductService;

@Controller
@Scope("prototype")
@RequestMapping("recommend")
public class RecommendController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private FetchClient client;
	
	@Autowired
	private TitlePolicy policy;
	
	/**
	 * 推荐新产品
	 * @return
	 */
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String recommend(Model model) {
		setTitle(policy.getRecommend(), model);
		return "recommend/new";
	}
	
	/**
	 * 确认产品信息
	 * @return
	 */
	@Token(add = true)
	@RequestMapping(value = "confirm", method=RequestMethod.GET)
	public String confirm(@RequestParam String url, Model model) {
		setTitle(policy.getRecommendConfirm(), model);
		
		if (productS.checkIsExists(url)) {
			model.addAttribute("exists", true);
			model.addAttribute("url", url);
			return "recommend/new";
		}
		
		WebInfo webInfo = client.fetch(url);
		model.addAttribute("webInfo", webInfo);
		return "recommend/confirm";
	}
	
	/**
	 * 发布
	 * @return
	 * @throws BaseException 
	 */
	@Token(delete = true)
	@RequestMapping(value = "submit", method=RequestMethod.GET)
	public String submit(@Validated ProductForm form, Model model, BindingResult r) throws BaseException {
		setTitle(policy.getRecommendSubmit(), model);
		
		if (r.hasErrors()) {
			logger.error("请求参数错误.");
		}
		
	 	Product record = productS.addProduct(form);
	 	model.addAttribute("record", record);
		return "recommend/submit";
	}

}
