app.controller('resetPasswordCtrl', function($scope, $http, $window, $filter, $location, $timeout) {
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.setFlag = function() {
		$scope.errorEmail = 0;
		$scope.msgEmail = "";
		$scope.errorNewPassword = 0;
		$scope.msgNewPassword = "";
		$scope.errorConfirmPassword = 0;
		$scope.msgConfirmPassword = "";
		$scope.submitSuccess = 0;
		$scope.submitError = 0;
	}
	
	$scope.resetPassword = function() {	
		if(!$scope.email) {			
			$scope.errorEmail = 1;
			$scope.msgEmail = "Please enter your registered email";
			document.getElementById("email").focus();
		} else if(!$scope.newpassword){
			$scope.errorNewPassword = 1;
			$scope.msgNewPassword = "Please enter your new password";
			document.getElementById("newpassword").focus();
		} else if(!$scope.confirmpassword){
			$scope.errorConfirmPassword = 1;
			$scope.msgConfirmPassword = "Please re-enter your new password";
			document.getElementById("confirmpassword").focus();
		} else if($scope.confirmpassword != $scope.newpassword){
			$scope.errorConfirmPassword = 1;
			$scope.msgConfirmPassword = "Password not matched!";
			document.getElementById("confirmpassword").focus();
		} else {
			$scope.spin=1;		
			
			var link = baseUrl+'resetPassword?email='+$scope.email+'&password='+$scope.newpassword;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.resetpassword = data;
				
				if($scope.resetpassword == "Success"){
					$scope.spin = 0;
					$scope.submitSuccess=1;
					$scope.msgSuccess="Your password has been reset! Please login to continue.";
					$timeout(function(){
						$scope.submitSuccess = 0;
						$window.location.href = adminurl;
					}, 2000);
				} else {
					$scope.spin = 0;
					$scope.submitError=1;
					$scope.msgError="Sorry! Email not found! Please try again";
				}				
			}).error(function(data, status, headers, config) {
				$scope.spin = 0;
				$scope.submitError=1;
				$scope.msgError="Sorry! Something went wrong! Please try after sometime.";
			});
		}
	}
	
});