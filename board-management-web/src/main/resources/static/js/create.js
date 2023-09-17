app.controller("BoardCreate", function($scope, BoardFactory) {

	$scope.insert = function() {
		BoardFactory.createBoard({}, function() {

			var newIndex;

			if (!$scope.boardlist.length) {
				newId = 1;
			} else {
				newId = $scope.boardlist[$scope.boardlist.length - 1].id + 1;
			}

			var newItem = {
				index: newIndex, title: $scope.boardForm.title, writer: $scope.boardForm.writer
			};

			$scope.boardlist.push(newItem); //배열에 요소 추가
		
		});
	}
});