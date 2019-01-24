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
		document.getElementById('myReportFrame').src = url;

	}
	function closeReport() {
		//$('#myModal').modal('toggle');
		document.getElementById('myReportFrame').src = null;

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

	<jsp:include page="filterOilReports.jsp" />
	<script type="text/javascript">
			$(document).ready(function() {

				$('.modal-content').resizable({
					//alsoResize: ".modal-dialog",
					minHeight : 300,
					minWidth : 600
				});
				$('.modal-dialog').draggable();
				applyFilters();
			});
		</script>
	<div id="tableContainer"></div>

	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Oil Report Preview</h4>
					<button type="button" class="close" data-dismiss="modal"
						onclick="closeReport()">&times;</button>
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