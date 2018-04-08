package DataManipulater;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Order.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BillDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public BillDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
    public boolean addBill(Bill bill) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(bill);
    		transaction.commit();
    		System.out.print("Bill Registered Success!");
    		return true;
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
		return false;
	}
    public Bill searchBill(Long billID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Bill bill = null;
        
        try {
         transaction = session.beginTransaction();
         bill = (Bill)session.get(Bill.class, billID);
         transaction.commit();
         return bill;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return bill;
	}
    
    public ObservableList<Bill> listBill( ){
        session = hibernateFactory.openSession();
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List bills = session.createQuery("FROM Bill").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = bills.iterator(); iterator.hasNext();){
        	   Bill bill = (Bill) iterator.next(); 
        	   billList.add(bill);
            }
           transaction.commit();
           return billList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return billList;
     }
    
    public boolean updateBill(Bill bill){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(bill);
            transaction.commit();
    		System.out.print("Bill Update Success!");
    		return true;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deleteBill(Long BillID){
        session = hibernateFactory.openSession();
        transaction = null;
        Bill bill = null;
        
        try {
        	transaction = session.beginTransaction();
           bill = (Bill)session.get(Bill.class, BillID); 
           if(bill!=null)
           session.delete(bill); 
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
        return "Bill Session is Close.";
    }
}
