package com.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FEEDBACK_REQUEST")
public class FeedbackRequest 
{
	@Id @GeneratedValue
	@Column(name = "FEEDBACK_ID")
	private long feedbackId;
	
	@Column(name = "FEEDBACK_NUMBER")
	private long feedbackNumber;
	
	@Column(name = "INITIATED_DATE")
	private Date initiatedDate;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "USERNAME")
	private Faculty faculty;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "COURSE_CODE")
	private Course course;
	
	@OneToMany(mappedBy = "feedbackRequest", cascade = CascadeType.ALL)
    private List<FeedbackResponse> responseList = new ArrayList<FeedbackResponse>();

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public long getFeedbackNumber() {
		return feedbackNumber;
	}

	public void setFeedbackNumber(long feedbackNumber) {
		this.feedbackNumber = feedbackNumber;
	}

	public Date getInitiatedDate() {
		return initiatedDate;
	}

	public void setInitiatedDate(Date initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<FeedbackResponse> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<FeedbackResponse> responseList) {
		this.responseList = responseList;
	}
}
