package org.pstcl.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.pstcl.birt.spring.core.BirtEngineFactory;
import org.pstcl.birt.spring.odtl.BirtViewOilReport;
import org.pstcl.dao.ReportFileDao;
import org.pstcl.logging.LogDetails;
import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.OilReportFilterModel;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.ReportFile;
import org.pstcl.model.entity.ReportOfficer;
import org.pstcl.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("oilReportService")
@Transactional
public class OilReportServiceImpl extends ODTLServiceUtil implements OilReportService {


	@Autowired
	ReportFileDao reportFileDao;
	
	@Autowired
	BirtEngineFactory engine;

	@Autowired
	private ServletContext context;


	public List<OilReport> findAllOilReports() {
		return this.oilReportDao.findAllOilReports();
	}

	//	public void saveOilReport(OilReport oilReport) {
	//		oilReport.setReportPreparationDate(new Date(System.currentTimeMillis()));
	//		this.oilReportDao.save(oilReport);
	//	}
	@Transactional
	private void logEvent(OilReport oldEntity,OilReport updatedEntity,String action) {

		LogDetails logDetails=new LogDetails();
		logDetails.setEntityAction(action);
		logDetails.setUser(userDao.findBySSO(getPrincipal()));
		if(null!=oldEntity)
		{
			logDetails.setObjectValueOld(oldEntity.toString());;
		}		logDetails.setObjectValueUpdated(updatedEntity.toString());
		logDetails.setEntityID(updatedEntity.getId());
		logDetails.setTableName(updatedEntity.getClass().toString());
		logDetails.setTimeStamp(new Date(System.currentTimeMillis()));

		logDetailsDao.save(logDetails);

	}

	@Transactional
	public OilReport saveOilReport(OilReport oilReport) {
		String msg="";
		oilReport.setApprovalAEE(StringUtil.DECISION_PENDING);
		oilReport.setApprovalASE(StringUtil.DECISION_PENDING);
		if (null==oilReport.getId()) {
			msg="OilReport No." + oilReport.getMemoNo()+ " saved successfully";
			oilReport.setOilSample(oilSampleDao.findById(oilReport.getOilSample().getId()));
			oilReport.setReportJEDetails(new ReportOfficer(userDao.findBySSO(getPrincipal()),oilReport.getClass()));
			oilReport.setReportPreparationDate(new Date(System.currentTimeMillis()));
			oilReport.setSampleNo(oilReport.getOilSample().getSampleNo());
			logEvent(null,oilReport,StringUtil.CREATE_EVENT);
			oilReportDao.save(oilReport,userDao.findBySSO(getPrincipal()));
		}
		return  oilReport;
	}

	@Transactional
	public OilReport updateOilReport(OilReport oilReport) {
		String msg="";
		oilReport.setApprovalAEE(StringUtil.DECISION_PENDING);
		oilReport.setApprovalASE(StringUtil.DECISION_PENDING);
		OilReport entity = this.oilReportDao.findById(oilReport.getId());
		if (entity != null) {
			msg="OilReport No." + oilReport.getMemoNo()+ " updated successfully";
			if(!(entity.getApprovalAEE().equals(StringUtil.APPROVED)&&entity.getApprovalASE().equals(StringUtil.APPROVED)))
			{
				oilReport.setOilSample(oilSampleDao.findById(oilReport.getOilSample().getId()));
				//oilReport.setSampleNo(oilReport.getOilSample().getSampleNo());
				logEvent(entity,oilReport,StringUtil.UPDATE_EVENT);
				entity.updateOilReport(oilReport);
			}
		}

		return  oilReportDao.findById(oilReport.getId());
	}


	public OilReport findOilReportById(Integer id) {
		return this.oilReportDao.findById(id);
	}

	//	public void updateOilReport(OilReport oilReport) {
	//		OilReport entity = this.oilReportDao.findById(oilReport.getId().intValue());
	//		if (entity != null) {
	//			entity.updateOilReport(oilReport);
	//		}
	//
	//	}



	public OilReport createOilReportForSample(Integer id) {
		OilReport entity = new OilReport();
		if(null!=id)
		{
			OilSample oilSample = oilSampleDao.findById(id);
			entity.setOilSample(oilSample);
			entity.setEquipment(oilSample.getEquipment());
		}
		else
		{
			entity.setOilSample(oilSampleService.createOilSampleForRoutineOil(null));
		}
		entity.setReportPreparedBy(this.userDao.findBySSO(getPrincipal()));
		entity.setReportDate(new Date(System.currentTimeMillis()));
		setReportNo(entity);

		return entity;
	}


	//@Autowired
	//BirtViewOilReport birtViewOilReport;

