package com.hutter.master.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.Product;
import com.hutter.master.data.form.ProductForm;
import com.hutter.master.data.repository.ProductRepository;
import com.hutter.master.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productR;
	
	@Override
	public Product addProduct(ProductForm form) throws BaseException {

		Product entity = new Product();
		entity.setName(entity.randomUUID());
		entity.setState((byte)1);
		entity.setUrl(form.getUrl());
		entity.setTitle(form.getTitle());
		entity.setDescription(form.getDescription());
		return productR.saveAndFlush(entity);
	}

	@Override
	public boolean checkIsExists(String url) {
		return productR.findByUrl(url) != null;
	}

	@Override
	public Product findOne(Long id) throws BaseException {
		return productR.findOne(id);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) throws BaseException {
		return productR.findAll(pageable);
	}

	@Override
	public Page<Product> findAll(String q, Pageable pageable) throws BaseException {
		Preconditions.checkNotNull(q, "查询关键字不能为空");
		return productR.findAll(new Specification<Product>() {
			
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.like(root.get("title"), "%" + q + "%");
				return query.where(predicate).getRestriction();
			}
		}, pageable);
	}

}
