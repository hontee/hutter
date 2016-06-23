package com.hutter.master.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;

public interface ProductService {

	/**
	 * 添加产品
	 * @param form
	 * @return
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
	 */
	Product findOne(Long id);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 */
	Page<Product> findAll(Pageable pageable);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 */
	Page<Product> findAll(String q, Pageable pageable);
	
	/**
	 * 分页查询
	 * @param pageable
	 * @return
	 */
	Page<Product> findAll(Long uid, Pageable pageable);
	
	/**
	 * 点击统计
	 * @param id
	 * @return
	 */
	String hit(Long id);
}
