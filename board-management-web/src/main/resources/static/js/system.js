app.controller("BoardSystem", function($scope, SystemFactory) {

	$scope.addSystem = false;

	$scope.add = function() {
		$scope.addSystem = true;
	};

	$scope.cancel = function() {
		$scope.addSystem = false;
	};
	
//	let findPage = function

	$scope.create = function(key, value, description) {

		const systemData = {
			key: key,
			value: value,
			description: description
		}

		SystemFactory.createSystem({}, systemData, function() {
			alert("생성이 완료되었습니다.");
		})
	};
	
	


});