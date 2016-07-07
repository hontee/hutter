package com.hutter.master.auto.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hutter.master.ApplicationTests;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.Product;
import com.hutter.master.service.ProductService;

/**
 * 自动化测试：产品服务
 * @author Administrator
 *
 */
public class ProductServiceTests extends ApplicationTests {

	@Autowired
	private ProductService productS;
	
	@Test
	public void findOne() throws BaseException {
		logger.info("### 查询单个产品");
		Product record = productS.findOne(1L);
		Assert.assertNotNull(record);
	}
	
	@Test
	public void findAll() throws BaseException {
		logger.info("### 分页查询产品");
		Page<Product> page = productS.findAll(new PageRequest(0, 10));
		for (Product product : page) {
			System.out.println(ToStringBuilder.reflectionToString(product));
		}
	}
	
}
