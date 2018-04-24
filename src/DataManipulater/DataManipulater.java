package DataManipulater;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import CustomerInfo.Customer;
import CustomerInfo.CustomerGroup;
import Employee.Employee;
import Employee.EmployeeWorkingTime;
import Order.Bill;
import Order.Invoice;
import Order.Orders;
import Order.Plan;
import Order.Product;
import Order.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManipulater {
	static SessionFactory hibernateFactory = new Configuration().configure().buildSessionFactory();
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
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ObservableList<Plan> planList(String hql){
        session = hibernateFactory.openSession();
        ObservableList<Plan> planList = FXCollections.observableArrayList();
        transaction = null;
        Query<Plan> query = null;
        try {
           	transaction = session.beginTransaction();
        	query = session.createQuery(hql);
        	List<Plan> resultList = query.list();
        	
          for (Iterator iterator = resultList.iterator(); iterator.hasNext();){
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
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObservableList<Service> serviceWaitingList(String hql){
        session = hibernateFactory.openSession();
        ObservableList<Service> serviceList = FXCollections.observableArrayList();
        transaction = null;
        Query<Service> query = null;
        try {
           	transaction = session.beginTransaction();
        	query = session.createQuery(hql);
        	List<Service> resultList = query.list();
        	
          for (Iterator iterator = resultList.iterator(); iterator.hasNext();){
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
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObservableList<Customer> customerList(String hql){
        session = hibernateFactory.openSession();
        ObservableList<Customer> customerlist = FXCollections.observableArrayList();
        transaction = null;
        Query<Customer> query = null;
        try {
           	transaction = session.beginTransaction();
        	query = session.createQuery(hql);
        	List<Customer> resultList = query.list();
        	
          for (Iterator iterator = resultList.iterator(); iterator.hasNext();){
        	  Customer customer = (Customer) iterator.next(); 
        	   customerlist.add(customer);
            }
           transaction.commit();
           return customerlist;
        } catch (HibernateException e) {
           if (transaction!=null) transaction.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        return customerlist;
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
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObservableList<Bill> BillwaitingList(String hql){
        session = hibernateFactory.openSession();
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        transaction = null;
        Query<Bill> query = null;
        try {
           	transaction = session.beginTransaction();
        	query = session.createQuery(hql);
        	List<Bill> resultList = query.list();
        	
          for (Iterator iterator = resultList.iterator(); iterator.hasNext();){
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
    
    public boolean addCustomerGroup(CustomerGroup customergroup) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(customergroup);
    		transaction.commit();
    		System.out.print("New Group Save Success!");
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
    		System.out.print("Group Update Success!");
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
    
    // time sheet generate
    public boolean addWorkingTime(EmployeeWorkingTime workTime) {
    	session = hibernateFactory.openSession();
    	transaction = null;

    	try {
    		transaction = session.beginTransaction();
    		session.persist(workTime);
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
    public EmployeeWorkingTime searchWorkingTime(Integer timeID) {
        session = hibernateFactory.openSession();
        transaction = null;
        EmployeeWorkingTime employee = null;
        
        try {
         transaction = session.beginTransaction();
         employee = (EmployeeWorkingTime)session.get(EmployeeWorkingTime.class, timeID);
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
    
    public ObservableList<EmployeeWorkingTime> listWorkingTime( ){
        session = hibernateFactory.openSession();
        ObservableList<EmployeeWorkingTime> employees = FXCollections.observableArrayList();
        transaction = null;
        
        try {
        	transaction = session.beginTransaction();
           @SuppressWarnings("rawtypes")
		List employeeList = session.createQuery("FROM EmployeeWorkingTime").list(); 
           for (@SuppressWarnings("rawtypes")
		Iterator iterator = employeeList.iterator(); iterator.hasNext();){
        	   EmployeeWorkingTime employee = (EmployeeWorkingTime) iterator.next(); 
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
    
    public boolean updateWorkingTime(EmployeeWorkingTime employee){
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
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObservableList<EmployeeWorkingTime> WorkingTimeList(String hql){
        session = hibernateFactory.openSession();
        ObservableList<EmployeeWorkingTime> list = FXCollections.observableArrayList();
        transaction = null;
        Query<EmployeeWorkingTime> query = null;
        try {
           	transaction = session.beginTransaction();
        	query = session.createQuery(hql);
        	List<EmployeeWorkingTime> resultList = query.list();
        	
          for (Iterator iterator = resultList.iterator(); iterator.hasNext();){
        	  EmployeeWorkingTime item = (EmployeeWorkingTime) iterator.next(); 
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
    
    public String closeSession() {
        session.close();
        return "Session is Close.";
    }
}
