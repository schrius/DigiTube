package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Order.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public OrderDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
	
    public boolean addOrder(Orders order) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(order);
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
    public Orders searchOrder(Long orderID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Orders order = null;
        
        try {
         transaction = session.beginTransaction();
         order = (Orders)session.get(Orders.class, orderID);
         transaction.commit();
         return order;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return order;
	}
    
    public ObservableList<Orders> listOrder( ){
        session = hibernateFactory.openSession();
        ObservableList<Orders> orderList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List orders = session.createQuery("FROM Customer").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = orders.iterator(); iterator.hasNext();){
        	   Orders order = (Orders) iterator.next(); 
        	   orderList.add(order);
            }
           transaction.commit();
           return orderList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return orderList;
     }
    
    public boolean updateOrder(Orders order){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(order);
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
    
    public void deleteOrder(Long orderID){
        session = hibernateFactory.openSession();
        transaction = null;
        Orders order = null;
        
        try {
        	transaction = session.beginTransaction();
        	order = (Orders)session.get(Orders.class, orderID); 
           if(order!=null)
           session.delete(order); 
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
