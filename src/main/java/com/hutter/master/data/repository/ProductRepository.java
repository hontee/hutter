package com.hutter.master.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hutter.master.data.domain.Product;

/**
 * 产品数据仓库
 * @author Administrator
 * @date 2016-06-08
 */
@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	/**
	 * 是否存在URL
	 * @param url
	 * @return
	 */
	Product findByUrl(String url);
}
