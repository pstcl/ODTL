package org.pstcl.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pstcl.dao.LocationDao;
import org.pstcl.model.FilterModel;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.Substation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("locationDao")
@Transactional
//public class EquipmentDaoImpl extends AbstractDao<Integer, Equipment> implements EquipmentDao {
public class LocationDaoImpl implements LocationDao {
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	private SessionFactory sessionFactory;

	
	@Deprecated //in favour of method below
	@Override
	public List<Circle> listCircles(LocationMaster equipment) {
		Criteria crit = getSession().createCriteria(Circle.class);
		crit.addOrder(Order.asc("circleDescription"));
		return (List<Circle>)crit.list();
		
	}



	@Deprecated //in favour of method below
	@Override
	public List<Division> listDivisions(LocationMaster equipment) {
		Criteria crit = getSession().createCriteria(Division.class);
		if(null!=equipment)
		{
			if(null!=equipment.getCircle())
			{
				crit.add(Restrictions.eq("circle.circleCode", equipment.getCircle().getCircleCode()));
			}
		}
		crit.addOrder(Order.asc("divisionDescription"));

		return (List<Division>)crit.list();
	}



	@Deprecated //in favour of method below
	@Override
	public List<Substation> listSubstations(LocationMaster entity) {
		Criteria crit = getSession().createCriteria(Substation.class);

		if(null!=entity)
		{
			if(null!=entity.getCircle())
			{
				crit.add(Restrictions.eq("circle.circleCode", entity.getCircle().getCircleCode()));
			}
			if(null!=entity.getDivision())
			{
				crit.add(Restrictions.eq("division.divisionCode", entity.getDivision().getDivisionCode()));
			}
			if(null!=entity.getSubstation())
			{
				crit.add(Restrictions.eq("substationCode", entity.getSubstation().getSubstationCode()));
			}
		}
		crit.addOrder(Order.asc("substationDescription"));
		return (List<Substation>)crit.list();
	}

	
	@Override
	public List<Circle> listCircles(FilterModel equipment) {
		Criteria crit = getSession().createCriteria(Circle.class);
		crit.addOrder(Order.asc("circleDescription"));
		return (List<Circle>)crit.list();
		
	}



	@Override
	public List<Division> listDivisions(FilterModel equipment) {
		Criteria crit = getSession().createCriteria(Division.class);
		if(null!=equipment)
		{
			if(null!=equipment.getSelectedCircle())
			{
				crit.add(Restrictions.eq("circle.circleCode", equipment.getSelectedCircle().getCircleCode()));
			}
		}
		crit.addOrder(Order.asc("divisionDescription"));

		return (List<Division>)crit.list();
	}



	@Override
	public List<Substation> listSubstations(FilterModel entity) {
		Criteria crit = getSession().createCriteria(Substation.class);

		if(null!=entity)
		{
			if(null!=entity.getSelectedCircle())
			{
				crit.add(Restrictions.eq("circle.circleCode", entity.getSelectedCircle().getCircleCode()));
			}
			if(null!=entity.getSelectedDivision())
			{
				crit.add(Restrictions.eq("division.divisionCode", entity.getSelectedDivision().getDivisionCode()));
			}
			if(null!=entity.getSelectedSubstation())
			{
				crit.add(Restrictions.eq("substationCode", entity.getSelectedSubstation().getSubstationCode()));
			}
		}
		crit.addOrder(Order.asc("substationDescription"));
		return (List<Substation>)crit.list();
	}


	@Override
	public Circle findCircleByID(String code) {
		return (Circle) getSession().get(Circle.class , code);
	}

	@Override
	public Division findDivisionByID(String code) {
		return (Division) getSession().get(Division.class, code);
	}

	@Override
	public Substation findSubstationByID(String code) {
		return (Substation) getSession().get(Substation.class, code);
	}



}
