package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FEEDBACK_RESPONSE")
public class FeedbackResponse 
{
	@Id @GeneratedValue
	@Column(name = "RESPONSE_ID")
	private long responseId;
	
	@Column(name = "SUBMISSION_DATE")
	private Date submissionDate;
	
	@Column(name = "RESPONSE")
	private String response;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "USERNAME")
	private Student student;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "FEEDBACK_ID")
	private FeedbackRequest feedbackRequest;

	public long getResponseId() {
		return responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeedbackRequest getFeedbackRequest() {
		return feedbackRequest;
	}

	public void setFeedbackRequest(FeedbackRequest feedbackRequest) {
		this.feedbackRequest = feedbackRequest;
	}
}
