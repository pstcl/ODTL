<%@page import="org.pstcl.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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


	<sec:authorize access="hasRole('ODTL')">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">Oil Report Options</a>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">

					<c:if test="${oilReport.approvalASE eq 100}">
						<li><a target="_blank"
							href="<c:url value='/pdfOilReport-${oilReport.id}' />"><i
								class="nav-link"></i> Download Report</a></li>
					</c:if>

					<sec:authorize access="hasRole('ODTL')">
						<sec:authorize access="hasRole('JE')">

							<li><a class="nav-link"
								href="javascript:window.location='addOilReport'"> Add New
									Report<br>

							</a></li>
						</sec:authorize>
						<c:choose>
							<c:when test="${oilReport.approvalASE ne 100}">
								<sec:authorize access="hasRole('JE')">
									<li><a class="nav-link"
										href="<c:url value='/edit-oilReport-${oilReport.id}' />">Edit
											Report</a></li>
								</sec:authorize>
							</c:when>
							<c:when test="${oilReport.approvalAEE ne 100}">
								<sec:authorize access="hasRole('JE')">
									<li><a class="nav-link"
										href="<c:url value='/edit-oilReport-${oilReport.id}' />">Edit
											Report</a></li>
								</sec:authorize>
							</c:when>
						</c:choose>
						<c:if test="${oilReport.approvalASE ne 100}">
							<sec:authorize access="hasRole('AEE')">
								<li><a class="nav-link"
									href="<c:url value='/aeeRemarksOilReport${oilReport.id}' />">
										Approve/Reject</a></li>
							</sec:authorize>

							<sec:authorize access="hasRole('ASE')">
								<li><a class="nav-link"
									href="<c:url value='/aseRemarksOilReport${oilReport.id}' />">Approve/Reject</a></li>
							</sec:authorize>
						</c:if>
					</sec:authorize>
				</ul>
			</div>
		</nav>


	</sec:authorize>


	<div class="container-fluid">
		<div class="well lead">Report Preview</div>
		<c:if test="${oilReport.approvalASE ne 100}">

			<iframe id="myReportFrame" width="100%" height="100%"
				src="<c:url value='/previewPdfOilReport-${oilReport.id}'/>"></iframe>
		</c:if>

		<c:if test="${oilReport.approvalASE eq 100}">

			<iframe id="myReportFrame" width="100%" height="100%"
				src="<c:url value='/pdfOilReport-${oilReport.id}'/>"></iframe>
		</c:if>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>