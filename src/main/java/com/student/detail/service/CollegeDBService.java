package com.student.detail.service;

import java.util.List; // Import for List
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.student.detail.model.College;
import com.student.detail.exception.CollegeNotFoundException; // Your custom exception
import com.student.detail.util.HibernateUtil; // HibernateUtil for session management
import com.student.detail.service.CollegeService; // The service interface you're implementing

@Service
public class CollegeDBService implements CollegeService {

	private static final Logger logger = LoggerFactory.getLogger(CollegeDBService.class);

	// Method to add or update college
	public College addOrUpdateCollege(College college) {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(college); // Handles both insert and update
			transaction.commit();
			logger.info("College {} saved/updated successfully.", college.getCollegeName());
			return college;
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			logger.error("Error saving/updating college: {}", e.getMessage());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to delete a college by ID
	public boolean deleteCollege(int collegeId) throws CollegeNotFoundException {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			College college = session.get(College.class, collegeId);
			if (college == null) {
				logger.error("College not found with ID: {}", collegeId);
				throw new CollegeNotFoundException("College not found with ID: " + collegeId);
			}
			session.delete(college);
			transaction.commit();
			logger.info("College with ID {} deleted successfully.", collegeId);
			return true;
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			logger.error("Error deleting college: {}", e.getMessage());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to find a college by ID
	public College findCollegeById(int id) throws CollegeNotFoundException {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		try {
			College college = session.get(College.class, id);
			if (college == null) {
				logger.error("College not found with ID: {}", id);
				throw new CollegeNotFoundException("College not found with ID: " + id);
			}
			return college;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to find a college by name
	public College findCollegeByName(String name) throws CollegeNotFoundException {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		try {
			Query<College> query = session.createQuery("from College where collegeName = :name", College.class);
			query.setParameter("name", name);
			College college = query.uniqueResult();
			if (college == null) {
				logger.error("College not found with name: {}", name);
				throw new CollegeNotFoundException("College not found with name: " + name);
			}
			return college;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to get all colleges
	public List<College> getAllColleges() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
			if (session == null) {
				logger.error("Session could not be obtained.");
				return null;
			}

			transaction = session.beginTransaction();
			Query<College> query = session.createQuery("from College", College.class);
			List<College> colleges = query.list();
			transaction.commit();

			if (colleges.isEmpty()) {
				logger.info("No colleges found.");
			} else {
				logger.info("Found {} colleges.", colleges.size());
			}
			return colleges;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Error while fetching colleges", e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to find colleges by city
	public List<College> findCollegesByCity(String city) {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		try {
			Query<College> query = session.createQuery("from College where city = :city", College.class);
			query.setParameter("city", city);
			return query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to get the count of colleges
	public int getCountOfColleges() {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		try {
			Query<Long> query = session.createQuery("select count(*) from College", Long.class);
			return query.uniqueResult().intValue();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to add a college
	@Override
	public College addCollege(College college) {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(college); // Persist the new college
			transaction.commit();
			logger.info("College {} added successfully.", college.getCollegeName());
			return college;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Error adding college: {}", e.getMessage());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to update a college
	@Override
	public College updateCollege(College college) throws CollegeNotFoundException {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			College existingCollege = session.get(College.class, college.getCollegeId());
			if (existingCollege == null) {
				logger.error("College not found with ID: {}", college.getCollegeId());
				throw new CollegeNotFoundException("College not found with ID: " + college.getCollegeId());
			}
			existingCollege.setCollegeName(college.getCollegeName());
			existingCollege.setAddress(college.getAddress());
			existingCollege.setCity(college.getCity());
			existingCollege.setState(college.getState());
			existingCollege.setZipcode(college.getZipcode());
			existingCollege.setPhoneNumber(college.getPhoneNumber());
			session.update(existingCollege);
			transaction.commit();
			logger.info("College {} updated successfully.", college.getCollegeName());
			return existingCollege;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Error updating college: {}", e.getMessage());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	// Method to delete a college by College object
	@Override
	public boolean deleteCollege(College college) throws CollegeNotFoundException {
		Session session = HibernateUtil.getSessionFactory().openSession(); // Get session from SessionFactory
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			College existingCollege = session.get(College.class, college.getCollegeId());
			if (existingCollege == null) {
				logger.error("College not found with ID: {}", college.getCollegeId());
				throw new CollegeNotFoundException("College not found with ID: " + college.getCollegeId());
			}
			session.delete(existingCollege);
			transaction.commit();
			logger.info("College {} deleted successfully.", college.getCollegeName());
			return true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Error deleting college: {}", e.getMessage());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
}
