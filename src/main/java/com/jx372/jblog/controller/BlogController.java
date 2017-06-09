package com.jx372.jblog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.jblog.service.BlogService;
import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.PostVo;
import com.jx372.security.Auth;


@Controller
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model,
			@RequestParam(value="ctitle", required=true, defaultValue="미분류") String ctitle,
			@RequestParam(value="no", required=true, defaultValue="") Long no){
		BlogVo blogVo = blogService.getMain(id);
		System.out.println(no);
		PostVo postVo = blogService.getPost(id, no, ctitle);
		Map<String, Object> map = blogService.getMainList(id, ctitle);
		
		model.addAttribute("map", map);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("postVo", postVo);

		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping(value="/{id}/admin/basic", method = RequestMethod.GET)
	public String basic(@PathVariable("id") String id, Model model){
		BlogVo blogVo = blogService.getMain(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}	
	
	@Auth
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.POST)
	public String update(@ModelAttribute BlogVo blogVo,
			@RequestParam("file1") MultipartFile file1,
			@PathVariable("id") String id, Model model){
	
		String url1 = blogService.restore(file1);	
		blogVo.setLogo(url1);
		blogVo.setBlogid(id);
		blogService.update(blogVo);
		model.addAttribute("blogVo", blogVo);
		return "redirect:/{id}/admin/basic";
	}
	
	@Auth
	@RequestMapping(value = "/{id}/admin/category",method = RequestMethod.GET)
	public String category(@PathVariable("id") String id, Model model){
		BlogVo blogVo = blogService.getMain(id);
		List<CategoryVo> list = blogService.getList(id);	
		int count = blogService.getCount(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value = "/{id}/admin/category", method = RequestMethod.POST)
	public String category(@PathVariable("id") String id, 
			@RequestParam(value = "name", required = true, defaultValue = "") String name,
			@RequestParam(value = "desc", required = true, defaultValue = "") String desc) {
		
		blogService.insertC(id, name, desc);
		
		return "redirect:/{id}/admin/category";		
	}
	
	@Auth
	@RequestMapping(value = "/{id}/admin/category/delete")
	public String categoryDelete(@PathVariable("id") String id, 
			@RequestParam(value = "no") Long no) {

		blogService.categoryDelete(id, no);
		
		return "redirect:/{id}/admin/category";		
	}
	
	@Auth
	@RequestMapping(value="/{id}/admin/write", method = RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model){
		BlogVo blogVo = blogService.getMain(id);
		List<CategoryVo> list = blogService.getList(id);	
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list", list);
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable("id") String id,
					@ModelAttribute PostVo postVo) {
		
		blogService.write(id, postVo);
		
		return "redirect:/{id}/admin/category";		
	}
 
	

}
