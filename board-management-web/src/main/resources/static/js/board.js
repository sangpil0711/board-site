app.controller("BoardCtrl", function($scope, BoardFactory) { // 'myCtrl' 컨트롤러 생성

	BoardFactory.query({}, function(res) { // 'BoardFactory'의 'query'메소드 호출
		$scope.boardlist = res; // $scope 객체의 'boardlist'에 데이터 할당
	});

	$scope.getBoard = function(index) {
        BoardFactory.readBoard({ id: index }, function(res) {
            $scope.board = res;
        });
    };
});

