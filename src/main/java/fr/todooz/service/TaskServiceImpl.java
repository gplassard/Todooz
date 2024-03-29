package fr.todooz.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.Interval;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.todooz.domain.Task;

@Service
public class TaskServiceImpl implements TaskService {
	@Inject
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(Task task) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(task);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hqlDelete = "delete Task t where t.id = :id";
		session.createQuery( hqlDelete ).setString("id", id.toString()).executeUpdate();
	}


	@Override
	@Transactional(readOnly = true)
	public List<Task> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Task.class);
		crit.addOrder(Order.desc("date"));
		@SuppressWarnings("unchecked")
		List<Task> tasks = crit.list();
		return tasks;
	}


	@Override
	@Transactional(readOnly = true)
	public List<Task> findByQuery(String query) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Task.class).add(Restrictions.ilike("title", "%"+query+"%"));
		@SuppressWarnings("unchecked")
		List<Task> tasks = crit.list();
		return tasks;
	}

	@Override
	@Transactional(readOnly = true)
	public int count() {
		Session session = sessionFactory.getCurrentSession();
		int size = session.createCriteria(Task.class).list().size();
		return size;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> findByTag(String tag) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Task.class).add(Restrictions.ilike("tags", "%"+tag+"%"));
		@SuppressWarnings("unchecked")
		List<Task> tasks = crit.list();
		return tasks;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> findByInterval(Interval timeInterval) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Task.class).add(Restrictions.between("date", timeInterval.getStart().toDate(), timeInterval.getEnd().toDate()));
		@SuppressWarnings("unchecked")
		List<Task> tasks = crit.list();
		return tasks;
	}

	@Override
	@Transactional(readOnly = true)
	public Task findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Task) session.get(Task.class, id);
	}

}