app.controller('companyCtrl', [ '$scope', '$filter', '$http', '$window', '$location', '$timeout',
	function($scope, $filter, $http, $window, $location, $timeout) {
	
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;
	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	    
	    $scope.numberOfPages=function() {
			return Math.ceil($scope.getCompany.length/$scope.pageSize);
		}
		
		var baseUrl = $location.protocol()+"://"+location.host+url;
		
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
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getCompanyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCompany = data;
		}).error(function(data, status, headers, config) {
			$scope.getCompany = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;				
				var link = baseUrl+'getCompanyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCompany = data;
				}).error(function(data, status, headers, config) {
					$scope.getCompany = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCompanyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCompany = data;
			}).error(function(data, status, headers, config) {
				$scope.getCompany = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllCompany';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCompany = data;
				}).error(function(data, status, headers, config) {
					$scope.getCompany = "Response Fail";
				});
			} else {
				var link = baseUrl+'getCompanyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCompany = data;
				}).error(function(data, status, headers, config) {
					$scope.getCompany = "Response Fail";
				});
			}
		}
		
		$scope.searchCompany = function() {
			var search = $scope.search;		
			if(search == undefined || search == "") {
				var link = baseUrl+'getCompanyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCompany = data;
				}).error(function(data, status, headers, config) {
					$scope.getCompany = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchCompany?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCompany = data;
				}).error(function(data, status, headers, config) {
					$scope.getCompany = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCompanyName = 0;
			$scope.msgCompanyName = "";
			$scope.errorWebsite = 0;
			$scope.msgWebsite = "";
			$scope.errorAdd1 = 0;
			$scope.msgAdd1 = "";
			$scope.errorAdd2 = 0;
			$scope.msgAdd2 = "";
			$scope.errorCountry = 0;
			$scope.msgCountry = "";
			$scope.errorState = 0;
			$scope.msgState = "";
			$scope.errorCity = 0;
			$scope.msgCity = "";
			$scope.errorPincode = 0;
			$scope.msgPincode = "";
			$scope.errorEmail = 0;
			$scope.msgEmail = "";
			$scope.errorBankName = 0;
			$scope.msgBankName = "";
			$scope.errorBankAdd = 0;
			$scope.msgBankAdd = "";
			$scope.errorIFSC = 0;
			$scope.msgIFSC = "";
			$scope.errorPAN = 0;
			$scope.msgPAN = "";
			$scope.errorAccountNumber = 0;
		}
	
		$scope.addCompany = function() {
			if($scope.contactno1add==undefined || $scope.contactno1add==""){
				$scope.contactno1add = "";
			}
			if($scope.contactno2add==undefined || $scope.contactno2add==""){
				$scope.contactno2add = "";
			}
			if($scope.faxnoadd==undefined || $scope.faxnoadd==""){
				$scope.faxnoadd = "";
			}
			if($scope.gstnoadd==undefined || $scope.gstnoadd==""){
				$scope.gstnoadd = "";
			}
			if($scope.swiftadd==undefined || $scope.swiftadd==""){
				$scope.swiftadd = "";
			}
			
			if($scope.companynameadd==undefined || $scope.companynameadd=="") {			
				$scope.errorCompanyName = 1;
				$scope.msgCompanyName = "Please enter company name";
				document.getElementById("companynameadd").focus();
			} else if ($scope.websiteadd==undefined || $scope.websiteadd=="") {			
				$scope.errorWebsite = 1;
				$scope.msgWebsite = "Please enter your website";
				document.getElementById("websiteadd").focus();
			} else if ($scope.websiteadd==undefined || $scope.websiteadd=="") {			
				$scope.errorWebsite = 1;
				$scope.msgWebsite = "Please enter your website";
				document.getElementById("websiteadd").focus();
			} else if ($scope.emailadd==undefined || $scope.emailadd=="") {			
				$scope.errorEmail = 1;
				$scope.msgEmail = "Please enter email";
				document.getElementById("emailadd").focus();
			} else if ($scope.banknameadd==undefined || $scope.banknameadd=="") {			
				$scope.errorBankName = 1;
				$scope.msgBankName = "Please enter bank name";
				document.getElementById("banknameadd").focus();
			} else if ($scope.bankaddadd==undefined || $scope.bankaddadd=="") {			
				$scope.errorBankAdd = 1;
				$scope.msgBankAdd = "Please enter bank address";
				document.getElementById("bankaddadd").focus();
			} else if ($scope.accnoadd==undefined || $scope.accnoadd=="") {			
				$scope.errorAccountNumber = 1;
				$scope.msgAccountNumber = "Please enter account number";
				document.getElementById("accnoadd").focus();
			} else if ($scope.ifscodeadd==undefined || $scope.ifscodeadd=="") {			
				$scope.errorIFSC = 1;
				$scope.msgIFSC = "Please enter ifs code";
				document.getElementById("ifscodeadd").focus();
			} else if ($scope.pannoadd==undefined || $scope.pannoadd=="") {			
				$scope.errorPAN = 1;
				$scope.msgPAN = "Please enter PAN";
				document.getElementById("pannoadd").focus();
			} else if ($scope.add1add==undefined || $scope.add1add=="") {			
				$scope.errorAdd1 = 1;
				$scope.msgAdd1 = "Please enter Address Line1";
				document.getElementById("add1add").focus();
			} else if ($scope.add2add==undefined || $scope.add2add=="") {			
				$scope.errorAdd2 = 1;
				$scope.msgAdd2 = "Please enter Address Line2";
				document.getElementById("add2add").focus();
			} else if ($scope.countryidadd==undefined || $scope.countryidadd=="") {			
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countryidadd").focus();
			} else if ($scope.stateidadd==undefined || $scope.stateidadd=="") {			
				$scope.errorState = 1;
				$scope.msgState = "Please select state";
				document.getElementById("stateidadd").focus();
			} else if ($scope.cityadd==undefined || $scope.cityadd=="") {			
				$scope.errorCity = 1;
				$scope.msgCity = "Please enter city";
				document.getElementById("cityadd").focus();
			} else if ($scope.pincodeadd==undefined || $scope.pincodeadd=="") {			
				$scope.errorPincode = 1;
				$scope.msgPincode = "Please enter pincode";
				document.getElementById("pincodeadd").focus();
			} else {
				$scope.spin = 1;		
				var link = baseUrl+'addCompany?companyname='+$scope.companynameadd+'&gstno='+$scope.gstnoadd+'&contactno1='+$scope.contactno1add+'&contactno2='+$scope.contactno2add+'&faxno='+$scope.faxnoadd+'&website='+$scope.websiteadd+'&email='+$scope.emailadd+'&bankname='+$scope.banknameadd+'&bankadd='+$scope.bankaddadd+'&accno='+$scope.accnoadd+'&ifscode='+$scope.ifscodeadd+'&swift='+$scope.swiftadd+'&panno='+$scope.pannoadd+'&add1='+$scope.add1add+'&add2='+$scope.add2add+'&countryid='+$scope.countryidadd+'&stateid='+$scope.stateidadd+'&city='+$scope.cityadd+'&pincode='+$scope.pincodeadd;				
				var formData=new FormData();
				formData.append("image",companylogoadd.files[0]);				
				$http({
			        method: 'POST',
			        url: link,
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction)
			        {
			        	return data;
			        }
				}).success(function(data, status, headers, config) {
	    			$scope.addcompany = data;
	    			if($scope.addcompany == "Success"){
	    				$scope.spin = 0;
	    				$scope.companySubmitSuccess = 1;
	    				$scope.msgSuccess = "Data updated successfully";
	    				$timeout(function(){
	        				$window.location.href = adminurl + 'manage_company'; 				
	    				}, 3000);
	    			} 
					if($scope.addcompany != "Success") {
	    				$scope.spin = 0;
	    				$scope.companySubmitError = 1;
	    				$scope.msgError = $scope.addcompany;
	    				$timeout(function(){
	    					$scope.companySubmitError = 0; 				
	    				}, 5000);
	    			} 			
				}).error(function(data, status, headers, config) {
					$scope.addcompany = data;
					$scope.spin = 0;
					$scope.companySubmitError = 1;
					$scope.msgError = $scope.addcompany;
					$timeout(function(){
						$scope.companySubmitError = 0; 				
					}, 5000);
				});				    			
			}
		}
		
		$scope.getCompanyDetail = function() {
			var link = baseUrl+'getCompanyById?companyid='+1;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCompanyById = data;
				$scope.companyid = $scope.getCompanyById.companyId;
				$scope.companyname = $scope.getCompanyById.companyName;
				$scope.companylogo = $scope.getCompanyById.companyLogo;
				$scope.gstno = $scope.getCompanyById.gstNo;
				$scope.contactno1 = $scope.getCompanyById.contactNo1;
				$scope.contactno2 = $scope.getCompanyById.contactNo2;
				$scope.faxno = $scope.getCompanyById.faxNo;
				$scope.website = $scope.getCompanyById.website;
				$scope.email = $scope.getCompanyById.email;
				$scope.bankname = $scope.getCompanyById.bankName;
				$scope.bankadd = $scope.getCompanyById.bankAdd;
				$scope.accno = $scope.getCompanyById.accountNumber;
				$scope.ifscode = $scope.getCompanyById.ifscCode;
				$scope.swift = $scope.getCompanyById.swiftCode;
				$scope.panno = $scope.getCompanyById.panNo;
				$scope.add1 = $scope.getCompanyById.add1;
				$scope.add2 = $scope.getCompanyById.add2;
				$scope.countryid = $scope.getCompanyById.countryId;
				$scope.stateid = $scope.getCompanyById.stateId;
				$scope.city = $scope.getCompanyById.city;
				$scope.pincode = $scope.getCompanyById.pincode;			
				
			}).error(function(data, status, headers, config) {
				$scope.getCompanyById = "Response Fail";
			});
		}
		
		$scope.editCompany = function(companyid) {
			if($scope.contactno1==undefined || $scope.contactno1==""){
				$scope.contactno1 = "";
			}
			if($scope.contactno2==undefined || $scope.contactno2==""){
				$scope.contactno2 = "";
			}
			if($scope.faxno==undefined || $scope.faxno==""){
				$scope.faxno = "";
			}
			if($scope.gstno==undefined || $scope.gstno==""){
				$scope.gstno = "";
			}
			if($scope.swiftadd==undefined || $scope.swiftadd==""){
				$scope.swiftadd = "";
			}
			
			if($scope.companyname==undefined || $scope.companyname=="") {			
				$scope.errorCompanyName = 1;
				$scope.msgCompanyName = "Please enter company name";
				document.getElementById("companyname").focus();
			} else if ($scope.website==undefined || $scope.website=="") {			
				$scope.errorWebsite = 1;
				$scope.msgWebsite = "Please enter your website";
				document.getElementById("website").focus();
			} else if ($scope.email==undefined || $scope.email=="") {			
				$scope.errorEmail = 1;
				$scope.msgEmail = "Please enter email";
				document.getElementById("email").focus();
			} else if ($scope.bankname==undefined || $scope.bankname=="") {			
				$scope.errorBankName = 1;
				$scope.msgBankName = "Please enter bank name";
				document.getElementById("bankname").focus();
			} else if ($scope.bankadd==undefined || $scope.bankadd=="") {			
				$scope.errorBankAdd = 1;
				$scope.msgBankAdd = "Please enter bank address";
				document.getElementById("bankadd").focus();
			} else if ($scope.accno==undefined || $scope.accno=="") {			
				$scope.errorAccountNumber = 1;
				$scope.msgAccountNumber = "Please enter account number";
				document.getElementById("accno").focus();
			} else if ($scope.ifscode==undefined || $scope.ifscode=="") {			
				$scope.errorIFSC = 1;
				$scope.msgIFSC = "Please enter ifs code";
				document.getElementById("ifscode").focus();
			} else if ($scope.panno==undefined || $scope.panno=="") {			
				$scope.errorPAN = 1;
				$scope.msgPAN = "Please enter PAN";
				document.getElementById("panno").focus();
			} else if ($scope.add1==undefined || $scope.add1=="") {			
				$scope.errorAdd1 = 1;
				$scope.msgAdd1 = "Please enter Address Line1";
				document.getElementById("add1add").focus();
			} else if ($scope.add2==undefined || $scope.add2=="") {			
				$scope.errorAdd2 = 1;
				$scope.msgAdd2 = "Please enter Address Line2";
				document.getElementById("add2").focus();
			} else if ($scope.countryid==undefined || $scope.countryid=="") {			
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countryid").focus();
			} else if ($scope.stateid==undefined || $scope.stateid=="") {			
				$scope.errorState = 1;
				$scope.msgState = "Please select state";
				document.getElementById("stateid").focus();
			} else if ($scope.city==undefined || $scope.city=="") {			
				$scope.errorCity = 1;
				$scope.msgCity = "Please enter city";
				document.getElementById("city").focus();
			} else if ($scope.pincode==undefined || $scope.pincode=="") {			
				$scope.errorPincode = 1;
				$scope.msgPincode = "Please enter pincode";
				document.getElementById("pincode").focus();
			} else {
				$scope.spin = 1;		
				var link = baseUrl+'editCompany?companyid='+companyid+'&companyname='+$scope.companyname+'&companylogo='+$scope.companylogo+'&gstno='+$scope.gstno+'&contactno1='+$scope.contactno1+'&contactno2='+$scope.contactno2+'&faxno='+$scope.faxno+'&website='+$scope.website+'&email='+$scope.email+'&bankname='+$scope.bankname+'&bankadd='+$scope.bankadd+'&accno='+$scope.accno+'&ifscode='+$scope.ifscode+'&swift='+$scope.swift+'&panno='+$scope.panno+'&add1='+$scope.add1+'&add2='+$scope.add2+'&countryid='+$scope.countryid+'&stateid='+$scope.stateid+'&city='+$scope.city+'&pincode='+$scope.pincode;				
				var formData=new FormData();
				formData.append("image",newcompanylogo.files[0]);				
				$http({
			        method: 'POST',
			        url: link,
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction)
			        {
			        	return data;
			        }
				}).success(function(data, status, headers, config) {
	    			$scope.editcompany = data;
	    			if($scope.editcompany == "Success"){
	    				$scope.spin = 0;
	    				$scope.companySubmitSuccess = 1;
	    				$scope.msgSuccess = "Data updated successfully";
	    				$timeout(function(){
	        				$window.location.href = adminurl + 'manage_company'; 				
	    				}, 3000);
	    			} 
					if($scope.editcompany != "Success") {
	    				$scope.spin = 0;
	    				$scope.companySubmitError = 1;
	    				$scope.msgError = $scope.editcompany;
	    				$timeout(function(){
	    					$scope.companySubmitError = 0; 				
	    				}, 5000);
	    			} 			
				}).error(function(data, status, headers, config) {
					$scope.editcompany = data;
					$scope.spin = 0;
					$scope.companySubmitError = 1;
					$scope.msgError = $scope.editcompany;
					$timeout(function(){
						$scope.companySubmitError = 0; 				
					}, 5000);
				});				    			
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {		
			$scope.d = 0;			
			angular.forEach($scope.getCompany,function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteCompany = function() {
			angular.forEach($scope.getCompany, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteCompany?companyid='+item.companyId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletecompany = data;
					}).error(function(data, status, headers, config) {
						$scope.deletecompany = "Response Fail";
					});
				}
			});
			$window.location.href = adminurl+'manage_company';
		}
	} 
]);