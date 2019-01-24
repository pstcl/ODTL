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
	<%@include file="dataTablesHeader.jsp"%>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="javascript:window.location='home'">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Oil
				Samples</li>
		</ol>
	</nav>
	<jsp:include page="filterOilSample.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {

			applyFilters() ;
		});
	</script>
	<div id="tableContainer"></div>
	<%@include file="footer.jsp"%>
</body>
</html>