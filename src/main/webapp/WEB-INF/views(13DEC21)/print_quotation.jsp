<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head><title> Quotation-Format-HomeStay Structures </title>
		<meta charset="UTF-8">
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title> Company </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/print_quotation.js"></script>
		<style>
			body {
				background-color: #ffffff;
			}
			
			@media print {
				body {-webkit-print-color-adjust: exact;}
				#non-printable { display: none; }
        		#printable { display: block; }
        		.pagebreak { page-break-before: always; }
			}
		</style>
	</head>
	<body ng-app="MyApp" ng-controller="printQuotationCtrl" ng-cloak ng-init='getQuotationDetailForPrint(<%= request.getParameter("quotationid")%>)'>
		<table id="printable" style="border:solid 1px #bcc2cf; font-family: Times New Roman" align="center" width="90%" cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td height="20" colspan="3"></td>
				</tr>
				<tr>
					<td width="2%"></td>
					<td>&nbsp;</td>
					<td width="2%"></td>
				</tr>
				<tr>
					<td width="2%"></td>
					<td>
						<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
							<tbody>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
											<tbody>
												<tr>
													<td style="text-align: left;">
														{{getCompanyById.add1}}, <br> 
														{{getCompanyById.add2}}, <br>
														{{getCompanyById.city}}, {{getCompanyById.stateName}} {{getCompanyById.pincode}}
													</td>
													<td >
														t: {{getCompanyById.contactNo1}}, {{getCompanyById.contactNo2}} <br>
														f: {{getCompanyById.faxNo}}<br>
														e: {{getCompanyById.email}}
													</td>
													<td style="text-align: right;">
														<img style="width: 200px;" src="{{getCompanyById.companyLogo}}"/>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
											<tbody>
												<tr>
													<td style="text-align: left">
														{{getQuotationDetail.quotationNo}} <br>
														{{getQuotationDetail.quotationDate}}
													</td>
												</tr>
												<tr>
													<td style="text-align: right">
														{{userDetail.mobileNumber}} <br>
														{{userDetail.email}}
													</td>
												</tr>
												<tr>
													<td height="10"></td>
												</tr>
												<tr>
													<td>
														<strong> {{userDetail.firstName}} {{userDetail.middleName}} {{userDetail.lastName}} </strong> <br>
														{{userDetail.address1}}, <br>
														{{userDetail.address2}}, <br>
														{{userDetail.address3}}, <br>
														{{userDetail.cityName}}, {{userDetail.stateName}} {{userDetail.pincode}}
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td height="15"></td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
											<tbody>
												<tr ng-bind-html="getQuotationDetail.coverLetter | to_trusted"></tr>												
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;<div class="pagebreak"></div>
									</td>									
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="1" style="width:100%" ng-repeat="item in getQuotationProduct">
											<tbody>
												<tr>
													<strong> {{item.productName}} </strong>
												</tr>
												<tr>
													<td height="10"></td>
												</tr>
												<tr>
													<td align="center"><strong> {{$index+1}} TECHNICAL SPECIFICATION & PRICE SUMMARY </strong></td>
												</tr>
												<tr>
													<td height="10"></td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="border:solid 1px #bcc2cf; width:100%">
															<tbody>
																<tr>
																	<td height="30" style="border-bottom:solid 1px #bcc2cf;" valign="middle" colspan="3">
																		<strong> &nbsp; Specifications for {{item.productName}} </strong>
																	</td>
																</tr>
																<tr ng-repeat="s in item.specifications">
																	<td height="30" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;" width="40%">
																		&nbsp; {{s.specification}}
																	</td>
																	<td style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;" width="40%">
																		&nbsp; {{s.specValue}}
																	</td>
																	<td style="border-bottom:solid 1px #bcc2cf;">
																		&nbsp; <span ng-if="s.unitName !='undefined'">{{s.unitName}}</span>
																	</td>
																</tr>															
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td height="15"></td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="border-right: solid 1px #bcc2cf; border-left:solid 1px #bcc2cf; border-top:solid 1px #bcc2cf; width:100%">
															<tbody>
																<tr>
																	<td align="center" height="30" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;" width="40%">
																		&nbsp; <strong> Particular </strong>
																	</td>
																	<td align="center" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;" width="20%"> 
																		&nbsp; <strong> Description </strong>
																	</td>
																	<td align="center" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;" width="10%"> 
																		&nbsp; <strong> Qty. </strong>
																	</td>
																	<td align="center" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;" width="10%"> 
																		&nbsp; <strong> Total </strong>
																	</td>
																	<td align="center" style="border-bottom:solid 1px #bcc2cf;" width="20%"> 
																		&nbsp; <strong> </strong>
																	</td>
																</tr>
																<tr ng-repeat="sop in item.scopeOfSupplies">
																	<td height="30" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;"> 
																		&nbsp; {{sop.particular}} 
																	</td>
																	<td align="right" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;"> 
																		{{sop.value}} &nbsp;
																	</td>
																	<td align="right" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;">
																		&nbsp; {{sop.qty}} No. &nbsp;
																	</td>
																	<td align="right" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;">
																		&nbsp; {{sop.qty * sop.value}}  &nbsp;
																	</td>
																	<td align="center" style="border-bottom:solid 1px #bcc2cf;">
																		&nbsp; {{sop.unitName}}  &nbsp;
																	</td>
																</tr>
																<tr>
																	<td style="border-bottom:solid 1px #bcc2cf;" colspan="5" height="20"></td>
																</tr>
																<tr>
																	<td height="30" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;">
																		&nbsp;   <strong> PRICE </strong>  each ex-our works, Baroda
																	</td>
																	<td style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;">
																		&nbsp;
																	</td>
																	<td align="right" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;"> 
																		{{item.productQty}} No.  &nbsp;
																	</td>
																	<td align="right" style="border-right: solid 1px #bcc2cf; border-bottom:solid 1px #bcc2cf;">
																		&nbsp; <strong> Rs. {{item.productQty * item.salePrice | number:2}} </strong>
																	</td>
																	<td style="border-bottom:solid 1px #bcc2cf;"> 
																		&nbsp;
																	</td>
																</tr>																
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"></td>
												</tr>
											</tbody>
										</table>										
										<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
											<tbody>	
												<tr>
													<td height="5"></td>
												</tr>											
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
															<tbody>
																<tr ng-repeat="item in getTerms" ng-show="item.termMasterId == 1">
																	<td align="center" style="text-transform: uppercase;"> <strong> {{getQuotationProduct.length+1}}. {{item.termTitle}} </strong> </td>
																</tr>
																<tr>
																	<td height="5"></td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
																			<tbody>
																				<tr ng-repeat="item in getStandardScopeOfSupply" ng-show="item.termMasterId == 1">
																					<td width="2%"> {{$index+1}}.</td>
																					<td width="10%" ng-show="item.label!=null"> {{item.label}}</td>
																					<td align="left" width="2%" ng-show="item.label!=null"> : </td>
																					<td> {{item.statement}} </td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		&nbsp;<div class="pagebreak"></div>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td height="35"></td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
															<tbody>
																<tr ng-repeat="item in getTerms" ng-show="item.termMasterId == 2">
																	<td align="center" style="text-transform: uppercase;"> <strong> {{getQuotationProduct.length+2}}. {{item.termTitle}} </strong> </td>
																</tr>
																<tr>
																	<td height="5"></td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
																			<tbody>
																				<tr ng-repeat="item in getExclusion" ng-show="item.termMasterId == 2">
																					<td width="2%"> {{$index+1}}.</td>
																					<td width="10%" ng-show="item.label!=null"> {{item.label}}</td>
																					<td align="left" width="2%" ng-show="item.label!=null"> : </td>
																					<td> {{item.statement}} </td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td height="35"></td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
															<tbody>
																<tr ng-repeat="item in getTerms" ng-show="item.termMasterId == 3">
																	<td align="center" style="text-transform: uppercase;"> <strong> {{getQuotationProduct.length+3}}. {{item.termTitle}} </strong> </td>
																</tr>
																<tr>
																	<td height="5"></td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
																			<tbody>
																				<tr ng-repeat="item in getRequirmentToBeMade" ng-show="item.termMasterId == 3">
																					<td width="2%"> {{$index+1}}.</td>
																					<td width="10%" ng-show="item.label!=null"> {{item.label}}</td>
																					<td align="left" width="2%" ng-show="item.label!=null"> : </td>
																					<td> {{item.statement}} </td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td height="35"></td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
															<tbody>
																<tr ng-repeat="item in getTerms" ng-show="item.termMasterId == 4">
																	<td align="center" style="text-transform: uppercase;"> <strong> {{getQuotationProduct.length+4}}. {{item.termTitle}} </strong> </td>
																</tr>
																<tr>
																	<td height="5"></td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="1" style="width:100%">
																			<tbody>
																				<tr ng-repeat="item in getTermAndCondition" ng-show="item.termMasterId == 4">
																					<td width="2%"> {{$index+1}}.</td>
																					<td width="10%" ng-show="item.label!=null"> {{item.label}}</td>
																					<td align="left" width="2%" ng-show="item.label!=null"> : </td>
																					<td> {{item.statement}} </td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td height="35"></td>
												</tr>												
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td align="right"><img src="<%=request.getContextPath() %>/resources/admin/images/TUV.png"/> </td>
								</tr>
							</tbody>
						</table>
					</td>
					<td width="2%"></td>
				</tr>
				<tr>
					<td height="20" colspan="3"></td>
				</tr>
			</tbody>
		</table>
		<table id="non-printable" align="center" width="1060" cellspacing="0" cellpadding="0" border="0" style="margin-top: 20px;">
			<tbody>
				<tr>
					<td width="49%" align="right"><input type="button" value="Print" onClick="window.print();"></td>
					<td width="2%"></td>
					<td width="49%" align="left"><input type="button" value="Cancel" onClick="window.history.back();"></td>
				</tr>
			</tbody>
		</table>
	</body>
</html>