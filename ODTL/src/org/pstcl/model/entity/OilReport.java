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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "report_master")
public class OilReport extends ODTLEntityMaster {


//	public ReportFile getReportFile() {
//		return reportFile;
//	}
//	public void setReportFile(ReportFile reportFile) {
//		this.reportFile = reportFile;
//	}



	@Column
	private String ageingCharacteristicsSpecificResistanceAt90Degree;


	
	
	@Column
	private String ageingCharacteristicsTanDeltaAt90Degree;


	@Column
	private String ageingCharacteristicsTotalAcidity;


	@Column
	private String ageingCharacteristicsTotalSludge;
	@Column
	private String appearance;
	@Column
	private Integer approvalAEE;
	@Column
	private Integer approvalASE;

	@Column
	private String corrosiveSulphur;
	@Column
	private String density;
	@Column
	private String dgaTestAcetylene;

	@Column
	private String dgaTestCarbonDioxide;

	@Column
	private String dgaTestCarbonMonoxide;

	@Column
	private String dgaTestEthane;


	@Column
	private String dgaTestEthylene;


	@Column
	private String dgaTestHydrogen;

	@Column
	private String dgaTestMethane;

	@Column
	private String dgaTestNitrogen;

	@Column
	private String dgaTestOxygen;

	@Column
	private String dielectricStrengthBreakdownVoltage;

	@Column
	private String dielectricStrengthFrequency;



	@Column
	private String dielectricStrengthOilTemperatureDuringTest;


	@Column
	private String dielectricStrengthTypeOfElectrode;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EQUIPMENT", nullable = false)
	private Equipment equipment;

	@Column
	private String flastPointTest;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String interFacialTensionAt27DegC;


	@Column
	private String memoNo;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OIL_SAMPLE", nullable = false)
	private OilSample oilSample;
	
//
//	@OneToOne(mappedBy="oilReport", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	private ReportFile reportFile;
//	
//	
	@Column
	private String oxidationStabilityTestNeutralization;


	@Column
	private String oxidationStabilityTestTotalSludge;

	@Column
	private String pnaAromatic;

	@Column
	private String pnaNapthenic;

	@Column
	private String pnaParaffinic;

	@Column
	private String pourPoint;

	@Column
	private String presenceOfOxidationInhibitor;


