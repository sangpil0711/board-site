app.controller("BoardCtrl", function($scope, BoardFactory) {

	BoardFactory.query({}, function(response) {
		$scope.boardlist = response;
	});

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
