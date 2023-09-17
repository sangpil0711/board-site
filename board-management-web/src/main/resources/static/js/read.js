app.controller("BoardRead", function($scope, BoardFactory) {

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response[0];
		});
	};

	if (current != undefined) {
		let index = current;
		$scope.getDataByIndex(index);
	}
	
});