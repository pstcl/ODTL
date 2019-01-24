<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Sample Entry Form</title>
<script type="text/javascript">
	function submitform() {

		document.forms['oilSampleForm'].action = 'filterForSample';
		document.forms['oilSampleForm'].submit();

	}
	function saveOrUpdate() {
		document.forms['oilSampleForm'].submit();

	}

	function setSampleUser() {

		document.forms['oilSampleForm'].action = 'setSampleUser';
		document.forms['oilSampleForm'].submit();

	}
</script>

<c:if test="${oilSample.sampleType ne 'OIL_REPORT_NEW_OIL'}">
	<script type="text/javascript">
		function selectEquipment() {

			document.forms['oilSampleForm'].action = 'setSampleEquipment';
			document.forms['oilSampleForm'].submit();

		}
	</script>
</c:if>

</head>

<body style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="javascript:window.location='home'">Home</a></li>
			<c:if test="${not empty oilSample.equipment.id}">
				<li class="breadcrumb-item"><a
					href="<c:url value='/viewEquipment-${oilSample.equipment.id}'/>">Equipment</a></li>
			</c:if>
			<li class="breadcrumb-item active" aria-current="page">Add Oil
				Sample</li>
		</ol>
	</nav>
	<div class="container-fluid">
		<script type="text/javascript">
			$(function() {
				$('.date-picker').datepicker({
					yearRange : '1950:2100',
					changeMonth : true,
					changeYear : true,
					showButtonPanel : false,
					dateFormat : "dd/mm/yy"

				});
			});
		</script>
		<form:form method="POST" id="oilSampleForm" action="saveOilSample"
			modelAttribute="oilSample" class="form-horizontal">
			<form:input type="hidden" path="id" id="id" />
			<form:input type="hidden" path="sampleType" id="sampleType" />
			<c:if test="${not empty success}">
				<div class="alert alert-success lead">${success}</div>
			</c:if>
			<div class="card card-default">
				<!-- Default card contents -->
				<div class="card-heading">
					<div class="alert alert-success lead">Equipment Details</div>
				</div>
				<c:choose>
					<c:when test="${not empty oilSample.equipment.id}">
						<div class="row">
							<ul class="custom-breadcrumb">
								<li class="light-blue-crumb">Equipment
									Type:${oilSample.equipment.equipmentType}</li>
								<li class="blue-crumb">Substation:
									${oilSample.equipment.substation.substationDescription}</li>
								<li class="gray-crumb">Division:
									${oilSample.equipment.division.divisionDescription}</li>
								<li class="light-blue-crumb">Circle:
									${oilSample.equipment.circle.circleDescription}</li>
							</ul>
						</div>

					</c:when>
					<c:otherwise>
						<c:if test="${oilSample.sampleType ne 'OIL_REPORT_NEW_OIL'}">
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
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<div class="row">
					<div class="form-control col-sm-4">
						<label class="col-sm-6 form-control-label" for="equipment">Equipment</label>
						<div class="col-sm-12">


							<form:select onchange="selectEquipment()" path="equipment"
								multiple="false" class="form-control input-sm">
								<form:option value="${null}" label="Select Equipment"></form:option>
								<form:options items="${equipmentList}" itemValue="id"
									itemLabel="equipmentLabel" />
							</form:select>

							<div>
								<form:errors path="equipment" class="alert alert-danger" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card card-default">
				<!-- Default card contents -->


				<div class="card-heading">
					<div class="alert alert-success lead">Sender Details</div>
				</div>


				<c:if test="${empty oilSample.sampleSentByUser.id}">
					<div class="row">
						<div class="form-control col-sm-4">
							<label class="col-sm-6 form-control-label"
								for="sampleSentByUser.circle">Circle Name</label>
							<div class="col-sm-12">


								<form:select onchange="setSampleUser()" id="circleDD"
									path="sampleSentByUser.circle" multiple="false"
									class="form-control input-sm">
									<form:option value="" label="Select Circle"></form:option>
									<form:options items="${circleListForSender}"
										itemValue="circleCode" itemLabel="circleDescription" />
								</form:select>

								<div class="has-error">
									<form:errors path="sampleSentByUser.circle"
										class="alert alert-danger" />
								</div>
							</div>
						</div>

						<div class="form-control col-sm-4">
							<label class="col-sm-6 form-control-label"
								for="sampleSentByUser.division">Division Name</label>
							<div class="col-sm-12">

								<form:select onchange="setSampleUser()"
									path="sampleSentByUser.division" multiple="false"
									class="form-control input-sm">
									<form:option value="" label="Select Division"></form:option>
									<form:options items="${divisionListForSender}"
										itemValue="divisionCode" itemLabel="divisionDescription" />
								</form:select>
								<div>
									<form:errors path="sampleSentByUser.division"
										class="alert alert-danger" />
								</div>
							</div>
						</div>

						<div class="form-control col-sm-4">
							<label class="col-sm-6 form-control-label"
								for="sampleSentByUser.substation">Sub-station</label>
							<div class="col-sm-12">


								<form:select onchange="setSampleUser()"
									path="sampleSentByUser.substation" multiple="false"
									class="form-control input-sm">
									<form:option value="${null}" label="Select Substation"></form:option>
									<form:options items="${substationListForSender}"
										itemValue="substationCode" itemLabel="substationDescription" />
								</form:select>
								<div>
									<form:errors path="sampleSentByUser.substation"
										class="alert alert-danger" />
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="form-control col-md-4">
						<label class="col-md-4 form-control-label" for="sampleSentByUser">Sent
							By</label>
						<div class="col-md-12">
							<form:select path="sampleSentByUser" onchange="setSampleUser()"
								class="form-control input-sm">
								<form:option value="${null}" label="Select Another Location"></form:option>
								<form:options items="${senderlist}" itemValue="id"
									itemLabel="labelForSample" />

							</form:select>

							<div class="has-error">
								<form:errors path="sampleSentByUser" class="help-inline" />
							</div>
						</div>
					</div>


					<div class="form-control col-md-4">
						<label class="col-md-12 form-control-label" for="referenceMemoNo">Reference
							Memo No</label>
						<div class="col-md-12">
							<form:input type="text" path="referenceMemoNo"
								id="referenceMemoNo" class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="referenceMemoNo" class="help-inline" />
							</div>
						</div>
					</div>


					<div class="form-control col-md-4">
						<label class="col-md-12 form-control-label"
							for="referenceMemoDate">Reference Memo No. Date</label>
						<div class="col-md-12">
							<form:input cssClass="date-picker" path="referenceMemoDate"
								id="referenceMemoDate" />
							<div class="has-error">
								<form:errors path="referenceMemoDate" class="help-inline" />
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="card card-default">
				<!-- Default card contents -->


				<div class="card-heading">
					<div class="alert alert-success lead">Sample Details</div>

				</div>
				<div class="row">
					<c:if test="${oilSample.sampleType ne 'OIL_REPORT_NEW_OIL'}">
						<div class="form-control col-md-4">
							<label class="col-md-12 form-control-label" for="sampleTakenFrom">Sample
								Taken From </label>
							<div class="col-md-12">

								<form:select path="sampleTakenFrom"
									items="${sampleTakenFromList}" multiple="false"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="sampleTakenFrom" class="help-inline" />
								</div>
							</div>
						</div>





						<div class="form-control col-md-4">
							<label class="col-md-12 form-control-label" for="samplingValve">Sampling
								Valve </label>
							<div class="col-md-12">

								<form:select path="samplingValve" items="${samplingValveList}"
									multiple="false" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="samplingValve" class="help-inline" />
								</div>
							</div>
						</div>
					</c:if>

					<div class="form-control col-md-4">
						<label class="col-md-12 form-control-label" for="sampleContainer">Sample
							Sent in </label>
						<div class="col-md-12">

							<form:select path="sampleContainer"
								items="${sampleContainerList}" multiple="false"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="sampleContainer" class="help-inline" />
							</div>
						</div>
					</div>

					
					<div class="form-control col-md-4">
						<label class="col-md-12 form-control-label" for="sampleNo">Sample
							No</label>
						<div class="col-md-12">
							<form:input type="text" path="sampleNo" id="sampleNo"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="sampleNo" class="help-inline" />
							</div>
						</div>
					</div>

					<div class="form-control col-md-4">
						<label class="col-md-12 form-control-label" for="bottleNo">Bottle
							No</label>
						<div class="col-md-12">
							<form:input type="text" path="bottleNo" id="bottleNo"
								class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="bottleNo" class="help-inline" />
							</div>
						</div>
					</div>



					<div class="form-control col-md-4">
						<label class="col-md-12 form-control-label"
							for="sampleReceiptDate">Date of Receipt of Sample</label>
						<div class="col-md-12">
							<form:input type="text" cssClass="date-picker"
								path="sampleReceiptDate" id="sampleReceiptDate" />
							<div class="has-error">
								<form:errors path="sampleReceiptDate" class="help-inline" />
							</div>
						</div>
					</div>

				</div>
			</div>










			<div class="form-actions">
				<c:choose>
					<c:when test="${edit}">
						<input type="button" value="Update" class="btn btn-primary btn-sm"
							onclick="saveOrUpdate()" /> or <a href="<c:url value='/home' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="button" value="Save" onclick="saveOrUpdate()"
							class="btn btn-primary btn-sm" /> or <a
							href="<c:url value='/home' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
	</div>

	</form:form>
	</div>



	<%@include file="footer.jsp"%>
</body>
</html>
