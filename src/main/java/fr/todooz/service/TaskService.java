package fr.todooz.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.todooz.domain.Task;

public class TaskService {
	private SessionFactory sessionFactory;
	
	public void save(Task task) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(task);
		tx.commit();
		session.close();
	}

	public void delete(Long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hqlDelete = "delete Task t where t.id = :id";
		session.createQuery( hqlDelete ).setString("id", id.toString()).executeUpdate();
		tx.commit();
		session.close();
	}

	public List<Task> findAll() {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Task.class);
		@SuppressWarnings("unchecked")
		List<Task> tasks = crit.list();
		session.close();
		return tasks;
	}

	public List<Task> findByQuery(String query) {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Task.class).add(Restrictions.ilike("title", "%"+query+"%"));
		@SuppressWarnings("unchecked")
		List<Task> tasks = crit.list();
		session.close();
		return tasks;
	}

	public int count() {
		Session session = sessionFactory.openSession();
		int size = session.createCriteria(Task.class).list().size();
		session.close();
		return size;
	}
}