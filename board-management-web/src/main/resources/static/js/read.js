app.controller("BoardRead", function($scope, BoardFactory, $window) {

	// 현재 페이지의 데이터를 가져오는 함수
	$scope.getDataByIndex = function(index) {
		// BoardFactory를 이용하여 서버에서 해당 인덱스(index)에 해당하는 게시글 데이터를 읽어옵니다.
		// 응답 데이터는 response 변수에 저장됩니다.
		BoardFactory.readBoard({ index: index }, function(response) {
			// 응답 데이터의 첫 번째 항목을 $scope.board 변수에 저장합니다.
			$scope.board = response[0];
		});
	};

	// current 변수가 정의되어 있다면 해당 인덱스로 데이터를 가져오는 함수 호출
	if (current != undefined) {
		let index = current;
		$scope.getDataByIndex(index);
	}

	// 게시글 삭제 함수
	$scope.remove = function(index) {
		// BoardFactory를 이용하여 서버에서 해당 인덱스(index)에 해당하는 게시글을 삭제합니다.
		BoardFactory.deleteBoard({ index: index }, function() {

			if (!index) return; // 예외처리

			// 'idx' 변수를 -1로 초기화합니다. 이 변수는 찾고자 하는 'index' 값을 가진 요소의 위치를 나타냅니다.
			var idx = -1;

			// '$scope.boardlist' 배열의 모든 요소를 반복합니다.
			for (var i = 0; i < $scope.boardlist.length; i++) {
				// 현재 반복 중인 요소의 'index' 속성값이 우리가 찾고자 하는 'index'와 일치하는지 확인합니다.
				if ($scope.boardlist[i].index === index) {
					// 일치하는 경우, 'idx' 변수에 현재 요소의 인덱스를 저장합니다.
					idx = i;
					// 반복문을 종료합니다.
					break;
				}
			}

			if (idx === -1) return; // 예외처리

			// JavaScript에서 배열에서 해당 인덱스의 요소를 삭제합니다.
			$scope.boardlist.splice(idx, 1);

		});
		var updateUrl = '/board';
		$window.location.href = updateUrl;
	};

	// 게시글 수정 페이지로 리디렉션하는 함수
	$scope.redirectToUpdate = function(index) {
		var updateUrl = '/board/update/' + index;
		// 페이지 리디렉션을 수행합니다.
		$window.location.href = updateUrl;
	};
});
