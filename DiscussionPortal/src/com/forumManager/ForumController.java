package com.forumManager;

import java.util.Date;
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
		dao.openConnection();
		faculty=dao.getObjectByID(Faculty.class, faculty.getUsername());
		JSONArray res=makeCourseJSON(faculty.getCourseList());
		dao.closeConnection();
		return res;
	}
	
	public JSONArray getCourseList(Student student)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		student=dao.getObjectByID(Student.class, student.getUsername());
		JSONArray res=makeCourseJSON(student.getCourseList());
		dao.closeConnection();
		return res;
	}
	
	public JSONObject getPostObject(long postID,String username)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		JSONObject postObject=getPostObject(postID, username,dao);
		dao.closeConnection();
		return postObject;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getPostObject(long postID,String username,DaoUtils dao)
	{
		Post post=dao.getObjectByID(Post.class, postID);
		JSONObject postObject=new JSONObject();
		postObject.put("postId", post.getPostId());
		postObject.put("description", post.getDescription());
		postObject.put("author", post.getUser().getName());
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
		postObject.put("date", DateUtils.getFormat(post.getCreationDate()));
		JSONArray replies=new JSONArray();
		for(Post p:post.getRepliesList())
			replies.add(getPostObject(p.getPostId(),username,dao));
		postObject.put("replies", replies);
		return postObject;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getPostList(String courseCode)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Course course=dao.getObjectByID(Course.class, courseCode);
		JSONArray postArray=new JSONArray();
		for(Post post:course.getPostList())
		{
			JSONObject postObject=new JSONObject();
			postObject.put("postID", post.getPostId());
			postObject.put("description", post.getDescription());
			postObject.put("username", post.getUser().getName());
			postObject.put("date", DateUtils.getFormat(post.getCreationDate()));
			postArray.add(postObject);
		}	
		dao.closeConnection();
		return postArray;
	}
	
	public boolean createPost(String courseCode,String postDescription,String username)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Course course=dao.getObjectByID(Course.class, courseCode);
		User user=dao.getObjectByID(User.class, username);
		Post post=new Post();
		post.setDescription(postDescription);
		post.setUser(user);
		post.setCreationDate(new Date());
		post.setCourse(course);
		boolean result=false;
		if(dao.addEntity(post))
			result=true;
		dao.closeConnection();
		return result;
	}
	
	public boolean replyPost(long postID,String reply,String username)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Post post=dao.getObjectByID(Post.class, postID);
		User user=dao.getObjectByID(User.class, username);
		Post replyPost=new Post();
		replyPost.setDescription(reply);
		replyPost.setUser(user);
		replyPost.setCreationDate(new Date());
		replyPost.setParentPost(post);
		boolean result=false;
		if(dao.addEntity(replyPost))
			result=true;
		dao.closeConnection();
		return result;
	}
	
	public boolean votePost(long postID,boolean voteType,String username)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Post post=dao.getObjectByID(Post.class, postID);
		User user=dao.getObjectByID(User.class, username);
		Vote vote=new Vote();
		vote.setVoteType(voteType);
		vote.setUser(user);
		post.getVoteList().add(vote);
		boolean result=true;
		if(dao.updateEntity(post))
			result=true;
		dao.closeConnection();
		return result;
	}
	
}
