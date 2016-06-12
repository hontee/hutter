package com.hutter.master.mvc.controller;

import javax.servlet.http.HttpServletRequest;

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
	 * 数据列表和分页
	 * @param pages
	 * @param urlTemplate
	 * @param model
	 */
	public void addRecordsAndPager(Page<?> pages, HttpServletRequest request, Model model) {
		model.addAttribute("records", pages.getContent());
		model.addAttribute("pager", Pager.of(pages, request));
	}

}
