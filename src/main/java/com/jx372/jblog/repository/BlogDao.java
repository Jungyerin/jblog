package com.jx372.jblog.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	   @Autowired
	    private SqlSession sqlSession;
		
		@Autowired
		private DataSource datasource;

		public boolean insert() {
			int count = sqlSession.insert("blog.insertLogin");
			return count==1;			
		}
		

		public boolean insertCategory() {
			int count = sqlSession.insert("blog.insertCategory");
			return count == 1;			
		}


		public BlogVo getMain(String id) {
			BlogVo blogVo = sqlSession.selectOne("blog.getMain", id);
			return blogVo;
		}

}
