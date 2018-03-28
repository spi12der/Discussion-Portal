package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.entity.Course;
import com.entity.Post;
import com.entity.User;

public class Test 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		JSONObject ob=new JSONObject();
		ob.put("text", "sdfsd sfsddfsf");
		ob.put("id", "s123");
		JSONArray a=new JSONArray();
		a.add("Excellent");
		a.add("Poor");
		ob.put("options", a);
		System.out.println(ob.toJSONString());
	}
	
	public static String getFormat(Date date)
	{
		String time="";
		try
		{
			SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
			SimpleDateFormat day = new SimpleDateFormat("MMM dd, yyyy");
	        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
	        Date _24HourDt = _24HourSDF.parse(_24HourSDF.format(date));
	        time=day.format(date);
	        time=time+" - "+_12HourSDF.format(_24HourDt);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return time;
	}
}
