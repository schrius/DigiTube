package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Employee.EmployeeWorkingTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeWorkingDataManipulater {
		SessionFactory hibernateFactory;
		Session session;
	    Transaction transaction;
	    
	    public EmployeeWorkingDataManipulater() {
	        try {
	    		hibernateFactory = new Configuration().configure().buildSessionFactory();
	         } catch (Throwable ex) { 
	            System.err.println("Failed to create sessionFactory object." + ex);
	            throw new ExceptionInInitializerError(ex); 
	         }
	    }
		
	    public boolean addEmployeeWorkingTimeWorkingTime(EmployeeWorkingTime employee) {
	    	session = hibernateFactory.openSession();
	    	transaction = null;

	    	try {
	    		transaction = session.beginTransaction();
	    		session.persist(employee);
	    		transaction.commit();
	    		System.out.print("Registered Success!");
	    		return true;
	    	}catch (HibernateException e) {
	    		if (transaction!=null) transaction.rollback();
	            e.printStackTrace(); 
			}finally {
				session.close();
			}
			return false;
		}
	    public EmployeeWorkingTime searchEmployeeWorkingTime(Integer employeeID) {
	        session = hibernateFactory.openSession();
	        transaction = null;
	        EmployeeWorkingTime employee = null;
	        
	        try {
	         transaction = session.beginTransaction();
	         employee = (EmployeeWorkingTime)session.get(EmployeeWorkingTime.class, employeeID);
	         transaction.commit();
	         return employee;
	        } catch (HibernateException e) {
	           if (transaction!=null) transaction.rollback();
	           e.printStackTrace(); 
	        } finally {
	           session.close(); 
	        }
			return employee;
		}
	    
	    public ObservableList<EmployeeWorkingTime> listEmployeeWorkingTime( ){
	        session = hibernateFactory.openSession();
	        ObservableList<EmployeeWorkingTime> employees = FXCollections.observableArrayList();
	        transaction = null;
	        
	        try {
	        	transaction = session.beginTransaction();
	           @SuppressWarnings("rawtypes")
			List employeeList = session.createQuery("FROM EmployeeWorkingTime").list(); 
	           for (@SuppressWarnings("rawtypes")
			Iterator iterator = employeeList.iterator(); iterator.hasNext();){
	        	   EmployeeWorkingTime employee = (EmployeeWorkingTime) iterator.next(); 
	        	   employees.add(employee);
	            }
	           transaction.commit();
	           return employees;
	        } catch (HibernateException e) {
	           if (transaction!=null) transaction.rollback();
	           e.printStackTrace(); 
	        } finally {
	           session.close(); 
	        }
	        return employees;
	     }
	    
	    public boolean updateEmployeeWorkingTime(EmployeeWorkingTime employee){
	        session = hibernateFactory.openSession();
	        transaction = null;
	        try {
	        	transaction = session.beginTransaction();
	                session.update(employee);
	            transaction.commit();
	    		System.out.print("Update Success!");
	    		return true;
	        } catch (HibernateException e) {
	           if (transaction!=null) transaction.rollback();
	           e.printStackTrace(); 
	        } finally {
	           session.close(); 
	        }
	        return transaction != null;
	     }
	    
	    public void deleteEmployeeWorkingTime(Long timeID){
	        session = hibernateFactory.openSession();
	        transaction = null;
	        EmployeeWorkingTime employee = null;
	        
	        try {
	        	transaction = session.beginTransaction();
	        	employee = (EmployeeWorkingTime)session.get(EmployeeWorkingTime.class, timeID); 
	           if(employee!=null)
	           session.delete(employee); 
	           else return;
	           transaction.commit();
	        } catch (HibernateException e) {
	           if (transaction!=null) transaction.rollback();
	           e.printStackTrace(); 
	        } finally {
	           session.close(); 
	        }
	     }
	    
	    public String closeSession() {
	        session.close();
	        return "Employee Working Data Session is Close.";
	    }
}