package com.bs23.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// Create a session Factory
		SessionFactory sessionFactory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Student.class)
												.buildSessionFactory();
		//create session
		Session session = sessionFactory.getCurrentSession();
				
		try {
			System.out.println("Creating a new student object");
			Student student1 = new Student("abc", "Saha", "abc179@gmail.com");
			Student student2 = new Student("def", "Saha", "def179@gmail.com");
			Student student3 = new Student("ghi", "Saha", "ghi179@gmail.com");
			// start a transaction
			System.out.println("Strating the transaction");
			session.beginTransaction();
					
			// Save the student object
			System.out.println("Saving the object to dB");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			// getting the session details
			session.getTransaction().commit();
			System.out.println("Done!");
					
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
