<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> User </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/user.js"></script>
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>					
	</head>	
	<body ng-app="MyApp" ng-controller="userCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage User
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">User</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add User</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<form ng-submit="addUser()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>User Type<font color="red" size="3">*</font></label>
											<div class="input-group">
												<select id="usertypenameadd" name="usertypenameadd" ng-model="usertypenameadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="setFlag()" class="form-control">
													<option value="">User Type</option>
												</select>
												<span class="input-group-btn">
													<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New User Type"><i class="fa fa-plus"></i></button>
												</span>
											</div>
											<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>											
										</div>
									</div>								
									<div class="col-md-3">
										<div class="form-group" ng-show="usertypenameadd != 2">
											<label>Company Name<font color="red" size="3" ng-show="usertypenameadd == 3">*</font></label>
											<input type="text" id="companynameadd" name="companynameadd" ng-model="companynameadd" placeholder="Company Name" class="form-control" ng-change="setFlag()">
											<p ng-show="errorCompanyName == 1" style="color: red;">{{msgCompanyName}}</p>											
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>First Name<font color="red" size="3">*</font></label>
											<input type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="First Name" class="form-control" ng-change="setFlag()">
											<p ng-show="errorFirstName == 1" style="color: red;">{{msgFirstName}}</p>
										</div>									
									</div>									
									<div class="col-md-3">
										<div class="form-group">
											<label>Last Name</label>
											<input type="text" id="lastnameadd" name="lastnameadd" ng-model="lastnameadd" placeholder="Last Name" class="form-control">
										</div>									
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label>Gender</label>
											<div class="form-group">
												<select id="genderadd" name="genderadd" ng-model="genderadd" class="form-control">
													<option value="">Gender</option>
													<option value="m">Male</option>
													<option value="f">Female</option>
													<option value="o">Other</option>
												</select>
											</div>
										</div>
									</div>																											
									<div class="col-md-10">
										<div class="form-group">
											<label> Profile Picture </label>
											<input type="file" id="profilepictureadd" name="profilepictureadd" class="form-control">
										</div>
									</div>
								</div>								
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label> Date Of Birth </label>
											<div class="form-group">
												<input class="KendoDate" id="datepicker" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label> Aadhar Number </label>
											<input type="text" id="aadharnumberadd" name="aadharnumberadd" ng-model="aadharnumberadd" class="form-control" placeholder="0000-0000-0000" data-mask="9999-9999-9999" />
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label> Passport Number </label>
											<input type="text" id="passportnumberadd" name="passportnumberadd" ng-model="passportnumberadd" class="form-control" placeholder="Passport Number" />
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>PAN Number </label>
											<input type="text" id="pannumberadd" name="pannumberadd" ng-model="pannumberadd" class="form-control" placeholder="PAN Number" />
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
										<label>Country</label>
										<div class="input-group">
											<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" class="form-control">
												<option value="">Country</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-3">
										<label>State</label>
										<div class="input-group">
											<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-init="statenameadd = 12" class="form-control">
												<option value="">State</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New State"><i class="fa fa-plus"></i></button>
											</span>
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
											<label> Email </label>
											<input type="text" id="emailadd" name="emailadd" ng-model="emailadd" ng-change="checkEmailAddress()" class="form-control" placeholder="Email">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Password </label>
											<input type="text" id="passwordadd" name="passwordadd" ng-model="passwordadd" class="form-control" placeholder="Password">
										</div>
									</div>
								</div>
								<div class="row" ng-show="usertypenameadd == 2">
									<div class="col-md-4 form-group"> 
										<label>Employee Code <font color="red" size="3">*</font></label>
										<input type="number" class="form-control" id="codeadd" ng-model="codeadd" placeholder="Emp Code">
										<p ng-show="errorEmployeeCode == 1" style="color: red;">{{msgEmployeeCode}}</p>
									</div>
									<div class="col-md-4 form-group"> 
										<label>Hourly Wages<font color="red" size="3">*</font></label>
										<input type="number" class="form-control" id="hourlywagesadd" ng-model="hourlywagesadd" placeholder="Hourly Wages">
										<p ng-show="errorHourlyWages == 1" style="color: red;">{{msgHourlyWages}}</p>
									</div>
									<div class="col-md-4 form-group"> 
										<label>Overtime Wages per Hour<font color="red" size="3">*</font></label>
										<input type="number" class="form-control" id="overtimewagesadd" ng-model="overtimewagesadd" placeholder="Over Time Wages">
										<p ng-show="errorOverTime == 1" style="color: red;">{{msgOverTime}}</p>
									</div>
								</div>
								<div class="row" ng-show="usertypenameadd == 3">
									<div class="col-md-4">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign Products to Dealer</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<select id="productidadd" name="productidadd" ng-model="productidadd" class="form-control" ng-change="setFlag()">
																	<option value="">Product</option>
																	<option value="All">All Products</option>
																	<option ng-repeat="item in getProductNameList" value="{{item.productId}}">{{item.productName}}</option>
																</select>																
																<p ng-show="errorProductName == 1" style="color: red;">{{msgProductName}}</p>
															</div>															
														</div>	
														<div class="col-md-2">
															<div class="form-group">
																<a href="#" ng-click="addAssignProductRow()" class="btn btn-primary btn-sm" title="Add Product"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>													
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Product Name </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in assignproductlist">
																	<td>{{item.productName}}</td>																															
																	<td>
																		<a href="#" ng-click="removeAssignProductRow(item)" class="delete" data-toggle="modal">
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
							<div class="box-footer">
								<div class="row">
									<div class="col-md-8 text-left">
										<strong ng-show="userSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="userSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
									</div>				
									<div class="col-md-4 text-right">
										<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-2">
									<h3 class="box-title">User List</h3>
								</div>
								<div class="col-md-3">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchUser() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchUser()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<div class="form-group">
											<select id="usertype" name="usertype" ng-model="usertype" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="filterUserById(usertype)" class="form-control">
												<option value="">User Type</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;"></select>
								</div>
								<div class="col-md-2">
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
									</div>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th>Company Name</th>
											<th>User Name</th>						
											<th>Mobile No.</th>
											<th>Email</th>																			
											<th class="text-center"><a href="#deleteModal" data-toggle="modal" title="Delete" style="color: red;"><i class="fa fa-trash-o" aria-hidden="true" ng-click="checkRecordSelectForDelete()"></i></a></th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getUsers == ''">
											<td colspan="5"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getUsers" style="cursor:pointer;cursor:hand">
											<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.userCompanyName}}</td>
											<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.firstName}} {{item.middleName}} {{item.lastName}}</td>																						
											<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.mobileNumber}}</td>
											<td ng-click="getUser(item.userId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.email}}</td>																																	
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.userId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getUsers != ''">
										<tr>
											<td colspan="5">
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
								<div class="col-md-5">
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getUsers.length+startindex}}</b> out of <b>{{allcounts.userCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getUsers.length+startindex == allcounts.userCount" ng-click="next()">
										<i class="fa fa-step-forward"></i>
									</button>
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
						<h4 class="modal-title">Edit User</h4>
					</div>
					<form ng-submit="editUser(userid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>User Type<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="usertypename" name="usertypename" ng-model="usertypename" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" class="form-control" autofocus>
												<option value="">User Type</option>
											</select>
											<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>
										</div>
									</div>
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Company Name</label>
										<input type="text" id="companyname" name="companyname" ng-model="companyname" placeholder="Company Name" class="form-control">										
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>First Name<font color="red" size="3">*</font></label>
										<input type="text" id="firstname" name="firstname" ng-model="firstname" placeholder="First Name" class="form-control">
										<p ng-show="errorFirstName == 1" style="color: red;">{{msgFirstName}}</p>
									</div>
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Last Name</label>
										<input type="text" id="lastname" name="lastname" ng-model="lastname" placeholder="Last Name" class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label>Gender</label>
										<div class="form-group">
											<select id="gender" name="gender" ng-model="gender" class="form-control">
												<option value="">Gender</option>
												<option value="m">Male</option>
												<option value="f">Female</option>
												<option value="o">Other</option>
											</select>
										</div>
									</div>
								</div>																
								<div class="col-md-4">
									<div class="form-group">
										<label> Profile Picture </label>
										<input type="file" id="profilepicture" name="profilepicture" class="form-control">
									</div>
								</div>
								<div class="col-md-6 text-center">
									<img src="{{profilepicture1}}" class="img-responsive">
									<br ng-if="profilepicture1 != ''">
									<a ng-click="deleteProfilePicture()" class="btn btn-danger text-center" ng-if="profilepicture1 != ''" data-toggle="tooltip" title="Remove Profile Picture">
										<span class="fa fa-trash-o"></span>
									</a>
								</div>
							</div>
							<div class="row">								
								<div class="col-md-3">
									<div class="form-group">
										<label> Date Of Birth</label>
										<div class="form-group">
											<input class="KendoDate" id="datepicker1" ng-model="dateofbirth" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Aadhar Number </label>
										<input type="text" id="aadharnumber" name="aadharnumber" ng-model="aadharnumber" class="form-control" placeholder="0000-0000-0000" data-mask="9999-9999-9999" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Passport Number </label>
										<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control" placeholder="Passport Number" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>PAN Number </label>
										<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control" placeholder="PAN Number" />
									</div>
								</div>
							</div>
							<div class="row">								
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-1 </label>
										<input type="text" id="address1" name="address1" ng-model="address1" class="form-control" placeholder="Address Line-1" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-2 </label>
										<input type="text" id="address2" name="address2" ng-model="address2" class="form-control" placeholder="Address Line-2" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-3 </label>
										<input type="text" id="address3" name="address3" ng-model="address3" class="form-control" placeholder="Address Line-3" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label>Select Country</label>
									<div class="input-group">
										<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countryname)" class="form-control">
											<option value="">Country</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<label>Select State</label>
									<div class="input-group">
										<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getStates" class="form-control">
											<option value="">State</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New State"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> City </label>
										<input type="text" id="cityname" name="cityname" ng-model="cityname" class="form-control" placeholder="City Name">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Pincode </label>
										<input type="text" id="pincode" name="pincode" ng-model="pincode" class="form-control" placeholder="Pincode">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label> Mobile Number </label>
										<input type="text" id="mobilenumber" name="mobilenumber" ng-model="mobilenumber" class="form-control" placeholder="Mobile Number">
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Landline Number </label>
										<input type="text" id="landlinenumber" name="landlinenumber" ng-model="landlinenumber" class="form-control" placeholder="Landline Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Email </label>
										<input type="text" id="email" name="email" ng-model="email" ng-change="checkEmailAddressEdit()" class="form-control" placeholder="Email">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Password </label>
										<input type="text" id="password" name="password" ng-model="password" class="form-control" placeholder="Password">
									</div>
								</div>
							</div>
							<div class="row" ng-show="usertypename == 2">
									<div class="col-md-4 form-group"> 
										<label>Employee Code<font color="red" size="3">*</font></label>
										<input type="number" class="form-control" id="codeedit" ng-model="codeedit" placeholder="Emp Code">
										<p ng-show="errorEmployeeCode == 1" style="color: red;">{{msgEmployeeCode}}</p>
									</div>
									<div class="col-md-4 form-group"> 
										<label>Hourly Wages<font color="red" size="3">*</font></label>
										<input type="number" class="form-control" id="hourlywagesedit" ng-model="hourlywagesedit" placeholder="Hourly Wages">
										<p ng-show="errorHourlyWages == 1" style="color: red;">{{msgHourlyWages}}</p>
									</div>
									<div class="col-md-4 form-group"> 
										<label>Overtime Wages per Hour<font color="red" size="3">*</font></label>
										<input type="number" class="form-control" id="overtimewagesedit" ng-model="overtimewagesedit" placeholder="Over Time Wages">
										<p ng-show="errorOverTime == 1" style="color: red;">{{msgOverTime}}</p>
									</div>
								</div>
							<div class="row" ng-show="usertypename == 3">
								<div class="col-md-6">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign Products to Dealer</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-10">
														<div class="form-group">
															<select id="productid" name="productid" ng-model="productid" class="form-control" ng-change="setFlag()">
																<option value="">Product</option>
																<option ng-repeat="item in getProductNameList" value="{{item.productId}}">{{item.productName}}</option>
															</select>																
															<p ng-show="errorProductName == 1" style="color: red;">{{msgProductName}}</p>
														</div>															
													</div>	
													<div class="col-md-2">
														<div class="form-group">
															<a href="#" ng-click="editAssignProductRow(userid)" class="btn btn-primary btn-sm" title="Add Product"><span class="fa fa-plus"></span></a>
														</div>
													</div>								
												</div>													
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Product Name </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in dealerProducts">
																<td>{{item.productName}}</td>																															
																<td>
																	<a href="#" ng-click="deleteAssignProductRow(userid, item.dealerProductId)" class="delete" data-toggle="modal">
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
								<div class="col-md-8 text-left">
									<strong ng-show="userSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="userSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>						
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>		
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete User </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteUser()">
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
		<div class="modal fade" id="countryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Country</h4>
					</div>
					<form ng-submit="addCountry()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Country Name<font color="red" size="3">*</font></label>
										<input type="text" id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" placeholder="Country Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorCountryName == 1" style="color: red;">{{msgCountryName}}</p>
									</div>									
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Country Code</label>
										<input type="text" id="countrycodeadd" name="countrycodeadd" ng-model="countrycodeadd" class="form-control">										
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Dialling Code</label>
										<input type="text" id="countrydialingcodeadd" name="countrydialingcodeadd" ng-model="countrydialingcodeadd" class="form-control">										
									</div>									
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="countrySubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="countrySubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spinCountry == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinCountry == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>		
		<div class="modal fade" id="stateModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add State</h4>
					</div>
					<form ng-submit="addState()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Country<font color="red" size="3">*</font></label>									
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" autofocus ng-change="setFlag()">
											<option value="">Country</option>
										</select>
										<p ng-show="errorCountry == 1" style="color: red;">{{msgCountry}}</p>										
									</div>									
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label>State Name<font color="red" size="3">*</font></label>
										<input type="text" id="statenameadd" name="statenameadd" ng-model="statenameadd" placeholder="State Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorStateName == 1" style="color: red;">{{msgStateName}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>State Code</label>
										<input type="text" id="statecodeadd" name="statecodeadd" ng-model="statecodeadd" placeholder="State Code" maxlength="3" capitalize class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="stateSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="stateSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spinState == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinState == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	
		<script src="<%= request.getContextPath() %>/resources/admin/js/bootstrap-inputmask.js"></script>
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker,#datepicker1").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true
					
		         });
		    });
			$(".KendoDate").bind("focus", function(){
	  			$(this).data("kendoDatePicker").open(); 
			});
			$(function () {
				$(".select2").select2();
			});
			document.getElementById("manage").classList.add("active");
			document.getElementById("user").classList.add("active");
		</script>		
	</body>
</html>