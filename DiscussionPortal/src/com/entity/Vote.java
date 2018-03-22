package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VOTE")
public class Vote 
{
	@Id @GeneratedValue
	@Column(name = "VOTE_ID")
	private long voteId;
	
	@Column(name = "VOTE_TYPE")
	private boolean voteType;

	public long getVoteId() {
		return voteId;
	}

	public void setVoteId(long voteId) {
		this.voteId = voteId;
	}

	public boolean isVoteType() {
		return voteType;
	}

	public void setVoteType(boolean voteType) {
		this.voteType = voteType;
	}
	
}
