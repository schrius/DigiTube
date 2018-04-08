package DataManipulater;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Order.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public ProductDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
	
    public boolean addProduct(Product product) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(product);
    		transaction.commit();
    		System.out.print("Product Save!");
    		return true;
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
		return false;
	}
    public Product searchProduct(Integer productID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Product product = null;
        
        try {
         transaction = session.beginTransaction();
         product = (Product)session.get(Product.class, productID);
         transaction.commit();
         return product;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return product;
	}
    
    public ObservableList<Product> listProduct( ){
        session = hibernateFactory.openSession();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List products = session.createQuery("FROM Customer").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = products.iterator(); iterator.hasNext();){
        	   Product product = (Product) iterator.next(); 
        	   productList.add(product);
            }
           transaction.commit();
           return productList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return productList;
     }
    
    public boolean updateProduct(Product product){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(product);
            transaction.commit();
    		System.out.print("Product Update Success!");
    		return true;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deleteProduct(Integer productID){
        session = hibernateFactory.openSession();
        transaction = null;
        Product product = null;
        
        try {
        	transaction = session.beginTransaction();
        	product = (Product)session.get(Product.class, productID); 
           if(product!=null)
           session.delete(product); 
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
        return "Product Session is Close.";
    }
}
