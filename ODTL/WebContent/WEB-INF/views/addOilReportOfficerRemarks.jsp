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
	function approvalAEEFunc(approval) {
		document.forms['oilReportForm'].action = 'aeeRemarksOilReport';
		$("#approvalAEE").prop('value', approval);
		document.forms['oilReportForm'].submit();
	}

	function approvalASEFunc(approval) {
		document.forms['oilReportForm'].action = 'aseRemarksOilReport';
		$("#approvalASE").prop('value', approval);

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
			<form:hidden path="oilSample.id" />



			<iframe id="myReportFrame" width="100%" height="100%"
				src="<c:url value='/previewPdfOilReport-${oilReport.id}'/>"></iframe>

			<div class="card">
				<div class="card-heading">Remarks</div>

				<div class="card-body">
					<div class="row">
					

						<sec:authorize access="hasAnyRole('AEE','ASE')">
<div class="form-control col-md-12">
								<label class="col-md-3 form-control-label" for="remarks">
									Remarks by JE </label>
								<div class="col-md-9">
									<form:textarea path="remarks" id="remarks"
										class="form-control input-sm" />

									<div class="has-error">
										<form:errors path="remarks" class="help-inline" />
									</div>
								</div>
							</div>

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

										<button type="button" id="aeeApproveBtn"
											onclick="approvalAEEFunc(100)" class="btn btn-success">Save
											& Approve</button>

										<button type="button" id="aeeRejectBtn"
											onclick="approvalAEEFunc(-100)" class="btn btn-danger">Reject
											Report</button>
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



										<button type="button" id="aseApproveBtn"
											onclick="approvalASEFunc(100)" class="btn btn-success">Save
											& Approve</button>

										<button type="button" id="aseRejectBtn"
											onclick="approvalASEFunc(-100)" class="btn btn-danger">Reject
											Report</button>
									</c:if>
								</c:if>
							</div>
						</sec:authorize>
					</div>
				</div>
			</div>


		</form:form>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>