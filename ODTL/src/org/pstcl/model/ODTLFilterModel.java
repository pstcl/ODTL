package org.pstcl.model;

import java.util.Date;

import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.OilSample;

public class ODTLFilterModel {
	
	
	private Boolean finalReport;
	private Boolean aseApproval;
	private Boolean aeeApproval;
	private Boolean rejectedReport;
	private Equipment equipment;
	private OilSample oilSample;
	private OilReport oilReport;

	private Date startDate;

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	private Date endDate;


	public ODTLFilterModel()
	{
		super();
	}
	public ODTLFilterModel(Equipment equipment, OilSample oilSample, OilReport oilReport) {
		super();
		this.equipment = equipment;
		this.oilSample = oilSample;
		this.oilReport = oilReport;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	public OilSample getOilSample() {
		return this.oilSample;
	}

	public void setOilSample(final OilSample oilSample) {
		this.oilSample = oilSample;
	}

	public OilReport getOilReport() {
		return this.oilReport;
	}

	public void setOilReport(final OilReport oilReport) {
		this.oilReport = oilReport;
	}
	public Boolean getFinalReport() {
		return finalReport;
	}
	public void setFinalReport(Boolean finalReport) {
		this.finalReport = finalReport;
	}
	public Boolean getRejectedReport() {
		return rejectedReport;
	}
	public void setRejectedReport(Boolean rejectedReport) {
		this.rejectedReport = rejectedReport;
	}
	public Boolean getAseApproval() {
		return aseApproval;
	}
	public void setAseApproval(Boolean aseApproval) {
		this.aseApproval = aseApproval;
	}
	public Boolean getAeeApproval() {
		return aeeApproval;
	}
	public void setAeeApproval(Boolean aeeApproval) {
		this.aeeApproval = aeeApproval;
	}
}