package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class DeleteOneToOneBiDirectionalDemo {
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
			int insId = 3;
			
			// getting the instructor
			InstructorDetails tempDetails = session.get(InstructorDetails.class, insId);
			
			System.out.println("Instructor details info: " + tempDetails);
			
			if(tempDetails != null) {
				System.out.println("getting the instructor info");
				Instructor tempInstructor = tempDetails.getInstructor();
				System.out.println("Instructor Details: " + tempInstructor);
			}
			// deleting the instructor details
			System.out.println("Deleting the instructordetails of " + insId);
			session.delete(tempDetails);
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
}
