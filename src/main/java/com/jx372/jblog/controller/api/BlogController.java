package com.jx372.jblog.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.jblog.dto.JSONResult;
import com.jx372.jblog.service.BlogService;
import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.security.Auth;

//@CrossOrigin( origins = { "http://localhost:8080" }, maxAge = 4800 )
@Controller("blogApiController")
public class BlogController {
	
	
	@Autowired
	BlogService blogService;	

	@Auth
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/api",method = RequestMethod.GET)
	public JSONResult category(@PathVariable("id") String id, Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		BlogVo blogVo = blogService.getMain(id);
		List<CategoryVo> list = blogService.getList(id);	
		int count = blogService.getCount(id);
		
		map.put("blogVo", blogVo);
		map.put("list", list);
		map.put("count", count);
		
		return JSONResult.success(list);
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/api", method = RequestMethod.POST)
	public JSONResult category(@PathVariable("id") String id,
			@RequestBody CategoryVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();		
		
		map.put("vo", vo);
		map.put("id", id);
		map.put("no", 0L);
		blogService.insertC(map); 

		System.out.println("add-form : "+vo);
		System.out.println("add-form : "+map.get( "no" ) );
		
		
		return JSONResult.success(map);		
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/delete/api")
	public JSONResult categoryDelete(@PathVariable("id") String id, 
			@RequestParam(value = "no") Long no) {
		blogService.categoryDelete(id, no);;
		//System.out.println("no:"+no+"id:"+id);		
		return JSONResult.success(no);		
	}
	
	
}
