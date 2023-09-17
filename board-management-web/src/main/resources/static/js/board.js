app.controller("BoardCtrl", function($scope, BoardFactory) {

	BoardFactory.query({}, function(response) {
		$scope.boardlist = response;
	});
	
});
