<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Change Password </title>		
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/change_password.js"></script>				
	</head>	
	<body ng-app="MyApp" ng-controller="changePasswordCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Change Password						
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Change Password</li>
					</ol>
				</section>
				<div class="login-box">			
					<div class="login-box-body">				
						<form ng-submit="changePassword(<%= session.getAttribute("useridadmin")%>)">
							<div class="form-group has-feedback">
								<input type="password" id="currentpassword" name="currentpassword" ng-model="currentpassword" class="form-control" placeholder="Current Password*" ng-change="setFlag()">
								<span class="fa fa-lock form-control-feedback"></span>
								<p ng-show="errorCurrentPassword == 1" style="color: red;">{{msgCurrentPassword}}</p>
							</div>	
							<div class="form-group has-feedback">
								<input type="password" id="newpassword" name="newpassword" ng-model="newpassword" class="form-control" placeholder="New Password*" ng-change="setFlag()">
								<span class="fa fa-lock form-control-feedback"></span>
								<p ng-show="errorNewPassword == 1" style="color: red;">{{msgNewPassword}}</p>
							</div>
							<div class="form-group has-feedback">
								<input type="password" id="confirmpassword" name="confirmpassword" ng-model="confirmpassword" class="form-control" placeholder="Confirm Password*" ng-change="setFlag()">
								<span class="fa fa-lock form-control-feedback"></span>
								<p ng-show="errorConfirmPassword == 1" style="color: red;">{{msgConfirmPassword}}</p>
							</div>				
							<div class="row">						
								<div class="col-xs-12 form-group">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-primary btn-block btn-flat">Submit <i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button>
								</div>						
							</div>							
							<div class="row">
								<div class="col-md-12 text-center">
									<p ng-show="submitSuccess == 1" style="color: green;">{{msgSuccess}}</p>
									<p ng-show="submitError == 1" style="color: red;">{{msgError}}</p>
								</div>
							</div>										
						</form>				
					</div>
				</div>				
			</div>							
		</div>	
			<script>
				document.getElementById("manage").classList.add("active");				
			</script>		
			<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
			<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
			<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>			
			<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	    	
				
	</body>
</html>