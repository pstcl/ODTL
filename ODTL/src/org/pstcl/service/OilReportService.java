package org.pstcl.service;

import java.util.List;

import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.entity.OilReport;
import org.springframework.security.access.prepost.PreAuthorize;

public interface OilReportService {
	List<OilReport> findAllOilReports();
	
	OilReport createOilReportForSample(Integer arg0);
	List<String> getSampleConditionList();

	ODTLFilterModel createODTLFilter();
	List<String> getAppearanceList();

	List<String> getCorrosiveSulphurList();

	List<String> getOxidationInhibitorList();

	OilReport findOilReportById(Integer arg0);

	OilReport saveOilReport(OilReport arg0);

	//void updateOilReport(OilReport arg0);

	OilReport saveASERemarksOilReport(OilReport arg0);

	OilReport saveAEERemarksOilReport(OilReport arg0);

	List<OilReport> findAllOilReports(ODTLFilterModel arg0);

	void setReportNo(OilReport oilReport);

	String getPrincipal();

	
	OilReport updateOilReport(OilReport arg0);

	OilReport runTask();

	//OilReport runTask();

	
}