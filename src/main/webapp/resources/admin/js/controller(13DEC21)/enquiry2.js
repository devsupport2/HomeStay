app.controller('enquiryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
	$scope.currentPage = 0;
	$scope.pageSize = 'All';
	$scope.search = '';
	$scope.startindex = $scope.currentPage;   
    $scope.pages = [5, 10, 20, 50, 100, 'All'];	
	$scope.info = 0;
	$scope.success = 0;
	$scope.spin = 0;
	$scope.currentdate = new Date();
	$scope.todyadate = new Date();

	$scope.numberOfPages=function() {
		return Math.ceil($scope.getEnquiries.length/$scope.pageSize);
	}

	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
	}).error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});	
	
	if($scope.pageSize == "All") {
		var link = baseUrl+'getAllOpenEnquiries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEnquiries = data;
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	} else {
		var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEnquiries = data;
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	}
	
	var link = baseUrl+'getTodayFollowupEnquiries';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.todayFollowupEnquiries = data;
	}).error(function(data, status, headers, config) {
		$scope.todayFollowupEnquiries = "Response Fail";
	});
	
	$scope.fromdate = $scope.todyadate.setDate($scope.todyadate.getDate()+1);
	$scope.fromdate = $filter('date')(new Date($scope.fromdate), "dd/MM/yyyy");
	$scope.todate = $scope.todyadate.setDate($scope.todyadate.getDate() + 20);
	$scope.todate = $filter('date')(new Date($scope.todate), "dd/MM/yyyy");
	
	
	var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
	$http.get(link).success(function(data, status, headers, config) {
		$scope.followupEnquiriesByDate = data;
	}).error(function(data, status, headers, config) {
		$scope.followupEnquiriesByDate = "Response Fail";
	});
	
	$scope.getEnquiryFollowupsByDate = function(){
		$scope.fromdate = document.getElementById("fromdate").value;
		$scope.todate = document.getElementById("todate").value;
		var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.followupEnquiriesByDate = data;
		}).error(function(data, status, headers, config) {
			$scope.followupEnquiriesByDate = "Response Fail";
		});
	}
	
	var link = baseUrl+'getUserTypes';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getUserTypes = data;
	}).error(function(data, status, headers, config) {
		$scope.getUserTypes = "Response Fail";
	});
	
	var link = baseUrl+'getClientAndProspectName';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.clientAndProspectName = data;
	}).error(function(data, status, headers, config) {
		$scope.clientAndProspectName = "Response Fail";
	});
	
	var link = baseUrl+'getEmployees';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getEmployees = data;
	}).error(function(data, status, headers, config) {
		$scope.getEmployees = "Response Fail";
	});
	
	var link = baseUrl+'getAllEnquiryProductName';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allEnquiryProducts = data;
	}).error(function(data, status, headers, config) {
		$scope.allEnquiryProducts = "Response Fail";
	});	
	
	var link = baseUrl+'getAllQuotation';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allQuotationsForDisplay = data;
	}).error(function(data, status, headers, config) {
		$scope.allQuotationsForDisplay = "Response Fail";
	});
	
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
	
	var link = baseUrl+'getSuppliers';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getSuppliers = data;
	}).error(function(data, status, headers, config) {
		$scope.getSuppliers = "Response Fail";
	});
	
	var link = baseUrl+'getPriceType';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getPriceType = data;
	}).error(function(data, status, headers, config) {
		$scope.getPriceType = "Response Fail";
	});	
	
	$scope.getUserByUserType = function(usertypeid) {		
		var link = baseUrl+'getUserNameByUserType?usertypeid='+usertypeid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserNameByUserType = data;
		}).error(function(data, status, headers, config) {
			$scope.getUserNameByUserType = "Response Fail";
		});		
	}
	
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
	
	var link = baseUrl+'getProducts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getProducts = data;
	}).error(function(data, status, headers, config) {
		$scope.getProducts = "Response Fail";
	});
	
	var link = baseUrl+'getStatusReason';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getStatusReason = data;		
	}).error(function(data, status, headers, config) {
		$scope.getStatusReason = "Response Fail";
	});	
	
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
	
	$scope.checkEmailAddress = function() {
		var email = $scope.emailadd;			
		if(email == "" || email == undefined){
		} else {
			for(i in $scope.getUsers) {
				if($scope.getUsers[i].email == email) {
					document.getElementById("emailadd").focus();
					$scope.emailadd = "";
					$scope.infouser = 1;
					$scope.messageuser = "Email address already registered with us";
					$timeout(function(){
						$scope.info = 0;
					}, 2000);
					
				}
			}
		}
	}
	
	$scope.next = function() {
		$scope.search = '';
		if($scope.pageSize == "All") {
			
		} else {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
				
			var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
	}
	
	$scope.prev = function() {
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEnquiries = data;
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	}
	
	$scope.changeStatus = function() {		
		if($scope.enquirystatus) {
			var link = baseUrl+'getEnquiriesByStatus?status='+$scope.enquirystatus;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		} else {
			if(!$scope.enquirystatus) {
				var link = baseUrl+'getAllOpenEnquiries';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getEnquiries = data;
				}).error(function(data, status, headers, config) {
					$scope.getEnquiries = "Response Fail";
				});
			}
		}				
	}
	
	$scope.changePage = function() {
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		if($scope.pageSize == "All") {
			var link = baseUrl+'getAllOpenEnquiries';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		} else {
			var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
	}
	
	$scope.searchEnquiry = function() {
		var search = $scope.search;
		if(search == undefined || search == "") {
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllOpenEnquiries';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getEnquiries = data;
				}).error(function(data, status, headers, config) {
					$scope.getEnquiries = "Response Fail";
				});
			} else {
				var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getEnquiries = data;
				}).error(function(data, status, headers, config) {
					$scope.getEnquiries = "Response Fail";
				});
			}
		} else {
			var link = baseUrl+'searchEnquiry?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
	}
	
	$scope.setFlag = function() {
		$scope.errorStatusReason = 0;
		$scope.errorEnquiryLog = 0;
		$scope.errorEnquiryStatus = 0;
		$scope.errorUserType = 0;
		$scope.errorCategoryName = 0;
		$scope.errorRecurring = 0;
		$scope.errorProductName = 0;
		$scope.errorUnit = 0;
		$scope.errorSpecification = 0;
		$scope.errorSupplier = 0;
		$scope.errorPriceType = 0;
		$scope.errorPrice = 0;
		$scope.errorCurrency = 0;		
		$scope.errorCompanyName = 0;
		$scope.errorEmail = 0;
		$scope.errorFollowupEmpName = 0;
		$scope.errorLogEmpName = 0;
		$scope.errorFollowupMember = 0;
		$scope.errorLogMember = 0;
	}
	
	$scope.setShowTable = function() {
		$scope.showTable = "y";
	}
	$scope.setProduct = function(productid) {
		var link = baseUrl+'getProductDetailById?productid='+productid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProductDetailById = data;
			if(productid != undefined){
				$scope.productlist.push({'productId':$scope.getProductDetailById.productId, 'product':$scope.getProductDetailById.productName});
				$scope.searchproduct = undefined;
				$scope.showTable = "n";
			}			
		}).error(function(data, status, headers, config) {
			$scope.getProductDetailById = "Response Fail";
		});
	}
	
	$scope.productlist = [];
	$scope.addProductRow = function() {		
		$scope.productId = 0;
		if($scope.searchproduct==undefined || $scope.searchproduct=="") {
			document.getElementById("searchproduct").focus();
			$scope.productinfo = 1;
			$scope.productmessage = "Please enter product!";
			$timeout(function(){
				$scope.productinfo = 0;
			}, 2000);
		} else {
			$scope.productlist.push({'productId':$scope.productId, 'product':$scope.searchproduct});
			document.getElementById("searchproduct").value = "";
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
	
	$scope.urllist = [];
	$scope.addUrlRow = function() {
		if($scope.urladd==undefined || $scope.urladd=="") {
			document.getElementById("urladd").focus();
			$scope.urlinfo = 1;
			$scope.urlmessage = "Please enter url!";
			$timeout(function(){
				$scope.urlinfo = 0;
			}, 2000);
		} else {
			$scope.urllist.push({ 'url':$scope.urladd});
			document.getElementById("urladd").value = "";
		}
	}

	$scope.removeUrlRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.urllist.length; i++) {
			if($scope.urllist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing url..Please try again!");
			return;
		}
		$scope.urllist.splice(index, 1);
	};
	
	$scope.assignlist = [];
	$scope.addAssignRow = function() {
		if($scope.employeeidadd==undefined || $scope.employeeidadd=="") {			
			$scope.errorEmpName = 1;
			$scope.msgEmpName = "Please select an employee!";
			document.getElementById("employeeidadd").focus();
		} else {
			
			var link = baseUrl+'getUserDetailById?userid='+$scope.employeeidadd;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;			
            	
            	$scope.firstname = $scope.userDetailById.firstName;            	
            	$scope.lastname = $scope.userDetailById.lastName;
            	
            	$scope.assignlist.push({'userId':$scope.employeeidadd, 'firstName':$scope.firstname, 'lastName':$scope.lastname});
    			$scope.employeeidadd = "";
    			
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});			
		}
	}

	$scope.removeAssignRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.assignlist.length; i++) {
			if($scope.assignlist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record..Please try again!");
			return;
		}
		$scope.assignlist.splice(index, 1);
	};
	
	$scope.filelist = [{}];
	var formData = new FormData();
	$scope.addFileRow = function() {		
		if($scope.filetitleadd==undefined) {
			document.getElementById("filetitleadd").focus();
			$scope.fileinfo = 1;
			$scope.filemessage = "Please enter file title!";
			$timeout(function(){
				$scope.fileinfo = 0;
			}, 2000);
		} else {
			$scope.filelist.push({'fileTitle' : $scope.filetitleadd});
			formData.append("fileadd",fileadd.files[0]);
			document.getElementById("filetitleadd").value = "";
		}				
	};

	$scope.removeFileRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.filelist.length; i++) {
			if($scope.filelist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing file..Please try again!");
			return;
		}
		$scope.filelist.splice(index, 1);
	};
	
	$scope.addEnquiry = function() {
		
		$scope.enquirydate = document.getElementById("datepicker").value;		
		$scope.employeeidadd = 0;
		$scope.clientidadd = document.getElementById("clientidadd").value;		
		
		if(!$scope.remarksadd) {
			$scope.remarksadd= "";
		}
		
		if(!$scope.referenceidadd) {
			$scope.referenceidadd= 0;
		}
		
		if($scope.enquirydate==undefined || $scope.enquirydate=="") {
			document.getElementById("enquirydate").focus();
			$scope.info = 1;
			$scope.message = "Please select enquiry date!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else if($scope.enquiryviaadd==undefined || $scope.enquiryviaadd=="") {
			document.getElementById("enquiryviaadd").focus();
			$scope.info = 1;
			$scope.message = "Please select enquiry via!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else if($scope.clientidadd==undefined || $scope.clientidadd=="") {
			document.getElementById("clientidadd").focus();
			$scope.info = 1;
			$scope.message = "Please select client!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else {
			var a = 0, b = 0, c = 0, d = 0;
			$scope.filetitlelist = [];
			angular.forEach($scope.filelist,function(item) {
				$scope.filetitlelist.push(item.fileTitle);				
				c = c + 1;
			});
			$scope.spin = 1;			
			var link = baseUrl+'addEnquiry?enquirydate='+$scope.enquirydate+'&enquiryvia='+$scope.enquiryviaadd+'&referenceid='+$scope.referenceidadd+'&clientid='+$scope.clientidadd+'&employeeid='+$scope.employeeidadd+'&remark='+$scope.remarksadd;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addenquiry = data;				
				if($scope.productlist.length == a && $scope.urllist.length == b && $scope.filelist.length == c && $scope.assignlist.length == d) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
				angular.forEach($scope.productlist,function(item) {	
					if(item.selected == true)
						item.selected = 'y';
					else
						item.selected = 'n';
					
					var link = baseUrl+'addEnquiryProduct?productid='+item.productId+'&product='+item.product+'&readytoinvoice='+item.selected;					
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addenquiryproduct = data;
						a = a + 1;
						if($scope.productlist.length == a && $scope.urllist.length == b && $scope.filelist.length == c && $scope.assignlist.length == d) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
					}).error(function(data, status, headers, config) {
						$scope.addenquiryproduct = "Response Fail";
					});
				});
				
				angular.forEach($scope.urllist,function(item) {												
					var link = baseUrl+'addEnquiryUrl?url='+item.url;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addenquiryurl = data;
						b = b + 1;
						if($scope.productlist.length == a && $scope.urllist.length == b && $scope.filelist.length == c && $scope.assignlist.length == d) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
					}).error(function(data, status, headers, config) {
						$scope.addenquiryurl = "Response Fail";
					});
				});
				
				angular.forEach($scope.assignlist,function(item) {												
					var link = baseUrl+'addEnquiryAssign?userid='+item.userId;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addenquiryassign = data;
						d = d + 1;
						if($scope.productlist.length == a && $scope.urllist.length == b && $scope.filelist.length == c && $scope.assignlist.length == d) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
					}).error(function(data, status, headers, config) {
						$scope.addenquiryassign = "Response Fail";
					});
				});
				
				if ($scope.filetitlelist.length > 1) {
					var link = baseUrl+ 'addEnquiryFiles?filetitle='+ $scope.filetitlelist;
					$http({method : 'POST',
						url : link,
						headers : {'Content-Type' : undefined},
						data : formData,transformRequest : function(data,headersGetterFunction) {					
							return data;
						}
					}).success(function(data,status) {
						$scope.addenquiryfiles = data;																		
						if($scope.productlist.length == a && $scope.urllist.length == b && $scope.filelist.length == c && $scope.assignlist.length == d) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
					}).error(function(data,status,headers,config) {
						$scope.addenquiryfiles = "Response Fail";
					});
				}
				
				var link = baseUrl+'sendEnquiryEmailandMessage';
    			$http.post(link).success(function(data, status, headers, config) {
    				$scope.sendEnqnotification = data;			    					
    			}).error(function(data, status, headers, config) {
    				$scope.sendEnqnotification = "Response Fail";
    			});
			}).error(function(data, status, headers, config) {
				$scope.addenquiry = "Response Fail";
			});
		}
	}
	
	$scope.getEnquiryDetail = function(enquiryid) {
		
		var link = baseUrl+'getEnquiryDetailsById?enquiryid='+enquiryid;    	
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEnquiryDetailById = data;        			
			
			$scope.enquiryid = $scope.getEnquiryDetailById.enquiryId;
        	//$scope.userid = $scope.getEnquiryDetailById.referenceId;
			$scope.userid = $scope.getEnquiryDetailById.clientId;
			console.log($scope.userid);
        	if($scope.userid != 0){
        		var link = baseUrl+'getUserDetailById?userid='+$scope.userid;        	
        		$http.get(link).success(function(data, status, headers, config) {
        			$scope.getUserDetailById = data;        			
        			$scope.usertypeid = $scope.getUserDetailById.userTypeId;
        			
        			var link = baseUrl+'getUserNameByUserType?usertypeid='+$scope.usertypeid;
        			$http.get(link).success(function(data, status, headers, config) {
        				$scope.getUserNameByUserType = data;
        				$scope.referenceid = $scope.userid;
        			}).error(function(data, status, headers, config) {
        				$scope.getUserNameByUserType = "Response Fail";
        			});
        			
        		}).error(function(data, status, headers, config) {
        			$scope.getUserDetailById = "Response Fail";
        		});
        	} else {
        		$scope.usertypeid = "";
        		$scope.referenceid = "";
        	}       	
    		
        	$scope.clientid = $scope.getEnquiryDetailById.clientId;        	
        	$scope.enquirydate = $scope.getEnquiryDetailById.enquiryDate;
        	$scope.enquiryvia = $scope.getEnquiryDetailById.enquiryVia;
        	$scope.remarks = $scope.getEnquiryDetailById.enquriRemarks;
        	
        	var link = baseUrl+'getEnquiryProductsByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getproductlist = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getproductlist = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryUrlsByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.geturllist = data;
    		}).error(function(data, status, headers, config) {
    			$scope.geturllist = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryAssignByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryFilesByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getfilelist = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getfilelist = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryLogByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getEnquiryLog = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getEnquiryLog = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryFollowupByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getEnquiryFollowup = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getEnquiryFollowup = "Response Fail";
    		});
    		
			
		}).error(function(data, status, headers, config) {
			$scope.getEnquiryDetailById = "Response Fail";
		});	
	}
	
	$scope.setProductEdit = function(productid) {
		var link = baseUrl+'getProductDetailById?productid='+productid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProductDetailById = data;
			if(productid != undefined){
				$scope.getproductlist.push({'productId':$scope.getProductDetailById.productId, 'productDetail':$scope.getProductDetailById.productName+'-'+$scope.getProductDetailById.partNumber});
				$scope.searchproductedit = undefined;
				$scope.showTable = "n";
			}			
		}).error(function(data, status, headers, config) {
			$scope.getProductDetailById = "Response Fail";
		});
	}	
	
	$scope.addProductRowEdit = function() {		
		$scope.productId = 0;
		if($scope.searchproductedit==undefined || $scope.searchproductedit=="") {
			document.getElementById("searchproductedit").focus();
			$scope.productinfo = 1;
			$scope.productmessage = "Please enter product!";
			$timeout(function(){
				$scope.productinfo = 0;
			}, 2000);
		} else {
			$scope.getproductlist.push({'productId':$scope.productId, 'productDetail':$scope.searchproductedit});
			document.getElementById("searchproductedit").value = "";
		}
	}

	$scope.removeProductRowEdit = function(item) {
		var index = -1;
		for(var i=0; i<$scope.getproductlist.length; i++){
			if($scope.getproductlist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0){
			$window.alert("Error while removing product..Please try again!");
			return;
		}
		$scope.getproductlist.splice(index, 1);
	};	
	
	$scope.addUrlRowEdit = function() {
		if($scope.url == undefined || $scope.url == "") {
			document.getElementById("url").focus();
			$scope.urlinfo = 1;
			$scope.urlmessage = "Please enter url!";
			$timeout(function(){
				$scope.urlinfo = 0;
			}, 2000);
		} else {
			$scope.geturllist.push({ 'url':$scope.url});
			document.getElementById("url").value = "";
		}
	}

	$scope.removeUrlRowEdit = function(item) {
		var index = -1;
		for(var i=0; i<$scope.geturllist.length; i++) {
			if($scope.geturllist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing url..Please try again!");
			return;
		}
		$scope.geturllist.splice(index, 1);
	};
	
	$scope.newfilelist = [{}];
	var formData1 = new FormData();
	$scope.addFileRowEdit = function() {		
		if($scope.filetitle==undefined) {
			document.getElementById("filetitle").focus();
			$scope.fileinfo = 1;
			$scope.filemessage = "Please enter file title!";
			$timeout(function(){
				$scope.fileinfo = 0;
			}, 2000);
		} else {
			$scope.newfilelist.push({'fileTitle' : $scope.filetitle});
			formData1.append("file",file.files[0]);
			document.getElementById("filetitle").value = "";
		}				
	};

	$scope.removeFileRowEdit = function(item) {
		var index = -1;
		for(var i=0; i<$scope.newfilelist.length; i++) {
			if($scope.newfilelist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing file..Please try again!");
			return;
		}
		$scope.newfilelist.splice(index, 1);
	};
	
	$scope.removeFileRowEdit1 = function(item) {
		var index = -1;
		for(var i=0; i<$scope.getfilelist.length; i++) {
			if($scope.getfilelist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing file..Please try again!");
			return;
		}
		$scope.getfilelist.splice(index, 1);
	};
	
	$scope.editEnquiry = function(enquiryid) {		
		$scope.enquirydate = document.getElementById("datepicker1").value;
		$scope.employeeid = 0;
		
		if($scope.remarks==undefined) {
			$scope.remarks= "";
		}
		if($scope.referenceid==undefined || $scope.referenceid=="") {
			$scope.referenceid=0;
		}
		if($scope.enquirydate==undefined) {
			document.getElementById("datepicker1").focus();
			$scope.info = 1;
			$scope.message = "Please select enquiry date!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else if($scope.enquiryvia==undefined) {
			document.getElementById("enquiryvia").focus();
			$scope.info = 1;
			$scope.message = "Please select enquiry via!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else if($scope.clientid==undefined) {
			document.getElementById("clientid").focus();
			$scope.info = 1;
			$scope.message = "Please select client!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else {
			var a = 0, b = 0, c = 0;
			$scope.newfiletitlelist = [];
			angular.forEach($scope.newfilelist,function(item) {
				$scope.newfiletitlelist.push(item.fileTitle);				
			});					
			
			var link = baseUrl+'deleteEnquiryProduct?enquiryid='+enquiryid;
			$http['delete'](link).success(function(data, status,headers, config) {
				$scope.deleteenquiryproduct = data;
			}).error(function(data, status,headers, config) {
				$scope.deleteenquiryproduct = "Response Fail";
			});
			
			var link = baseUrl+'deleteEnquiryUrl?enquiryid='+enquiryid;
			$http['delete'](link).success(function(data, status,headers, config) {
				$scope.deleteenquiryurl = data;
			}).error(function(data, status,headers, config) {
				$scope.deleteenquiryurl = "Response Fail";
			});
			
			var link = baseUrl+'deleteEnquiryFile?enquiryid='+enquiryid;
			$http['delete'](link).success(function(data, status,headers, config) {
				$scope.deleteenquiryfile = data;
			}).error(function(data, status,headers, config) {
				$scope.deleteenquiryfile = "Response Fail";
			});		
			
			$scope.spin = 1;			
			var link = baseUrl+'editEnquiry?enquiryid='+enquiryid+'&enquirydate='+$scope.enquirydate+'&enquiryvia='+$scope.enquiryvia+'&referenceid='+$scope.referenceid+'&clientid='+$scope.clientid+'&employeeid='+$scope.employeeid+'&remark='+$scope.remarks;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editenquiry = data;				
				var link = baseUrl+'editNewFileList?enquiryid='+enquiryid+'&filetitle='+$scope.newfiletitlelist;
				$http({method: 'POST',
						url: link,
						headers: {'Content-Type': undefined},
						data: formData1,
						transformRequest: function(data, headersGetterFunction) {
				        	return data;
				        }
					}).success(function(data, status) {
						$scope.editnewfilelist = data;
						
						angular.forEach($scope.getfilelist,function(item) {
							if (item.fileTitle != null) {
								var link = baseUrl+'editFileList?enquiryid='+enquiryid+'&filetitle='+item.fileTitle+'&filepath='+item.filePath;								
								$http.post(link).success(function(data,status,headers,config) {									
									$scope.editdocumentlist = data;	
									c = c + 1;
								}).error(function(data,status,headers,config) {
									$scope.editdocumentlist = "Response Fail";
								});
							}
						});
						
						angular.forEach($scope.getproductlist,function(item) {	
							if(item.selected == true)
								item.selected = 'y';
							else
								item.selected = 'n';
							
							var link = baseUrl+'editEnquiryProduct?enquiryid='+enquiryid+'&productid='+item.productId+'&product='+item.productDetail+'&readytoinvoice='+item.selected;							
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editenquiryproduct = data;
								a = a + 1;
								if($scope.editenquiry == "Success" && $scope.getproductlist.length == a && $scope.geturllist.length == b && $scope.getfilelist.length == c) {
									$scope.spin = 0;
									$scope.success = 1;
									$scope.message = "Enquiry Updated Successfully.";
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_enquiry';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.editenquiryproduct = "Response Fail";
							});
						});
						
						angular.forEach($scope.geturllist,function(item) {												
							var link = baseUrl+'editEnquiryUrl?enquiryid='+enquiryid+'&url='+item.url;							
							$http.post(link).success(function(data, status, headers, config) {
								$scope.editenquiryurl = data;
								b = b + 1;								
								if($scope.editenquiry == "Success" && $scope.getproductlist.length == a && $scope.geturllist.length == b && $scope.getfilelist.length == c) {
									$scope.spin = 0;
									$scope.success = 1;
									$scope.message = "Enquiry Updated Successfully.";
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'manage_enquiry';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.editenquiryurl = "Response Fail";
							});
						});						
					}).error(function(data, status, headers, config) {
						$scope.editnewfilelist = "Response Fail";
					});							
			}).error(function(data, status, headers, config) {
				$scope.editenquiry = "Response Fail";
			});				
		}
	}
	
	$scope.addAssignRowEdit = function(enquiryid) {
		if($scope.employeeid==undefined || $scope.employeeid=="") {			
			$scope.errorEmpName = 1;
			$scope.msgEmpName = "Please select an employee!";
			document.getElementById("employeeid").focus();
		} else {
			$scope.spinEmp = 1;
			
			var link = baseUrl+'editEnquiryAssign?enquiryid='+enquiryid+'&userid='+$scope.employeeid;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editenquiryassign = data;												
				if($scope.editenquiryassign == "Success") {
					var link = baseUrl+'getEnquiryAssignByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getenquiryassignlist = data;		    			
		    			$scope.spinEmp = 0;
		    		}).error(function(data, status, headers, config) {
		    			$scope.getenquiryassignlist = "Response Fail";
		    		});											
				}
			}).error(function(data, status, headers, config) {
				$scope.editenquiryassign = "Response Fail";
			});
						
		}
	}
	
	$scope.removeAssignRowEdit = function(id, enquiryid) {
		var link = baseUrl+'deleteEnquiryAssignRow?id='+id;		
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deleteenquiryassign = data;
			var link = baseUrl+'getEnquiryAssignByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = data;  			
    		}).error(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = "Response Fail";
    		});
		}).error(function(data, status, headers, config) {
			$scope.deleteenquiryassign = "Response Fail";
		});
	}
	
	
	$scope.checkRecordSelectForDelete = function() {
		$scope.d = 0;		
		angular.forEach($scope.getEnquiries, function(item) {
			if (item.selected) {
				$scope.d = $scope.d + 1
			}
		});
	}
	$scope.deleteEnquiry = function() {
		angular.forEach($scope.getEnquiries, function(item) {
			if (item.selected) {
				var link = baseUrl+'deleteEnquiry?enquiryid='+item.enquiryId;
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deleteenquiry = data;
				}).error(function(data, status, headers, config) {
					$scope.deleteenquiry = "Response Fail";
				});
			}
		});
		$window.location.href = adminurl+'manage_enquiry';
	}
	
	$scope.addUser = function() {		
		var companyname = $scope.companynameadd;
		var firstname = $scope.firstnameadd;		
		var lastname = $scope.lastnameadd;
		var usertypename = $scope.usertypename;		
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
		if(password==undefined || password=="") {
			password = "";
		}
		if(!$scope.codeadd) {
			$scope.codeadd = 0;
		}
		if(!$scope.hourlywagesadd) {
			$scope.hourlywagesadd = 0;
		}
		if(!$scope.overtimewagesadd) {
			$scope.overtimewagesadd = 0;
		}
		if(!companyname) {
			companyname = "";
		}
		if(!email) {
			email = "";
		}
		
		/*if(!companyname && !firstname) {				
			$scope.errorCompanyName = 1;
			$scope.msgCompanyName = "Please enter company name or first name";
			document.getElementById("companynameadd").focus();
		} else*/ if(usertypename==undefined || usertypename=="") {				
			$scope.errorUserType = 1;
			$scope.msgUserType = "Please select user type";
			document.getElementById("usertypename").focus();
		} else /*if(!email) {				
			$scope.errorEmail = 1;
			$scope.msgEmail = "Please enter client email";
			document.getElementById("emailadd").focus();
		} else*/ {
			$scope.userspin = 1;		
			var link = baseUrl+'addUser?companyname='+companyname+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&usertypename='+usertypename+'&gender='+gender+'&dateofbirth='+dateofbirth+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&landlinenumber='+landlinenumber+'&email='+email+'&password='+password+'&code='+$scope.codeadd+'&hourlywages='+$scope.hourlywagesadd+'&overtimewages='+$scope.overtimewagesadd;			
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
				
				if($scope.adduser == 'Success'){
					
					var link = baseUrl+'getUserNameByUserType?usertypeid='+usertypename;					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getUserNameByUserType = data;
						
						$scope.userspin = 0;
						$scope.userSuccess = 1;
						$scope.userMsg = "User Added Successfully.";
						$timeout(function(){
							$scope.userSuccess = 0;
							$('#userModal').modal('hide');
							document.getElementById("AddUserForm").reset();
						}, 2000);
						
					}).error(function(data, status, headers, config) {
						$scope.getUserNameByUserType = "Response Fail";
					});					
					
				} else {
					$scope.userspin = 0;
					$scope.userError = 1;
					$scope.userMsg = $scope.adduser;
					$timeout(function(){
						$scope.userError = 0;				
					}, 2000);
				}						
				
			}).error(function(data, status, headers, config) {
				$scope.userspin = 0;
				$scope.userError = 1;
				$scope.userMsg = "Something went wrong! Please try later!";
				$timeout(function(){
					$scope.userError = 0;				
				}, 2000);
			});			
		}
	}
	
	$scope.addClient = function() {		
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
		if(password==undefined || password=="") {
			password = "";
		}
		if(!$scope.codeadd) {
			$scope.codeadd = 0;
		}
		if(!$scope.hourlywagesadd) {
			$scope.hourlywagesadd = 0;
		}
		if(!$scope.overtimewagesadd) {
			$scope.overtimewagesadd = 0;
		}
		
		if(!companyname && !firstname) {				
			$scope.errorCompanyName = 1;
			$scope.msgCompanyName = "Please enter company name or first name";
			document.getElementById("companynameadd").focus();
		} else if(usertypename==undefined || usertypename=="") {				
			$scope.errorUserType = 1;
			$scope.msgUserType = "Please select user type";
			document.getElementById("usertypenameadd").focus();
		} else if(!email) {				
			$scope.errorEmail = 1;
			$scope.msgEmail = "Please enter client email";
			document.getElementById("emailadd").focus();
		} else {
			$scope.userspin = 1;		
			var link = baseUrl+'addUser?companyname='+companyname+'&firstname='+firstname+'&middlename='+middlename+'&lastname='+lastname+'&usertypename='+usertypename+'&gender='+gender+'&dateofbirth='+dateofbirth+'&aadharnumber='+aadharnumber+'&passportnumber='+passportnumber+'&pannumber='+pannumber+'&address1='+address1+'&address2='+address2+'&address3='+address3+'&statename='+statename+'&cityname='+cityname+'&pincode='+pincode+'&mobilenumber='+mobilenumber+'&landlinenumber='+landlinenumber+'&email='+email+'&password='+password+'&code='+$scope.codeadd+'&hourlywages='+$scope.hourlywagesadd+'&overtimewages='+$scope.overtimewagesadd;			
			var formData=new FormData();
			formData.append("profilepicture",profilepicture.files[0]);					
			$http({method: 'POST',
				url: link,				
		        headers: {'Content-Type': undefined},
		        data: formData,
		        transformRequest: function(data, headersGetterFunction) {
		        	return data;
		        }
			}).success(function(data, status) {
				$scope.adduser = data;
				
				if($scope.adduser == 'Success'){
					var link = baseUrl+'getClientAndProspectName';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.clientAndProspectName = data;
													
						$scope.userspin = 0;
						$scope.userSuccess = 1;
						$scope.userMsg = "Client Added Successfully.";
						$timeout(function(){
							$scope.userSuccess = 0;									
							$('#clientModal').modal('hide');
							document.getElementById("AddClientForm").reset();
						}, 2000);				
						
					}).error(function(data, status, headers, config) {
						$scope.clientAndProspectName = "Response Fail";
					});
				} else {
					$scope.userspin = 0;
					$scope.userError = 1;
					$scope.userMsg = $scope.adduser;
					$timeout(function(){
						$scope.userError = 0;				
					}, 2000);
				}						
				
			}).error(function(data, status, headers, config) {
				$scope.userspin = 0;
				$scope.userError = 1;
				$scope.userMsg = "Something went wrong! Please try later!";
				$timeout(function(){
					$scope.userError = 0;				
				}, 2000);
			});			
		}
	}
	
	$scope.goToGenerateQuotation = function(enquiryid) {
		$window.location.href = adminurl+'generate_quotation?enquiryid='+enquiryid;
	}
	
	$scope.addEnquiryLog = function(enquiryid) {
		$scope.logdatetime = document.getElementById("datetimepicker").value;
		
		if (document.getElementById("notifylogviaemail").checked == true)
			$scope.notifylogviaemail = 'Yes';
		if (document.getElementById("notifylogviaemail").checked == false)
			$scope.notifylogviaemail = 'No';
		if (document.getElementById("notifylogviasms").checked == true)
			$scope.notifylogviasms = 'Yes';
		if (document.getElementById("notifylogviasms").checked == false)
			$scope.notifylogviasms = 'No';
		
		 if(!$scope.enquirylog){			
			$scope.errorEnquiryLog = 1;
			$scope.msgEnquiryLog = "Please enter your remark";
			document.getElementById("enquirylog").focus();
		} else if($scope.logmemberlist.length==0 && $scope.notifylogviaemail=='Yes' || $scope.logmemberlist.length==0 && $scope.notifylogviasms == 'Yes' ){
			$scope.errorLogMember = 1;
			$scope.msgLogMember = "Please select atleast one member";
			document.getElementById("logemployeeidadd").focus();
		} else {
			var a = 0;
			
			$scope.spinLog = 1;
			$scope.enquirylog = tools_replaceAll($scope.enquirylog, "&" , "$" );
			$scope.enquirylog = tools_replaceAll($scope.enquirylog, "#" , "~" );
			$scope.enquirylog = tools_replaceAll($scope.enquirylog, "%" , "!" );
			
			var link = baseUrl+'addEnquiryLog?enquiryid='+enquiryid+'&enquirylog='+$scope.enquirylog+'&logdatetime='+$scope.logdatetime+'&notifylogviaemail='+$scope.notifylogviaemail+'&notifylogviasms='+$scope.notifylogviasms;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addEnquiryRemark = data;												
				if($scope.addEnquiryRemark == "Success") {
					
					angular.forEach($scope.logmemberlist,function(item) {
						var link = baseUrl+'addLogMemberAssign?enquiryid='+enquiryid+'&userid='+item.userId;
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addenquiryassign = data;
							a = a + 1;
							if($scope.logmemberlist.length == a && $scope.addEnquiryRemark == "Success") {
								$scope.spin = 0;
								$scope.success = 1;
								$scope.message = "Enquiry Added Successfully.";
							}
						}).error(function(data, status, headers, config) {
							$scope.addenquiryassign = "Response Fail";
						});
					});
					
					var link = baseUrl+'getEnquiryLogByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getEnquiryLog = data;
		    			$scope.spinLog = 0;
		    			var link = baseUrl+'getAllOpenEnquiries';
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getEnquiries = data;
		    				$scope.enquirylog = "";
			    			$scope.spinLog = 0;
			    			
			    			var link = baseUrl+'sendLogNotification';
			    			$http.post(link).success(function(data, status, headers, config) {
			    				$scope.sendlognotification = data;			    					
			    			}).error(function(data, status, headers, config) {
			    				$scope.sendlognotification = "Response Fail";
			    			});
			    			
		    			}).error(function(data, status, headers, config) {
		    				$scope.getEnquiries = "Response Fail";
		    			});		    					    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.getEnquiryLog = "Response Fail";
		    		});					
				}
			}).error(function(data, status, headers, config) {
				$scope.addEnquiryRemark = "Response Fail";
			});
		}
	}
	
	$scope.addEnquiryFollowup = function(enquiryid) {
		$scope.followupdatetime = document.getElementById("datetimepicker1").value;
		
		if (document.getElementById("notifyfollowupviaemail").checked == true)
			$scope.notifyfollowupviaemail = 'Yes';
		if (document.getElementById("notifyfollowupviaemail").checked == false)
			$scope.notifyfollowupviaemail = 'No';
		if (document.getElementById("notifyfollowupviasms").checked == true)
			$scope.notifyfollowupviasms = 'Yes';
		if (document.getElementById("notifyfollowupviasms").checked == false)
			$scope.notifyfollowupviasms = 'No';
		
		if(!$scope.followupremark){
			$scope.followupremark = "";
		}
		
		if(!$scope.followupdatetime){			
			$scope.errorFollowupDate = 1;
			$scope.msgFollowupDate = "Please enter date & time ";
			document.getElementById("followupdatetime").focus();
		} else if($scope.followupmemberlist.length==0 && $scope.notifyfollowupviaemail=='Yes' || $scope.followupmemberlist.length==0 && $scope.notifyfollowupviasms == 'Yes'){
			$scope.errorFollowupMember = 1;
			$scope.msgFollowupMember = "Please select atleast one member";
			document.getElementById("followupemployeeid").focus();
		} else {
			var a = 0;
			$scope.spinFollowup = 1;
			$scope.followupremark = tools_replaceAll($scope.followupremark, "&" , "$" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "#" , "~" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "%" , "!" );
			
			var link = baseUrl+'addEnquiryFollowup?enquiryid='+enquiryid+'&followupremark='+$scope.followupremark+'&followupdatetime='+$scope.followupdatetime+'&notifyfollowupviaemail='+$scope.notifyfollowupviaemail+'&notifyfollowupviasms='+$scope.notifyfollowupviasms;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addFollowup = data;												
				if($scope.addFollowup == "Success") {
					
					angular.forEach($scope.followupmemberlist,function(item) {
						var link = baseUrl+'addFollowupMemberAssign?enquiryid='+enquiryid+'&userid='+item.userId;
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addenquiryassign = data;
							a = a + 1;
							if($scope.followupmemberlist.length == a && $scope.addFollowup == "Success") {
								$scope.spin = 0;
								$scope.success = 1;
								$scope.message = "Enquiry Added Successfully.";
							}
						}).error(function(data, status, headers, config) {
							$scope.addenquiryassign = "Response Fail";
						});
					});
					
					var link = baseUrl+'getEnquiryFollowupByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getEnquiryFollowup = data;
		    			
		    			var link = baseUrl+'getTodayFollowupEnquiries';
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.todayFollowupEnquiries = data;
		    				
		    				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
		    				$http.get(link).success(function(data, status, headers, config) {
		    					$scope.followupEnquiriesByDate = data;
		    					$scope.followupremark = "";
				    			$scope.spinFollowup = 0;
				    			
				    			var link = baseUrl+'sendFollowupNotification';
				    			$http.post(link).success(function(data, status, headers, config) {
				    				$scope.sendfollowupnotification = data;			    					
				    			}).error(function(data, status, headers, config) {
				    				$scope.sendfollowupnotification = "Response Fail";
				    			});
				    			
		    				}).error(function(data, status, headers, config) {
		    					$scope.followupEnquiriesByDate = "Response Fail";
		    				});		    				
		    							    			
		    			}).error(function(data, status, headers, config) {
		    				$scope.todayFollowupEnquiries = "Response Fail";
		    			});		    			
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.getEnquiryFollowup = "Response Fail";
		    		});					
				}
			}).error(function(data, status, headers, config) {
				$scope.addFollowup = "Response Fail";
			});
		}
	}
	
	$scope.getQuotationDetail = function(quotationid) {
		
		var link = baseUrl+'getInvoiceDetailById?invoiceid='+quotationid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getInvoiceDetail = data;
			$scope.invoiceid = $scope.getInvoiceDetail.invoiceId;
			$scope.enquiryid = $scope.getInvoiceDetail.enquiryId;
			
			var link = baseUrl+'getEnquiryDetailsById?enquiryid='+$scope.getInvoiceDetail.enquiryId;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getEnquiryDetails = data;
				
				var link = baseUrl+'getUserDetailsById?userid='+$scope.getInvoiceDetail.clientId;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUserDetails = data;										
				}).error(function(data, status, headers, config) {
					$scope.getUserDetails = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.getEnquiryDetails = "Response Fail";
			});					
			
			var link = baseUrl+'getInvoiceProduct?invoiceid='+quotationid;		
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getInvoiceProduct = data;
			}).error(function(data, status, headers, config) {
				$scope.getInvoiceProduct = "Response Fail";
			});
			
			var link = baseUrl+'getInvoiceTerms?invoiceid='+quotationid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getInvoiceTerms = data;
			}).error(function(data, status, headers, config) {
				$scope.getInvoiceTerms = "Response Fail";
			});
		}).error(function(data, status, headers, config) {
			$scope.getInvoiceDetail = "Response Fail";
		});	
	}
	
	$scope.addStatusReason = function(){
		if(!$scope.reason){			
			$scope.errorStatusReason = 1;
			$scope.msgStatusReason = "Please enter reason";
			document.getElementById("reason").focus();
		} else {
			$scope.spinReason = 1;
			$scope.reason = tools_replaceAll($scope.reason, "&" , "$" );
			$scope.reason = tools_replaceAll($scope.reason, "#" , "~" );
			$scope.reason = tools_replaceAll($scope.reason, "%" , "!" );
			
			var link = baseUrl+'addStatusReason?reason='+$scope.reason;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addstatusreason = data;												
				if($scope.addstatusreason == "Success") {					
					var link = baseUrl+'getStatusReason';
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getStatusReason = data;		    			
		    			$scope.spinReason = 0;
		    			$scope.reasonSubmitSuccess = 1;
		    			$scope.reasonSuccessMsg = "Data added"
		    			$timeout(function(){
		    				$scope.reasonSubmitSuccess = 0;
		    				$('#statusReasonModal').modal('hide'); 				
		    			}, 2000);
		    		}).error(function(data, status, headers, config) {
		    			$scope.getStatusReason = "Response Fail";
		    		});					
				} else {
					$scope.spinReason = 0;
	    			$scope.reasonSubmitError = 1;
	    			$scope.reasonErrorMsg = "Something wrong! Data not added!"	    			
				}
			}).error(function(data, status, headers, config) {
				$scope.addstatusreason = "Response Fail";
				$scope.spinReason = 0;
    			$scope.reasonSubmitError = 1;
    			$scope.reasonErrorMsg = "Something wrong! Data not added!"
			});
		}	
	}
	
	$scope.updateEnquiryStatus = function(enquiryid){		
		
		if(!$scope.statusreason){			
			$scope.statusreason = 0;
		}
		if(!$scope.enquirystatus){			
			$scope.errorEnquiryStatus = 1;
			$scope.msgEnquiryStatus = "Please select status";
			document.getElementById("enquirystatus").focus();
		} else {
			$scope.spinStatus = 1;
			
			if(document.getElementById("statusclose").checked == true && $scope.enquirystatus == 'w')
				$scope.enquirystatus = 'cw';
			if(document.getElementById("statusclose").checked == true && $scope.enquirystatus == 'l')
				$scope.enquirystatus = 'cl';		
			
			var link = baseUrl+'changeEnquiryStatus?enquiryid='+enquiryid+'&enquirystatus='+$scope.enquirystatus+'&statusreason='+$scope.statusreason;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.updateenquirystatus = data;												
				if($scope.updateenquirystatus == "Success") {    			
					var link = baseUrl+'getAllOpenEnquiries';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getEnquiries = data;	    			
		    			$scope.spinStatus = 0;
		    			$scope.statusSubmitSuccess = 1;
		    			$scope.statusSuccessMsg = "Status updated"
		    			$timeout(function(){
		    				$scope.enquirystatus = "";
		    				$scope.statusreason = "";
		    				$scope.statusSubmitSuccess = 0;
		    				$('#statusModal').modal('hide'); 				
		    			}, 2000);
		    		}).error(function(data, status, headers, config) {
		    			$scope.getEnquiries = "Response Fail";
		    		});					
				} else {
					$scope.spinStatus = 0;
	    			$scope.statusSubmitError = 1;
	    			$scope.statusErrorMsg = "Something wrong! Status not updated!"	    			
				}
			}).error(function(data, status, headers, config) {
				$scope.updateenquirystatus = "Response Fail";
				$scope.spinStatus = 0;
    			$scope.statusSubmitError = 1;
    			$scope.statusErrorMsg = "Something wrong! Status not updated!"
			});
		}	
	}
	
	$scope.addUserType = function() {		
		if(!$scope.usertypename) {			
			$scope.errorUserType = 1;
			$scope.msgUserType = "Please enter user type name";
			document.getElementById("usertypename").focus();
		} else {
			$scope.userTypeSpin = 1;			
			var link = baseUrl+'addUserType?usertypename='+$scope.usertypename;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addusertype = data;
				var link = baseUrl+'getUserTypes';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUserTypes = data;
					$scope.userTypeSpin = 0;
					$scope.userTypeSubmitSuccess = 1;
					$scope.userTypeSuccessMsg = "Data added successfully.";
					$timeout(function() {
						$scope.userTypeSubmitSuccess = 0;
						$scope.usertypename = "";
						$('#userTypeModal').modal('hide');
					}, 2000);
				}).error(function(data, status, headers, config) {
					$scope.getUserTypes = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.addusertype = "Response Fail";
			});
		}
	}
	
	$scope.specificationlist = [];
	$scope.addSpecificationRow = function() {
		if($scope.specificationadd==undefined || $scope.specificationadd=="") {				
			$scope.errorSpecification = 1;
			$scope.msgSpecification = "Please enter specification!";
			document.getElementById("specificationadd").focus();
		} else {
			$scope.specificationlist.push({ 'specification':$scope.specificationadd});
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
					$scope.suppliername = $scope.getSuppliers[i].firstName+' '+$scope.getSuppliers[i].lastName;
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
		var recurringbilling = $scope.recurringbillingadd;
		var productname = $scope.productnameadd;
		var partnumber = $scope.partnumberadd;
		var recurringvalue = $scope.recurringvalueadd;
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
		if(recurringvalue==undefined || recurringvalue=="") {
			recurringvalue = 0;
		}

		if(categoryname==undefined || categoryname=="") {				
			$scope.errorCategoryName = 1;
			$scope.msgCategoryName = "Please select category";
			document.getElementById("categorynameadd").focus();
		} else if(recurringbilling==undefined || recurringbilling=="") {				
			$scope.errorRecurring = 1;
			$scope.msgRecurring = "Please select recurring billing status";
			document.getElementById("recurringbillingadd").focus();
		} else if(productname==undefined || productname=="") {
			document.getElementById("productnameadd").focus();
			$scope.errorProductName = 1;
			$scope.msgProductName = "Please enter product name";				
		} else if(unitid==undefined || unitid=="") {				
			$scope.errorUnit = 1;
			$scope.msgUnit = "Please select unit";
			document.getElementById("unitidadd").focus();
		} else {			
			$scope.productSpin = 1;
				
			var pro = productname.replace("&","$");
			var pro1 = pro.replace("#","~");
			var pro2 = pro1.replace("%","!");
			
			var link = baseUrl+'addProduct?categoryname='+categoryname+'&subcategoryname='+subcategoryname+'&brandname='+brandname+'&saletype='+saletype+'&recurringbilling='+recurringbilling+'&productname='+pro2+'&partnumber='+partnumber+'&recurringvalue='+recurringvalue+'&unitid='+unitid+'&hsn='+hsn+'&description='+description;				
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addproduct = data;
				
				var a = 0, b = 0, c = 0;
				
				angular.forEach($scope.specificationlist,function(item) {												
					var link = baseUrl+'addProductSpecification?specification='+item.specification;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addproductspecification = data;
						a = a + 1;						
						if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c) {
							
							var link = baseUrl+'getProducts';
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getProducts = data;
								
								$scope.productSpin = 0;
								$scope.productSubmitSuccess = 1;
								$scope.productSuccessMsg = "Product Added Successfully.";
								$timeout(function(){
									$scope.productSubmitSuccess = 0;
									$('#productModal').modal('hide'); 
									document.getElementById("AddProductForm").reset();
								}, 2000);
								
							}).error(function(data, status, headers, config) {
								$scope.getProducts = "Response Fail";
							});							
						}
					}).error(function(data, status, headers, config) {
						$scope.addproductspecification = "Response Fail";
					});
				});
				
				angular.forEach($scope.supplierlist,function(item) {												
					var link = baseUrl+'addProductSupplier?supplierid='+item.supplierId;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addproductsupplier = data;
						b = b + 1;
						if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c) {
							
							var link = baseUrl+'getProducts';
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getProducts = data;
								
								$scope.productSpin = 0;
								$scope.productSubmitSuccess = 1;
								$scope.productSuccessMsg = "Product Added Successfully.";
								$timeout(function(){
									$scope.productSubmitSuccess = 0;
									$('#productModal').modal('hide');
									document.getElementById("AddProductForm").reset();
								}, 2000);
								
							}).error(function(data, status, headers, config) {
								$scope.getProducts = "Response Fail";
							});							
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
						if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c) {
							
							var link = baseUrl+'getProducts';
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getProducts = data;
								
								$scope.productSpin = 0;
								$scope.productSubmitSuccess = 1;
								$scope.productSuccessMsg = "Product Added Successfully.";
								$timeout(function(){
									$scope.productSubmitSuccess = 0;
									$('#productModal').modal('hide');
									document.getElementById("AddProductForm").reset();
								}, 2000);
								
							}).error(function(data, status, headers, config) {
								$scope.getProducts = "Response Fail";
							});							
						}
					}).error(function(data, status, headers, config) {
						$scope.addproductprice = "Response Fail";
					});
				});			
				
				if($scope.specificationlist.length == a && $scope.supplierlist.length == b && $scope.pricelist.length == c) {
					
					var link = baseUrl+'getProducts';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getProducts = data;
						
						$scope.productSpin = 0;
						$scope.productSubmitSuccess = 1;
						$scope.productSuccessMsg = "Product Added Successfully.";
						$timeout(function(){
							$scope.productSubmitSuccess = 0;
							$('#productModal').modal('hide'); 
							document.getElementById("AddProductForm").reset();
						}, 2000);
						
					}).error(function(data, status, headers, config) {
						$scope.getProducts = "Response Fail";
					});							
				}
			}).error(function(data, status, headers, config) {
				$scope.addproduct = "Response Fail";
			});				
		}
	}
	
	$scope.addCategory = function() {
		var categoryname = $scope.categorynameadd;
		var categorycode = $scope.categorycodeadd;
		var description = $scope.categorydescriptionadd;
		
		if(description==undefined || description=="") {
			description = "";
		}

		if(categoryname==undefined || categoryname=="") {
			document.getElementById("categorynameadd").focus();
			$scope.catinfo = 1;
			$scope.catmessage = "Please enter category name";
			$timeout(function(){
				$scope.catinfo = 0;
			}, 2000);
		} else if(categorycode==undefined || categorycode=="") {
			document.getElementById("categorycodeadd").focus();
			$scope.catinfo = 1;
			$scope.catmessage = "Please enter category Code";
			$timeout(function(){
				$scope.catinfo = 0;
			}, 2000);
		} else {
			var a = 0, b = 0;				
			if(categorycode!=undefined || categorycode!="") {
				for(i in $scope.getCategories) {
					b = b + 1;
					if($scope.getCategories[i].categoryCode == categorycode) {
						a = 1;
						document.getElementById("categorycodeadd").focus();
						$scope.catinfo = 1;
						$scope.catmessage = "Category Code Already Exist";
						$timeout(function(){
							$scope.catinfo = 0;
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
							$scope.catsuccess = 1;								
							$scope.catmessage = "Category Added Successfully.";								
							$timeout(function(){
								$scope.catsuccess = 0;
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
			document.getElementById("categorynameadd").focus();
			$scope.subinfo = 1;
			$scope.submessage = "Please select category";
			$timeout(function(){
				$scope.subinfo = 0;
			}, 2000);
		} else if(subcategoryname==undefined || subcategoryname=="") {
			document.getElementById("subcategorynameadd").focus();
			$scope.subinfo = 1;
			$scope.submessage = "Please enter subcategory name";
			$timeout(function(){
				$scope.subinfo = 0;
			}, 2000);
		} else if(subcategorycode==undefined || subcategorycode=="") {
			document.getElementById("subcategorycodeadd").focus();
			$scope.subinfo = 1;
			$scope.submessage = "Please enter subcategory code";
			$timeout(function(){
				$scope.subinfo = 0;
			}, 2000);
		} else {
			var a = 0, b = 0;				
			if(subcategorycode!=undefined || subcategorycode!="") {
				for(i in $scope.getSubcategories) {
					b = b + 1;
					if($scope.getSubcategories[i].subcategoryCode == subcategorycode) {
						a = 1;
						document.getElementById("subcategorycodeadd").focus();
						$scope.subinfo = 1;
						$scope.submessage = "Sub Category code already exist";
						$timeout(function(){
							$scope.subinfo = 0;
						}, 2000);
					}
				}
			}
			
			if(a == 0 && $scope.getSubcategories.length == b) {
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
							$scope.subsuccess = 1;								
							$scope.submessage = "Subcategory Added Successfully.";								
							$timeout(function(){
								$scope.subsuccess = 0;
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
			document.getElementById("brandnameadd").focus();
			$scope.brandinfo = 1;
			$scope.brandmessage = "Please Enter Brand Name";
			$timeout(function(){
				$scope.brandinfo = 0;
			}, 2000);
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
					$scope.brandsuccess = 1;							
					$scope.brandmessage = "Brand Added Successfully.";							
					$timeout(function(){
						$scope.brandsuccess = 0;
						$('#brandModal').modal('hide');
					}, 2000);
				}).error(function(data, status, headers, config) {
					$scope.getBrands = "Response Fail";
				});					
			}).error(function(data, status, headers, config) {
				$scope.addbrand = "Response Fail";
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
			document.getElementById("measurementunitnameadd").focus();
			$scope.unitinfo = 1;
			$scope.unitmessage = "Please enter measurement unit name";
			$timeout(function(){
				$scope.unitinfo = 0;
			}, 2000);
		} else {
			$scope.spin = 1;				
			var link = baseUrl+'addMeasurementUnit?measurementunitname='+measurementunitname+'&description='+description;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addmeasurementunit = data;
				var link = baseUrl+'getMeasurementUnits';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMeasurementUnits = data;
					$scope.spin = 0;
					$scope.unitsuccess = 1;
					$scope.unitmessage = "Measurement Unit Added Successfully.";
					$timeout(function() {
						$scope.unitsuccess = 0;
						$('#unitModal').modal('hide');
						document.getElementById("AddUnitForm").reset();
					}, 2000);
				}).error(function(data, status, headers, config) {
					$scope.getMeasurementUnits = "Response Fail";
				});					
			}).error(function(data, status, headers, config) {
				$scope.addmeasurementunit = "Response Fail";
			});
		}
	}
	
	$scope.markCompleteFollowup = function(followupid, status) {
		if(status == "y") {
			status = "c";
		} else if(status == "c") {
			status = "y";
		}
		
		var link = baseUrl+'markCompleteFollowup?followupid='+followupid+'&status='+status;		
		$http.post(link).success(function(data, status, headers, config) {
			$scope.markfollowuptocomplete = data;
			if($scope.markfollowuptocomplete == "Success"){
				var link = baseUrl+'getTodayFollowupEnquiries';
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = data;
    				
    				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
    				$http.get(link).success(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = data;
    				}).error(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = "Response Fail";
    				});
    				
    			}).error(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = "Response Fail";
    			});	
			} else {
				$scope.errorFollowupStatus = 1;
				$scope.msgFollowupStatus = $scope.markfollowuptocomplete;
				$timeout(function() {
					$scope.errorFollowupStatus = 0;
				}, 2000);
			}
		}).error(function(data, status, headers, config) {
			$scope.errorFollowupStatus = 1;
			$scope.msgFollowupStatus = "Something wrong!";
			$timeout(function() {
				$scope.errorFollowupStatus = 0;
			}, 2000);
		});
	}
	
	$scope.deleteFollowup = function(followupid) {		
		var link = baseUrl+'deleteFollowup?followupid='+followupid;		
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deletefollowup = data;
			
				var link = baseUrl+'getTodayFollowupEnquiries';
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = data;
    				
    				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
    				$http.get(link).success(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = data;
    				}).error(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = "Response Fail";
    				});
    				
    			}).error(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = "Response Fail";
    			});	
			
		}).error(function(data, status, headers, config) {
			$scope.errorFollowupStatus = 1;
			$scope.msgFollowupStatus = "Something wrong!";
			$timeout(function() {
				$scope.errorFollowupStatus = 0;
			}, 2000);
		});
	}
	
	$scope.goToGenerateInvoice = function(enquiryid) {
		$window.location.href = adminurl+'generate_invoice?enquiryid='+enquiryid;
	}
	
	$scope.getInvoicePrintPreview = function(enquiryid) {
		var link = baseUrl+'getInvoiceDetailByEnquiryId?enquiryid='+enquiryid;	
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getInvoiceDetailByEnquiryId = data;
			
			$window.location.href = adminurl+'print_invoice?invoiceid='+$scope.getInvoiceDetailByEnquiryId.invoiceId; 
			
		}).error(function(data, status, headers, config) {
			$scope.getInvoiceDetailByEnquiryId = "Response Fail";
		});
	}
	
	/*
	$scope.addNewFollowup = function(){
		var enquiryid = 0;
		$scope.followupdatetime = document.getElementById("datetimepicker2").value;
		
		if(!$scope.followupdatetime){			
			$scope.errorFollowupDate = 1;
			$scope.msgFollowupDate = "Please enter date & time ";
			document.getElementById("followupdatetime").focus();
		} else if(!$scope.followupremark){
			$scope.errorFollowupRemark = 1;
			$scope.msgFollowupRemark = "Please enter your remark ";
			document.getElementById("followupremark").focus();		
		} else {
			$scope.spinFollowup = 1;
			$scope.followupremark = tools_replaceAll($scope.followupremark, "&" , "$" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "#" , "~" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "%" , "!" );
			
			var link = baseUrl+'addEnquiryFollowup?enquiryid='+enquiryid+'&followupremark='+$scope.followupremark+'&followupdatetime='+$scope.followupdatetime;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addFollowup = data;												
				if($scope.addFollowup == "Success") {		
					
		    			
		   			var link = baseUrl+'getTodayFollowupEnquiries';
		   			$http.get(link).success(function(data, status, headers, config) {
		   				$scope.todayFollowupEnquiries = data;
		   				
		   				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
		   				$http.get(link).success(function(data, status, headers, config) {
		   					$scope.followupEnquiriesByDate = data;	   					
			    			
			    			$('#toDoModal').modal('hide');
			    			$scope.spinFollowup = 0;
			    			$scope.followupremark = "";
			    			
		    			}).error(function(data, status, headers, config) {
		    				$scope.followupEnquiriesByDate = "Response Fail";
		    			});		    				
		    						    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.todayFollowupEnquiries = "Response Fail";
		    		});			    							
				}
				
			}).error(function(data, status, headers, config) {
				$scope.addFollowup = "Response Fail";
			});
		}
	}	
	
	*/
	
	
	//--------add new from astar
	
	$scope.addNewFollowup = function(){
		var enquiryid = 0;
		$scope.followupdatetime = document.getElementById("datetimepicker2").value;
		
		if (document.getElementById("notifyfollowupviaemail").checked == true)
			$scope.notifyfollowupviaemail = 'Yes';
		if (document.getElementById("notifyfollowupviaemail").checked == false)
			$scope.notifyfollowupviaemail = 'No';
		if (document.getElementById("notifyfollowupviasms").checked == true)
			$scope.notifyfollowupviasms = 'Yes';
		if (document.getElementById("notifyfollowupviasms").checked == false)
			$scope.notifyfollowupviasms = 'No';
		
		if(!$scope.followupdatetime){			
			$scope.errorFollowupDate = 1;
			$scope.msgFollowupDate = "Please enter date & time ";
			document.getElementById("followupdatetime").focus();
		} else if(!$scope.followupremark){
			$scope.errorFollowupRemark = 1;
			$scope.msgFollowupRemark = "Please enter your remark ";
			document.getElementById("followupremark").focus();		
		} else {
			$scope.spinFollowup = 1;
			$scope.followupremark = tools_replaceAll($scope.followupremark, "&" , "$" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "#" , "~" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "%" , "!" );
			
			var link = baseUrl+'addEnquiryFollowup?enquiryid='+enquiryid+'&followupremark='+$scope.followupremark+'&followupdatetime='+$scope.followupdatetime+'&notifyfollowupviaemail='+$scope.notifyfollowupviaemail+'&notifyfollowupviasms='+$scope.notifyfollowupviasms;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addFollowup = data;												
				if($scope.addFollowup == "Success") {
					
					angular.forEach($scope.emplist,function(item) {												
						var link = baseUrl+'addEmpFollowup?userid='+item.userId;
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addempfollowup = data;
							
							var link = baseUrl+'getTodayFollowupEnquiries';
				   			$http.get(link).success(function(data, status, headers, config) {
				   				$scope.todayFollowupEnquiries = data;
				   				
				   				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
				   				$http.get(link).success(function(data, status, headers, config) {
				   					$scope.followupEnquiriesByDate = data;	   					
					    			
					    			$('#toDoModal').modal('hide');
					    			$scope.spinFollowup = 0;
					    			$scope.followupremark = "";
					    			
					    			var link = baseUrl+'sendFollowupNotification';
					    			$http.post(link).success(function(data, status, headers, config) {
					    				$scope.sendfollowupnotification = data;			    					
					    			}).error(function(data, status, headers, config) {
					    				$scope.sendfollowupnotification = "Response Fail";
					    			});
					    			
				    			}).error(function(data, status, headers, config) {
				    				$scope.followupEnquiriesByDate = "Response Fail";
				    			});		    				
				    						    			
				    		}).error(function(data, status, headers, config) {
				    			$scope.todayFollowupEnquiries = "Response Fail";
				    		});								
							
						}).error(function(data, status, headers, config) {
							$scope.addempfollowup = "Response Fail";
						});
					});
					
					if($scope.emplist.length == 0){
						var link = baseUrl+'getTodayFollowupEnquiries';
			   			$http.get(link).success(function(data, status, headers, config) {
			   				$scope.todayFollowupEnquiries = data;
			   				
			   				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
			   				$http.get(link).success(function(data, status, headers, config) {
			   					$scope.followupEnquiriesByDate = data;	   					
				    			
				    			$('#toDoModal').modal('hide');
				    			$scope.spinFollowup = 0;
				    			$scope.followupremark = "";
				    			
			    			}).error(function(data, status, headers, config) {
			    				$scope.followupEnquiriesByDate = "Response Fail";
			    			});		    				
			    						    			
			    		}).error(function(data, status, headers, config) {
			    			$scope.todayFollowupEnquiries = "Response Fail";
			    		});	
					}
				}
			}).error(function(data, status, headers, config) {
				$scope.addFollowup = "Response Fail";
			});
		}
	}
	

	
	
	
	
	
	
	
	$scope.goToGenerateQuotation = function(enquiryid) {
		$window.location.href = adminurl+'generate_quotation?enquiryid='+enquiryid;
	}
	
	
	//ADD LOG Member LIST
	
	$scope.logmemberlist = [];
	$scope.addLogMemberRow = function() {
		if($scope.logemployeeidadd==undefined || $scope.logemployeeidadd=="") {			
			$scope.errorLogEmpName = 1;
			$scope.msgLogEmpName = "Please select an employee!";
			document.getElementById("logemployeeidadd").focus();
		} else {
			
			var link = baseUrl+'getUserDetailById?userid='+$scope.logemployeeidadd;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;			
            	
            	$scope.firstname = $scope.userDetailById.firstName;            	
            	$scope.lastname = $scope.userDetailById.lastName;
            	
            	$scope.logmemberlist.push({'userId':$scope.logemployeeidadd, 'firstName':$scope.firstname, 'lastName':$scope.lastname});
    			$scope.logemployeeidadd = "";
    			
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});			
		}
	}
	
	$scope.removeLogMemberRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.logmemberlist.length; i++) {
			if($scope.logmemberlist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record..Please try again!");
			return;
		}
		$scope.logmemberlist.splice(index, 1);
	};
	
	
	$scope.followupmemberlist = [];
	$scope.addFollowupMemberRow = function() {
		if($scope.followupemployeeid==undefined || $scope.followupemployeeid=="") {			
			$scope.errorFollowupEmpName = 1;
			$scope.msgFollowupEmpName = "Please select an employee!";
			document.getElementById("followupemployeeid").focus();
		} else {
			var link = baseUrl+'getUserDetailById?userid='+$scope.followupemployeeid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;			
            	
            	$scope.firstname = $scope.userDetailById.firstName;            	
            	$scope.lastname = $scope.userDetailById.lastName;
            	
            	$scope.followupmemberlist.push({'userId':$scope.followupemployeeid, 'firstName':$scope.firstname, 'lastName':$scope.lastname});
    			$scope.followupemployeeid = "";
    			
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});			
		}
	}
	
	$scope.removeFollowupMemberRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.followupmemberlist.length; i++) {
			if($scope.followupmemberlist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record..Please try again!");
			return;
		}
		$scope.followupmemberlist.splice(index, 1);
	};
	
	//----------------------add emp row and delete
	
	$scope.emplist = [];
	$scope.addEmpRow = function() {
		if($scope.employeeidadd==undefined || $scope.employeeidadd=="") {			
			$scope.errorEmpName = 1;
			$scope.msgEmpName = "Please select an employee!";
			document.getElementById("employeeidadd").focus();
		} else {
			
			var link = baseUrl+'getUserDetailById?userid='+$scope.employeeidadd;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;			
            	
            	$scope.firstname = $scope.userDetailById.firstName;            	
            	$scope.lastname = $scope.userDetailById.lastName;
            	
            	$scope.emplist.push({'userId':$scope.employeeidadd, 'firstName':$scope.firstname, 'lastName':$scope.lastname});
    			$scope.employeeidadd = "";
    			
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});			
		}
	}

	$scope.removeEmpRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.emplist.length; i++) {
			if($scope.emplist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record..Please try again!");
			return;
		}
		$scope.emplist.splice(index, 1);
	};
	
	//quatation

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
	
	
	
//quatation end
	
	
	
	
}]);