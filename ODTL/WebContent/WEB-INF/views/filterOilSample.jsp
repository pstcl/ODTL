<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<script type="text/javascript">
	function applyFilters() {
		showProgressBar();
		
		
		$('#tableContainer').css('display', 'none');

		var circleSelected = $("#circleDD").val();
		var divisionSelected = $("#divisionDD").val();
		var substationSelected = $("#substationDD").val();
		var bottleNo = $("#bottleNo").val();
		var sampleNo = $("#sampleNo").val();
		//var referenceMemoDate = $("#referenceMemoDate").val();
		var referenceMemoNo = $("#referenceMemoNo").val();

		if ($("#reportDate").val() == "") {
			var newdate = new Date();
			newdate.setDate(new Date().getDate() - 15); // minus the date
			document.getElementById("reportDate").value = newdate.getFullYear()
					+ "-" + ("0" + (newdate.getMonth() + 1)).slice(-2);
		}

		var reportDateArray = $("#reportDate").val().split("-");

		var month = reportDateArray[1];
		var year = reportDateArray[0];

		var pendingSamples = $("#pendingSamples").prop('checked');
		// 		$.post("/ODTL/filterEquipmentOBJ",{filterModel1:filterModel},populateTableContainer)
		// 				.done(function() {})
		// 				.fail(function(data, status, er) {alert("Couldn't load location information !" + data + er);});
		// 			}
		$.ajax({
			url : "/ODTL/filterOilSample",
			type : "POST",
			data : {
				circleSelected : circleSelected,
				divisionSelected : divisionSelected,
				substationSelected : substationSelected,
				bottleNo : bottleNo,
				sampleNo : sampleNo,
				referenceMemoDate : null,
				referenceMemoNo : referenceMemoNo,
				pendingSamples : pendingSamples,
				month : month,
				year : year
			},
			success : function(response) {
				hideProgressBar();
				
				$("#tableContainer").replaceWith(response);
				$('#tableContainer').css('display', 'block');

			},
			error : function(data, status, er) {
				//alert("Error" + data + er);
			}
		});

		// 		$.ajax({
		// 			url : "/ODTL/filterEquipmentOBJ",
		// 			type : "POST",
		// 			contentType : "application/json",
		// 			data : JSON.stringify({
		// 				filterModel : filterModel
		// 			}),
		// 			success : function(response) {
		// 				$("#tableContainer").replaceWith(response);
		// 			},
		// 			error : function(data, status, er) {
		// 				alert("Error" + data + er);
		// 			}
		// 		});

		//).fail(function(data, status, er) {alert("Couldn't load location information !" + data + er);}
	}

	function populateTableContainer(data, status) {
		$("#tableContainer").replaceWith(data);
	}
</script>
<div id="accordion">
	<div class="card-header">
		<div class="card">
			<div class="card-header" id="headingOne">
				<h5 class="mb-0">
					<button class="btn btn-link" data-toggle="collapse"
						data-target="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne">Filter Oil Samples</button>
				</h5>
			</div>
		</div>

		<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
			data-parent="#accordion">
			<div class="card-body">



				<div>
					<div class="row">
						<%@include file="locationFilterView.jsp"%>

					</div>
					<div class="row">


						<div class="form-control col-md-4">
							<div class="col-sm-12">
								Reference Memo No<input type="text" id="referenceMemoNo"
									id="referenceMemoNo" class="form-control input-sm" />
							</div>
						</div>


						<div class="form-control col-md-4">

							Month of Oil Sample

							<!-- 						<input id="reportDate" type="text" -->
							<!-- 							class="date-picker" class="form-control input-sm" /> -->
							<input id="reportDate" type="month" class="form-control input-sm" />


						</div>




						<div class="form-control col-md-4">

							<div class="col-sm-12">
								Sample No <input type="text" id="sampleNo"
									class="form-control input-sm" />

							</div>
						</div>

					</div>
					<div class="row">


						<div class="form-control col-md-4">

							<div class="col-sm-12">
								Bottle No <input type="text" id="bottleNo"
									class="form-control input-sm" />

							</div>
						</div>

						<div class="form-control  col-md-4">

							<label class="switch"> Show only Pending<c:if
									test="${pendingSamples}">
									<input type="checkbox" id="pendingSamples" checked="checked">
								</c:if> <c:if test="${not pendingSamples}">
									<input type="checkbox" id="pendingSamples">
								</c:if> <span class="slider"></span>
							</label>
						</div>


						<div class="row">
							<div class="float-right">
								<input type="button" onclick="applyFilters()"
									value="Filter Oil Samples" class="btn btn-primary btn-sm" /> <a
									class="btn btn-light btn-sm float-right"
									href="javascript:window.location='listOilSampleNew'"><span
									class="glyphicon glyphicon-plus"></span>Clear Filters</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>