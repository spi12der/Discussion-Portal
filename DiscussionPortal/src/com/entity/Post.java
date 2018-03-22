package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POST_POST", 
        joinColumns = { @JoinColumn(name = "POST_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "POST_ID") })
    private List<Post> repliesList = new ArrayList<Post>();
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POST_VOTE", 
        joinColumns = { @JoinColumn(name = "POST_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "VOTE_ID") })
    private List<Vote> voteList = new ArrayList<Vote>();

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
		
}
