<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<script type="text/javascript">
	$('div .checkbox').click(function() {
		checkedState = $(this).attr('checked');
		$(this).parent('div').children('.checkbox:checked').each(function() {
			$(this).attr('checked', false);
		});
		$(this).attr('checked', checkedState);
	});
</script>
<!-- <script type="text/javascript"> -->

<!-- // 	$(function() { -->
<!-- // 	    $('.date-picker').datepicker({ -->
<!-- // 	          dateFormat: 'MM yy', -->
<!-- // 	          changeMonth: true, -->
<!-- // 	          changeYear: true, -->
<!-- // 	          showButtonPanel: true, -->
<!-- // 	          onClose: function (dateText, inst) { -->
<!-- // 	              var month = inst.dpDiv.find('.ui-datepicker-month').find(':selected').val(); -->
<!-- // 	              var   year = inst.dpDiv.find('.ui-datepicker-year').find(':selected').val(); -->
<!-- // 	              $("#reportDate").datepicker('setDate', new Date(year, month, 1)); -->
<!-- // 	             } -->
<!-- // 	     }); -->
<!-- // 	    $("#reportDate").datepicker('setDate', new Date(2018, 03, 1)); -->

<!-- // 	}); -->
<!-- </script> -->
<script type="text/javascript">
	function applyFilters() {
		
		showProgressBar();
		
		$('#tableContainer').css('display', 'none');
		$('#loader').css('display', 'block');
		
		var circleSelected = $("#circleDD").val();
		var divisionSelected = $("#divisionDD").val();
		var substationSelected = $("#substationDD").val();
		var sampleNo = $("#sampleNo").val();

		if ($("#reportDate").val() == "") {
			var newdate = new Date();
			newdate.setDate(new Date().getDate() - 15); // minus the date
			document.getElementById("reportDate").value = newdate.getFullYear()
					+ "-" + ("0" + (newdate.getMonth() + 1)).slice(-2);
		}

		var reportDateArray = $("#reportDate").val().split("-");

		var month = reportDateArray[1];
		var year = reportDateArray[0];

		var finalReport = $("#finalReport").prop('checked');
		var aeeApproval = $("#aeeApproval").prop('checked');
		var rejectedReport = $("#rejectedReport").prop('checked');

		// 		$.post("/ODTL/filterEquipmentOBJ",{filterModel1:filterModel},populateTableContainer)
		// 				.done(function() {})
		// 				.fail(function(data, status, er) {alert("Couldn't load location information !" + data + er);});
		// 			}
		$.ajax({
			url : "/ODTL/filterOilReport",
			type : "POST",
			data : {
				circleSelected : circleSelected,
				divisionSelected : divisionSelected,
				substationSelected : substationSelected,

				sampleNo : sampleNo,
				reportDateStart : null,
				reportDateEnd : null,
				month : month,
				year : year,
				finalReport : finalReport,
				aeeApproval : aeeApproval,
				rejectedReport : rejectedReport

			},
			success : function(response) {
				$("#tableContainer").replaceWith(response);
				$('#tableContainer').css('display', 'block');
				hideProgressBar();
				$('#loader').css('display', 'none');

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

<script type="text/javascript">
var customModal;
customModal = customModal || (function () {
    var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div></div></div>');
    return {
        showPleaseWait: function() {
            pleaseWaitDiv.modal();
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();</script>

<div id="accordion">
	<div class="card-header">
		<div class="card">
			<div class="card-header" id="headingOne">
				<h5 class="mb-0">
					<button class="btn btn-link" data-toggle="collapse"
						data-target="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne">Filter Reports</button>
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
							Month of Report

							<!-- 						<input id="reportDate" type="text" -->
							<!-- 							class="date-picker" class="form-control input-sm" /> -->
							<input id="reportDate" type="month" class="form-control input-sm" />


						</div>
						<!-- 					<div class="form-control col-md-4"> -->
						<!-- 						Month <select id="month"> -->
						<!-- 							<option value="">All Months</option> -->
						<!-- 							<option value="1">Jan</option> -->
						<!-- 							<option value="2">Feb</option> -->
						<!-- 							<option value="3">March</option> -->
						<!-- 							<option value="4">April</option> -->
						<!-- 							<option value="5">May</option> -->
						<!-- 							<option value="6">June</option> -->
						<!-- 							<option value="7">July</option> -->
						<!-- 							<option value="8">Aug</option> -->
						<!-- 							<option value="9">Sept</option> -->
						<!-- 							<option value="10"  selected="selected">Oct</option> -->
						<!-- 							<option value="11">Nov</option> -->
						<!-- 							<option value="12">Dec</option> -->

						<!-- 						</select> Year<select id="year"> -->

						<!-- 							<option value="2018" selected="selected">2018</option> -->
						<!-- 							<option value="2019">2019</option> -->
						<!-- 							<option value="2020">2020</option> -->

						<!-- 						</select> -->
						<!-- 					</div> -->




						<div class="form-control col-md-4">


							Sample No <input type="text" id="sampleNo"
								class="form-control input-sm" />


						</div>
						<div class="form-control col-md-4">




							<label class="switch"> Published<c:if
									test="${finalReport}">
									<input type="checkbox" id="finalReport" checked="checked">
								</c:if> <c:if test="${not finalReport}">
									<input type="checkbox" id="finalReport">
								</c:if> <span class="slider"></span>
							</label> <label class="switch"> Approved by AEE<c:if
									test="${aeeApproval}">
									<input type="checkbox" id="aeeApproval" checked="checked">
								</c:if> <c:if test="${not aeeApproval}">
									<input type="checkbox" id="aeeApproval">
								</c:if> <span class="slider"></span>
							</label> <label class="switch"> Rejected<c:if
									test="${rejectedReport}">
									<input type="checkbox" id="rejectedReport" checked="checked">
								</c:if> <c:if test="${not rejectedReport}">
									<input type="checkbox" id="rejectedReport">
								</c:if> <span class="slider"></span>
							</label>
						</div>
					</div>

					<div class="row">
						<div class="float-right">
							<input type="button" onclick="applyFilters()"
								value="Filter Reports" class="btn btn-primary btn-sm" /> <a
								class="btn btn-light btn-sm float-right"
								href="javascript:window.location='listOilReport'"><span
								class="glyphicon glyphicon-plus"></span>Clear Filters</a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>


