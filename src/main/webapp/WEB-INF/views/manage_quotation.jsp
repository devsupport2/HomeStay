<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">		
		<title> Quotations </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/quotation.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>				
	</head>	
	<body ng-app="MyApp" ng-controller="quotationCtrl" ng-cloak ng-init="sendQuotationEmail('<%= session.getAttribute("quotationEmailFlag") %>', <%= session.getAttribute("quotationId") %>)" class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Quotations
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Quotations</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title"> Add Quotation</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>	
						<form>					
							<div class="box-body">								
								<div class="row">								
									<div class="col-md-2">
										<label>Quotation Date<font color="red" size="3">*</font></label>
										<div class="input-group">
											<input class="KendoDate" id="datepicker" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>P.O.</label>
											<input type="text" id="purchaseorderadd" name="purchaseorderadd" ng-model="purchaseorderadd" placeholder="Purchase Order" class="form-control" ng-change="setFlag()">											
										</div>									
									</div>
									<div class="col-md-2">
										<label>P.O. Date</label>
										<div class="input-group">
											<input class="KendoDate" id="podate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Client<font color="red" size="3">*</font></label>											
											<select id="clientidadd" name="clientidadd" ng-model="clientidadd" class="form-control">
												<option value="">Client</option>
												<option ng-repeat="item in clientAndProspectName" value="{{item.userId}}">{{item.userCompanyName}} - {{item.firstName}} {{item.lastName}}</option>
											</select>												
											<p ng-show="errorClient == 1" style="color: red; font-size: 14px;">{{msgClient}}</p>										
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
													<div class="row">														
														<div class="col-md-3">															
															<div class="form-group">
																<select id="productid" name="productid" ng-model="productid" class="form-control" ng-options="item.productId as item.productName for item in getProductName" ng-change="getProductDetailById(productid)">
																	<option value="">Product</option>																	
																</select>
																<p ng-show="errorProduct == 1" style="color: red; font-size: 14px;">{{msgProduct}}</p>																																
															</div>
														</div>
														<div class="col-md-1">
															<div class="form-group">
																<input type="text" id="qty" name="qty" ng-model="qty" class="form-control" placeholder="Qty" ng-change="setFlag()">
																<p ng-show="errorQty == 1" style="color: red; font-size: 14px;">{{msgQty}}</p>
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<select id="unitid" name="unitid" ng-model="unitid" class="form-control" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" disabled>
																	<option value="">Unit</option>																	
																</select>																
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<input type="text" id="saleprice" name="saleprice" ng-model="saleprice" class="form-control" placeholder="Sale Price per Unit"  ng-change="setFlag()">
																<p ng-show="errorSalePrice == 1" style="color: red; font-size: 14px;">{{msgSalePrice}}</p>
															</div>
														</div>	
														<div class="col-md-1">
															<div class="form-group">
																<a href="#" ng-click="addProductRow()" class="btn btn-primary btn-sm" title="Add Product Row"><span class="fa fa-plus"></span></a>
															</div>
														</div>													
													</div>				
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>																	
																	<th> Product Name </th>																																										
																	<th class="text-right"> Qty </th>
																	<th class="text-right"> Per Unit </th>
																	<th class="text-right"> Total </th>														
																	<th class="text-center"> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in productlist">																	
																	<td> {{item.productTitle}}</td>																																																
																	<td align="right"> {{item.qty}} </td>
																	<td align="right"> {{item.salePrice | number:2}}	</td>
																	<td class="text-right"> {{item.qty * item.salePrice | number:2}} </td>														
																	<td class="text-center">
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
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;Scope of Supply</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="particular" name="particular" ng-model="particular" placeholder="Particular" class="form-control">
																<p ng-show="errorParticular == 1" style="color: red;">{{msgParticular}}</p>
															</div>
														</div>	
														<div class="col-md-3">
															<div class="form-group">
																<input type="text" id="value" name="value" ng-model="value" placeholder="Value" class="form-control" ng-change="setFlag()">
																<p ng-show="errorValue == 1" style="color: red;">{{msgValue}}</p>
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<input type="text" id="scopeqty" name="scopeqty" ng-model="scopeqty" placeholder="Qty" class="form-control" ng-change="setFlag()">
																<p ng-show="errorScopeQty == 1" style="color: red;">{{msgScopeQty}}</p>
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
																<a href="#" ng-click="addScopeRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>							
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Particular </th>														
																	<th> Value </th>
																	<th> Qty </th>
																	<th> Unit </th>
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in getproductscopeofsupplyedit">
																	<td> {{item.particular}} </td>
																	<td> {{item.value}} </td>
																	<td> {{item.scopeQty}} </td>
																	<td> {{item.unitName}} </td>														
																	<td>
																		<a href="#" ng-click="removeScopeRowEdit1(item)" class="delete" data-toggle="modal">
																			<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																		</a>
																	</td>
																</tr>
																<tr ng-repeat="item in getproductscopeofsupply">
																	<td> {{item.particular}} </td>
																	<td> {{item.value}} </td>
																	<td>
																		<input type="text" ng-model="item.scopeQty" placeholder="Qty" class="form-control" style="width: 50%">
																	</td>
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
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="specification" name="specification" ng-model="specification" placeholder="Specification" class="form-control">
																<p ng-show="errorSpecification == 1" style="color: red;">{{msgSpecification}}</p>
															</div>
														</div>	
														<div class="col-md-3">
															<div class="form-group">
																<input type="text" id="specvalue" name="specvalue" ng-model="specvalue" placeholder="Specification Value" class="form-control" ng-change="setFlag()">
																<p ng-show="errorSpecValue == 1" style="color: red;">{{msgSpecValue}}</p>
															</div>
														</div>
														<div class="col-md-3">						
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
																<a href="#" ng-click="addSpecificationRowEdit()" class="btn btn-primary btn-sm"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>							
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Specification </th>														
																	<th> Value </th>
																	<th> Unit </th>
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in getproductspecificationlist">
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
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>&nbsp;Quotation Cover Letter</h4>
												</div>
												<div class="panel-body">						
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">															
																<textarea id="letterdescription" name="letterdescription" cols="200" class="form-control"></textarea>																
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>&nbsp;Terms & Conditions</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">
																<select id="termid" name="termid" ng-model="termid" ng-options="item.termMasterId as item.termTitle for item in getTermTitle" class="form-control">
																	<option value="">Term Title</option>
																</select>
																<p ng-show="errorTermTitle == 1" style="color: red;">{{msgTermTitle}}</p>																								
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<input type="number" id="sequence" name="sequence" ng-model="sequence" placeholder="Sequence*" class="form-control" ng-change="setFlag()">
																<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>																
															</div>
														</div>
														<div class="col-md-2">
															<div class="form-group">
																<input type="text" id="label" name="label" ng-model="label" placeholder="Label" class="form-control" ng-change="setFlag()">																
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="statement" name="statement" ng-model="statement" placeholder="Statement" class="form-control" ng-change="setFlag()">
																<p ng-show="errorStatement == 1" style="color: red;">{{msgStatement}}</p>
															</div>
														</div>
														<div class="col-md-1">
															<button ng-click="addTermRow()" class="btn btn-primary btn-sm" ng-disabled="conditionspin == 1" title="Add New Condtion"><span class="fa fa-plus" ng-if="conditionspin != 1"></span><i class="fa fa-refresh fa-spin" ng-if="conditionspin == 1"></i></button>
														</div>
													</div>						
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Term Title </th>
																	<th> Sequence </th>														
																	<th> Label </th>
																	<th> Statement </th>
																	<th class="text-center"> Action </th>
																</tr>
															</thead>															
															<tbody>																
																<tr ng-repeat="item in termStatement">																	
																	<td>{{item.termTitle}}</td>
																	<td>{{item.sequence}}</td>
																	<td>{{item.label}}</td>
																	<td>{{item.statement}}</td>																															
																	<td class="text-center">
																		<a href="#" ng-click="removeTermRow(item)" class="delete" data-toggle="modal">
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
										<strong ng-show="quotationSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="quotationSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
									</div>
									<div class="col-md-4 text-right">
										<button type="button" ng-click="generateQuatation(<%= request.getParameter("enquiryid") %>)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>					
					</div>					
									
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Quotation List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchInvoice() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchQuotation()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th>#</th>											
											<th>Quotation Date</th>											
											<th>Client</th>
											<th class="text-center">Preview</th>
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getQuotation == ''">
											<td colspan="5"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getQuotation">
											<td style="vertical-align: middle;">{{item.quotationNo}}</td>											
											<td style="vertical-align: middle;">{{item.quotationDate}}</td>											
											<td style="vertical-align: middle;">{{item.userCompanyName}} <br> {{item.firstName}} {{item.middleName}} {{item.lastName}}</td>
											<td style="vertical-align: middle;" class="text-center"><a href="#"><i class="fa fa-print" aria-hidden="true" ng-click="printQuotation(item.quotationId)"></i></a></td>											
											<td style="vertical-align: middle;" title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.quotationId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getQuotation != ''">
										<tr>
											<td colspan="4">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getQuotation.length+startindex}}</b> out of <b>{{allcounts.quotationCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getQuotation.length+startindex == allcounts.quotationCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Quotation</h4>
					</div>
					<form ng-submit="editQuotation(invoiceid)">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-2">
									<label>Quotation Date<font color="red" size="3">*</font></label>
									<div class="input-group">
										<input class="KendoDate" id="datepicker2" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>P.O.<font color="red" size="3">*</font></label>
										<input type="text" id="purchaseorder" name="purchaseorder" ng-model="purchaseorder" placeholder="Purchase Order" class="form-control" ng-change="setFlag()">
										<p ng-show="errorPurchaseOrder == 1" style="color: red; font-size: 14px;">{{msgPurchaseOrder}}</p>
									</div>									
								</div>
								<div class="col-md-2">
									<label>P.O. Date</label>
									<div class="input-group">
										<input class="KendoDate" id="podate1" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
									</div>
								</div>
								<div class="col-md-5">
									<label>Client<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="clientid" name="clientid" ng-model="clientid" ng-options="item.userId as item.userCompanyName+' - '+item.firstName+' '+item.lastName for item in getClientName" class="form-control">
											<option value="">Client</option>
										</select>
										<p ng-show="errorClient == 1" style="color: red; font-size: 14px;">{{msgClient}}</p>										
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
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="productid" name="productid" ng-model="productid" class="form-control" ng-options="item.productId as item.productName for item in getProductName" ng-change="getProductDetailById(productid)">
																<option value="">Product</option>																	
															</select>																
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="domainname" name="domainname" ng-model="domainname" class="form-control" placeholder="Domain Name">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<input type="text" id="description" name="description" ng-model="description" class="form-control" placeholder="Description">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">
															<select id="saletype" name="saletype" ng-model="saletype" class="form-control">
																<option value="">Sale Type</option>
																<option value="New">New</option>
																<option value="Renewal">Renewal</option>																	
															</select>																
														</div>
													</div>
													<div class="col-md-3">															
														<div class="input-group">
															<input class="KendoDate" id="chargefrom1" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
														</div>
													</div>														
													<div class="col-md-3">															
														<div class="input-group">
															<input class="KendoDate" id="chargeto1" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
														</div>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<input type="text" id="qty" name="qty" ng-model="qty" class="form-control" placeholder="Qty">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-2">
														<div class="form-group">
															<select id="unitid" name="unitid" ng-model="unitid" class="form-control" ng-options="item.measurementUnitId as item.measurementUnitName for item in getMeasurementUnits" disabled>
																<option value="">Unit</option>																	
															</select>																
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="saleprice" name="saleprice" ng-model="saleprice" class="form-control" placeholder="Sale Price per Unit">
														</div>
													</div>													
													<div class="col-md-1">
														<div class="form-group">
															<a href="#" ng-click="editProductRow(invoiceid)" class="btn btn-primary btn-sm" title="Add Product Row"><span class="fa fa-plus"></span></a>
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
																<th> Product Name </th>
																<th> Sale Type </th>																									
																<th class="text-right"> Qty </th>
																<th class="text-right"> Per Unit </th>
																<th class="text-right"> Total </th>														
																<th class="text-center"> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in getInvoiceProduct">																	
																<td> {{item.productName}} <br> {{item.productDescription}}<br>{{item.domainName}}<br>{{item.chargeFromDate}} <span ng-if="item.chargeFromDate != null">to</span> {{item.chargeToDate}}</td>
																<td> {{item.saleType}} </td>																															
																<td align="right"> {{item.productQty}} </td>
																<td align="right"> {{item.productSalePrice | number:2}}	</td>
																<td class="text-right"> {{item.productSalePrice * item.productQty | number:2}} </td>														
																<td class="text-center">
																	<a href="#" ng-click="deleteInvoiceProduct(item.invoiceProductId, invoiceid)" class="delete" data-toggle="modal">
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
																<td align="right">
																	<input type="text" class="form-control" ng-model="item.conditionStatement">																	
																</td>																															
																<td class="text-center">
																	<a href="#" ng-click="deleteInvoiceTerm(item.invoiceTermsId, invoiceid)" class="delete" data-toggle="modal">
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
									<div class="alert alert-success" ng-show="quotationSubmitSuccess == 1">
										<strong><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									</div>
									<div class="alert alert-info" ng-show="quotationSubmitError == 1">
										<strong><span class="fa fa-info-circle"></span> {{msgError}}</strong>
									</div>
								</div>
								<div class="col-md-6">									
									<button type="button" ng-disabled="spinEmail == 1" class="btn btn-success" ng-click="emailSmsInvoice(invoiceid)"><i class="fa fa-refresh fa-spin" ng-if="spinEmail == 1"></i> Email & SMS Quotation</button>
									<button type="button" class="btn btn-success" ng-click="printInvoice(invoiceid)">Print</button>
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Update</button>
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
						<div class="row">	
							<div class="col-md-12">
								<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Quotation </h4>								
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
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteQuotation()">
					</div>
				</div>
			</div>
		</div>
		
								
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker,#datepicker2").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true,
					value: new Date()
		         });        
		         $("#podate, #podate1").kendoDatePicker({
					format: "dd/MM/yyyy",
					dateInput: true,	
					value: "PO Date"
				});
		    });
			$(".KendoDate").bind("focus", function(){
				$(this).data("kendoDatePicker").open(); 
			});	
			$(function () {
				$(".select2").select2();
			});
			document.getElementById("manage").classList.add("active");
			document.getElementById("quotation").classList.add("active");
			
			//For Add
			//Initialize the Editor
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
	</body>
</html>