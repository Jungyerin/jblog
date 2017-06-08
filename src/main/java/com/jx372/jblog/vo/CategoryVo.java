package com.jx372.jblog.vo;

public class CategoryVo {
	private Long no;
	private Long userNo;
	private String title;
	private String description;
	private String date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", description=" + description
				+ ", date=" + date + "]";
	}
	
	
	

}
