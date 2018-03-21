package com.dao;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoUtils 
{
	public SessionFactory getObjFactory()
	{
		SessionFactory factory=null;
		try 
		{
			factory = new Configuration().configure().buildSessionFactory();
	    } 
		catch (Throwable ex) 
		{ 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	    }
		return factory;
	}
	
	public boolean addEntity(Object obj)
	{
		SessionFactory factory=getObjFactory();
		Session session = factory.openSession();
		Transaction tx=null;
		try 
		{
			tx = session.beginTransaction();
			session.save(obj);
	        tx.commit();
	    }
		catch (HibernateException e) 
		{
			if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } 
		finally 
		{
			session.close(); 
	    }
		return false;
	}
}
