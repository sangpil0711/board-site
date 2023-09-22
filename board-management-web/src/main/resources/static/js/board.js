/**
 * $scope 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 query 메소드를 이용하여 게시글 목록 조회 및 페이지네이션 구현
 * 
 * 작성일 : 2023.09.01
 * 작성자 : 황상필
 */
app.controller("BoardCtrl", function($rootScope, $scope, BoardFactory) {

	BoardFactory.query({}, function(response) {
		$scope.boardlist = response;
		$scope.maxSize = 5;
		$scope.totalItems = $scope.boardlist.length;
		$scope.currentPage = 1;
		$scope.view = 5;
		$scope.itemsPerPage = 5;
	});
	
	$scope.setItemsPerPage = function(num) {
		$scope.itemsPerPage = num;
		$scope.currentPage = 1;
	}

	$scope.writeBoard = function() {
		$rootScope.selectMenu('static/templates/general_write.html');
	}
	
	$scope.readBoard = function() {
		$rootScope.selectMenu('static/templates/general_read.html');
	}

});
