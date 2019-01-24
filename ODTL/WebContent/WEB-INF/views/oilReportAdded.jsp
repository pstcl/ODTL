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


	<div class="container-fluid">
		<div class="well lead">Report Preview</div>

		<iframe id="myReportFrame" width="100%" height="100%"
			src="<c:url value='/previewPdfOilReport-${oilReport.id}'/>"></iframe>
		<div class="row">
			<div class="form-control col-sm-4">

				<a class="nav-link"
					href="<c:url value='/edit-oilReport-${oilReport.id}' />">
					<div class="app-title">
						Edit Report<br>
					</div>

				</a>
			</div>
			<div class="form-control col-sm-4">
				<a class="nav-link" href="javascript:window.location='addOilReport'">
					<div class="app-title">
						Add New Report<br>
					</div>

				</a>
			</div>

		</div>

	</div>
	<%@include file="footer.jsp"%>
</body>
</html>