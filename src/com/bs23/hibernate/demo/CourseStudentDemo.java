package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class CourseStudentDemo {
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
			Student student1 = new Student("Tonmoy", "Saha", "tonmoysaha179@gmail.com");
			
			// start a transaction
			System.out.println("Strating the transaction");
			session.beginTransaction();
			
			// Save the student object
			System.out.println("Saving the object to dB");
			session.save(student1);
			
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
