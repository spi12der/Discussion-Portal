package com.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class DaoUtils
{
	private Session session;
	private Transaction tx; 
	
	public void openConnection() 
	{
		try 
		{
			SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
			session=factory.openSession();
	    } 
		catch (Throwable ex) 
		{ 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	    }
	}
	
	public boolean addEntity(Object obj)
	{
		boolean f=false;
		try 
		{
			tx = session.beginTransaction();
			session.save(obj);
	        tx.commit();
	        f=true;
	    }
		catch (HibernateException e) 
		{
			if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } 
		return f;
	}
	
	public boolean deleteEntity(Object obj)
	{
		boolean f=false;
		try 
		{
			tx = session.beginTransaction();
			session.delete(obj);
	        tx.commit();
	        f=true;
	    }
		catch (HibernateException e) 
		{
			if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } 
		return f;
	}
	
	public boolean updateEntity(Object obj)
	{
		boolean f=false;
		try 
		{
			tx = session.beginTransaction();
			session.update(obj);
	        tx.commit();
	        f=true;
	    }
		catch (HibernateException e) 
		{
			if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } 
		return f;
	}
	
	public <T> T getObjectByID(Class<T> clazz, Serializable key) 
	{
		T dbObject=null;
		try 
		{
			tx = session.beginTransaction();
			dbObject = clazz.cast(session.get(clazz, key));
			tx.commit();
		}
		catch (Exception e) 
		{
			if (tx!=null) tx.rollback();
	        e.printStackTrace();
		}
		return dbObject;
	}
	
	public void closeConnection()
	{
		session.close();
	}
}
