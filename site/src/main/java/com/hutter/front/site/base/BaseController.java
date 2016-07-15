package com.hutter.front.site.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.hutter.front.site.utils.Pager;

public abstract class BaseController extends AssertBase {
	
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
