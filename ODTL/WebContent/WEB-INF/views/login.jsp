<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>Login page</title>

<!-- Stylesheets -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<script
	src="${pageContext.servletContext.contextPath}/static/js/jquery-3.3.1.min.js"
	type="text/javascript"></script>

<script
	src="${pageContext.servletContext.contextPath}/static/js/jqueryUI.min.js"></script>

<script
	src="${pageContext.servletContext.contextPath}/static/js/jquery.dataTables.min.js"></script>



<script
	src="${pageContext.servletContext.contextPath}/static/js/popper.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/js/bootstrap.min.js"></script>

<link
	href="<c:url value='/static/css/datepicker.min.css?vol=2511811.211' />"
	rel="stylesheet" type="text/css" media="all" />

<link
	href="<c:url value='/static/css/dataTables.bootstrap.min.css?vol=251181.2111' />"
	rel="stylesheet" type="text/css" media="all" />

<link
	href="<c:url value='/static/css/bootstrap.min.css?vol=251181.2111' />"
	rel="stylesheet"></link>




</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-faded">
			<a class="navbar-brand" href="javascript:window.location='home'">
				<img alt="PSTCL" width="70px;" height="50px;"
				src="<c:url value='/static/img/pstcl.png' />">
			</a>

			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbarNavAltMarkup" class="navbar-collapse collapse">


				<ul class="navbar-nav  mr-auto">
					<li><h3>
							<span class="navbar-text">ODTL Reporting Portal</span>
						</h3></li>
				</ul>
			</div>
		</nav>

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				Please login
				<hr>
			</div>
		</div>
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="post" class="form-horizontal">





			<c:if test="${param.error != null}">
				<div class="alert alert-warning">
					<p>Invalid username and password.</p>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>
			<c:if test="${param.passwordChanged != null}">
				<div class="alert alert-success">
					<p>Password Changed successfully.</p>
				</div>
			</c:if>
			<div class="input-group input-sm">
				<label class="input-group-addon" for="username"><i
					class="fa fa-user"></i></label> <input type="text" class="form-control"
					id="username" name="ssoId" placeholder="Enter Username" required>
			</div>
			<div class="input-group input-sm">
				<label class="input-group-addon" for="password"><i
					class="fa fa-lock"></i></label> <input type="password" class="form-control"
					id="password" name="password" placeholder="Enter Password" required>
			</div>
			<div class="input-group input-sm">
				<div class="checkbox">
					<label><input type="checkbox" id="rememberme"
						name="remember-me"> Remember Me</label>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="form-actions">
				<input type="submit" class="btn btn-block btn-primary btn-default"
					value="Log in">
			</div>

		</form>
	</div>
</body>
</html>