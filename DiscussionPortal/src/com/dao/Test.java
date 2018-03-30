package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.Course;
import com.entity.Faculty;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Test 
{
	/*public static void main(String[] args) 
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Faculty f=new Faculty();
		f.setAge(18);
		f.setFacultyCode("123");
		f.setName("Kishore");
		f.setUsername("kk");
		f.setPassword("123");
		Course c1=dao.getObjectByID(Course.class, "123");
		Course c2=dao.getObjectByID(Course.class, "456");
		f.getCourseList().add(c1);
		f.getCourseList().add(c2);
		dao.addEntity(f);
		dao.closeConnection();
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
	}*/
	
	public static void main(String[] args) 
	{
		try {
			HttpResponse<JsonNode> response = Unirest.post("https://textanalysis-text-summarization.p.mashape.com/text-summarizer-text")
					.header("X-Mashape-Key", "ReKEOisfkVmsh15P1mf7dOCpce5ip1mgVI9jsnVuBiGUtppMZf")
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.field("sentnum", 5)
					.field("text", "Automatic summarization is the process of reducing a text document with a computer program in order to create a summary that retains the most important points of the original document. As the problem of information overload has grown, and as the quantity of data has increased, so has interest in automatic summarization. Technologies that can make a coherent summary take into account variables such as length, writing style and syntax. An example of the use of summarization technology is search engines such as Google. Document summarization is another.")
					.asJson();
			response.getBody();
		} 
		catch (UnirestException e) 
		{
			e.printStackTrace();
		}
	}
}
