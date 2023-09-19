///**
// * $scope 객체와 BoardFactory 서비스를 사용하는 controller 생성
// * BoardFactory의 createBoard 메소드를 이용하여 게시글 등록 구현
// * 
// * 작성일 : 2023.09.18
// * 작성자 : 황상필
// */
//app.controller("BoardCreate", function($scope, BoardFactory) {
//
//	$scope.insert = function() {
//		BoardFactory.createBoard({}, function() {
//
//			var newIndex;
//
//			if (!$scope.boardlist.length) {
//				newIndex = 1;
//			} 
//			
//			else {
//				newIndex = $scope.boardlist[$scope.boardlist.length - 1].id + 1;
//			}
//
//			$scope.boardlist.push(newIndex);
//		});
//	}
//});
