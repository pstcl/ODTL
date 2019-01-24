<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="tableContainer">
	<div class="container-fluid">
		<div class="card card-default">
			<c:if test="${not empty success}">

				<div class="alert alert-success lead">${success}</div>
			</c:if>

			<div class="card-heading">
				<span class="lead">List of Samples </span>
			</div>


			<table id="myDataTable"
				class="table table-striped table-bordered table-hover">
				<thead>

					<tr>
					<tr>

						<th>Sample No.</th>

						<th>Circle</th>
						<th>Division</th>
						<th>Sub-Station</th>
						<th>Equipment</th>
						<th>Sample Receipt Date</th>
						<th>Bottle No</th>
						<th>Reference No</th>

						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${oilSamples}" var="oilSample"
						varStatus="varStatus">
						<tr>
							<%-- 							<td>${varStatus.index+1}</td> --%>
							<td><a href="<c:url value='/viewSample-${oilSample.id}'/>">${oilSample.sampleNo}</a>
							</td>



							<td>${oilSample.equipment.circle.circleDescription}</td>
							<td>${oilSample.equipment.division.divisionDescription}</td>
							<td>${oilSample.equipment.substation.substationDescription}</td>



							<td><a
								href="<c:url value='/viewEquipment-${oilSample.equipment.id}'/>">${oilSample.equipment.equipmentType}
									( ${oilSample.equipment.equipmentID}) <c:if
										test="${not empty oilSample.sampleTakenFrom}">(${oilSample.sampleTakenFrom})</c:if>
							</a></td>

							<td><fmt:formatDate value="${oilSample.sampleReceiptDate}"
									pattern="dd/MM/yyyy" /></td>
							<td>${oilSample.bottleNo}</td>

							<td>${oilSample.referenceMemoNo}</td>


							<sec:authorize access="hasRole('ADMIN')">
								<td>
									<div class="dropdown">
										<button class="btn btn-primary dropdown-toggle" type="button"
											data-toggle="dropdown">
											Options <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">



											<c:choose>

												<c:when test="${oilSample.oilReport.id == null}">
													<sec:authorize access="hasRole('JE')">
														<sec:authorize access="hasRole('ODTL')">
															<li><a
																href="<c:url value='/newOilReport${oilSample.id}' />">Add
																	Oil Report</a></li>
														</sec:authorize>

														<li><a
															href="<c:url value='/edit-oilSample-${oilSample.id}' />">Edit
																Sample</a></li>
													</sec:authorize>
													<sec:authorize access="hasRole('AEE') or hasRole('ASE')">
														Report not prepared yet
														</sec:authorize>
												</c:when>
												<c:otherwise>

													<sec:authorize
														access="hasRole('AEE') or hasRole('ASE') or hasRole('ADMIN') or hasRole('ODTL')">
														<li><a
															href="<c:url value='/viewSampleReport${oilSample.id}' />">View
																Report</a></li>
													</sec:authorize>
												</c:otherwise>
											</c:choose>
							</sec:authorize>


						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div><%@include file="footer.jsp"%>
