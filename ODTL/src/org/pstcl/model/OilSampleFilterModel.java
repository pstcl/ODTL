package org.pstcl.model;

import java.util.Date;

import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Substation;
import org.pstcl.util.StringUtil;
import org.springframework.format.annotation.NumberFormat;

public class OilSampleFilterModel  extends FilterModel{
	
	private Integer sampleMonth;



	public Integer getSampleMonth() {
		return sampleMonth;
	}



	public void setSampleMonth(Integer sampleMonth) {
		this.sampleMonth = sampleMonth;
	}



	public Integer getSampleYear() {
		return sampleYear;
	}



	public void setSampleYear(Integer sampleYear) {
		this.sampleYear = sampleYear;
	}

	private Integer sampleYear;

	
	public OilSampleFilterModel(Circle circle,Division div,Substation sub,Integer bottleNo, Integer sampleNo, Date referenceMemoDate, String referenceMemoNo,Boolean pendingSamples,
			Integer month,Integer year) {
		super();
		super.setSelectedCircle(circle);
		super.setSelectedDivision(div);
		super.setSelectedSubstation(sub);
		this.bottleNo = bottleNo;
		this.sampleNo = sampleNo;
		this.referenceMemoDate = referenceMemoDate;
		this.referenceMemoNo = referenceMemoNo;
		this.setPendingSamples(pendingSamples);
		this.sampleMonth=month;
		this.sampleYear=year;
	}
	
	
	
	public OilSampleFilterModel() {
		super();
		
	}



	public Integer getBottleNo() {
		return bottleNo;
	}

	public void setBottleNo(Integer bottleNo) {
		this.bottleNo = bottleNo;
	}

	public Integer getSampleNo() {
		return sampleNo;
	}

	public void setSampleNo(Integer sampleNo) {
		this.sampleNo = sampleNo;
	}

	public Date getReferenceMemoDate() {
		return referenceMemoDate;
	}

	public void setReferenceMemoDate(Date referenceMemoDate) {
		this.referenceMemoDate = referenceMemoDate;
	}

	public String getReferenceMemoNo() {
		return referenceMemoNo;
	}

	public void setReferenceMemoNo(String referenceMemoNo) {
		this.referenceMemoNo = referenceMemoNo;
	}

	public Boolean getPendingSamples() {
		return pendingSamples;
	}



	public void setPendingSamples(Boolean pendingSamples) {
		this.pendingSamples = pendingSamples;
	}

	private Integer bottleNo;
	
	private Integer sampleNo;
	
	private Date referenceMemoDate;
	
	private String referenceMemoNo;
	
	
	private Boolean pendingSamples;



	public void setFreshOilReport(boolean b) {
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