	@Transactional
	public OilReport saveASERemarksOilReport(OilReport oilReport) {
		OilReport entity = this.oilReportDao.findById(oilReport.getId().intValue());
		if (entity != null) {
			logEvent(entity,oilReport,StringUtil.ASE_REMARKS);
			//This has been added to save edited JE remarks by ASE
			entity.setRemarks(oilReport.getRemarks());
			//This has been added to save edited AEE remarks by ASE
			entity.setRemarksAEE(oilReport.getRemarksAEE());
			//Added to check Rejection Status

			entity.setReportASE(this.userDao.findBySSO(getPrincipal()));
			entity.setSuggestionsASE(oilReport.getSuggestionsASE());
			if(oilReport.getApprovalASE().equals(StringUtil.APPROVED))
			{
				logEvent(entity,oilReport,StringUtil.ASE_APPROVAL);
				entity.setApprovalASE(StringUtil.APPROVED);

				ReportFile file=createReportFile(entity);
				try {
					reportFileDao.save(file, getLoggedInUser());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			else if(oilReport.getApprovalASE().equals(StringUtil.REJECTED))
			{
				logEvent(entity,oilReport,StringUtil.ASE_REJECTION);
				entity.setApprovalASE(StringUtil.REJECTED);
			}

			entity.setReportAEEApprovalDate(new Date(System.currentTimeMillis()));
			entity.setReportAEEDetails(new ReportOfficer(userDao.findBySSO(getPrincipal()),oilReport.getClass()));

		}
		return entity;
	}



	@Transactional
	public OilReport saveAEERemarksOilReport(OilReport oilReport) {
		OilReport entity = this.oilReportDao.findById(oilReport.getId());
		if (entity != null) {
			logEvent(entity,oilReport,StringUtil.AEE_REMARKS);

			//This has been added to save edited JE remarks by AEE
			entity.setRemarks(oilReport.getRemarks());
			entity.setReportAEE(this.userDao.findBySSO(getPrincipal()));
			entity.setRemarksAEE(oilReport.getRemarksAEE());
			//Added to check Rejection Status
			if(oilReport.getApprovalAEE().equals(StringUtil.APPROVED))
			{
				logEvent(entity,oilReport,StringUtil.AEE_APPROVAL);
				entity.setApprovalAEE(StringUtil.APPROVED);
				entity.setApprovalASE(StringUtil.DECISION_PENDING);

			}
			else if(oilReport.getApprovalAEE().equals(StringUtil.REJECTED))
			{
				logEvent(entity,oilReport,StringUtil.AEE_REJECTION);
				entity.setApprovalAEE(StringUtil.REJECTED);
				entity.setApprovalASE(StringUtil.DECISION_PENDING);
			}
			entity.setReportASEDetails(new ReportOfficer(userDao.findBySSO(getPrincipal()),oilReport.getClass()));
			entity.setReportASEApprovalDate(new Date(System.currentTimeMillis()));
		}
		return entity;
	}

	public List<OilReport> findAllOilReports(ODTLFilterModel filterModel) {
		if(userService.hasRole("ODTL"))
		{
			if(userService.hasRole("ASE"))
			{
				filterModel.setAeeApproval(true);
			}

		}
		else
		{
			filterModel.setFinalReport(true);
		}
		return this.oilReportDao.findAllOilReports();
	}




	@Override
	public ODTLFilterModel createODTLFilter() {
		ODTLFilterModel filterModel=new ODTLFilterModel(new Equipment(), new OilSample(), new OilReport());
		return filterModel;
	}



	@Override
	public void setReportNo(OilReport oilReport) {
		if(null!=oilReport.getOilSample())
		{
			oilReport.setMemoNo("ODTL/"+getFiscalYear(oilReport.getReportDate())+"/"+(1+oilReport.getReportDate().getMonth())+"/"+oilReport.getOilSample().getSampleNo());
		}
		else
		{
			oilReport.setMemoNo("ODTL/"+getFiscalYear(oilReport.getReportDate())+"/"+(1+oilReport.getReportDate().getMonth())+"/");
		}
	}



	private String getFiscalYear(Date reportDate ) {

		Calendar calendarDate=Calendar.getInstance();
		calendarDate.setTime(reportDate);
		int month = calendarDate.get(Calendar.MONTH);
		int year = calendarDate.get(Calendar.YEAR);
		int startYear= (month >= Calendar.MARCH+1) ? year : year - 1;
		return startYear+"-"+(startYear+1);


	}

		@Override
		public OilReport runTask() {
			OilReportFilterModel oilReportFilterModel=new OilReportFilterModel();
	
			oilReportFilterModel.setFinalReport(true);
	
			List<OilReport> list= oilReportDao.findAllOilReports(oilReportFilterModel);
	
			for (OilReport oilReport : list) {
				try {
	
	
	
					ReportFile file=createReportFile(oilReport);	
					reportFileDao.save(file, getLoggedInUser());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	
			return null;
		}
	private ReportFile createReportFile(OilReport oilReport) {
		
		BirtViewOilReport  birtViewOilReport=new BirtViewOilReport();
		birtViewOilReport.setBirtEngine(engine.getObject());
		
		
		ReportFile file=reportFileDao.findBySampleId(oilReport.getOilSample().getId());

		if(null==file)
		{

			file=new ReportFile(oilReport,oilReport.getOilSample(),oilReport.getEquipment());
		}

		birtViewOilReport.setReportID(oilReport.getId());
		

		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_ROUTINE_OIL))
		{
			birtViewOilReport.setReportOutputFileName("ODTL_Circle_"+oilReport.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()+"_Div_"+oilReport.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()+"_SS_"+oilReport.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()+"id"+oilReport.getId());
			birtViewOilReport.setReportName("RoutineOil.rptdesign");
		} 
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
		{
			birtViewOilReport.setReportOutputFileName("ODTL_Circle_"+oilReport.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()+"_Div_"+oilReport.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()+"_SS_"+oilReport.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()+"id"+oilReport.getId());
			birtViewOilReport.setReportName("NewOil.rptdesign");	
		}
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_FRESH_OIL))
		{
			birtViewOilReport.setReportOutputFileName("ODTL_Circle_"+oilReport.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()+"_Div_"+oilReport.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()+"_SS_"+oilReport.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()+"id"+oilReport.getId());
			birtViewOilReport.setReportName("RoutineOil.rptdesign");	
		}
		try {
			birtViewOilReport.saveReportToDb(file,context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}


}