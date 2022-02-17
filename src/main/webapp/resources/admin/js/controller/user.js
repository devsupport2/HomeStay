app.controller('userCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];	
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getUsers.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUsers = data;
		}).error(function(data, status, headers, config) {
			$scope.getUsers = "Response Fail";
		});
		
		$scope.filterUserById = function(id){
			if(id == null){
				$scope.pageSize = 20;
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
					$scope.pageSize = 20;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			} else {
				var link = baseUrl+'getUsersById?id='+id;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
					$scope.pageSize = "All";
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
			
		}
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getUsers = data;
			}).error(function(data, status, headers, config) {
				$scope.getUsers = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getUsers';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			} else {
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
		}
		
		$scope.searchUser = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchUsers?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
		}	
		
		var link = baseUrl+'getUserTypes';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserTypes = data;
		}).error(function(data, status, headers, config) {
			$scope.getUserTypes = "Response Fail";
		});
		
		var link = baseUrl+'getProductName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProductNameList = data;
		}).error(function(data, status, headers, config) {
			$scope.getProductNameList = "Response Fail";
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
		
		$scope.setFlag = function(){			
			$scope.errorUserType = 0;
			$scope.errorFirstName = 0;
			$scope.errorCountryName = 0;
			$scope.errorCountry = 0;
			$scope.errorStateName = 0;
			$scope.errorCompanyName = 0;
			$scope.errorEmployeeCode = 0;
			$scope.errorHourlyWages = 0;
			$scope.errorOverTime = 0;
		}
		
		$scope.assignproductlist = [];
		$scope.addAssignProductRow = function() {
			if($scope.productidadd==undefined || $scope.productidadd=="") {			
				$scope.errorProductName = 1;
				$scope.msgProductName = "Please select a product!";
				document.getElementById("productidadd").focus();
			} else if ($scope.productidadd == "All"){
				angular.forEach($scope.getProductNameList,function(item) {
					var link = baseUrl+'getProductDetailById?productid='+item.productId;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getProductDetailById = data;
						
						$scope.assignproductlist.push({'productId':$scope.getProductDetailById.productId, 'productName':$scope.getProductDetailById.productName});											
									
					}).error(function(data, status, headers, config) {
						$scope.getProductDetailById = "Response Fail";
					});
				});
				return;
			} else {				
				var link = baseUrl+'getProductDetailById?productid='+$scope.productidadd;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProductDetailById = data;
					
					$scope.assignproductlist.push({'productId':$scope.getProductDetailById.productId, 'productName':$scope.getProductDetailById.productName});
					$scope.productidadd = "";					
								
				}).error(function(data, status, headers, config) {
					$scope.getProductDetailById = "Response Fail";
				});			
			}
		}

		$scope.removeAssignProductRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.assignproductlist.length; i++) {
				if($scope.assignproductlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record..Please try again!");
				return;
			}
			$scope.assignproductlist.splice(index, 1);
		};
		
		$scope.addUser = function() {						
			$scope.dateofbirth = document.getElementById("datepicker").value;
			$scope.aadharnumber = document.getElementById("aadharnumberadd").value;	
			
			if($scope.usertypenameadd == 1 || $scope.usertypenameadd == 2) {
				$scope.companynameadd = "HomeStay Structures";
			}
			if(!$scope.middlenameadd)	{
				$scope.middlenameadd = "";
			}
			if(!$scope.lastnameadd)	{
				$scope.lastnameadd = "";
			}
			if(!$scope.genderadd)	{
				$scope.genderadd = "";
			}			
			if(!$scope.dateofbirth || $scope.dateofbirth=="day/month/year")	{
				$scope.dateofbirth = "";
			}
			if(!$scope.aadharnumber) {
				$scope.aadharnumber = "";
			}
			if(!$scope.passportnumberadd)	{
				$scope.passportnumberadd = "";
			}
			if(!$scope.pannumberadd) {
				$scope.pannumberadd = "";
			}
			if(!$scope.address1add) {
				$scope.address1add = "";
			}
			if(!$scope.address2add) {
				$scope.address2add = "";
			}
			if(!$scope.address3add) {
				$scope.address3add = "";
			}
			if(!$scope.countrynameadd) {
				$scope.countrynameadd = 0;
			}
			if(!$scope.statenameadd) {
				$scope.statenameadd = 0;
			}
			if(!$scope.citynameadd) {
				$scope.citynameadd = "";
			}
			if(!$scope.pincodeadd) {
				$scope.pincodeadd = "";
			}
			if(!$scope.mobilenumberadd) {
				$scope.mobilenumberadd = "";
			}
			if(!$scope.landlinenumberadd) {
				$scope.landlinenumberadd = "";
			}
			if(!$scope.emailadd) {
				$scope.emailadd = "";
			}
			if(!$scope.passwordadd) {
				$scope.passwordadd = "";
			}
			if($scope.usertypenameadd != 2 && !$scope.codeadd) {
				$scope.codeadd = 0;
			}
			/*if($scope.usertypenameadd != 2 && $scope.hourlywagesadd == undefined && $scope.hourlywagesadd == "") {
				$scope.hourlywagesadd = 0;
			}*/
			
			if($scope.hourlywagesadd==undefined) {
				$scope.hourlywagesadd = 0;
			}
			/*if($scope.usertypenameadd != 2 && $scope.overtimewagesadd == undefined && $scope.overtimewagesadd == "") {
				$scope.overtimewagesadd = 0;
			}*/
			
			if($scope.overtimewagesadd == undefined) {
				$scope.overtimewagesadd = 0;
			}
			
			if(!$scope.usertypenameadd) {				
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please select user type";
				document.getElementById("usertypenameadd").focus();
				return;
			} else if($scope.usertypenameadd == 3 && !$scope.companynameadd)	{
				$scope.errorCompanyName = 1;
				$scope.msgCompanyName = "Please enter company name";
				document.getElementById("companynameadd").focus();
				return;
			} else if(!$scope.firstnameadd) {				
				$scope.errorFirstName = 1;
				$scope.msgFirstName = "Please enter first name";
				document.getElementById("firstnameadd").focus();
				return;
			}
			else if($scope.usertypenameadd == 2 && !$scope.codeadd) {				
				$scope.errorEmployeeCode = 1;
				$scope.msgEmployeeCode = "Please enter employee code";
				document.getElementById("codeadd").focus();
				return;
			} 
			
		/*	else if($scope.usertypenameadd == 2 && !$scope.hourlywagesadd) {				
				$scope.errorHourlyWages = 1;
				$scope.msgHourlyWages = "Please enter hourly wages";
				document.getElementById("hourlywagesadd").focus();
				return;
			} 
			
			else if($scope.usertypenameadd == 2 && !$scope.overtimewagesadd) {				
				$scope.errorOverTime = 1;
				$scope.msgOverTime = "Please enter over time wages";
				document.getElementById("overtimewagesadd").focus();
				return;
			} 
			*/
			else {
				var a = 0;
				$scope.spin = 1;		
				var link = baseUrl+'addUser?companyname='+$scope.companynameadd+'&firstname='+$scope.firstnameadd+'&middlename='+$scope.middlenameadd+'&lastname='+$scope.lastnameadd+'&usertypename='+$scope.usertypenameadd+'&gender='+$scope.genderadd+'&dateofbirth='+$scope.dateofbirth+'&aadharnumber='+$scope.aadharnumber+'&passportnumber='+$scope.passportnumberadd+'&pannumber='+$scope.pannumberadd+'&address1='+$scope.address1add+'&address2='+$scope.address2add+'&address3='+$scope.address3add+'&statename='+$scope.statenameadd+'&cityname='+$scope.citynameadd+'&pincode='+$scope.pincodeadd+'&mobilenumber='+$scope.mobilenumberadd+'&landlinenumber='+$scope.landlinenumberadd+'&email='+$scope.emailadd+'&password='+$scope.passwordadd+'&code='+$scope.codeadd+'&hourlywages='+$scope.hourlywagesadd+'&overtimewages='+$scope.overtimewagesadd;
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
					
					if ($scope.assignproductlist.length > 1) {
						angular.forEach($scope.assignproductlist,function(item) {												
							var link = baseUrl+'addDealerProduct?productid='+item.productId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.adddealerproduct = data;
								a = a + 1;
								if($scope.assignproductlist.length == a) {
									$scope.spin = 0;
									if($scope.adduser == 'Success'){
										$scope.userSubmitError = 0;
										$scope.userSubmitSuccess = 1;
										$scope.successMsg = "Data added";
										$timeout(function(){
											$scope.userSubmitSuccess = 0;
											$window.location.href = adminurl+'manage_user';
										}, 2000);
									} else {
										$scope.userSubmitSuccess = 0;
										$scope.userSubmitError = 1;
										$scope.errorMsg = $scope.adduser;						
									}
								}
							}).error(function(data, status, headers, config) {
								$scope.adddealerproduct = "Response Fail";
							});
						});
					} else {
						$scope.spin = 0;
						if($scope.adduser == 'Success'){
							$scope.userSubmitError = 0;
							$scope.userSubmitSuccess = 1;
							$scope.successMsg = "Data added";
							$timeout(function(){
								$scope.userSubmitSuccess = 0;
								$window.location.href = adminurl+'manage_user';
							}, 2000);
						} else {
							$scope.userSubmitSuccess = 0;
							$scope.userSubmitError = 1;
							$scope.errorMsg = $scope.adduser;						
						}
					}			
										
				}).error(function(data, status, headers, config) {
					$scope.adduser = data;
					$scope.spin = 0;
					$scope.userSubmitSuccess = 0;
					$scope.userSubmitError = 1;
					$scope.errorMsg = "Something wrong! Please try again later!";
				});			
			}
		}
		
		$scope.getUser = function(userid) {
			
			var link = baseUrl+'getUserDetailById?userid='+userid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;
				
				$scope.userid = $scope.userDetailById.userId;
            	$scope.locationname = $scope.userDetailById.locationId;
            	$scope.companyname = $scope.userDetailById.userCompanyName;
            	$scope.firstname = $scope.userDetailById.firstName;
            	$scope.middlename = $scope.userDetailById.middleName;
            	$scope.lastname = $scope.userDetailById.lastName;
            	$scope.usertypename = $scope.userDetailById.userTypeId;                	
            	$scope.gender = $scope.userDetailById.gender;                	
            	$scope.dateofbirth = $scope.userDetailById.dateOfBirth;            	                	
            	$scope.aadharnumber = $scope.userDetailById.aadharNumber;
            	$scope.passportnumber = $scope.userDetailById.passportNumber;
            	$scope.pannumber = $scope.userDetailById.panNumber;
            	$scope.profilepicture1 = $scope.userDetailById.profilePicture;
            	$scope.address1 = $scope.userDetailById.address1;
            	$scope.address2 = $scope.userDetailById.address2;
            	$scope.address3 = $scope.userDetailById.address3;
            	$scope.statename = $scope.userDetailById.stateId;
            	$scope.cityname = $scope.userDetailById.cityName;
            	$scope.pincode = $scope.userDetailById.pincode;
            	$scope.mobilenumber = $scope.userDetailById.mobileNumber;
            	$scope.landlinenumber = $scope.userDetailById.landlineNumber;
            	$scope.email = $scope.userDetailById.email;
            	$scope.email1 = $scope.userDetailById.email;
            	$scope.password = $scope.userDetailById.password;
            	$scope.codeedit = $scope.userDetailById.code;
            	$scope.hourlywagesedit = $scope.userDetailById.hourlyWages;
            	$scope.overtimewagesedit = $scope.userDetailById.overtimeWages;
            	
            	
            	var link = baseUrl+'getDealerProductById?userid='+userid;            	
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.dealerProducts = data;
    			}).error(function(data, status, headers, config) {
    				$scope.dealerProducts = "Response Fail";
    			});
            	
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});		
		}
		
		$scope.deleteProfilePicture = function() {
			$scope.profilepicture1 = "";
		}
		
		$scope.editUser = function(userid) {					
			var profilepicture1 = $scope.profilepicture1;		
			
			$scope.dateofbirth = document.getElementById("datepicker1").value;			
			$scope.aadharnumber = document.getElementById("aadharnumber").value;	
			
			if(!$scope.companyname)	{
				$scope.companyname = "";
			}
			if(!$scope.middlename)	{
				$scope.middlename = "";
			}
			if(!$scope.lastname)	{
				$scope.lastname = "";
			}
			if(!$scope.gender)	{
				$scope.gender = "";
			}			
			if(!$scope.dateofbirth || $scope.dateofbirth=="day/month/year")	{
				$scope.dateofbirth = "";
			}
			if(!$scope.aadharnumber) {
				$scope.aadharnumber = "";
			}
			if(!$scope.passportnumber)	{
				$scope.passportnumber = "";
			}
			if(!$scope.pannumber) {
				$scope.pannumber = "";
			}
			if(!$scope.address1) {
				$scope.address1 = "";
			}
			if(!$scope.address2) {
				$scope.address2 = "";
			}
			if($scope.address3) {
				$scope.address3 = "";
			}
			if(!$scope.countryname) {
				$scope.countryname = 0;
			}
			if(!$scope.statename) {
				$scope.statename = 0;
			}
			if(!$scope.cityname) {
				$scope.cityname = "";
			}
			if(!$scope.pincode) {
				$scope.pincode = "";
			}
			if(!$scope.mobilenumber) {
				$scope.mobilenumber = "";
			}
			if(!$scope.landlinenumber) {
				$scope.landlinenumber = "";
			}
			if(!$scope.email) {
				$scope.email = "";
			}
			if(!$scope.password) {
				$scope.password = "";
			}
			if($scope.usertypename != 2 && !$scope.codeedit) {
				$scope.codeedit = 0;
			}
			if($scope.usertypename != 2 && !$scope.hourlywagesedit) {
				$scope.hourlywagesedit = 0;
			}
			if($scope.usertypename != 2 && !$scope.overtimewagesedit) {
				$scope.overtimewagesedit = 0;
			}
			
			if(!$scope.firstname) {				
				$scope.errorFirstName = 1;
				$scope.msgFirstName = "Please enter first name";
				document.getElementById("firstname").focus();
			} else if(!$scope.usertypename) {				
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please select user type";
				document.getElementById("usertypename").focus();
			} else if($scope.usertypename == 2 && !$scope.codeedit) {				
				$scope.errorEmployeeCode = 1;
				$scope.msgEmployeeCode = "Please enter employee code";
				document.getElementById("codeedit").focus();
				return;
			} else if($scope.usertypename == 2 && !$scope.hourlywagesedit) {				
				$scope.errorHourlyWages = 1;
				$scope.msgHourlyWages = "Please enter hourly wages";
				document.getElementById("hourlywagesedit").focus();
				return;
			} else if($scope.usertypename == 2 && !$scope.overtimewagesedit) {				
				$scope.errorOverTime = 1;
				$scope.msgOverTime = "Please enter over time wages";
				document.getElementById("overtimewagesedit").focus();
				return;
			} else {			
				$scope.spin = 1;					
				var link = baseUrl+'editUser?userid='+userid+'&companyname='+$scope.companyname+'&firstname='+$scope.firstname+'&middlename='+$scope.middlename+'&lastname='+$scope.lastname+'&usertypename='+$scope.usertypename+'&gender='+$scope.gender+'&dateofbirth='+$scope.dateofbirth+'&aadharnumber='+$scope.aadharnumber+'&passportnumber='+$scope.passportnumber+'&pannumber='+$scope.pannumber+'&address1='+$scope.address1+'&address2='+$scope.address2+'&address3='+$scope.address3+'&statename='+$scope.statename+'&cityname='+$scope.cityname+'&pincode='+$scope.pincode+'&mobilenumber='+$scope.mobilenumber+'&landlinenumber='+$scope.landlinenumber+'&email='+$scope.email+'&password='+$scope.password+'&profilepicture1='+profilepicture1+'&code='+$scope.codeedit+'&hourlywages='+$scope.hourlywagesedit+'&overtimewages='+$scope.overtimewagesedit;				
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
			    	$scope.edituser = data;
			    	$scope.spin = 0;
			    	if($scope.edituser == 'Success'){			    		
			    		var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getUsers = data;
							
							$scope.userSubmitError = 0;
							$scope.userSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.userSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getUsers = "Response Fail";
						});			
						
					} else {
						$scope.userSubmitSuccess = 0;
						$scope.userSubmitError = 1;
						$scope.errorMsg = $scope.edituser;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.edituser = data;
					$scope.spin = 0;
					$scope.userSubmitSuccess = 0;
					$scope.userSubmitError = 1;
					$scope.errorMsg = "Something wrong! Please try again later!";
				});			
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;			
			angular.forEach($scope.getUsers,function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteUser = function() {
			angular.forEach($scope.getUsers,function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteUser?userid='+item.userId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteuser = data;
					}).error(function(data, status, headers, config) {
						$scope.deleteuser = "Response Fail";
					});
				}
			});
			var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getUsers = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getUsers = "Response Fail";
			});
		}
		
		$scope.editAssignProductRow = function(userid) {
			if($scope.productid==undefined || $scope.productid=="") {			
				$scope.errorProductName = 1;
				$scope.msgProductName = "Please select a product!";
				document.getElementById("productid").focus();
			} else {				
				var link = baseUrl+'editDealerProduct?userid='+userid+'&productid='+$scope.productid;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editdealerproduct = data;
					
					var link = baseUrl+'getDealerProductById?userid='+userid;            	
	    			$http.get(link).success(function(data, status, headers, config) {
	    				$scope.dealerProducts = data;
	    			}).error(function(data, status, headers, config) {
	    				$scope.dealerProducts = "Response Fail";
	    			});
	    			
				}).error(function(data, status, headers, config) {
					$scope.editdealerproduct = data;						
				});
			}
		}
		
		$scope.deleteAssignProductRow = function(userid, dealerproductid) {			
			var link = baseUrl+'deleteDealerProduct?dealerproductid='+dealerproductid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletedealerproduct = data;
				
				var link = baseUrl+'getDealerProductById?userid='+userid;            	
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.dealerProducts = data;
    			}).error(function(data, status, headers, config) {
    				$scope.dealerProducts = "Response Fail";
    			});
    			
			}).error(function(data, status, headers, config) {
				$scope.deletedealerproduct = "Response Fail";
			});						
		}
		
		$scope.addCountry = function() {			
			if(!$scope.countrycodeadd) {
				$scope.countrycodeadd = "";
			}			
			if(!$scope.countrydialingcodeadd) {
				$scope.countrydialingcodeadd = "";
			}
			if(!$scope.countrynameadd) {				
				$scope.errorCountryName = 1;
				$scope.msgCountryName = "Please enter country name";
				document.getElementById("countrynameadd").focus();
			} else {
				$scope.spinCountry = 1;				
				var link = baseUrl+'addCountry?countryname='+$scope.countrynameadd+'&countrycode='+$scope.countrycodeadd+'&countrydialingcode='+$scope.countrydialingcodeadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addcountry = data;
					$scope.spinCountry = 0;
					if($scope.addcountry == 'Success'){
						
						var link = baseUrl+'getCountries';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getCountries = data;
							
							$scope.countrySubmitError = 0;
							$scope.countrySubmitSuccess = 1;
							$scope.successMsg = "Data added";
							$timeout(function(){
								$scope.countrySubmitSuccess = 0;
								$('#countryModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getCountries = "Response Fail";
						});		
						
					} else {
						$scope.countrySubmitSuccess = 0;
						$scope.countrySubmitError = 1;
						$scope.errorMsg = $scope.addcountry;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addcountry = data;
					$scope.spinCountry = 0;
					$scope.countrySubmitSuccess = 0;
					$scope.countrySubmitError = 1;
					$scope.errorMsg = $scope.addcountry;	
				});
			}
		}
		
		$scope.addState = function() {			
			if(!$scope.statecodeadd) {
				$scope.statecodeadd = "";
			}

			if(!$scope.countrynameadd) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countrynameadd").focus();
			} else if(!$scope.statenameadd) {				
				$scope.errorStateName = 1;
				$scope.msgStateName = "Please enter state name";
				document.getElementById("statenameadd").focus();
			} else {
				$scope.spinState = 1;				
				var link = baseUrl+'addState?statename='+$scope.statenameadd+'&statecode='+$scope.statecodeadd+'&countryname='+$scope.countrynameadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addstate = data;
					$scope.spinState = 0;					
					if($scope.addstate == 'Success'){
						
						var link = baseUrl+'getStateByCountryId?countryid='+$scope.countrynameadd;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getStates = data;
							
							$scope.stateSubmitError = 0;
							$scope.stateSubmitSuccess = 1;
							$scope.successMsg = "Data added";
							$timeout(function(){
								$scope.stateSubmitSuccess = 0;
								$('#stateModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getStates = "Response Fail";
						});					
						
					} else {
						$scope.stateSubmitSuccess = 0;
						$scope.stateSubmitError = 1;
						$scope.errorMsg = $scope.addstate;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.spinState = 0;
					$scope.addstate = data;
					$scope.stateSubmitSuccess = 0;
					$scope.stateSubmitError = 1;
					$scope.errorMsg = $scope.addstate;
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
		
}]);