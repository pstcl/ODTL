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
//
//@Entity
//@Table(name = "REPORT_MASTER")
public class OilReport_OLD extends ODTLEntityMaster {
	
	@NumberFormat
	@Column
	private Double ageingCharacteristicsSpecificResistanceAt90Degree;


	@NumberFormat
	@Column
	private Double ageingCharacteristicsTanDeltaAt90Degree;

	@NumberFormat
	@Column
	private Double ageingCharacteristicsTotalAcidity;

	@NumberFormat
	@Column
	private Double ageingCharacteristicsTotalSludge;
	@Column
	private String appearance;
	@Column
	private Boolean approvalAEE;
	@Column
	private Boolean approvalASE;

	@Column
	private Boolean rejectionAEE;
	@Column
	private Boolean rejectionASE;
	@Column
	private String corrosiveSulphur;
	@NumberFormat
	@Column
	private Double density;
	@NumberFormat
	@Column
	private Double dgaTestAcetylene;
	@NumberFormat
	@Column
	private Double dgaTestCarbonDioxide;

	@NumberFormat
	@Column
	private Double dgaTestCarbonMonoxide;

	@NumberFormat
	@Column
	private Double dgaTestEthane;
	@NumberFormat
	@Column
	private Double dgaTestEthylene;
	@NumberFormat
	@Column
	private Double dgaTestHydrogen;
	@NumberFormat
	@Column
	private Double dgaTestMethane;
	@NumberFormat
	@Column
	private Double dgaTestNitrogen;
	@NumberFormat
	@Column
	private Double dgaTestOxygen;


	@NumberFormat
	@Column
	private Double dielectricStrengthBreakdownVoltage;

	@NumberFormat
	@Column
	private Double dielectricStrengthFrequency;

	@NumberFormat
	@Column
	private Double dielectricStrengthOilTemperatureDuringTest;

	@Column
	private String dielectricStrengthTypeOfElectrode;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EQUIPMENT", nullable = false)
	private Equipment equipment;
	@NumberFormat
	@Column
	private Double flastPointTest;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NumberFormat
	@Column
	private Double interFacialTensionAt27DegC;
	@Column
	private String memoNo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OIL_SAMPLE", nullable = false)
    private OilSample oilSample;
	@NumberFormat
	@Column
	private Double oxidationStabilityTestNeutralization;
	@NumberFormat
	@Column
	private Double oxidationStabilityTestTotalSludge;
	@NumberFormat
	@Column
	private Double pnaAromatic;
	@NumberFormat
	@Column
	private Double pnaNapthenic;
	@NumberFormat
	@Column
	private Double pnaParaffinic;
	@NumberFormat
	@Column
	private Double pourPoint;
	@Column
	private String presenceOfOxidationInhibitor;
	@Column(length = 500)
	private String remarks;
	@Column(length = 500)
	private String remarksAEE;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_AEE")
	private User reportAEE;
	@OneToOne(cascade=CascadeType.ALL)
		@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportAEEApprovalDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_ASE")
	private User reportASE;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportASEApprovalDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reportPreparationDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_PREPARED_BY", nullable = false)
	private User reportPreparedBy;
	
	@Column
	private String sampleCondition;
	@NumberFormat
	@Column
	private Double specificResistanceAt90Degree;
	
	@Column(length = 500)
	private String suggestionsASE;
	@NumberFormat
	@Column
	private Double tanDeltaAt90Degree;
	
	@NumberFormat
	@Column
	private Double totalAcidity;
	
	@NumberFormat
	@Column
	private Double viscosity;
	@NumberFormat
	@Column
	private Double waterContent;
	public Double getAgeingCharacteristicsSpecificResistanceAt90Degree() {
		return this.ageingCharacteristicsSpecificResistanceAt90Degree;
	}
	public Double getAgeingCharacteristicsTanDeltaAt90Degree() {
		return this.ageingCharacteristicsTanDeltaAt90Degree;
	}
	
	public Double getAgeingCharacteristicsTotalAcidity() {
		return this.ageingCharacteristicsTotalAcidity;
	}

	public Double getAgeingCharacteristicsTotalSludge() {
		return this.ageingCharacteristicsTotalSludge;
	}

