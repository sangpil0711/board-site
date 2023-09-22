/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 updateBoard 메소드를 이용하여 게시글 수정 구현
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 황상필
 */
app.controller("BoardUpdate", function($rootScope, $scope, BoardFactory, $window) {

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		});
	};

	$scope.update = function(index) {
		BoardFactory.updateBoard({ index: index }, function() {

			var updateUrl = '/board/' + index;
			$window.location.href = updateUrl;

		});
	};
	
	$scope.cancleUpdate = function() {
		$rootScope.selectMenu('static/templates/general_read.html');
	}

	$scope.redirectToUpdate = function(index) {
		var updateUrl = '/board/' + index;
		$window.location.href = updateUrl;
	};
});
