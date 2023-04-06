package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Course;
import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;
import com.bs23.hibernate.demo.entity.Review;
import com.bs23.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SourceType;
import org.hibernate.cfg.*;


public class AddMoreCoursesToEsha {
	public static void main(String[] args) {
		// Create a session Factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(InstructorDetails.class)
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		//create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start the transaction
			session.beginTransaction();
			int studentId = 7;
			
			Student teStudent = session.get(Student.class, studentId);
			
			System.out.print("Gettinmg the courses or Esha: ");
			System.out.println(teStudent.getCourses());
			
			Course course1 = new Course("Rubix Cube");
			
			Course course2 = new Course("IELTS prep");
			
			teStudent.addCourse(course1);
			teStudent.addCourse(course2);
			
			session.save(course1);
			session.save(course2);
			
			System.out.println("New courses: " +teStudent.getCourses());
			
			// commit the session into db
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
