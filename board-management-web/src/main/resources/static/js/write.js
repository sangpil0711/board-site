/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * general_write.html에 필요한 메서드 작성
 * 
 * 작성일 : 2023.09.15
 * 작성자 : 황상필
 */
app.controller("BoardWrite", function($scope, $window, BoardFactory) {

	$scope.board = {
		title: null,
		text: null
	}

	$scope.insert = function() {
		BoardFactory.createBoard({}, $scope.board, function() {
			$scope.redirectToBoard();
		});
	}

	$scope.redirectToBoard = function() {
		$window.location.href = '#!/board';
	}
	
	

});
