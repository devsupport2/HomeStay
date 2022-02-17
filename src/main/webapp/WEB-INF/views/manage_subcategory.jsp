<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Sub Category </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/subcategory.js"></script>	
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css" type="text/css" />			
	</head>	
	<body ng-app="MyApp" ng-controller="subcategoryCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Sub Category
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Sub Category</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Sub Category</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>												
						<div class="box-body">
							<div class="row">
								<div class="col-md-3">
									<label>Select Category<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" ng-options="item.categoryId as item.categoryName for item in getCategories" class="form-control" autofocus ng-change="setFlag()">
											<option value="">Category</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorCategoryId == 1" style="color: red;">{{msgCategoryId}}</p>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Sub Category Name<font color="red" size="3">*</font></label>
										<input type="text" id="subcategorynameadd" name="subcategorynameadd" ng-model="subcategorynameadd" placeholder="Sub Category Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorSubcategoryName == 1" style="color: red;">{{msgSubcategoryName}}</p>
									</div>									
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Sub Category Code<font color="red" size="3">*</font></label>
										<input type="text" id="subcategorycodeadd" name="subcategorycodeadd" ng-model="subcategorycodeadd" placeholder="Sub Category Code" maxlength="5" capitalize class="form-control" ng-change="setFlag()">
										<p ng-show="errorSubcategoryCode == 1" style="color: red;">{{msgSubcategoryCode}}</p>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd" name="imageadd" ng-model="imageadd" class="form-control">										
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
						<div class="box-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="subcategorySubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="subcategorySubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success" ng-click="addSubcategory()"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>										
								</div>
							</div>			
						</div>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Sub Category List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchSubcategory() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchSubcategory()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
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
											<th width="50%">Sub Category Name</th>
											<th width="25%">Category Name</th>											
											<th width="20%">Image</th>
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getSubcategories == ''">
											<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getSubcategories" style="cursor:pointer;cursor:hand">
											<td ng-click="getSubcategory(item.subcategoryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.subcategoryName}} ({{item.subcategoryCode}})</td>
											<td ng-click="getSubcategory(item.subcategoryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.categoryName}}</td>											
											<td ng-click="getSubcategory(item.subcategoryId)" title="Edit Record" data-toggle="modal" data-target="#editModal"><img src="{{item.image}}" class="img-tgsc-list" alt="Image" ng-if="item.image != ''"></td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.subcategoryId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getSubcategories != ''">
										<tr>
											<td colspan="3">
												<div class="alert alert-info" ng-show="infodelete == 1">
													<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
												</div>
											</td>
											<td class="text-right">
												<a href="#deleteModal" data-toggle="modal" ng-click="checkRecordSelectForDelete()" style="color: #fff;" class="btn btn-danger">
													<i style="margin: 0 0px;" class="fa fa-trash-o" aria-hidden="true"></i>
												</a>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="box-footer">
							<div class="row">								
								<div class="col-md-5">
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getSubcategories.length+startindex}}</b> out of <b>{{allcounts.subcategoryCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getSubcategories.length+startindex == allcounts.subcategoryCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Sub Category</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-4">
								<label>Select Category<font color="red" size="3">*</font></label>
								<div class="input-group">
									<select id="categoryname" name="categoryname" ng-model="categoryname" ng-options="item.categoryId as item.categoryName for item in getCategories" class="form-control" autofocus ng-change="setFlag()">
										<option value="">Category</option>
									</select>
									<span class="input-group-btn">
										<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
									</span>
								</div>
								<p ng-show="errorCategoryId == 1" style="color: red;">{{msgCategoryId}}</p>
							</div>
							<div class="col-md-5">
								<div class="form-group">
									<label>Sub Category Name<font color="red" size="3">*</font></label>
									<input type="text" id="subcategoryname" name="subcategoryname" ng-model="subcategoryname" placeholder="Sub Category Name" class="form-control" ng-change="setFlag()">
									<p ng-show="errorSubcategoryName == 1" style="color: red;">{{msgSubcategoryName}}</p>
								</div>									
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Sub Category Code<font color="red" size="3">*</font></label>
									<input type="text" id="subcategorycode" name="subcategorycode" ng-model="subcategorycode" placeholder="Sub Category Code" maxlength="5" capitalize class="form-control" ng-change="setFlag()">
									<p ng-show="errorSubcategoryCode == 1" style="color: red;">{{msgSubcategoryCode}}</p>
								</div>									
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Image</label>
									<input type="file" id="image" name="image" ng-model="image" class="form-control">
								</div>
							</div>
							<div class="col-md-2 text-center" ng-if="subcategoryimage != ''">
								<img src="{{subcategoryimage}}" class="img-responsive">								
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Description</label>
									<textarea id="description" name="description" ng-model="description" placeholder="Description" class="form-control"></textarea>
								</div>
							</div>
						</div>
						<div class="row" align="center">
							<div class="col-md-12">
								<img src="" id="target1" />
								<form name="myForm1">
									<input type="hidden" name="x1" id="valuex1" ng-model="valuex1" />
									<input type="hidden" name="y1" id="valuey1" ng-model="valuey1" />
									<input type="hidden" name="w1" id="valuew1" ng-model="valuew1" />
									<input type="hidden" name="h1" id="valueh1" ng-model="valueh1" />
								</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">								
							<div class="col-md-8 text-left">
								<strong ng-show="subcategorySubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
								<strong ng-show="subcategorySubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
							</div>
							<div class="col-md-4 text-right">
								<button type="submit" ng-disabled="spin == 1" class="btn btn-success" ng-click="editSubcategory(subcategoryid)"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
							</div>
						</div>					
					</div>
				</div>
			</div>
		</div>
		
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Sub Category </h4>						
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">						
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteSubcategory()">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					</div>
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
								<div class="col-md-5">
									<div class="form-group">
										<label>Category Name<font color="red" size="3">*</font></label>
										<input type="text" id="categorynameadd1" name="categorynameadd1" ng-model="categorynameadd1" placeholder="Category Name" class="form-control" autofocus>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Category Code<font color="red" size="3">*</font></label>
										<input type="text" id="categorycodeadd1" name="categorycodeadd1" ng-model="categorycodeadd1" placeholder="Category Code" maxlength="3" capitalize class="form-control">
									</div>									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd1" name="imageadd1" ng-model="imageadd1" class="form-control">										
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="descriptionadd1" name="descriptionadd1" ng-model="descriptionadd1" placeholder="Description" class="form-control"></textarea>
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
									<div class="alert alert-success" ng-show="successcategory == 1">
										<strong><span class="fa fa-check-circle"></span> {{messagecategory}}</strong>
									</div>
									<div class="alert alert-info" ng-show="infocategory == 1">
										<strong><span class="fa fa-info-circle"></span> {{messagecategory}}</strong>
									</div>
								</div>
								<div class="col-md-3">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<script>
			//For Add
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target').data('Jcrop')) {
							    $('#target').data('Jcrop').destroy();
							    $('#target').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        if(img.width < 360 || img.height < 255)
						        {
						        	alert("Minimum image size should be 360px X 255px");
						        	$('#target').attr('src', "");
						        	document.getElementById("imageadd").value = null;
						        }
						        else
						        {
						        	$('#target').css("min-height", "255px");
								    $('#target').css("min-width", "360px");
									
									$('#target').attr('src', e.target.result);
									$('#target').Jcrop({
										aspectRatio : 1.6 / 1,
										boxWidth : 650,
										boxHeight : 400,
										minSize : [100, 100],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates,
										onSelect : setCoordinates
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imageadd").change(function() {
					readURL(this);
				});
				$("#imageadd").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm.x.value = Math.round(c.x);
				document.myForm.y.value = Math.round(c.y);
				document.myForm.w.value = Math.round(c.w);
				document.myForm.h.value = Math.round(c.h);
			};
			
			function checkCoordinates() {
				if (document.myForm.x.value == "" || document.myForm.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		
		<script>
			//For Edit
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target1').data('Jcrop')) {
							    $('#target1').data('Jcrop').destroy();
							    $('#target1').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        if(img.width < 360 || img.height < 255)
						        {
						        	alert("Minimum image size should be 360px X 255px");
						        	$('#target1').attr('src', "");
						        	document.getElementById("image").value = null;
						        }
						        else
						        {
						        	$('#target1').css("min-height", "255px");
								    $('#target1').css("min-width", "360px");
									
									$('#target1').attr('src', e.target.result);
									$('#target1').Jcrop({
										aspectRatio : 1.6 / 1,
										boxWidth : 650,
										boxHeight : 400,
										minSize : [100, 100],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates1,
										onSelect : setCoordinates1
									});
						        }
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#image").change(function() {
					readURL(this);
				});
				$("#image").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates1(c) {
				//alert("x " + c.x + " y " + c.y);
				//alert("w " + c.w + " h " + c.h);
				document.myForm1.x1.value = Math.round(c.x);
				document.myForm1.y1.value = Math.round(c.y);
				document.myForm1.w1.value = Math.round(c.w);
				document.myForm1.h1.value = Math.round(c.h);
			};
			
			function checkCoordinates1() {
				if (document.myForm1.x1.value == "" || document.myForm1.y1.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		
		<script>
		document.getElementById("master").classList.add("active");
		document.getElementById("subcategory").classList.add("active");
		</script>
		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		
	</body>
</html>