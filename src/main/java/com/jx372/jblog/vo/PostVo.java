package com.jx372.jblog.vo;

public class PostVo {
	
	private Long no;
	private Long cNo;
	private String ctitle;
	private String title;
	private String content;
	private String date;
	private String category;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getcNo() {
		return cNo;
	}
	public void setcNo(Long cNo) {
		this.cNo = cNo;
	}
	
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", cNo=" + cNo + ", ctitle=" + ctitle + ", title=" + title + ", content=" + content
				+ ", date=" + date + ", category=" + category + "]";
	}

	
	
	

}
