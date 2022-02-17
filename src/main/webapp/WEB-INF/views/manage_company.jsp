<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Company </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/company.js"></script>					
	</head>	
	<body ng-app="MyApp" ng-controller="companyCtrl" ng-cloak class="skin-blue sidebar-mini" ng-init="getCompanyDetail()">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						My Company						
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">My Company</li>
					</ol>
				</section>
				<section class="content">					
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Company List</h3>
								</div>		
							</div>
						</div>
						<form ng-submit="editCompany(1)">
						<div class="box-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Company Name<font color="red" size="3">*</font></label>
										<input type="text" id="companyname" name="companyname" ng-model="companyname" placeholder="Company Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCompanyName == 1" style="color: red; font-size: 14px;">{{msgCompanyName}}</p>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Company Logo</label>
										<input type="file" id="newcompanylogo" name="newcompanylogo" ng-model="newcompanylogo" class="form-control">										
									</div>									
								</div>
								<div class="col-md-2 text-center">
									<img alt="Comapny Logo" src="{{companylogo}}" class="img-responsive" style="width: 60px;">
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>GST No.</label>
										<input type="text" id="gstno" name="gstno" ng-model="gstno" placeholder="GST No." class="form-control" ng-change="setFlag()">
										<p ng-show="errorGSTNo == 1" style="color: red; font-size: 14px;">{{msgGSTNo}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Contact No1</label>
										<input type="text" id="contactno1" name="contactno1" ng-model="contactno1" placeholder="Contact No1" class="form-control">											
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Contact No2</label>
										<input type="text" id="contactno2" name="contactno2" ng-model="contactno2" placeholder="Contact No2" class="form-control">											
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Fax No</label>
										<input type="text" id="faxno" name="faxno" ng-model="faxno" placeholder="Fax No" class="form-control">											
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Website<font color="red" size="3">*</font></label>
										<input type="text" id="website" name="website" ng-model="website" placeholder="Website" class="form-control" ng-change="setFlag()">
										<p ng-show="errorWebsite == 1" style="color: red; font-size: 14px;">{{msgWebsite}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Email<font color="red" size="3">*</font></label>
										<input type="text" id="email" name="email" ng-model="email" placeholder="Email" class="form-control" ng-change="setFlag()">
										<p ng-show="errorEmail == 1" style="color: red; font-size: 14px;">{{msgEmail}}</p>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Bank Name<font color="red" size="3">*</font></label>
										<input type="text" id="bankname" name="bankname" ng-model="bankname" placeholder="Bank Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorBankName == 1" style="color: red; font-size: 14px;">{{msgBankName}}</p>
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Bank Address</label>
										<input type="text" id="bankadd" name="bankadd" ng-model="bankadd" placeholder="Bank Address" class="form-control" ng-change="setFlag()">
										<p ng-show="errorBankAdd == 1" style="color: red; font-size: 14px;">{{msgBankAdd}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Account Number<font color="red" size="3">*</font></label>
										<input type="text" id="accno" name="accno" ng-model="accno" placeholder="Account Number" class="form-control" ng-change="setFlag()">
										<p ng-show="errorAccountNumber == 1" style="color: red; font-size: 14px;">{{msgAccountNumber}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>IFS Code<font color="red" size="3">*</font></label>
										<input type="text" id="ifscode" name="ifscode" ng-model="ifscode" placeholder="IFS Code" class="form-control" ng-change="setFlag()">
										<p ng-show="errorIFSC == 1" style="color: red; font-size: 14px;">{{msgIFSC}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>SWIFT</label>
										<input type="text" id="swift" name="swift" ng-model="swift" placeholder="SWIFT" class="form-control">										
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>PAN<font color="red" size="3">*</font></label>
										<input type="text" id="panno" name="panno" ng-model="panno" placeholder="PAN" class="form-control" ng-change="setFlag()">
										<p ng-show="errorPAN == 1" style="color: red; font-size: 14px;">{{msgPAN}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Address Line1<font color="red" size="3">*</font></label>
										<input type="text" id="add1" name="add1" ng-model="add1" placeholder="Address 1" class="form-control" ng-change="setFlag()">
										<p ng-show="errorAdd1 == 1" style="color: red; font-size: 14px;">{{msgAdd1}}</p>
									</div>									
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Address Line2<font color="red" size="3">*</font></label>
										<input type="text" id="add2" name="add2" ng-model="add2" placeholder="Address 2" class="form-control" ng-change="setFlag()">
										<p ng-show="errorAdd2 == 1" style="color: red; font-size: 14px;">{{msgAdd2}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label>Country<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="countryid" name="countryid" ng-model="countryid" ng-options="item.countryId as item.countryName for item in getCountries" ng-init="countryid = 1" class="form-control" ng-change="getStateByCountryId(countryid)">
											<option value="">Country</option>
										</select>
										<p ng-show="errorCountry == 1" style="color: red; font-size: 14px;">{{msgCountry}}</p>										
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>State<font color="red" size="3">*</font></label>											
										<select id="stateid" name="stateid" ng-model="stateid" ng-options="item.stateId as item.stateName for item in getStates" ng-init="stateid = 1" class="form-control" ng-change="setFlag()">
											<option value="">State*</option>
										</select>
										<p ng-show="errorState == 1" style="color: red; font-size: 14px;">{{msgState}}</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>City<font color="red" size="3">*</font></label>
										<input type="text" id="city" name="city" ng-model="city" placeholder="City" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCity == 1" style="color: red; font-size: 14px;">{{msgCity}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Pincode<font color="red" size="3">*</font></label>
										<input type="text" id="pincode" name="pincode" ng-model="pincode" placeholder="Pincode" class="form-control" ng-change="setFlag()">
										<p ng-show="errorPincode == 1" style="color: red; font-size: 14px;">{{msgPincode}}</p>
									</div>									
								</div>
							</div>
						</div>
						<div class="box-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<div class="alert alert-success" ng-show="companySubmitSuccess == 1">
										<strong><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									</div>
									<div class="alert alert-info" ng-show="companySubmitError == 1">
										<strong><span class="fa fa-info-circle"></span> {{msgError}}</strong>
									</div>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>			
						</div>
						</form>
					</div>
				</section>
			</div>
		</div>
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("company").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
	</body>
</html>