	public String getAppearance() {
		return this.appearance;
	}
	public Boolean getApprovalAEE() {
		return this.approvalAEE;
	}
	public Boolean getApprovalASE() {
		return this.approvalASE;
	}
	public String getCorrosiveSulphur() {
		return this.corrosiveSulphur;
	}
	public Double getDensity() {
		return this.density;
	}
	public Double getDgaTestAcetylene() {
		return this.dgaTestAcetylene;
	}
	public Double getDgaTestCarbonDioxide() {
		return this.dgaTestCarbonDioxide;
	}
	public Double getDgaTestCarbonMonoxide() {
		return this.dgaTestCarbonMonoxide;
	}
	public Double getDgaTestEthane() {
		return this.dgaTestEthane;
	}
	public Double getDgaTestEthylene() {
		return this.dgaTestEthylene;
	}
	public Double getDgaTestHydrogen() {
		return this.dgaTestHydrogen;
	}
	public Double getDgaTestMethane() {
		return this.dgaTestMethane;
	}

	public Double getDgaTestNitrogen() {
		return this.dgaTestNitrogen;
	}

	public Double getDgaTestOxygen() {
		return this.dgaTestOxygen;
	}

	public Double getDielectricStrengthBreakdownVoltage() {
		return this.dielectricStrengthBreakdownVoltage;
	}

	public Double getDielectricStrengthFrequency() {
		return dielectricStrengthFrequency;
	}

	public Double getDielectricStrengthOilTemperatureDuringTest() {
		return this.dielectricStrengthOilTemperatureDuringTest;
	}

