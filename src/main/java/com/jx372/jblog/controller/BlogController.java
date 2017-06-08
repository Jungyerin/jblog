package com.jx372.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jx372.jblog.service.BlogService;
import com.jx372.jblog.vo.BlogVo;


@Controller
@RequestMapping( "/blog" )
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping("/{id}")
	public String join(@PathVariable("id") String id, Model model){
		
		BlogVo blogVo = blogService.getMain(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic/{id}")
	public String adminbasic(@PathVariable("id") String id, Model model){
		BlogVo blogVo = blogService.getMain(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}	
	

}
