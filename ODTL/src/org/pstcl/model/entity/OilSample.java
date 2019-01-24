package org.pstcl.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotBlank;
import org.pstcl.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.ibm.icu.text.SimpleDateFormat;

@Entity
@Table(name = "oilsample_master")
public class OilSample extends ODTLEntityMaster {

	public ReportFile getReportFile() {
		return reportFile;
	}
	public void setReportFile(ReportFile reportFile) {
		this.reportFile = reportFile;
	}


	@OneToOne(fetch = FetchType.EAGER, mappedBy="oilSample", cascade=CascadeType.ALL)
	private ReportFile reportFile;
	
	
	
	@Transient
	public StringUtil REPORT_TYPE;

	@Column
	private String sampleType;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EQUIPMENT", nullable = false)
	private Equipment equipment;





	
	
	@OneToOne(mappedBy="oilSample", cascade=CascadeType.ALL)
	private OilReport oilReport;


	@Column(name = "SampleTakenFrom")
	private String sampleTakenFrom;



	@Column
	private String samplingValve;

	@NumberFormat
	@Column(unique = true)
	private Integer sampleNo;


	@Column
	@NumberFormat
	private Integer bottleNo;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date sampleReceiptDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date referenceMemoDate;

	@NotBlank
	@Column
	private String referenceMemoNo;

	@Column
	private String sampleContainer;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sampleSender")
	private User sampleSentByUser;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "senderDetails")
	private ReportOfficer senderDetails;
	
	
	public OilSample(String oilReportRoutineOil) {
		super();
		this.sampleType=oilReportRoutineOil;
	}
	public OilSample() {
		super();
		this.sampleType=StringUtil.OIL_REPORT_ROUTINE_OIL;
	}
	public ReportOfficer getSenderDetails() {
		return senderDetails;
	}
	public void setSenderDetails(ReportOfficer senderDetails) {
		this.senderDetails = senderDetails;
	}
	//	@NotNull
	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "SAMPLE_CIRCLE", nullable = false)
	//	private Circle sampleSentByCircle;
	//	@NotNull
	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "SAMPLE_DIVISION", nullable = false)
	//	private Division sampleSentByDivision;
	//	@NotNull
	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "SAMPLE_SUBSTATION", nullable = false)
	//	private Substation sampleSentBySubstation;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OilSample other = (OilSample) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public Integer getBottleNo() {
		return bottleNo;
	}
	public Equipment getEquipment() {
		return this.equipment;
	}
	public Integer getId() {
		return this.id;
	}
	public OilReport getOilReport() {
		return oilReport;
	}
	public Date getReferenceMemoDate() {
		return this.referenceMemoDate;
	}

	public String getReferenceMemoNo() {
		return this.referenceMemoNo;
	}



	public  StringUtil getREPORT_TYPE() {
		return REPORT_TYPE;
	}



	public String getSampleContainer() {
		return this.sampleContainer;
	}




	public Integer getSampleNo() {
		return this.sampleNo;
	}
	public Date getSampleReceiptDate() {
		return this.sampleReceiptDate;
	}
	//	public Circle getSampleSentByCircle() {
	//		return sampleSentByCircle;
	//	}
	//
	//	public Division getSampleSentByDivision() {
	//		return sampleSentByDivision;
	//	}
	//
	//	public Substation getSampleSentBySubstation() {
	//		return sampleSentBySubstation;
	//	}
	public User getSampleSentByUser() {
		return sampleSentByUser;
	}

	public String getSampleTakenFrom() {
		return this.sampleTakenFrom;
	}

	public String getSampleType() {
		return sampleType;
	}

	public String getSamplingValve() {
		return this.samplingValve;
	}

	public void setBottleNo(Integer bottleNo) {
		this.bottleNo = bottleNo;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOilReport(OilReport oilReport) {
		this.oilReport = oilReport;
	}

	public void setReferenceMemoDate(Date referenceMemoDate) {
		this.referenceMemoDate = referenceMemoDate;
	}

	public void setReferenceMemoNo(String referenceMemoNo) {
		this.referenceMemoNo = referenceMemoNo;
	}

	public  void setREPORT_TYPE(StringUtil rEPORT_TYPE) {
		REPORT_TYPE = rEPORT_TYPE;
	}



	public void setSampleContainer(String sampleContainer) {
		this.sampleContainer = sampleContainer;
	}

	public void setSampleNo(Integer sampleNo) {
		this.sampleNo = sampleNo;
	}

	public void setSampleReceiptDate(Date sampleReceiptDate) {
		this.sampleReceiptDate = sampleReceiptDate;
	}

	//	public void setSampleSentByCircle(Circle sampleSentByCircle) {
	//		this.sampleSentByCircle = sampleSentByCircle;
	//	}
	//
	//	public void setSampleSentByDivision(Division sampleSentByDivision) {
	//		this.sampleSentByDivision = sampleSentByDivision;
	//	}
	//
	//	public void setSampleSentBySubstation(Substation sampleSentBySubstation) {
	//		this.sampleSentBySubstation = sampleSentBySubstation;
	//	}

	public void setSampleSentByUser(User sampleSentByUser) {
		this.sampleSentByUser = sampleSentByUser;
	}

	public void setSampleTakenFrom(String sampleTakenFrom) {
		this.sampleTakenFrom = sampleTakenFrom;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public void setSamplingValve(String samplingValve) {
		this.samplingValve = samplingValve;
	}
	public String getLabelForReportEntry()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return "Sample No:"+sampleNo +" Date "+ formatter.format(sampleReceiptDate) +" Sent By" + sampleSentByUser.toString()+","+sampleSentByUser.getSubstation().getSubstationDescription()+","+sampleSentByUser.getDivision().getDivisionDescription()+","+sampleSentByUser.getCircle().getCircleDescription();
	}

	@Override
	public String toString() {
		return "OilSample [REPORT_TYPE=" + REPORT_TYPE + ", sampleType=" + sampleType + ", id=" + id + ", equipment="
				+ equipment  + ", sampleTakenFrom=" + sampleTakenFrom + ", samplingValve="
				+ samplingValve + ", sampleNo=" + sampleNo + ", sampleReceiptDate=" + sampleReceiptDate
				+ ", referenceMemoDate=" + referenceMemoDate + ", referenceMemoNo=" + referenceMemoNo
				+ ", sampleContainer=" + sampleContainer + ",  sampleSentByUser=" + sampleSentByUser + ", getDateEntered()=" + getDateEntered()
				+ ", getAppUser()=" + getAppUser() + "]";
	}
	
	
	public OilSample updateOilSample(OilSample modifiedOilSample) {
		this.sampleTakenFrom = modifiedOilSample.sampleTakenFrom;
		this.samplingValve = modifiedOilSample.samplingValve;
		this.sampleNo = modifiedOilSample.sampleNo;
		this.sampleReceiptDate = modifiedOilSample.sampleReceiptDate;
		this.referenceMemoDate = modifiedOilSample.referenceMemoDate;
		this.referenceMemoNo = modifiedOilSample.referenceMemoNo;
		this.sampleContainer = modifiedOilSample.sampleContainer;
		this.bottleNo=modifiedOilSample.bottleNo;
		//moved to OIL Report
		//this.sampleCondition = modifiedOilSample.sampleCondition;
		return this;
	}


}