<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">		
		<title> Enquiries </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />		
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/enquiry.js"></script>
		
			<link   rel="stylesheet"	 href="<%=request.getContextPath() %>/resources/admin/css/responsive.css">
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>
		
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />		
		
		 <link href='https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/ui-lightness/jquery-ui.css'rel='stylesheet'>
      
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" >    </script>
      
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" >    </script>
		
		
		<style type="text/css">
			#productModal, #editModal  { overflow-y:scroll };
			


		</style>				
	</head>	
	<body ng-app="MyApp" ng-controller="enquiryCtrl" ng-cloak class="skin-blue sidebar-collapse sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Task
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">TASK</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title"> Add Task</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<form>						
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<div class="alert alert-success" ng-show="success == 1">
											<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
										</div>
										<div class="alert alert-info" ng-show="info == 1">
											<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
										</div>
									</div>
								</div>							
								<div class="row">
									<div class="col-md-4" ng-show="<%= session.getAttribute("usertypeidadmin") %> ==1 ">
										<label>Task Date<font color="red" size="3">*</font></label>
										<div class="input-group">
											<input class="KendoDate" style="width: 250px;" id="datepicker"  ng-model="Task.taskDate"title="datepicker" />
										</div>
									</div>
									
									
								</div>														
								
								<br>
								<div class="row">
								
								<%if((Integer)session.getAttribute("usertypeidadmin") != 3){%>
									<div class="col-md-4">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign to</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-10">
															<div class="input-group">
																<select id="employeeidadd" name="employeeidadd" ng-model="employeeidadd" class="form-control" ng-change="setFlag()">
																	<option value="">Employee</option>
																	<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																</select>
																<span class="input-group-btn">
																	<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Employee"><i class="fa fa-plus"></i></button>
																</span>
															</div>
															<p ng-show="errorEmpName == 1" style="color: red;">{{msgEmpName}}</p>
														</div>	
														<div class="col-md-2">
															<div class="form-group">
																<a href="#" ng-click="addAssignTaskRow()" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>													
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Employee Name </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in assigntasklist">
																	<td> {{item.firstName}} {{item.lastName}} </td>																															
																	<td>
																		<a href="#" ng-click="removeAssignTaskRow(item)" class="delete" data-toggle="modal">
																			<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																		</a>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
									<%}%>
									
																	
									<div class="col-md-8">
										<div class="form-group">
										
											<textarea rows="8" id="remarksadd" name="remarksadd" ng-model="Task.Description" placeholder="Description..." class="form-control"></textarea>										
										</div>
									</div>								
								</div>						
							</div>
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="success == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{message}}</strong>
										<strong ng-show="info == 1" style="color: red;"><span class="fa fa-info-circle"></span> {{message}}</strong>										
									</div>
									<div class="col-md-4 text-right">
										<button type="submit" ng-click="addTaskList()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="row">
						
						<div class="col-md-12">
							<div class="hint-text">&nbsp;</div>
						
								<!-- <div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin">											
											<tbody>												
												<tr ng-show="errorFollowupStatus == 1">
													<td colspan="3" class="text-center"><p style="color: red;">{{msgFollowupStatus}}</p></td>
												</tr>
												<tr ng-repeat="item in TaskList" style="cursor:pointer; cursor:hand; border-left: 8px solid #eee; border-radius: 3px;" ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC	'} || {'border-color': 'green'}">
													<td width="25%" ng-click="getEnquiryDetail(item.enquiryId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#logModal" style="line-height: 45px;">{{item.followupTime}}</td>
													<td width="70%" ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.remark}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.Description}}</span></td>
													<td width="5%">
														<i class="fa fa-check-circle-o" aria-hidden="true" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" ng-if="item.followupStatus == 'y'" title="Click mark complete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>														
														<i class="fa fa-check-circle" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" title="Marked as completed" aria-hidden="true" ng-if="item.followupStatus == 'c'" style="color: green; font-size: 20px; line-height: 45px;"></i>
														<br>
														<i class="fa fa-close" aria-hidden="true" ng-click="deleteFollowup(item.followupId)" ng-if="item.followupStatus == 'y'" title="Click to delete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>
													</td>													
												</tr>
											</tbody>
										</table>
									
								</div>								
							</div> -->
							<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-12 text-left">
											<div class="box-tools pull-right">
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
											<h6 class="box-title">Coming up Follow-ups</h6>
											<div class="input-group">
												<input class="KendoDate" id="fromdate" ng-model="fromdate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 40%;"/> to
												<input class="KendoDate" id="todate" ng-model="todate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 40%;"/>
												&nbsp;<a href="#" ng-click="getEnquiryFollowupsByDate()" class="btn btn-success">
													<i class="fa fa-ok" aria-hidden="true" ng-if="spin == 0"></i>
													<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>Go
												</a>
											</div>																						
										</div>										
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin">											
											<tbody>												
												<tr ng-repeat="item in TaskList" style="width: 190% !important;cursor:pointer; cursor:hand; border-left: 8px solid #eee; border-radius: 3px; " ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC'} || {'border-color': 'green'}">
													<td width="25%"  style="padding-right: 173px;"ng-click="getTaskDetailsById(item.taskId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#editModal">{{item.createdDate}}<br>{{item.followupTime}}</td>
													<td width="70%" ng-click="getTaskDetailsById(item.taskId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.Description}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</span></td>
													<td width="5%">
														<i class="fa fa-check-circle-o" aria-hidden="true" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" ng-if="item.followupStatus == 'y'" title="Click mark complete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>														
														<i class="fa fa-check-circle" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" title="Marked as completed" aria-hidden="true" ng-if="item.followupStatus == 'c'" style="color: green; font-size: 20px; line-height: 45px;"></i>
														<br>
														<i class="fa fa-close" aria-hidden="true" ng-click="deleteFollowup(item.followupId)" ng-if="item.followupStatus == 'y'" title="Click to delete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>
													</td>													
												</tr>
											</tbody>
										</table>
									</div>
								</div>								
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		<div class="modal fade" id="editModal" ng-init="getTaskDetailsById()">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="nav-tabs-custom" >
							<ul class="nav nav-tabs pull-right" id="navForMobile" style="margin-right: 20px;">
							  
								
								<li><a href="#tab3" data-toggle="tab">Status</a></li>
								<li><a href="#tab2" data-toggle="tab">Logs & Follow-ups</a></li>
								<li class="active"><a href="#tab1" data-toggle="tab">Edit Task</a></li>
								<!-- <li class="pull-left header"><i class="fa fa-user"></i> Enquiry - {{enquitynumber}} </li> -->
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<div class="box-body">
										<div class="row">
											<div class="col-md-2" ng-show="<%= session.getAttribute("usertypeidadmin") %> ==1 ">
												<label>	Task Date<font color="red" size="3">*</font></label>
												<div class="input-group">
													<input class="KendoDate" id="datepicker1" ng-model="Task.taskDate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
												</div>
											</div>
																		
											
																															
										</div>						
										<br>
										<div class="row">
											<%if((Integer)session.getAttribute("usertypeidadmin") != 3){%>
											<div class="col-md-6">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign to</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-10">
																	<select id="employeeid" name="employeeid" ng-model="employeeid" class="form-control" ng-change="setFlag()">
																		<option value="">Employee</option>
																		<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																	</select>												
																	<p ng-show="errorEmpName == 1" style="color: red;">{{msgEmpName}}</p>
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addAssignRowEdit(enquiryid)" ng-disabled="spinEmp == 1" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus" ng-show="spinEmp != 1"></span><i class="fa fa-refresh fa-spin" ng-if="spinEmp == 1"></i></a>
																	</div>
																</div>								
															</div>													
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> Employee Name </th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in getenquiryassignlist">
																			<td> {{item.firstName}} {{item.lastName}} </td>																															
																			<td>
																				<a href="#" ng-click="removeAssignRowEdit(item.enquiryAssignId, enquiryid)" class="delete" data-toggle="modal">
																					<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																				</a>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
											<% } %>	
											
											<div class="col-md-4">
												<div class="form-group">
													
													<textarea rows="8" id="Description" name="Description" ng-model="Task.Description"  placeholder="Description" class="form-control"></textarea>										
												</div>
											</div>
																		
											
										</div>
										
									</div>
									<div class="modal-footer">
										<div class="row">							
											<div class="col-md-6 text-left">
												<strong ng-show="success == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{message}}</strong>
												<strong ng-show="info == 1" style="color: red;"><span class="fa fa-info-circle"></span> {{message}}</strong>								
											</div>
											<div class="col-md-6">
												<!-- <button type="button" ng-click="goToGenerateInvoice(enquiryid)" class="btn btn-success">Generate Invoice</button> -->
												<button type="button" ng-click="goToGenerateQuotation(enquiryid)" class="btn btn-success">Generate Quote</button>
												<button type="submit" ng-click="editEnquiry(enquiryid)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Update </button>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tab2">
									<div class="modal-body">
										<div class="row">
											<div class="col-md-6">
												<div class="col-md-12">
													<div class="form-group">
														<label>Log Date Time<span class="red-star">*</span></label>
														<input class="KendoDateTime" id="datetimepicker" title="Date & Time Picker" style="width: 100%"/>
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<textarea rows="4" name="remark" id="remark" ng-model="TaskLogs.remark" class="form-control" placeholder="Log text..." ng-change="setFlag()"></textarea>
														<p ng-show="errorEnquiryLog == 1" style="color: red;">{{msgEnquiryLog}}</p> 
													</div>
												</div>
												
												
													<div class="col-md-12">
													<div class="form-group">
														<!-- <button ng-disabled="spinLog == 1" ng-click="addEnquiryLog(enquiryid)" class="btn btn-primary btn-lg" title="Add Enquiry Log" ng-if="spinLog == 1">submit<span ng-show="spinLog != 1" class="fa fa-plus"></span></button> -->
														<button type="button"  ng-disabled="spinLog == 1" style="    display: block;    margin: auto;"  ng-disabled="spinLog == 1" ng-click="addTaskLogs()"class="btn btn-primary btn-lg" title="Add Enquiry Log"  >Submit <span ng-show="spinLog != 1" ></span> </button>
													</div>
												</div>
												<div class="col-md-12" id="emailSmsCheckbox">
													<div class="col-md-3 text-right">
														<div class="form-group">
															<input type="checkbox" id="notifylogviaemail" name="notifylogviaemail" ng-model="notifylogviaemail">&nbsp;E-Mail																		
														</div>
													</div>
													<div class="col-md-8 text-right">
														<div class="form-group">									
															<input type="checkbox" id="notifylogviasms" name="notifylogviasms" ng-model="notifylogviasms">&nbsp;SMS									
														</div>
													</div>
												</div>
											
												
											</div>
											<div class="col-md-6">
												<div class="col-md-12">
													<div class="form-group">
														<label>Next Follow-Up Date Time<span class="red-star">*</span></label>
														<input class="KendoDateTime" id="datetimepicker1" title="Date & Time Picker" style="width: 100%"/>
														<p ng-show="errorFollowupDate == 1" style="color: red;">{{msgFollowupDate}}</p>
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<textarea rows="4" name="remark" id="remark" ng-model="TaskFollowUp.remark" class="form-control" placeholder="remark"></textarea>										 
													</div>
												</div>
											
												<div class="col-md-12">
																				
												</div>
												<div class="col-md-12">
													<div class="panel-group">
														<div class="panel panel-default">
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-10" id="followupemployeeidBtn">
																		<div class="form-group">
																			<select id="followupemployeeid" name="followupemployeeid" ng-model="followupemployeeid" class="form-control" ng-change="setFlag()">
																				<option value="">Employee</option>
																				<option ng-repeat="item in getEnquiryDetailById.assignedMemberList" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																			</select>
																		</div>
																		<p ng-show="errorFollowupEmpName == 1" style="color: red;">{{msgFollowupEmpName}}</p>
																		<p ng-show="errorFollowupMember == 1" style="color: red;">{{msgFollowupMember}}</p>
																	</div>	
																	<div class="col-md-2">
																		<div class="form-group" id="addFollowupMemberRowBtn">
																			<a href="#" ng-click="addFollowupMemberRow()" class="btn btn-primary btn-sm" title="Add LOG MEMBER LIST"><span class="fa fa-plus"></span></a>
																		</div>
																	</div>								
																</div>													
																<div class="table-responsive table-bordered">
																	<table class="table">
																		<thead>
																			<tr>
																				<th> Employee Name </th>														
																				<th> Action </th>
																			</tr>
																		</thead>
																		<tbody>
																			<tr ng-repeat="item in followupmemberlist">
																				<td> {{item.firstName}} {{item.lastName}} </td>																															
																				<td>
																					<a href="#" ng-click="removeFollowupMemberRow(item)" class="delete" data-toggle="modal">
																						<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																					</a>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<!-- <button ng-disabled="spinFollowup == 1" ng-click="addEnquiryFollowup(enquiryid)" class="btn btn-primary btn-lg" title="Add Enquiry Log"><i ng-if="spinFollowup == 1"></i><span ng-show="spinFollowup != 1" class="fa fa-plus"></span></button> -->
														<button type="button" ng-disabled="spinFollowup == 1"  style="    display: block;    margin: auto;"  ng-click="addTaskFolloup()" class="btn btn-primary btn-lg" title="Add Enquiry Log">Submit<span ng-show="spinFollowup != 1" ></span></button>
													</div>
												</div>
												<div class="col-md-12" id="emailSmsCheckbox">
													<div class="col-md-3 text-right" >
														<div class="form-group">
															<input type="checkbox" id="notifyfollowupviaemail" name="notifyfollowupviaemail" ng-model="notifyfollowupviaemail">&nbsp;E-Mail																		
														</div>
													</div>
													<div class="col-md-8 text-right"  id="emailSmsCheckbox">
														<div class="form-group">									
															<input type="checkbox" id="notifyfollowupviasms" name="notifyfollowupviasms" ng-model="notifyfollowupviasms">&nbsp;SMS									
														</div>
													</div>
													</div>
											</div>							
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="box box-warning direct-chat direct-chat-warning" style="border-color: #f4f4f4;">
													<div class="box-header with-border">
														<h3 class="box-title">Inquiry Logs</h3>
														<div class="box-tools pull-right">										
															<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>							
														</div>
													</div>
													<div class="box-body">
														<!-- <div ng-show="logLoader" class="direct-chat-msg">																				
															<div class="direct-chat-text text-center" style="margin: 5px 0 0 0; font-size: 16px; background-color: #e3e7f0; position: unset;">
																Loading data... Please Wait...<i class="fa fa-spinner fa-pulse"></i>
															</div>											
								                        </div> -->
														<div class="direct-chat-msg" ng-repeat="item in getEnquiryLog">																				
															<div class="direct-chat-text" style="margin: 5px 0 0 0; font-size: 16px; background-color: #e3e7f0; position: unset;">
																{{item.statusName}}
															</div>
															<div class="direct-chat-info clearfix">
																<span class="direct-chat-timestamp pull-left">{{item.firstName}} {{item.lastName}}</span>
																<span class="direct-chat-timestamp pull-right"><span>{{item.logDateTime}}</span> <span ng-if="item.logDateTime == null">{{item.createdDate}}</span></span>
															</div>
								                        </div>
								                    </div>
								                 </div>	
											</div>
											<div class="col-md-6">
												<div class="box box-warning direct-chat direct-chat-warning" style="border-color: #f4f4f4;">
													<div class="box-header with-border">
														<h3 class="box-title">Inquiry Followups</h3>
														<div class="box-tools pull-right">										
															<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>							
														</div>
													</div>
													<div class="box-body">	
														<!-- <div ng-show="followupLoader" class="direct-chat-msg right">																				
															<div class="direct-chat-text text-center" style="margin: 5px 0 0 0; font-size: 16px; position: unset;">
																Loading data... Please Wait...<i class="fa fa-spinner fa-pulse"></i>
															</div>											
								                        </div> -->
														<div class="direct-chat-msg right" ng-repeat="item in getEnquiryFollowup">									
															<div class="direct-chat-text" style="margin: 5px 0 0 0; font-size: 16px; position: unset;">
																{{item.remark}}
															</div>
															<div class="direct-chat-info clearfix">
																<span class="direct-chat-timestamp pull-right">{{item.firstName}} {{item.lastName}}</span>
																<span class="direct-chat-timestamp pull-left">{{item.followupDateTime}}</span>
															</div>
								                        </div>
								                    </div>
								                 </div>								
											</div>
										</div>						
									</div>											
								</div>					
								<div class="tab-pane" id="tab3">
									<div class="modal-body">
										<div class="row">
											<!-- <div class="col-md-6">								
												<div class="input-group">
													<input class="KendoDateTime" id="statusdate" style="width: 100%;"/>
												</div>
											</div> -->
											<div class="col-md-3">								
												<div class="form-group">
													<select id="enquirystatus" name="enquirystatus" ng-model="enquirystatus" class="form-control" ng-change="setFlag()">
														<option value="">Enquiry Status*</option>
														<option value="o">Working</option>
														<option value="l">Lost</option>
														<option value="w">Won</option>
													</select>
													<p ng-show="errorEnquiryStatus == 1" style="color: red;">{{msgEnquiryStatus}}</p>																				
												</div>				
											</div>
											<div class="col-md-7">
												<div class="input-group form-group">
													<select id="statusreason" name="statusreason" ng-model="statusreason" class="form-control">
														<option value="">Select Reason</option>
														<option ng-repeat="item in getStatusReason" value="{{item.reasonId}}">{{item.statusReason}}</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#statusReasonModal" title="Add New Product"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-2" ng-show="enquirystatus == 'l' || enquirystatus == 'w'">
												<label class="checkbox-inline">
											      <input type="checkbox" ng-model="statusclose" id="statusclose" value="cw">Close Enquiry
											    </label>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<div class="row">															
											<div class="col-md-6 text-left">
												<strong ng-show="statusSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{statusSuccessMsg}}</strong>
												<strong ng-show="statusSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{statusErrorMsg}}</strong>								
											</div>
											<div class="col-md-6 text-right">
												<button type="submit" ng-click="updateEnquiryStatus(enquiryid)" ng-disabled="spinStatus == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinStatus == 1"></i> Submit</button>
											</div>
										</div>
									</div>
								</div>
								<!-- start quatition -->
								
								
								
								
								<!--  -->
							
							</div>
						</div>
						<div class="text-right">
							<button class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
						
					</div>
					
				</div>
			</div>
		</div>
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-12">
								<h4 class="modal-title" style="color: red;"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Enquiry </h4>								
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-default" value="Delete" ng-click="deleteEnquiry()">
					</div>
				</div>
			</div>
		</div>
		
		<div id="statusReasonModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-12">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title"> <i class="fa fa-question-circle" aria-hidden="true"></i> Add Status Reason</h4>								
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">								
								<div class="form-group">
									<label> Status Reason<span class="red-star">*</span> </label>
									<input type="text" id="reason" name="reason" ng-model="reason"  class="form-control" placeholder="Reason" ng-change="setFlag()">
									<p ng-show="errorStatusReason == 1" style="color: red;">{{msgStatusReason}}</p>
								</div>												
							</div>							
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-6 text-left">
								<strong ng-show="reasonSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{reasonSuccessMsg}}</strong>
								<strong ng-show="reasonSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{reasonErrorMsg}}</strong>
							</div>					
							<div class="col-md-6 text-right">
								<button type="submit" ng-click="addStatusReason()" ng-disabled="spinReason == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinReason == 1"></i> Submit</button>
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Close">																										
							</div>								
						</div>					
					</div>					
				</div>
			</div>
		</div>
		<div class="modal fade" id="userTypeModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add User Type</h4>
					</div>
					<form ng-submit="addUserType()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>User Type Name<font color="red" size="3">*</font></label>
										<input type="text" id="usertypename" name="usertypename" ng-model="usertypename" placeholder="User Type Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="userTypeSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{userTypeSuccessMsg}}</strong>
									<strong ng-show="userTypeSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{userTypeErrorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userTypeSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userTypeSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		
		
		
		
		
		<div class="modal fade" id="userModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add User</h4>
					</div>
					<form id="AddUserForm" ng-submit="addUser()">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-4">
									<div class="form-group">
										<label>Company Name<font color="red" size="3">*</font></label>
										<input type="text" id="companyname" name="companyname" ng-model="companyname" placeholder="Company Name" class="form-control" ng-change="setFlag()" autofocus>
										<p ng-show="errorCompanyName == 1" style="color: red;">{{msgCompanyName}}</p>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>First Name</label>
										<input type="text" id="firstName" name="firstName" ng-model="userDetailById.firstName" placeholder="First Name" class="form-control">										
									</div>									
								</div>								
								<div class="col-md-4">
									<div class="form-group">
										<label>Last Name</label>
										<input type="text" id="lastnameadd" name="lastnameadd" ng-model="lastnameadd" placeholder="Last Name" class="form-control">
									</div>									
								</div>
							</div>
							<div class="row">								
								<div class="col-md-3">
									<div class="form-group">
										<label>User Type<font color="red" size="3">*</font></label>
										<select id="usertypename" name="usertypename" ng-model="usertypename" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" class="form-control" ng-change="setFlag()">
											<option value="">User Type</option>
										</select>											
										<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>
									</div>
								</div>																	
								<div class="col-md-9">
									<div class="form-group">
										<label> Profile Picture </label>
										<input type="file" id="profilepictureadd" name="profilepictureadd" class="form-control">
									</div>
								</div>
							</div>							
							<div class="row">									
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-1 </label>
										<input type="text" id="address1add" name="address1add" ng-model="address1add" class="form-control" placeholder="Address Line-1" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-2 </label>
										<input type="text" id="address2add" name="address2add" ng-model="address2add" class="form-control" placeholder="Address Line-2" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-3 </label>
										<input type="text" id="address3add" name="address3add" ng-model="address3add" class="form-control" placeholder="Address Line-3" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label>Select Country</label>
									<div class="form-group">
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" class="form-control">
											<option value="">Country</option>
										</select>										
									</div>
								</div>
								<div class="col-md-3">
									<label>Select State</label>
									<div class="form-group">
										<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-init="statenameadd = 12" class="form-control">
											<option value="">State</option>
										</select>										
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> City </label>
										<input type="text" id="citynameadd" name="citynameadd" ng-model="citynameadd" class="form-control" placeholder="City Name">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Pincode </label>
										<input type="text" id="pincodeadd" name="pincodeadd" ng-model="pincodeadd" class="form-control" placeholder="Pincode">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label> Mobile Number </label>
										<input type="text" id="mobilenumberadd" name="mobilenumberadd" ng-model="mobilenumberadd" class="form-control" placeholder="Mobile Number">
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Landline Number </label>
										<input type="text" id="landlinenumberadd" name="landlinenumberadd" ng-model="landlinenumberadd" class="form-control" placeholder="Landline Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Email <font color="red" size="3">*</font></label>
										<input type="text" id="emailadd" name="emailadd" ng-model="emailadd" class="form-control" placeholder="Email" ng-change="setFlag()">
										<p ng-show="errorEmail == 1" style="color: red;">{{msgEmail}}</p>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Password </label>
										<input type="text" id="passwordadd" name="passwordadd" ng-model="passwordadd" class="form-control" placeholder="Password">
									</div>
								</div>
							</div>							
						</div>
						<div class="modal-footer">
							<div class="row">																
								<div class="col-md-6 text-left">
									<p ng-show="userSuccess == 1" style="color: green; font-size: 18px;"><b>{{userMsg}}</b></p>
									<p ng-show="userError == 1" style="color: red; font-size: 18px;"><b>{{userMsg}}</b></p>																	
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userspin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userspin == 1"></i> Submit</button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>										
								</div>								
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		
		
		
		<div id="toDoModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-12">
								<h4 class="modal-title"> <i class="fa fa-info-circle" aria-hidden="true"></i> Add New Follow-up </h4>								
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Follow-Up Date Time<span class="red-star">*</span></label>
									<input class="KendoDateTime" id="datetimepicker2" title="Date & Time Picker" style="width: 100%"/>
									<p ng-show="errorFollowupDate == 1" style="color: red;">{{msgFollowupDate}}</p>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<textarea rows="4" name="followupremark" id="followupremark" ng-model="followupremark" class="form-control" placeholder="Remark*..."></textarea>
									<p ng-show="errorFollowupRemark == 1" style="color: red;">{{msgFollowupRemark}}</p>										 
								</div>
							</div>
						</div>
						
						    <div class="row">
							<div class="col-md-12"  id="emailSmsCheckbox">
								<div class="col-md-3">
									<div class="form-group">
										<input type="checkbox" id="notifyfollowupviaemail" name="notifyfollowupviaemail" ng-model="notifyfollowupviaemail" class="ng-pristine ng-valid">&nbsp;E-Mail																		
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">									
										<input type="checkbox" id="notifyfollowupviasms" name="notifyfollowupviasms" ng-model="notifyfollowupviasms" class="ng-pristine ng-valid">&nbsp;SMS									
									</div>
								</div>								
							</div>
							
					    	</div>
					    <div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<select id="employeeidadd" name="employeeidadd" ng-model="employeeidadd" class="form-control" ng-change="setFlag()">
										<option value="">Employee</option>
										<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
									</select>									
								</div>
								<p ng-show="errorEmpName == 1" style="color: red;">{{msgEmpName}}</p>
							</div>	
							<div class="col-md-2">
								<div class="form-group">
									<a href="#" ng-click="addEmpRow()" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus"></span></a>
								</div>
							</div>								
						</div>
									
					    <div class="table-responsive table-bordered" ng-if="emplist.length > 0">
							<table class="table">
								<thead>
									<tr>
										<th> Employee Name </th>														
										<th> Action </th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in emplist">
										<td> {{item.firstName}} {{item.lastName}} </td>																															
										<td>
											<a href="#" ng-click="removeEmpRow(item)" class="delete" data-toggle="modal">
												<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
											</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						
					</div>
					<div class="modal-footer">						
						<button type="submit" ng-disabled="spinFollowup == 1"  class="btn btn-success" ng-click="addNewFollowup()"><i class="fa fa-refresh fa-spin" ng-if="spinFollowup == 1"></i>Submit</button>
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					</div>
				</div>
			</div>
			
		</div>		
			
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		
		<script>

            

		
			document.getElementById("manage").classList.add("active");
			document.getElementById("enquiry").classList.add("active");
			
			$("#datepicker,#datepicker1,#datepicker2,#datepicker3,#datepicker3").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true,
				value: new Date()
			});

			 $("#podate, #podate1").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true,	
				value: "PO Date"
			});

			
			$("#fromdate,#todate").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true
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



			initEditor();		
			
			//For Add
			function initEditor() {
				CKEDITOR.replace( 'letterdescription', {
							pluginsLoaded: function( evt ) {
			 					var doc = CKEDITOR.document, ed = evt.editor;
			 					if ( !ed.getCommand( 'bold' ) )
			  						doc.getById( 'exec-bold' ).hide();
			 					if ( !ed.getCommand( 'link' ) )
			  						doc.getById( 'exec-link' ).hide();
			 				}
						 });
			}			
			if ( typeof CKEDITOR == 'undefined' ) {
				document.write(
					'<strong><span style="color: #ff0000">Error</span>: CKEditor not found</strong>.' +
					'This sample assumes that CKEditor (not included with CKFinder) is installed in' +
					'the "/ckeditor/" path. If you have it installed in a different place, just edit' +
					'this file, changing the wrong paths in the &lt;head&gt; (line 5) and the "BasePath"' +
					'value (line 32).' ) ;
			} else {
				var editor = CKEDITOR.replace( 'editor1' );
				CKFinder.setupCKEditor(editor,'../') ;
			}	



				
		</script>
		
<script>

		$("#datepicker").kendoDatePicker({
			format: "dd/MM/yyyy",
			dateInput: true,
			value: new Date()
		});

</script>
    
    
    
	</body>
</html>