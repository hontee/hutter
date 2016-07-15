package com.hutter.front.site.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hutter.front.core.domain.Product;
import com.hutter.front.core.form.PageForm;
import com.hutter.front.core.form.ProductForm;
import com.hutter.front.core.service.ProductService;
import com.hutter.front.site.base.BaseRest;
import com.hutter.front.site.base.Response;

@RestController
@Scope("prototype")
@RequestMapping("api/posts")
public class ProductRest extends BaseRest {
	
	Logger logger = LoggerFactory.getLogger(ProductRest.class);

	@Autowired
	private ProductService productS;
	
	@GetMapping("list")
	public ResponseEntity<Response> findAll(PageForm page) {
		Response body = new Response();
		Page<Product> pages = productS.findAll(page.buildPageable());
		body.setSuccess(true);
		body.setMessage("查询成功");
		body.setResult(pages);
		return buildResponse(body);
	}
	
	@PostMapping("new")
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
