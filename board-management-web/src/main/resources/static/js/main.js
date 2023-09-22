/**
 * $scope 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 query 메소드를 이용하여 게시글 목록 조회 및 페이지네이션 구현
 * 
 * 작성일 : 2023.09.01
 * 작성자 : 황상필
 */
app.controller("BoardMain", function($rootScope, $scope) {

	$scope.template = {
		url: 'static/templates/main_display.html'
	}

	$scope.redirectToMain = function() {

		$scope.template.url = 'static/templates/main_display.html';

	};

	$scope.redirectToBoard = function() {

		$scope.template.url = 'static/templates/general_board.html';

	};
	
	$rootScope.selectMenu = function(url) {
		$scope.template.url = url;
	}

});
