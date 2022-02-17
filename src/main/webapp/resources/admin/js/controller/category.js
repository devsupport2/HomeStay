app.controller('categoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
	
	$scope.currentPage = 0;
	$scope.pageSize = 20;
	$scope.search = '';
	$scope.startindex = $scope.currentPage;
	$scope.pages = [5, 10, 20, 50, 100, 'All'];
	$scope.info = 0;
	$scope.success = 0;
	$scope.spin = 0;  
	$scope.numberOfPages=function() {
		return Math.ceil($scope.getCategories.length/$scope.pageSize);
	}
    
	var baseUrl = $location.protocol()+"://"+location.host+url;

	var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
	}).error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});
	
	var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getCategories = data;
	}).error(function(data, status, headers, config) {
		$scope.getCategories = "Response Fail";
	});
	
	$scope.next = function() {
		$scope.search = '';
		if($scope.pageSize == "All") {
				
		} else {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;				
			var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCategories = data;
			}).error(function(data, status, headers, config) {
				$scope.getCategories = "Response Fail";
			});
		}
	}
	
	$scope.prev = function() {
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		
		var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCategories = data;
		}).error(function(data, status, headers, config) {
			$scope.getCategories = "Response Fail";
		});
	}
	
	$scope.changePage = function() {
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		
		if($scope.pageSize == "All") {
			var link = baseUrl+'getCategories';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCategories = data;
			}).error(function(data, status, headers, config) {
				$scope.getCategories = "Response Fail";
			});
		} else {
			var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCategories = data;
			}).error(function(data, status, headers, config) {
				$scope.getCategories = "Response Fail";
			});
		}
	}
	
	$scope.searchCategory = function() {
		var search = $scope.search;
		if(search == undefined || search == "") {
			var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCategories = data;
			}).error(function(data, status, headers, config) {
				$scope.getCategories = "Response Fail";
			});
		} else {
			var link = baseUrl+'searchCategories?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCategories = data;
			}).error(function(data, status, headers, config) {
				$scope.getCategories = "Response Fail";
			});
		}
	}	
	
	$scope.setFlag = function() {
		$scope.errorCategoryName = 0;
		$scope.errorCategoryCode = 0;
	}
	
	$scope.addCategory = function() {
		
		if(!$scope.descriptionadd) {
			$scope.descriptionadd = "";
		}
		
		if(!$scope.categorynameadd) {
			$scope.errorCategoryName = 1;
			$scope.msgCategoryName = "Please enter category name";			
			document.getElementById("categorynameadd").focus();			
		} else if(!$scope.categorycodeadd) {
			$scope.errorCategoryCode = 1;
			$scope.msgCategoryCode = "Please enter category code";		
			document.getElementById("categorycodeadd").focus();			
		} else {
			$scope.spin = 1;
			var link = baseUrl+'addCategory?categoryname='+$scope.categorynameadd+'&categorycode='+$scope.categorycodeadd+'&description='+$scope.descriptionadd;			
			var formData=new FormData();
			formData.append("image",imageadd.files[0]);					
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
				$scope.addcategory = data;
				$scope.spin = 0;
				if($scope.addcategory == 'Success'){
					$scope.categorySubmitError = 0;
					$scope.categorySubmitSuccess = 1;
					$scope.successMsg = "Data added";
					$timeout(function(){
						$scope.categorySubmitSuccess = 0;
						$window.location.href = adminurl+'manage_category';
					}, 2000);
				} else {
					$scope.categorySubmitSuccess = 0;
					$scope.categorySubmitError = 1;
					$scope.errorMsg = $scope.addcategory;						
				}				
			}).error(function(data, status, headers, config) {
				$scope.addcategory = "Response Fail";
				$scope.spin = 0;
				$scope.categorySubmitSuccess = 0;
				$scope.categorySubmitError = 1;
				$scope.errorMsg = "Something went wrong!";
			});
		
		}	
	}
	
	$scope.getCategory = function(categoryid) {
		for(i in $scope.getCategories) {
			if($scope.getCategories[i].categoryId == categoryid) {
				$scope.categoryid = $scope.getCategories[i].categoryId;
               	$scope.categoryname = $scope.getCategories[i].categoryName;
               	$scope.categorycode = $scope.getCategories[i].categoryCode;               	
               	$scope.description = $scope.getCategories[i].description;
               	$scope.image1 = $scope.getCategories[i].image;
            }
		}
	}
	
	$scope.editCategory = function(categoryid) {
		if(!$scope.description) {
			$scope.description = "";
		}
		
		if(!$scope.categoryname) {
			$scope.errorCategoryName = 1;
			$scope.msgCategoryName = "Please enter category name";			
			document.getElementById("categoryname").focus();			
		} else if(!$scope.categorycode) {
			$scope.errorCategoryCode = 1;
			$scope.msgCategoryCode = "Please enter category code";		
			document.getElementById("categorycode").focus();			
		} else {
			$scope.spin = 1;
			var link = baseUrl+'editCategory?categoryid='+categoryid+'&categoryname='+$scope.categoryname+'&categorycode='+$scope.categorycode+'&description='+$scope.description+'&categoryimage='+$scope.image1;
			var formData=new FormData();
			formData.append("image",image.files[0]);
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
				$scope.editcategory = data;
				$scope.spin = 0;
				
				if($scope.editcategory == 'Success'){					
					var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getCategories = data;
						$scope.categorySubmitError = 0;
						$scope.categorySubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.categorySubmitSuccess = 0;
							$('#editModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.getCategories = "Response Fail";
					});		
					
				} else {
					$scope.categorySubmitSuccess = 0;
					$scope.categorySubmitError = 1;
					$scope.errorMsg = $scope.editcategory;						
				}				
			}).error(function(data, status, headers, config) {
				$scope.editcategory = "Response Fail";
				$scope.spin = 0;
				$scope.categorySubmitSuccess = 0;
				$scope.categorySubmitError = 1;
				$scope.errorMsg = "Something went wrong!";
			});
		}		
	}
	
	$scope.checkRecordSelectForDelete = function() {			
		$scope.d = 0;		
		angular.forEach($scope.getCategories, function(item) {
			if (item.selected) {
				$scope.d = $scope.d + 1
			}
		});			
	}
	
	$scope.deleteCategory = function() {
		angular.forEach($scope.getCategories, function(item) {
			if(item.selected) {
				var link = baseUrl+'deleteCategory?categoryid='+item.categoryId;
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deletecategory = data;
				}).error(function(data, status, headers, config) {
					$scope.deletecategory = "Response Fail";
				});
			}
		});
		var link = baseUrl+'getCategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCategories = data;
			$('#deleteModal').modal('hide');
		}).error(function(data, status, headers, config) {
			$scope.getCategories = "Response Fail";
		});
	}
	
}]);