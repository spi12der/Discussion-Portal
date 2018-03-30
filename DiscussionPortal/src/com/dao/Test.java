package com.dao;

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
}
