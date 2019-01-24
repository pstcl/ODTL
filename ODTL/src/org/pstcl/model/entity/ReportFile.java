package org.pstcl.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Report_File_master")
public class ReportFile {


//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "OIL_REPORT", nullable = false)
//	private OilReport oilReport;
//

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OIL_SAMPLE", nullable = false)
	private OilSample oilSample;
//
//	@Lob
//	@Column(name="REPORT_PDF_FILE", nullable=false, columnDefinition="mediumblob")
//	private byte[] pdfFile;
//	
	
	@Transient
	private byte[] pdfFile;

	
	
	public byte[] getPdfFile() {
		return pdfFile;
	}
	public void setPdfFile(byte[] pdfFile) {
		this.pdfFile = pdfFile;
	}




	@Column(name="REPORT_PDF_FILE_NAME", nullable=false)
	private String pdfFileName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EQUIPMENT", nullable = false)
	private Equipment equipment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sampleSender")
	private User sampleSentByUser;

	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public ReportFile()
	{
		super();
	}
	public ReportFile(OilReport oilReport2, OilSample oilSample2, Equipment equipment2) {
		super();
		//this.oilReport=oilReport2;
		this.equipment=equipment2;
		this.oilSample=oilSample2;
		this.sampleSentByUser=oilSample2.getSampleSentByUser();
	}

	

	public Integer getId() {
		return id;
	}

//	public OilReport getOilReport() {
//		return oilReport;
//	}

	public OilSample getOilSample() {
		return oilSample;
	}

	

	public User getSampleSentByUser() {
		return sampleSentByUser;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setId(Integer id) {
		this.id = id;
	}
//
//	public void setOilReport(OilReport oilReport) {
//		this.oilReport = oilReport;
//	}

	public void setOilSample(OilSample oilSample) {
		this.oilSample = oilSample;
	}


	

	public void setSampleSentByUser(User sampleSentByUser) {
		this.sampleSentByUser = sampleSentByUser;
	}


}
