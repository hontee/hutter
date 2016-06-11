package com.hutter.master.data.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 分页表单
 * @author Administrator
 */
public class PageForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(PageForm.class);
	
	/**
	 * 当前页数
	 */
	@Min(1)
	@Max(9999)
	private Integer page = 1;
	
	/**
	 * 每页显示数
	 */
	private Integer size = 20;
	
	/**
	 * 排序字段
	 */
	private String sort;
	
	/**
	 * 排序方式：ASC,DESC
	 */
	private String order;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public PageRequest buildPageable() {
		if (StringUtils.isEmpty(sort)) {
			return new PageRequest(page - 1, size);
		}
		
		// 自定义排序
		Direction direction = Direction.ASC;
		
		try {
			direction = Direction.valueOf(order);
		} catch (Exception e) {
			log.warn("Order Direction Convert failed: order=[{}]", order);
		}
		
		Sort s = new Sort(new Sort.Order(direction, sort));
		return new PageRequest(page - 1, size, s);
	}
	
	/**
	 * 按最新排序
	 */
	public void orderNewest() {
		this.setSort("created");
		this.setOrder("DESC");
	}

}
