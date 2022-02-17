var app = angular.module("MyApp", []);

var url = "/HomeStayStructures/";
var adminurl = "/HomeStayStructures/";

/*var url = "/";
var adminurl = "/HomeStay/";*/

function tools_replaceAll(str, find, replace) {
   return str.replace(new RegExp(find, 'g'), replace);
}

app.directive('capitalize', function() {
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, modelCtrl) {
			var capitalize = function(inputValue) {
				if (inputValue == undefined) inputValue = '';
				var capitalized = inputValue.toUpperCase();
				if (capitalized !== inputValue) {
					modelCtrl.$setViewValue(capitalized);
					modelCtrl.$render();
				} 
				return capitalized;
			}
			modelCtrl.$parsers.push(capitalize);
			capitalize(scope[attrs.ngModel]); 
		}
	};
});

/*Filter For Convert Number into Words Start*/
app.filter('words', function() {
	  function isInteger(x) {
	        return x % 1 === 0;
	    }

	  
	  return function(value) {
	    if (value && isInteger(value))
	      return  toWords(value);
	    
	    return value;
	  };

	});


	var th = ['','Thousand','Million', 'Billion','Trillion'];
	var dg = ['Zero','One','Two','Three','Four', 'Five','Six','Seven','Eight','Nine']; 
	var tn = ['Ten','Eleven','Twelve','Thirteen', 'Fourteen','Fifteen','Sixteen', 'Seventeen','Eighteen','Nineteen'];
	var tw = ['Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety']; 


	function toWords(s)
	{  
	    s = s.toString(); 
	    s = s.replace(/[\, ]/g,''); 
	    if (s != parseFloat(s)) return 'not a number'; 
	    var x = s.indexOf('.'); 
	    if (x == -1) x = s.length; 
	    if (x > 15) return 'too big'; 
	    var n = s.split(''); 
	    var str = ''; 
	    var sk = 0; 
	    for (var i=0; i < x; i++) 
	    {
	        if ((x-i)%3==2) 
	        {
	            if (n[i] == '1') 
	            {
	                str += tn[Number(n[i+1])] + ' '; 
	                i++; 
	                sk=1;
	            }
	            else if (n[i]!=0) 
	            {
	                str += tw[n[i]-2] + ' ';
	                sk=1;
	            }
	        }
	        else if (n[i]!=0) 
	        {
	            str += dg[n[i]] +' '; 
	            if ((x-i)%3==0) str += 'hundred ';
	            sk=1;
	        }


	        if ((x-i)%3==1)
	        {
	            if (sk) str += th[(x-i-1)/3] + ' ';
	            sk=0;
	        }
	    }
	    if (x != s.length)
	    {
	        var y = s.length; 
	        str += 'point '; 
	        for (var i=x+1; i<y; i++) str += dg[n[i]] +' ';
	    }
	    return str.replace(/\s+/g,' ');
	}
	/*Filter For Convert Number into Words End*/