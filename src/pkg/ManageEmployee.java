package pkg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create SessionFactory object : " + ex);
			throw new ExceptionInInitializerError(ex);
		}
		ManageEmployee ME = new ManageEmployee();
		
		//Let us have a set of certificates for the first employee
		HashMap<String, Certificate> set1 = new HashMap<String, Certificate>();
		set1.put("ComputerScience", new Certificate("MCA"));
		set1.put("BusinessManagement", new Certificate("MBA"));
		set1.put("ProjectManagement", new Certificate("PMP"));
		
		/* Add employee records in the database*/
		Integer empID1 = ME.addEmployee("Jonasse", "Juliana", 10000, set1);
		
		// List down all the employees
		ME.listEmployees();
		
		//Update empployee's records
		ME.updateEmployee(empID1, 5000);
		
		//List down new list of the employees
		ME.listEmployees();
		
		sessionFactory.close();
	}
	
	/**
	 * Method to CREATE an employee in the database
	 */
	public Integer addEmployee(String fname, String lname, int salary, HashMap<String, Certificate> cert){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Integer employeeID = null;
		try {
			transaction = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employee.setCertificates(cert);
			employeeID = (Integer) session.save(employee);
			transaction.commit();
		} catch (HibernateException he) {
			if(transaction != null)
				transaction.rollback();
			he.printStackTrace();
		} finally{
			session.close();
		}
		return employeeID;
	}
	
	/**
	 * Method to READ all the employees
	 */
	@SuppressWarnings("unchecked")
	public void listEmployees(){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			List<Employee> employees = session.createQuery("FROM Employee").list();
			for(Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();){
				Employee employee = (Employee) iterator.next();
				System.out.println("-----------------------------------------------");
				System.out.print("ID = " + employee.getId());
				System.out.print(", Firstname = " + employee.getFirstName());
				System.out.print(", Lastname = " + employee.getLastName());
				System.out.println(", Salary = " + employee.getSalary());
				Map<String, Certificate> cert = employee.getCertificates();
				System.out.println("--- Certificate : " + (((Certificate)cert.get("ComputerScience")).getName()));
				System.out.println("--- Certificate : " + (((Certificate)cert.get("BusinessManagement")).getName()));
				System.out.println("--- Certificate : " + (((Certificate)cert.get("ProjectManagement")).getName()));
			}
			transaction.commit();
		} catch(HibernateException he){
			if(transaction != null)
				transaction.rollback();
			he.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	/**
	 * Method UPDATE salary for an employee
	 */
	private void updateEmployee(Integer empID, int salary) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, empID);
			employee.setSalary(salary);
			session.update(employee);
			transaction.commit();
		} catch (HibernateException he) {
			if(transaction != null)
				transaction.rollback();
			he.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	/**
	 * DELETE an employee from the employee
	 */
	public void deleteEmployee(Integer empID){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, empID);
			session.delete(employee);
			transaction.commit();
		} catch(HibernateException he){
			if(transaction != null)
				transaction.rollback();
			he.printStackTrace();
		} finally{
			session.close();
		}
	}

}
