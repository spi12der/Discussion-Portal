package com.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class DaoUtils 
{
	public SessionFactory getObjFactory()
	{
		SessionFactory factory=null;
		try 
		{
			factory= new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
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
		boolean f=false;
		Session session = getObjFactory().openSession();
		Transaction tx=null;
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
		finally 
		{
			session.close(); 
	    }
		return f;
	}
	
	public boolean deleteEntity(Object obj)
	{
		boolean f=false;
		Session session = getObjFactory().openSession();
		Transaction tx=null;
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
		finally 
		{
			session.close(); 
	    }
		return f;
	}
	
	public boolean updateEntity(Object obj)
	{
		boolean f=false;
		Session session = getObjFactory().openSession();
		Transaction tx=null;
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
		finally 
		{
			session.close(); 
	    }
		return f;
	}
	
	public <T> T getObjectByID(Class<T> clazz, Serializable key) 
	{
		Session session = getObjFactory().openSession();
		T dbObject;
		try 
		{
			session.beginTransaction();
			dbObject = clazz.cast(session.get(clazz, key));
		} 
		finally 
		{
			session.getTransaction().commit();
		}
		return dbObject;
	}
}
