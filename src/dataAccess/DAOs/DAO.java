package dataAccess.DAOs;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import dataAccess.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;


public abstract class DAO {

	protected Object getByID(int id, Class clase){
		Session s=getSession();
		try{
			Object result=s.get(clase.getName(), id);
			commitTransaction(s);
			return result;
		}catch(HibernateException ex){
			s.getTransaction().rollback();
			s.close();
			return null;
		}
	}
	
	protected List getAll(Class clase){
		Session s=getSession();
		try{
			List entities = null;        
	        Query query = s.createQuery("from " + clase.getName());
	        entities = query.list();
	        
	        commitTransaction(s);
	        
	        return entities;
		}catch(HibernateException ex){
			s.getTransaction().rollback();
			s.close();
			return new ArrayList();
		}	
		
	}
	
	protected boolean create(Object o){
		Session s= getSession();
		try{
			s.save(o);
			commitTransaction(s);
			return true;
		}catch(Exception e){
			s.getTransaction().rollback();
			s.close();
			return false;
		}
	}
	
	protected boolean delete(Object o){
		Session s= getSession();
		try{
			s.delete(o);
			commitTransaction(s);
			return true;
		}catch(Exception e){
			s.getTransaction().rollback();
			s.close();
			return false;
		}
	}
	
	protected boolean update(Object o){
		Session s= getSession();
		try{
			s.update(o);
			commitTransaction(s);
			return true;
		}catch(Exception e){
			s.getTransaction().rollback();
			s.close();
			return false;
		}
	}
	
	protected List getByQuery(String query){
		Session s=getSession();
		try{
			List entities = null;        
	        Query q = s.createQuery(query);
	        entities = q.list();
	        
	        commitTransaction(s);
	        
	        return entities;	
		}catch(HibernateException ex){
			s.getTransaction().rollback();
			s.close();
			return new ArrayList();
		}
	}
	
	protected void commitTransaction(Session s){
		s.getTransaction().commit();
		s.close();
	}
	
	protected Session getSession(){
		Session s=(Session) HibernateUtil.getCurrentSession();
		s.beginTransaction();
		return s;
	}
	
}
