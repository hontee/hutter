package com.hutter.master.mvc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.PageForm;
import com.hutter.master.data.form.ProductForm;
import com.hutter.master.mvc.base.BaseRest;
import com.hutter.master.mvc.base.Response;
import com.hutter.master.service.ProductService;

@RestController
@Scope("prototype")
@RequestMapping("api/posts")
public class ProductRest extends BaseRest {
	
	Logger logger = LoggerFactory.getLogger(ProductRest.class);

	@Autowired
	private ProductService productS;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<Response> findAll(PageForm page) {
		Response body = new Response();
		Page<Product> pages = productS.findAll(page.buildPageable());
		body.setSuccess(true);
		body.setMessage("查询成功");
		body.setResult(pages);
		return buildResponse(body);
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public ResponseEntity<Response> addProduct(@Validated ProductForm form, BindingResult r) {
		Response body = new Response();
		if (r.hasErrors()) {
			body.setMessage("请求参数错误");
			return buildResponse(body);
		}
		
		Product entity = productS.addProduct(form);
		body.setSuccess(true);
		body.setMessage("添加成功");
		body.setResult(entity);
		return buildResponse(body);
	}
	
}
