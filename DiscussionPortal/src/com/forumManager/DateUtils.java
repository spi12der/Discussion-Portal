package com.forumManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils 
{
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
