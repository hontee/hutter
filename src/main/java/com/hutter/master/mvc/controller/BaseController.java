package com.hutter.master.mvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.hutter.master.mvc.utils.Pager;

public abstract class BaseController {
	
	/**
	 * 设置标题
	 * @param title
	 * @param model
	 */
	public void setTitle(String title, Model model) {
		model.addAttribute("title", title);
	}
	
	/**
	 * 数据列表
	 * @param records
	 * @param model
	 */
	public void addRecords(Object records, Model model) {
		model.addAttribute("records", records);
	}
	
	/**
	 * 分页
	 * @param pages
	 * @param urlTemplate
	 * @param model
	 */
	public void addPager(Page<?> pages, String urlTemplate, Model model) {
		model.addAttribute("pager", Pager.of(pages, urlTemplate));
	}

}
