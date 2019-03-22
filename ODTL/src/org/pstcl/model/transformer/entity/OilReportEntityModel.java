package org.pstcl.model.transformer.entity;

import java.util.Date;

import javax.persistence.Column;

import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilSample;
import org.springframework.format.annotation.DateTimeFormat;

public class OilReportEntityModel {


	//approvalAEE approvalASE reportDate oilSample equipment id
	
	private Integer approvalAEE;
	private Integer approvalASE;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportDate;
	private Integer id;
	private OilSample oilSample;
	private Equipment equipment;
	public Integer getApprovalAEE() {
		return approvalAEE;
	}
	public void setApprovalAEE(Integer approvalAEE) {
		this.approvalAEE = approvalAEE;
	}
	public Integer getApprovalASE() {
		return approvalASE;
	}
	public void setApprovalASE(Integer approvalASE) {
		this.approvalASE = approvalASE;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public OilSample getOilSample() {
		return oilSample;
	}
	public void setOilSample(OilSample oilSample) {
		this.oilSample = oilSample;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


}
