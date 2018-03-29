package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class Course 
{
	@Id
	@Column(name = "COURSE_CODE")
	private String courseCode;
	
	@Column(name = "COURSE_NAME")
	private String courseName;
	
	@ManyToMany(mappedBy="courseList")
    private List<Student> students = new ArrayList<Student>();
	
	@ManyToMany(mappedBy="courseList")
    private List<Faculty> faculties = new ArrayList<Faculty>();
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<Post>();
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<FeedbackRequest> requestList = new ArrayList<FeedbackRequest>();

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public List<FeedbackRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<FeedbackRequest> requestList) {
		this.requestList = requestList;
	}
}
