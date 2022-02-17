app.controller('employeeActivitiesCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	var date = new Date();
	
	var firstDay = new Date(date.getFullYear(), date.getMonth()- 1, 1);
	$scope.fromdate = $filter('date')(new Date(firstDay), "dd/MM/yyyy");
	var lastDay = new Date(date.getFullYear(), date.getMonth() , 0);
	$scope.todate = $filter('date')(new Date(lastDay), "dd/MM/yyyy");
	
	$scope.totalEmployeeSalary = 0;
	$scope.getCurrentMonthData = function(){
		var link = baseUrl+'getEmpActivityByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEmployeeActivities = data;
			
			var totalOTSalary = 0;
			var totalRSalary = 0;
			for(i in $scope.getEmployeeActivities){
				totalOTSalary = totalOTSalary + $scope.getEmployeeActivities[i].overTimeSalary;
				totalRSalary = totalRSalary + $scope.getEmployeeActivities[i].regularSalary;
			}
			$scope.totalEmployeeSalary = totalRSalary+totalOTSalary;
		}).error(function(data, status, headers, config) {
			$scope.getEmployeeActivities = data;
		});
	}
	
	$scope.importEmployeeActivities = function() {
		if(document.getElementById("employeefile").value == "" || document.getElementById("employeefile").value == undefined){			
			$scope.errorFile = 1;
			$scope.msgFile = "Please choose file!";
			$timeout(function(){
				$scope.errorFile = 0; 				
			}, 2000);
		} else {
			$scope.spin = 1;				
			var link = baseUrl+'importEmployeeActivities';				
			var formData=new FormData();
			formData.append("employeefile",employeefile.files[0]);				
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
				$scope.addimportemp = data;						
				if($scope.addimportemp != 0){
    				$scope.spin = 0;
    				$scope.submitSuccess = 1;
    				$scope.msgSuccess = "Total "+$scope.addimportemp+" activities imported from file";
    				$timeout(function(){
    					/*$('#importModal').modal('hide');*/
    					$window.location.href = adminurl+'employee_activity';
    				}, 3000);
    			} else {
    				$scope.spin = 0;    				
    				$scope.submitError = 1;
    				$scope.msgError = "Data not inserted! Something went wrong!";
    				$timeout(function(){
    					$scope.submitError = 0;				
    				}, 3000);
    			}
			}).error(function(data, status, headers, config) {
				$scope.addimportemp = data;
				$scope.spin = 0;
				$scope.submitError = 1;
				$scope.msgError = $scope.addimportemp;
				$timeout(function(){
					$scope.submitError = 0;
				}, 5000);
			});
		}
	}
	
	$scope.getEmployeeDetail = function(empcode){
		var link = baseUrl+'getEmpActivityByDateAndCode?code='+empcode+'&fromdate='+$scope.fromdate+'&todate='+$scope.todate;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEmployeeDetails = data;
		}).error(function(data, status, headers, config) {
			$scope.getEmployeeDetails = data;
		});
		
	}
	
	$scope.exportData = function() {
		var blob = new Blob([document.getElementById('exportable').innerHTML], {
			type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		});
		saveAs(blob, "Employee Activities List.xls");
	}
	$scope.exportData1 = function() {
		var blob = new Blob([document.getElementById('exportable1').innerHTML], {
			type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		});
		saveAs(blob, "Employee Activities List.xls");
	}
	
}]);