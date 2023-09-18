app.controller("BoardCreate", function($scope, BoardFactory) {

	// 'insert' 함수 정의
	$scope.insert = function() {
		BoardFactory.createBoard({}, function() {

			var newIndex;

			// '$scope.boardlist' 배열이 비어있는 경우
			if (!$scope.boardlist.length) {
				// 'newIndex'를 1로 초기화합니다.
				newIndex = 1;
			} else {
				// 'newIndex'를 현재 배열에서 가장 큰 'id' 값에 1을 더한 값으로 설정합니다.
				newIndex = $scope.boardlist[$scope.boardlist.length - 1].id + 1;
			}

			// 'newIndex' 값을 가진 요소를 '$scope.boardlist' 배열에 추가합니다.
			$scope.boardlist.push(newIndex);
		});
	}
});
