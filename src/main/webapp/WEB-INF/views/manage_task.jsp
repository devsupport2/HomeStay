<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">		
		<title> Task </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />		
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/task.js"></script>
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />		
		<style type="text/css">
			#productModal, #editModal  { overflow-y:scroll };
		</style>				
	</head>	
	<body ng-app="MyApp" ng-controller="taskCtrl" ng-cloak class="skin-blue sidebar-collapse sidebar-mini" ng-init="sendEnquiryNotification()">
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
						<li class="active">Task</li>
					</ol>
				</section>
				<section class="content">
					<%-- <div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title"> Add Enquiry</h3>
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
									<div class="col-md-2" ng-show="<%= session.getAttribute("usertypeidadmin") %> ==1 ">
										<label>Enquiry Date<font color="red" size="3">*</font></label>
										<div class="input-group">
											<input class="KendoDate" id="datepicker" title="datepicker" />
										</div>
									</div>
									<div class="col-md-2">
										<label>Enquiry Via<font color="red" size="3">*</font></label>										
										<div class="form-group">
											<select id="enquiryviaadd" name="enquiryviaadd" ng-model="enquiryviaadd" class="form-control">
												<option value="">Enquiry Via</option>
												<option value="Call">Call</option>
												<option value="Email">Email</option>
												<option value="Personal Meeting">Personal Meeting</option>
												<option value="Reference">Reference</option>
												<option value="Walk-In">Walk-In</option>
												<option value="Web">Web</option>
												<option value="Other">Other</option>												
											</select>																	
										</div>
									</div>
									<%if((Integer)session.getAttribute("usertypeidadmin") != 3){%>
									<div class="col-md-2">
										<label>Reference Type</label>
										<div class="input-group">
											<select id="usertypeidadd" name="usertypeidadd" ng-model="usertypeidadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="getUserByUserType(usertypeidadd)" class="form-control">
												<option value="">Reference Type</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New Client"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-3">
										<label>Reference By</label>
										<div class="input-group">
											<select id="referenceidadd" name="referenceidadd" ng-model="referenceidadd" class="select2" style="width: 100%;">
												<option value="">Reference By</option>
												<option ng-repeat="item in getUserNameByUserType" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Client"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<%}%>
									<div class="col-md-3">
										<label>Client<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="clientidadd" name="clientidadd" ng-model="clientidadd" class="select2" style="width: 100%;">
												<option value="">Client</option>
												<option ng-repeat="item in clientAndProspectName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#clientModal" title="Add New Client"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
								</div>														
								<div class="row">
									<div class="col-md-4">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Add Products</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-10">
															<div class="input-group">
																<input type="text" id="searchproduct" name="searchproduct" ng-model="searchproduct" class="form-control" placeholder="Search" ng-change="setShowTable()"/>
																<span class="input-group-btn">
																	<button type="button" name="searchproduct" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
																</span>
																<span class="input-group-btn" ng-show="<%= session.getAttribute("usertypeidadmin") %> != 3">
																	<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#productModal" title="Add New Product"><i class="fa fa-plus"></i></button>
																</span>
															</div>
														</div>	
														<div class="col-md-2">
															<div class="form-group">
																<a href="#" ng-click="addProductRow()" class="btn btn-primary btn-sm" title="Add Product"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>
													<div class="row" ng-show="showTable == 'y'">
														<div class="col-md-12">
															<div class="table-responsive">
																<table class="table" ng-show="searchproduct.length > 0">																	
																	<tbody>
																		<tr ng-repeat="item in getProducts | filter:searchproduct" ng-show="searchproduct != null" style="cursor: pointer;" class="success">
																			<td ng-click="setProduct(item.productId)" style="color: #000;">{{item.productName}}-{{item.partNumber}}</td>
																		</tr>																		
																	</tbody>
																</table>
															</div>
														</div>
													</div>								
													<div class="row">
														<div class="col-md-12 text-center">
															<div class="alert alert-info" ng-show="productinfo == 1">
																<strong><span class="fa fa-info-circle"></span> {{productmessage}}</strong>
															</div>
														</div>
													</div>
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Product </th>
																	<th title="Ready to Invoice"> RtI </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in productlist">
																	<td> {{item.product}} </td>
																	<td> <input type="checkbox" ng-model="item.selected" title="check if ready to invoice"> </td>														
																	<td>
																		<a href="#" ng-click="removeProductRow(item)" class="delete" data-toggle="modal">
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
									<div class="col-md-4">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Reference Url</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<input type="text" id="urladd" name="urladd" ng-model="urladd" placeholder="Reference URL" class="form-control">
															</div>
														</div>	
														<div class="col-md-2">
															<div class="form-group">
																<a href="#" ng-click="addUrlRow()" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>								
													<div class="row">
														<div class="col-md-12 text-center">
															<div class="alert alert-info" ng-show="urlinfo == 1">
																<strong><span class="fa fa-info-circle"></span> {{urlmessage}}</strong>
															</div>
														</div>
													</div>
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Reference Url </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in urllist">
																	<td> {{item.url}} </td>														
																	<td>
																		<a href="#" ng-click="removeUrlRow(item)" class="delete" data-toggle="modal">
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
																<a href="#" ng-click="addAssignRow()" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus"></span></a>
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
																<tr ng-repeat="item in assignlist">
																	<td> {{item.firstName}} {{item.lastName}} </td>																															
																	<td>
																		<a href="#" ng-click="removeAssignRow(item)" class="delete" data-toggle="modal">
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
								</div>
								<div class="row">																		
									<div class="col-md-12">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Attach Files</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-5">
															<div class="form-group">
																<input type="text" id="filetitleadd" name="filetitleadd" ng-model="filetitleadd" placeholder="File Title" class="form-control">
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<input type="file" id="fileadd" name="fileadd" ng-model="fileadd" class="form-control">
															</div>
														</div>	
														<div class="col-md-1">
															<div class="form-group">
																<a href="#" ng-click="addFileRow()" class="btn btn-primary btn-sm" title="Add File"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>								
													<div class="row">
														<div class="col-md-12 text-center">
															<div class="alert alert-info" ng-show="fileinfo == 1">
																<strong><span class="fa fa-info-circle"></span> {{filemessage}}</strong>
															</div>
														</div>
													</div>
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> File Title </th>
																	<th> File </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in filelist" ng-show="item.fileTitle != null">
																	<td> {{item.fileTitle}} </td>
																	<td> <i class="fa fa-file-o" aria-hidden="true"></i> </td>														
																	<td>
																		<a href="#" ng-click="removeFileRow(item)" class="delete" data-toggle="modal">
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
								</div>
								<div class="row">								
									<div class="col-md-12">
										<div class="form-group">
											<label>Remarks</label>
											<textarea rows="4" id="remarksadd" name="remarksadd" ng-model="remarksadd" placeholder="Remarks" class="form-control"></textarea>										
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
										<button type="submit" ng-click="addEnquiry()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div> --%>
					
					
					
					
					<div class="row">
						
						
						<div class="col-md-12">
							<div class="hint-text">&nbsp;</div>
						
						<!-- 	<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-8 text-left">
											<h6 class="box-title">Today's Follow-up ({{currentdate | date:'dd-MM-yyyy'}})</h6>
										</div>
										<div class="col-md-4 text-right">
											<div class="box-tools pull-right">
												<button class="btn btn-success" data-toggle="modal" data-target="#toDoModal">Add New</button>
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
										</div>
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin">											
											<tbody>												
												<tr ng-show="errorFollowupStatus == 1">
													<td colspan="3" class="text-center"><p style="color: red;">{{msgFollowupStatus}}</p></td>
												</tr>
												<tr ng-repeat="item in todayFollowupEnquiries" style="cursor:pointer; cursor:hand; border-left: 8px solid #eee; border-radius: 3px;" ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC	'} || {'border-color': 'green'}">
													<td width="25%" ng-click="getEnquiryDetail(item.enquiryId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#logModal" style="line-height: 45px;">{{item.followupTime}}</td>
													<td width="70%" ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.remark}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</span></td>
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
							 -->
							
							
							
							<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
									<div class="col-md-8 text-left">
