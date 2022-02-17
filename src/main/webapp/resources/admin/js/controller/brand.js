app.controller('brandCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
	{
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
		
		$scope.info = 0;
		$scope.success = 0;
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getBrands.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getBrands = data;
		}).error(function(data, status, headers, config) {
			$scope.getBrands = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			}
			else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;
					
				var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBrands = data;
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getBrands = data;
			}).error(function(data, status, headers, config) {
				$scope.getBrands = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getBrands';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBrands = data;
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});
			} else {
				var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBrands = data;
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});
			}
		}
		
		$scope.searchBrand = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBrands = data;
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchBrands?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBrands = data;
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorBrandName = 0;						
		}
		
		$scope.addBrand = function() {
			
			if(!$scope.branddescriptionadd) {
				$scope.branddescriptionadd = "";
			}

			if(!$scope.brandnameadd) {
				$scope.errorBrandName = 1;
				$scope.msgBrandName = "Please enter brand name";				
				document.getElementById("brandnameadd").focus();				
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addBrand?brandname='+$scope.brandnameadd+'&branddescription='+$scope.branddescriptionadd;				
				var formData=new FormData();
				formData.append("image",brandlogoadd.files[0]);				
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
					$scope.addbrand = data;
					
					if($scope.addbrand == 'Success'){
						$scope.brandSubmitError = 0;
						$scope.brandSubmitSuccess = 1;
						$scope.spin = 0;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.brandSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_brand';
						}, 2000);
					} else {
						$scope.brandSubmitSuccess = 0;
						$scope.brandSubmitError = 1;
						$scope.spin = 0;
						$scope.errorMsg = $scope.addbrand;						
					}	
					
				}).error(function(data, status, headers, config) {
					$scope.addbrand = "Response Fail";
					$scope.brandSubmitSuccess = 0;
					$scope.brandSubmitError = 1;
					$scope.spin = 0;
					$scope.errorMsg = $scope.addbrand;
				});
			}
		}
			
		$scope.getBrand = function(brandid) {
			for (i in $scope.getBrands) {
                if ($scope.getBrands[i].brandId == brandid) {
                	$scope.brandid = $scope.getBrands[i].brandId;
                	$scope.brandname = $scope.getBrands[i].brandName;
                	$scope.logo = $scope.getBrands[i].logo;
                	$scope.branddescription = $scope.getBrands[i].description;
                }
			}
		}	
		
		$scope.editBrand = function(brandid) {			
			if(!$scope.branddescription) {
				$scope.branddescription = "";
			}

			if(!$scope.brandname) {
				$scope.errorBrandName = 1;
				$scope.msgBrandName = "Please enter brand name";				
				document.getElementById("brandname").focus();				
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editBrand?brandid='+brandid+'&brandname='+$scope.brandname+'&logo='+$scope.logo+'&branddescription='+$scope.branddescription;				
				var formData=new FormData();
				formData.append("image",brandlogo.files[0]);				
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
					$scope.editbrand = data;
					$scope.spin = 0;
					
					if($scope.editbrand == 'Success'){						
						var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getBrands = data;
							$scope.brandSubmitError = 0;
							$scope.brandSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.brandSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
						}).error(function(data, status, headers, config) {
							$scope.getBrands = "Response Fail";
						});		
						
					} else {
						$scope.brandSubmitSuccess = 0;
						$scope.brandSubmitError = 1;
						$scope.errorMsg = $scope.editbrand;						
					}				
				}).error(function(data, status, headers, config) {
					$scope.editbrand = "Response Fail";
					$scope.spin = 0;
					$scope.brandSubmitSuccess = 0;
					$scope.brandSubmitError = 1;
					$scope.errorMsg = $scope.editbrand;
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;			
			angular.forEach($scope.getBrands, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteBrand = function() {
			angular.forEach($scope.getBrands, function(item) {
				if(item.selected) {
					var link = baseUrl+'deleteBrand?brandid='+item.brandId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletebrand = data;
					}).error(function(data, status, headers, config) {
						$scope.deletebrand = "Response Fail";
					});
				}
				var link = baseUrl+'getBrandsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBrands = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});
			});			
		}		
}]);