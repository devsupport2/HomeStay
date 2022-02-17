app.controller('subcategoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];	
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getSubcategories.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getCategories';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCategories = data;
		}).error(function(data, status, headers, config) {
			$scope.getCategories = "Response Fail";
		});
		
		var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getSubcategories = data;
		}).error(function(data, status, headers, config) {
			$scope.getSubcategories = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getSubcategories = data;
			}).error(function(data, status, headers, config) {
				$scope.getSubcategories = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getSubcategories';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
				});
			} else {
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
				});
			}
		}
		
		$scope.searchSubcategory = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchSubcategories?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
						});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCategoryId = 0;
			$scope.errorSubcategoryName = 0;
			$scope.errorSubcategoryCode = 0;
		}
		
		$scope.addSubcategory = function() {
			
			var valuex = document.getElementById("valuex").value;
			var valuey = document.getElementById("valuey").value;
			var valuew = document.getElementById("valuew").value;
			var valueh = document.getElementById("valueh").value;
			
			if(valuex == '') {
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

			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}

			if(!$scope.categorynameadd) {
				$scope.errorCategoryId = 1;
				$scope.msgCategoryId = "Please select category";				
				document.getElementById("categorynameadd").focus();				
			} else if(!$scope.subcategorynameadd) {
				$scope.errorSubcategoryName = 1;
				$scope.msgSubcategoryName = "Please enter subcategory name";			
				document.getElementById("subcategorynameadd").focus();				
			} else if(!$scope.subcategorycodeadd) {
				$scope.errorSubcategoryCode = 1;
				$scope.msgSubcategoryCode = "Please enter subcategory code";		
				document.getElementById("subcategorycodeadd").focus();				
			} else {				
				$scope.spin = 1;				
				var link = baseUrl+'addSubcategory?categoryname='+$scope.categorynameadd+'&subcategoryname='+$scope.subcategorynameadd+'&subcategorycode='+$scope.subcategorycodeadd+'&description='+$scope.descriptionadd+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;					
				var formData=new FormData();
				formData.append("image",imageadd.files[0]);					
				$http({ method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
					$scope.addsubcategory = data;								
					$scope.spin = 0;
					
					if($scope.addsubcategory == 'Success'){
						$scope.subcategorySubmitError = 0;
						$scope.subcategorySubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.subcategorySubmitSuccess = 0;
							$window.location.href = adminurl+'manage_subcategory';
						}, 2000);
					} else {
						$scope.subcategorySubmitSuccess = 0;
						$scope.subcategorySubmitError = 1;
						$scope.errorMsg = $scope.addsubcategory;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addsubcategory = "Response Fail";
					$scope.subcategorySubmitSuccess = 0;
					$scope.subcategorySubmitError = 1;
					$scope.spin = 0;
					$scope.errorMsg = $scope.addsubcategory;
				});			
			}
		}

		$scope.getSubcategory = function(subcategoryid) {
			for(i in $scope.getSubcategories) {
				if ($scope.getSubcategories[i].subcategoryId == subcategoryid) {
                	$scope.subcategoryid = $scope.getSubcategories[i].subcategoryId;
                	$scope.categoryname = $scope.getSubcategories[i].categoryId;
                	$scope.subcategoryname = $scope.getSubcategories[i].subcategoryName;
                	$scope.subcategorycode = $scope.getSubcategories[i].subcategoryCode;
                	$scope.subcategorycode1 = $scope.getSubcategories[i].subcategoryCode;
                	$scope.description = $scope.getSubcategories[i].description;
                	$scope.subcategoryimage = $scope.getSubcategories[i].image;
                }
			}
		}	
		
		$scope.editSubcategory = function(subcategoryid) {
			
			var valuex = document.getElementById("valuex1").value;
			var valuey = document.getElementById("valuey1").value;
			var valuew = document.getElementById("valuew1").value;
			var valueh = document.getElementById("valueh1").value;
			
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
			
			if(!$scope.description) {
				$scope.description = "";
			}

			if(!$scope.categoryname) {
				$scope.errorCategoryId = 1;
				$scope.msgCategoryId = "Please select category";				
				document.getElementById("categoryname").focus();				
			} else if(!$scope.subcategoryname) {
				$scope.errorSubcategoryName = 1;
				$scope.msgSubcategoryName = "Please enter subcategory name";			
				document.getElementById("subcategoryname").focus();				
			} else if(!$scope.subcategorycode) {
				$scope.errorSubcategoryCode = 1;
				$scope.msgSubcategoryCode = "Please enter subcategory code";		
				document.getElementById("subcategorycode").focus();				
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editSubcategory?subcategoryid='+subcategoryid+'&categoryname='+$scope.categoryname+'&subcategoryname='+$scope.subcategoryname+'&subcategorycode='+$scope.subcategorycode+'&description='+$scope.description+'&subcategoryimage='+$scope.subcategoryimage+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;				
				var formData=new FormData();
				formData.append("image",image.files[0]);				
				$http({ method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
					$scope.editsubcategory = data;
					
					if($scope.editsubcategory == 'Success') {						
						var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getSubcategories = data;
							$scope.subcategorySubmitError = 0;
							$scope.subcategorySubmitSuccess = 1;
							$scope.spin = 0;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.subcategorySubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
						}).error(function(data, status, headers, config) {
							$scope.getSubcategories = "Response Fail";
						});							
					} else {
						$scope.subcategorySubmitSuccess = 0;
						$scope.subcategorySubmitError = 1;
						$scope.errorMsg = $scope.editsubcategory;
						$scope.spin = 0;
					}				
				}).error(function(data, status, headers, config) {
					$scope.editsubcategory = data;
					$scope.subcategorySubmitSuccess = 0;
					$scope.subcategorySubmitError = 1;
					$scope.errorMsg = "Something went wrong!";
					$scope.spin = 0;
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getSubcategories, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}		
		
		$scope.deleteSubcategory = function() {
			angular.forEach($scope.getSubcategories, function(item) {
				if(item.selected) {
					var link = baseUrl+'deleteSubcategory?subcategoryid='+item.subcategoryId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletesubcategory = data;
					}).error(function(data, status, headers, config) {
						$scope.deletesubcategory = "Response Fail";
					});
				}
				var link = baseUrl+'getSubcategoriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategories = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getSubcategories = "Response Fail";
				});
			});
			
		}
		
		$scope.addCategory = function()
		{
			var categoryname = $scope.categorynameadd1;
			var categorycode = $scope.categorycodeadd1;
			var description = $scope.descriptionadd1;
			
			if(description==undefined || description=="")
			{
				description = "";
			}

			if(categoryname==undefined || categoryname=="")
			{
				document.getElementById("categorynameadd1").focus();
				$scope.infocategory = 1;
				$scope.messagecategory = "Please enter category name";
				$timeout(function(){
					$scope.infocategory = 0;
				}, 2000);
			}
			else if(categorycode==undefined || categorycode=="")
			{
				document.getElementById("categorycodeadd1").focus();
				$scope.infocategory = 1;
				$scope.messagecategory = "Please enter category Code";
				$timeout(function(){
					$scope.infocategory = 0;
				}, 2000);
			}
			else
			{
				var a = 0, b = 0;
				
				if(categorycode!=undefined || categorycode!="")
				{
					for(i in $scope.getCategories)
					{
						b = b + 1;
						if($scope.getCategories[i].categoryCode == categorycode)
						{
							a = 1;
							document.getElementById("categorycodeadd1").focus();
							$scope.infocategory = 1;
							$scope.messagecategory = "Category Code Already Exist";
							$timeout(function(){
								$scope.infocategory = 0;
							}, 2000);
						}
					}
				}
				
				if(a == 0 && $scope.getCategories.length == b)
				{
					$scope.spin = 1;
					
					var link = baseUrl+'addCategory?categoryname='+categoryname+'&categorycode='+categorycode+'&description='+description;
					
					var formData=new FormData();
					formData.append("image",imageadd1.files[0]);
					
					$http({
				        method: 'POST',
				        url: link,
				        headers: {'Content-Type': undefined},
				        data: formData,
				        transformRequest: function(data, headersGetterFunction)
				        {
				        	return data;
				        }
						})
						 .success(function(data, status)
						{
							$scope.addcategory = data;
							
							var link = baseUrl+'getCategories';
							$http.get(link).success(
								function(data, status, headers, config)
								{
									$scope.getCategories = data;
									
									$scope.spin = 0;
									$scope.successcategory = 1;
										
									$scope.messagecategory = "Category Added Successfully.";
										
									$timeout(function(){
										$scope.successcategory = 0;
										
										$scope.categorynameadd1 = "";
										$scope.categorycodeadd1 = "";
										$scope.imageadd1 = "";
										$scope.descriptionadd1 = "";
										
										$('#categoryModal').modal('hide');
									}, 2000);
								}).
								error(function(data, status, headers, config)
								{
									$scope.getCategories = "Response Fail";
								});
						}).
						error(function(data, status, headers, config)
						{
							$scope.addcategory = "Response Fail";
						});
				}
			}
		}
		
		$scope.getSubcategoryByCategoryId = function(categoryid)
		{
			var link = baseUrl+'getSubCategoriesByCategoryId?categoryid='+categoryid;
			$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getSubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubcategories = "Response Fail";
				});
		}
		
		$scope.redirectToProductPage = function(subcategoryid, subcategoryname)
		{
			var link = baseUrl+'productredirect?subcategoryid='+subcategoryid;
			$http.post(link).success(
					function(data, status, headers, config)
					{
						$window.location.href = url+"product?sname="+subcategoryname;
					}).
					error(function(data, status, headers, config)
					{
					});
		}
		
		
		
}]);