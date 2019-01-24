<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Users List</title>
</head>

<body style="margin: 0;">
	<%@include file="authheader.jsp"%>

	<div class="container-fluid">






		<div class="card card-default">



			<div class="card">
				<div class="row">


					<div class="card" style="width: 18rem;">
						<div class="card-body">
							<h5 class="app-title">Location</h5>
							<table class="table table-hover">


								<tr>

									<th>Circle</th>
									<td>${oilReport.equipment.circle.circleDescription}</td>
								</tr>
								<tr>

									<th>Division</th>
									<td>${oilReport.equipment.division.divisionDescription}</td>
								</tr>
								<tr>

									<th>Sub-Station</th>
									<td>${oilReport.equipment.substation.substationDescription}</td>
								</tr>
							</table>
						</div>
					</div>


					<div class="card" style="width: 18rem;">
						<div class="card-body">
							<h5 class="app-title">Equipment Details</h5>
							<table class="table table-hover card-table">



								<tr>
									<th>Equipment Type</th>
									<td>${oilReport.equipment.equipmentType}</td>
								</tr>
								<tr>
									<th>Equipment ID</th>
									<td>${oilReport.equipment.equipmentID}</td>
								</tr>
								<tr>
									<th>Make</th>
									<td>${oilReport.equipment.make}</td>
								</tr>
								<tr>
									<th>Serial No.</th>
									<td>${oilReport.equipment.serialNo}</td>
								</tr>
								<tr>
									<th>Year of Mfg.</th>
									<td>${oilReport.equipment.yearOfMfg}</td>
								</tr>
								<tr>
									<th>Voltage Class</th>
									<td>${oilReport.equipment.voltageClass}</td>
								</tr>
								<tr>
									<th>Capacity</th>
									<td>${oilReport.equipment.capacity}</td>
								</tr>
								<tr>
									<th>Cooling Type</th>
									<td>${oilReport.equipment.coolingType}</td>
								</tr>
								<tr>
									<th>Oil Type</th>
									<td>${oilReport.equipment.oilType}</td>
								</tr>
							</table>
						</div>
					</div>


					<div class="card" style="width: 18rem;">

						<div class="card-body">
							<h5 class="app-title">Oil Sample Details</h5>
							<table class="table table-hover card-table">


								<tr>

									<th>Sample Taken From</th>
									<td>${oilReport.oilSample.sampleTakenFrom}</td>
								</tr>
								<tr>

									<th>Sampling Valve</th>
									<td>${oilReport.oilSample.samplingValve}</td>
								</tr>
								<tr>

									<th>Sample No.</th>
									<td>${oilReport.oilSample.sampleNo}</td>
								</tr>
								<tr>

									<th>Date of Receipt of Sample</th>
									<td><fmt:formatDate
											value="${oilReport.oilSample.sampleReceiptDate}"
											pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>

									<th>Reference Memo No.</th>
									<td><fmt:formatDate
											value="${oilReport.oilSample.referenceMemoDate}"
											pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>

									<th>Reference Memo Date</th>
									<td>${oilReport.oilSample.referenceMemoNo}</td>
								</tr>
								<tr>

									<th>Sample Container</th>
									<td>${oilReport.oilSample.sampleContainer}</td>
								</tr>
								<tr>

									<th>Sample Condition</th>
									<td>${oilReport.oilSample.sampleCondition}</td>
								</tr>

							</table>
						</div>
					</div>





					<div class="card" style="width: 18rem;">

						<div class="card-body">
							<h5 class="app-title">Report Details</h5>
							<table class="table table-hover">
								<tr>

									<th>Report Prepared By</th>
									<td>${oilReport.reportPreparedBy}</td>
								</tr>

								<tr>

									<th>Approved by (AEE)</th>
									<td>${oilReport.reportAEE}</td>
								</tr>
								<tr>

									<th>Date of Approval of Report by AEE</th>
									<td>${oilReport.reportAEEApprovalDate}</td>
								</tr>

								<tr>

									<th>Seen by (ASE)</th>
									<td>${oilReport.reportASE}</td>
								</tr>


								<tr>

									<th>Date of Approval of Report by ASE</th>
									<td>${oilReport.reportASEApprovalDate}</td>
								</tr>



								<tr>

									<th>Report Preparation Date</th>
									<td><fmt:formatDate
											value="${oilReport.reportPreparationDate}"
											pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>

									<th>Memo No.</th>
									<td>${oilReport.memoNo}</td>
								</tr>




							</table>
						</div>
					</div>
				</div>

				<div class="card">

					<div class="card-body">
						<h5 class="app-title">Test Results</h5>
						<table class="table report-table table-hover">


							<tr>
								<th>Sr. No.</th>
								<th>Parameter</th>
								<th>Measuring Unit</th>
								<th>Measured Value</th>
							</tr>

							<tr>

								<td>1</td>
								<th>Appearance</th>
								<td></td>
								<td>${oilReport.appearance}</td>
							</tr>

							<tr>

								<th colspan="4">2. Dielectric Strength</th>

							</tr>
							<tr>

								<td></td>
								<th>Breakdown Voltage</th>
								<td></td>
								<td>${oilReport.dielectricStrengthBreakdownVoltage}</td>
							</tr>
							<tr>

								<td></td>
								<th>Dielectric Strength Oil Temperature during Test</th>
								<td></td>
								<td>${oilReport.dielectricStrengthOilTemperatureDuringTest}</td>
							</tr>
							<tr>

								<td>3</td>
								<th>Water Content</th>
								<td></td>
								<td>${oilReport.waterContent}</td>
							</tr>
							<tr>

								<td>4</td>
								<th>Total Acidity</th>
								<td></td>
								<td>${oilReport.totalAcidity}</td>
							</tr>
							<tr>

								<td>5</td>
								<th>Tan Delta at 90 <sup>o</sup></th>
								<td></td>
								<td>${oilReport.tanDeltaAt90Degree}</td>
							</tr>
							<tr>

								<td>6</td>
								<th>Specific Resistance at 90 <sup>o</sup></th>
								<td></td>
								<td>${oilReport.specificResistanceAt90Degree}</td>
							</tr>
							<tr>

								<td>7</td>
								<th>Flash Point Test</th>
								<td></td>
								<td>${oilReport.flastPointTest}</td>
							</tr>
							<tr>

								<td>8</td>
								<th>Viscosity</th>
								<td></td>
								<td>${oilReport.viscosity}</td>
							</tr>
							<tr>

								<td>9</td>
								<th>Pour Point</th>
								<td></td>
								<td>${oilReport.pourPoint}</td>
							</tr>
							<tr>

								<td>10</td>
								<th>Density</th>
								<td></td>
								<td>${oilReport.density}</td>
							</tr>
							<tr>
								<th colspan="3">11 DGA Test</th>

							</tr>
							<tr>


								<td>11(a)</td>
								<th>Acetylene</th>
								<td></td>
								<td>${oilReport.dgaTestAcetylene}</td>
							</tr>
							<tr>


								<td>11(b)</td>
								<th>Carbon Dioxide</th>
								<td></td>
								<td>${oilReport.dgaTestCarbonDioxide}</td>
							</tr>
							<tr>


								<td>11(c)</td>
								<th>CarbonMonoxide</th>
								<td></td>
								<td>${oilReport.dgaTestCarbonMonoxide}</td>
							</tr>
							<tr>


								<td>11(d)</td>
								<th>Ethane</th>
								<td></td>
								<td>${oilReport.dgaTestEthane}</td>
							</tr>
							<tr>


								<td>11(e)</td>
								<th>Ethylene</th>
								<td></td>
								<td>${oilReport.dgaTestEthylene}</td>
							</tr>
							<tr>


								<td>11(f)</td>
								<th>Methane</th>
								<td></td>
								<td>${oilReport.dgaTestMethane}</td>
							</tr>
							<tr>


								<td>11(g)</td>
								<th>Hydrogen</th>
								<td></td>
								<td>${oilReport.dgaTestHydrogen}</td>
							</tr>
							<tr>


								<td>11(g)</td>
								<th>Nitrogen</th>
								<td></td>
								<td>${oilReport.dgaTestNitrogen}</td>
							</tr>
							<tr>


								<td>11(h)</td>
								<th>Oxygen</th>
								<td></td>
								<td>${oilReport.dgaTestOxygen}</td>
							</tr>
							<tr>

								<td>12</td>
								<th>Inter-Facial Tension at 27<sup>o</sup>C
								</th>
								<td></td>
								<td>${oilReport.interFacialTensionAt27DegC}</td>
							</tr>

							<tr>
								<th colspan="3">13 PNA Analysis</th>

							</tr>

							<tr>

								<td>13(a)</td>
								<th>Paraffinic</th>
								<td></td>
								<td>${oilReport.pnaParaffinic}</td>
							</tr>
							<tr>
								<td>13(a)</td>

								<th>Napthenic</th>
								<td></td>
								<td>${oilReport.pnaNapthenic}</td>
							</tr>
							<tr>

								<td>13(a)</td>
								<th>Aromatic</th>
								<td></td>
								<td>${oilReport.pnaAromatic}</td>
							</tr>


							<tr>
								<th colspan="3">14 Oxidation Stability Test(164 Hours @ 100<sup>o</sup>
									C)
								</th>

							</tr>
							<tr>

								<td>14(a)</td>
								<th>Neutralization</th>
								<td></td>
								<td>${oilReport.oxidationStabilityTestNeutralization}</td>
							</tr>
							<tr>

								<td>14(b)</td>
								<th>Total Sludge</th>
								<td></td>
								<td>${oilReport.oxidationStabilityTestTotalSludge}</td>
							</tr>



							<tr>
								<th colspan="3">15 Ageing Characteristics Test (96 Hours
									with Copper Catalyst)</th>

							</tr>
							<tr>

								<td>15(a)</td>
								<th>Specific Resistance at 90<sup>o</sup></th>
								<td></td>
								<td>${oilReport.ageingCharacteristicsSpecificResistanceAt90Degree}</td>
							</tr>
							<tr>

								<td>15(b)</td>
								<th>Tan Delta at 90<sup>o</sup></th>
								<td></td>
								<td>${oilReport.ageingCharacteristicsTanDeltaAt90Degree}</td>
							</tr>
							<tr>

								<td>15(c)</td>
								<th>Total Acidity</th>
								<td></td>
								<td>${oilReport.ageingCharacteristicsTotalAcidity}</td>
							</tr>
							<tr>

								<td>15(d)</td>
								<th>Total Sludge</th>
								<td></td>
								<td>${oilReport.ageingCharacteristicsTotalSludge}</td>
							</tr>
							<tr>

								<td>16</td>
								<th>Corrosive Sulphur</th>
								<td></td>
								<td>${oilReport.corrosiveSulphur}</td>
							</tr>
							<tr>

								<td>17</td>
								<th>Presence of Oxidation Inhibitor</th>
								<td></td>
								<td>${oilReport.presenceOfOxidationInhibitor}</td>
							</tr>



							<tr>
								<th colspan="3">Report Remarks</th>

							</tr>
							<tr>

								<td></td>
								<th>Remarks by JE</th>
								<td>${oilReport.remarks}</td>
							</tr>
							<tr>

								<td></td>
								<th>Remarks by AEE</th>
								<td>${oilReport.remarksAEE}</td>
							</tr>
							<tr>

								<td></td>
								<th>Suggestions by ASE</th>
								<td>${oilReport.suggestionsASE}</td>
							</tr>


							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div><%@include file="footer.jsp"%>
</body>
</html>