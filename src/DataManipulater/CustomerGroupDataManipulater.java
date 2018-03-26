package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import CustomerInfo.CustomerGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CustomerGroupDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public CustomerGroupDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
    public boolean addCustomerGroup(CustomerGroup customergroup) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(customergroup);
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
    public CustomerGroup searchCustomerGroup(long customergroupID) {
        session = hibernateFactory.openSession();
        transaction = null;
        CustomerGroup customergroup = null;
        
        try {
         transaction = session.beginTransaction();
         customergroup = (CustomerGroup)session.get(CustomerGroup.class, customergroupID);
         transaction.commit();
         return customergroup;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return customergroup;
	}
    
    public ObservableList<CustomerGroup> listCustomerGroup( ){
        session = hibernateFactory.openSession();
        ObservableList<CustomerGroup> customergroupList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List customergroups = session.createQuery("FROM CustomerGroup").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = customergroups.iterator(); iterator.hasNext();){
        	   CustomerGroup customergroup = (CustomerGroup) iterator.next(); 
        	   customergroupList.add(customergroup);
            }
           transaction.commit();
           return customergroupList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return customergroupList;
     }
    
    public boolean updateCustomerGroup(CustomerGroup customergroup){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(customergroup);
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
    
    public void deleteCustomerGroup(Long CustomerGroupID){
        session = hibernateFactory.openSession();
        transaction = null;
        CustomerGroup customergroup = null;
        
        try {
        	transaction = session.beginTransaction();
           customergroup = (CustomerGroup)session.get(CustomerGroup.class, CustomerGroupID); 
           if(customergroup!=null)
           session.delete(customergroup); 
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

