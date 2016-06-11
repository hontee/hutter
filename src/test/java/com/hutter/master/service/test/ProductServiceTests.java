package com.hutter.master.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hutter.master.HutterApplicationTests;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;
import com.hutter.master.service.ProductService;

public class ProductServiceTests extends HutterApplicationTests {

	@Autowired
	private ProductService productS;
	
	@Test
	public void addProduct() throws BaseException {
		ProductForm form = new ProductForm();
		form.setUrl("https://www.aliyun.com/");
		form.setTitle("阿里云");
		form.setDescription("全球领先的云计算服务平台");
		Product record = productS.addProduct(form);
		System.out.println(record.getTitle());
	}
	
	@Test
	public void findOne() throws BaseException {
		Product record = productS.findOne(1L);
		System.out.println(record.getTitle());
	}
	
	@Test
	public void findAll() throws BaseException {
		Page<Product> page = productS.findAll(new PageRequest(0, 10));
		for (Product product : page.getContent()) {
			System.out.println(product.getUrl());
		}
	}
	
}
