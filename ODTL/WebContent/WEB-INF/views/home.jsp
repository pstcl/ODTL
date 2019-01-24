<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ODTL Reporting</title>


</head>

<body style="margin: 0;">

	<%@include file="header.jsp"%>

	<script>
		$(document).ready(function() {
			$("[data-toggle=popover]").popover({
				html : true
			})
		});
	</script>
	<%-- 	<%@include file="authheader.jsp"%> --%>
	<div id="app" class="container">

		<nav class="navbar navbar-expand-lg navbar-light bg-faded  fixed-top">
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

								<sec:authorize access="hasRole('ODTL')">
									<sec:authorize access="hasRole('JE')">
										<a class="nav-link" href="<c:url value='/downloadZips' />">Download
											all Reports</a>
									</sec:authorize>
								</sec:authorize>

				<ul class="navbar-nav">
					<sec:authorize access="hasRole('ODTL')">
						<li class="nav-item"><a class="nav-link"
							href="javascript:window.location='firstLoginPwdChange'"><span
								class="glyphicon glyphicon-home"></span>Change Password</a></li>

						<li><span class="navbar-text"></span></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/list' />">Manage Users</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/newuser' />">Add User</a></li>
							
					</sec:authorize>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/logout" />"><span
							class="glyphicon glyphicon-log-out"></span> Logout </a></li>
				</ul>

			</div>


		</nav>
	</div>


	<div class="container-fluid">
		<div class="row d-flex d-md-block flex-nowrap wrapper">
			<!-- 			<div class="col-md-3 float-left col-1 pl-0 pr-0 collapse width hide" -->
			<!-- 				id="sidebar"> -->



			<%-- 					<jsp:include page="filter.jsp"> --%>
			<%-- 						<jsp:param value="searchOilReports" name="actionName" /> --%>
			<%-- 					</jsp:include> --%>



			<!-- 			</div> -->
			<div class="col-md-9 float-left col px-5 pl-md-2 pt-2 main">


				<!-- 				<a href="#" data-target="#sidebar" data-toggle="collapse"><i -->
				<!-- 					class="fa fa-navicon fa-2x py-2 p-1"></i></a> -->

				<div class="page-header">
					<h4>Welcome ${loggedinuser}</h4>
				</div>
				<p class="lead">Reports portal for Oil Diagnotic and Testing
					Labs, PSTCL.</p>
				<hr>


				<sec:authorize access="hasRole('ODTL')">
					<sec:authorize access="hasRole('JE')">
						<div class="row">
							<div class="form-control col-sm-4">
								<a class="nav-link"
									href="javascript:window.location='newEquipment'">


									<div class="card">
										<img class="card-img-top"
											src="<c:url value='/static/img/tf1.jpg' />" alt="Equipment">
										<div class="card-body">
											<div class="app-title">
												Add Equipment<br>
											</div>
										</div>
									</div>

								</a>
							</div>
							<div class="form-control col-sm-4">


								<a href="#" role="button" class="nav-link btn popovers"
									data-toggle="popover" title=""
									data-content="<a href='addOilSampleRoutine' title='Regular'>Routine (In-service) Oil</a><br><a href='addNewOilSample' title='New(in Drums-SSD)'>New Oil(SS Design)</a><br><a href='addFreshOilSample' title='Fresh'>Fresh Oil(Grid Const.)</a>"
									data-original-title="Select Oil Type"><span>
										<div class="card">
											<img class="card-img-top"
												src="<c:url value='/static/img/sample1.jpg' />"
												alt="Add Sample">
											<div class="card-body">
												<div class="app-title">
													Add Oil Sample<br>
												</div>
											</div>
										</div>
								</span></a>
							</div>

							<div class="form-control col-sm-4">
								<a class="nav-link"
									href="javascript:window.location='addOilReport'">
									<div class="card">
										<img class="card-img-top"
											src="<c:url value='/static/img/report.png' />"
											alt="Add Report">
										<div class="card-body">
											<div class="app-title">
												Add Test Report<br>
											</div>
										</div>
									</div>
								</a>
							</div>



						</div>
					</sec:authorize>
				</sec:authorize>
				<div class="row">
					<div class="form-control col-sm-4">
						<a class="nav-link"
							href="javascript:window.location='equipmentListNew'">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value='/static/img/tf1.jpg' />" alt="Equipment">
								<div class="card-body">
									<div class="app-title">
										View Equipments<br>
									</div>
								</div>
							</div>
						</a>
					</div>
					<div class="form-control col-sm-4">
						<a class="nav-link"
							href="javascript:window.location='listOilSampleNew'">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value='/static/img/sample1.jpg' />"
									alt="View Sample">
								<div class="card-body">
									<div class="app-title">
										View Oil Samples<br>
									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="form-control col-sm-4">
						<a class="nav-link"
							href="javascript:window.location='listOilReport'">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value='/static/img/report.png' />" alt="Add Report">
								<div class="card-body">
									<div class="app-title">
										View Test Reports<br>
									</div>
								</div>
							</div>
						</a>
					</div>

				</div>


				<sec:authorize access="hasRole('ODTL')">
					<div class="row">
						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='listOilReportPublished'">
								<div class="card">
									<img class="card-img-top"
										src="<c:url value='/static/img/report.png' />"
										alt="Add Report">
									<div class="card-body">
										<div class="app-title">
											Published Reports<br>
										</div>
									</div>
								</div>
							</a>
						</div>
						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='approveOilReports'">
								<div class="card">
									<img class="card-img-top"
										src="<c:url value='/static/img/report.png' />"
										alt="Add Report">
									<div class="card-body">
										<div class="app-title">
											Approve Pending Reports<br>
										</div>
									</div>
								</div>
							</a>
						</div>
						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='listOilReportRejected'">
								<div class="card">
									<img class="card-img-top"
										src="<c:url value='/static/img/report.png' />"
										alt="Add Report">
									<div class="card-body">
										<div class="app-title">
											Rejected Reports <br>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>
				</sec:authorize>

				<!-- 				<div class="row"> -->
				<!-- 					<div class="form-control col-sm-4"> -->
				<!-- 						<a class="nav-link" -->
				<!-- 							href="javascript:window.location='equipmentListNew'"> -->
				<!-- 							<div class="card"> -->
				<!-- 								<img class="card-img-top" -->
				<%-- 									src="<c:url value='/static/img/tf1.jpg' />" alt="Equipment"> --%>
				<!-- 								<div class="card-body"> -->
				<!-- 									<div class="app-title"> -->
				<!-- 										Search Equipments<br> -->
				<!-- 									</div> -->
				<!-- 								</div> -->
				<!-- 							</div> -->
				<!-- 						</a> -->
				<!-- 					</div> -->

				<!-- 					<div class="form-control col-sm-4"> -->
				<!-- 						<a class="nav-link" -->
				<!-- 							href="javascript:window.location='listOilSampleNew'"> -->
				<!-- 							<div class="card"> -->
				<!-- 								<img class="card-img-top" -->
				<%-- 									src="<c:url value='/static/img/sample1.jpg' />" --%>
				<!-- 									alt="Search Sample"> -->
				<!-- 								<div class="card-body"> -->
				<!-- 									<div class="app-title"> -->
				<!-- 										Search Oil Samples<br> -->
				<!-- 									</div> -->
				<!-- 								</div> -->
				<!-- 							</div> -->
				<!-- 						</a> -->
				<!-- 					</div> -->
				<!-- 					<div class="form-control col-sm-4"> -->
				<!-- 						<a class="nav-link" -->
				<!-- 							href="javascript:window.location='listOilReportNew'"> -->
				<!-- 							<div class="card"> -->
				<!-- 								<img class="card-img-top" -->
				<%-- 									src="<c:url value='/static/img/report.png' />" alt="Add Report"> --%>
				<!-- 								<div class="card-body"> -->
				<!-- 									<div class="app-title"> -->
				<!-- 										Search Oil Reports<br> -->
				<!-- 									</div> -->
				<!-- 								</div> -->
				<!-- 							</div> -->
				<!-- 						</a> -->
				<!-- 					</div> -->

				<!-- 				</div> -->
			</div>
		</div>
	</div>
	<script>
		// sandbox disable popups
		if (window.self !== window.top && window.name != "view1") {
			;
			window.alert = function() {/*disable alert*/
			};
			window.confirm = function() {/*disable confirm*/
			};
			window.prompt = function() {/*disable prompt*/
			};
			window.open = function() {/*disable open*/
			};
		}

		// prevent href=# click jump
		document.addEventListener("DOMContentLoaded", function() {
			var links = document.getElementsByTagName("A");
			for (var i = 0; i < links.length; i++) {
				if (links[i].href.indexOf('#') != -1) {
					links[i].addEventListener("click", function(e) {
						console.debug("prevent href=# click");
						if (this.hash) {
							if (this.hash == "#") {
								e.preventDefault();
								return false;
							} else {
								/*
								var el = document.getElementById(this.hash.replace(/#/, ""));
								if (el) {
								  el.scrollIntoView(true);
								}
								 */
							}
						}
						return false;
					})
				}
			}
		}, false);
	</script>

	<%@include file="footer.jsp"%>
</body>
</html>