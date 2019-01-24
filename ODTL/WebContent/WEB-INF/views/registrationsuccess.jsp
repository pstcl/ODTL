<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head><meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Registration Confirmation Page</title>
	
</head>
<body style="margin:0;">
	<%@include file="authheader.jsp"%>

	<div id="page-wrapper" class="generic-container">
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			Go to <a href="<c:url value='/list' />">Users List</a>
		</span>
	</div><%@include file="footer.jsp"%>
</body>

</html>