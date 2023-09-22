/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * general_read.html에 필요한 메서드 작성
 * 
 * 작성일 : 2023.09.15
 * 작성자 : 황상필
 */
app.controller("BoardRead", function($rootScope, $scope, $window, BoardFactory) {

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({index: index}, function(response) {
			$scope.board = response;
		});
	};

	$scope.remove = function(index) {
		BoardFactory.deleteBoard({ index: index }, function() {
			var updateUrl = '/board';
			$window.location.href = updateUrl;
		});
	};

	$scope.updateBoard = function() {
		$rootScope.selectMenu('static/templates/general_update.html');
	}
	
	$scope.backBoard = function() {
		$rootScope.selectMenu('static/templates/general_board.html');
	}
});
