<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>Equipment Entry Form</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript">
function submitform() {

	document.forms['equipmentForm'].action = 'setLocation';
	document.forms['equipmentForm'].submit();

}

function saveOrUpdate() {
	document.forms['equipmentForm'].submit();

}

</script>
</head>


<body style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="javascript:window.location='home'">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Add
				Equipment</li>
		</ol>
	</nav>
	<div class="container-fluid">

		<div class="well lead">Equipment Entry Form</div>
		
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
		<form:form id="equipmentForm" action="saveEquipment" method="POST"
			modelAttribute="equipment" class="form-horizontal">
			<form:input type="hidden" path="id" id="id" />
			<input type="hidden" id="isLocation" />


			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="circle">Circle
						Name</label>
					<div class="col-sm-12">


						<form:select onchange="submitform()" id="circleDD" path="circle"
							multiple="false" class="form-control input-sm">
							<form:option value="" label="Select Circle"></form:option>
							<form:options items="${circleList}" itemValue="circleCode"
								itemLabel="circleDescription" />
						</form:select>

						<div class="has-error">
							<form:errors path="circle" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="division">Division
						Name</label>
					<div class="col-sm-12">

						<form:select onchange="submitform()" path="division"
							multiple="false" class="form-control input-sm">
							<form:option value="" label="Select Division"></form:option>
							<form:options items="${divisionList}" itemValue="divisionCode"
								itemLabel="divisionDescription" />
						</form:select>
						<div>
							<form:errors path="division" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="substation">Sub-station</label>
					<div class="col-sm-12">


						<form:select onchange="submitform()" path="substation"
							multiple="false" class="form-control input-sm">
							<form:option value="${null}" label="Select Substation"></form:option>
							<form:options items="${substationList}"
								itemValue="substationCode" itemLabel="substationDescription" />
						</form:select>
						<div>
							<form:errors path="substation" class="alert alert-danger" />
						</div>
					</div>
				</div>
			</div>





			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="equipmentType">Equipment
						Type</label>
					<div class="col-sm-12">

						<form:select path="equipmentType" items="${equipmentTypeList}"
							multiple="false" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="equipmentType" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="equipmentID">Equipment
						ID</label>
					<div class="col-sm-12">
						<form:input type="text" path="equipmentID" id="equipmentID"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="equipmentID" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="make">Make</label>
					<div class="col-sm-12">
						<form:input type="text" path="make" id="make"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="make" class="alert alert-danger" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="serialNo">Serial
						No.</label>
					<div class="col-sm-12">
						<form:input type="text" path="serialNo" id="serialNo"
						class="form-control input-sm" /> 
						<div class="has-error">
 							<form:errors path="serialNo" class="alert alert-danger" /> 
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="yearOfMfg">Year
						of Mfg.</label>
					<div class="col-sm-12">
						<form:input type="text" path="yearOfMfg" id="yearOfMfg"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="yearOfMfg" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="voltageClass">Voltage
						Class</label>
					<div class="col-sm-12">

						<form:select path="voltageClass" items="${voltageClassTypeList}"
							multiple="false" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="voltageClass" class="alert alert-danger" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="capacity">Capacity</label>
					<div class="col-sm-12">
						<form:input type="text" path="capacity" id="capacity"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="capacity" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="coolingType">Cooling
						Type</label>
					<div class="col-sm-12">

						<form:select path="coolingType" items="${coolingTypeList}"
							multiple="false" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="coolingType" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="oilType">Oil
						Type</label>
					<div class="col-sm-12">

						<form:select path="oilType" items="${oilTypeList}"
							multiple="false" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="oilType" class="alert alert-danger" />
						</div>
					</div>
				</div>

				<div class="form-control col-md-4">
					<label class="col-md-5 form-control-label" for="commissioningDate">Date
						of Commissioning</label>
					<div class="col-md-7">
						<form:input type="text" cssClass="date-picker"
							path="commissioningDate" id="commissioningDate" />
						<div class="has-error">
							<form:errors path="commissioningDate" class="help-inline" />
						</div>
					</div>
				</div>

			</div>










			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="button" onclick="saveOrUpdate()" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/home' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="button" onclick="saveOrUpdate()" value="Save" class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/home' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>