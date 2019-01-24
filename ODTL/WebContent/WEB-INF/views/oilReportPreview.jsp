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

</head>

<body style="margin: 0;">

	<%@include file="authheader.jsp"%>


	<div class="container-fluid">
		<div class="well lead">Report Preview</div>

		<iframe id="myReportFrame" width="100%" height="100%"
			src="<c:url value='/previewPdfOilReport-${oilReport.id}'/>"></iframe>

	</div>
	<%@include file="footer.jsp"%>
</body>
</html>