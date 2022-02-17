app.controller('dashboardCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
    
	var link = baseUrl+'getEnquiryCurrentStats';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getEnquiryCurrentStats = data;
	}).error(function(data, status, headers, config) {
		$scope.getEnquiryCurrentStats = "Response Fail";
	});
	
	
}]);