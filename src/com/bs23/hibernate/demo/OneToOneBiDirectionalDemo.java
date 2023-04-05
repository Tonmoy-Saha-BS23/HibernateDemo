package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class OneToOneBiDirectionalDemo {
	public static void main(String[] args) {
		// Create a session Factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(InstructorDetails.class)
										.addAnnotatedClass(Instructor.class)
										.buildSessionFactory();
		//create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start the transaction
			session.beginTransaction();
			int insId = 5;
			
			// getting the instructor
			InstructorDetails tempDetails = session.get(InstructorDetails.class, insId);
			
			System.out.println("Instructor details info: " + tempDetails);
			
			if(tempDetails != null) {
				System.out.println("getting the instructor info");
				Instructor tempInstructor = tempDetails.getInstructor();
				System.out.println("Instructor Details: " + tempInstructor);
			}
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
