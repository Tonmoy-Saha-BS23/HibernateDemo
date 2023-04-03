package com.bs23.hibernate.demo;

import org.hibernate.cfg.Configuration;

import com.bs23.hibernate.demo.entity.Student;

import java.util.List;

import javax.persistence.criteria.From;

import org.dom4j.io.SAXContentHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;


public class QueryStudentDemo {
	public static void main(String[] args) {
		// Create a session Factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		//create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// query starts
			session.beginTransaction();
			
			//query students
			List<Student> thestudetList = session.createQuery("from Student").list();
			
			display(thestudetList);
			
			// query: show all the student who has saha as a last name
			thestudetList = session.createQuery("from Student s where s.lastName='saha'").list();
			
			System.out.println("\nStudent who has last name as saha\n");
			
			display(thestudetList);
			
			thestudetList = session.createQuery("from Student s where"
					+ " s.lastName='saha' OR s.firstName='tonmoy'").list();
			
			System.out.println("\n student ho last name as shaa or first name as tonmoy\n");
			
			display(thestudetList);
			
			// students whose first name is Tonmoy and last name is saha
			thestudetList = session.createQuery("from Student s where "
					+ "s.firstName='Tonmoy' AND s.lastName='saha'").list();
			
			System.out.println("\nStudent whose first name is Tonmoy and last name is saha\n");
			
			display(thestudetList);
			
			// student whose email ends with c179@gmail.com
			
			thestudetList = session.createQuery("from Student s where "
					+ "s.email like '%c179@gmail.com'").list();
			
			System.out.println("\n Students whose email ends with c179@gmail.com\n");
			
			display(thestudetList);
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	private static void display(List<Student> thestudetList) {
		for(Student s:thestudetList) {
			System.out.println(s);
		}
	}
}
