package com.jx372.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.jblog.repository.BlogDao;
import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	private static final String SAVE_PATH="/uploads";
	private static final String PREFIX_URL="/uploads/images/";
	
	public BlogVo getMain(String id) {
		BlogVo blogVo = blogDao.getMain(id); 
		return blogVo;
	}

	public boolean insertC(String id, String name, String desc) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("title", name);
		map.put("description", desc);
		
		return blogDao.insertC(map);		
		
	}
	
	public void insertC(Map<String, Object> map) {
		
		blogDao.insertC2(map);
	}
	

	public List<CategoryVo> getList(String id) {
		List<CategoryVo> list = blogDao.getList(id);
		return list;
	}

	public int getCount(String id) {
		return blogDao.getCount(id);
	}

	public boolean categoryDelete(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		return blogDao.categoryDelete(map);		
	}

	public void write(String id, PostVo postVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("postVo", postVo);
		blogDao.insertP(map);		
		
	}

	public Map<String, Object> getMainList(String id, String ctitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PostVo> pList=blogDao.getPostList(id,ctitle);
		List<CategoryVo> cList = blogDao.getList(id);
		map.put("pList", pList);
		map.put("cList", cList);
		return map;
	}

	public PostVo getPost(String id, Long no, String ctitle) {		
		return blogDao.getPost(id, no, ctitle);
	}
	
	public String restore(MultipartFile file) {
		String url = "";
		try {
			
			if (file.isEmpty() == true) {
				return url;
			}

			String originalFileName = file.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
			Long fileSize = file.getSize();
			String saveFileName = genSaveFileName(extName);

			System.out.println("##########" + originalFileName);
			System.out.println("##########" + extName);
			System.out.println("##########" + fileSize);
			System.out.println("##########" + saveFileName);

			writeFile(file, saveFileName);
			
			url = PREFIX_URL+saveFileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return url;
	}
	
	private String genSaveFileName(String extName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos =  new FileOutputStream(SAVE_PATH+"/"+saveFileName);
		fos.write(fileData);
		fos.close();

	}

	public void update(BlogVo blogVo) {
		blogDao.update(blogVo);
		
	}



}
