app.controller("BoardUpdate", function($scope, BoardFactory) {

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response[0];
		});
	};

	if (current != undefined) {
		let index = current;
		$scope.getDataByIndex(index);
	}

	$scope.update = function(id) {
		BoardFactory.updateBoard({}, function() {
			if (!id) return;

			var idx = -1
			for (var i = 0; i < $scope.boardlist.length; i++) {
				if ($scope.boardlist[i].id === id) {
					idx = i;
					break;
				}
			}

			if (idx === -1) return;

			var newItem = {
				id: id, title: $scope.boardForm.title, writer: $scope.boardForm.writer
			};


			$scope.boardlist.splice(idx, 1, newItem);

		});
	}

});