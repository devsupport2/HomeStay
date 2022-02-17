<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Quotation Templates </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/quotation_template.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/admin/ckfinder/ckfinder.js"></script>					
	</head>	
	<body ng-app="MyApp" ng-controller="quotationTemplateCtrl" ng-cloak class="skin-blue sidebar-mini" ng-init="getQuotationTempleteDetail()">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Quotation Templates						
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Quotation Templates</li>
					</ol>
				</section>
				<section class="content">					
					<div class="box box-tgsc">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title"> Quotation Cover Letter Template</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<form ng-submit="editQuotationCoverLetter(1)">
						<div class="box-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Cover Letter Template<font color="red" size="3">*</font></label>
										<textarea id="letterdescription" name="letterdescription" cols="200" class="form-control"></textarea>
										<span ng-show="errorLetterDescription == 1" style="color: red;">{{msgLetterDescription}}</span>
									</div>
								</div>
							</div>					
						</div>
						<div class="box-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="letterSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									<strong ng-show="letterSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save changes</button>	
								</div>
							</div>			
						</div>
						</form>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title"> Quotation Terms Master</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th>#</th>
											<th>Term</th>		
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getTerms == ''">
											<td colspan="2"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getTerms" style="cursor:pointer;cursor:hand">
											<td ng-click="getTermDetail(item.termMasterId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{$index+1}}</td>
											<td ng-click="getTermDetail(item.termMasterId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.termTitle}}</td>											
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.termMasterId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getTerms != ''">
										<tr>
											<td colspan="2">									
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
					</div>
				</section>
			</div>
		</div>
		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit {{}}</h4>
					</div>
					<div class="modal-body">
						<div class="row form-group">
							<div class="col-md-4">
								<label>Term</label>										
								<input type="text" id="termtitle" name="termtitle" ng-model="termtitle" placeholder="Term" class="form-control">
							</div>
							<div class="col-md-1">
								<label>&nbsp;</label><br>	
								<button type="submit" ng-click="editTerm(termid)" ng-disabled="spinTerm == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinTerm == 1"></i> Update </button>
							</div>
							<div class="col-md-7">
								<label>&nbsp;</label><br>	
								<strong ng-show="termSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
								<strong ng-show="termSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-md-2">										
								<label>Sequence<font color="red" size="3">*</font></label>										
								<input type="number" id="sequence" name="sequence" ng-model="sequence" class="form-control" ng-change="setFlag()">
								<span ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</span>									
							</div>									
							<div class="col-md-2">										
								<label>Label</label>										
								<input type="text" id="label" name="label" ng-model="label" placeholder="Label" class="form-control">									
							</div>
							<div class="col-md-7">										
								<label>Statement<font color="red" size="3">*</font></label>										
								<input type="text" id="statement" name="statement" ng-model="statement" placeholder="Statement" class="form-control" ng-change="setFlag()">
								<span ng-show="errorStatement == 1" style="color: red;">{{msgStatement}}</span>									
							</div>
							<div class="col-md-1">
								<div class="form-group">
									<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<a href="#" ng-click="addTermItemRow(termid)" class="btn btn-primary btn-sm" title="Add"><span class="fa fa-plus"></span></a>
								</div>
							</div>																								
						</div>			
						<div class="table-responsive table-bordered">
							<table class="table">
								<thead>
									<tr>
										<th> Sequence </th>
										<th> Label </th>
										<th> Statement </th>														
										<th> Action </th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in termStatementById">
										<td> {{item.sequence}} </td>
										<td> {{item.label}} </td>
										<td> {{item.statement}} </td>														
										<td>
											<a href="#" ng-click="deleteTermItemRow(item.termStatementId, termid)" class="delete" data-toggle="modal">
												<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
											</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">					
							<div class="col-md-12 text-right">								
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Term </h4>						
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure you want to delete these Records?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">						
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteTerm()">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					</div>
				</div>
			</div>
		</div>
		
		<!-- Script For CKEditor Start -->
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("quotation_template").classList.add("active");
			//For Add
			//Initialize the Editor
			initEditor();		
		
			//For Add
			function initEditor()
			{
				CKEDITOR.replace( 'letterdescription',
						{
							pluginsLoaded: function( evt ) 
							{
			 					var doc = CKEDITOR.document, ed = evt.editor;
			 					if ( !ed.getCommand( 'bold' ) )
			  						doc.getById( 'exec-bold' ).hide();
			 					if ( !ed.getCommand( 'link' ) )
			  						doc.getById( 'exec-link' ).hide();
			 				}
						 });
			}			
			if ( typeof CKEDITOR == 'undefined' )
			{
				document.write(
					'<strong><span style="color: #ff0000">Error</span>: CKEditor not found</strong>.' +
					'This sample assumes that CKEditor (not included with CKFinder) is installed in' +
					'the "/ckeditor/" path. If you have it installed in a different place, just edit' +
					'this file, changing the wrong paths in the &lt;head&gt; (line 5) and the "BasePath"' +
					'value (line 32).' ) ;
			}
			else
			{
				var editor = CKEDITOR.replace( 'editor1' );
				CKFinder.setupCKEditor(editor,'../') ;
			}
			
		</script>
		
		<!-- Script For CKEditor End -->		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
	</body>
</html>