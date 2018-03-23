package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User 
{
	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "AGE")
	private int age;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_VOTE", 
        joinColumns = { @JoinColumn(name = "USERNAME") }, 
        inverseJoinColumns = { @JoinColumn(name = "VOTE_ID") })
    private List<Vote> voteList = new ArrayList<Vote>();
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private Post post;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Vote> getVoteList() {
		return voteList;
	}
	public void setVoteList(List<Vote> voteList) {
		this.voteList = voteList;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
