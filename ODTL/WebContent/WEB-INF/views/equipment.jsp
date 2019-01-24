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
			<!-- Default card contents -->
			<div class="alert alert-success lead">${success}</div>

			<div class="card-heading">
				<span class="lead">Equipment</span>
			</div>
			<div class="row">


				<div class="card" style="width: 18rem;">
					<img class="card-img-top"
						src="<c:url value='/static/img/tf1.jpg' />" alt="Equipment">
					<div class="card-body">
						<div class="app-title">${equipment.equipmentType}<br>
							<div class="sub-title">${equipment.substation.substationDescription},${equipment.circle.circleDescription}</div>
						</div>
						<table class="table table-hover card-table">

							<tr>

								<th>Circle</th>
								<td>${equipment.circle.circleDescription}</td>
							</tr>
							<tr>

								<th>Division</th>
								<td>${equipment.division.divisionDescription}</td>
							</tr>
							<tr>

								<th>Sub-Station</th>
								<td>${equipment.substation.substationDescription}</td>
							</tr>

							<tr>
								<th>Equipment Type</th>
								<td>${equipment.equipmentType}</td>
							</tr>
							<tr>
								<th>Equipment ID</th>
								<td>${equipment.equipmentID}</td>
							</tr>
							<tr>
								<th>Make</th>
								<td>${equipment.make}</td>
							</tr>
							<tr>
								<th>Serial No.</th>
								<td>${equipment.serialNo}</td>
							</tr>
							<tr>
								<th>Year of Mfg.</th>
								<td>${equipment.yearOfMfg}</td>
							</tr>
							<tr>
								<th>Voltage Class</th>
								<td>${equipment.voltageClass}</td>
							</tr>
							<tr>
								<th>Capacity</th>
								<td>${equipment.capacity}</td>
							</tr>
							<tr>
								<th>Cooling Type</th>
								<td>${equipment.coolingType}</td>
							</tr>
							<tr>
								<th>Oil Type</th>
								<td>${equipment.oilType}</td>
							</tr>


							


						</table>
					</div>
				</div>


			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>