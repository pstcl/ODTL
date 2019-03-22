package org.pstcl.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.pstcl.dao.AbstractDao;
import org.pstcl.dao.OilReportDao;
import org.pstcl.model.FilterModel;
import org.pstcl.model.OilReportFilterModel;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.User;
import org.pstcl.model.transformer.entity.OilReportEntityModel;
import org.pstcl.util.ODTLDateUtil;
import org.pstcl.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("oilReportDao")
@Transactional
public class OilReportDaoImpl extends AbstractDao<Integer, OilReport> implements OilReportDao {

	static final Logger logger = LoggerFactory.getLogger(OilReportDaoImpl.class);

	@Override
	public OilReport findById(int id) {
		OilReport oilReport = getByKey(id);
		return oilReport;
	}



	@Override
	public List<OilReport> findAllOilReports() {
		Criteria crit = createEntityCriteria();
		//crit.addOrder(Order.desc("Dia_MM_G6"));
		return (List<OilReport>)crit.list();

	}



	@Override
	@Transactional
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		OilReport oilReport = (OilReport)crit.uniqueResult();
		delete(oilReport);
	}


	@Override
	@Transactional
	public void save(OilReport oilReport,User user) {

		persist(oilReport,user);

	}

	private List<Integer> getCriteriaOilSampleIds(OilReportFilterModel filterModel)
	{
		Criteria critOilSample = getSession().createCriteria(OilSample.class);


		if(null!=filterModel.getReportMonth() && null!= filterModel.getReportYear())
		{
			critOilSample.add(Restrictions.ge("sampleReceiptDate",ODTLDateUtil.getFirstDayOfMonth(filterModel.getReportMonth(), filterModel.getReportYear())));
			critOilSample.add(Restrictions.le("sampleReceiptDate",ODTLDateUtil.getLastDayOfMonth(filterModel.getReportMonth(), filterModel.getReportYear()) ));
		}		
		if(null!=filterModel.getSampleNo())
		{
			critOilSample.add(Restrictions.eq("sampleNo",filterModel.getSampleNo()));
		}
		if(null!=filterModel.getOilSampleType())
		{
			if(!filterModel.getOilSampleType().equalsIgnoreCase(""))
			{
				critOilSample.add(Restrictions.eq("sampleType",filterModel.getOilSampleType()));
			}
		}
		critOilSample.setProjection(Projections.property("id"));
		critOilSample.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);



		return critOilSample.list();
	}

	private List<Integer> getCriteriaEquipmentIds(FilterModel filterModel)
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
		critEquip.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);


		critEquip.setProjection(Projections.property("id"));

		return critEquip.list();
	}




	@Deprecated
	@Override
	public List<OilReport> findAllOilReports(OilReportFilterModel filterModel) {
		Criteria crit =  createEntityCriteria();


		if(null!=filterModel.getOilSample())
		{
			if(null!=filterModel.getOilSample().getId())
			{
				crit.add(Restrictions.eq("oilSample.id", filterModel.getOilSample().getId()));
			}
		}
		if(filterModel.getFinalReport())
		{
			crit.add(Restrictions.eq("approvalASE", StringUtil.APPROVED));
		}

		else if(filterModel.getRejectedReport())
		{

			Criterion rest1= Restrictions.eq("approvalAEE", StringUtil.REJECTED);
			Criterion rest2= Restrictions.eq("approvalASE", StringUtil.REJECTED);
			crit.add(Restrictions.or(rest1, rest2));

		}

		else if(filterModel.getAeeApproval())
		{
			crit.add(Restrictions.eq("approvalAEE",StringUtil.APPROVED ));
			crit.add(Restrictions.ne("approvalASE", StringUtil.APPROVED));
		}

		else if(!filterModel.getFinalReport()&&!filterModel.getAeeApproval()&&!filterModel.getRejectedReport())
		{
			Criterion rest3= Restrictions.eq("approvalAEE", StringUtil.DECISION_PENDING);
			Criterion rest4= Restrictions.eq("approvalASE", StringUtil.DECISION_PENDING);
			crit.add(Restrictions.or(rest3, rest4));
		}




		crit.addOrder(Order.desc("sampleNo"));


		//		crit.setProjection(Projections.projectionList()
		//				.add(Projections.property("id"), "id")
		//				.add(Projections.property("approvalAEE"), "approvalAEE")
		//				.add(Projections.property("approvalASE"), "approvalASE")
		//				.add(Projections.property("oilSample"), "oilSample")
		//				.add(Projections.property("equipment"), "equipment")
		//				.add(Projections.property("reportDate"), "reportDate"))
		//		.setResultTransformer(Transformers.aliasToBean(OilReport.class));

		//		crit.setProjection(
		//				Projections.projectionList()
		//				.add(Projections.property("equipment"), "equipment")
		//				.add(Projections.property("oilSample"), "oilSample")
		//				.add(Projections.property("sampleNo"), "sampleNo")
		//				.add(Projections.property("reportDate"), "reportDate")
		//				.add(Projections.property("approvalAEE"), "approvalAEE")
		//				.add(Projections.property("approvalASE"), "approvalASE")
		//				.add(Projections.property("id"), "id")
		//				)
		//		.setResultTransformer(Transformers.aliasToBean(OilReport.class));

		if(!(null==filterModel.getSelectedCircle()&&null==filterModel.getSelectedDivision()&&null==filterModel.getSelectedSubstation()))
		{


			List <Integer> ids=getCriteriaEquipmentIds(filterModel);

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
		List <Integer> sampleIds=getCriteriaOilSampleIds(filterModel);

		if(sampleIds!=null){
			if(sampleIds.size()>0)
			{
				crit.add(Restrictions.in("oilSample.id", sampleIds));
			}
			else
			{
				crit.add(Restrictions.sqlRestriction("(1=0)"));
			}
		}

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<OilReport> list=(List<OilReport>) crit.list();
		return list;
	}


	@Override
	public List<OilReportEntityModel> findAllOilReportEntityModelList(OilReportFilterModel filterModel) {
		Criteria crit =  createEntityCriteria();


		if(null!=filterModel.getOilSample())
		{
			if(null!=filterModel.getOilSample().getId())
			{
				crit.add(Restrictions.eq("oilSample.id", filterModel.getOilSample().getId()));
			}
		}
		if(filterModel.getFinalReport())
		{
			crit.add(Restrictions.eq("approvalASE", StringUtil.APPROVED));
		}

		else if(filterModel.getRejectedReport())
		{

			Criterion rest1= Restrictions.eq("approvalAEE", StringUtil.REJECTED);
			Criterion rest2= Restrictions.eq("approvalASE", StringUtil.REJECTED);
			crit.add(Restrictions.or(rest1, rest2));

		}

		else if(filterModel.getAeeApproval())
		{
			crit.add(Restrictions.eq("approvalAEE",StringUtil.APPROVED ));
			crit.add(Restrictions.ne("approvalASE", StringUtil.APPROVED));
		}

		else if(!filterModel.getFinalReport()&&!filterModel.getAeeApproval()&&!filterModel.getRejectedReport())
		{
			Criterion rest3= Restrictions.eq("approvalAEE", StringUtil.DECISION_PENDING);
			Criterion rest4= Restrictions.eq("approvalASE", StringUtil.DECISION_PENDING);
			crit.add(Restrictions.or(rest3, rest4));
		}




		//crit.addOrder(Order.desc("sampleNo"));


		
		//		crit.setProjection(
		//				Projections.projectionList()
		//				.add(Projections.property("equipment"), "equipment")
		//				.add(Projections.property("oilSample"), "oilSample")
		//				.add(Projections.property("sampleNo"), "sampleNo")
		//				.add(Projections.property("reportDate"), "reportDate")
		//				.add(Projections.property("approvalAEE"), "approvalAEE")
		//				.add(Projections.property("approvalASE"), "approvalASE")
		//				.add(Projections.property("id"), "id")
		//				)
		//		.setResultTransformer(Transformers.aliasToBean(OilReport.class));

		if(!(null==filterModel.getSelectedCircle()&&null==filterModel.getSelectedDivision()&&null==filterModel.getSelectedSubstation()))
		{


			List <Integer> ids=getCriteriaEquipmentIds(filterModel);

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
		List <Integer> sampleIds=getCriteriaOilSampleIds(filterModel);

		if(sampleIds!=null){
			if(sampleIds.size()>0)
			{
				crit.add(Restrictions.in("oilSample.id", sampleIds));
			}
			else
			{
				crit.add(Restrictions.sqlRestriction("(1=0)"));
			}
		}

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		//List<OilReportEntityModel> results = crit.setResultTransformer(Transformers.aliasToBean(OilReportEntityModel.class)).list();
		//	List<OilReport> list=(List<OilReport>) crit.list();
		
		List<OilReportEntityModel> results =crit.setProjection(Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("approvalAEE"), "approvalAEE")
				.add(Projections.property("approvalASE"), "approvalASE")
				.add(Projections.property("oilSample"), "oilSample")
				.add(Projections.property("equipment"), "equipment")
				.add(Projections.property("reportDate"), "reportDate"))
		.setResultTransformer(Transformers.aliasToBean(OilReport.class)).list();

		return results;
	}




}
