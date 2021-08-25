package com.travelandtour.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
    private Long feedbackId;
	private Integer experience;
	private Integer satisfy;
	private Integer recommend;
	private String message;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

	public Feedback() {
		super();
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", experience=" + experience + ", satisfy=" + satisfy
				+ ", recommend=" + recommend + ", message=" + message + ", user=" + user + "]";
	}
}
