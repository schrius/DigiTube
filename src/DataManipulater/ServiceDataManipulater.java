package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Order.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public ServiceDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
    public boolean addService(Service service) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(service);
    		transaction.commit();
    		System.out.print("Service Save!");
    		return true;
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
		return false;
	}
    public Service searchService(Long serviceID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Service service = null;
        
        try {
         transaction = session.beginTransaction();
         service = (Service)session.get(Service.class, serviceID);
         transaction.commit();
         return service;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return service;
	}
    
    public ObservableList<Service> listService( ){
        session = hibernateFactory.openSession();
        ObservableList<Service> serviceList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List services = session.createQuery("FROM Service").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = services.iterator(); iterator.hasNext();){
        	   Service service = (Service) iterator.next(); 
        	   serviceList.add(service);
            }
           transaction.commit();
           return serviceList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return serviceList;
     }
    
    public boolean updateService(Service service){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(service);
            transaction.commit();
    		System.out.print("Service Update Success!");
    		return true;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deleteService(Long ServiceID){
        session = hibernateFactory.openSession();
        transaction = null;
        Service service = null;
        
        try {
        	transaction = session.beginTransaction();
           service = (Service)session.get(Service.class, ServiceID); 
           if(service!=null)
           session.delete(service); 
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
        return "Service Session is Close.";
    }
}
