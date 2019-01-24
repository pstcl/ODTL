\<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/javascript">
	function previewReport(url) {
		$('#myModal').modal('toggle');
				document.getElementById('myReportFrame').src=url;
	
	}
	function closeReport() {
		//$('#myModal').modal('toggle');
				document.getElementById('myReportFrame').src=null;
	
	}
	</script>

<%-- <c:url value='/previewOilReport-${oilReport.id}' /> --%>


<title>Oil Reports</title>
</head>

<body style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<%@include file="dataTablesHeader.jsp"%>

	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">Test
					Reports</li>
			</ol>
		</nav>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			$('.modal-content').resizable({
				//alsoResize: ".modal-dialog",
				minHeight : 300,
				minWidth : 600
			});
			$('.modal-dialog').draggable();
		});
	</script>

	<div class="container-fluid">
		<c:if test="${not empty success}">
			<div class="alert alert-success lead">${success}</div>
		</c:if>
		<span class="lead">Oil Reports</span>


		<div class="col-sm-12">
			<jsp:include page="filter.jsp" />
		</div>

		<div class="table-responsive">
			<table id="myDataTable"
				class="table table-striped table-bordered table-hover">
				<thead class="thead-light">
					<tr>

						<th scope="col">#</th>

						<th>Sample No:</th>
						<th>Sample Date</th>
						<th>Circle</th>
						<th>Division</th>
						<th>Substation</th>


						<th>Report Date</th>
						<th>Equipment</th>
						<th></th>



					</tr>
				</thead>
				<tbody>



					<c:forEach items="${oilReports}" var="oilReport"
						varStatus="indexStatus">




						<c:choose>
							<c:when test="${oilReport.approvalAEE eq -100}">
								<tr class="table-warning">

									<td><span> <i class="fa fa-warning"
											style="font-size: 18px; color: orange;"> Rejected by AEE</i>
									</span></td>
							</c:when>
							<c:when test="${oilReport.approvalASE eq -100}">
								<tr class="table-danger">

									<td><span> <i class="fa fa-warning"
											style="font-size: 18px; color: red"> Rejected by ASE</i>
									</span></td>
							</c:when>
							<c:otherwise>
								<tr>

									<td><c:choose>
											<c:when test="${oilReport.approvalASE eq 100}">
												<span> <i class="fa fa-warning"
													style="font-size: 18px; color: Blue">Published</i>
												</span>
											</c:when>

											<c:when test="${oilReport.approvalAEE eq 100}">
												<span> <i class="fa fa-warning"
													style="font-size: 18px; color: Green">(1) Approvals
														Pending: ASE</i>
												</span>
											</c:when>
											<c:otherwise>

												<span> <i class="fa fa-warning"
													style="font-size: 18px; color: Black">(2) Approvals
														Pending: AEE,ASE</i>

												</span>
											</c:otherwise>
										</c:choose></td>
							</c:otherwise>
						</c:choose>
						<td>${oilReport.oilSample.sampleNo}</td>
						<td><fmt:formatDate
								value="${oilReport.oilSample.sampleReceiptDate}"
								pattern="dd/MM/yyyy" /></td>
						<td>${oilReport.equipment.circle.circleDescription}</td>
						<td>${oilReport.equipment.division.divisionDescription}</td>
						<td>${oilReport.equipment.substation.substationDescription}</td>

						<td><fmt:formatDate value="${oilReport.reportDate}"
								pattern="dd/MM/yyyy" /></td>
						<td>${oilReport.equipment.equipmentType}(
							${oilReport.equipment.equipmentID}) <c:if
								test="${not empty oilReport.oilSample.sampleTakenFrom}">(${oilReport.oilSample.sampleTakenFrom})</c:if>
						</td>
						<td>
							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" type="button"
									data-toggle="dropdown">
									Options <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<c:choose>
										<c:when test="${oilReport.approvalASE eq 100}">
											<li><a target="_blank"
												href="<c:url value='/pdfOilReport-${oilReport.id}' />"><i
													class="far fa-file-pdf"></i> View as PDF</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="#"
												onclick="previewReport('<c:url value='/previewPdfOilReport-${oilReport.id}'/>')">Preview</a>

											</li>
										</c:otherwise>
									</c:choose>



									<sec:authorize access="hasRole('ODTL')">

										<c:choose>
											<c:when test="${oilReport.approvalASE ne 100}">
												<sec:authorize access="hasRole('JE')">
													<li><a
														href="<c:url value='/edit-oilReport-${oilReport.id}' />">Edit
															Report</a></li>
												</sec:authorize>
											</c:when>
											<c:when test="${oilReport.approvalAEE ne 100}">
												<sec:authorize access="hasRole('JE')">
													<li><a
														href="<c:url value='/edit-oilReport-${oilReport.id}' />">Edit
															Report</a></li>
												</sec:authorize>
											</c:when>
										</c:choose>
										<c:if test="${oilReport.approvalASE ne 100}">
											<sec:authorize access="hasRole('AEE')">
												<li><a
													href="<c:url value='/aeeRemarksOilReport${oilReport.id}' />">
														Approve/Reject</a></li>
											</sec:authorize>

											<sec:authorize access="hasRole('ASE')">
												<li><a
													href="<c:url value='/aseRemarksOilReport${oilReport.id}' />">Approve/Reject</a></li>
											</sec:authorize>
										</c:if>
									</sec:authorize>
								</ul>
							</div>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>
	</div>


	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Oil Report Preview</h4>
					<button type="button" class="close" data-dismiss="modal" onclick="closeReport()">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<iframe id="myReportFrame" width="100%" height="100%"></iframe>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" onclick="closeReport()">Close</button>
				</div>

			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>