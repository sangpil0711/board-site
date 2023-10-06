/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 updateBoard 메소드를 이용하여 게시글 수정 구현
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 황상필
 */
app.controller("BoardUpdate", function($scope, BoardFactory, $window, $routeParams) {

	var index = $routeParams.index;

	$scope.board = {
		title: null,
		text: null
	}

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		});
	};

	$scope.getDataByIndex(index);

	$scope.update = function(index) {
		BoardFactory.updateBoard({ index: index }, $scope.board, function() {
			$window.location.href = '#!/board/read/' + index;
		});
	};

	$scope.redirectToRead = function(index) {
		$window.location.href = '#!/board/read/' + index;
	};
});
