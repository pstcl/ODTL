<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head><meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Users List</title>
</head>

<body style="margin:0;">
	<%@include file="authheader.jsp"%>

	<div id="page-wrapper" class="generic-container">
		<div class="card card-default">
			<!-- Default card contents -->
			<div class="alert alert-success lead">${success}</div>

			<div class="card-heading">
				<span class="lead">List of Users </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>User ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Designation</th>
						<sec:authorize access="hasRole('ODTL')">
							<th width="100"></th>
						
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.ssoId}</td>
							<td>${user.firstName}&nbsp;${user.lastName}</td>
							<td>${user.pswrdenc}</td>
							<td>${user.designation.designation}</td>
							<sec:authorize access="hasRole('ODTL')">
								<td><a href="<c:url value='/edit-user-${user.ssoId}' />"
									class="btn btn-success custom-width">edit</a></td>
							
								<td><a href="<c:url value='/delete-user-${user.ssoId}' />"
									class="btn btn-warning custom-width">delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('ODTL')">
			<div class="well">
				<a href="<c:url value='/newuser' />">Add New User</a>
			</div>
		</sec:authorize>
	</div><%@include file="footer.jsp"%>
</body>
</html>