package com.forumManager;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.DaoUtils;
import com.entity.Course;
import com.entity.Faculty;
import com.entity.Post;
import com.entity.Student;
import com.entity.User;
import com.entity.Vote;

public class ForumController 
{
	@SuppressWarnings("unchecked")
	public JSONArray makeCourseJSON(List<Course> courseList)
	{
		JSONArray courseArray=new JSONArray();
		for(Course course:courseList)
		{
			JSONObject courseObject=new JSONObject();
			courseObject.put("courseCode", course.getCourseCode());
			courseObject.put("courseName", course.getCourseName());
			courseArray.add(courseObject);
		}
		return courseArray;
	}
	
	public JSONArray getCourseList(Faculty faculty)
	{
		DaoUtils dao=new DaoUtils();
		faculty=dao.getObjectByID(Faculty.class, faculty.getUsername());
		return makeCourseJSON(faculty.getCourseList());
	}
	
	public JSONArray getCourseList(Student student)
	{
		DaoUtils dao=new DaoUtils();
		student=dao.getObjectByID(Student.class, student.getUsername());
		return makeCourseJSON(student.getCourseList());
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getPostObject(long postID,String username)
	{
		DaoUtils dao=new DaoUtils();
		Post post=dao.getObjectByID(Post.class, postID);
		JSONObject postObject=new JSONObject();
		postObject.put("postId", post.getPostId());
		postObject.put("description", post.getDescription());
		postObject.put("author", post.getUser().getUsername());
		long upVote=0,downVote=0,userVote=0;
		for(Vote v:post.getVoteList())
		{
			if(v.isVoteType())
				upVote++;
			else
				downVote++;
			if(v.getUser().getUsername().equalsIgnoreCase(username))
			{
				if(v.isVoteType())
					userVote=1;
				else
					userVote=-1;
			}	
		}
		postObject.put("upVote", upVote);
		postObject.put("downVote", downVote);
		postObject.put("userVote", userVote);
		JSONArray replies=new JSONArray();
		for(Post p:post.getRepliesList())
			replies.add(getPostObject(p.getPostId(),username));
		postObject.put("replies", replies);
		return postObject;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getPostList(String courseCode,String username)
	{
		DaoUtils dao=new DaoUtils();
		Course course=dao.getObjectByID(Course.class, courseCode);
		JSONArray postArray=new JSONArray();
		for(Post post:course.getPostList())
		{
			JSONObject postObject=new JSONObject();
			postObject.put("postID", post.getPostId());
			postObject.put("description", post.getDescription());
			postObject.put("username", post.getUser().getUsername());
			postArray.add(postObject);
		}	
		return postArray;
	}
	
	public boolean replyPost(long postID,String reply,String username)
	{
		DaoUtils dao=new DaoUtils();
		Post post=dao.getObjectByID(Post.class, postID);
		User user=dao.getObjectByID(User.class, username);
		Post replyPost=new Post();
		replyPost.setDescription(reply);
		replyPost.setUser(user);
		post.getRepliesList().add(replyPost);
		if(dao.updateEntity(post))
			return true;
		else
			return false;
	}
	
	public boolean votePost(long postID,boolean voteType,String username)
	{
		DaoUtils dao=new DaoUtils();
		Post post=dao.getObjectByID(Post.class, postID);
		User user=dao.getObjectByID(User.class, username);
		Vote vote=new Vote();
		vote.setVoteType(voteType);
		vote.setUser(user);
		post.getVoteList().add(vote);
		if(dao.updateEntity(post))
			return true;
		else
			return false;
	}
	
}
