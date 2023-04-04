package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Instructor;
import com.bs23.hibernate.demo.entity.InstructorDetails;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class OneToOneDemo {
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
			// creating the Instructor details Object and Instructor Object
			Instructor instructor = new 
						Instructor("Jhon", "Doe", "jhondoe@gmail.com");
			
			InstructorDetails inmsInstructorDetails = new 
						InstructorDetails("www.youtube.com/jhon-doe", "teaching");
			
			// associate the two object
			instructor.setInstructorDetail(inmsInstructorDetails);
			
			// session transaction started
			session.beginTransaction();
			
			// save the instructor
			// since cascade type is all then it will save the associate transaction
			System.out.println("Saving the details" + instructor);
			session.save(instructor);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// deleting one object
			int insId = 1;
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			System.out.println("getting the instructor");
			Instructor tempInstructor = session.get(Instructor.class, insId);
			
			if(tempInstructor != null) {
				System.out.println("Deleting the instructor");
				session.delete(tempInstructor);
			}
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
