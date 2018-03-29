package com.feedback;

import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.DaoUtils;
import com.entity.Course;
import com.entity.Faculty;
import com.entity.FeedbackRequest;
import com.entity.FeedbackResponse;
import com.entity.Student;
import com.forumManager.DateUtils;

public class FeedbackController 
{
	@SuppressWarnings("unchecked")
	public JSONArray getCourseFeedbackJSON(String username)
	{
		JSONArray courseArr=new JSONArray();
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Faculty facutly=dao.getObjectByID(Faculty.class, username);
		Map<Course,List<FeedbackRequest>> m=new HashMap<Course,List<FeedbackRequest>>();
		for(FeedbackRequest fr:facutly.getRequestList())
		{
			List<FeedbackRequest> l;
			if(m.get(fr.getCourse())==null)
				l=m.get(fr.getCourse());
			else
				l=new ArrayList<FeedbackRequest>();
			l.add(fr);
			m.put(fr.getCourse(), l);
		}
		for(Map.Entry<Course,List<FeedbackRequest>> entry : m.entrySet())
		{
			JSONObject courseObject=new JSONObject();
			Course c=entry.getKey();
			courseObject.put("code", c.getCourseCode());
			courseObject.put("name", c.getCourseName());
			JSONArray requestArr=new JSONArray();
			for(FeedbackRequest fr:entry.getValue())
			{
				JSONObject frObject=new JSONObject();
				frObject.put("id", fr.getFeedbackId());
				frObject.put("number", fr.getFeedbackNumber());
				frObject.put("date", DateUtils.getFormat(fr.getInitiatedDate()));
				frObject.put("replies", fr.getResponseList().size());
				frObject.put("total", c.getStudents().size());
				requestArr.add(frObject);
			}
			courseObject.put("request",requestArr);
			courseArr.add(courseObject);
		}
		dao.closeConnection();
		return courseArr;
	}
	
	public boolean initiateRequest(String username,String courseCode)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Faculty faculty=dao.getObjectByID(Faculty.class, username);
		Course course=dao.getObjectByID(Course.class, courseCode);
		FeedbackRequest fr=new FeedbackRequest();
		fr.setFeedbackNumber(course.getRequestList().size()+1);
		fr.setInitiatedDate(new Date());
		fr.setCourse(course);
		fr.setFaculty(faculty);
		boolean res=false;
		if(dao.addEntity(fr))
			res=true;
		dao.closeConnection();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getRequestJSON(String username)
	{
		JSONArray courseArr=new JSONArray();
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Student student=dao.getObjectByID(Student.class, username);
		for(Course c:student.getCourseList())
		{
			JSONObject courseObject=new JSONObject();
			courseObject.put("code", c.getCourseCode());
			courseObject.put("name", c.getCourseName());
			JSONArray requestArr=new JSONArray();
			for(FeedbackRequest fr:c.getRequestList())
			{
				JSONObject frObject=new JSONObject();
				frObject.put("id", fr.getFeedbackId());
				frObject.put("number", fr.getFeedbackNumber());
				frObject.put("date", DateUtils.getFormat(fr.getInitiatedDate()));
				
				requestArr.add(frObject);
			}
			courseObject.put("request", requestArr);
			courseArr.add(courseObject);
		}
		dao.closeConnection();
		return courseArr;
	}
	
	public boolean submitResponse(long requestId,String responseString,String username)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Student student=dao.getObjectByID(Student.class, username);
		FeedbackRequest fr=dao.getObjectByID(FeedbackRequest.class, requestId);
		FeedbackResponse fres=new FeedbackResponse();
		fres.setResponse(responseString);
		fres.setSubmissionDate(new Date());
		fres.setFeedbackRequest(fr);
		fres.setStudent(student);
		boolean res=false;
		if(dao.addEntity(fres))
			res=true;
		dao.closeConnection();
		return res;
	}
	
	public JSONObject getResponses(long requestId)
	{
		JSONObject responseObject=new JSONObject();
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		FeedbackRequest fr=dao.getObjectByID(FeedbackRequest.class, requestId);
		for(FeedbackResponse fres:fr.getResponseList())
		{
			System.out.println(fres.getResponse());
		}
		dao.closeConnection();
		return responseObject;
	}
}
