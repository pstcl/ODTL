<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AccessDenied page</title>
</head>
<body>
	<div class="generic-container">
		<div class="authbar">
			<span>Sorry <strong>${loggedinuser}</strong>, You are not
				authorized to access this feature.
			</span> <span class="floatRight"><a href="<c:url value="/home" />">Home</a></span>
			</span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
		</div>
	</div>
</body>
</html>