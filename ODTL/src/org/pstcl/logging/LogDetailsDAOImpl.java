package org.pstcl.logging;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pstcl.dao.AbstractDao;
import org.pstcl.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository("logDetailsDAO")

public class LogDetailsDAOImpl extends AbstractDao<Integer, LogDetails> implements ILogDetailsDao {

	static final Logger logger = LoggerFactory.getLogger(LogDetailsDAOImpl.class);

	

	 



	@Override
	public List<LogDetails> getList() {
		Criteria crit = createEntityCriteria();
	
		return (List<LogDetails>)crit.list();

	}



	@Override
	@Transactional
	public void save(LogDetails oilSample) {

		persist(oilSample);
	}



	@Override
	public List<LogDetails> getList(User user, LogDetails logDetailsFilter) {
		Criteria crit =  createEntityCriteria();
		if(null!=user)
		{
			if(null!=user.getId())
			{
				crit.add(Restrictions.eq("user", user.getId()));
			}
		}
		if(null!=logDetailsFilter)
		{
			
			if(null!=logDetailsFilter.getEntityID())
			{
				crit.add(Restrictions.eq("entityID", logDetailsFilter.getEntityID()));
			}
			if(null!=logDetailsFilter.getEntityAction()&&"".equalsIgnoreCase(logDetailsFilter.getEntityAction().trim()))
			{
				crit.add(Restrictions.eq("entityAction", logDetailsFilter.getEntityAction()));
			}
			
			if(null!=logDetailsFilter.getTableName()&&"".equalsIgnoreCase(logDetailsFilter.getTableName().trim()))
			{
				crit.add(Restrictions.eq("tableName", logDetailsFilter.getTableName()));
			}
			if(null!=logDetailsFilter.getObjectValueUpdated()&&"".equalsIgnoreCase(logDetailsFilter.getObjectValueUpdated().trim()))
			{
				crit.add(Restrictions.like("objectValueUpdated", logDetailsFilter.getObjectValueUpdated()));
			}
			if(null!=logDetailsFilter.getObjectValueOld()&&"".equalsIgnoreCase(logDetailsFilter.getObjectValueOld().trim()))
			{
				crit.add(Restrictions.like("objectValueOld", logDetailsFilter.getObjectValueOld()));
			}
		
		}
		
		return crit.list();
	}













}
