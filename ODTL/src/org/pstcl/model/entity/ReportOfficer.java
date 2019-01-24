package org.pstcl.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

//CLASS CREATED TO DELINK USER TABLE AND DETAILS OF OFFICERS REQUIRED IN REPORTS
//NOT BEING USED AS ODTL DECIDED NOT TO USE NAME OR EMPLOYEE CODES BUT ONLY POSTS
@Entity
@Table(name = "report_officer")
public class ReportOfficer extends ODTLEntityMaster{
	

	public ReportOfficer()
	{
		super();
	}
	public ReportOfficer(User sampleSentByUser,Class name) {
		super();
		this.designation =sampleSentByUser.getDesignation().getDesignation();
		this.email =sampleSentByUser.getEmail();
		this.firstName =sampleSentByUser.getFirstName();
		this.lastName =sampleSentByUser.getLastName();
		this.ssoId =sampleSentByUser.getSsoId();
		this.circleCode =sampleSentByUser.getCircle().getCircleCode();
		this.circleDescription =sampleSentByUser.getCircle().getCircleDescription();
		this.divisionCode =sampleSentByUser.getDivision().getDivisionCode();
		this.divisionDescription =sampleSentByUser.getDivision().getDivisionDescription();
		this.substationCode =sampleSentByUser.getSubstation().getSubstationCode();
		this.substationDescription =sampleSentByUser.getSubstation().getSubstationDescription();
		this.entityDetails=name.getName();
	}
	
	public ReportOfficer updateReportOfficer(User sampleSentByUser)
	{
		this.designation =sampleSentByUser.getDesignation().getDesignation();
		this.email =sampleSentByUser.getEmail();
		this.firstName =sampleSentByUser.getFirstName();
		this.lastName =sampleSentByUser.getLastName();
		this.ssoId =sampleSentByUser.getSsoId();
		this.circleCode =sampleSentByUser.getCircle().getCircleCode();
		this.circleDescription =sampleSentByUser.getCircle().getCircleDescription();
		this.divisionCode =sampleSentByUser.getDivision().getDivisionCode();
		this.divisionDescription =sampleSentByUser.getDivision().getDivisionDescription();
		this.substationCode =sampleSentByUser.getSubstation().getSubstationCode();
		this.substationDescription =sampleSentByUser.getSubstation().getSubstationDescription();
		return this;
		
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String designation;

	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;


	@NotEmpty
	@Column(name = "USER_ID", nullable = false)
	private String ssoId;

	@NotEmpty
	@Column(name = "ENTITY_DETAILS", nullable = false)
	private String entityDetails;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSsoId() {
		return ssoId;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	public String getCircleCode() {
		return circleCode;
	}
	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}
	public String getCircleDescription() {
		return circleDescription;
	}
	public void setCircleDescription(String circleDescription) {
		this.circleDescription = circleDescription;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionDescription() {
		return divisionDescription;
	}
	public void setDivisionDescription(String divisionDescription) {
		this.divisionDescription = divisionDescription;
	}
	public String getSubstationCode() {
		return substationCode;
	}
	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}
	public String getSubstationDescription() {
		return substationDescription;
	}
	public void setSubstationDescription(String substationDescription) {
		this.substationDescription = substationDescription;
	}


	@Column(name = "CR_CODE")
	private String circleCode;
	@Column(name = "CIRCLE_DESC")
	private String circleDescription;


	@Column(name = "DIV_CODE")
	private String divisionCode;
	@Column(name = "DIV_DESC")
	private String divisionDescription;


	@Column(name = "SS_CODE")
	private String substationCode;
	@Column(name = "SS_DESC")
	private String substationDescription;
}
