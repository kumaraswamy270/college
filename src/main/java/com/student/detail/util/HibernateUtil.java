package com.student.detail.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {
			// Load configuration and build the SessionFactory
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Log the exception to provide debugging information
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// Provide a way to get the SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Shutdown Hibernate resources
	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
