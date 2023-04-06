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


public class CreateCoursesAndStudentDemoManyToMany {
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
			
			System.out.println("Creating the course");
			
			Course course = new Course("Intro to c");
			
			session.save(course);
			System.out.println("getting the student");
			Student student1 = session.get(Student.class, 6);
			Student student2 = session.get(Student.class, 7);
			
			System.out.println("adding course to student");
			
			student1.addCourse(course);
			student2.addCourse(course);
//			
			session.save(student1);
			session.save(student2);
			
			System.out.println(student1.getCourses());
			System.out.println(student2.getCourses());
			System.out.println("Course: " + course);
			
			// commit the session into db
			session.getTransaction().commit();
			
			System.out.println("Assigned Student: "+ course.getStudents());
			
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
}
