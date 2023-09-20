/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 updateBoard 메소드를 이용하여 게시글 수정 구현
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 황상필
 */
app.controller("BoardUpdate", function($scope, BoardFactory, $window) {

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response[0];
		});
	};

	if (current != undefined) {
		let index = current;
		$scope.getDataByIndex(index);
	}

	$scope.update = function(index) {
		BoardFactory.updateBoard({ index: index }, $scope.board, function() {

			var updateUrl = '/board/' + index;
			$window.location.href = updateUrl;

		});
	};


	$scope.redirectToUpdate = function(index) {
		var updateUrl = '/board/' + index;
		$window.location.href = updateUrl;
	};
});
