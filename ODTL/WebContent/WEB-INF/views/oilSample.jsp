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

	<div class="container-fluid">






		<div class="card card-default">

			<div class="card-heading">
				<span class="lead">Oil Sample </span>
			</div>
			<div class="row">

				<div class="card" style="width: 18rem;">
					<img class="card-img-top" width="200px" height="200px"
						src="<c:url value='/static/img/sample1.jpg' />" alt="Sample">
					<div class="card-body">
						<h5 class="app-title">Oil Sample Details</h5>
						<table class="table table-hover card-table">


							<tr>

								<th>Sample Taken From</th>
								<td>${oilSample.sampleTakenFrom}</td>
							</tr>
							<tr>

								<th>Sampling Valve</th>
								<td>${oilSample.samplingValve}</td>
							</tr>
							<tr>

								<th>Sample No.</th>
								<td>${oilSample.sampleNo}</td>
							</tr>

							<tr>

								<th>Bottle No.</th>
								<td>${oilSample.bottleNo}</td>
							</tr>


							<tr>

								<th>Date of Receipt of Sample</th>
								<td><fmt:formatDate value="${oilSample.sampleReceiptDate}"
										pattern="dd/MM/yyyy" /></td>
							</tr>
							<tr>

								<th>Reference Memo No.</th>
								<td><fmt:formatDate value="${oilSample.referenceMemoDate}"
										pattern="dd/MM/yyyy" /></td>
							</tr>
							<tr>

								<th>Reference Memo Date</th>
								<td>${oilSample.referenceMemoNo}</td>
							</tr>
							<tr>

								<th>Sample Container</th>
								<td>${oilSample.sampleContainer}</td>
							</tr>
							<tr>

								<th>Sample Condition</th>
								<td>${oilSample.sampleCondition}</td>
							</tr>
							
						</table>
					</div>
				</div>

			
			</div>
		</div>
	</div><%@include file="footer.jsp"%>
</body>
</html>