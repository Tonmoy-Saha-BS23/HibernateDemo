package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Course;
import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class CreateCoursesDemoManyToOne {
	public static void main(String[] args) {
		// Create a session Factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(InstructorDetails.class)
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		//create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start the transaction
			session.beginTransaction();
			
			int theId = 5;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			// commit the transaction
			
			Course course1 = new Course("Into to Java");
			Course course2 = new Course("Intro to Javascript");
			
			course1.setInstructor(tempInstructor);
			course2.setInstructor(tempInstructor);
			
			session.save(course1);
			session.save(course2);
			
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
