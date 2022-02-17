/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
/* For Print data with html tag end */
app.controller('printQuotationCtrl', [ '$scope', '$filter', '$http', '$window', '$location', '$timeout',
	function($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.getQuotationDetailForPrint = function(quotationid){
		
		var link = baseUrl+'getCompanyById?companyid='+1;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCompanyById = data;
			if($scope.getCompanyById.stateId == 1){
				$scope.getCompanyById.stateName = "Gujarat";
			}
			if($scope.getCompanyById.countryId == 1){
				$scope.getCompanyById.countryName = "India";
			}
			
			var link = baseUrl+'getQuotationDetailById?quotationid='+quotationid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getQuotationDetail = data;
				
				var link = baseUrl+'getUserDetailById?userid='+$scope.getQuotationDetail.clientId;		
				$http.get(link).success(function(data, status, headers, config) {
					$scope.userDetail = data;
					
					if($scope.userDetail.stateId == 1){
						$scope.userDetail.stateName = "Gujarat";
					}		
					
				}).error(function(data, status, headers, config) {
					$scope.userDetail = "Response Fail";
				});
				
				var link = baseUrl+'getQuotationProductByQuotationId?quotationid='+quotationid;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotationProduct = data;
					$scope.totalAmount = 0;
					angular.forEach($scope.getQuotationProduct,function(item) {
						$scope.amount = item.productQty * item.salePrice;
						$scope.totalAmount = $scope.totalAmount+$scope.amount;
					});
				}).error(function(data, status, headers, config) {
					$scope.getQuotationProduct = "Response Fail";
				});
				
				/*var link = baseUrl+'getQuotationProductSpecificationByQuotationId?quotationid='+quotationid;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotationProductSpecification = data;				
				}).error(function(data, status, headers, config) {
					$scope.getQuotationProductSpecification = "Response Fail";
				});
				
				var link = baseUrl+'getQuotationProductScopeOfSupplyByQuotationId?quotationid='+quotationid;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getQuotationProductScopeOfSupply = data;				
				}).error(function(data, status, headers, config) {
					$scope.getQuotationProductScopeOfSupply = "Response Fail";
				});*/
				
				var link = baseUrl+'getAllTerm';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTerms = data;
				}).error(function(data, status, headers, config) {
					$scope.getTerms = "Response Fail";
				});
				
				var link = baseUrl+'getQuotationTermStatementByQuotationId?quotationid='+quotationid+'&termid='+1;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStandardScopeOfSupply = data;				
				}).error(function(data, status, headers, config) {
					$scope.getStandardScopeOfSupply = "Response Fail";
				});
				
				var link = baseUrl+'getQuotationTermStatementByQuotationId?quotationid='+quotationid+'&termid='+2;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getExclusion = data;				
				}).error(function(data, status, headers, config) {
					$scope.getExclusion = "Response Fail";
				});
				
				var link = baseUrl+'getQuotationTermStatementByQuotationId?quotationid='+quotationid+'&termid='+3;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRequirmentToBeMade = data;				
				}).error(function(data, status, headers, config) {
					$scope.getRequirmentToBeMade = "Response Fail";
				});
				
				var link = baseUrl+'getQuotationTermStatementByQuotationId?quotationid='+quotationid+'&termid='+4;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTermAndCondition = data;				
				}).error(function(data, status, headers, config) {
					$scope.getTermAndCondition = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.getQuotationDetail = "Response Fail";
			});
			
		}).error(function(data, status, headers, config) {
			$scope.getCompanyById = "Response Fail";
		});
		
	}
}]);