package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Course;
import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class DeleteCoursesDemo {
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
			
			int theId = 1;
			Course course = session.get(Course.class, theId);
			
			// deleting the course
			System.out.println("Deleting the courses");
			
			session.delete(course);
			
			// commit the session
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}catch (Exception e) {
			System.out.println("Course doesn't exists");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
}
