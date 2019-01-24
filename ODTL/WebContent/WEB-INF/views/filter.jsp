<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<script type="text/javascript">
	function submitform() {
		document.forms['oilReportForm'].submit();
	}
	function selectEquipment() {
		document.forms['oilReportForm'].action = "equipmentFilterForReport";
		document.forms['oilReportForm'].submit();

	}
</script>

<script>
	$(document)
			.ready(
					function() {
						$("#filter-panel")
								.on(
										"hide.bs.collapse",
										function() {
											$("#btn1")
													.html(
															' <i class="fa fa-filter" style="font-size: 18px"> </i> Filter');
										});
						$("#filter-panel")
								.on(
										"show.bs.collapse",
										function() {
											$("#btn1")
													.html(
															' <i class="fas fa-window-close"></i> </i> Close');
										});
					});
</script>



<div class="card card-default">

	<div class="card-body">
		<div class="filter-panel">
			<button id="btn1" type="button"
				class="btn btn-light btn-sm float-right" data-toggle="collapse"
				data-target="#filter-panel">
				<i class='fa fa-filter' style='font-size: 18px'> Filter </i>
			</button>


			<c:if test="${filterOpen}">


				<div id="filter-panel" class="show">
			</c:if>
			<c:if test="${!filterOpen}">


				<div id="filter-panel" class="collapse">
			</c:if>

			<form:form id="oilReportForm" method="POST" modelAttribute="filter"
				action="listOilReportNew" class="form-horizontal">
				<div class="row">

					<div class="form-control col-sm-2">

						<div class="col-sm-12">

							Circle
							<form:select onchange="submitform()" id="circleDD"
								path="equipment.circle" multiple="false"
								class="form-control input-sm">
								<form:option value="" label="Select Circle"></form:option>
								<form:options items="${circleList}" itemValue="circleCode"
									itemLabel="circleDescription" />
							</form:select>


						</div>
					</div>

					<div class="form-control col-sm-2">
						<div class="col-sm-12">
							Division
							<form:select onchange="submitform()" path="equipment.division"
								multiple="false" class="form-control input-sm">
								<form:option value="" label="Select Division"></form:option>
								<form:options items="${divisionList}" itemValue="divisionCode"
									itemLabel="divisionDescription" />
							</form:select>
						</div>
					</div>

					<div class="form-control col-sm-2">
						<div class="col-sm-12">
							Substation

							<form:select onchange="submitform()" path="equipment.substation"
								multiple="false" class="form-control input-sm">
								<form:option value="${null}" label="Select Substation"></form:option>
								<form:options items="${substationList}"
									itemValue="substationCode" itemLabel="substationDescription" />
							</form:select>

						</div>
					</div>


					<div class="form-control col-sm-2">
						<div class="col-sm-12">
							Equipment
							<form:select onchange="selectEquipment()" path="equipment"
								multiple="false" class="form-control input-sm">
								<form:option value="${null}" label="Select Equipment"></form:option>
								<form:options items="${equipments}" itemValue="id"
									itemLabel="equipmentID" />
							</form:select>

						</div>
					</div>


					<sec:authorize access="hasRole('ODTL')">
						<div class="form-control col-sm-2">

							<div class="col-sm-12">
								Approved?
								<form:checkbox path="finalReport" onclick="submitform()" />
							</div>
						</div>
					</sec:authorize>
					<div class="col-md-2">

						<a class="btn btn-primary"
							href="javascript:window.location='listOilReport'"><span
							class="glyphicon glyphicon-plus"></span>Clear Filters</a>

					</div>
				</div>
			</form:form>

		</div>
	</div>
</div>
</div>