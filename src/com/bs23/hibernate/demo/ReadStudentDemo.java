package com.bs23.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			Student student = new Student("Mubassar", "Ahmed", "tcbhjgc179@gmail.com");
					
			// start a transaction
			System.out.println("Starting the transaction");
			session.beginTransaction();
					
			// Save the student object
			System.out.println("Saving the object to dB");
			session.save(student);
					
			// getting the session details
			session.getTransaction().commit();
			
			// New Code
			// printing the generated primary key
			System.out.println("Generted id: " + student.getId());
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			// retrive student based on the primary key
			System.out.println("Getting student with id: " + student.getId());
			
			Student student2 = session.get(Student.class, student.getId());
			session.getTransaction().commit();
			
			System.out.println(student2.getFirstName() + student2.getLastName());
			System.out.println(student2.getEmail());
			
			System.out.println("Done!");
					
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
