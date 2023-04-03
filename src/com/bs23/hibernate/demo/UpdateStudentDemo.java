package com.bs23.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		// creating the session
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// starting the session transaction
			session.beginTransaction();
			int studentId = 7;
			System.out.println("Getting the student who has studentId: "+studentId);
			Student student = session.get(Student.class, studentId);
			
			System.out.println("Changing the student firstName");
			
			student.setFirstName("Esha");
			
			session.getTransaction().commit();
			
			// update all the student id where id is greater than 7
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='foo@gmail.com' where id > 8")
					.executeUpdate();			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
