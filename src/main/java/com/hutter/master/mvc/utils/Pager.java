package com.hutter.master.mvc.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.hutter.master.base.utils.HttpUtil;

/**
 * 分页实体
 * @author Administrator
 */
public class Pager implements Serializable {

	private static final long serialVersionUID = 1L;

	private int total;
	private int page;
	private int size;
	private boolean isFirst;
	private boolean isLast;
	private boolean hasNext;
	private boolean hasPrevious;
	private String nextUrl;
	private String previousUrl;
	
	/**
	 * 分页处理
	 * @param pages
	 * @return
	 */
	public static Pager of(Page<?> pages, HttpServletRequest request) {
		Pager pager = new Pager();
		pager.setFirst(pages.isFirst());
		pager.setHasNext(pages.hasNext());
		pager.setHasPrevious(pages.hasPrevious());
		pager.setLast(pages.isLast());
		pager.setPage(pages.getNumber() + 1);
		pager.setSize(pages.getSize());
		pager.setTotal(pages.getTotalPages());
		
		// 设置分页链接
		try {
			String url = HttpUtil.getRequestURL(request);
			String baseUrl = HttpUtil.removeQueryParams(url);
			Map<String, String> map = HttpUtil.getQueryParams(url);
			map.put("page", String.valueOf(pages.getNumber()));
			pager.setPreviousUrl(HttpUtil.appendQueryParams(baseUrl, map));
			map.put("page", String.valueOf(pages.getNumber() + 2));
			pager.setNextUrl(HttpUtil.appendQueryParams(baseUrl, map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pager;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getPreviousUrl() {
		return previousUrl;
	}

	public void setPreviousUrl(String previousUrl) {
		this.previousUrl = previousUrl;
	}
	
}
