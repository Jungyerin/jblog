package com.jx372.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.jblog.vo.UserVo;

@Repository
public class UserDao {
	
    @Autowired
    private SqlSession sqlSession;
	
	@Autowired
	private DataSource datasource;

	public boolean insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);
		return count==1;
		
	}

	public UserVo get(String id, String password) {
		Map<String,Object> map = new HashMap<String , Object>();
		
		map.put("id", id);
		map.put("password", password);
		
		UserVo userVo=sqlSession.selectOne("user.getByLogin",map);
		
		return userVo;
	}

}
