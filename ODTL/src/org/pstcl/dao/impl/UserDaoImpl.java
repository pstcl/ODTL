package org.pstcl.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pstcl.dao.AbstractDao;
import org.pstcl.dao.UserDao;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		//	Hibernate.initialize(user.getSeniors());
		}
		return user;
	}

	@Transactional
	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		//	Hibernate.initialize(user.getSeniors());
		}
		
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		//Criteria criteria = createEntityCriteria().addOrder(Order.asc("designation.designationLevel"));


//		Criteria c = getSession().createCriteria(User.class);
//		c.createAlias("designation.designationLevel", "designationDesignationLevel");
//		c.addOrder(Order.asc("designationDesignationLevel"));
//		//return c.list();
//		
//		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
//		
		org.hibernate.Query q = getSession().createQuery("SELECT user from User as user ORDER BY user.designation.designationLevel");
		//return q.list();
		
		List<User> users = (List<User>) q.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	

	@Transactional
	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

	//appuser is the user who is creating new username and password. 
	//entity is the user that will be saved in DB as new row
	@Override
	@Transactional
	public void save(User entity,User appUser) {
		
		persist(entity,appUser);
	}

	@Override
	public List<User> findColleagues(User user) {
		return findAllUsers();
	}

	@Override
	public List<User> findLocationUsers(LocationMaster user) {
		Criteria crit =  createEntityCriteria();
		
		if(null!=user)
		{
			if(null!=user.getCircle())
			{
				crit.add(Restrictions.eq("circle.circleCode", user.getCircle().getCircleCode()));
			}
			if(null!=user.getDivision())
			{
				crit.add(Restrictions.eq("division.divisionCode", user.getDivision().getDivisionCode()));
			}
			if(null!=user.getSubstation())
			{
				crit.add(Restrictions.eq("substation.substationCode", user.getSubstation().getSubstationCode()));
			}
			
			
		}
		
		return (List<User>)crit.list();
	}

	

}
