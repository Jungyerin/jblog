package com.jx372.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.jblog.repository.BlogDao;
import com.jx372.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	public BlogVo getMain(String id) {
		BlogVo blogVo = blogDao.getMain(id); 
		return blogVo;
	}
	

}
