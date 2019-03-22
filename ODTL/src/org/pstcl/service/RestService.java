package org.pstcl.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.pstcl.dao.LocationDao;
import org.pstcl.dao.ReportFileDao;
import org.pstcl.model.EquipmentFilterModel;
import org.pstcl.model.FilterModel;
import org.pstcl.model.OilReportFilterModel;
import org.pstcl.model.OilSampleFilterModel;
import org.pstcl.model.RestLocationMaster;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.ReportFile;
import org.pstcl.model.entity.Substation;
import org.pstcl.model.entity.User;
import org.pstcl.model.transformer.entity.OilReportEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("restService")
public class RestService extends ODTLServiceUtil {
	@Autowired
	ReportFileDao reportFileDao;


	public Circle findCircleById(String code) {
		return this.locationDao.findCircleByID(code);
	}

	public Division findDivisionById(String code) {
		return this.locationDao.findDivisionByID(code);
	}

	public Substation findSubstationById(String code) {
		return this.locationDao.findSubstationByID(code);
	}


	public List<Circle> getCircleList(FilterModel locationModel) {

		return this.locationDao.listCircles(locationModel);
	}

	public List<Division> getDivisionList(FilterModel locationModel) {
		return this.locationDao.listDivisions(locationModel);
	}

	public List<Substation> getSubstationList(FilterModel locationModel) {
		return this.locationDao.listSubstations(locationModel);
	}

	public FilterModel getLocationModel(String circle, String divCode, String substationCode) {
		FilterModel locationModel=new FilterModel();
		locationModel.setSelectedCircle(findCircleById(circle));
		locationModel.setSelectedDivision(findDivisionById(divCode));
		locationModel.setSelectedSubstation(findSubstationById(substationCode));

		locationModel.setCircleList(getCircleList(locationModel));
		locationModel.setDivisionList(getDivisionList(locationModel));
		locationModel.setSubstationList(getSubstationList(locationModel));
		return locationModel;
	}

	public FilterModel getLocationModel(FilterModel filterModel1) {
		FilterModel locationModel=new FilterModel();
		locationModel.setSelectedCircle(findCircleById(filterModel1.getSelectedCircle().getCircleCode()));
		//locationModel.setSelectedDivision(findDivisionById(divCode));
		//locationModel.setSelectedSubstation(findSubstationById(substationCode));

		locationModel.setCircleList(getCircleList(locationModel));
		locationModel.setDivisionList(getDivisionList(locationModel));
		locationModel.setSubstationList(getSubstationList(locationModel));
		return locationModel;
	}


	public List<Equipment> getEquipmentsList(EquipmentFilterModel filter) {
		return this.equipmentDao.getEquipmentsList(filter);
	}

	public List<OilSample> findAllOilSamples(OilSampleFilterModel oilSampleFilterModel) {

		//return oilSampleDao.findAllOilSamples(oilSampleFilterModel);

		User user=getLoggedInUser();
		List<OilSample> oilSamples;
		if(userService.hasRole("ODTL"))
		{
			oilSamples=oilSampleDao.findAllOilSamples(oilSampleFilterModel);
		}
		else if(userService.hasRole("MGMT"))
		{

			oilSamples=oilSampleDao.findAllOilSamples(oilSampleFilterModel);

		}
		else
		{

			if(userService.hasRole("GRID"))
			{
				oilSampleFilterModel.setFreshOilReport(true);
			}
			else
			{
				if(userService.hasRole("ASE"))
				{
					oilSampleFilterModel.setSelectedDivision(user.getDivision());
				}
				else
				{
					oilSampleFilterModel.setSelectedSubstation(user.getSubstation());
				}
			}


			oilSamples=oilSampleDao.findAllOilSamples(oilSampleFilterModel);
		}
		return oilSamples;



	}


	//Deprecated in favor of method below that returns a subset of OilReport for viewing report details without parameters in OilReportEntityModel List
	@Deprecated
	public List<OilReport> findAllOilReports(OilReportFilterModel oilReportFilterModel) {
		List<OilReport> oilreports;
		User user=getLoggedInUser();

		if(userService.hasRole("ODTL"))
		{
			if(userService.hasRole("ASE"))
			{
				oilReportFilterModel.setAeeApproval(true);
			}
			oilreports=oilReportDao.findAllOilReports(oilReportFilterModel);
		}
		else if(userService.hasRole("MGMT"))
		{
			oilReportFilterModel.setFinalReport(true);
			oilreports=oilReportDao.findAllOilReports(oilReportFilterModel);

		}
		else
		{
			oilReportFilterModel.setFinalReport(true);
			if(userService.hasRole("GRID"))
			{

				oilReportFilterModel.setGridReport(true);
			}
			else
			{
				oilReportFilterModel.setReportMonth(null);
				oilReportFilterModel.setReportYear(null);
				if(userService.hasRole("ASE"))
				{
					oilReportFilterModel.setSelectedDivision(user.getDivision());
				}
				else
				{
					oilReportFilterModel.setSelectedSubstation(user.getSubstation());
				}
			}


			oilreports=oilReportDao.findAllOilReports(oilReportFilterModel);
		}
		return oilreports;
	}


	public List<OilReportEntityModel> findAllOilReportEntityModelList(OilReportFilterModel oilReportFilterModel) {
		List<OilReportEntityModel> oilreports;
		User user=getLoggedInUser();

		if(userService.hasRole("ODTL"))
		{
			if(userService.hasRole("ASE"))
			{
				oilReportFilterModel.setAeeApproval(true);
			}
		}
		else if(userService.hasRole("MGMT"))
		{
			oilReportFilterModel.setFinalReport(true);

		}
		else
		{
			oilReportFilterModel.setFinalReport(true);
			if(userService.hasRole("GRID"))
			{

				oilReportFilterModel.setGridReport(true);
			}
			else
			{
				oilReportFilterModel.setReportMonth(null);
				oilReportFilterModel.setReportYear(null);
				if(userService.hasRole("ASE"))
				{
					oilReportFilterModel.setSelectedDivision(user.getDivision());
				}
				else
				{
					oilReportFilterModel.setSelectedSubstation(user.getSubstation());
				}
			}

		}

		oilreports=oilReportDao.findAllOilReportEntityModelList(oilReportFilterModel);

		return oilreports;
	}


	public List<ReportFile> getPDFFiles(Integer start, Integer end) {
		List<ReportFile> files= reportFileDao.findAllReportFiles(start,end);


		return  files;
	}


}
