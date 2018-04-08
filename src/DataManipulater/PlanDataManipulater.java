package DataManipulater;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Order.Plan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlanDataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public PlanDataManipulater() {
        try {
    		hibernateFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    }
    public boolean addPlan(Plan plan) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(plan);
    		transaction.commit();
    		System.out.print("Plan Save!");
    		return true;
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
		return false;
	}
    public Plan searchPlan(Long planID) {
        session = hibernateFactory.openSession();
        transaction = null;
        Plan plan = null;
        
        try {
         transaction = session.beginTransaction();
         plan = (Plan)session.get(Plan.class, planID);
         transaction.commit();
         return plan;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
		return plan;
	}
    
    public ObservableList<Plan> listPlan( ){
        session = hibernateFactory.openSession();
        ObservableList<Plan> planList = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List plans = session.createQuery("FROM Plan").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = plans.iterator(); iterator.hasNext();){
        	   Plan plan = (Plan) iterator.next(); 
        	   planList.add(plan);
            }
           transaction.commit();
           return planList;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return planList;
     }
    
    public boolean updatePlan(Plan plan){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(plan);
            transaction.commit();
    		System.out.print("Plan Update Success!");
    		return true;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deletePlan(Long PlanID){
        session = hibernateFactory.openSession();
        transaction = null;
        Plan plan = null;
        
        try {
        	transaction = session.beginTransaction();
           plan = (Plan)session.get(Plan.class, PlanID); 
           if(plan!=null)
           session.delete(plan); 
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
        return "Plan Session is Close.";
    }
}
