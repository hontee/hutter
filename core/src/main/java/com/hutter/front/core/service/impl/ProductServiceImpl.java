package com.hutter.front.core.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.hutter.front.core.domain.Product;
import com.hutter.front.core.form.ProductForm;
import com.hutter.front.core.repository.ProductRepository;
import com.hutter.front.core.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productR;
	
	@Override
	public Product addProduct(ProductForm form) {
		Preconditions.checkNotNull(form);
		logger.info("addProduct: {}", form.toJSONString());
		Product entity = new Product();
		entity.setName(entity.randomUUID());
		entity.setState((byte)1);
		entity.setHit(0);
		entity.setUrl(form.getUrl());
		entity.setTitle(form.getTitle());
		entity.setDescription(form.getDescription());
		return productR.saveAndFlush(entity);
	}

	@Override
	public boolean checkIsExists(String url) {
		Preconditions.checkNotNull(url, "Check URL is empty.");
		return productR.findByUrl(url) != null;
	}

	@Override
	public Product findOne(Long id) {
		return productR.findOne(id);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productR.findAll(pageable);
	}

	@Override
	public Page<Product> findAll(String q, Pageable pageable) {
		Preconditions.checkNotNull(q, "search keywords is null.");
		return productR.findAll(new Specification<Product>() {
			
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.like(root.get("title"), "%" + q + "%");
				return query.where(predicate).getRestriction();
			}
		}, pageable);
	}
	
	@Override
	public Page<Product> findAll(Long uid, Pageable pageable) {
		Preconditions.checkNotNull(uid, "user id is null.");
		return productR.findAll(new Specification<Product>() {
			
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.equal(root.get("creator"), uid);
				return query.where(predicate).getRestriction();
			}
		}, pageable);
	}

	@Override
	public String hit(Long id) {
		Product entity = productR.findOne(id);
		Preconditions.checkNotNull(entity, "Product id isn't found.");
		entity.setHit(entity.getHit().intValue() + 1);
		productR.save(entity);
		return entity.getUrl();
	}

}
