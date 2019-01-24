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
		var capacity = $("#capacity").val();
		var serialNo = $("#serialNo").val();
		//var equipmentID = $("#equipmentID").val();
		var make = $("#make").val();

		// 		$.post("/ODTL/filterEquipmentOBJ",{filterModel1:filterModel},populateTableContainer)
		// 				.done(function() {})
		// 				.fail(function(data, status, er) {alert("Couldn't load location information !" + data + er);});
		// 			}
		$.ajax({
			url : "/ODTL/filterEquipment",
			type : "POST",
			data : {
				circleSelected : circleSelected,
				divisionSelected : divisionSelected,
				substationSelected : substationSelected,
				capacity : capacity,
				serialNo : serialNo,
				equipmentID : null,
				make : make
			},
			success : function(response) {
				hideProgressBar();
				
				$("#tableContainer").replaceWith(response);
				$('body').css('display', 'block');

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
						aria-controls="collapseOne">Filter Equipments</button>
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



						<div class="form-control col-sm-4">

							<div class="col-sm-12">
								Make <input type="text" id="make" class="form-control input-sm" />

							</div>
						</div>


						<div class="form-control col-sm-4">

							<div class="col-sm-12">
								Capacity <input type="text" id="capacity"
									class="form-control input-sm" />

							</div>
						</div>

						<div class="form-control  col-sm-4">

							<div class="col-sm-12 col-sm-4">
								Serial No. <input type="text" id="serialNo"
									class="form-control input-sm" />

							</div>

						</div>

					</div>
					<div class="row">
						<div class="float-right">
							<input type="button" onclick="applyFilters()"
								value="Apply Filters" class="btn btn-primary btn-sm" /> <a
								class="btn btn-light btn-sm float-right"
								href="javascript:window.location='equipmentListNew'"><span
								class="glyphicon glyphicon-plus"></span>Clear Filters</a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

