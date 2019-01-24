
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="tableContainer">
	<div class="container-fluid">
		<div class="card card-default">
			<!-- Default card contents -->

			<c:if test="${not empty success}">

				<div class="alert alert-success lead">${success}</div>
			</c:if>
			<div class="card-heading">
				<span class="lead">List of Equipments </span>
			</div>
			<div class="card-body">

				<div class="row">




					<table id="myDataTable"
						class="table table-striped table-bordered table-hover">
						<thead>

							<tr>
							<tr>

								<th>#</th>

								<th>Circle</th>
								<th>Division</th>
								<th>Sub-Station</th>
								<th>Type</th>
								<th>Make</th>
								<th>ID</th>
								<th>Serial No.</th>
								<th>Control Panel</th>

								<sec:authorize access="hasRole('ADMIN')">
									<th width="100"></th>
								</sec:authorize>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${equipments}" var="equipment"
								varStatus="varStatus">

								<tr>
									<td>${varStatus.index+1}</td>

									<td>${equipment.circle.circleDescription}</td>
									<td>${equipment.division.divisionDescription}</td>
									<td>${equipment.substation.substationDescription}</td>
									<td><img class="card-img-report"
										src="<c:url value='/static/img/${equipment.equipmentType}.jpg' />"
										alt="Equipment"> ${equipment.equipmentType}</td>
									<td>${equipment.make}</td>
									<td>${equipment.equipmentID}</td>
									<td>${equipment.serialNo}</td>


									<td>

										<div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												Options <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">

												<li><a
													href="<c:url value='/viewEquipment-${equipment.id}'/>"><i
														class="fa fa-folder-open"></i>View Details</a></li>

												<sec:authorize access="hasRole('ADMIN')">
													<sec:authorize access="hasRole('JE') or hasRole('AEE')">
														<li><a
															href="<c:url value='/edit-equipment-${equipment.id}' />"><i
																class="far fa fa-gears"></i> Edit Equipment</a></li>
														<li><a
															href="<c:url value='/oilSampleRoutine${equipment.id}' />"><i
																class="fa fa-plus-circle"></i> Add Oil Sample</a></li>
													</sec:authorize>
												</sec:authorize>
											</ul>
										</div>
									</td>
								</tr>








							</c:forEach>
						</tbody>
					</table>


				</div>
			</div>
		</div>
	</div>
</div>