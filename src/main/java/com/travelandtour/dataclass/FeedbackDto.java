package com.travelandtour.dataclass;

public class FeedbackDto {
	private Integer experience;
	private Integer satisfy;
	private Integer recommend;
	private String message;
	private Long userId;
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Integer getSatisfy() {
		return satisfy;
	}
	public void setSatisfy(Integer satisfy) {
		this.satisfy = satisfy;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
