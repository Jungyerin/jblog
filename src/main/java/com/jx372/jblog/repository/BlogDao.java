package com.jx372.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.PostVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private DataSource datasource;

	public boolean insert() {
		int count = sqlSession.insert("blog.insertLogin");
		return count == 1;
	}

	public boolean insertCategory() {
		int count = sqlSession.insert("blog.insertCategory");
		return count == 1;
	}

	public BlogVo getMain(String id) {
		BlogVo blogVo = sqlSession.selectOne("blog.getMain", id);
		return blogVo;
	}

	public boolean insertC(Map<String, Object> map) {
		int count = sqlSession.insert("blog.insertC", map);
		return count == 1;

	}

	public List<CategoryVo> getList(String id) {
		List<CategoryVo> list = sqlSession.selectList("blog.getList", id);
		return list;
	}

	public int getCount(String id) {
		return sqlSession.selectOne("blog.getCount", id);
	}

	public boolean categoryDelete(Map<String, Object> map) {
		int count = sqlSession.delete("blog.categoryDelete", map);
		return count == 1;

	}

	public boolean insertP(Map<String, Object> map) {
		int count = sqlSession.insert("blog.insertP", map);
		return count == 1;

	}

	public List<PostVo> getPostList(String id, String ctitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("ctitle", ctitle);
		List<PostVo> list = sqlSession.selectList("blog.getPostList", map);
		return list;
	}

	public PostVo getPost(String id, Long no, String ctitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		map.put("ctitle", ctitle);
		PostVo postVo = sqlSession.selectOne("blog.getPost", map);
		return postVo;
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);

	}

}
