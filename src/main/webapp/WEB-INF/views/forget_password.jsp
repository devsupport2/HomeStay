<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Forget Password</title>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />	
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/AdminLTE.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/blue.css">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/forget_password.js"></script>						
	</head>
	<body class="login-page" ng-app="MyApp" ng-controller="forgetPasswordCtrl" ng-cloak>
		<div class="login-box">			
			<div class="login-box-body">
				<div class="login-logo">
					<%-- <img class="img-responsive" style="display: unset;" src="<%=request.getContextPath() %>/resources/admin/images/Logo.png" alt="The Great Stuff. Co."> --%>
					<label>HomeStay Structures</label>
				</div>
				<p class="login-box-msg">Please enter your registered email</p>
				<form ng-submit="forgetPassword()">
					<div class="form-group has-feedback">
						<input type="email" id="email" name="email" ng-model="email" class="form-control" placeholder="Email*" autofocus ng-change="setFlag()">
						<span class="fa fa-envelope form-control-feedback"></span>
						<p ng-show="errorEmail == 1" style="color: red;">{{msgEmail}}</p>
					</div>					
					<div class="row">						
						<div class="col-xs-12 form-group">
							<button type="submit" ng-disabled="spin == 1" class="btn btn-primary btn-block btn-flat">Submit <i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i></button>
						</div>						
					</div>
					<div class="row">
						<div class="col-md-12">
							<a href="<%=request.getContextPath() %>/">Login</a>
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
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/icheck.min.js"></script>
		<script>
			$(function () {
				$('input').iCheck({
					checkboxClass: 'icheckbox_square-blue',
					radioClass: 'iradio_square-blue',
					increaseArea: '20%' // optional
				});
			});
		</script>
	</body>	
</html>

