package org.pstcl.model;

import java.util.Date;

import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Substation;
import org.pstcl.util.StringUtil;
import org.springframework.format.annotation.NumberFormat;

public class OilReportFilterModel  extends FilterModel{
	
	


	private Integer reportMonth;



	private Integer reportYear;



	private Integer sampleNo;



	public OilReportFilterModel() {
		super();
		
	}


	public OilReportFilterModel(Circle circle,Division div,Substation sub, Integer sampleNo, String reportDateStart,String reportDateEnd, Integer reportMonth,Integer reportYear, Boolean finalReport, Boolean aeeApproval, Boolean rejectedReport) {
		super();
		super.setSelectedCircle(circle);
		super.setSelectedDivision(div);
		super.setSelectedSubstation(sub);
		super.setFinalReport(finalReport);
		super.setRejectedReport(rejectedReport);
		super.setAeeApproval(aeeApproval);
		
		this.sampleNo = sampleNo;
		super.setStartDate(null);
		super.setEndDate(null);
		this.reportMonth = reportMonth;
		this.reportYear=reportYear;
		
	}


	public Integer getReportMonth() {
		return reportMonth;
	}



	public Integer getReportYear() {
		return reportYear;
	}



	public Integer getSampleNo() {
		return sampleNo;
	}



	
	
	public void setReportMonth(Integer reportMonth) {
		this.reportMonth = reportMonth;
	}
	
	public void setReportYear(Integer reportYear) {
		this.reportYear = reportYear;
	}
	public void setSampleNo(Integer sampleNo) {
		this.sampleNo = sampleNo;
	}


	public void setGridReport(boolean b) {
		if(b)
		{
			setOilSampleType(StringUtil.OIL_REPORT_FRESH_OIL);
		}
		else
		{
			setOilSampleType(null);
				
		}
	}
	
	
	
	
}