<!-- 									      <h6 class="box-title">Today's Follow-up ({{currentdate | date:'dd-MM-yyyy'}})</h6> -->
									       <h6 class="box-title">TASKS  </h6>
									       <h6></h6>
										</div>
										<div class="col-md-12 text-center">
										</div>
										 <div class="row"></div>
										 
										<div class="col-md-12 text-left">
											<div class="box-tools pull-right">
											    <button class="btn btn-success" data-toggle="modal" data-target="#toDoModal">Add New</button>
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
								
								
									
										
											
											<!-- <h6 class="box-title">Coming up Follow-ups</h6> -->
											
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
												<tr ng-repeat="item in followupEnquiriesByDate" style="cursor:pointer; cursor:hand; border-left: 8px solid #eee; border-radius: 3px;" ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC'} || {'border-color': 'green'}">
													<td width="25%" ng-click="getEnquiryDetail(item.enquiryId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#editModal">{{item.followupDate}}<br>{{item.followupTime}}</td>
													<td width="70%" ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.remark}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</span></td>
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
	
	
		
		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="nav-tabs-custom" >
							<ul class="nav nav-tabs pull-right" id="navForMobile" style="margin-right: 20px;">
							 
								
								<li><a href="#tab3" data-toggle="tab"> Status</a></li>
								<li><a href="#tab2" data-toggle="tab">Logs & Follow-ups</a></li>
								<li class="active"><a href="#tab1" data-toggle="tab">Edit Task</a></li>
								<!-- <li class="pull-left header"><i class="fa fa-user"></i> Enquiry - {{enquitynumber}} </li> -->
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<div class="box-body">
										<div class="row">
											<div class="col-md-2" ng-show="<%= session.getAttribute("usertypeidadmin") %> ==1 ">
												<label>Task Date<font color="red" size="3">*</font></label>
												<div class="input-group">
													<input class="KendoDate" id="datepicker1" ng-model="enquirydate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
												</div>
											</div>
											<div class="col-md-2">
												<label>Task Via<font color="red" size="3">*</font></label>
												<div class="form-group">
													<select id="enquiryvia" name="enquiryvia" ng-model="enquiryvia" class="form-control">
														<option value="">Task Via</option>
														<option value="Call">Call</option>
														<option value="Email">Email</option>
														<option value="Personal Meeting">Personal Meeting</option>
														<option value="Reference">Reference</option>
														<option value="Walk-In">Walk-In</option>
														<option value="Web">Web</option>
														<option value="Other">Other</option>										
													</select>																	
												</div>
											</div>
											<%if((Integer)session.getAttribute("usertypeidadmin") != 3){%>
											<div class="col-md-3">
												<div class="form-group">
													<label>Reference Type</label>
													<div class="input-group">
														<select id="usertypeid" name="usertypeid" ng-model="usertypeid" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="getUserByUserType(usertypeidadd)" class="form-control">
															<option value="">Reference Type</option>
														</select>
														<span class="input-group-btn">
															<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New Client"><i class="fa fa-plus"></i></button>
														</span>
													</div>
												</div>
											</div>							
											<div class="col-md-4">
												<div class="form-group">
													<label>Reference By</label>
													<div class="input-group">
														<select id="referenceid" name="referenceid" ng-model="referenceid" ng-options="item.userId as item.userCompanyName+' - '+item.firstName+' '+item.middleName+' '+item.lastName for item in getUserNameByUserType" class="form-control">
															<option value="">Reference By</option>
														</select>
														<span class="input-group-btn">
															<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Client"><i class="fa fa-plus"></i></button>
														</span>
													</div>
												</div>
											</div>
											<%}%>
											<div class="col-md-5">
												<div class="form-group">
													<label>Client<font color="red" size="3">*</font></label>
													<div class="input-group">
														<select id="clientid" name="clientid" ng-model="clientid" ng-options="item.userId as item.userCompanyName+' - '+item.firstName+' '+item.lastName for item in clientAndProspectName" class="form-control">
															<option value="">Client</option>
														</select>
														<span class="input-group-btn">
															<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#clientModal" title="Add New Client"><i class="fa fa-plus"></i></button>
														</span>
													</div>
												</div>
											</div>																						
										</div>						
										<div class="row">
											<div class="col-md-6">
												<div class="panel-group">
													<div class="panel panel-default" style="height:228px">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Add Products</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-10">
																	<div class="input-group">
																		<input type="text" id="searchproductedit" name="searchproductedit" ng-model="searchproductedit" class="form-control" placeholder="Search" ng-change="setShowTable()"/>
																		<span class="input-group-btn">
																			<button type="button" name="searchproduct" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
																		</span>														
																	</div>
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addProductRowEdit()" class="btn btn-primary btn-sm" title="Add Product"><span class="fa fa-plus"></span></a>
																	</div>
																</div>								
															</div>
															<div class="row" ng-show="showTable == 'y'">
																<div class="col-md-12">
																	<div class="table-responsive">
																		<table class="table" ng-show="searchproductedit.length > 0">																	
																			<tbody>
																				<tr ng-repeat="item in getProducts | filter:searchproductedit" ng-show="searchproductedit != null" style="cursor: pointer;" class="success">
																					<td ng-click="setProductEdit(item.productId)" style="color: #000;">{{item.productName}}-{{item.partNumber}}</td>
																				</tr>																		
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>								
															<div class="row">
																<div class="col-md-12 text-center">
																	<div class="alert alert-info" ng-show="productinfo == 1">
																		<strong><span class="fa fa-info-circle"></span> {{productmessage}}</strong>
																	</div>
																</div>
															</div>
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> Product </th>
																			<th title="Ready to Invoice">RtI</th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in getproductlist">
																			<td> {{item.productDetail}} </td>	
																			<td> 
																				<input type="checkbox" ng-model="item.selected" ng-if="item.readyToInvoice == 'y' || (item.selected == true && item.selected != undefined)" ng-init="item.selected = true" title="check if ready to invoice" > 
																				<input type="checkbox" ng-model="item.selected" ng-if="item.readyToInvoice == 'n' || (item.selected == undefined && item.selected != true)" ng-init="item.selected = false" title="check if ready to invoice" >
																			</td>													
																			<td>
																				<a href="#" ng-click="removeProductRowEdit(item)" class="delete" data-toggle="modal">
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
											<!-- <div class="col-md-6">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Reference Url</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-10">
																	<div class="form-group">
																		<input type="text" id="url" name="url" ng-model="url" placeholder="Reference URL" class="form-control">
																	</div>
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addUrlRowEdit()" class="btn btn-primary btn-sm" title="Add URL Row"><span class="fa fa-plus"></span></a>
																	</div>
																</div>								
															</div>								
															<div class="row">
																<div class="col-md-12 text-center">
																	<div class="alert alert-info" ng-show="urlinfo == 1">
																		<strong><span class="fa fa-info-circle"></span> {{urlmessage}}</strong>
																	</div>
																</div>
															</div>
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> Reference Url </th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in geturllist">
																			<td> {{item.url}} </td>														
																			<td>
																				<a href="#" ng-click="removeUrlRowEdit(item)" class="delete" data-toggle="modal">
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
											</div> -->	
											
											<div class="col-md-6">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Attach Files</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<div class="form-group">
																		<input type="text" id="filetitle" name="filetitle" ng-model="filetitle" placeholder="File Title" class="form-control">
																	</div>
																</div>
																<div class="col-md-10">
																	<div class="form-group">
																		<input type="file" id="file" name="file" ng-model="file" class="form-control">
																	</div>
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addFileRowEdit()" class="btn btn-primary btn-sm" title="Add File Row"><span class="fa fa-plus"></span></a>
																	</div>
																</div>								
															</div>								
															<div class="row">
																<div class="col-md-12 text-center">
																	<div class="alert alert-info" ng-show="fileinfo == 1">
																		<strong><span class="fa fa-info-circle"></span> {{filemessage}}</strong>
																	</div>
																</div>
															</div>
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> File Title </th>
																			<th> File </th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in getfilelist" ng-show="item.fileTitle != null">
																			<td> {{item.fileTitle}} </td>
																			<td><a href="{{item.filePath}}" target="_blank" title="Click here to open this file!"><i class="fa fa-file-o" aria-hidden="true"></i></a></td>														
																			<td>
																				<a href="#" ng-click="removeFileRowEdit1(item)" class="delete" data-toggle="modal">
																					<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																				</a>
																			</td>
																		</tr>
																		<tr ng-repeat="item in newfilelist" ng-show="item.fileTitle != null">
																			<td> {{item.fileTitle}} </td>
																			<td><i class="fa fa-file-o" aria-hidden="true"></i></td>														
																			<td>
																				<a href="#" ng-click="removeFileRowEdit(item)" class="delete" data-toggle="modal">
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
										</div>
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
											<!-- <div class="col-md-6">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Attach Files</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-12">
																	<div class="form-group">
																		<input type="text" id="filetitle" name="filetitle" ng-model="filetitle" placeholder="File Title" class="form-control">
																	</div>
																</div>
																<div class="col-md-10">
																	<div class="form-group">
																		<input type="file" id="file" name="file" ng-model="file" class="form-control">
																	</div>
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addFileRowEdit()" class="btn btn-primary btn-sm" title="Add File Row"><span class="fa fa-plus"></span></a>
																	</div>
																</div>								
															</div>								
															<div class="row">
																<div class="col-md-12 text-center">
																	<div class="alert alert-info" ng-show="fileinfo == 1">
																		<strong><span class="fa fa-info-circle"></span> {{filemessage}}</strong>
																	</div>
																</div>
															</div>
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> File Title </th>
																			<th> File </th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in getfilelist" ng-show="item.fileTitle != null">
																			<td> {{item.fileTitle}} </td>
																			<td><a href="{{item.filePath}}" target="_blank" title="Click here to open this file!"><i class="fa fa-file-o" aria-hidden="true"></i></a></td>														
																			<td>
																				<a href="#" ng-click="removeFileRowEdit1(item)" class="delete" data-toggle="modal">
																					<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																				</a>
																			</td>
																		</tr>
																		<tr ng-repeat="item in newfilelist" ng-show="item.fileTitle != null">
																			<td> {{item.fileTitle}} </td>
																			<td><i class="fa fa-file-o" aria-hidden="true"></i></td>														
																			<td>
																				<a href="#" ng-click="removeFileRowEdit(item)" class="delete" data-toggle="modal">
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
											</div> -->
										</div>
										<div class="row">								
											<div class="col-md-12">
												<div class="form-group">
													<label>Remarks</label>
													<textarea rows="4" id="remarks" name="remarks" ng-model="remarks" placeholder="Remarks" class="form-control"></textarea>										
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
														<textarea rows="4" name="enquirylog" id="enquirylog" ng-model="enquirylog" class="form-control" placeholder="Log text..." ng-change="setFlag()"></textarea>
														<p ng-show="errorEnquiryLog == 1" style="color: red;">{{msgEnquiryLog}}</p> 
													</div>
												</div>
												
												
													<div class="col-md-12">
													<div class="form-group">
														<!-- <button ng-disabled="spinLog == 1" ng-click="addEnquiryLog(enquiryid)" class="btn btn-primary btn-lg" title="Add Enquiry Log" ng-if="spinLog == 1">submit<span ng-show="spinLog != 1" class="fa fa-plus"></span></button> -->
														<button type="button"  ng-disabled="spinLog == 1" style="    display: block;    margin: auto;"  ng-disabled="spinLog == 1" ng-click="addEnquiryLog(enquiryid)"class="btn btn-primary btn-lg" title="Add Enquiry Log"  >Submit <span ng-show="spinLog != 1" ></span> </button>
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
											
												<!-- <div class="col-md-12">
													<div class="panel-group">
														<div class="panel panel-default">
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-10">
																		<div class="form-group">
																			<select id="logemployeeidadd" name="logemployeeidadd" ng-model="logemployeeidadd" class="form-control" ng-change="setFlag()">
																				<option value="">Employee</option>
																				<option ng-repeat="item in getEnquiryDetailById.assignedMemberList" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																			</select>
																		</div>
																		<p ng-show="errorLogEmpName == 1" style="color: red;">{{msgLogEmpName}}</p>
																		<p ng-show="errorLogMember == 1" style="color: red;">{{msgLogMember}}</p>
																	</div>	
																	<div class="col-md-2">
																		<div class="form-group">
																			<a href="#" ng-click="addLogMemberRow()" class="btn btn-primary btn-sm" title="Add LOG MEMBER LIST"><span class="fa fa-plus"></span></a>
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
																			<tr ng-repeat="item in logmemberlist">
																				<td> {{item.firstName}} {{item.lastName}} </td>																															
																				<td>
																					<a href="#" ng-click="removeLogMemberRow(item)" class="delete" data-toggle="modal">
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
												</div> -->
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
														<textarea rows="4" name="remark" id="remark" ng-model="taskFollowUp.remark" class="form-control" placeholder="Remark..."></textarea>										 
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
																			<select id="employeeId" name="employeeId" ng-model="taskFollowUp.employeeId" class="form-control" ng-change="setFlag()">
																				<option value="">Employee</option>
																				<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																			</select>
																		</div>
																		<p ng-show="errorFollowupEmpName == 1" style="color: red;">{{msgFollowupEmpName}}</p>
																		<p ng-show="errorFollowupMember == 1" style="color: red;">{{msgFollowupMember}}</p>
																	</div>	
																	<div class="col-md-2">
																		<div class="form-group" id="addFollowupMemberRowBtn">
																			<a href="#" ng-click="addTaskFollowupMemberRow()" class="btn btn-primary btn-sm" title="Add LOG MEMBER LIST"><span class="fa fa-plus"></span></a>
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
														<button type="button" ng-disabled="spinFollowup == 1"  style="    display: block;    margin: auto;"  ng-click="addEnquiryFollowup(enquiryid)" class="btn btn-primary btn-lg" title="Add Enquiry Log">Submit<span ng-show="spinFollowup != 1" ></span></button>
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
														<option value="l">Closed</option>
														<option value="w">Open</option>
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
		
		<div class="modal fade" id="quotationModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Quotation Detail</h4>
					</div>
					<form name="quatationForm">
					<div class="modal-body">
						<div class="row">								
							<div class="col-md-12">
								<div class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">													
											 <h4 class="panel-title"><i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp; Details</h4>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-4">
													<div class="form-group">
														<p>Enquiry No. :<b style="margin-left: 15px;">{{getEnquiryDetails.enquiryNo}}</b></p>															
													</div>
													<div class="form-group">
														<p>Quote No. :<b style="margin-left: 24px;">{{getInvoiceDetail.invoiceNo}}</b></p>															
													</div>																			
												</div>
												<div class="col-md-4">	
													<div class="form-group">
														<p>Reference : <b style="margin-left: 23px;">{{getEnquiryDetails.enquiryVia}}</b></p>
													</div>
													<div class="form-group">
														<p>Quote Date :<b style="margin-left: 18px;">{{getInvoiceDetail.invoiceDate}}</b></p>															
													</div>																						
												</div>
												<div class="col-md-4">	
													<div class="form-group">
														<p>Enquiry Date :<b style="margin-left: 10px;">{{getEnquiryDetails.enquiryDate}}</b></p>															
													</div>
													<div class="form-group">
														<p>Client Name :<b style="margin-left: 12px;">{{getUserDetails.firstName}} {{getUserDetails.lastName}}</b></p>																
													</div>																																				
												</div>		
											</div>
											<div class="row">
												<div class="col-md-12">
													<p>Address :<b style="margin-left: 38px;">{{getUserDetails.address1}}, {{getUserDetails.address2}}<span ng-show="getUserDetails.address3 != ''">, {{getUserDetails.address3}},</span> {{getUserDetails.cityName}} - {{getUserDetails.pincode}}</b></p>															
												</div>
											</div>																																					
										</div>
									</div>
								</div>
							</div>							
						</div>						
						<div class="row">								
							<div class="col-md-12">
								<div class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											 <h4 class="panel-title"><i class="fa fa-gift" aria-hidden="true"></i>&nbsp;Add Products</h4>
										</div>
										<div class="panel-body">											
											<div class="table-responsive table-bordered">
												<table class="table">
													<thead>
														<tr>																	
															<th> Product Name </th>
															<th> Sale Type </th>																									
															<th class="text-right"> Qty </th>
															<th class="text-right"> Per Unit </th>
															<th class="text-right"> Total </th>							
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="item in getInvoiceProduct">																	
															<td> {{item.productName}} <br> {{item.productDescription}}<br>{{item.domainName}}<br>{{item.chargeFromDate}} <span ng-if="item.chargeFromDate != null">to</span> {{item.chargeToDate}}</td>
															<td> {{item.saleType}} </td>																															
															<td align="right"> {{item.productQty}} </td>
															<td align="right"> {{item.productSalePrice | number:2}}	</td>
															<td class="text-right"> {{item.productSalePrice * item.productQty | number:2}} </td>															
														</tr>																																
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">								
							<div class="col-md-12">
								<div class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											 <h4 class="panel-title"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>&nbsp;Terms & Conditions</h4>
										</div>
										<div class="panel-body">						
											<div class="table-responsive table-bordered">
												<table class="table">															
													<tbody>
														<tr ng-repeat="item in getInvoiceTerms">																	
															<td align="left">
																<p>{{item.conditionStatement}}</p>																																	
															</td>												
														</tr>																																
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>						
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-12">
								<button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>								
							</div>
						</div>											
					</div>
					</form>
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
		<div class="modal fade" id="productModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Product</h4>
					</div>
					<form id="AddProductForm" ng-submit="addProduct()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<label>Category<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoryByCategoryId(categorynameadd)" class="form-control" autofocus>
											<option value="">Category</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorCategoryName == 1" style="color: red;">{{msgCategoryName}}</p>
								</div>
								<div class="col-md-4">
									<label>Sub Category</label>
									<div class="input-group">
										<select id="subcategorynameadd" name="subcategorynameadd" ng-model="subcategorynameadd" ng-options="item.subcategoryId as item.subcategoryName for item in getSubcategories" class="form-control">
											<option value="">Sub Category</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#subcategoryModal" title="Add New Sub Category"><i class="fa fa-plus"></i></button>
										</span>
									</div>									
								</div>
								<div class="col-md-4">
									<label>Brand</label>
									<div class="input-group">
										<select id="brandnameadd" name="brandnameadd" ng-model="brandnameadd" ng-options="item.brandId as item.brandName for item in getBrands" class="form-control">
											<option value="">--Brand--</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#brandModal" title="Add New Brand"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
							</div>
							<div class="form-group"></div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Sale Type</label>									
										<select id="saletypeadd" name="saletypeadd" ng-model="saletypeadd" ng-options="item.productSaleTypeId as item.productSaleTypeName for item in getProductSaleType" class="form-control">
											<option value="">--Sale Type--</option>
										</select>										
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Product Name<font color="red" size="3">*</font></label>
										<input type="text" id="productnameadd" name="productnameadd" ng-model="productnameadd" placeholder="Product Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorProductName == 1" style="color: red;">{{msgProductName}}</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Part Number</label>
										<input type="text" id="partnumberadd" name="partnumberadd" ng-model="partnumberadd" placeholder="Part Number" class="form-control">
									</div>
								</div>								
							</div>							
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>HSN</label>
										<input type="number" id="hsnadd" name="hsnadd" ng-model="hsnadd" placeholder="HSN" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Recurring Billing<font color="red" size="3">*</font></label>									
										<select id="recurringbillingadd" name="recurringbillingadd" ng-model="recurringbillingadd" class="form-control" ng-change="setFlag()">
											<option value="">Recurring Billing</option>
											<option value="Y">Yes</option>
											<option value="N">No</option>
										</select>
										<p ng-show="errorRecurring == 1" style="color: red;">{{msgRecurring}}</p>										
									</div>
								</div>			
								<div class="col-md-3">
									<div class="form-group">
										<label>Recurring Value</label>
										<input type="number" id="recurringvalueadd" name="recurringvalueadd" ng-model="recurringvalueadd" placeholder="Recurring Value" class="form-control">
									</div>
								</div>
								<div class="col-md-3">									
									<label>Unit<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="unitidadd" name="unitidadd" ng-model="unitidadd" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control" ng-change="setFlag()">
											<option value="">Unit</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitModal" title="Add New Sub Category"><i class="fa fa-plus"></i></button>
										</span>								
									</div>
									<p ng-show="errorUnit == 1" style="color: red;">{{msgUnit}}</p>
								</div>																
							</div>
							<div class="row">								
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea rows="5" id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Description" class="form-control"></textarea>										
									</div>
								</div>								
							</div>
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Specifications</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-10">
												<div class="form-group">
													<input type="text" id="specificationadd" name="specificationadd" ng-model="specificationadd" placeholder="Specification" class="form-control" ng-change="setFlag()">
													<p ng-show="errorSpecification == 1" style="color: red;">{{msgSpecification}}</p>
												</div>
											</div>	
											<div class="col-md-2">
												<div class="form-group">
													<a href="#" ng-click="addSpecificationRow()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
												</div>
											</div>								
										</div>						
										<div class="table-responsive table-bordered">
											<table class="table">
												<thead>
													<tr>
														<th> Specification </th>														
														<th> Action </th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="item in specificationlist">
														<td> {{item.specification}} </td>														
														<td>
															<a href="#" ng-click="removeSpecificationRow(item)" class="delete" data-toggle="modal">
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
							<div class="row">
								<div class="col-md-4">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-truck" aria-hidden="true"></i>&nbsp;Supplier</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-8">
														<div class="input-group">
															<select id="supplieradd" name="supplieradd" ng-model="supplieradd" ng-options="item.userId as item.userCompanyName+' - '+item.firstName+' '+item.lastName for item in getSuppliers" class="form-control" ng-change="setFlag()">
																<option value=""> Supplier </option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Category"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorSupplier == 1" style="color: red;">{{msgSupplier}}</p>
													</div>					
													<div class="col-md-2">
														<div class="form-group">
															<a href="#" ng-click="addSupplierRow()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
														</div>
													</div>
												</div>												
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Supplier </th>																
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in supplierlist">
																<td> {{item.supplierName}} </td>																
																<td>
																	<a href="#" ng-click="removeSupplierRow(item)" class="delete" data-toggle="modal">
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
								<div class="col-md-8">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-inr" aria-hidden="true"></i>&nbsp;Product Price</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<select id="pricetypeadd" name="pricetypeadd" ng-model="pricetypeadd" ng-options="item.priceTypeId as item.priceTypeName for item in getPriceType" class="form-control" ng-change="setFlag()">
																<option value="">Price Type</option>
															</select>
															<p ng-show="errorPriceType == 1" style="color: red;">{{msgPriceType}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input id="priceadd" name="priceadd" ng-model="priceadd" type="text" placeholder="Price" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPrice == 1" style="color: red;">{{msgPrice}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<select id="currencyadd" name="currencyadd" ng-model="currencyadd" ng-options="item.currencyId as item.currencyName+' - '+item.currencyCode for item in getCurrencies" ng-init="currencyadd = 1" class="form-control" ng-change="setFlag()">
																<option value="">Currency</option>
															</select>
															<p ng-show="errorCurrency == 1" style="color: red;">{{msgCurrency}}</p>
														</div>
													</div>											
													<div class="col-md-3">
														<div class="form-group">
															<a href="#" ng-click="addPriceRow()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12 text-center">
														<div class="alert alert-info" ng-show="priceinfo == 1">
															<strong><span class="fa fa-info-circle"></span> {{pricemessage}}</strong>
														</div>
													</div>
												</div>
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Price Type </th>
																<th> Price </th>
																<th> Currency </th>
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in pricelist">
																<td> {{item.priceTypeName}} </td>
																<td> {{item.price | number : 2}} </td>
																<td> {{item.currencyCode}} </td>
																<td>
																	<a href="#" ng-click="removePriceRow(item)" class="delete" data-toggle="modal">
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
							</div>						
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="productSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{productSuccessMsg}}</strong>
									<strong ng-show="productSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{productErrorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="productSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="productSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="categoryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Category</h4>
					</div>
					<form ng-submit="addCategory()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Category Name<font color="red" size="3">*</font></label>
										<input type="text" id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" placeholder="Category Name" class="form-control" autofocus>
									</div>									
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Cat. Code<font color="red" size="3">*</font></label>
										<input type="text" id="categorycodeadd" name="categorycodeadd" ng-model="categorycodeadd" placeholder="Code" maxlength="2" capitalize class="form-control">
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd" name="imageadd" ng-model="imageadd" class="form-control">										
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="categorydescriptionadd" name="categorydescriptionadd" ng-model="categorydescriptionadd" placeholder="Description" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">
								<div class="col-md-3">
									<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								</div>
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="catsuccess == 1">
										<strong><span class="fa fa-check-circle"></span> {{catmessage}}</strong>
									</div>
									<div class="alert alert-info" ng-show="catinfo == 1">
										<strong><span class="fa fa-info-circle"></span> {{catmessage}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-tgsc"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>	
		<div class="modal fade" id="subcategoryModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Subcategory</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-3">
								<label>Select Category<font color="red" size="3">*</font></label>
								<div class="input-group">
									<select id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" ng-options="item.categoryId as item.categoryName for item in getCategories" class="form-control" autofocus>
										<option value="">Category</option>
									</select>
									<span class="input-group-btn">
										<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
									</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Sub Category Name<font color="red" size="3">*</font></label>
									<input type="text" id="subcategorynameadd" name="subcategorynameadd" ng-model="subcategorynameadd" placeholder="Sub Category Name" class="form-control" autofocus>
								</div>									
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Sub Cat. Code<font color="red" size="3">*</font></label>
									<input type="text" id="subcategorycodeadd" name="subcategorycodeadd" ng-model="subcategorycodeadd" placeholder="Code" maxlength="2" capitalize class="form-control">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Image</label>
									<input type="file" id="subimageadd" name="subimageadd" ng-model="subimageadd" class="form-control">										
								</div>									
							</div>
						</div>
						<div class="row" align="center">
							<div class="col-md-12">
								<img src="" id="target" />
								<form name="myForm">
									<input type="hidden" name="x" id="valuex" ng-model="valuex" />
									<input type="hidden" name="y" id="valuey" ng-model="valuey" />
									<input type="hidden" name="w" id="valuew" ng-model="valuew" />
									<input type="hidden" name="h" id="valueh" ng-model="valueh" />
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Description</label>
									<textarea id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Description" class="form-control"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-3">
								<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
							</div>
							<div class="col-md-6 text-left">
								<div class="alert alert-success" ng-show="subsuccess == 1">
									<strong><span class="fa fa-check-circle"></span> {{submessage}}</strong>
								</div>
								<div class="alert alert-info" ng-show="subinfo == 1">
									<strong><span class="fa fa-info-circle"></span> {{submessage}}</strong>
								</div>
							</div>
							<div class="col-md-3">
								<button type="submit" ng-click="addSubcategory()" ng-disabled="spin == 1" class="btn btn-tgsc"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>	
							</div>
						</div>					
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="brandModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Brand</h4>
					</div>
					<form ng-submit="addBrand()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Brand Name<font color="red" size="3">*</font></label>
										<input type="text" id="brandnameadd" name="brandnameadd" ng-model="brandnameadd" placeholder="Brand Name" class="form-control" autofocus>
									</div>									
								</div>								
								<div class="col-md-6">
									<div class="form-group">
										<label>Brand Logo</label>
										<input type="file" id="brandlogoadd" name="brandlogoadd" ng-model="brandlogoadd" class="form-control">										
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Brand Description</label>
										<textarea id="branddescriptionadd" name="branddescriptionadd" ng-model="branddescriptionadd" placeholder="Description" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">
								<div class="col-md-3">
									<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								</div>
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="brandsuccess == 1">
										<strong><span class="fa fa-check-circle"></span> {{brandmessage}}</strong>
									</div>
									<div class="alert alert-info" ng-show="brandinfo == 1">
										<strong><span class="fa fa-info-circle"></span> {{brandmessage}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-tgsc"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="unitModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Measurement Unit</h4>
					</div>
					<form id="AddUnitForm" ng-submit="addMeasurementUnit()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Measurement Unit Name<font color="red" size="3">*</font></label>
										<input type="text" id="measurementunitnameadd" name="measurementunitnameadd" ng-model="measurementunitnameadd" placeholder="Measurement Unit Name" class="form-control" autofocus>
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-group">
										<label>Description</label>
										<textarea id="unitdescriptionadd" name="unitdescriptionadd" ng-model="unitdescriptionadd" placeholder="Description" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">
								<div class="col-md-3">
									<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
								</div>
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="unitsuccess == 1">
										<strong><span class="fa fa-check-circle"></span> {{unitmessage}}</strong>
									</div>
									<div class="alert alert-info" ng-show="unitinfo == 1">
										<strong><span class="fa fa-info-circle"></span> {{unitmessage}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-tgsc"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
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
										<input type="text" id="companynameadd" name="companynameadd" ng-model="companynameadd" placeholder="Company Name" class="form-control" ng-change="setFlag()" autofocus>
										<p ng-show="errorCompanyName == 1" style="color: red;">{{msgCompanyName}}</p>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>First Name</label>
										<input type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="First Name" class="form-control">										
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
		<div class="modal fade" id="clientModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Client</h4>
					</div>
					<form id="AddClientForm" ng-submit="addClient()">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-4">
									<div class="form-group">
										<label>Company Name<font color="red" size="3">#</font></label>
										<input type="text" id="companynameadd" name="companynameadd" ng-model="companynameadd" placeholder="Company Name" class="form-control" ng-change="setFlag()" autofocus>
										<p ng-show="errorCompanyName == 1" style="color: red;">{{msgCompanyName}}</p>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>First Name<font color="red" size="3">#</font></label>
										<input type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="First Name" class="form-control" ng-change="setFlag()">										
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
							<div class="row">								
								<div class="col-md-3">
									<div class="form-group">
										<label>User Type<font color="red" size="3">*</font></label>
										<select id="usertypenameadd" name="usertypenameadd" ng-model="usertypenameadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" class="form-control" ng-init="usertypenameadd = 4" disabled>
											<option value="">User Type</option>
										</select>											
										<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>
									</div>
								</div>																	
								<div class="col-md-9">
									<div class="form-group">
										<label> Profile Picture </label>
										<input type="file" id="profilepicture" name="profilepicture" class="form-control">
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
							<div class="col-md-12">
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
			
			$("#datepicker,#datepicker1,#datepicker2,#datepicker3").kendoDatePicker({
				format: "dd/MM/yyyy",
				dateInput: true,
				value: new Date()
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
		</script>
	</body>
</html>