package org.pstcl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.pstcl.dao.AbstractDao;
import org.pstcl.dao.IUserDesignationDao;
import org.pstcl.model.entity.UserDesignation;
import org.springframework.stereotype.Repository;


@Repository("designationDao")
public class UserDesignationMasterDAOImpl extends AbstractDao<Integer, UserDesignation> implements IUserDesignationDao {
	
	@Override
	public UserDesignation findById(int id) {
		UserDesignation userDesignation = getByKey(id);
		return userDesignation;
	}
	
	@Override
	public List<UserDesignation> findAllDesignations() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("designationLevel"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<UserDesignation> designations = (List<UserDesignation>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return designations;
	}

	
}
