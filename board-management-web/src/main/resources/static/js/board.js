/**
 * $scope 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 query 메소드를 이용하여 게시글 목록 조회 구현
 * 
 * 작성일 : 2023.09.01
 * 작성자 : 황상필
 */
app.controller("BoardCtrl", function($scope, BoardFactory) {

	BoardFactory.query({}, function(response) {
		$scope.boardlist = response;
	});

	$scope.totalItems = 14;
	$scope.currentPage = 1;

});
