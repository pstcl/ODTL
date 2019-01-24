<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="tableContainer">
	<div class="container-fluid">
		<div class="card card-default">
			<!-- Default card contents -->

			<c:if test="${not empty success}">

				<div class="alert alert-success lead">${success}</div>
			</c:if>
			<div class="card-heading">
				<span class="lead"> OilReports </span>
			</div>
			<div class="card-body">

				<div class="row">

					<table id="myDataTable"
						class="table table-striped table-bordered table-hover">
						<thead class="thead-light">
							<tr>

								<th scope="col">#</th>

								<th>Sample No:</th>
								<th>Sample Date</th>
								<th>Circle</th>
								<th>Division</th>
								<th>Substation</th>


								<th>Report Date</th>
								<th>Equipment</th>
								<th></th>



							</tr>
						</thead>
						<tbody>



							<c:forEach items="${oilReports}" var="oilReport"
								varStatus="indexStatus">




								<c:choose>
									<c:when test="${oilReport.approvalAEE eq -100}">
										<tr class="table-warning">

											<td><span> <i class="fa fa-warning"
													style="font-size: 18px; color: orange;"> Rejected by
														AEE</i>
											</span></td>
									</c:when>
									<c:when test="${oilReport.approvalASE eq -100}">
										<tr class="table-danger">

											<td><span> <i class="fa fa-warning"
													style="font-size: 18px; color: red"> Rejected by ASE</i>
											</span></td>
									</c:when>
									<c:otherwise>
										<tr>

											<td><c:choose>
													<c:when test="${oilReport.approvalASE eq 100}">
														<span> <i class="fa fa-warning"
															style="font-size: 18px; color: Blue">Published</i>
														</span>
													</c:when>

													<c:when test="${oilReport.approvalAEE eq 100}">
														<span> <i class="fa fa-warning"
															style="font-size: 18px; color: Green">(1) Approvals
																Pending: ASE</i>
														</span>
													</c:when>
													<c:otherwise>

														<span> <i class="fa fa-warning"
															style="font-size: 18px; color: Black">(2) Approvals
																Pending: AEE,ASE</i>

														</span>
													</c:otherwise>
												</c:choose></td>
									</c:otherwise>
								</c:choose>
								<td>${oilReport.oilSample.sampleNo}</td>
								<td><fmt:formatDate
										value="${oilReport.oilSample.sampleReceiptDate}"
										pattern="dd/MM/yyyy" /></td>
								<td>${oilReport.equipment.circle.circleDescription}</td>
								<td>${oilReport.equipment.division.divisionDescription}</td>
								<td>${oilReport.equipment.substation.substationDescription}</td>

								<td><fmt:formatDate value="${oilReport.reportDate}"
										pattern="dd/MM/yyyy" /></td>
								<td>${oilReport.equipment.equipmentType}(
									${oilReport.equipment.equipmentID}) <c:if
										test="${not empty oilReport.oilSample.sampleTakenFrom}">(${oilReport.oilSample.sampleTakenFrom})</c:if>
								</td>



								<td><c:choose>
										<c:when test="${oilReport.approvalASE eq 100}">
											<a target="_blank"
												href="<c:url value='/pdfOilReport-${oilReport.id}' />"><i
												class="nav-link"></i> Download Report</a>
										</c:when>
										<c:when test="${oilReport.approvalASE ne 100}">
											<sec:authorize access="hasRole('ODTL')">
												<sec:authorize access="hasRole('AEE')">
													<c:if test="${oilReport.approvalAEE eq -10}">
													<a class="nav-link"
														href="<c:url value='/aeeRemarksOilReport${oilReport.id}' />">
														Approve/Reject</a>
													</c:if>
												</sec:authorize>

												<sec:authorize access="hasRole('ASE')">
													<a class="nav-link"
														href="<c:url value='/aseRemarksOilReport${oilReport.id}' />">Approve/Reject</a>
												</sec:authorize>

												<sec:authorize access="hasRole('JE')">

													<a
														href="<c:url value='/oilReport-options-${oilReport.id}' />">Options</a>
												</sec:authorize>
											</sec:authorize>

										</c:when>
									</c:choose></td>

								<%-- 								<td><sec:authorize access="hasRole('ODTL')"> --%>
								<!-- 										<div class="dropdown"> -->
								<!-- 											<button class="btn btn-primary dropdown-toggle" type="button" -->
								<!-- 												data-toggle="dropdown"> -->
								<!-- 												Options <span class="caret"></span> -->
								<!-- 											</button> -->
								<!-- 											<ul class="dropdown-menu"> -->
								<%-- 												<c:choose> --%>
								<%-- 													<c:when test="${oilReport.approvalASE eq 100}"> --%>
								<!-- 														<li><a target="_blank" -->
								<%-- 															href="<c:url value='/pdfOilReport-${oilReport.id}' />"><i --%>
								<!-- 																class="far fa-file-pdf"></i> View as PDF</a></li> -->
								<%-- 													</c:when> --%>
								<%-- 													<c:otherwise> --%>
								<!-- 														<li><a href="#" -->
								<%-- 															onclick="previewReport('<c:url value='/previewPdfOilReport-${oilReport.id}'/>')">Preview</a> --%>

								<!-- 														</li> -->
								<%-- 													</c:otherwise> --%>
								<%-- 												</c:choose> --%>



								<%-- 												<sec:authorize access="hasRole('ODTL')"> --%>

								<%-- 													<c:choose> --%>
								<%-- 														<c:when test="${oilReport.approvalASE ne 100}"> --%>
								<%-- 															<sec:authorize access="hasRole('JE')"> --%>
								<!-- 																<li><a -->
								<%-- 																	href="<c:url value='/edit-oilReport-${oilReport.id}' />">Edit --%>
								<!-- 																		Report</a></li> -->
								<%-- 															</sec:authorize> --%>
								<%-- 														</c:when> --%>
								<%-- 														<c:when test="${oilReport.approvalAEE ne 100}"> --%>
								<%-- 															<sec:authorize access="hasRole('JE')"> --%>
								<!-- 																<li><a -->
								<%-- 																	href="<c:url value='/edit-oilReport-${oilReport.id}' />">Edit --%>
								<!-- 																		Report</a></li> -->
								<%-- 															</sec:authorize> --%>
								<%-- 														</c:when> --%>
								<%-- 													</c:choose> --%>
								<%-- 													<c:if test="${oilReport.approvalASE ne 100}"> --%>
								<%-- 														<sec:authorize access="hasRole('AEE')"> --%>
								<!-- 															<li><a -->
								<%-- 																href="<c:url value='/aeeRemarksOilReport${oilReport.id}' />"> --%>
								<!-- 																	Approve/Reject</a></li> -->
								<%-- 														</sec:authorize> --%>

								<%-- 														<sec:authorize access="hasRole('ASE')"> --%>
								<!-- 															<li><a -->
								<%-- 																href="<c:url value='/aseRemarksOilReport${oilReport.id}' />">Approve/Reject</a></li> --%>
								<%-- 														</sec:authorize> --%>
								<%-- 													</c:if> --%>
								<%-- 												</sec:authorize> --%>
								<!-- 											</ul> -->
								<!-- 										</div> -->
								<%-- 									</sec:authorize> <c:if test="${oilReport.approvalASE eq 100}"> --%>
								<!-- 										<a target="_blank" -->
								<%-- 											href="<c:url value='/pdfOilReport-${oilReport.id}' />"><i --%>
								<!-- 											class="far fa-file-pdf"></i> View Report</a> -->
								<%-- 									</c:if></td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
</div>