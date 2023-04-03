package com.bs23.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
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
			int studentId = 8;
			// approach 1
			System.out.println("Deleting the student who has studentId: "+studentId);
			Student student = session.get(Student.class, studentId);
			
			System.out.println(student);
			
			session.delete(student);
			
			session.getTransaction().commit();
			
			// approach 2
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Deleteing student with id=10");
			session.createQuery("delete from Student where id=10").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
