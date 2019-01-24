package org.pstcl.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pstcl.dao.AbstractDao;
import org.pstcl.dao.OilSampleDao;
import org.pstcl.model.OilSampleFilterModel;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.User;
import org.pstcl.util.ODTLDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("oilSampleDao")
@Transactional
public class OilSampleDaoImpl extends AbstractDao<Integer, OilSample> implements OilSampleDao {

	static final Logger logger = LoggerFactory.getLogger(OilSampleDaoImpl.class);

	@Override
	public OilSample findById(int id) {
		OilSample oilSample = getByKey(id);

		return oilSample;
	}

	@Override
	public OilSample findBySampleNo(Integer sampleNo) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("sampleNo", sampleNo));
		List<OilSample>  list=crit.list();
		OilSample oilSample = (OilSample)crit.uniqueResult();

		return oilSample;
	}

	@Override
	public OilSample findLatestByUser(User user) {

		Criteria crit = getSession().createCriteria(OilSample.class);//.setProjection(proj);
		crit.add(Restrictions.eq("appUser.id", user.getId()));
		crit.setProjection(Projections.max("sampleNo"));
		Integer id=(Integer)crit.uniqueResult();
		//OilSample entry=(OilSample)crit.uniqueResult();;


		return findById(id);
	}

	@Override
	public OilSample findLargestSampleByNo() {
		Criteria crit = getSession().createCriteria(OilSample.class);//.setProjection(proj);
		crit.setProjection(Projections.max("sampleNo"));
		Integer largestSampleNo=(Integer)crit.uniqueResult();

		return findBySampleNo(largestSampleNo);

	}




	@Override
	public List<OilSample> findAllOilSamples() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.desc("sampleNo"));

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<OilSample>)crit.list();

	}



	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		OilSample oilSample = (OilSample)crit.uniqueResult();
		delete(oilSample);
	}

	@Override
	@Transactional
	public void save(OilSample oilSample,User user) {

		persist(oilSample,user);
	}

	@Override
	public List<OilSample> findPendingOilSamples() {

		//crit.add(Restrictions.isNull("oilReport"));


		Criteria critEquip = getSession().createCriteria(OilReport.class);
		critEquip.setProjection(Projections.property("oilSample.id"));
		List <Integer> ids=critEquip.list();


		Criteria crit = createEntityCriteria();

		if(ids!=null){
			if(ids.size()>0)
			{
				crit.add(Restrictions.not((Restrictions.in("id", ids))));
			}
			else
			{
				crit.add(Restrictions.sqlRestriction("(1=0)"));
			}
		}
		crit.addOrder(Order.desc("sampleNo"));

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<OilSample>)crit.list();

	}


	@Override
	public List<OilSample> findAllOilSamples(OilSampleFilterModel filterModel) {
		Criteria crit =  createEntityCriteria();


		if(!(null==filterModel.getSelectedCircle()&&null==filterModel.getSelectedDivision()&&null==filterModel.getSelectedSubstation()))
		{
			Criteria critEquip = getSession().createCriteria(Equipment.class);
			if(null!=filterModel.getSelectedCircle())
			{
				critEquip.add(Restrictions.eq("circle.circleCode", filterModel.getSelectedCircle().getCircleCode()));
			}
			if(null!=filterModel.getSelectedDivision())
			{
				critEquip.add(Restrictions.eq("division.divisionCode", filterModel.getSelectedDivision().getDivisionCode()));
			}
			if(null!=filterModel.getSelectedSubstation())
			{
				critEquip.add(Restrictions.eq("substation.substationCode", filterModel.getSelectedSubstation().getSubstationCode()));
			}



			critEquip.setProjection(Projections.property("id"));

			List <Integer> ids=critEquip.list();
			if(ids!=null){
				if(ids.size()>0)
				{
					crit.add(Restrictions.in("equipment.id", ids));
				}
				else
				{
					crit.add(Restrictions.sqlRestriction("(1=0)"));
				}
			}
		}

		if(filterModel.getPendingSamples())
		{
			Criteria critOilReport = getSession().createCriteria(OilReport.class);
			critOilReport.setProjection(Projections.property("oilSample.id"));
			List <Integer> oilReportIds=critOilReport.list();

			if(oilReportIds!=null){
				if(oilReportIds.size()>0)
				{
					crit.add(Restrictions.not((Restrictions.in("id", oilReportIds))));
				}
				else
				{
					crit.add(Restrictions.sqlRestriction("(1=0)"));
				}
			}
		}

		
		
		if(null!=filterModel.getSampleMonth() && null!= filterModel.getSampleYear())
		{
			crit.add(Restrictions.ge("sampleReceiptDate",ODTLDateUtil.getFirstDayOfMonth(filterModel.getSampleMonth(), filterModel.getSampleYear())));
			crit.add(Restrictions.le("sampleReceiptDate",ODTLDateUtil.getLastDayOfMonth(filterModel.getSampleMonth(), filterModel.getSampleYear()) ));
		}
		
	
		//crit.add(Restrictions.ge("sampleReceiptDate",filterModel.getStartDate()));
		//crit.add(Restrictions.le("sampleReceiptDate",filterModel.getEndDate()));

		
		if(null!=filterModel.getSampleNo())
		{
			crit.add(Restrictions.eq("sampleNo",filterModel.getSampleNo()));
		}
		if(null!=filterModel.getOilSampleType())
		{
			if(!filterModel.getOilSampleType().isEmpty())
			{
				crit.add(Restrictions.eq("sampleType",filterModel.getOilSampleType()));
			}
		}
		if(null!=filterModel.getBottleNo())
		{
			crit.add(Restrictions.eq("bottleNo",filterModel.getBottleNo()));
		}
		
		if(null!=filterModel.getReferenceMemoNo()&&!filterModel.getReferenceMemoNo().isEmpty())
		{
			crit.add(Restrictions.like("referenceMemoNo",filterModel.getReferenceMemoNo(),MatchMode.ANYWHERE));
		}
		
		crit.addOrder(Order.desc("sampleNo"));

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<OilSample>)crit.list();
	}








}