	@Column(length = 500)
	private String remarks;
	@Column(length = 500)
	private String remarksAEE;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_AEE")
	private User reportAEE;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportAEEApprovalDate;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "reportAEEDetails")
	private ReportOfficer reportAEEDetails;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_ASE")
	private User reportASE;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportASEApprovalDate;


	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "reportASEDetails")
	private ReportOfficer reportASEDetails;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportDate;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "reportJEDetails")
	private ReportOfficer reportJEDetails;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportPreparationDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_PREPARED_BY", nullable = false)
	private User reportPreparedBy;
	@Column
	private String sampleCondition;

	@Column
	private String specificResistanceAt90Degree;

	@Column(length = 500)
	private String suggestionsASE;

	@Column
	private String tanDeltaAt90Degree;


	@Column
	private String totalAcidity;


	@Column
	private String viscosity;

	@Column
	private String waterContent;
	
	
	
	//FOR ORDER BY CLAUSE on Sample No.
	//NOT A GOOD APPROACH, but saves joining tables in HQL, lets use Hibernate Criteria
	@NumberFormat
	private Integer sampleNo;
	
	
	public Integer getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(Integer sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getAgeingCharacteristicsSpecificResistanceAt90Degree() {
		return this.ageingCharacteristicsSpecificResistanceAt90Degree;
	}
	public String getAgeingCharacteristicsTanDeltaAt90Degree() {
		return this.ageingCharacteristicsTanDeltaAt90Degree;
	}

	public String getAgeingCharacteristicsTotalAcidity() {
		return this.ageingCharacteristicsTotalAcidity;
	}

	public String getAgeingCharacteristicsTotalSludge() {
		return this.ageingCharacteristicsTotalSludge;
	}

	public String getAppearance() {
		return this.appearance;
	}
	public Integer getApprovalAEE() {
		return this.approvalAEE;
	}
	public Integer getApprovalASE() {
		return this.approvalASE;
	}
	public String getCorrosiveSulphur() {
		return this.corrosiveSulphur;
	}
	public String getDensity() {
		return this.density;
	}
	public String getDgaTestAcetylene() {
		return this.dgaTestAcetylene;
	}
	public String getDgaTestCarbonDioxide() {
		return this.dgaTestCarbonDioxide;
	}
	public String getDgaTestCarbonMonoxide() {
		return this.dgaTestCarbonMonoxide;
	}
	public String getDgaTestEthane() {
		return this.dgaTestEthane;
	}
	public String getDgaTestEthylene() {
		return this.dgaTestEthylene;
	}
	public String getDgaTestHydrogen() {
		return this.dgaTestHydrogen;
	}
	public String getDgaTestMethane() {
		return this.dgaTestMethane;
	}

	public String getDgaTestNitrogen() {
		return this.dgaTestNitrogen;
	}

	public String getDgaTestOxygen() {
		return this.dgaTestOxygen;
	}

	public String getDielectricStrengthBreakdownVoltage() {
		return this.dielectricStrengthBreakdownVoltage;
	}

	public String getDielectricStrengthFrequency() {
		return dielectricStrengthFrequency;
	}

	public String getDielectricStrengthOilTemperatureDuringTest() {
		return this.dielectricStrengthOilTemperatureDuringTest;
	}

	public String getDielectricStrengthTypeOfElectrode() {
		return dielectricStrengthTypeOfElectrode;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public String getFlastPointTest() {
		return this.flastPointTest;
	}

	public Integer getId() {
		return this.id;
	}

	public String getInterFacialTensionAt27DegC() {
		return this.interFacialTensionAt27DegC;
	}

	public String getMemoNo() {
		return this.memoNo;
	}



	public OilSample getOilSample() {
		return this.oilSample;
	}

	public String getOxidationStabilityTestNeutralization() {
		return this.oxidationStabilityTestNeutralization;
	}

	public String getOxidationStabilityTestTotalSludge() {
		return this.oxidationStabilityTestTotalSludge;
	}

	public String getPnaAromatic() {
		return this.pnaAromatic;
	}

	public String getPnaNapthenic() {
		return this.pnaNapthenic;
	}

	public String getPnaParaffinic() {
		return this.pnaParaffinic;
	}

	public String getPourPoint() {
		return this.pourPoint;
	}

	public String getPresenceOfOxidationInhibitor() {
		return this.presenceOfOxidationInhibitor;
	}



	public String getRemarks() {
		return this.remarks;
	}

	public String getRemarksAEE() {
		return this.remarksAEE;
	}

	public User getReportAEE() {
		return this.reportAEE;
	}

	public Date getReportAEEApprovalDate() {
		return this.reportAEEApprovalDate;
	}

	public ReportOfficer getReportAEEDetails() {
		return reportAEEDetails;
	}

	public User getReportASE() {
		return this.reportASE;
	}

	public Date getReportASEApprovalDate() {
		return this.reportASEApprovalDate;
	}

	public ReportOfficer getReportASEDetails() {
		return reportASEDetails;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public ReportOfficer getReportJEDetails() {
		return reportJEDetails;
	}

	public Date getReportPreparationDate() {
		return this.reportPreparationDate;
	}

	public User getReportPreparedBy() {
		return this.reportPreparedBy;
	}

	public String getSampleCondition() {
		return this.sampleCondition;
	}

	public String getSpecificResistanceAt90Degree() {
		return this.specificResistanceAt90Degree;
	}

	public String getSuggestionsASE() {
		return this.suggestionsASE;
	}

	public String getTanDeltaAt90Degree() {
		return this.tanDeltaAt90Degree;
	}

	public String getTotalAcidity() {
		return this.totalAcidity;
	}

	public String getViscosity() {
		return this.viscosity;
	}

	public String getWaterContent() {
		return this.waterContent;
	}

	public void setAgeingCharacteristicsSpecificResistanceAt90Degree(
			final String ageingCharacteristicsSpecificResistanceAt90Degree) {
		this.ageingCharacteristicsSpecificResistanceAt90Degree = ageingCharacteristicsSpecificResistanceAt90Degree;
	}

	public void setAgeingCharacteristicsTanDeltaAt90Degree(final String ageingCharacteristicsTanDeltaAt90Degree) {
		this.ageingCharacteristicsTanDeltaAt90Degree = ageingCharacteristicsTanDeltaAt90Degree;
	}

	public void setAgeingCharacteristicsTotalAcidity(final String ageingCharacteristicsTotalAcidity) {
		this.ageingCharacteristicsTotalAcidity = ageingCharacteristicsTotalAcidity;
	}

	public void setAgeingCharacteristicsTotalSludge(final String ageingCharacteristicsTotalSludge) {
		this.ageingCharacteristicsTotalSludge = ageingCharacteristicsTotalSludge;
	}

	public void setAppearance(final String appearance) {
		this.appearance = appearance;
	}

	public void setApprovalAEE(final Integer approvalAEE) {
		this.approvalAEE = approvalAEE;
	}

	public void setApprovalASE(final Integer approvalASE) {
		this.approvalASE = approvalASE;
	}

	public void setCorrosiveSulphur(final String corrosiveSulphur) {
		this.corrosiveSulphur = corrosiveSulphur;
	}

	public void setDensity(final String density) {
		this.density = density;
	}

	public void setDgaTestAcetylene(final String dgaTestAcetylene) {
		this.dgaTestAcetylene = dgaTestAcetylene;
	}

	public void setDgaTestCarbonDioxide(final String dgaTestCarbonDioxide) {
		this.dgaTestCarbonDioxide = dgaTestCarbonDioxide;
	}

	public void setDgaTestCarbonMonoxide(final String dgaTestCarbonMonoxide) {
		this.dgaTestCarbonMonoxide = dgaTestCarbonMonoxide;
	}

	public void setDgaTestEthane(final String dgaTestEthane) {
		this.dgaTestEthane = dgaTestEthane;
	}

	public void setDgaTestEthylene(final String dgaTestEthylene) {
		this.dgaTestEthylene = dgaTestEthylene;
	}

	public void setDgaTestHydrogen(final String dgaTestHydrogen) {
		this.dgaTestHydrogen = dgaTestHydrogen;
	}

	public void setDgaTestMethane(final String dgaTestMethane) {
		this.dgaTestMethane = dgaTestMethane;
	}

	public void setDgaTestNitrogen(final String dgaTestNitrogen) {
		this.dgaTestNitrogen = dgaTestNitrogen;
	}

	public void setDgaTestOxygen(final String dgaTestOxygen) {
		this.dgaTestOxygen = dgaTestOxygen;
	}

	public void setDielectricStrengthBreakdownVoltage(final String dielectricStrengthBreakdownVoltage) {
		this.dielectricStrengthBreakdownVoltage = dielectricStrengthBreakdownVoltage;
	}

	public void setDielectricStrengthFrequency(String dielectricStrengthFrequency) {
		this.dielectricStrengthFrequency = dielectricStrengthFrequency;
	}

	public void setDielectricStrengthOilTemperatureDuringTest(final String dielectricStrengthOilTemperatureDuringTest) {
		this.dielectricStrengthOilTemperatureDuringTest = dielectricStrengthOilTemperatureDuringTest;
	}

	public void setDielectricStrengthTypeOfElectrode(String dielectricStrengthTypeOfElectrode) {
		this.dielectricStrengthTypeOfElectrode = dielectricStrengthTypeOfElectrode;
	}

	public void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	public void setFlastPointTest(final String flastPointTest) {
		this.flastPointTest = flastPointTest;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setInterFacialTensionAt27DegC(final String interFacialTensionAt27DegC) {
		this.interFacialTensionAt27DegC = interFacialTensionAt27DegC;
	}

	public void setMemoNo(final String memoNo) {
		this.memoNo = memoNo;
	}

	public void setOilSample(final OilSample oilSample) {
		this.oilSample = oilSample;
	}

	public void setOxidationStabilityTestNeutralization(final String oxidationStabilityTestNeutralization) {
		this.oxidationStabilityTestNeutralization = oxidationStabilityTestNeutralization;
	}

	public void setOxidationStabilityTestTotalSludge(final String oxidationStabilityTestTotalSludge) {
		this.oxidationStabilityTestTotalSludge = oxidationStabilityTestTotalSludge;
	}

	public void setPnaAromatic(final String pnaAromatic) {
		this.pnaAromatic = pnaAromatic;
	}

	public void setPnaNapthenic(final String pnaNapthenic) {
		this.pnaNapthenic = pnaNapthenic;
	}

	public void setPnaParaffinic(final String pnaParaffinic) {
		this.pnaParaffinic = pnaParaffinic;
	}

	public void setPourPoint(final String pourPoint) {
		this.pourPoint = pourPoint;
	}

	public void setPresenceOfOxidationInhibitor(final String presenceOfOxidationInhibitor) {
		this.presenceOfOxidationInhibitor = presenceOfOxidationInhibitor;
	}



	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	public void setRemarksAEE(final String remarksAEE) {
		this.remarksAEE = remarksAEE;
	}

	public void setReportAEE(final User reportAEE) {
		this.reportAEE = reportAEE;
	}

	public void setReportAEEApprovalDate(final Date reportAEEApprovalDate) {
		this.reportAEEApprovalDate = reportAEEApprovalDate;
	}

	public void setReportAEEDetails(ReportOfficer reportAEEDetails) {
		this.reportAEEDetails = reportAEEDetails;
	}

	public void setReportASE(final User reportASE) {
		this.reportASE = reportASE;
	}

	public void setReportASEApprovalDate(final Date reportASEApprovalDate) {
		this.reportASEApprovalDate = reportASEApprovalDate;
	}

	public void setReportASEDetails(ReportOfficer reportASEDetails) {
		this.reportASEDetails = reportASEDetails;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public void setReportJEDetails(ReportOfficer reportJEDetails) {
		this.reportJEDetails = reportJEDetails;
	}

	public void setReportPreparationDate(final Date reportPreparationDate) {
		this.reportPreparationDate = reportPreparationDate;
	}

	public void setReportPreparedBy(final User reportPreparedBy) {
		this.reportPreparedBy = reportPreparedBy;
	}

	public void setSampleCondition(final String sampleCondition) {
		this.sampleCondition = sampleCondition;
	}

	public void setSpecificResistanceAt90Degree(final String specificResistanceAt90Degree) {
		this.specificResistanceAt90Degree = specificResistanceAt90Degree;
	}

	public void setSuggestionsASE(final String suggestionsASE) {
		this.suggestionsASE = suggestionsASE;
	}

	public void setTanDeltaAt90Degree(final String tanDeltaAt90Degree) {
		this.tanDeltaAt90Degree = tanDeltaAt90Degree;
	}

	public void setTotalAcidity(final String totalAcidity) {
		this.totalAcidity = totalAcidity;
	}

	public void setViscosity(final String viscosity) {
		this.viscosity = viscosity;
	}

	public void setWaterContent(final String waterContent) {
		this.waterContent = waterContent;
	}


	@Override
	public String toString() {
		return "OilReport [ageingCharacteristicsSpecificResistanceAt90Degree="
				+ ageingCharacteristicsSpecificResistanceAt90Degree + ", ageingCharacteristicsTanDeltaAt90Degree="
				+ ageingCharacteristicsTanDeltaAt90Degree + ", ageingCharacteristicsTotalAcidity="
				+ ageingCharacteristicsTotalAcidity + ", ageingCharacteristicsTotalSludge="
				+ ageingCharacteristicsTotalSludge + ", appearance=" + appearance + ", approvalAEE=" + approvalAEE
				+ ", approvalASE=" + approvalASE + ", density=" + density + ", dgaTestAcetylene="
				+ dgaTestAcetylene + ", dgaTestCarbonDioxide=" + dgaTestCarbonDioxide + ", dgaTestCarbonMonoxide="
				+ dgaTestCarbonMonoxide + ", dgaTestEthane=" + dgaTestEthane + ", dgaTestEthylene=" + dgaTestEthylene
				+ ", dgaTestHydrogen=" + dgaTestHydrogen + ", dgaTestMethane=" + dgaTestMethane + ", dgaTestNitrogen="
				+ dgaTestNitrogen + ", dgaTestOxygen=" + dgaTestOxygen + ", dielectricStrengthBreakdownVoltage="
				+ dielectricStrengthBreakdownVoltage + ", dielectricStrengthFrequency=" + dielectricStrengthFrequency
				+ ", dielectricStrengthOilTemperatureDuringTest=" + dielectricStrengthOilTemperatureDuringTest
				+ ", dielectricStrengthTypeOfElectrode=" + dielectricStrengthTypeOfElectrode + ", equipment="
				+ equipment + ", flastPointTest=" + flastPointTest + ", id=" + id + ", interFacialTensionAt27DegC="
				+ interFacialTensionAt27DegC + ", memoNo=" + memoNo + ", oilSample=" + oilSample
				+ ", oxidationStabilityTestNeutralization=" + oxidationStabilityTestNeutralization
				+ ", oxidationStabilityTestTotalSludge=" + oxidationStabilityTestTotalSludge + ", pnaAromatic="
				+ pnaAromatic + ", pnaNapthenic=" + pnaNapthenic + ", pnaParaffinic=" + pnaParaffinic + ", pourPoint="
				+ pourPoint + ", presenceOfOxidationInhibitor=" + presenceOfOxidationInhibitor + ", remarks=" + remarks
				+ ", remarksAEE=" + remarksAEE + ", reportAEE=" + reportAEE + ", reportAEEDetails=" + reportAEEDetails
				+ ", reportAEEApprovalDate=" + reportAEEApprovalDate + ", reportASE=" + reportASE
				+ ", reportASEDetails=" + reportASEDetails + ", reportASEApprovalDate=" + reportASEApprovalDate
				+ ", reportDate=" + reportDate + ", reportPreparationDate=" + reportPreparationDate
				+ ", reportPreparedBy=" + reportPreparedBy + ", reportJEDetails=" + reportJEDetails
				+ ", sampleCondition=" + sampleCondition + ", specificResistanceAt90Degree="
				+ specificResistanceAt90Degree + ", suggestionsASE=" + suggestionsASE + ", tanDeltaAt90Degree="
				+ tanDeltaAt90Degree + ", totalAcidity=" + totalAcidity + ", viscosity=" + viscosity + ", waterContent="
				+ waterContent + "]";
	}



	public OilReport updateOilReport(final OilReport oilReport) {


		this.approvalAEE=oilReport.approvalAEE;
		this.approvalASE=oilReport.approvalASE;
		this.reportDate=oilReport.reportDate;
		this.memoNo = oilReport.memoNo;
		this.appearance = oilReport.appearance;
		this.dielectricStrengthBreakdownVoltage = oilReport.dielectricStrengthBreakdownVoltage;
		this.dielectricStrengthOilTemperatureDuringTest = oilReport.dielectricStrengthOilTemperatureDuringTest;
		this.waterContent = oilReport.waterContent;
		this.totalAcidity = oilReport.totalAcidity;
		this.tanDeltaAt90Degree = oilReport.tanDeltaAt90Degree;
		this.specificResistanceAt90Degree = oilReport.specificResistanceAt90Degree;
		this.flastPointTest = oilReport.flastPointTest;
		this.viscosity = oilReport.viscosity;
		this.pourPoint = oilReport.pourPoint;
		this.density = oilReport.density;
		this.dgaTestAcetylene = oilReport.dgaTestAcetylene;
		this.dgaTestCarbonDioxide = oilReport.dgaTestCarbonDioxide;
		this.dgaTestCarbonMonoxide = oilReport.dgaTestCarbonMonoxide;
		this.dgaTestEthane = oilReport.dgaTestEthane;
		this.dgaTestEthylene = oilReport.dgaTestEthylene;
		this.dgaTestMethane = oilReport.dgaTestMethane;
		this.dgaTestHydrogen = oilReport.dgaTestHydrogen;
		this.dgaTestNitrogen = oilReport.dgaTestNitrogen;
		this.dgaTestOxygen = oilReport.dgaTestOxygen;
		this.interFacialTensionAt27DegC = oilReport.interFacialTensionAt27DegC;
		this.pnaParaffinic = oilReport.pnaParaffinic;
		this.pnaNapthenic = oilReport.pnaNapthenic;
		this.pnaAromatic = oilReport.pnaAromatic;
		this.oxidationStabilityTestNeutralization = oilReport.oxidationStabilityTestNeutralization;
		this.oxidationStabilityTestTotalSludge = oilReport.oxidationStabilityTestTotalSludge;
		this.ageingCharacteristicsSpecificResistanceAt90Degree = oilReport.ageingCharacteristicsSpecificResistanceAt90Degree;
		this.ageingCharacteristicsTanDeltaAt90Degree = oilReport.ageingCharacteristicsTanDeltaAt90Degree;
		this.ageingCharacteristicsTotalAcidity = oilReport.ageingCharacteristicsTotalAcidity;
		this.ageingCharacteristicsTotalSludge = oilReport.ageingCharacteristicsTotalSludge;
		this.corrosiveSulphur = oilReport.corrosiveSulphur;
		this.presenceOfOxidationInhibitor = oilReport.presenceOfOxidationInhibitor;
		this.remarks = oilReport.remarks;
		this.sampleCondition = oilReport.sampleCondition;
		this.reportPreparationDate = new Date(System.currentTimeMillis());
		return this;
	}



}