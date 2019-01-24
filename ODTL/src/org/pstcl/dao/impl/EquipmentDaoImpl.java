package org.pstcl.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pstcl.dao.AbstractDao;
import org.pstcl.dao.EquipmentDao;
import org.pstcl.model.EquipmentFilterModel;
import org.pstcl.model.FilterModel;
import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("equipmentDao")
@Transactional
public class EquipmentDaoImpl extends AbstractDao<Integer, Equipment> implements EquipmentDao {

	static final Logger logger = LoggerFactory.getLogger(EquipmentDaoImpl.class);

	@Override
	public Equipment findById(int id) {
		Equipment equipment = getByKey(id);

		return equipment;
	}



	@Override
	public List<Equipment> findAllEquipments() {
		Criteria crit = createEntityCriteria();

		crit.addOrder(Order.desc("circle"));
		crit.addOrder(Order.desc("division"));
		crit.addOrder(Order.desc("substation"));


		return (List<Equipment>)crit.list();

	}



	@Override
	@Transactional
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Equipment equipment = (Equipment)crit.uniqueResult();
		delete(equipment);
	}



	@Override
	@Transactional
	public void save(Equipment equipment,User user) {

		persist(equipment,user);
	}

	@Override
	public List<Equipment> getEquipmentsList(Equipment equipment,Boolean getNAEquipments) 
	{
		Criteria crit =  createEntityCriteria();

		//crit.add(Restrictions.eq("id", ));
		//Expression.ge("date",startDate)
		if(null!=equipment)
		{

			if(null!=equipment.getId())
			{
				crit.add(Restrictions.eq("id", equipment.getId()));
			}
			else
			{
				if(!getNAEquipments)
				{
					crit.add(Expression.ge("id",0));
				}
				if(null!=equipment.getCircle())
				{
					crit.add(Restrictions.eq("circle.circleCode", equipment.getCircle().getCircleCode()));
				}
				if(null!=equipment.getDivision())
				{
					crit.add(Restrictions.eq("division.divisionCode", equipment.getDivision().getDivisionCode()));
				}
				if(null!=equipment.getSubstation())
				{
					crit.add(Restrictions.eq("substation.substationCode", equipment.getSubstation().getSubstationCode()));
				}

				if(null!=equipment.getEquipmentID())
				{
					crit.add(Restrictions.eq("equipmentID", equipment.getEquipmentID()));
				}
				if(null!=equipment.getSerialNo())
				{
					crit.add(Restrictions.eq("serialNo", equipment.getSerialNo()));
				}
			}
		}

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Equipment>)crit.list();
	}


	@Override
	public List<Equipment> getEquipmentsList(Equipment equipment) {
		return getEquipmentsList(equipment,false);

	}

	@Override
	public List<Equipment> getEquipmentsList(EquipmentFilterModel filterModel) {
		return getEquipmentsList(filterModel,false);

	}

	@Override
	public List<Equipment> getEquipmentsList(EquipmentFilterModel filterModel,Boolean getNAEquipments) 
	{
		//Equipment equipment=filterModel.getEquipment();
		Criteria crit =  createEntityCriteria();

		//crit.add(Restrictions.eq("id", ));
		//Expression.ge("date",startDate)
		if(null!=filterModel.getEquipmentID()&&!filterModel.getEquipmentID().isEmpty())
		{
			crit.add(Restrictions.ilike("equipmentID", filterModel.getEquipmentID(),MatchMode.ANYWHERE));
		}
		if(null!=filterModel.getSerialNo()&&!filterModel.getSerialNo().isEmpty())
		{
			crit.add(Restrictions.ilike("serialNo", filterModel.getSerialNo(),MatchMode.ANYWHERE));
		}
		if(null!=filterModel.getMake()&&!filterModel.getMake().isEmpty())
		{
			crit.add(Restrictions.ilike("make", filterModel.getMake(),MatchMode.ANYWHERE));
		}
		if(null!=filterModel.getCapacity()&&!filterModel.getCapacity().isEmpty())
		{
			crit.add(Restrictions.ilike("capacity", filterModel.getCapacity(),MatchMode.ANYWHERE));
		}
		if(!getNAEquipments)
		{
			crit.add(Expression.ge("id",0));
		}
		if(null!=filterModel.getSelectedCircle())
		{
			crit.add(Restrictions.eq("circle.circleCode", filterModel.getSelectedCircle().getCircleCode()));
		}
		if(null!=filterModel.getSelectedDivision())
		{
			crit.add(Restrictions.eq("division.divisionCode", filterModel.getSelectedDivision().getDivisionCode()));
		}
		if(null!=filterModel.getSelectedSubstation())
		{
			crit.add(Restrictions.eq("substation.substationCode", filterModel.getSelectedSubstation().getSubstationCode()));
		}


		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Equipment>)crit.list();
	}




}
