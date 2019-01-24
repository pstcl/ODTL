<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript">
	function submitform() {

		document.forms['userEntryForm'].action = 'setLocationForUser';
		document.forms['userEntryForm'].submit();

	}
</script>
<title>User Registration Form</title>
</head>

<body style="margin: 0;">
	<%@include file="authheader.jsp"%>

	<div id="page-wrapper" class="generic-container">
		<div class="well lead">User Registration Form</div>
		<form:form method="POST" action="saveUser" id="userEntryForm"
			modelAttribute="user" class="form-horizontal">
			<form:input type="hidden" path="id" id="id" />


			<!-- 			<div class="row"> -->
			<!-- 				<div class="form-control col-md-12"> -->
			<!-- 					<label class="col-md-3 form-control-label" for="officeConcerned">Office -->
			<!-- 					</label> -->
			<!-- 					<div class="col-md-7"> -->

			<%-- 						<form:select path="officeConcerned" class="form-control input-sm" --%>
			<%-- 							multiple="false"> --%>
			<%-- 							<form:option value="${null}" label="Select Office" /> --%>

			<%-- 							<form:options items="${offices}" itemValue="id" --%>
			<%-- 								itemLabel="officeName" /> --%>



			<%-- 						</form:select> --%>

			<!-- 						<div class="has-error"> -->
			<%-- 							<form:errors path="officeConcerned" class="help-inline" /> --%>
			<!-- 						</div> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<!-- 			</div> -->




			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="circle">Circle
						Name</label>
					<div class="col-sm-12">


						<form:select onchange="submitform()" id="circleDD" path="circle"
							multiple="false" class="form-control input-sm">
							<form:option value="" label="Select Circle"></form:option>
							<form:options items="${circleList}" itemValue="circleCode"
								itemLabel="circleDescription" />
						</form:select>

						<div class="has-error">
							<form:errors path="circle" class="alert alert-danger" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="division">Division
						Name</label>
					<div class="col-sm-12">

						<form:select onchange="submitform()" path="division"
							multiple="false" class="form-control input-sm">
							<form:option value="" label="Select Division"></form:option>
							<form:options items="${divisionList}" itemValue="divisionCode"
								itemLabel="divisionDescription" />
						</form:select>
						<div>
							<form:errors path="division" class="alert alert-danger" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-control col-sm-4">
					<label class="col-sm-6 form-control-label" for="substation">Sub-station</label>
					<div class="col-sm-12">


						<form:select onchange="submitform()" path="substation"
							multiple="false" class="form-control input-sm">
							<form:option value="${null}" label="Select Substation"></form:option>
							<form:options items="${substationList}"
								itemValue="substationCode" itemLabel="substationDescription" />
						</form:select>
						<div>
							<form:errors path="substation" class="alert alert-danger" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="firstName">First
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="lastName">Last
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="ssoId">User
						Name</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="ssoId" id="ssoId"
									class="form-control input-sm" disabled="true" />
								<form:input type="hidden" path="ssoId" id="ssoId"
									class="form-control input-sm" />
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="ssoId" class="help-inline" />
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="designation">Designation</label>
					<div class="col-md-7">
						<form:select path="designation" items="${designationList}"
							multiple="false" itemValue="designationCode"
							itemLabel="designation" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="designation" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

<!-- 			<div class="row"> -->
<!-- 				<div class="form-control col-md-12"> -->
<!-- 					<label class="col-md-3 form-control-label" for="seniors">Reporting -->
<!-- 						Officer(s)</label> -->
<!-- 					<div class="col-md-7"> -->
<%-- 						<form:select path="seniors" items="${colleagues}" multiple="true" --%>
<%-- 							itemValue="id" itemLabel="ssoId" class="form-control input-sm" /> --%>
<!-- 						<div class="has-error"> -->
<%-- 							<form:errors path="seniors" class="help-inline" /> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="row">
				<div class="form-control col-md-12">
					<label class="col-md-3 form-control-label" for="userProfiles">Roles</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true"
							itemValue="id" itemLabel="label" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/list' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/list' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div><%@include file="footer.jsp"%>
</body>
</html>