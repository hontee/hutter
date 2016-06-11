package com.hutter.master.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;
import com.hutter.master.mvc.rest.exts.BaseRest;
import com.hutter.master.mvc.rest.exts.ResponseVO;
import com.hutter.master.service.ProductService;

@RestController
@Scope("prototype")
@RequestMapping("api/posts")
public class ProductRest extends BaseRest {

	@Autowired
	private ProductService productS;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO> findAll() throws BaseException {
		ResponseVO body = new ResponseVO();
		Page<Product> page = productS.findAll(new PageRequest(0, 20));
		body.setSuccess(true);
		body.setMessage("查询成功");
		body.setResult(page);
		return buildResponse(body);
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO> addProduct(@Validated ProductForm form, BindingResult r) throws BaseException {
		ResponseVO body = new ResponseVO();
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
