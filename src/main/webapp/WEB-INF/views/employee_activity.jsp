<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">		
		<title> Employee  Activities</title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="<%=request.getContextPath() %>/resources/datetimepicker/js/jquery.min.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/admin/js/FileSaver.js"	type="text/javascript"></script>
	</head>	
	<body ng-app="MyApp" ng-controller="employeeActivitiesCtrl" ng-cloak ng-init="getCurrentMonthData()" class="skin-blue sidebar-collapse sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header" style="margin-bottom:10px;">
					<h1>
						Manage Employee Activity
					</h1>
					<ol class="breadcrumb">
						<li><button class="btn btn-success" data-toggle="modal" data-target="#importModal">Import Employee Activities</button></li>
						<li><button class="btn btn-success" ng-click="exportData()">Export Employee Activities</button></li>
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Employee Activity</li>
					</ol>
				</section>
				<section class="content">
					<!-- <div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title"> Add Enquiry</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
					</div> -->
					<div class="row">
						<div class="col-md-12 text-right">
							<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-3 text-left">
											<h3 class="box-title">Employee Activities List</h3>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-12 col-xs-12 demo-section k-content">
											<input class="kendoDate" id="fromdate" ng-model="fromdate" title="datepicker" ng-change="getCurrentMonthData()"/>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-12 col-xs-12 demo-section k-content">
											<input class="kendoDate" id="todate" ng-model="todate" title="datepicker" style="width: 100%" ng-change="getCurrentMonthData()"/>
										</div>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchEnquiry() : null"/>
												<span class="input-group-btn">
													<button type="button" name="search" id="search-btn" ng-click="searchEnquiry()" class="btn btn-flat"><i class="fa fa-search"></i></button>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive" id="exportable">
										<table class="table no-margin">
											<thead>
												<tr>
													<th class="text-left" width="20%">Member Name</th>
													<th ng-show="item.employeeCode==15000" class="text-center" width="20%">Date</th>
													<th class="text-right" width="10%">Total Hours</th>
													<th class="text-right" width="10%">Regular Hours</th>
													<th class="text-right" width="15%">Total Over Time</th>
													<th class="text-right" width="15%">Regular Salary</th>
													<th class="text-right" width="15%">Over Time Salary</th>
													<th class="text-right" width="10%">Total</th>
													<th class="text-center" width="5%">Details</th>
												</tr>
											</thead>
											<tbody>
												<tr class="text-center" ng-if="getEmployeeActivities == ''">
													<td colspan="7"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
												</tr>
												<tr ng-repeat="item in getEmployeeActivities | filter:search" style="cursor:pointer; cursor:hand;">
													<td class="text-left">{{item.firstName}} {{item.lastName}}</td>
													<td class="text-center" ng-show="item.employeeCode==15000">{{fromdate}} - {{todate}}</td>
													<td class="text-right">{{item.hoursWorked | number:2}}</td>
													<td class="text-right">{{item.regularWorked | number:2}}</td>
													<td class="text-right">{{item.totalOverTime | number:2}}</td>
													<td class="text-right">{{item.regularSalary | number:2}}</td>
													<td class="text-right">{{item.overTimeSalary | number:2}}</td>
													<td class="text-right">{{item.overTimeSalary + item.regularSalary | number:2}}</td>
													<td class="text-center" ng-click="getEmployeeDetail(item.employeeCode)" title="Details" data-toggle="modal" data-target="#editModal" ><a href="#"><i class="fa fa-eye" aria-hidden="true"></i></a></td>
												</tr>
											</tbody>
											<tfoot ng-if="getEnquiries != ''">
												<tr>
													<td colspan="7">
														Total Amount :  {{totalEmployeeSalary | number:2}}
													</td>											
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
								<div class="box-footer">
									<div class="row">
									</div>
								</div>
							</div>					
						</div>
					</div>
				</section>
			</div>
		</div>
		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Details</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-right">
								<div class="box box-tgsc">
									<div class="box-body">
										<div class="table-responsive" id="exportable1">
											<table class="table no-margin">
												<thead>
													<tr>
														<th class="text-left" width="20%">Member Name</th>
														<th class="text-left" width="10%">Code</th>
														<th class="text-center" width="15%">Date</th>
														<th class="text-right" title="Hours Worked" width="5%">HW</th>
														<th class="text-right" title="Over Time" width="5%">OT</th>
														<th class="text-right" title="Over Time Wages" width="10%">OTW</th>
														<th class="text-right" title="Over Time Salary" width="10%">OT Salary</th>
														<th class="text-right" title="Regular Salary" width="10%">R Salary</th>
														<th class="text-center" title="Employee Status" width="10%">Status</th>
													</tr>
												</thead>
												<tbody>
													<tr class="text-center" ng-if="getEmployeeDetails == ''">
														<td colspan="7"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
													</tr>
													<tr ng-repeat="item in getEmployeeDetails" style="cursor:pointer; cursor:hand;">
														<td class="text-left">{{item.firstName}} {{item.lastName}}</td>
														<td class="text-left">{{item.employeeCode}}</td>
														<td class="text-center">{{item.date}}</td>
														<td class="text-right">{{item.hoursWorked | number:2}}</td>
														<td class="text-right">{{item.overTime | number:2}}</td>
														<td class="text-right">{{item.overTimeWages | number:2}}</td>
														<td class="text-right">{{item.overTimeSalary | number:2}}</td>
														<td class="text-right">{{item.regularSalary | number:2}}</td>
														<td class="text-center">{{item.employeeStatus}}</td>
													</tr>
												</tbody>
												<tfoot ng-if="getEnquiries != ''">
													<tr>
														<td colspan="7">
															<div class="alert alert-info" ng-show="infodelete == 1">
																<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
															</div>
														</td>											
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
									<div class="box-footer">
										<div class="row">
										</div>
									</div>
								</div>					
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">								
							<div class="col-md-8 text-left">
								<strong ng-show="brandSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
								<strong ng-show="brandSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
							</div>
							<div class="col-md-4 text-right">
								<button type="submit" ng-click="exportData1()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Export</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
							</div>
						</div>					
					</div>
				
				</div>
			</div>
		</div>
		<div id="importModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-6">
								<h4 class="modal-title"> <i class="fa fa-cloud-download" aria-hidden="true"></i> Import Employee Activities </h4>								
							</div>
							<div class="col-md-6 text-right">
								<h5><a href="<%=request.getContextPath() %>/resources/admin/Sample/ImportSampleFile.xls" download> <i class="fa fa-download" aria-hidden="true"></i> Download Sample Sheet</a></h5>
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<input type="file" class="form-control" name="employeefile" id="employeefile" ng-model="employeefile" accept=".xls">
									<p ng-show="errorFile == 1" style="color: red; font-size: 14px;">{{msgFile}}</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-6 text-left">
								<label style="color: green" ng-if="submitSuccess == 1">{{msgSuccess}}</label>
								<label style="color: red" ng-if="submitError == 1">{{msgError}}</label>
							</div>
							<div class="col-md-6 text-right">
								<button type="submit" class="btn btn-success" ng-click="importEmployeeActivities()"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>Submit</button>
								<input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancel">	
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/employee_activity.js"></script>
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		
		<script>
			document.getElementById("manage").classList.add("active");
			document.getElementById("enquiry").classList.add("active");
			
			$("#datepicker,#datepicker1,#datepicker2,#datepicker3").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true,
				value: new Date()
			});
			
			$("#fromdate").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true,
			});
			$("#todate").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true,
			});
		    
			$(".KendoDate").bind("focus", function(){
	   			$(this).data("kendoDatePicker").open(); 
			});
			$(".KendoDateTime").bind("focus", function(){
    			$(this).data("kendoDateTimePicker").open(); 
			});
			$("#datetimepicker, #datetimepicker1, #datetimepicker2").kendoDateTimePicker({
	           	format: "dd/MM/yyyy hh:mm tt",
				value: new Date(),					
	            dateInput: true
            });
			$(function () {
				$(".select2").select2();
			});			
		</script>
	</body>
</html>