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
@Table(name="FACULTY")  
@PrimaryKeyJoinColumn(name="USERNAME")  
public class Faculty extends User
{
	@Column(name = "faculty_code")
	private String facultyCode;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "FACULTY_COURSE", 
        joinColumns = { @JoinColumn(name = "USERNAME") }, 
        inverseJoinColumns = { @JoinColumn(name = "COURSE_CODE") })
    private List<Course> courseList = new ArrayList<Course>();
	
	@OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<FeedbackRequest> requestList = new ArrayList<FeedbackRequest>();

	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public List<FeedbackRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<FeedbackRequest> requestList) {
		this.requestList = requestList;
	}
	
}
