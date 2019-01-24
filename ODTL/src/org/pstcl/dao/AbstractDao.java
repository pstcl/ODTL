package org.pstcl.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pstcl.model.entity.ODTLEntityMaster;
import org.pstcl.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	//	public HRDDomain save(HRDDomain user) {
	//		// TODO Auto-generated method stub
	//		System.out.println("Before updation");
	//		LogDetails logDetails=new LogDetails();
	//		logDetails.setTableName(user.getClass().getName());
	//		logDetails.setBasicDetails(user.getBasicDetails());
	//		logDetails.setTimeStamp(new Date(System.currentTimeMillis()));
	//		logDetails.setUser(getUser());
	//		getHibernateTemplate().saveOrUpdate(user);
	//		logDetails.setObjectValue(user.getLogDetails());
	//		getHibernateTemplate().save(logDetails);
	//		getHibernateTemplate().flush();
	//		return user;
	//	}
	//
	//	public HRDDomain saveNew(HRDDomain user) {
	//		// TODO Auto-generated method stub
	//		System.out.println("Before updation");
	//		LogDetails logDetails=new LogDetails();
	//		logDetails.setTableName(user.getClass().getName());
	//		logDetails.setBasicDetails(user.getBasicDetails());
	//		logDetails.setTimeStamp(new Date(System.currentTimeMillis()));
	//		logDetails.setUser(getUser());
	//		getHibernateTemplate().save(user);;
	//		logDetails.setObjectValue(user.getLogDetails());
	//		getHibernateTemplate().save(logDetails);
	//		getHibernateTemplate().flush();
	//		getHibernateTemplate().flush();
	//		return user;
	//	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	@Transactional
	public void persist(T entity) {


		getSession().saveOrUpdate(entity);
	}
	
	@Transactional
	public void persist(T entity,User user) {

		((ODTLEntityMaster)entity).setAppUser(user);
		((ODTLEntityMaster)entity).setDateEntered(new Date(System.currentTimeMillis()));
		getSession().persist(entity);
	}


	@Transactional
	public void update(T entity) {
		getSession().update(entity);
	}

	@Transactional
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Transactional
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}


}