	public String getDielectricStrengthTypeOfElectrode() {
		return dielectricStrengthTypeOfElectrode;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public Double getFlastPointTest() {
		return this.flastPointTest;
	}

	public Integer getId() {
		return this.id;
	}

	public Double getInterFacialTensionAt27DegC() {
		return this.interFacialTensionAt27DegC;
	}

	public String getMemoNo() {
		return this.memoNo;
	}



	public OilSample getOilSample() {
		return this.oilSample;
	}

	public Double getOxidationStabilityTestNeutralization() {
		return this.oxidationStabilityTestNeutralization;
	}

	public Double getOxidationStabilityTestTotalSludge() {
		return this.oxidationStabilityTestTotalSludge;
	}

	public Double getPnaAromatic() {
		return this.pnaAromatic;
	}

	public Double getPnaNapthenic() {
		return this.pnaNapthenic;
	}

	public Double getPnaParaffinic() {
		return this.pnaParaffinic;
	}

	public Double getPourPoint() {
		return this.pourPoint;
	}

	public String getPresenceOfOxidationInhibitor() {
		return this.presenceOfOxidationInhibitor;
	}

	public Boolean getRejectionAEE() {
		return rejectionAEE;
	}

	public Boolean getRejectionASE() {
		return rejectionASE;
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

	
	public User getReportASE() {
		return this.reportASE;
	}

	public Date getReportASEApprovalDate() {
		return this.reportASEApprovalDate;
	}

	

	public Date getReportDate() {
		return reportDate;
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

	public Double getSpecificResistanceAt90Degree() {
		return this.specificResistanceAt90Degree;
	}

	public String getSuggestionsASE() {
		return this.suggestionsASE;
	}

	public Double getTanDeltaAt90Degree() {
		return this.tanDeltaAt90Degree;
	}

	public Double getTotalAcidity() {
		return this.totalAcidity;
	}

	public Double getViscosity() {
		return this.viscosity;
	}

	public Double getWaterContent() {
		return this.waterContent;
	}

	public void setAgeingCharacteristicsSpecificResistanceAt90Degree(
			final Double ageingCharacteristicsSpecificResistanceAt90Degree) {
		this.ageingCharacteristicsSpecificResistanceAt90Degree = ageingCharacteristicsSpecificResistanceAt90Degree;
	}

	public void setAgeingCharacteristicsTanDeltaAt90Degree(final Double ageingCharacteristicsTanDeltaAt90Degree) {
		this.ageingCharacteristicsTanDeltaAt90Degree = ageingCharacteristicsTanDeltaAt90Degree;
	}

	public void setAgeingCharacteristicsTotalAcidity(final Double ageingCharacteristicsTotalAcidity) {
		this.ageingCharacteristicsTotalAcidity = ageingCharacteristicsTotalAcidity;
	}

	public void setAgeingCharacteristicsTotalSludge(final Double ageingCharacteristicsTotalSludge) {
		this.ageingCharacteristicsTotalSludge = ageingCharacteristicsTotalSludge;
	}

	public void setAppearance(final String appearance) {
		this.appearance = appearance;
	}

	public void setApprovalAEE(final Boolean approvalAEE) {
		this.approvalAEE = approvalAEE;
	}

	public void setApprovalASE(final Boolean approvalASE) {
		this.approvalASE = approvalASE;
	}

	public void setCorrosiveSulphur(final String corrosiveSulphur) {
		this.corrosiveSulphur = corrosiveSulphur;
	}

	public void setDensity(final Double density) {
		this.density = density;
	}

	public void setDgaTestAcetylene(final Double dgaTestAcetylene) {
		this.dgaTestAcetylene = dgaTestAcetylene;
	}

	public void setDgaTestCarbonDioxide(final Double dgaTestCarbonDioxide) {
		this.dgaTestCarbonDioxide = dgaTestCarbonDioxide;
	}

	public void setDgaTestCarbonMonoxide(final Double dgaTestCarbonMonoxide) {
		this.dgaTestCarbonMonoxide = dgaTestCarbonMonoxide;
	}

	public void setDgaTestEthane(final Double dgaTestEthane) {
		this.dgaTestEthane = dgaTestEthane;
	}

	public void setDgaTestEthylene(final Double dgaTestEthylene) {
		this.dgaTestEthylene = dgaTestEthylene;
	}

	public void setDgaTestHydrogen(final Double dgaTestHydrogen) {
		this.dgaTestHydrogen = dgaTestHydrogen;
	}

	public void setDgaTestMethane(final Double dgaTestMethane) {
		this.dgaTestMethane = dgaTestMethane;
	}

	public void setDgaTestNitrogen(final Double dgaTestNitrogen) {
		this.dgaTestNitrogen = dgaTestNitrogen;
	}

	public void setDgaTestOxygen(final Double dgaTestOxygen) {
		this.dgaTestOxygen = dgaTestOxygen;
	}

	public void setDielectricStrengthBreakdownVoltage(final Double dielectricStrengthBreakdownVoltage) {
		this.dielectricStrengthBreakdownVoltage = dielectricStrengthBreakdownVoltage;
	}

	public void setDielectricStrengthFrequency(Double dielectricStrengthFrequency) {
		this.dielectricStrengthFrequency = dielectricStrengthFrequency;
	}

	public void setDielectricStrengthOilTemperatureDuringTest(final Double dielectricStrengthOilTemperatureDuringTest) {
		this.dielectricStrengthOilTemperatureDuringTest = dielectricStrengthOilTemperatureDuringTest;
	}

	public void setDielectricStrengthTypeOfElectrode(String dielectricStrengthTypeOfElectrode) {
		this.dielectricStrengthTypeOfElectrode = dielectricStrengthTypeOfElectrode;
	}

	public void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	public void setFlastPointTest(final Double flastPointTest) {
		this.flastPointTest = flastPointTest;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setInterFacialTensionAt27DegC(final Double interFacialTensionAt27DegC) {
		this.interFacialTensionAt27DegC = interFacialTensionAt27DegC;
	}

	public void setMemoNo(final String memoNo) {
		this.memoNo = memoNo;
	}

	public void setOilSample(final OilSample oilSample) {
		this.oilSample = oilSample;
	}

	public void setOxidationStabilityTestNeutralization(final Double oxidationStabilityTestNeutralization) {
		this.oxidationStabilityTestNeutralization = oxidationStabilityTestNeutralization;
	}

	public void setOxidationStabilityTestTotalSludge(final Double oxidationStabilityTestTotalSludge) {
		this.oxidationStabilityTestTotalSludge = oxidationStabilityTestTotalSludge;
	}

	public void setPnaAromatic(final Double pnaAromatic) {
		this.pnaAromatic = pnaAromatic;
	}

	public void setPnaNapthenic(final Double pnaNapthenic) {
		this.pnaNapthenic = pnaNapthenic;
	}

	public void setPnaParaffinic(final Double pnaParaffinic) {
		this.pnaParaffinic = pnaParaffinic;
	}

	public void setPourPoint(final Double pourPoint) {
		this.pourPoint = pourPoint;
	}

	public void setPresenceOfOxidationInhibitor(final String presenceOfOxidationInhibitor) {
		this.presenceOfOxidationInhibitor = presenceOfOxidationInhibitor;
	}

	public void setRejectionAEE(Boolean rejectionAEE) {
		this.rejectionAEE = rejectionAEE;
	}

	public void setRejectionASE(Boolean rejectionASE) {
		this.rejectionASE = rejectionASE;
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



	public void setReportASE(final User reportASE) {
		this.reportASE = reportASE;
	}

	public void setReportASEApprovalDate(final Date reportASEApprovalDate) {
		this.reportASEApprovalDate = reportASEApprovalDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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

	public void setSpecificResistanceAt90Degree(final Double specificResistanceAt90Degree) {
		this.specificResistanceAt90Degree = specificResistanceAt90Degree;
	}

	public void setSuggestionsASE(final String suggestionsASE) {
		this.suggestionsASE = suggestionsASE;
	}

	public void setTanDeltaAt90Degree(final Double tanDeltaAt90Degree) {
		this.tanDeltaAt90Degree = tanDeltaAt90Degree;
	}

	public void setTotalAcidity(final Double totalAcidity) {
		this.totalAcidity = totalAcidity;
	}

	public void setViscosity(final Double viscosity) {
		this.viscosity = viscosity;
	}

	public void setWaterContent(final Double waterContent) {
		this.waterContent = waterContent;
	}

	@Override
	public String toString() {
		return "OilReport [id=" + id + ", equipment=" + equipment + ", oilSample=" + oilSample + ", reportPreparedBy="
				+ reportPreparedBy + ", reportAEE=" + reportAEE + ", reportASE=" + reportASE
				+ ", reportPreparationDate=" + reportPreparationDate + ", reportDate=" + reportDate
				+ ", reportAEEApprovalDate=" + reportAEEApprovalDate + ", reportASEApprovalDate="
				+ reportASEApprovalDate + ", memoNo=" + memoNo + ", appearance=" + appearance
				+ ", dielectricStrengthBreakdownVoltage=" + dielectricStrengthBreakdownVoltage
				+ ", dielectricStrengthOilTemperatureDuringTest=" + dielectricStrengthOilTemperatureDuringTest
				+ ", dielectricStrengthFrequency=" + dielectricStrengthFrequency
				+ ", dielectricStrengthTypeOfElectrode=" + dielectricStrengthTypeOfElectrode + ", waterContent="
				+ waterContent + ", totalAcidity=" + totalAcidity + ", tanDeltaAt90Degree=" + tanDeltaAt90Degree
				+ ", specificResistanceAt90Degree=" + specificResistanceAt90Degree + ", flastPointTest="
				+ flastPointTest + ", viscosity=" + viscosity + ", pourPoint=" + pourPoint + ", density=" + density
				+ ", dgaTestAcetylene=" + dgaTestAcetylene + ", dgaTestCarbonDioxide=" + dgaTestCarbonDioxide
				+ ", dgaTestCarbonMonoxide=" + dgaTestCarbonMonoxide + ", dgaTestEthane=" + dgaTestEthane
				+ ", dgaTestEthylene=" + dgaTestEthylene + ", dgaTestMethane=" + dgaTestMethane + ", dgaTestHydrogen="
				+ dgaTestHydrogen + ", dgaTestNitrogen=" + dgaTestNitrogen + ", dgaTestOxygen=" + dgaTestOxygen
				+ ", interFacialTensionAt27DegC=" + interFacialTensionAt27DegC + ", pnaParaffinic=" + pnaParaffinic
				+ ", pnaNapthenic=" + pnaNapthenic + ", pnaAromatic=" + pnaAromatic
				+ ", oxidationStabilityTestNeutralization=" + oxidationStabilityTestNeutralization
				+ ", oxidationStabilityTestTotalSludge=" + oxidationStabilityTestTotalSludge
				+ ", ageingCharacteristicsSpecificResistanceAt90Degree="
				+ ageingCharacteristicsSpecificResistanceAt90Degree + ", ageingCharacteristicsTanDeltaAt90Degree="
				+ ageingCharacteristicsTanDeltaAt90Degree + ", ageingCharacteristicsTotalAcidity="
				+ ageingCharacteristicsTotalAcidity + ", ageingCharacteristicsTotalSludge="
				+ ageingCharacteristicsTotalSludge + ", corrosiveSulphur=" + corrosiveSulphur
				+ ", presenceOfOxidationInhibitor=" + presenceOfOxidationInhibitor + ", remarks=" + remarks
				+ ", remarksAEE=" + remarksAEE + ", suggestionsASE=" + suggestionsASE + ", approvalAEE=" + approvalAEE
				+ ", approvalASE=" + approvalASE + ", sampleCondition=" + sampleCondition + ", getDateEntered()="
				+ getDateEntered() + ", getAppUser()=" + getAppUser() + "]";
	}
	public OilReport_OLD updateOilReport(final OilReport_OLD oilReport) {
		this.reportPreparationDate = oilReport.reportPreparationDate;
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