package com.feedback;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
		Map<String,List<FeedbackRequest>> m=new HashMap<String,List<FeedbackRequest>>();
		for(FeedbackRequest fr:facutly.getRequestList())
		{
			List<FeedbackRequest> l;
			if(m.get(fr.getCourse().getCourseCode())!=null)
				l=m.get(fr.getCourse().getCourseCode());
			else
				l=new ArrayList<FeedbackRequest>();
			l.add(fr);
			m.put(fr.getCourse().getCourseCode(), l);
		}
		for(Course c:facutly.getCourseList())
		{
			JSONObject courseObject=new JSONObject();
			courseObject.put("code", c.getCourseCode());
			courseObject.put("name", c.getCourseName());
			JSONArray requestArr=new JSONArray();
			if(m.containsKey(c.getCourseCode()))
			{
				for(FeedbackRequest fr:m.get(c.getCourseCode()))
				{
					JSONObject frObject=new JSONObject();
					frObject.put("id", fr.getFeedbackId());
					frObject.put("number", fr.getFeedbackNumber());
					frObject.put("date", DateUtils.getFormat(fr.getInitiatedDate()));
					frObject.put("replies", fr.getResponseList().size());
					frObject.put("total", c.getStudents().size());
					requestArr.add(frObject);
				}
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
		Set<Long> s=new HashSet<Long>();
		for(FeedbackResponse fres:student.getResponseList())
			s.add(fres.getFeedbackRequest().getFeedbackId());
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
				if(s.contains(fr.getFeedbackId()))
					frObject.put("status", "green");
				else
					frObject.put("status", "red");
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
	
	@SuppressWarnings("unchecked")
	public JSONObject getResponses(long requestId)
	{
		JSONObject responseObject=new JSONObject();
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		FeedbackRequest fr=dao.getObjectByID(FeedbackRequest.class, requestId);
		int resD[]=new int[27];
		int prof[]=new int[5];
		int crat[]=new int[5];
		for(FeedbackResponse fres:fr.getResponseList())
		{
			String res[]=fres.getResponse().split("##");
			for(int i=0;i<27;i++)
			{
				Integer d=new Integer(res[i]);
				resD[i]+=d;
				if(i==10)
					prof[d-1]++;
				else if(i==24)
					crat[d-1]++;
			}
		}
		Integer tot=fr.getResponseList().size();
		JSONArray areas=new JSONArray();
		for(int i=0;i<27;i++)
		{
			resD[i]/=tot;
			if(resD[i]<2)
				areas.add(i);
		}	
		int category[][]={{0,4},{1,2,3,5,6,26,7},{8,9},{13,25},{12,14,15},{19,20},{16,17,18},{21,22,23},{11}};
		JSONArray categoryRating=new JSONArray();
		for(int i=0;i<category.length;i++)
		{
			int sum=0;
			for(int j=0;j<category[i].length;j++)
				sum+=resD[category[i][j]];
			categoryRating.add(sum/category[i].length);
		}
		JSONArray profRating=new JSONArray();
		JSONArray courseRating=new JSONArray();
		for(int i=0;i<5;i++)
		{
			profRating.add(prof[i]);
			courseRating.add(crat[i]);
		}
		responseObject.put("category", categoryRating);
		responseObject.put("prof", profRating);
		responseObject.put("course", courseRating);
		responseObject.put("areas", areas);
		dao.closeConnection();
		return responseObject;
	}
}
