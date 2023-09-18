app.controller("BoardUpdate", function($scope, BoardFactory, $window) {

	// 현재 페이지의 데이터를 가져오는 함수
	$scope.getDataByIndex = function(index) {
		// BoardFactory를 이용하여 서버에서 해당 인덱스(index)에 해당하는 게시글 데이터를 읽어옵니다.
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

	// 게시글을 업데이트하는 함수
	$scope.update = function(index) {
		console.log($scope.board);
		// BoardFactory를 이용하여 서버에 업데이트 요청을 보냅니다.
		BoardFactory.updateBoard({ index: index }, $scope.board, function() {
			// 만약 index가 없다면 함수를 종료합니다.


			var updateUrl = '/board/' + index;
			$window.location.href = updateUrl;

		});

		//페이지 리디렉션을 수행합니다.
	};


	$scope.redirectToUpdate = function(index) {
		var updateUrl = '/board/' + index;
		// 페이지 리디렉션을 수행합니다.
		$window.location.href = updateUrl;
	};
});
