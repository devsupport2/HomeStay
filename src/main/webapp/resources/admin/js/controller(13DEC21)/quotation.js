app.controller('quotationCtrl', [ '$scope', '$filter', '$http', '$window', '$location', '$timeout',
	function($scope, $filter, $http, $window, $location, $timeout) {
	
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	    
	    $scope.numberOfPages=function() {
			return Math.ceil($scope.getInvoice.length/$scope.pageSize);
		}
		
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getQuotationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getQuotation = data;
		}).error(function(data, status, headers, config) {
			$scope.getQuotation = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllQuotation';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotation = data;
				}).error(function(data, status, headers, config) {
					$scope.getQuotation = "Response Fail";
				});
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;
					
				var link = baseUrl+'getQuotationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotation = data;
				}).error(function(data, status, headers, config) {
					$scope.getQuotation = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			var link = baseUrl+'getQuotationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getQuotation = data;
			}).error(function(data, status, headers, config) {
				$scope.getQuotation = "Response Fail";
			});
		}
		
		$scope.searchQuotation = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getQuotationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotation = data;
				}).error(function(data, status, headers, config) {
					$scope.getQuotation = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchQuotation?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotation = data;
				}).error(function(data, status, headers, config) {
					$scope.getQuotation = "Response Fail";
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;
			angular.forEach($scope.getQuotation,function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteQuotation = function() {
			angular.forEach($scope.getQuotation, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteQuotation?quotationid='+item.quotationId;				
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteinvoice = data;
						var link = baseUrl+'getQuotationByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getQuotation = data;
							$('#deleteModal').modal('hide');
						}).error(function(data, status, headers, config) {
							$scope.getQuotation = "Response Fail";
						});
					}).error(function(data, status, headers, config) {
						$scope.deleteinvoice = "Response Fail";
					});
				}
			});		
		}
	
		
		var link = baseUrl+'getClientAndProspectName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.clientAndProspectName = data;
		}).error(function(data, status, headers, config) {
			$scope.clientAndProspectName = "Response Fail";
		});
		
		var link = baseUrl+'getAllTerm';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getTermTitle = data;
		}).error(function(data, status, headers, config) {
			$scope.getTermTitle = "Response Fail";
		});
		
		var link = baseUrl+'getProductName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProductName = data;			
		}).error(function(data, status, headers, config) {
			$scope.getProductName = "Response Fail";			
		});	
		
		var link = baseUrl+'getMeasurementUnits';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMeasurementUnits = data;
		}).error(function(data, status, headers, config) {
			$scope.getMeasurementUnits = "Response Fail";
		});	
		
		var link = baseUrl+'getCoverLetterDetailById?id='+1;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.coverLetterDetailById = data;
			$scope.coverletterid = $scope.coverLetterDetailById.coverLetterId;
			$scope.coverlettertitle = $scope.coverLetterDetailById.coverLetterTitle;
			CKEDITOR.instances.letterdescription.setData($scope.coverLetterDetailById.coverLetterDescription);		
		}).error(function(data, status, headers, config) {
			$scope.coverLetterDetailById = "Response Fail";
		});
		
		var link = baseUrl+'getQuotationTermStatement';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.termStatement = data;
		}).error(function(data, status, headers, config) {
			$scope.termStatement = "Response Fail";
		});
		
		$scope.getProductDetailById = function(productid) {
			
			var link = baseUrl+'getProductDetailById?productid='+$scope.productid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.productDetail = data;				
				$scope.unitid = $scope.productDetail.unitId;
				
				var link = baseUrl+'getProductPriceByProductId?productid='+productid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getproductpricelist = data;
					for (i in $scope.getproductpricelist) {
						if ($scope.getproductpricelist[i].priceTypeId == 2) {
							$scope.saleprice = $scope.getproductpricelist[i].price;
						}
					}				 
				}).error(function(data, status, headers, config) {
					$scope.getproductpricelist = "Response Fail";
				});
				
				var link = baseUrl+'getProductScopeOfSupplyByProductId?productid='+productid;
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.getproductscopeofsupply = data;
    			}).error(function(data, status, headers, config) {
    				$scope.getproductscopeofsupply = "Response Fail";
    			});
            	
            	var link = baseUrl+'getProductSpecificationsByProductId?productid='+productid;
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.getproductspecificationlist = data;
    			}).error(function(data, status, headers, config) {
    				$scope.getproductspecificationlist = "Response Fail";
    			});
								 
			}).error(function(data, status, headers, config) {
				$scope.productDetail = "Response Fail";
			});			
		}	
		
		$scope.productlist = [];
		$scope.getproductscopeofsupplyedit = [];
		$scope.quotationproductscopeofsupplylist = [];
		$scope.quotationproductspecificationlist = [];
		
		
		$scope.addProductRow = function() {				
			if($scope.productid==undefined || $scope.productid=="") {
				document.getElementById("productid").focus();
				$scope.errorProduct = 1;
				$scope.msgProduct = "Please select product!";
				$timeout(function(){
					$scope.productinfo = 0;
				}, 2000);
			} else if($scope.qty==undefined || $scope.qty=="") {
				document.getElementById("qty").focus();
				$scope.errorQty = 1;
				$scope.msgQty = "Please enter quantity!";
				$timeout(function(){
					$scope.productinfo = 0;
				}, 2000);
			} else if($scope.saleprice==undefined || $scope.saleprice=="") {
				document.getElementById("saleprice").focus();
				$scope.errorSalePrice = 1;
				$scope.msgSalePrice = "Please enter product saleprice!";
				$timeout(function(){
					$scope.productinfo = 0;
				}, 2000);
			} else {			
				
				var link = baseUrl+'getProductDetailById?productid='+$scope.productid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.productDetail = data;
					$scope.producttitle = $scope.productDetail.productName;
					$scope.partnumber = $scope.productDetail.partNumber;
					$scope.unitid = $scope.productDetail.unitId;
					
					$scope.productlist.push({'productId':$scope.productid, 'productTitle':$scope.producttitle, 'partNumber':$scope.partnumber, 'qty':$scope.qty, 'salePrice':$scope.saleprice});
					
					angular.forEach($scope.getproductscopeofsupply,function(item) {
						if(item.productId == undefined){
							item.productId = $scope.productid;
						}
						if(item.scopeQty == undefined){
							item.scopeQty = 0;
						}
						$scope.quotationproductscopeofsupplylist.push({'productId':item.productId, 'particular':item.particular, 'value':item.value, 'scopeQty':item.scopeQty, 'unitId':item.unitId, 'unitName':item.unitName});
					});
					
					angular.forEach($scope.getproductscopeofsupplyedit,function(item) {
						if(item.productId == undefined){
							item.productId = $scope.productid;
						}						
						$scope.quotationproductscopeofsupplylist.push({'productId':item.productId, 'particular':item.particular, 'value':item.value, 'scopeQty':item.scopeQty, 'unitId':item.unitId, 'unitName':item.unitName});
					});
					
					angular.forEach($scope.getproductspecificationlist,function(item) {
						if(item.productId == undefined){
							item.productId = $scope.productid;
						}
						$scope.quotationproductspecificationlist.push({'productId':item.productId, 'specification':item.specification, 'specValue':item.specValue, 'unitId':item.unitId, 'unitName':item.unitName});
					});
					
					$scope.getproductscopeofsupply = "";
					$scope.getproductscopeofsupplyedit = "";				
					$scope.getproductspecificationlist = "";
					
					$scope.productid = "";
					$scope.qty = "";
					$scope.saleprice = "";
					$scope.unitid = "";
										
									 
				}).error(function(data, status, headers, config) {
					$scope.productDetail = "Response Fail";
				});			
			}
		}

		$scope.removeProductRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.productlist.length; i++){
				if($scope.productlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing product..Please try again!");
				return;
			}
			$scope.productlist.splice(index, 1);
		};
		$scope.getproductscopeofsupplyedit = [];
		$scope.addScopeRowEdit = function() {
			
			if(!$scope.scopeunitid){
				$scope.scopeunitid = 0;
			}
			if($scope.particular==undefined || $scope.particular=="") {				
				$scope.errorParticular = 1;
				$scope.msgParticular = "Please enter particular!";
				document.getElementById("particular").focus();
			} else if($scope.scopeqty==undefined || $scope.scopeqty=="") {				
				$scope.errorScopeQty = 1;
				$scope.msgScopeQty = "Please enter qty!";
				document.getElementById("scopeqty").focus();
			} else if($scope.value==undefined || $scope.value=="") {				
				$scope.errorValue = 1;
				$scope.msgValue = "Please enter value!";
				document.getElementById("value").focus();
			}else {				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.scopeunitid) {
						$scope.unitname = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					} else {
						$scope.unitname = "";
					}
				}				
				$scope.getproductscopeofsupplyedit.push({'particular':$scope.particular, 'value':$scope.value, 'scopeQty':$scope.scopeqty, 'unitId':$scope.scopeunitid, 'unitName':$scope.unitname});
				$scope.particular = "";
				$scope.value = "";
				$scope.scopeunitid = "";
			}		
		}

		$scope.removeScopeRowEdit = function(item) {
			var index = -1;
			for(var i=0; i<$scope.getproductscopeofsupply.length; i++){
				if($scope.getproductscopeofsupply[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing record..Please try again!");
				return;
			}
			$scope.getproductscopeofsupply.splice(index, 1);
		};
		
		$scope.removeScopeRowEdit1 = function(item) {
			var index = -1;
			for(var i=0; i<$scope.getproductscopeofsupplyedit.length; i++){
				if($scope.getproductscopeofsupplyedit[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing record..Please try again!");
				return;
			}
			$scope.getproductscopeofsupplyedit.splice(index, 1);
		};
		$scope.getproductspecificationlist=[];
		$scope.addSpecificationRowEdit = function() {
			
			if(!$scope.specunitid){
				$scope.specunitid = 0;
			}
			if($scope.specification==undefined || $scope.specification=="") {				
				$scope.errorSpecification = 1;
				$scope.msgSpecification = "Please enter specification!";
				document.getElementById("specification").focus();
			} else if($scope.specvalue==undefined || $scope.specvalue=="") {				
				$scope.errorSpecValue = 1;
				$scope.msgSpecValue = "Please enter specification value!";
				document.getElementById("specvalue").focus();
			} else {				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.specunitid) {
						$scope.unitname = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					} else {
						$scope.unitname = "";
					}
				}				
				$scope.getproductspecificationlist.push({ 'specification':$scope.specification, 'specValue':$scope.specvalue, 'unitId':$scope.specunitid, 'unitName':$scope.unitname });
				$scope.specification = "";
				$scope.specvalue = "";
				$scope.specunitid = "";
			}		
		}

		$scope.removeSpecificationRowEdit = function(item) {
			var index = -1;
			for(var i=0; i<$scope.getproductspecificationlist.length; i++){
				if($scope.getproductspecificationlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing specification..Please try again!");
				return;
			}
			$scope.getproductspecificationlist.splice(index, 1);
		};
		
		$scope.removeConditionRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.termsandConditions.length; i++){
				if($scope.termsandConditions[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing product..Please try again!");
				return;
			}
			$scope.termsandConditions.splice(index, 1);
		};
		
		$scope.addTermRow = function() {
			
			if(!$scope.label){
				$scope.label = "";
			}
			if(!$scope.termid) {				
				$scope.errorTermTitle = 1;
				$scope.msgTermTitle = "Please select term title!";
				document.getElementById("termid").focus();
			} else if(!$scope.sequence) {				
				$scope.errorSequence = 1;
				$scope.msgSequence = "Please enter sequence!";
				document.getElementById("sequence").focus();
			} else if(!$scope.statement) {				
				$scope.errorStatement = 1;
				$scope.msgStatement = "Please enter statement!";
				document.getElementById("statement").focus();
			} else {				
				for (i in $scope.getTermTitle) {
					if ($scope.getTermTitle[i].termMasterId == $scope.termid) {
						$scope.termtitle = $scope.getTermTitle[i].termTitle;
						break;
					}
				}				
				$scope.termStatement.push({'termTitle':$scope.termtitle, 'termMasterId':$scope.termid, 'sequence':$scope.sequence, 'label':$scope.label, 'statement':$scope.statement});
				$scope.termid = "";
				$scope.label = "";
				$scope.statement = "";
				$scope.sequence = "";
			}		
		}

		$scope.removeTermRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.termStatement.length; i++){
				if($scope.termStatement[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing record..Please try again!");
				return;
			}
			$scope.termStatement.splice(index, 1);
		};
		
		$scope.setFlag = function() {		
			$scope.errorClient = 0;		
			$scope.errorProductName = 0;
			$scope.errorUnitId = 0;			
			$scope.errorProduct = 0;
			$scope.errorQty = 0;
			$scope.errorSalePrice = 0;
			$scope.errorTermTitle = 0;
			$scope.errorLabel = 0;
			$scope.errorStatement = 0;
			$scope.errorSequence = 0;
		}
		
		$scope.generateQuatation = function() {
			var enquiryid = 0;
			$scope.quotationdate = document.getElementById("datepicker").value;
			$scope.podate = document.getElementById("podate").value;
			$scope.coverletter = CKEDITOR.instances.letterdescription.getData();
			if($scope.podate=="day/month/year" || $scope.podate=="PO Date") {
				$scope.podate = "";
			}
			if(!$scope.purchaseorderadd) {
				$scope.purchaseorderadd = "";
			}
			if($scope.clientidadd==undefined || $scope.clientidadd=="") {
				$scope.errorClient = 1;
				$scope.msgClient = "Please select client";
				document.getElementById("clientidadd").focus();
				return;
			} else {
				var coverletter = encodeURIComponent($scope.coverletter);
				
				var formData=new FormData();
				formData.append("coverletter",coverletter);
				
				var a = 0, b = 0, c = 0, d = 0;
				$scope.spin = 1;
				$scope.termstatementlist = [];
				angular.forEach($scope.termStatement,function(item) {					
					item.statement = tools_replaceAll(item.statement, "&" , "$" );
					item.statement = tools_replaceAll(item.statement, "#" , "~" );
					item.statement = tools_replaceAll(item.statement, "%" , "!" );
					if(!item.label){
						item.label = "";
					}
						
					$scope.termstatementlist.push({'termMasterId':item.termMasterId, 'termTitle':item.termTitle, 'sequence':item.sequence, 'label':item.label, 'statement':item.statement});									
				});
				
				var link = baseUrl+'generateQuotation?enquiryid='+enquiryid+'&quotationdate='+$scope.quotationdate+'&purchaseorder='+$scope.purchaseorderadd+'&podate='+$scope.podate+'&clientid='+$scope.clientidadd;				
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) {return data;}}).success(function(data, status, headers, config) {
					$scope.createquotation = data;
					
					if($scope.createquotation == "Success"){
						angular.forEach($scope.productlist,function(item) {												
							var link = baseUrl+'addQuotationProduct?productid='+item.productId+'&productname='+item.productTitle+'&partnumber='+item.partNumber+'&productqty='+item.qty+'&saleprice='+item.salePrice;							
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addquotationproduct = data;
								a = a + 1;
								if($scope.createquotation == "Success" && $scope.productlist.length == a && $scope.quotationproductscopeofsupplylist.length == b && $scope.quotationproductspecificationlist.length == c && $scope.termstatementlist.length == d) {
									$scope.spin = 0;
									$scope.quotationSubmitError = 1;
									$scope.quotationSubmitSuccess = 1;
									$scope.successMsg = "Quotation qenerated successfully";
									$timeout(function(){
										$scope.quotationSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_quotation';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addquotationproduct = "Response Fail";
							});
						});
						
						angular.forEach($scope.quotationproductscopeofsupplylist,function(item) {												
							var link = baseUrl+'addQuotationProductScopeOfSupply?productid='+item.productId+'&particular='+item.particular+'&value='+item.value+'&qty='+item.scopeQty+'&unitname='+item.unitName;						
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addquotationproductscopeofsupply = data;
								b = b + 1;								
								if($scope.createquotation == "Success" && $scope.productlist.length == a && $scope.quotationproductscopeofsupplylist.length == b && $scope.quotationproductspecificationlist.length == c && $scope.termstatementlist.length == d) {
									$scope.spin = 0;
									$scope.quotationSubmitError = 1;
									$scope.quotationSubmitSuccess = 1;
									$scope.successMsg = "Quotation qenerated successfully";
									$timeout(function(){
										$scope.quotationSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_quotation';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addquotationproductscopeofsupply = "Response Fail";
							});
						});
						
						angular.forEach($scope.quotationproductspecificationlist,function(item) {												
							var link = baseUrl+'addQuotationProductSpecification?productid='+item.productId+'&specification='+item.specification+'&specvalue='+item.specValue+'&unitname='+item.unitName;						
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addquotationproductspecification = data;								
								c = c + 1;								
								if($scope.createquotation == "Success" && $scope.productlist.length == a && $scope.quotationproductscopeofsupplylist.length == b  && $scope.quotationproductspecificationlist.length == c && $scope.termstatementlist.length == d) {
									$scope.spin = 0;
									$scope.quotationSubmitError = 1;
									$scope.quotationSubmitSuccess = 1;
									$scope.successMsg = "Quotation qenerated successfully";
									$timeout(function(){
										$scope.quotationSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_quotation';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addquotationproductspecification = "Response Fail";
							});
						});
						
						angular.forEach($scope.termstatementlist,function(item) {												
							var link = baseUrl+'addQuotationTermStatement?termmasterid='+item.termMasterId+'&termtitle='+item.termTitle+'&sequence='+item.sequence+'&label='+item.label+'&statement='+item.statement;						
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addquotationtermstatement = data;								
								d = d + 1;								
								if($scope.createquotation == "Success" && $scope.productlist.length == a && $scope.quotationproductscopeofsupplylist.length == b && $scope.quotationproductspecificationlist.length == c && $scope.termstatementlist.length == d) {
									$scope.spin = 0;
									$scope.quotationSubmitError = 0;
									$scope.quotationSubmitSuccess = 1;
									$scope.successMsg = "Quotation qenerated successfully";
									$timeout(function(){
										$scope.quotationSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_quotation';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addquotationtermstatement = "Response Fail";
							});
						});
					} else {						
						$scope.quotationSubmitSuccess = 0;
						$scope.quotationSubmitError = 1;
						$scope.errorMsg = "Something went wrong!";
						$timeout(function(){
							$scope.quotationSubmitError = 0;							
						}, 3000);
					}			
					
				}).error(function(data, status, headers, config) {					
					$scope.quotationSubmitSuccess = 0;
					$scope.quotationSubmitError = 1;
					$scope.errorMsg = "Something went wrong!";
					$timeout(function(){
						$scope.quotationSubmitError = 0;							
					}, 3000);
				});
			}
		}
		
		$scope.printQuotation = function(quotationid) {
			$window.location.href = adminurl+'print_quotation?quotationid='+quotationid;
		}
		
}]);