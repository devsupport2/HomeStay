/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
/* For Print data with html tag end */
app.controller('quotationTemplateCtrl', [ '$scope', '$filter', '$http', '$window', '$location', '$timeout',
	function($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.setFlag = function() {
		$scope.errorLetterDescription = 0;
		$scope.errorStatement = 0;
		$scope.errorSequence = 0;
	}
	
	$scope.getQuotationTempleteDetail = function() {
		var link = baseUrl+'getCoverLetterDetailById?id='+1;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.coverLetterDetailById = data;
			$scope.coverletterid = $scope.coverLetterDetailById.coverLetterId;
			$scope.coverlettertitle = $scope.coverLetterDetailById.coverLetterTitle;
			CKEDITOR.instances.letterdescription.setData($scope.coverLetterDetailById.coverLetterDescription);		
		}).error(function(data, status, headers, config) {
			$scope.coverLetterDetailById = "Response Fail";
		});
		
		var link = baseUrl+'getAllTerm';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getTerms = data;
		}).error(function(data, status, headers, config) {
			$scope.getTerms = "Response Fail";
		});
	}
	
	$scope.editQuotationCoverLetter = function(id) {
		$scope.description = CKEDITOR.instances.letterdescription.getData();
		if ($scope.description==undefined || $scope.description=="") {			
			$scope.errorLetterDescription = 1;
			$scope.msgLetterDescription = "Please enter description";
			document.getElementById("description").focus();
		} else {
			$scope.spin = 1;
			
			var description = encodeURIComponent($scope.description);
			
			var formData=new FormData();
			formData.append("description",description);
			
			var link = baseUrl+'editCoverLetter?id='+id;			
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) {return data;}
			}).success(function(data, status, headers, config) {
				$scope.editcoverletter = data;

				if($scope.editcoverletter == "Success"){
    				$scope.spin = 0;
    				$scope.letterSubmitSuccess = 1;
    				$scope.msgSuccess = "Data updated successfully";
    				$timeout(function(){
        				$window.location.href = adminurl + 'manage_quotation_template'; 				
    				}, 3000);
    			} 
				if($scope.editcoverletter != "Success") {
    				$scope.spin = 0;
    				$scope.letterSubmitSuccess = 1;
    				$scope.msgError = $scope.editcoverletter;
    				$timeout(function(){
    					$scope.letterSubmitSuccess = 0; 				
    				}, 5000);
    			} 
				
			}).error(function(data, status, headers, config) {
				$scope.editcoverletter = "Response Fail";
				$scope.spin = 0;
				$scope.letterSubmitSuccess = 1;
				$scope.msgError = "Something went wrong!";
				$timeout(function(){
					$scope.letterSubmitSuccess = 0; 				
				}, 5000);
			});
		}	
	}
	
	$scope.getTermDetail = function(termid) {
		$scope.termid = termid;
		for (i in $scope.getTerms) {
            if ($scope.getTerms[i].termMasterId == termid) {
            	$scope.termtitle = $scope.getTerms[i].termTitle;            	
            }            
		}
		
		var link = baseUrl+'getTermStatementById?termid='+termid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.termStatementById = data;			
		}).error(function(data, status, headers, config) {
			$scope.termStatementById = "Response Fail";
		});
		
	}
	
	$scope.checkRecordSelectForDelete = function() {
		$scope.d = 0;			
		angular.forEach($scope.getTerms, function(item) {
			if (item.selected) {
				$scope.d = $scope.d + 1
			}
		});
	}
	
	$scope.deleteTerm = function() {
		angular.forEach($scope.getTerms, function(item) {
			if(item.selected) {
				var link = baseUrl+'deleteTerm?termid='+item.termMasterId;
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deleteterm = data;
				}).error(function(data, status, headers, config) {
					$scope.deleteterm = "Response Fail";
				});
			}
			var link = baseUrl+'getAllTerm';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getTerms = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getTerms = "Response Fail";
			});
		});			
	}
	
	$scope.addTermItemRow = function(termid) {
		if (!$scope.label) {
			$scope.label = "";
		}
		if (!$scope.sequence) {			
			$scope.errorSequence = 1;
			$scope.msgSequence = "Please enter sequence";
			document.getElementById("sequence").focus();
		} else if (!$scope.statement) {			
			$scope.errorStatement = 1;
			$scope.msgStatement = "Please enter statement";
			document.getElementById("statement").focus();
		} else {
			$scope.spinItem = 1;
			
			$scope.statement = tools_replaceAll($scope.statement, "&" , "$" );
			$scope.statement = tools_replaceAll($scope.statement, "#" , "~" );
			$scope.statement = tools_replaceAll($scope.statement, "%" , "!" );
			
			var link = baseUrl+'addTermItemRow?termid='+termid+'&sequence='+$scope.sequence+'&label='+$scope.label+'&statement='+$scope.statement;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addtermitem = data;
				if($scope.addtermitem == "Success"){
					var link = baseUrl+'getTermStatementById?termid='+termid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.termStatementById = data;
						$scope.label = "";
						$scope.statement = "";
						$scope.sequence = $scope.sequence + 1;
					}).error(function(data, status, headers, config) {
						$scope.termStatementById = "Response Fail";
					});
    			} 
				if($scope.addtermitem != "Success") {
					$scope.spinItem = 0;
    				$scope.itemSubmitError = 1;
    				$scope.msgError = "Smoething went wrong!";
    				$timeout(function(){
    					$scope.itemSubmitError = 0; 				
    				}, 5000);
    			} 
				
			}).error(function(data, status, headers, config) {
				$scope.spinItem = 0;
				$scope.itemSubmitError = 1;
				$scope.msgError = "Smoething went wrong!";
				$timeout(function(){
					$scope.itemSubmitError = 0; 				
				}, 5000);
			});
		}	
	}
	
	$scope.deleteTermItemRow = function(termstatementid, termid) {		
		var link = baseUrl+'deleteTermItem?termstatementid='+termstatementid;
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deletetermitem = data;
			
			var link = baseUrl+'getTermStatementById?termid='+termid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.termStatementById = data;				
			}).error(function(data, status, headers, config) {
				$scope.termStatementById = "Response Fail";
			});
			
		}).error(function(data, status, headers, config) {
			$scope.deletetermitem = "Response Fail";
		});					
	}
	
	$scope.editTerm = function(termid) {		
		if (!$scope.termtitle) {			
			$scope.errorTermTitle = 1;
			$scope.msgTermTitle = "Please enter term title";
			document.getElementById("termtitle").focus();
		} else {
			$scope.termtitle = tools_replaceAll($scope.termtitle, "&" , "$" );
			$scope.termtitle = tools_replaceAll($scope.termtitle, "#" , "~" );
			$scope.termtitle = tools_replaceAll($scope.termtitle, "%" , "!" );
			
			$scope.spinTerm = 1;			
			var link = baseUrl+'editTerm?termid='+termid+'&termtitle='+$scope.termtitle;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editterm = data;
				if($scope.editterm == "Success"){					
					$scope.spinTerm = 0;
					$scope.termSubmitError = 0;
    				$scope.termSubmitSuccess = 1;
    				$scope.msgSuccess = "Data updated!";
    				$timeout(function(){
    					$scope.termSubmitError = 0; 				
    				}, 2000);
    			} 
				if($scope.editterm != "Success") {
					$scope.spinTerm = 0;
    				$scope.termSubmitError = 1;
    				$scope.termSubmitSuccess = 0;
    				$scope.msgError = "Smoething went wrong!";
    				$timeout(function(){
    					$scope.termSubmitError = 0; 				
    				}, 5000);
    			} 
				
			}).error(function(data, status, headers, config) {
				$scope.spinTerm = 0;
				$scope.termSubmitSuccess = 0;
				$scope.termSubmitError = 1;
				$scope.msgError = "Smoething went wrong!";
				$timeout(function(){
					$scope.termSubmitError = 0; 				
				}, 5000);
			});
		}	
	}
}]);