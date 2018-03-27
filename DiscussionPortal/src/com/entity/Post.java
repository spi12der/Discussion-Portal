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
@Table(name = "POST")
public class Post 
{
	@Id @GeneratedValue
	@Column(name = "POST_ID")
	private long postId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@OneToMany(mappedBy = "parentPost")
    private List<Post> repliesList = new ArrayList<Post>();
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Vote> voteList = new ArrayList<Vote>();
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "USERNAME")
	private User user;
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "COURSE_CODE")
	private Course course;

	@ManyToOne(cascade={CascadeType.ALL}, optional = true)
    @JoinColumn(name = "REPLY_ID")
	private Post parentPost;
	
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getRepliesList() {
		return repliesList;
	}

	public void setRepliesList(List<Post> repliesList) {
		this.repliesList = repliesList;
	}

	public List<Vote> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<Vote> voteList) {
		this.voteList = voteList;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Post getParentPost() {
		return parentPost;
	}

	public void setParentPost(Post parentPost) {
		this.parentPost = parentPost;
	}
		
}
