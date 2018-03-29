package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity  
@Table(name="STUDENT")  
@PrimaryKeyJoinColumn(name="USERNAME")  
public class Student extends User
{
	@Column(name = "roll_no")
	private String rollNo;
	
	@Column(name = "batch")
	private String batch;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_COURSE", 
        joinColumns = { @JoinColumn(name = "USERNAME") }, 
        inverseJoinColumns = { @JoinColumn(name = "COURSE_CODE") })
    private List<Course> courseList = new ArrayList<Course>();
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<FeedbackResponse> responseList = new ArrayList<FeedbackResponse>();

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public List<FeedbackResponse> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<FeedbackResponse> responseList) {
		this.responseList = responseList;
	}
}
