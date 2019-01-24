<%@page import="org.pstcl.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Report Entry Form</title>
<script type="text/javascript">
	function saveOrUpdate() {
		$('body').css('display', 'none');
		document.forms['oilReportForm'].submit();
	}

	function submitform() {
		$('body').css('display', 'none');
		document.forms['oilReportForm'].action = 'filterForOilReport';
		document.forms['oilReportForm'].submit();

	}
	function selectEquipment() {
		$('body').css('display', 'none');
		document.forms['oilReportForm'].action = 'setOilReportEquipment';
		document.forms['oilReportForm'].submit();

	}
	function selectOilSample() {
		$('body').css('display', 'none');
		document.forms['oilReportForm'].action = 'setOilReportSample';
		document.forms['oilReportForm'].submit();

	}
</script>

<script type="text/javascript">
	function setReportDate() {
		document.forms['oilReportForm'].action = 'setOilReportDate';
		document.forms['oilReportForm'].submit();
	}
</script>
</head>

<body style="margin: 0;">

	<%@include file="authheader.jsp"%>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="javascript:window.location='home'">Home</a></li>
			<li class="breadcrumb-item"><a
				href="<c:url value='/viewEquipment-${oilReport.equipment.id}'/>">Equipment</a></li>
			<li class="breadcrumb-item"><a
				href="<c:url value='/viewSample-${oilReport.oilSample.id}'/>">Oil
					Sample</a></li>
			<li class="breadcrumb-item active" aria-current="page">Add Test
				Report</li>
		</ol>
	</nav>
	<div class="container-fluid">
		<div class="well lead">Report Entry Form</div>

		<script type="text/javascript">
			$(function() {
				$('.date-picker').datepicker({
					yearRange : '2010:2100',
					changeMonth : true,
					changeYear : true,
					showButtonPanel : false,
					dateFormat : "dd/mm/yy"

				});
			});
		</script>
		<form:form id="oilReportForm" method="POST" modelAttribute="oilReport"
			action="saveOilReport" class="form-horizontal">
			
			
			<form:hidden path="id" />
			<form:hidden path="equipment.id" />

			<form:hidden path="reportPreparedBy.id" />
			<form:input id="approvalAEE" type="hidden" path="approvalAEE" />
			<form:input id="approvalASE" type="hidden" path="approvalASE" />


			<div class="card">
				<div class="card-heading">Report Details</div>
				<div class="card-body">

					<c:choose>
						<c:when test="${oilReport.oilSample.id == null}">
							<div class="row">
								<div class="form-control col-sm-4">
									<label class="col-sm-6 form-control-label"
										for="equipment.circle">Circle Name</label>
									<div class="col-sm-12">


										<form:select onchange="submitform()" id="circleDD"
											path="equipment.circle" multiple="false"
											class="form-control input-sm">
											<form:option value="" label="Select Circle"></form:option>
											<form:options items="${circleList}" itemValue="circleCode"
												itemLabel="circleDescription" />
										</form:select>

										<div class="has-error">
											<form:errors path="equipment.circle"
												class="alert alert-danger" />
										</div>
									</div>
								</div>

								<div class="form-control col-sm-4">
									<label class="col-sm-6 form-control-label"
										for="equipment.division">Division Name</label>
									<div class="col-sm-12">

										<form:select onchange="submitform()" path="equipment.division"
											multiple="false" class="form-control input-sm">
											<form:option value="" label="Select Division"></form:option>
											<form:options items="${divisionList}"
												itemValue="divisionCode" itemLabel="divisionDescription" />
										</form:select>
										<div>
											<form:errors path="equipment.division"
												class="alert alert-danger" />
										</div>
									</div>
								</div>

								<div class="form-control col-sm-4">
									<label class="col-sm-6 form-control-label"
										for="equipment.substation">Sub-station</label>
									<div class="col-sm-12">


										<form:select onchange="submitform()"
											path="equipment.substation" multiple="false"
											class="form-control input-sm">
											<form:option value="${null}" label="Select Substation"></form:option>
											<form:options items="${substationList}"
												itemValue="substationCode" itemLabel="substationDescription" />
										</form:select>
										<div>
											<form:errors path="equipment.substation"
												class="alert alert-danger" />
										</div>
									</div>
								</div>
								<div class="form-control col-sm-4">
									<label class="col-sm-6 form-control-label" for="oilSample">
										Sample No</label>
									<div class="col-sm-12">
										<form:select onchange="selectOilSample()" path="oilSample"
											multiple="false" class="form-control input-sm">
											<form:option value="${null}" label="Select Oil Sample"></form:option>
											<form:options items="${oilSampleList}" itemValue="id"
												itemLabel="labelForReportEntry" />
										</form:select>
										<div>
											<form:errors path="oilSample" class="alert alert-danger" />
										</div>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<form:hidden path="oilSample.id" />

							<table class="table table-striped table-bordered table-hover">
								<tr>
									<th style="text-align: center; font-size: 17px;" colspan="6">Equipment
										Details</th>
								</tr>
								<tr>

									<th>Circle</th>
									<td>${oilReport.equipment.circle.circleDescription}</td>


									<th>Division</th>
									<td>${oilReport.equipment.division.divisionDescription}</td>


									<th>Sub-Station</th>
									<td>${oilReport.equipment.substation.substationDescription}</td>
								</tr>
								<tr>
									<th>Voltage Class</th>
									<td>${oilReport.equipment.voltageClass}</td>
									<th>Capacity</th>
									<td>${oilReport.equipment.capacity}</td>
									<th>Cooling Type</th>
									<td>${oilReport.equipment.coolingType}</td>
								</tr>
								<tr>
									<th>Oil Type</th>
									<td>${oilReport.equipment.oilType}</td>
									<th>Type</th>
									<td>${oilReport.equipment.equipmentType}</td>

									<th>ID</th>
									<td>${oilReport.equipment.equipmentID}</td>
									
								</tr>
							</table>
							<table class="table table-striped table-bordered table-hover">

								<tr>
									<th style="text-align: center; font-size: 17px;" colspan="6">Sample
										Details</th>
								</tr>
								<tr>

									<th>Sample No.</th>
									<td>${oilReport.oilSample.sampleNo}</td>


									<th>Date of Receipt</th>
									<td><fmt:formatDate
											value="${oilReport.oilSample.sampleReceiptDate}"
											pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>

									<th>Sample Taken From</th>
									<td>${oilReport.oilSample.sampleTakenFrom}</td>

									<th>Sampling Valve</th>
									<td>${oilReport.oilSample.samplingValve}</td>


									<th>Sample Container</th>
									<td>${oilReport.oilSample.sampleContainer}</td>

								</tr>
								<tr>
									<th>Reference Memo No</th>
									<td>${oilReport.oilSample.referenceMemoNo}</td>
									<th>Sample Reference Date</th>
									<td><fmt:formatDate
											value="${oilReport.oilSample.referenceMemoDate}"
											pattern="dd/MM/yyyy" /></td>
									<td></td>
									<td></td>
								</tr>
							</table>
							<table class="table table-striped table-bordered table-hover">

								<tr>
									<th style="text-align: center; font-size: 17px;" colspan="6">User
										Details:-${oilReport.oilSample.sampleSentByUser}</th>

								</tr>
								<tr>

									<th>Sender Circle</th>
									<td>${oilReport.oilSample.sampleSentByUser.circle.circleDescription}</td>


									<th>Sender Division</th>
									<td>${oilReport.oilSample.sampleSentByUser.division.divisionDescription}</td>


									<th>Sender Sub-Station</th>
									<td>${oilReport.oilSample.sampleSentByUser.substation.substationDescription}</td>
							</table>

						</c:otherwise>
					</c:choose>
					<div class="row">

						<!-- 						<div class="form-control col-md-6"> -->
						<!-- 							<label class="col-md-3 form-control-label" for="reportPreparedBy"> -->
						<!-- 								Report Prepared By </label> -->
						<!-- 							<div class="col-md-6"> -->
						<!-- 								<label class="col-md-3 form-control-label" -->
						<!-- 									for="reportPreparedBy"> -->
						<%-- 									${oilReport.reportPreparedBy.ssoId}&nbsp;${oilReport.reportPreparedBy.firstName}&nbsp;${oilReport.reportPreparedBy.lastName} --%>
						<!-- 								</label> -->
						<!-- 								<div class="has-error"> -->
						<%-- 									<form:errors path="reportPreparedBy" class="help-inline" /> --%>
						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
						<div class="form-control col-md-4">
							<label class="col-md-5 form-control-label" for="reportDate">Date
								of Testing</label>
							<div class="col-md-7">
								<form:input type="text" cssClass="date-picker"
									onchange="setReportDate()" path="reportDate" id="reportDate" />
								<div class="has-error">
									<form:errors path="reportDate" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="memoNo">
								Report No. </label>
							<div class="col-md-6">
								<form:input type="text" path="memoNo" id="memoNo"
									class="form-control input-sm" />


								<div class="has-error">
									<form:errors path="memoNo" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="card">
				<div class="card-heading">1.Appearance</div>
				<div class="card-body">

					<div class="row">
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="appearance">
								1.1 Color </label>
							<div class="col-md-6">


								<form:select path="appearance" items="${appearanceList}"
									multiple="false" class="form-control input-sm" />


								<div class="has-error">
									<form:errors path="appearance" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-4">
							<label class="col-md-12 form-control-label" for="sampleCondition">1.2
								Condition of Sample </label>
							<div class="col-md-12">

								<form:select path="sampleCondition"
									items="${sampleConditionList}" multiple="false"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="sampleCondition" class="help-inline" />
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>

			<div class="card">
				<div class="card-heading">2.Dielectric Strength</div>
				<div class="card-body">

					<div class="row">

						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label"
								for="dielectricStrengthBreakdownVoltage">2.1 Breakdown
								Voltage</label>
							<div class="col-md-6">
								<form:input type="text"
									path="dielectricStrengthBreakdownVoltage"
									id="dielectricStrengthBreakdownVoltage"
									class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									KV</label>
								<div class="has-error">
									<form:errors path="dielectricStrengthBreakdownVoltage"
										class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label"
								for="dielectricStrengthOilTemperatureDuringTest">2.2 Oil
								Temperature During Test </label>
							<div class="col-md-6">
								<form:input type="text"
									path="dielectricStrengthOilTemperatureDuringTest"
									id="dielectricStrengthOilTemperatureDuringTest"
									class="form-control input-sm" />

								<!-- 									HIDDEN FIELDS  -->
								<form:input type="hidden" path="dielectricStrengthFrequency"
									id="dielectricStrengthFrequency" class="form-control input-sm"
									value="61.8" />

								<form:input type="hidden"
									path="dielectricStrengthTypeOfElectrode"
									id="dielectricStrengthTypeOfElectrode"
									class="form-control input-sm" value="Brass Spherical" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									<sup>o</sup>C
								</label>
								<div class="has-error">
									<form:errors path="dielectricStrengthOilTemperatureDuringTest"
										class="help-inline" />
								</div>
							</div>
						</div>
					</div>


				</div>

			</div>


			<div class="card">
				<div class="card-heading">Other Parameters</div>
				<div class="card-body">
					<div class="card-body">
						<div class="row">
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="waterContent">
									3.Water Content </label>
								<div class="col-md-6">
									<form:input type="text" path="waterContent" id="waterContent"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										ppm</label>
									<div class="has-error">
										<form:errors path="waterContent" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="totalAcidity">
									4.Total Acidity </label>
								<div class="col-md-6">
									<form:input type="text" path="totalAcidity" id="totalAcidity"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										mg KOH/g</label>
									<div class="has-error">
										<form:errors path="totalAcidity" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label"
									for="tanDeltaAt90Degree"> 5.Tan Delta at 90 Degree C </label>
								<div class="col-md-6">
									<form:input type="text" path="tanDeltaAt90Degree"
										id="tanDeltaAt90Degree" class="form-control input-sm" />

									<div class="has-error">
										<form:errors path="tanDeltaAt90Degree" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label"
									for="specificResistanceAt90Degree"> 6. Specific
									Resistance at 90 Degree C</label>
								<div class="col-md-6">
									<form:input type="text" path="specificResistanceAt90Degree"
										id="specificResistanceAt90Degree"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										X 10 <sup>12</sup> ohm-cm
									</label>
									<div class="has-error">
										<form:errors path="specificResistanceAt90Degree"
											class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="flastPointTest">
									7.Flash Point Test </label>
								<div class="col-md-6">
									<form:input type="text" path="flastPointTest"
										id="flastPointTest" class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										<sup>o</sup>C
									</label>
									<div class="has-error">
										<form:errors path="flastPointTest" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="viscosity">
									8.Viscosity </label>
								<div class="col-md-6">
									<form:input type="text" path="viscosity" id="viscosity"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										cSt</label>
									<div class="has-error">
										<form:errors path="viscosity" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="pourPoint">
									9.Pour Point </label>
								<div class="col-md-6">
									<form:input type="text" path="pourPoint" id="pourPoint"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										<sup>o</sup>C
									</label>
									<div class="has-error">
										<form:errors path="pourPoint" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="density">
									10.Density </label>
								<div class="col-md-6">
									<form:input type="text" path="density" id="density"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										g/cm<sup>3</sup>
									</label>
									<div class="has-error">
										<form:errors path="density" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-heading">11.DGA/TOGA</div>
				<div class="card-body">

					<div class="row">
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestAcetylene">11.1
								Acetylene C<sub>2</sub>H<sub>2</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestAcetylene"
									id="dgaTestAcetylene" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestAcetylene" class="help-inline" />
								</div>
							</div>
						</div>


						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label"
								for="dgaTestCarbonDioxide">11.2 Carbon Dioxide CO<sub>2</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestCarbonDioxide"
									id="dgaTestCarbonDioxide" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestCarbonDioxide" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label"
								for="dgaTestCarbonMonoxide">11.3 Carbon Monoxide CO </label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestCarbonMonoxide"
									id="dgaTestCarbonMonoxide" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestCarbonMonoxide" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestEthane">
								11.4. Ethane C<sub>2</sub>H<sub>6</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestEthane" id="dgaTestEthane"
									class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestEthane" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestEthylene">
								11.5 Ethylene C<sub>2</sub>H<sub>4</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestEthylene"
									id="dgaTestEthylene" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestEthylene" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestMethane">
								11.6 Methane CH<sub>4</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestMethane"
									id="dgaTestMethane" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestMethane" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestHydrogen">
								11.7 Hydrogen H<sub>2</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestHydrogen"
									id="dgaTestHydrogen" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestHydrogen" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestNitrogen">
								11.8 Nitrogen N<sub>2</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestNitrogen"
									id="dgaTestNitrogen" class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestNitrogen" class="help-inline" />
								</div>
							</div>
						</div>
						<div class="form-control col-md-6">
							<label class="col-md-3 form-control-label" for="dgaTestOxygen">
								11.9 Oxygen O<sub>2</sub>
							</label>
							<div class="col-md-6">
								<form:input type="text" path="dgaTestOxygen" id="dgaTestOxygen"
									class="form-control input-sm" />
								<label class="col-md-12 form-control-label" for="unitLabel">
									ppm</label>
								<div class="has-error">
									<form:errors path="dgaTestOxygen" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="card">
				<div class="card-heading">
					12. Inter-Facial Tension at 27.0 <sup>o</sup>C
				</div>
				<div class="card-body">
					<div class="card-body">
						<div class="row">
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label"
									for="interFacialTensionAt27DegC"> Inter-Facial Tension
									at 27.0 <sup>o</sup>C
								</label>
								<div class="col-md-6">
									<form:input type="text" path="interFacialTensionAt27DegC"
										id="interFacialTensionAt27DegC" class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										mN/m</label>
									<div class="has-error">
										<form:errors path="interFacialTensionAt27DegC"
											class="help-inline" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-heading">13. PNA Analysis</div>
				<div class="card-body">
					<div class="card-body">
						<div class="row">
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="pnaParaffinic">
									Paraffinic </label>
								<div class="col-md-6">
									<form:input type="text" path="pnaParaffinic" id="pnaParaffinic"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										Vol.percent</label>
									<div class="has-error">
										<form:errors path="pnaParaffinic" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="pnaNapthenic">
									Napthenic </label>
								<div class="col-md-6">
									<form:input type="text" path="pnaNapthenic" id="pnaNapthenic"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										Vol.percent</label>
									<div class="has-error">
										<form:errors path="pnaNapthenic" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-6">
								<label class="col-md-3 form-control-label" for="pnaAromatic">
									Aromatic </label>
								<div class="col-md-6">
									<form:input type="text" path="pnaAromatic" id="pnaAromatic"
										class="form-control input-sm" />
									<label class="col-md-12 form-control-label" for="unitLabel">
										Vol.percent</label>
									<div class="has-error">
										<form:errors path="pnaAromatic" class="help-inline" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%-- 			<c:if --%>
			<%-- 				test="${oilReport.oilSample.sampleType ==  StringUtil.OIL_REPORT_NEW_OIL}"> --%>

			<c:if
				test="${oilReport.oilSample.sampleType eq 'OIL_REPORT_NEW_OIL'}">

				<div class="card">
					<div class="card-heading">
						14. Oxidation Stability Test(164 Hours at 100 <sup>o</sup>C)
					</div>
					<div class="card-body">
						<div class="card-body">
							<div class="row">
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="oxidationStabilityTestNeutralization">Neutralization
										Value </label>
									<div class="col-md-6">
										<form:input type="text"
											path="oxidationStabilityTestNeutralization"
											id="oxidationStabilityTestNeutralization"
											class="form-control input-sm" />
										<label class="col-md-12 form-control-label" for="unitLabel">
											KOH/g</label>
										<div class="has-error">
											<form:errors path="oxidationStabilityTestNeutralization"
												class="help-inline" />
										</div>
									</div>

								</div>
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="oxidationStabilityTestTotalSludge"> Total Sludge
									</label>
									<div class="col-md-6">
										<form:input type="text"
											path="oxidationStabilityTestTotalSludge"
											id="oxidationStabilityTestTotalSludge"
											class="form-control input-sm" />
										<label class="col-md-12 form-control-label" for="unitLabel">
											%age Weight</label>
										<div class="has-error">
											<form:errors path="oxidationStabilityTestTotalSludge"
												class="help-inline" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-heading">15. Aging Characteristics Test(96
						Hours with Copper Catalyst)</div>
					<div class="card-body">
						<div class="card-body">
							<div class="row">
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="ageingCharacteristicsSpecificResistanceAt90Degree">
										Specific Resistance at 90 <sup>o</sup> C
									</label>
									<div class="col-md-6">
										<form:input type="text"
											path="ageingCharacteristicsSpecificResistanceAt90Degree"
											id="ageingCharacteristicsSpecificResistanceAt90Degree"
											class="form-control input-sm" />
										<label class="col-md-12 form-control-label" for="unitLabel">
											X 10 <sup>12</sup> ohm-cm
										</label>
										<div class="has-error">
											<form:errors
												path="ageingCharacteristicsSpecificResistanceAt90Degree"
												class="help-inline" />
										</div>
									</div>
								</div>
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="ageingCharacteristicsTanDeltaAt90Degree"> Tan
										Delta at 90 <sup>o</sup> C
									</label>
									<div class="col-md-6">
										<form:input type="text"
											path="ageingCharacteristicsTanDeltaAt90Degree"
											id="ageingCharacteristicsTanDeltaAt90Degree"
											class="form-control input-sm" />

										<div class="has-error">
											<form:errors path="ageingCharacteristicsTanDeltaAt90Degree"
												class="help-inline" />
										</div>
									</div>
								</div>
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="ageingCharacteristicsTotalAcidity"> Total Acidity
									</label>
									<div class="col-md-6">
										<form:input type="text"
											path="ageingCharacteristicsTotalAcidity"
											id="ageingCharacteristicsTotalAcidity"
											class="form-control input-sm" />
										<label class="col-md-12 form-control-label" for="unitLabel">
											Mg KOH/g</label>
										<div class="has-error">
											<form:errors path="ageingCharacteristicsTotalAcidity"
												class="help-inline" />
										</div>
									</div>
								</div>
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="ageingCharacteristicsTotalSludge"> Total Sludge </label>
									<div class="col-md-6">
										<form:input type="text"
											path="ageingCharacteristicsTotalSludge"
											id="ageingCharacteristicsTotalSludge"
											class="form-control input-sm" />
										<label class="col-md-12 form-control-label" for="unitLabel">
											%age weight</label>
										<div class="has-error">
											<form:errors path="ageingCharacteristicsTotalSludge"
												class="help-inline" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-heading">Other Parameters</div>
					<div class="card-body">
						<div class="card-body">
							<div class="row">
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="corrosiveSulphur">16. Corrosive Sulphur </label>
									<div class="col-md-6">

										<form:radiobuttons path="corrosiveSulphur"
											items="${corrosiveSulphurList}" />

										<div class="has-error">
											<form:errors path="corrosiveSulphur" class="help-inline" />
										</div>
									</div>
								</div>
								<div class="form-control col-md-6">
									<label class="col-md-3 form-control-label"
										for="presenceOfOxidationInhibitor">17. Presence of
										Oxidation Inhibitor </label>
									<div class="col-md-6">
										<form:radiobuttons path="presenceOfOxidationInhibitor"
											items="${oxidationInhibitorList}" />

										<div class="has-error">
											<form:errors path="presenceOfOxidationInhibitor"
												class="help-inline" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


			</c:if>

			<div class="card">
				<div class="card-heading">Remarks</div>

				<div class="card-body">
					<div class="row">
						<div class="form-control col-md-12">
							<label class="col-md-3 form-control-label" for="remarks">
								Remarks by JE </label>
							<div class="col-md-9">
								<form:textarea path="remarks" id="remarks"
									class="form-control input-sm" />

								<div class="has-error">
									<form:errors path="remarks" class="help-inline" />
								</div>
								<div class="form-control col-md-12">
									<c:if test="${ not empty oilReport.remarksAEE}">
										<div class="row">
											<span> <i class="fa fa-warning"
												style="font-size: 18px; color: Orange"> Remarks by
													AEE,ODTL:- ${oilReport.remarksAEE}</i>
											</span>
										</div>
									</c:if>

									<c:if test="${ not empty oilReport.suggestionsASE}">
										<div class="row">
											<span> <i class="fa fa-warning"
												style="font-size: 18px; color: Red">Remarks by
													ASE,Protection and OS:- ${oilReport.suggestionsASE} </i>
											</span>
										</div>
									</c:if>
								</div>
							</div>
						</div>
						<sec:authorize access="hasAnyRole('AEE','ASE')">
							<div class="form-control col-md-12">
								<label class="col-md-3 form-control-label" for="remarksAEE">
									Remarks by AEE </label>
								<div class="col-md-9">
									<form:textarea path="remarksAEE" id="remarksAEE"
										class="form-control input-sm" />

									<div class="has-error">
										<form:errors path="remarksAEE" class="help-inline" />
									</div>
								</div>
							</div>
							<sec:authorize access="hasAnyRole('AEE')">
								<div class="form-control col-md-12">
									<c:if test="${oilReport.approvalASE ne 100}">
										<c:if test="${oilReport.approvalAEE ne 100}">
											<button type="button" onclick="approvalAEEFunc(-10)"
												class="btn btn-success">Save Remarks</button>
										</c:if>

										<button type="button" id="aeeApproveBtn" onclick="approvalAEEFunc(100)"
											class="btn btn-success">Save & Approve</button>

										<button type="button" id="aeeRejectBtn" onclick="approvalAEEFunc(-100)"
											class="btn btn-danger">Reject Report</button>
									</c:if>
								</div>
							</sec:authorize>
						</sec:authorize>
						<sec:authorize access="hasRole('ASE')">
							<div class="form-control col-md-12">
								<label class="col-md-3 form-control-label" for="suggestionsASE">
									Suggestions by ASE</label>
								<div class="col-md-9">
									<form:textarea path="suggestionsASE" id="suggestionsASE"
										class="form-control input-sm" />

									<div class="has-error">
										<form:errors path="suggestionsASE" class="help-inline" />
									</div>
								</div>
							</div>
							<div class="form-control col-md-12">
								<c:if test="${oilReport.approvalASE ne 100}">
									<c:if test="${oilReport.approvalAEE eq 100}">
										<button type="button" onclick="approvalASEFunc(-10)"
											class="btn btn-success">Save Remarks</button>



										<button type="button" id="aseApproveBtn" onclick="approvalASEFunc(100)"
											class="btn btn-success">Save & Approve</button>

										<button type="button" id="aseRejectBtn" onclick="approvalASEFunc(-100)"
											class="btn btn-danger">Reject Report</button>
									</c:if>
								</c:if>
							</div>
						</sec:authorize>
					</div>
				</div>
			</div>

			<sec:authorize access="hasRole('JE')">
				<div class="row">
					<div class="form-actions floatRight">
						<c:choose>
							<c:when test="${edit}">



								<input type="button" id="jeUpdateBtn" onClick="saveOrUpdate()" value="Update"
									class="btn btn-primary btn-sm" /> or <a
									href="<c:url value='/oilReportsList' />">Cancel</a>
							</c:when>
							<c:otherwise>
								<input type="button" id="jeUpdateBtn" onClick="saveOrUpdate()" value="Save"
									class="btn btn-primary btn-sm" /> or <a
									href="<c:url value='/listOilReport' />">Cancel</a>
							</c:otherwise>
						</c:choose>
					</div>

				</div>
			</sec:authorize>
		</form:form>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>