package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Employee.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeDataManipulater {
		SessionFactory hibernateFactory;
		Session session;
	    Transaction transaction;
	    
	    public EmployeeDataManipulater() {
	        try {
	    		hibernateFactory = new Configuration().configure().buildSessionFactory();
	         } catch (Throwable ex) { 
	            System.err.println("Failed to create sessionFactory object." + ex);
	            throw new ExceptionInInitializerError(ex); 
	         }
	    }
		
	    public boolean addEmployee(Employee employee) {
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
	    public Employee searchEmployee(Integer employeeID) {
	        session = hibernateFactory.openSession();
	        transaction = null;
	        Employee employee = null;
	        
	        try {
	         transaction = session.beginTransaction();
	         employee = (Employee)session.get(Employee.class, employeeID);
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
	    
	    public ObservableList<Employee> listEmployee( ){
	        session = hibernateFactory.openSession();
	        ObservableList<Employee> employees = FXCollections.observableArrayList();
	        transaction = null;
	        
	        try {
	        	transaction = session.beginTransaction();
	           @SuppressWarnings("rawtypes")
			List employeeList = session.createQuery("FROM Employee").list(); 
	           for (@SuppressWarnings("rawtypes")
			Iterator iterator = employeeList.iterator(); iterator.hasNext();){
	        	   Employee employee = (Employee) iterator.next(); 
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
	    
	    public boolean updateEmployee(Employee employee){
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
	    
	    public void deleteEmployee(Integer employeeID){
	        session = hibernateFactory.openSession();
	        transaction = null;
	        Employee employee = null;
	        
	        try {
	        	transaction = session.beginTransaction();
	        	employee = (Employee)session.get(Employee.class, employeeID); 
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
	        return "Session is Close.";
	    }
}