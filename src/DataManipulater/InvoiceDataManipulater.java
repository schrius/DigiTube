package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Order.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public InvoiceDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
	
    public boolean addInvoice(Invoice invoice) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(invoice);
    		session.flush();
    		transaction.commit();
    		System.out.print("Invoice Save!");
    		return true;
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
		return false;
	}
    public Invoice searchInvoice(Long invoiceID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Invoice invoice = null;
        
        try {
         transaction = session.beginTransaction();
         invoice = (Invoice)session.get(Invoice.class, invoiceID);
         transaction.commit();
         return invoice;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return invoice;
	}
    
    public ObservableList<Invoice> listInvoice( ){
        session = hibernateFactory.openSession();
        ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List invoices = session.createQuery("FROM Customer").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = invoices.iterator(); iterator.hasNext();){
        	   Invoice invoice = (Invoice) iterator.next(); 
        	   invoiceList.add(invoice);
            }
           transaction.commit();
           return invoiceList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return invoiceList;
     }
    
    public boolean updateInvoice(Invoice invoice){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(invoice);
            transaction.commit();
    		System.out.print("Invoice Update Success!");
    		return true;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deleteInvoice(Long invoiceID){
        session = hibernateFactory.openSession();
        transaction = null;
        Invoice invoice = null;
        
        try {
        	transaction = session.beginTransaction();
        	invoice = (Invoice)session.get(Invoice.class, invoiceID); 
           if(invoice!=null)
           session.delete(invoice); 
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
        return "Invoice Session is Close.";
    }
}

