package DataManipulater;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import CustomerInfo.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CustomerDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public CustomerDataManipulater() {
        try {
        	hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
    public void addCustomer(Customer customer) {
    	session = hibernateFactory.openSession();
    	transaction = null;
    	try {
    		transaction = session.beginTransaction();
    		session.persist(customer);
    		transaction.commit();
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
	}
    public Customer searchCustomer(Integer customerID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Customer customer = null;
        
        try {
         transaction = session.beginTransaction();
         customer = (Customer)session.get(Customer.class, customerID);
         transaction.commit();
         return customer;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return customer;
	}
    
    public ObservableList<Customer> listCustomer( ){
        session = hibernateFactory.openSession();
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List customers = session.createQuery("FROM Customer").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = customers.iterator(); iterator.hasNext();){
        	   Customer customer = (Customer) iterator.next(); 
        	   customerList.add(customer);
            }
           transaction.commit();
           return customerList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return customerList;
     }
    
    public boolean updateCustomer(Integer CustomerID, Customer customer){
        session = hibernateFactory.openSession();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           if(session.get(Customer.class, CustomerID)!=null)
           session.update(customer);
           else addCustomer(customer);
           transaction.commit();
           return transaction != null;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deleteCustomer(Integer CustomerID){
        session = hibernateFactory.openSession();
        transaction = null;
        Customer customer = null;
        
        try {
        	transaction = session.beginTransaction();
           customer = (Customer)session.get(Customer.class, CustomerID); 
           if(customer!=null)
           session.delete(customer); 
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
