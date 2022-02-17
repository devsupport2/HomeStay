app.controller('productCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
		function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];	
    
		$scope.numberOfPages=function()	{
			return Math.ceil($scope.getProducts.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});	
		
		var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProducts = data;
		}).error(function(data, status, headers, config) {
			$scope.getProducts = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
				
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;
					
				var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProducts = data;
				}).error(function(data, status, headers, config) {
					$scope.getProducts = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getProducts = data;
			}).error(function(data, status, headers, config) {
				$scope.getProducts = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			if($scope.pageSize == "All") {
				var link = baseUrl+'getProducts';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProducts = data;
				}).error(function(data, status, headers, config) {
					$scope.getProducts = "Response Fail";
				});
			} else {
				var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProducts = data;
				}).error(function(data, status, headers, config) {
					$scope.getProducts = "Response Fail";
				});
			}
		}
		$scope.searchProduct = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProducts = data;
				}).error(function(data, status, headers, config) {
					$scope.getProducts = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchProducts?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProducts = data;
				}).error(function(data, status, headers, config) {
					$scope.getProducts = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getCategories';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCategories = data;
		}).error(function(data, status, headers, config) {
			$scope.getCategories = "Response Fail";
		});
		
		$scope.getSubcategoryByCategoryId = function(categoryname) {
			if(categoryname == "" || categoryname == undefined) {
				$scope.subcategorynameadd = "";
				$scope.subcategoryname = "";
				$scope.getSubcategories = "";
			} else {
				var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+categoryname;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
				});
			}
		}
		var link = baseUrl+'getBrands';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getBrands = data;
		}).error(function(data, status, headers, config) {
			$scope.getBrands = "Response Fail";
		});
		
		$scope.getSubbrandByBrandId = function(brandid) {			
			var link = baseUrl+'getSubbrandByBrandId?brandid='+brandid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getSubbrand = data;
			}).error(function(data, status, headers, config) {
				$scope.getSubbrand = "Response Fail";
			});		
		}
		
		var link = baseUrl+'getProductSaleType';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProductSaleType = data;
		}).error(function(data, status, headers, config) {
			$scope.getProductSaleType = "Response Fail";
		});
		
		var link = baseUrl+'getMeasurementUnits';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMeasurementUnits = data;
		}).error(function(data, status, headers, config) {
			$scope.getMeasurementUnits = "Response Fail";
		});
		
		var link = baseUrl+'getCurrencies';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCurrencies = data;
		}).error(function(data, status, headers, config) {
			$scope.getCurrencies = "Response Fail";
		});
		
		var link = baseUrl+'getTaxes';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getTaxes = data;
		}).error(function(data, status, headers, config) {
			$scope.getTaxes = "Response Fail";
		});	
		
		var link = baseUrl+'getSuppliers';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getSuppliers = data;
		}).error(function(data, status, headers, config) {
			$scope.getSuppliers = "Response Fail";
		});
		
		var link = baseUrl+'getUserTypes';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserTypes = data;
		}).error(function(data, status, headers, config) {
			$scope.getUserTypes = "Response Fail";
		});		
		
		var link = baseUrl+'getCountries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCountries = data;
		}).error(function(data, status, headers, config) {
			$scope.getCountries = "Response Fail";
		});
		
		var link = baseUrl+'getStateByCountryId?countryid='+1;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getStates = data;
		}).error(function(data, status, headers, config) {
			$scope.getStates = "Response Fail";
		});
		
		var link = baseUrl+'getPriceType';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getPriceType = data;
		}).error(function(data, status, headers, config) {
			$scope.getPriceType = "Response Fail";
		});
		
		$scope.getUserRole = function() {
			if($scope.usertypenameadd == undefined){
				var link = baseUrl+'getUserRoleByUserTypeId?usertypeid='+$scope.usertypename;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUserRoles = data;
				}).error(function(data, status, headers, config) {
					$scope.getUserRoles = "Response Fail";
				});
			} else {
				var link = baseUrl+'getUserRoleByUserTypeId?usertypeid='+$scope.usertypenameadd;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRoles = data;
				}).error(function(data, status, headers, config) {
					$scope.getRoles = "Response Fail";
				});
			}								
		}
		
		$scope.getStateByCountryId = function(countryname) {
			if(countryname == "" || countryname == undefined) {
				$scope.statenameadd = "";
				$scope.statename = "";
				$scope.getStates = "";
			} else {
				var link = baseUrl+'getStateByCountryId?countryid='+countryname;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			}
		}	
		
		$scope.checkAllState = function() {
			if ($scope.selectedAll) {
				$scope.selectedAll = false;
			} else {
				$scope.selectedAll = true;
			}
			angular.forEach($scope.getStates, function (item) {
				item.statenameadd = $scope.selectedAll;
			});
		}
		
		$scope.setFlag = function(){
			$scope.errorCategoryName = 0;
			$scope.errorRecurring = 0;
			$scope.errorProductName = 0;
			$scope.errorUnit = 0;
			$scope.errorSpecification = 0;
			$scope.errorSupplier = 0;
			$scope.errorPriceType = 0;
			$scope.errorPrice = 0;
			$scope.errorCurrency = 0;
			$scope.errorBrandName = 0;
			$scope.errorBrandId = 0;
			$scope.errorSubbrandName = 0;
			$scope.errorSubcategoryName = 0;
			$scope.errorUnitName = 0;
			$scope.errorCompanyName = 0;
			$scope.errorShowCode = 0;
			$scope.errorSpecValue = 0;
			$scope.errorParticular = 0;
			$scope.errorValue = 0;
			$scope.errorSequence = 0;
			$scope.errorScopeSequence = 1;
		}
		
		$scope.taxlist = [];
		$scope.addTaxRow = function() {
			if($scope.taxnameadd==undefined || $scope.taxnameadd=="") {
				document.getElementById("taxnameadd").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please select tax";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			} else if($scope.rateadd==undefined || $scope.rateadd=="") {
				document.getElementById("rateadd").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please enter rate";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			} else {
				for (i in $scope.getTaxes) {
					if ($scope.getTaxes[i].taxId == $scope.taxnameadd) {
						$scope.taxname1 = $scope.getTaxes[i].taxName;
						break;
					}
				}			
				$scope.taxlist.push({'taxId':$scope.taxnameadd, 'taxName':$scope.taxname1, 'rate':$scope.rateadd});				
			}
		}

		$scope.removeTaxRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.taxlist.length; i++){
				if($scope.taxlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing tax..Please try again!");
				return;
			}
			$scope.taxlist.splice(index, 1);
		};
		
		$scope.scopeofsupplylist = [];
		$scope.addScopeRow = function() {			
			if(!$scope.scopeunitidadd){
				$scope.scopeunitidadd = 0;
			}
			if($scope.scopesequenceadd==undefined || $scope.scopesequenceadd=="") {				
				$scope.errorScopeSequence = 1;
				$scope.msgScopeSequence = "Please enter sequence!";
				document.getElementById("scopesequenceadd").focus();
			} else if($scope.particularadd==undefined || $scope.particularadd=="") {				
				$scope.errorParticular = 1;
				$scope.msgParticular = "Please enter particular!";
				document.getElementById("particularadd").focus();
			} else if($scope.valueadd==undefined || $scope.valueadd=="") {				
				$scope.errorValue = 1;
				$scope.msgValue = "Please enter a value!";
				document.getElementById("valueadd").focus();
			} else {				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.scopeunitidadd) {
						$scope.unitname = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					} else {
						$scope.unitname = "";
					}
				}				
				$scope.scopeofsupplylist.push({ 'sequence':$scope.scopesequenceadd,  'particular':$scope.particularadd, 'value':$scope.valueadd, 'unitId':$scope.scopeunitidadd, 'unitName':$scope.unitname });
				$scope.scopesequenceadd = undefined;
				$scope.particularadd = "";
				$scope.valueadd = "";
				$scope.scopeunitidadd = "";
			}
		}

		$scope.removeScopeRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.scopeofsupplylist.length; i++){
				if($scope.scopeofsupplylist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.scopeofsupplylist.splice(index, 1);
		};
		
		$scope.specificationlist = [];
		$scope.addSpecificationRow = function() {			
			if(!$scope.specunitidadd){
				$scope.specunitidadd = 0;
			}
			if($scope.sequenceadd==undefined || $scope.sequenceadd=="") {				
				$scope.errorSequence = 1;
				$scope.msgSequence = "Please enter sequence!";
				document.getElementById("sequenceadd").focus();
			} else if($scope.specificationadd==undefined || $scope.specificationadd=="") {				
				$scope.errorSpecification = 1;
				$scope.msgSpecification = "Please enter specification!";
				document.getElementById("specificationadd").focus();
			} else if($scope.specvalueadd==undefined || $scope.specvalueadd=="") {				
				$scope.errorSpecValue = 1;
				$scope.msgSpecValue = "Please enter specification value!";
				document.getElementById("specvalueadd").focus();
			} else {				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.specunitidadd) {
						$scope.unitname = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					} else {
						$scope.unitname = "";
					}
				}				
				$scope.specificationlist.push({ 'sequence':$scope.sequenceadd, 'specification':$scope.specificationadd, 'specValue':$scope.specvalueadd, 'unitId':$scope.specunitidadd, 'unitName':$scope.unitname });
				$scope.sequenceadd = undefined;
				$scope.specificationadd = "";
				$scope.specvalueadd = "";
				$scope.specunitidadd = "";
			}
		}

		$scope.removeSpecificationRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.specificationlist.length; i++){
				if($scope.specificationlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing specification..Please try again!");
				return;
			}
			$scope.specificationlist.splice(index, 1);
		};
		
		$scope.supplierlist = [];
		$scope.addSupplierRow = function() {
			if($scope.supplieradd==undefined || $scope.supplieradd=="") {				
				$scope.errorSupplier = 1;
				$scope.msgSupplier = "Please select supplier!";
				document.getElementById("supplieradd").focus();
			} else {
				for (i in $scope.getSuppliers) {
					if ($scope.getSuppliers[i].userId == $scope.supplieradd) {
						$scope.suppliername = $scope.getSuppliers[i].userCompanyName;
						break;
					}
				}	
				$scope.supplierlist.push({'supplierId':$scope.supplieradd, 'supplierName':$scope.suppliername});
			}
		}

		$scope.removeSupplierRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.supplierlist.length; i++){
				if($scope.supplierlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing supplier..Please try again!");
				return;
			}
			$scope.supplierlist.splice(index, 1);
		};
		
		$scope.pricelist = [];
		$scope.addPriceRow = function() {
			if($scope.pricetypeadd==undefined || $scope.pricetypeadd=="") {				
				$scope.errorPriceType = 1;
				$scope.msgPriceType = "Please select price type!";			
				document.getElementById("pricetypeadd").focus();
			} else if($scope.priceadd==undefined || $scope.priceadd=="") {				
				$scope.errorPrice = 1;
				$scope.msgPrice = "Please enter price!";				
				document.getElementById("priceadd").focus();
			} else if($scope.currencyadd==undefined || $scope.currencyadd=="") {				
				$scope.errorCurrency = 1;
				$scope.msgCurrency = "Please select currency!";
				document.getElementById("currencyadd").focus();
			} else {
				for (i in $scope.getPriceType) {
					if ($scope.getPriceType[i].priceTypeId == $scope.pricetypeadd) {
						$scope.pricetypename = $scope.getPriceType[i].priceTypeName;
						break;
					}
				}
				
				for (i in $scope.getCurrencies) {
					if ($scope.getCurrencies[i].currencyId == $scope.currencyadd) {
						$scope.currencycode = $scope.getCurrencies[i].currencyCode;
						break;
					}
				}
				
				$scope.pricelist.push({'priceTypeId':$scope.pricetypeadd, 'priceTypeName':$scope.pricetypename, 'price':$scope.priceadd, 'currencyId':$scope.currencyadd, 'currencyCode':$scope.currencycode});
			}
		}

		$scope.removePriceRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.pricelist.length; i++){
				if($scope.pricelist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing price..Please try again!");
				return;
			}
			$scope.pricelist.splice(index, 1);
		};
		
		$scope.statusadd = "y";
		
		$scope.addProduct = function() {
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var brandname = $scope.brandnameadd;			
			var saletype = $scope.saletypeadd;			
			var productname = $scope.productnameadd;
			var partnumber = $scope.partnumberadd;			
			var hsn = $scope.hsnadd;
			var unitid = $scope.unitidadd;
			var description = $scope.descriptionadd;			
			
			if(subcategoryname==undefined || subcategoryname=="") {
				subcategoryname = 0;
			}
			if(brandname==undefined || brandname=="") {
				brandname = 0;
			}			
			if(partnumber==undefined || partnumber=="") {
				partnumber = "";
			}
			if(saletype==undefined || saletype=="") {
				saletype = 0;
			}
			if(description==undefined || description=="") {
				description = "";
			}
			if(hsn==undefined || hsn=="") {
				hsn = 0;
			}			

			if(categoryname==undefined || categoryname=="") {				
				$scope.errorCategoryName = 1;
				$scope.msgCategoryName = "Please select category";
				document.getElementById("categorynameadd").focus();
			} else if(productname==undefined || productname=="") {
				document.getElementById("productnameadd").focus();
				$scope.errorProductName = 1;
				$scope.msgProductName = "Please enter product name";				
			}  else {				
				$scope.spin = 1;
				var a = 0, b = 0, c = 0, d = 0, e = 0;
				var pro = productname.replace("&","$");
				var pro1 = pro.replace("#","~");
				var pro2 = pro1.replace("%","!");
				
				var link = baseUrl+'addProduct?categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&brandname='+brandname+'&saletype='+saletype+'&productname='+pro2+'&partnumber='+partnumber+'&hsn='+hsn+'&description='+description;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addproduct = data;
					if($scope.addproduct == "Success"){
						$scope.spin = 0;
						if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c && $scope.taxlist.length == d && $scope.scopeofsupplylist.length == e) {
									$scope.productSubmitError = 0;
									$scope.productSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.productSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
						angular.forEach($scope.specificationlist,function(item) {												
							var link = baseUrl+'addProductSpecification?sequence='+item.sequence+'&specification='+item.specification+'&specvalue='+item.specValue+'&unitid='+item.unitId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addproductspecification = data;
								a = a + 1;
								if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c && $scope.taxlist.length == d && $scope.scopeofsupplylist.length == e) {
									$scope.productSubmitError = 0;
									$scope.productSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.productSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addproductspecification = "Response Fail";
							});
						});
						
						angular.forEach($scope.scopeofsupplylist,function(item) {												
							var link = baseUrl+'addProductScopeOfSupply?sequence='+item.sequence+'&particular='+item.particular+'&value='+item.value+'&unitid='+item.unitId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addscopeofsupply = data;
								e = e + 1;
								if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c && $scope.taxlist.length == d && $scope.scopeofsupplylist.length == e) {
									$scope.productSubmitError = 0;
									$scope.productSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.productSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addscopeofsupply = "Response Fail";
							});
						});
						
						angular.forEach($scope.supplierlist,function(item) {												
							var link = baseUrl+'addProductSupplier?supplierid='+item.supplierId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addproductsupplier = data;
								b = b + 1;
								if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c && $scope.taxlist.length == d && $scope.scopeofsupplylist.length == e) {
									$scope.productSubmitError = 0;
									$scope.productSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.productSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addproductsupplier = "Response Fail";
							});
						});
						
						angular.forEach($scope.pricelist,function(item) {												
							var link = baseUrl+'addProductPrice?pricetypeid='+item.priceTypeId+'&price='+item.price+'&currencyid='+item.currencyId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addproductprice = data;
								c = c + 1;
								if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c && $scope.taxlist.length == d && $scope.scopeofsupplylist.length == e) {
									$scope.productSubmitError = 0;
									$scope.productSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.productSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addproductprice = "Response Fail";
							});
						});
						
						angular.forEach($scope.taxlist,function(item) {						
							var link = baseUrl+'addProductTax?taxid='+item.taxId+'&rate='+item.rate;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addproducttax = data;
								d = d + 1;
								if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c && $scope.taxlist.length == d && $scope.scopeofsupplylist.length == e) {
									$scope.productSubmitError = 0;
									$scope.productSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.productSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_product';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addproducttax = "Response Fail";
							});
						});
						
					} else {
						$scope.productSubmitSuccess = 0;
						$scope.productSubmitError = 1;
						$scope.spin = 0;
						$scope.errorMsg = $scope.addproduct;
					}					
					
				}).error(function(data, status, headers, config) {
					$scope.addproduct = "Response Fail";
					$scope.productSubmitSuccess = 0;
					$scope.productSubmitError = 1;
					$scope.spin = 0;
					$scope.errorMsg = "Something went wrong!";
				});				
			}
		}
			
		$scope.getProduct = function(productid) {
			var link = baseUrl+'getProductDetailById?productid='+productid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getProductDetailById = data;
				$scope.productid = $scope.getProductDetailById.productId;
            	$scope.categoryname = $scope.getProductDetailById.categoryId;
            	$scope.subcategoryname = $scope.getProductDetailById.subcategoryId;
            	$scope.brandname = $scope.getProductDetailById.brandId;            	
            	$scope.saletype = $scope.getProductDetailById.productSaleTypeId;
            	$scope.productname = $scope.getProductDetailById.productName; 
            	$scope.partnumber = $scope.getProductDetailById.partNumber;            	
            	$scope.unitid = $scope.getProductDetailById.unitId;
            	$scope.hsn = $scope.getProductDetailById.hsn;
            	$scope.recurringvalue = $scope.getProductDetailById.recurringValue;
            	$scope.recurringbilling = $scope.getProductDetailById.recurringBilling;
            	$scope.description = $scope.getProductDetailById.description;
            	
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
    			
    			var link = baseUrl+'getProductSupplierByProductId?productid='+productid;
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.getproductsupplierlist = data;
    			}).error(function(data, status, headers, config) {
    				$scope.getproductsupplierlist = "Response Fail";
    			});
    			
    			var link = baseUrl+'getProductPriceByProductId?productid='+productid;
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.getproductpricelist = data;
    			}).error(function(data, status, headers, config) {
    				$scope.getproductpricelist = "Response Fail";
    			});

    			var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+$scope.categoryname;
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.getSubcategories = data;
    			}).error(function(data, status, headers, config) {
    				$scope.getSubcategories = "Response Fail";
    			});
    			
    			var link = baseUrl+'getProductTaxByProductId?productid='+productid;			
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.gettaxlist = data;
    			}).error(function(data, status, headers, config) {
    				$scope.gettaxlist = "Response Fail";
    			});
            	
			}).error(function(data, status, headers, config) {
				$scope.getProductDetailById = "Response Fail";
			});			
		}	
		
		$scope.addTaxRowEdit = function() {
			if($scope.taxname==undefined || $scope.taxname=="") {
				document.getElementById("taxname").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please select tax";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			} else if($scope.rate==undefined || $scope.rate=="") {
				document.getElementById("rate").focus();
				$scope.taxinfo = 1;
				$scope.taxmessage = "Please enter rate";
				$timeout(function(){
					$scope.taxinfo = 0;
				}, 2000);
			} else {
				for (i in $scope.getTaxes) {
					if ($scope.getTaxes[i].taxId == $scope.taxname) {
						$scope.taxname1 = $scope.getTaxes[i].taxName;
						break;
					}
				}		
				$scope.gettaxlist.push({'taxId':$scope.taxname, 'taxName':$scope.taxname1, 'rate':$scope.rate});				
			}
		}

		$scope.removeTaxRowEdit = function(item) {
			var index = -1;
			for(var i=0; i<$scope.gettaxlist.length; i++){
				if($scope.gettaxlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing tax..Please try again!");
				return;
			}
			$scope.gettaxlist.splice(index, 1);
		};
		
		$scope.addScopeRowEdit = function() {
			
			if(!$scope.scopeunitid){
				$scope.scopeunitid = 0;
			}
			if($scope.scopesequence==undefined || $scope.scopesequence=="") {				
				$scope.errorScopeSequence = 1;
				$scope.msgScopeSequence = "Please enter sequence!";
				document.getElementById("scopesequence").focus();
			} else if($scope.particular==undefined || $scope.particular=="") {				
				$scope.errorParticular = 1;
				$scope.msgParticular = "Please enter particular!";
				document.getElementById("particular").focus();
			} else if($scope.value==undefined || $scope.value=="") {				
				$scope.errorValue = 1;
				$scope.msgValue = "Please enter value!";
				document.getElementById("value").focus();
			} else {				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.scopeunitid) {
						$scope.unitname = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					} else {
						$scope.unitname = "";
					}
				}				
				$scope.getproductscopeofsupply.push({'sequence':$scope.scopesequence, 'particular':$scope.particular, 'value':$scope.value, 'unitId':$scope.scopeunitid, 'unitName':$scope.unitname});
				$scope.scopesequence = undefined;
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
		
		$scope.addSpecificationRowEdit = function() {
			
			if(!$scope.specunitid){
				$scope.specunitid = 0;
			}
			
			if($scope.sequence==undefined || $scope.sequence=="") {				
				$scope.errorSequence = 1;
				$scope.msgSequence = "Please enter sequence!";
				document.getElementById("sequence").focus();
			} else if($scope.specification==undefined || $scope.specification=="") {				
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
				$scope.getproductspecificationlist.push({ 'sequence':$scope.sequence, 'specification':$scope.specification, 'specValue':$scope.specvalue, 'unitId':$scope.specunitid, 'unitName':$scope.unitname });
				$scope.sequence = undefined;
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
		
		$scope.addSupplierRowEdit = function() {
			if($scope.supplier==undefined || $scope.supplier=="") {				
				$scope.errorSupplier = 1;
				$scope.msgSupplier = "Please select supplier!";
				document.getElementById("supplier").focus();
			} else {
				for (i in $scope.getSuppliers) {
					if ($scope.getSuppliers[i].userId == $scope.supplier) {
						$scope.suppliername = $scope.getSuppliers[i].userCompanyName;
						break;
					}
				}	
				$scope.getproductsupplierlist.push({'supplierId':$scope.supplier, 'supplierName':$scope.suppliername});
			}
		}

		$scope.removeSupplierRowEdit = function(item) {
			var index = -1;
			for(var i=0; i<$scope.getproductsupplierlist.length; i++){
				if($scope.getproductsupplierlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing supplier..Please try again!");
				return;
			}
			$scope.getproductsupplierlist.splice(index, 1);
		};		
		
		$scope.addPriceRowEdit = function() {
			if($scope.pricetype==undefined || $scope.pricetype=="") {				
				$scope.errorPriceType = 1;
				$scope.msgPriceType = "Please select price type!";
				document.getElementById("pricetype").focus();
			} else if($scope.price==undefined || $scope.price=="") {				
				$scope.errorPrice = 1;
				$scope.msgPrice = "Please enter price!";
				document.getElementById("price").focus();
			} else if($scope.currency==undefined || $scope.currency=="") {				
				$scope.errorCurrency = 1;
				$scope.msgCurrency = "Please select currency!";
				document.getElementById("currency").focus();
			} else {
				for (i in $scope.getPriceType) {
					if ($scope.getPriceType[i].priceTypeId == $scope.pricetype) {
						$scope.pricetypename = $scope.getPriceType[i].priceTypeName;
						break;
					}
				}
				
				for (i in $scope.getCurrencies) {
					if ($scope.getCurrencies[i].currencyId == $scope.currency) {
						$scope.currencycode = $scope.getCurrencies[i].currencyCode;
						break;
					}
				}
				
				$scope.getproductpricelist.push({'priceTypeId':$scope.pricetype, 'priceTypeName':$scope.pricetypename, 'price':$scope.price, 'currencyId':$scope.currency, 'currencyCode':$scope.currencycode});
			}
		}

		$scope.removePriceRowEdit = function(item) {
			var index = -1;
			for(var i=0; i<$scope.getproductpricelist.length; i++){
				if($scope.getproductpricelist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0){
				$window.alert("Error while removing price..Please try again!");
				return;
			}
			$scope.getproductpricelist.splice(index, 1);
		};
		
		
		$scope.editProduct = function(productid) {
			var categoryname = $scope.categoryname;
			var subcategoryname = $scope.subcategoryname;
			var brandname = $scope.brandname;			
			var saletype = $scope.saletype;			
			var productname = $scope.productname;
			var partnumber = $scope.partnumber;			
			var unitid = $scope.unitid;
			var hsn = $scope.hsn;
			var description = $scope.description;			
			
			if(subcategoryname==undefined || subcategoryname=="") {
				subcategoryname = 0;
			}
			if(brandname==undefined || brandname=="") {
				brandname = 0;
			}			
			if(saletype==undefined || saletype=="") {
				saletype = 0;
			}
			if(partnumber==undefined || partnumber=="") {
				partnumber = "";
			}
			if(description==undefined || description=="") {
				description = "";
			}
			if(hsn==undefined || hsn=="") {
				hsn = 0;
			}			

			if(categoryname==undefined || categoryname=="") {				
				$scope.errorCategoryName = 1;
				$scope.msgCategoryName = "Please select category";
				document.getElementById("categoryname").focus();
			} else if(productname==undefined || productname=="") {				
				$scope.errorProductName = 1;
				$scope.msgProductName = "Please enter product name";
				document.getElementById("productname").focus();
			} else if(unitid==undefined || unitid=="") {				
				$scope.errorUnit = 1;
				$scope.msgUnit = "Please select unit";
				document.getElementById("unitid").focus();
			} else {
				$scope.spin = 1;
				
				var link = baseUrl+'deleteProductScopeOfSupply?productid='+productid;
				$http['delete'](link).success(function(data, status,headers, config) {
					$scope.deleteproductscopeofsupply = data;
				}).error(function(data, status,headers, config) {
					$scope.deleteproductscopeofsupply = "Response Fail";
				});
				
				var link = baseUrl+'deleteProductSpecifications?productid='+productid;
				$http['delete'](link).success(function(data, status,headers, config) {
					$scope.deleteproductspecifications = data;
				}).error(function(data, status,headers, config) {
					$scope.deleteproductspecifications = "Response Fail";
				});
				
				var link = baseUrl+'deleteProductSupplier?productid='+productid;
				$http['delete'](link).success(function(data, status,headers, config) {
					$scope.deleteproductsupplier = data;
				}).error(function(data, status,headers, config) {
					$scope.deleteproductsupplier = "Response Fail";
				});
				
				var link = baseUrl+'deleteProductPrice?productid='+productid;
				$http['delete'](link).success(function(data, status,headers, config) {
					$scope.deleteproductprice = data;
				}).error(function(data, status,headers, config) {
					$scope.deleteproductprice = "Response Fail";
				});
				
				var link = baseUrl+'deleteProductTax?productid='+productid;
				$http['delete'](link).success(function(data, status,headers, config) {
					$scope.deleteproducttax = data;
				}).error(function(data, status,headers, config) {
					$scope.deleteproducttax = "Response Fail";
				});
				
				var pro = productname.replace("&","$");
				var pro1 = pro.replace("#","~");
				var pro2 = pro1.replace("%","!");
				
				var link = baseUrl+'editProduct?productid='+productid+'&categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&brandname='+brandname+'&saletype='+saletype+'&productname='+pro2+'&partnumber='+partnumber+'&unitid='+unitid+'&hsn='+hsn+'&description='+description;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editproduct = data;				
					
					var e = 0, f = 0, g = 0, h = 0, i = 0;
					$scope.spin = 0;
					if($scope.editproduct == "Success"){
						
						angular.forEach($scope.getproductscopeofsupply,function(item) {												
							var link = baseUrl+'editProductScopeOfSupply?productid='+productid+'&sequence='+item.sequence+'&particular='+item.particular+'&value='+item.value+'&unitid='+item.unitId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editproductscopeofvalue = data;
								i = i + 1;
								if($scope.getproductspecificationlist.length == e && $scope.getproductsupplierlist.length == f && $scope.getproductpricelist.length == g && $scope.getproductscopeofsupply.length == i) {
									
									var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
									$http.get(link).success(function(data, status, headers, config) {
										$scope.getProducts = data;
										
										$scope.productSubmitSuccess = 1;
										$scope.successMsg = "Data updated";
										$timeout(function(){
											$scope.productSubmitSuccess = 0;
											$('#editModal').modal('hide');
										}, 2000);
										
									}).error(function(data, status, headers, config) {
										$scope.getProducts = "Response Fail";
									});									
								}
							}).error(function(data, status, headers, config) {
								$scope.editproductscopeofvalue = "Response Fail";
							});
						});
						
						angular.forEach($scope.getproductspecificationlist,function(item) {												
							var link = baseUrl+'editProductSpecification?productid='+productid+'&sequence='+item.sequence+'&specification='+item.specification+'&specvalue='+item.specValue+'&unitid='+item.unitId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editproductspecification = data;
								e = e + 1;
								if($scope.getproductspecificationlist.length == e && $scope.getproductsupplierlist.length == f && $scope.getproductpricelist.length == g && $scope.getproductscopeofsupply.length == i) {
									
									var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
									$http.get(link).success(function(data, status, headers, config) {
										$scope.getProducts = data;
										
										$scope.productSubmitSuccess = 1;
										$scope.successMsg = "Data updated";
										$timeout(function(){
											$scope.productSubmitSuccess = 0;
											$('#editModal').modal('hide');
										}, 2000);
										
									}).error(function(data, status, headers, config) {
										$scope.getProducts = "Response Fail";
									});									
								}
							}).error(function(data, status, headers, config) {
								$scope.editproductspecification = "Response Fail";
							});
						});
						
						angular.forEach($scope.getproductsupplierlist,function(item) {												
							var link = baseUrl+'editProductSupplier?productid='+productid+'&supplierid='+item.supplierId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editproductsupplier = data;
								f = f + 1;
								if($scope.getproductspecificationlist.length == e && $scope.getproductsupplierlist.length == f && $scope.getproductpricelist.length == g && $scope.getproductscopeofsupply.length == i) {
									
									var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
									$http.get(link).success(function(data, status, headers, config) {
										$scope.getProducts = data;
										
										$scope.productSubmitSuccess = 1;
										$scope.successMsg = "Data updated";
										$timeout(function(){
											$scope.productSubmitSuccess = 0;
											$('#editModal').modal('hide');
										}, 2000);
										
									}).error(function(data, status, headers, config) {
										$scope.getProducts = "Response Fail";
									});									
								}
							}).error(function(data, status, headers, config) {
								$scope.editproductsupplier = "Response Fail";
							});
						});
						
						angular.forEach($scope.getproductpricelist,function(item) {												
							var link = baseUrl+'editProductPrice?productid='+productid+'&pricetypeid='+item.priceTypeId+'&price='+item.price+'&currencyid='+item.currencyId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editproductprice = data;
								g = g + 1;
								if($scope.getproductspecificationlist.length == e && $scope.getproductsupplierlist.length == f && $scope.getproductpricelist.length == g && $scope.getproductscopeofsupply.length == i) {
									
									var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
									$http.get(link).success(function(data, status, headers, config) {
										$scope.getProducts = data;
										
										$scope.productSubmitSuccess = 1;
										$scope.successMsg = "Data updated";
										$timeout(function(){
											$scope.productSubmitSuccess = 0;
											$('#editModal').modal('hide');
										}, 2000);
										
									}).error(function(data, status, headers, config) {
										$scope.getProducts = "Response Fail";
									});									
								}
							}).error(function(data, status, headers, config) {
								$scope.editproductprice = "Response Fail";
							});
						});
						
						angular.forEach($scope.gettaxlist,function(item) {
							$scope.taxstate = [];
							for(i in item.states){
								$scope.taxstate.push(item.states[i].stateId);
							}
							var link = baseUrl+'editProductTax?productid='+productid+'&taxid='+item.taxId+'&rate='+item.rate;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editproducttax = data;
								h = h + 1;
								if($scope.getproductspecificationlist.length == e && $scope.getproductsupplierlist.length == f && $scope.getproductpricelist.length == g && $scope.getproductscopeofsupply.length == i) {
									
									var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
									$http.get(link).success(function(data, status, headers, config) {
										$scope.getProducts = data;
										
										$scope.productSubmitSuccess = 1;
										$scope.successMsg = "Data updated";
										$timeout(function(){
											$scope.productSubmitSuccess = 0;
											$('#editModal').modal('hide');
										}, 2000);
										
									}).error(function(data, status, headers, config) {
										$scope.getProducts = "Response Fail";
									});									
								}
							}).error(function(data, status, headers, config) {
								$scope.editproducttax = "Response Fail";
							});
						});
					} else {
						$scope.productSubmitSuccess = 0;
						$scope.productSubmitError = 1;
						$scope.spin = 0;
						$scope.errorMsg = $scope.editproduct;
					}			
					
				}).error(function(data, status, headers, config) {
					$scope.editproduct = "Response Fail";
					$scope.productSubmitSuccess = 0;
					$scope.productSubmitError = 1;
					$scope.spin = 0;
					$scope.errorMsg = "Smoething went wrong!";
				});				
			}
		}		
		
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;		
			angular.forEach($scope.getProducts, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteProduct = function() {
			angular.forEach($scope.getProducts, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteProduct?productid='+item.productId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteproduct = data;
					}).error(function(data, status, headers, config) {
						$scope.deleteproduct = "Response Fail";
					});
				}
				var link = baseUrl+'getProductsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProducts = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getProducts = "Response Fail";
				});
			});			
		}
		
		$scope.addCategory = function() {
			var categoryname = $scope.categorynameadd;
			var categorycode = $scope.categorycodeadd;
			var description = $scope.categorydescriptionadd;
			
			if(description==undefined || description=="") {
				description = "";
			}

			if(categoryname==undefined || categoryname=="") {				
				$scope.errorCategoryName = 1;
				$scope.msgCategoryName = "Please enter category name";
				document.getElementById("categorynameadd").focus();
			} else if(categorycode==undefined || categorycode=="") {				
				$scope.errorCategoryCode = 1;
				$scope.msgCategoryCode = "Please enter category Code";
				document.getElementById("categorycodeadd").focus();
			} else {
				var a = 0, b = 0;				
				if(categorycode!=undefined || categorycode!="") {
					for(i in $scope.getCategories) {
						b = b + 1;
						if($scope.getCategories[i].categoryCode == categorycode) {
							a = 1;
							document.getElementById("categorycodeadd").focus();
							$scope.categorySubmitError = 1;
							$scope.msgError = "Category Code Already Exist";
							$timeout(function(){
								$scope.categorySubmitError = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0 && $scope.getCategories.length == b) {
					$scope.spin = 1;
					
					var cat = categoryname.replace("&","$");
					var cat1 = cat.replace("#","~");
					var cat2 = cat1.replace("%","!");
					
					var link = baseUrl+'addCategory?categoryname='+cat2+'&categorycode='+categorycode+'&description='+description;
					
					var formData=new FormData();
					formData.append("image",imageadd.files[0]);
					
					$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
						}).success(function(data, status) {
							$scope.addcategory = data;
							var link = baseUrl+'getCategories';
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getCategories = data;
								$scope.spin = 0;
								$scope.categorySubmitSuccess = 1;								
								$scope.msgSuccess = "Data added successfully";								
								$timeout(function(){
									$scope.categorySubmitSuccess = 0;
									$('#categoryModal').modal('hide');
								}, 2000);
							}).error(function(data, status, headers, config) {
								$scope.getCategories = "Response Fail";
							});							
						}).error(function(data, status, headers, config) {
							$scope.addcategory = "Response Fail";
						});
				}
			}
		}
		
		$scope.addSubcategory = function() {
			var categoryname = $scope.categorynameadd;
			var subcategoryname = $scope.subcategorynameadd;
			var subcategorycode = $scope.subcategorycodeadd;
			var description = $scope.descriptionadd;
			
			var valuex = document.getElementById("valuex").value;
			var valuey = document.getElementById("valuey").value;
			var valuew = document.getElementById("valuew").value;
			var valueh = document.getElementById("valueh").value;
			
			if(valuex == ''){
				valuex = 0;
			}
			if(valuey == ''){
				valuey = 0;
			}
			if(valuew == ''){
				valuew = 0;
			}
			if(valueh == ''){
				valueh = 0;
			}

			if(description==undefined || description=="") {
				description = "";
			}

			if(categoryname==undefined || categoryname=="") {				
				$scope.errorCategoryName = 1;
				$scope.msgCategoryName = "Please select category";
				document.getElementById("categorynameadd").focus();
			} else if(subcategoryname==undefined || subcategoryname=="") {				
				$scope.errorSubcategoryName = 1;
				$scope.msgSubcategoryName = "Please enter subcategory name";
				document.getElementById("subcategorynameadd").focus();				
			} else if(subcategorycode==undefined || subcategorycode=="") {				
				$scope.errorSubcategoryCode = 1;
				$scope.msgSubcategoryCode = "Please enter subcategory code";
				document.getElementById("subcategorycodeadd").focus();
			} else {
				var a = 0, b = 0;				
				if(subcategorycode!=undefined || subcategorycode!="") {
					for(i in $scope.getSubcategories) {
						b = b + 1;
						if($scope.getSubcategories[i].subcategoryCode == subcategorycode) {
							a = 1;
							document.getElementById("subcategorycodeadd").focus();
							$scope.subcategorySubmitError = 1;
							$scope.msgError = "Sub Category code already exist";
							$timeout(function(){
								$scope.subinfo = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0) {
					$scope.spin = 1;					
					var sub = subcategoryname.replace("&","$");
					var sub1 = sub.replace("#","~");
					var sub2 = sub1.replace("%","!");					
					var link = baseUrl+'addSubcategory?categoryname='+categoryname+'&subcategoryname='+sub2+'&subcategorycode='+subcategorycode+'&description='+description+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;					
					var formData=new FormData();
					formData.append("image",subimageadd.files[0]);					
					$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction) {
				        	return data;
				        }
						}).success(function(data, status) {
							$scope.addsubcategory = data;
							var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+categoryname;
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getSubcategories = data;
								$scope.spin = 0;
								$scope.subcategorySubmitSuccess = 1;								
								$scope.msgSuccess = "Data added successfully.";								
								$timeout(function(){
									$scope.subcategorySubmitSuccess = 0;
									$('#subcategoryModal').modal('hide');
								}, 2000);
							}).error(function(data, status, headers, config) {
								$scope.getSubcategories = "Response Fail";
							});							
						}).error(function(data, status, headers, config) {
							$scope.addsubcategory = "Response Fail";
						});
				}
			}
		}
		
		$scope.addBrand = function() {
			var brandname = $scope.brandnameadd;
			var branddescription = $scope.branddescriptionadd;
			
			if(branddescription==undefined || branddescription=="") {
				branddescription = "";
			}

			if(brandname==undefined || brandname=="") {				
				$scope.errorBrandName = 1;
				$scope.msgBrandName = "Please Enter Brand Name";
				document.getElementById("brandnameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addBrand?brandname='+brandname+'&branddescription='+branddescription;				
				var formData=new FormData();
				formData.append("image",brandlogoadd.files[0]);				
				$http({method: 'POST',
					url: link,				
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction) {
			        	return data;
			        }
				}).success(function(data, status) {
					$scope.addbrand = data;	
					var link = baseUrl+'getBrands';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getBrands = data;
						$scope.spin = 0;
						$scope.brandSubmitSuccess = 1;							
						$scope.msgSuccess = "Data added successfully.";							
						$timeout(function(){
							$scope.brandSubmitSuccess = 0;
							$('#brandModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.getBrands = "Response Fail";
					});					
				}).error(function(data, status, headers, config) {
					$scope.addbrand = "Response Fail";
					$scope.spin = 0;
					$scope.brandSubmitError = 1;							
					$scope.msgError = "Something wrong! Please try after sometime!";					
				});
			}
		}
		
		$scope.addMeasurementUnit = function() {
			var measurementunitname = $scope.measurementunitnameadd;
			var description = $scope.unitdescriptionadd;			
			if(description==undefined || description=="") {
				description = "";
			}
			if(measurementunitname==undefined || measurementunitname=="") {				
				$scope.errorUnitName = 1;
				$scope.msgUnitName = "Please enter unit name";
				document.getElementById("measurementunitnameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addMeasurementUnit?measurementunitname='+measurementunitname+'&description='+description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addmeasurementunit = data;
					var link = baseUrl+'getMeasurementUnits';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getMeasurementUnits = data;
						$scope.spin = 0;
						$scope.unitSubmitSuccess = 1;
						$scope.msgSuccess = "Data added successfully.";
						$timeout(function() {
							$scope.unitSubmitSuccess = 0;
							$('#unitModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.getMeasurementUnits = "Response Fail";
					});					
				}).error(function(data, status, headers, config) {
					$scope.addmeasurementunit = "Response Fail";
					$scope.spin = 0;
					$scope.unitSubmitError = 1;
					$scope.msgError = "Something wrong! Please try again later!";
				});
			}
		}
		
		$scope.addSubbrand = function() {
			if($scope.descriptionadd==undefined || $scope.descriptionadd=="") {
				$scope.descriptionadd = "";
			}
			if(!$scope.brandnameadd) {				
				$scope.errorBrandId = 1;
				$scope.msgBrandId = "Please select brand";
				document.getElementById("brandnameadd").focus();
			} else if(!$scope.subbrandnameadd) {				
				$scope.errorSubbrandName = 1;
				$scope.msgSubbrandName = "Please enter subbrand name";
				document.getElementById("subbrandnameadd").focus();
			} else {				
				$scope.spin = 1;					
				$scope.subbrandnameadd = $scope.subbrandnameadd.replace("&","$");
				$scope.subbrandnameadd = $scope.subbrandnameadd.replace("#","~");
				$scope.subbrandnameadd = $scope.subbrandnameadd.replace("%","!");					
				var link = baseUrl+'addSubbrand?brandname='+$scope.brandnameadd+'&subbrandname='+$scope.subbrandnameadd+'&description='+$scope.descriptionadd;					
				$http.post(link).success(function(data, status, headers, config) {
	    			$scope.addsubbrand = data; 
	    			var link = baseUrl+'getSubbrand';
	    			$http.get(link).success(function(data, status, headers, config) {
	    				$scope.getSubbrand = data;
	    				
	    				if($scope.addsubbrand == "Success"){
		    				$scope.spin = 0;
		    				$scope.successSubmit = 1;
		    				$scope.msgSuccess = "Data added successfully";
		    				$timeout(function(){
		    					$('#subbrandModal').modal('hide'); 				
		    				}, 3000);
		    			} else {
		    				$scope.spin = 0;
		    				$scope.errorSubmit = 1;
		    				$scope.msgError = $scope.addsubbrand;
		    				$timeout(function(){
		    					$('#subbrandModal').modal('hide'); 				
		    				}, 3000);
		    			}
	    				
	    			}).error(function(data, status, headers, config) {
	    				$scope.getSubbrand = "Response Fail";
	    			});    			
				}).error(function(data, status, headers, config) {
					$scope.addsubbrand = data;
					$scope.spin = 0;
					$scope.errorSubmit = 1;
					$scope.msgError = $scope.addsubbrand;
					$timeout(function(){
						$('#subbrandModal').modal('hide'); 				
					}, 3000);
				});
			}
		}
		
		$scope.addUser = function() {		
			var companyname = $scope.companynameadd;
			var firstname = $scope.firstnameadd;		
			var lastname = $scope.lastnameadd;
			var usertypename = $scope.usertypenameadd;		
			var address1 = $scope.address1add;
			var address2 = $scope.address2add;
			var address3 = $scope.address3add;
			var countryname = $scope.countrynameadd;
			var statename = $scope.statenameadd;
			var cityname = $scope.citynameadd;
			var pincode = $scope.pincodeadd;
			var mobilenumber = $scope.mobilenumberadd;
			var landlinenumber = $scope.landlinenumberadd;
			var email = $scope.emailadd;
			var password = $scope.passwordadd;	
			
			var middlename = "";
			var gender = "";
			var dateofbirth = "";
			var aadharnumber = "";
			var passportnumber = "";
			var pannumber = "";					
			
			if(firstname==undefined || firstname=="")	{
				firstname = "";
			}
			if(lastname==undefined || lastname=="")	{
				lastname = "";
			}			
			if(address1==undefined || address1=="") {
				address1 = "";
			}
			if(address2==undefined || address2=="") {
				address2 = "";
			}
			if(address3==undefined || address3=="") {
				address3 = "";
			}
			if(countryname==undefined || countryname=="") {
				countryname = 0;
			}
			if(statename==undefined || statename=="") {
				statename = 0;
			}
			if(cityname==undefined || cityname=="") {
				cityname = "";
			}
			if(pincode==undefined || pincode=="") {
				pincode = "";
			}
			if(mobilenumber==undefined || mobilenumber=="") {
				mobilenumber = "";
			}
			if(landlinenumber==undefined || landlinenumber=="") {
				landlinenumber = "";
			}
			if(email==undefined || email=="") {
				email = "";
			}
			if(password==undefined || password=="") {
				password = "";
			}
			
			if(companyname==undefined || companyname=="") {				
				$scope.errorCompanyName = 1;
				$scope.msgCompanyName = "Please enter company name";
				document.getElementById("companynameadd").focus();
			} else if(usertypename==undefined || usertypename=="") {				
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please select user type";
				document.getElementById("usertypenameadd").focus();
			} else {
				$scope.userspin = 1;		
				var link = baseUrl+'addUser?companyname='+companyname+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&usertypename='+usertypename+'&gender='+gender+'&dateofbirth='+dateofbirth+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&landlinenumber='+landlinenumber+'&email='+email+'&password='+password;			
				var formData=new FormData();
				formData.append("profilepicture",profilepictureadd.files[0]);					
				$http({method: 'POST',
					url: link,				
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction) {
			        	return data;
			        }
				}).success(function(data, status) {
					$scope.adduser = data;
					$scope.userspin = 0;
					$scope.userSuccess = 1;
					$scope.userMsg = "User Added Successfully.";
					
					var link = baseUrl+'getSuppliers';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getSuppliers = data;
					}).error(function(data, status, headers, config) {
						$scope.getSuppliers = "Response Fail";
					});
					
					$timeout(function(){
						$scope.userSuccess = 0;
						$('#userModal').modal('hide');						
					}, 2000);
				}).error(function(data, status, headers, config) {
					$scope.adduser = "Response Fail";
				});			
			}
		}
		
}]);