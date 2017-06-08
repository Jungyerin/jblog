package com.jx372.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.jblog.repository.BlogDao;
import com.jx372.jblog.repository.UserDao;
import com.jx372.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BlogDao blogDao;

	public void join(UserVo userVo) {
		userDao.insert(userVo);
		blogDao.insert();
		blogDao.insertCategory();				
	}
	
	public UserVo getUser(String id, String password){
		
		UserVo userVo=userDao.get(id, password);
		
		return userVo;
	}
	
	

}
