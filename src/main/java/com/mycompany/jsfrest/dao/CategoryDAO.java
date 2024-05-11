package com.mycompany.jsfrest.dao;

/**
 *
 * @author cristiane
 */
import com.mycompany.jsfrest.entity.Category;
import com.mycompany.jsfrest.utils.HibernateUtil;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class CategoryDAO {
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public List<Category> selectAll() {
		List<Category> categories;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			categories = session.createQuery("from Category", Category.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			categories = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return categories;
	}
}
