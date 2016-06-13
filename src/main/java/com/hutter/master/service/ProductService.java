package com.hutter.master.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;

public interface ProductService {

	/**
	 * 添加产品
	 * @param form
	 * @return
	 * @throws BaseException
	 */
	Product addProduct(ProductForm form);
	
	/**
	 * 检测是否存在URL
	 * @param url
	 * @return
	 */
	boolean checkIsExists(String url);
	
	/**
	 * 查询单个产品
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	Product findOne(Long id);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 * @throws BaseException
	 */
	Page<Product> findAll(Pageable pageable);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 * @throws BaseException
	 */
	Page<Product> findAll(String q, Pageable pageable);
	
	/**
	 * 点击统计
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	String hit(Long id);
}
