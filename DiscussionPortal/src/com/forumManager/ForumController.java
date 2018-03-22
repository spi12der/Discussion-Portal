package com.forumManager;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.DaoUtils;
import com.entity.Course;
import com.entity.Faculty;
import com.entity.Post;
import com.entity.Student;
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
	
	public JSONArray getPostList(Course course)
	{
		DaoUtils dao=new DaoUtils();
		return null;
	}
	
	public boolean replyPost(long postID,String reply)
	{
		DaoUtils dao=new DaoUtils();
		Post post=dao.getObjectByID(Post.class, postID);
		Post replyPost=new Post();
		replyPost.setDescription(reply);
		post.getRepliesList().add(replyPost);
		if(dao.updateEntity(post))
			return true;
		else
			return false;
	}
	
	public boolean votePost(long postID,boolean voteType)
	{
		DaoUtils dao=new DaoUtils();
		Post post=dao.getObjectByID(Post.class, postID);
		Vote vote=new Vote();
		vote.setVoteType(voteType);
		post.getVoteList().add(vote);
		if(dao.updateEntity(post))
			return true;
		else
			return false;
	}
	
}
