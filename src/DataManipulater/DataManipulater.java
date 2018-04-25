package DataManipulater;
/*
 * Create connection with database.
 * Data manipulating functions including adding new data
 * search, update, delete, list
 */
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManipulater {
	static SessionFactory hibernateFactory;
	static Session session;
	static Transaction transaction;
    
    public DataManipulater() {
        try {
        	if(hibernateFactory ==null)
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
	// data generating 
    public static boolean addData(Object object) {
        try {
        	if(hibernateFactory ==null)
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(object);
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object searchData(Long id, Class target) {
        try {
        	if(hibernateFactory ==null)
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
        session = hibernateFactory.openSession();
        transaction = null;
        Object object = null;
        try {
         transaction = session.beginTransaction();
         object = session.get(target, id);
         transaction.commit();
         return object;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return object;
	}
    
    public static boolean updateData(Object object){
        try {
        	if(hibernateFactory ==null)
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(object);
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
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ObservableList<?> ListData(String hql){
        try {
        	if(hibernateFactory ==null)
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
        session = hibernateFactory.openSession();
        ObservableList<Object> list = FXCollections.observableArrayList();
        transaction = null;
        Query<Object> query = null;
        try {
           	transaction = session.beginTransaction();
        	query = session.createQuery(hql);
        	List<Object> resultList = query.list();
        	
          for (Iterator iterator = resultList.iterator(); iterator.hasNext();){
        	  Object item = iterator.next(); 
        	  list.add(item);
            }
           transaction.commit();
           return list;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return list;
     }
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void deleteData(Integer id, Class target){
	        try {
	        	if(hibernateFactory ==null)
	    		hibernateFactory = new Configuration().configure().buildSessionFactory();
	         } catch (Throwable ex) { 
	            System.err.println("Failed to create sessionFactory object." + ex);
	            throw new ExceptionInInitializerError(ex); 
	         }
	        session = hibernateFactory.openSession();
	        transaction = null;
	        Object object = null;
	        
	        try {
	        	transaction = session.beginTransaction();
	        	object = session.get(target, id); 
	           if(object!=null)
	           session.delete(object); 
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
