package net.sigfap.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;

public class HibernateDAO<E, PK extends Serializable> implements
		IGenericDAO<E, PK> {

	private Class<E> persistentClass;

	@Inject
	private Logger log;

	@Inject
	private Session session;

	@SuppressWarnings("unchecked")
	public HibernateDAO() {

		persistentClass = (Class<E>) ((ParameterizedType) (getClass()
				.getGenericSuperclass())).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return session;
	}

	@Override
	public void deleteById(PK id) {
		delete(findById(id, false));
	}

	@Override
	public Class<E> getEntityClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findById(PK id, boolean lock) {
		E entity;
		if (lock)
			entity = (E) getSession().get(getEntityClass(), id,
					LockOptions.UPGRADE);
		else
			entity = (E) getSession().get(getEntityClass(), id);

		return entity;
	}

	public E findById(PK id) {
		return findById(id, false);
	}

	@Override
	public List<E> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findByExample(E exampleInstance) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		crit.add(Example.create(exampleInstance));
		return crit.list();
	}

	protected int countByCriteria(Criterion... criterion) {

		Criteria crit = getSession().createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion)
			crit.add(c);

		return ((Long) crit.list().get(0)).intValue();
	}

	@Override
	public int countAll() {
		return countByCriteria();
	}

	@Override
	public int countByExample(E exampleInstance) {

		Criteria crit = getSession().createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());
		crit.add(Example.create(exampleInstance));

		return (Integer) crit.list().get(0);
	}

	@Override
	public E persist(E entity) {

		getSession().persist(entity);

		return entity;
	}

	@Override
	public E update(E entity) {

		getSession().merge(entity);

		return entity;
	}

	@Override
	public void delete(E entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteriaPageByPage(int firstResult, int maxResults,
			Criterion... criterion) {
		Criteria criteria = getSession().createCriteria(getEntityClass());

		for (Criterion c : criterion)
			criteria.add(c);

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);

		return criteria.list();
	}
}
