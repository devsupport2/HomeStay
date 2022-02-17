<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">		
		<title> Product </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/product.js"></script>	
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="<%= request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css" type="text/css" />		
		<style type="text/css">
			input[type=number]::-webkit-inner-spin-button, 
			input[type=number]::-webkit-outer-spin-button { 
			    -webkit-appearance: none;
			    -moz-appearance: none;
			    appearance: none;
			    margin: 0; 
			}
		</style>
					
	</head>	
	<body ng-app="MyApp" ng-controller="productCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Product
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Product</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Product</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<div class="box-body">							
							<div class="row">
								<div class="col-md-3">
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
								<div class="col-md-3">
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
								<!-- <div class="col-md-2">
									<label>Brand</label>
									<div class="input-group">
										<select id="brandnameadd" name="brandnameadd" ng-model="brandnameadd" ng-options="item.brandId as item.brandName for item in getBrands" class="form-control" ng-change="getSubbrandByBrandId(brandnameadd)">
											<option value="">Brand</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#brandModal" title="Add New Brand"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div> -->
								<div class="col-md-2">
									<div class="form-group">
										<label>Product Code</label>
										<input type="text" id="partnumberadd" name="partnumberadd" ng-model="partnumberadd" placeholder="Item Code" class="form-control">
									</div>
								</div>
								<!-- <div class="col-md-2">
									<div class="form-group">
										<label>Sale Type</label>									
										<select id="saletypeadd" name="saletypeadd" ng-model="saletypeadd" ng-options="item.productSaleTypeId as item.productSaleTypeName for item in getProductSaleType" class="form-control">
											<option value="">Sale Type</option>
										</select>										
									</div>
								</div> -->
								<!-- <div class="col-md-2">
									<div class="form-group">
										<label>HSN</label>
										<input type="number" id="hsnadd" name="hsnadd" ng-model="hsnadd" placeholder="HSN" class="form-control">
									</div>
								</div>	 -->	
								
								<div class="col-md-4">
									<div class="form-group">
										<label>Product Name<font color="red" size="3">*</font></label>
										<input type="text" id="productnameadd" name="productnameadd" ng-model="productnameadd" placeholder="Product Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorProductName == 1" style="color: red;">{{msgProductName}}</p>
									</div>
								</div>
								
														
							</div>							
							<div class="row">																
								
								
								<!-- <div class="col-md-3">									
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
								</div>	 -->															
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
										 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Scope of Supply</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-2">
												<div class="form-group">
													<input type="number" id="scopesequenceadd" name="scopesequenceadd" ng-model="scopesequenceadd" placeholder="Sequence" class="form-control" ng-change="setFlag()">
													<p ng-show="errorScopeSequence == 1" style="color: red;">{{msgScopeSequence}}</p>
												</div>
											</div>
											<div class="col-md-5">
												<div class="form-group">
													<input type="text" id="particularadd" name="particularadd" ng-model="particularadd" placeholder="Particular" class="form-control" ng-change="setFlag()">
													<p ng-show="errorParticular == 1" style="color: red;">{{msgParticular}}</p>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<input type="text" id="valueadd" name="valueadd" ng-model="valueadd" placeholder="Value" class="form-control" ng-change="setFlag()">
													<p ng-show="errorValue == 1" style="color: red;">{{msgValue}}</p>
												</div>
											</div>
											<div class="col-md-2">						
												<div class="input-group">
													<select id="scopeunitidadd" name="scopeunitidadd" ng-model="scopeunitidadd" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control">
														<option value="">Unit</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitModal" title="Add New Unit"><i class="fa fa-plus"></i></button>
													</span>								
												</div>												
											</div>	
											<div class="col-md-1">
												<div class="form-group">
													<a href="#" ng-click="addScopeRow()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
												</div>
											</div>								
										</div>						
										<div class="table-responsive table-bordered">
											<table class="table">
												<thead>
													<tr>
														<th> Sequence </th>
														<th> Particular </th>
														<th> Value </th>
														<th> Unit </th>														
														<th> Action </th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="item in scopeofsupplylist">
														<td>{{item.sequence}}</td>
														<td>{{item.particular}}</td>
														<td>{{item.value}}</td>
														<td>{{item.unitName}}</td>
														<td>
															<a href="#" ng-click="removeScopeRow(item)" class="delete" data-toggle="modal">
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
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Specifications</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-2">
												<div class="form-group">
													<input type="number" id="sequenceadd" name="sequenceadd" ng-model="sequenceadd" placeholder="Sequence" class="form-control" ng-change="setFlag()">
													<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>
												</div>
											</div>
											<div class="col-md-5">
												<div class="form-group">
													<input type="text" id="specificationadd" name="specificationadd" ng-model="specificationadd" placeholder="Specification" class="form-control" ng-change="setFlag()">
													<p ng-show="errorSpecification == 1" style="color: red;">{{msgSpecification}}</p>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<input type="text" id="specvalueadd" name="specvalueadd" ng-model="specvalueadd" placeholder="Specification Value" class="form-control" ng-change="setFlag()">
													<p ng-show="errorSpecValue == 1" style="color: red;">{{msgSpecValue}}</p>
												</div>
											</div>
											<div class="col-md-2">						
												<div class="input-group">
													<select id="specunitidadd" name="specunitidadd" ng-model="specunitidadd" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control">
														<option value="">Unit</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitModal" title="Add New Unit"><i class="fa fa-plus"></i></button>
													</span>								
												</div>												
											</div>	
											<div class="col-md-1">
												<div class="form-group">
													<a href="#" ng-click="addSpecificationRow()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
												</div>
											</div>								
										</div>						
										<div class="table-responsive table-bordered">
											<table class="table">
												<thead>
													<tr>
														<th> Sequence </th>
														<th> Specification </th>
														<th> Value </th>
														<th> Unit </th>														
														<th> Action </th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="item in specificationlist">
														<td>{{item.sequence}}</td>
														<td>{{item.specification}}</td>
														<td>{{item.specValue}}</td>
														<td>{{item.unitName}}</td>
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
								<!-- <div class="col-md-4">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-truck" aria-hidden="true"></i>&nbsp;Supplier</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-8">
														<div class="input-group">
															<select id="supplieradd" name="supplieradd" ng-model="supplieradd" ng-options="item.userId as item.userCompanyName for item in getSuppliers" class="form-control" ng-change="setFlag()">
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
								</div> -->
								<!-- <div class="col-md-8">
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
								</div> -->
							</div>													
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										 <h4 class="panel-title"><i class="fa fa-money" aria-hidden="true"></i>&nbsp;Tax</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-3">
												<div class="form-group">
													<select id="taxnameadd" name="taxnameadd" ng-model="taxnameadd" ng-options="item.taxId as item.taxName for item in getTaxes" class="form-control">
														<option value="">Tax*</option>
													</select>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<input id="rateadd" name="rateadd" ng-model="rateadd" type="text" placeholder="Rate(%)*" class="form-control">
												</div>
											</div>
											<div class="col-md-2 text-center">
												<div class="form-group">
													<a href="#" ng-click="addTaxRow()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
												</div>
											</div>											
										</div>										
										<div class="table-responsive table-bordered">
											<table class="table">
												<thead>
													<tr>
														<th> Tax </th>
														<th> Rate </th>														
														<th> Action </th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="item in taxlist">
														<td> {{item.taxName}} </td>
														<td> {{item.rate | number : 2}} %</td>														
														<td>
															<a href="#" ng-click="removeTaxRow(item)" class="delete" data-toggle="modal">
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
						<div class="box-footer">
							<div class="row">									
								<div class="col-md-8 text-left">
									<strong ng-show="productSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="productSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-click="addProduct()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>										
						</div>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Product List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchProduct() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchProduct()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th>Product Name</th>											
											<th>Product Code</th>
											<th>HSN</th>											
											<th class="text-center">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getProducts == ''">
											<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getProducts" style="cursor:pointer;cursor:hand">
											<td ng-click="getProduct(item.productId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.productName}}</td>											
											<td ng-click="getProduct(item.productId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.partNumber}}</td>
											<td ng-click="getProduct(item.productId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.hsn}}</td>											
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.productId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getProducts != ''">
										<tr>
											<td colspan="3">
												<div class="alert alert-info" ng-show="infodelete == 1">
													<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
												</div>
											</td>
											<td class="text-center">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getProducts.length+startindex}}</b> out of <b>{{allcounts.productCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getProducts.length+startindex == allcounts.productCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Product</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-4">
								<label>Category<font color="red" size="3">*</font></label>
								<div class="input-group">
									<select id="categoryname" name="categoryname" ng-model="categoryname" ng-options="item.categoryId as item.categoryName for item in getCategories" ng-change="getSubcategoryByCategoryId(categoryname)" class="form-control" autofocus>
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
									<select id="subcategoryname" name="subcategoryname" ng-model="subcategoryname" ng-options="item.subcategoryId as item.subcategoryName for item in getSubcategories" class="form-control">
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
									<select id="brandname" name="brandname" ng-model="brandname" ng-options="item.brandId as item.brandName for item in getBrands" class="form-control"  ng-change="getSubbrandByBrandId(brandname)">
										<option value="">Brand</option>
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
									<select id="saletype" name="saletype" ng-model="saletype" ng-options="item.productSaleTypeId as item.productSaleTypeName for item in getProductSaleType" class="form-control">
										<option value="">Sale Type</option>
									</select>									
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Product Name<font color="red" size="3">*</font></label>
									<input type="text" id="productname" name="productname" ng-model="productname" placeholder="Product Name" class="form-control" ng-change="setFlag()">
									<p ng-show="errorProductName == 1" style="color: red;">{{msgProductName}}</p>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Product Code</label>
									<input type="text" id="partnumber" name="partnumber" ng-model="partnumber" placeholder="Item Code" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">														
							<div class="col-md-3">
								<div class="form-group">
									<label>HSN</label>
									<input type="text" id="hsn" name="hsn" ng-model="hsn" placeholder="HSN" class="form-control">
								</div>
							</div>							
							<div class="col-md-3">									
								<label>Unit<font color="red" size="3">*</font></label>
								<div class="input-group">
									<select id="unitid" name="unitid" ng-model="unitid" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control" ng-change="setFlag()">
										<option value="">UoM</option>
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
									<textarea rows="5" id="description" name="description" ng-model="description" placeholder="Description" class="form-control"></textarea>
								</div>
							</div>							
						</div>
						<div class="panel-group">
							<div class="panel panel-default">
								<div class="panel-heading">
									 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Scope of Supply</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-2">
											<div class="form-group">
												<input type="number" id="scopesequence" name="scopesequence" ng-model="scopesequence" placeholder="Sequence" class="form-control" ng-change="setFlag()">
												<p ng-show="errorScopeSequence == 1" style="color: red;">{{msgScopeSequence}}</p>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<input type="text" id="particular" name="particular" ng-model="particular" placeholder="Particular" class="form-control">
												<p ng-show="errorParticular == 1" style="color: red;">{{msgParticular}}</p>
											</div>
										</div>	
										<div class="col-md-2">
											<div class="form-group">
												<input type="text" id="value" name="value" ng-model="value" placeholder="Value" class="form-control" ng-change="setFlag()">
												<p ng-show="errorValue == 1" style="color: red;">{{msgValue}}</p>
											</div>
										</div>
										<div class="col-md-2">						
											<div class="input-group">
												<select id="scopeunitid" name="scopeunitid" ng-model="scopeunitid" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control">
													<option value="">Unit</option>
												</select>
												<span class="input-group-btn">
													<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitModal" title="Add New Unit"><i class="fa fa-plus"></i></button>
												</span>								
											</div>												
										</div>
										<div class="col-md-1">
											<div class="form-group">
												<a href="#" ng-click="addScopeRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
											</div>
										</div>								
									</div>							
									<div class="table-responsive table-bordered">
										<table class="table">
											<thead>
												<tr>
													<th> Sequence </th>
													<th> Particular </th>														
													<th> Value </th>
													<th> Unit </th>
													<th> Action </th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in getproductscopeofsupply">
													<td> {{item.sequence}} </td>
													<td> {{item.particular}} </td>
													<td> {{item.value}} </td>
													<td> {{item.unitName}} </td>														
													<td>
														<a href="#" ng-click="removeScopeRowEdit(item)" class="delete" data-toggle="modal">
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
						<div class="panel-group">
							<div class="panel panel-default">
								<div class="panel-heading">
									 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Specifications</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-2">
											<div class="form-group">
												<input type="number" id="sequence" name="sequence" ng-model="sequence" placeholder="Sequence" class="form-control" ng-change="setFlag()">
												<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<input type="text" id="specification" name="specification" ng-model="specification" placeholder="Specification" class="form-control">
												<p ng-show="errorSpecification == 1" style="color: red;">{{msgSpecification}}</p>
											</div>
										</div>	
										<div class="col-md-2">
											<div class="form-group">
												<input type="text" id="specvalue" name="specvalue" ng-model="specvalue" placeholder="Specification Value" class="form-control" ng-change="setFlag()">
												<p ng-show="errorSpecValue == 1" style="color: red;">{{msgSpecValue}}</p>
											</div>
										</div>
										<div class="col-md-2">						
											<div class="input-group">
												<select id="specunitid" name="specunitid" ng-model="specunitid" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" class="form-control">
													<option value="">Unit</option>
												</select>
												<span class="input-group-btn">
													<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitModal" title="Add New Unit"><i class="fa fa-plus"></i></button>
												</span>								
											</div>												
										</div>
										<div class="col-md-1">
											<div class="form-group">
												<a href="#" ng-click="addSpecificationRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
											</div>
										</div>								
									</div>							
									<div class="table-responsive table-bordered">
										<table class="table">
											<thead>
												<tr>
													<th> Sequence </th>
													<th> Specification </th>														
													<th> Value </th>
													<th> Unit </th>
													<th> Action </th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in getproductspecificationlist">
													<td> {{item.sequence}} </td>
													<td> {{item.specification}} </td>													
													<td> {{item.specValue}} </td>
													<td> {{item.unitName}} </td>														
													<td>
														<a href="#" ng-click="removeSpecificationRowEdit(item)" class="delete" data-toggle="modal">
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
													<div class="form-group">
														<select id="supplier" name="supplier" ng-model="supplier" ng-options="item.userId as item.userCompanyName for item in getSuppliers" class="form-control" ng-change="setFlag()">
															<option value=""> Supplier </option>
														</select>
														<p ng-show="errorSupplier == 1" style="color: red;">{{msgSupplier}}</p>
													</div>
												</div>					
												<div class="col-md-2">
													<div class="form-group">
														<a href="#" ng-click="addSupplierRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<div class="alert alert-info" ng-show="supplierinfo == 1">
														<strong><span class="fa fa-info-circle"></span> {{suppliermessage}}</strong>
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
														<tr ng-repeat="item in getproductsupplierlist">
															<td> {{item.supplierName}} </td>																
															<td>
																<a href="#" ng-click="removeSupplierRowEdit(item)" class="delete" data-toggle="modal">
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
														<select id="pricetype" name="pricetype" ng-model="pricetype" ng-options="item.priceTypeId as item.priceTypeName for item in getPriceType" class="form-control" ng-change="setFlag()">
															<option value="">Price Type</option>
														</select>
														<p ng-show="errorPriceType == 1" style="color: red;">{{msgPriceType}}</p>
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<input id="price" name="price" ng-model="price" type="text" placeholder="Price" class="form-control" ng-change="setFlag()">
														<p ng-show="errorPrice == 1" style="color: red;">{{msgPrice}}</p>
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<select id="currency" name="currency" ng-model="currency" ng-options="item.currencyId as item.currencyName+' - '+item.currencyCode for item in getCurrencies" ng-init="currency = 1" class="form-control" ng-change="setFlag()">
															<option value="">Currency</option>
														</select>
														<p ng-show="errorCurrency == 1" style="color: red;">{{msgCurrency}}</p>
													</div>
												</div>											
												<div class="col-md-3">
													<div class="form-group">
														<a href="#" ng-click="addPriceRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
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
														<tr ng-repeat="item in getproductpricelist">
															<td> {{item.priceTypeName}} </td>
															<td> {{item.price | number : 2}} </td>
															<td> {{item.currencyCode}} </td>
															<td>
																<a href="#" ng-click="removePriceRowEdit(item)" class="delete" data-toggle="modal">
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
						<div class="panel-group">
							<div class="panel panel-default">
								<div class="panel-heading">
									 <h4 class="panel-title"><i class="fa fa-money" aria-hidden="true"></i>&nbsp;Tax</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<select id="taxname" name="taxname" ng-model="taxname" ng-options="item.taxId as item.taxName for item in getTaxes" class="form-control">
													<option value="">Tax*</option>
												</select>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<input id="rate" name="rate" ng-model="rate" type="text" placeholder="Rate(%)*" class="form-control">
											</div>
										</div>
										<div class="col-md-2 text-center">
											<div class="form-group">
												<a href="#" ng-click="addTaxRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span>&nbsp; ADD </a>
											</div>
										</div>
									</div>									
									<div class="row">
										<div class="col-md-12 text-center">
											<div class="alert alert-info" ng-show="taxinfo == 1">
												<strong><span class="fa fa-info-circle"></span> {{taxmessage}}</strong>
											</div>
										</div>
									</div>
									<div class="table-responsive table-bordered">
										<table class="table">
											<thead>
												<tr>
													<th> Tax </th>
													<th> Rate </th>													
													<th> Action </th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in gettaxlist">
													<td> {{item.taxName}} </td>
													<td> {{item.rate | number : 2}} %</td>													
													<td>
														<a href="#" ng-click="removeTaxRowEdit(item)" class="delete" data-toggle="modal">
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
					<div class="modal-footer">
						<div class="row">								
							<div class="col-md-8 text-left">
								<strong ng-show="productSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
								<strong ng-show="productSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
							</div>
							<div class="col-md-4 text-right">
								<button type="submit" ng-click="editProduct(productid)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Product </h4>						
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">						
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteProduct()">
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
								<div class="col-md-6">
									<div class="form-group">
										<label>Category Name<font color="red" size="3">*</font></label>
										<input type="text" id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" placeholder="Category Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorCategoryName == 1" style="color: red; font-size: 14px;">{{msgCategoryName}}</p>
									</div>									
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Cat. Code<font color="red" size="3">*</font></label>
										<input type="text" id="categorycodeadd" name="categorycodeadd" ng-model="categorycodeadd" placeholder="Code" maxlength="5" capitalize class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryCode == 1" style="color: red; font-size: 14px;">{{msgCategoryCode}}</p>
									</div>									
								</div>								
							</div>
							<div class="row">
								<div class="col-md-12">
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
								<div class="col-md-8 text-left">
									<p ng-show="categorySubmitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
									<p ng-show="categorySubmitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>	
								</div>			
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
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
									<select id="categorynameadd" name="categorynameadd" ng-model="categorynameadd" ng-options="item.categoryId as item.categoryName for item in getCategories" class="form-control" autofocus ng-change="setFlag()">
										<option value="">Category</option>
									</select>
									<span class="input-group-btn">
										<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#categoryModal" title="Add New Category"><i class="fa fa-plus"></i></button>
									</span>									
								</div>
								<p ng-show="errorCategoryName == 1" style="color: red; font-size: 14px;">{{msgCategoryName}}</p>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Sub Category Name<font color="red" size="3">*</font></label>
									<input type="text" id="subcategorynameadd" name="subcategorynameadd" ng-model="subcategorynameadd" placeholder="Sub Category Name" class="form-control" autofocus ng-change="setFlag()">
									<p ng-show="errorSubcategoryName == 1" style="color: red; font-size: 14px;">{{msgSubcategoryName}}</p>
								</div>									
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Sub Cat. Code<font color="red" size="3">*</font></label>
									<input type="text" id="subcategorycodeadd" name="subcategorycodeadd" ng-model="subcategorycodeadd" placeholder="Code" maxlength="5" capitalize class="form-control">
									<p ng-show="errorSubcategoryCode == 1" style="color: red; font-size: 14px;">{{msgSubcategoryCode}}</p>
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
							<div class="col-md-8 text-left">
								<p ng-show="subcategorySubmitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
								<p ng-show="subcategorySubmitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>	
							</div>			
							<div class="col-md-4 text-right">
								<button type="submit" ng-click="addSubcategory()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
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
										<input type="text" id="brandnameadd" name="brandnameadd" ng-model="brandnameadd" placeholder="Brand Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorBrandName == 1" style="color: red; font-size: 14px;">{{msgBrandName}}</p>
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
								<div class="col-md-8 text-left">
									<p ng-show="brandSubmitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
									<p ng-show="brandSubmitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>	
								</div>			
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
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
						<h4 class="modal-title">Add Unit</h4>
					</div>
					<form ng-submit="addMeasurementUnit()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Unit Name<font color="red" size="3">*</font></label>
										<input type="text" id="measurementunitnameadd" name="measurementunitnameadd" ng-model="measurementunitnameadd" placeholder="Measurement Unit Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorUnitName == 1" style="color: red; font-size: 14px;">{{msgUnitName}}</p>
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
								<div class="col-md-8 text-left">
									<p ng-show="unitSubmitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
									<p ng-show="unitSubmitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
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
					<form ng-submit="addUser()">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-4">
									<div class="form-group">
										<label>Company Name<font color="red" size="3">*</font></label>
										<input type="text" id="companynameadd" name="companynameadd" ng-model="companynameadd" placeholder="Company Name" class="form-control" ng-change="setFlag()">
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
										<select id="usertypenameadd" name="usertypenameadd" ng-model="usertypenameadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-init="usertypenameadd = 5" disabled class="form-control">
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
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" class="form-control" autofocus>
											<option value="">Country</option>
										</select>										
									</div>
								</div>
								<div class="col-md-3">
									<label>Select State</label>
									<div class="form-group">
										<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-init="statenameadd = 12" class="form-control" autofocus>
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
						</div>
						<div class="modal-footer">
							<div class="row">																
								<div class="col-md-6 text-left">
									<p ng-show="userSuccess == 1" style="color: green; font-size: 18px;"><b>{{userMsg}}</b></p>																	
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userspin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userspin == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>										
								</div>								
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>	
		
		<script>
			document.getElementById("manage").classList.add("active");
			document.getElementById("product").classList.add("active");
		</script>
		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		
		
	</body>
</html>