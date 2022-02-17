<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Dashboard </title>		
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />				
	</head>	
	<body ng-app="MyApp" ng-controller="dashboardCtrl" ng-cloak ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Admin Dashboard						
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Dashboard</li>
					</ol>
				</section>
				<!-- <section class="content">
					<div class="row">
						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>{{getEnquiryCurrentStats.openCount}}</h3>
									<p>New Orders</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-green">
								<div class="inner">
									<h3>{{getEnquiryCurrentStats.wonCount}}</h3>
									<p>New Orders</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-red">
								<div class="inner">
									<h3>{{getEnquiryCurrentStats.lostCount}}</h3>
									<p>New Orders</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</section> -->					
			</div>				
		</div>	
		<script>
			document.getElementById("manage").classList.add("active");				
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>			
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/dashboard.js"></script>			
	</body>
</html>