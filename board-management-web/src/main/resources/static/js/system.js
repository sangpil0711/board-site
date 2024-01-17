app.controller("BoardSystem", function($scope) {

	console.debug("asdasdasd");

	$scope.addSystem = false;
	
	$scope.add = function() {
		$scope.addSystem = true;
		console.debug($scope.addSystem);
	};

});