package DataManipulater;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import CustomerInfo.Customer;
import Employee.Employee;
import Order.Bill;
import Order.Invoice;
import Order.Orders;
import Order.Plan;
import Order.Product;
import Order.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManipulater {
	SessionFactory hibernateFactory;
	Session session;
    Transaction transaction;
    
    public DataManipulater() {
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
    
    public boolean addOrder(Orders order) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(order);
    		transaction.commit();
    		System.out.print("Order Save!");
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
    		System.out.print("Order Update Success!");
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
    
    public boolean addCustomer(Customer customer) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(customer);
    		transaction.commit();
    		System.out.print("New Cusotmer Save Success!");
    		return true;
    	}catch (HibernateException e) {
    		if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
		}finally {
			session.close();
		}
		return false;
	}
    public Customer searchCustomer(Long customerID) {
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
    
    public boolean updateCustomer(Customer customer){
        session = hibernateFactory.openSession();
        transaction = null;
        try {
        	transaction = session.beginTransaction();
                session.update(customer);
            transaction.commit();
    		System.out.print("Customer Update Success!");
    		return true;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return transaction != null;
     }
    
    public void deleteCustomer(Long CustomerID){
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
        return "Session is Close.";
    }
}
