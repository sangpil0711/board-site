/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 readBoard와 deleteBoard 메소드를 이용하여 게시글 상세 조회 및 게시글 삭제 구현
 * 
 * 작성일 : 2023.09.15
 * 작성자 : 황상필
 */
app.controller("BoardRead", function($scope, $window, BoardFactory) {

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		});
	};

	if (current != undefined) {
		let index = current;
		$scope.getDataByIndex(index);
		console.log(index);
	}

	$scope.remove = function(index) {
		BoardFactory.deleteBoard({ index: index }, function() {
		var updateUrl = '/board';
		$window.location.href = updateUrl;
		});
	};

	$scope.redirectToUpdate = function(index) {
		var updateUrl = '/board/update/' + index;
		$window.location.href = updateUrl;
	};
});
