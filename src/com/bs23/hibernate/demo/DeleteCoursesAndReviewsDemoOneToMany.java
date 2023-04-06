package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Course;
import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;
import com.bs23.hibernate.demo.entity.Review;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class DeleteCoursesAndReviewsDemoOneToMany {
	public static void main(String[] args) {
		// Create a session Factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(InstructorDetails.class)
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();
		//create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start the transaction
			session.beginTransaction();
			
			System.out.println("Getting the course");
			int courseId = 8;
			Course tempCourse = session.get(Course.class, courseId);
			
			System.out.println("Deleting the course");
			
			session.delete(tempCourse);
			
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
