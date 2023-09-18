app.controller("BoardDelete", function($scope, BoardFactory, $window) {
    $scope.remove = function(index) {
		 BoardFactory.deleteBoard({index: index}, function() {
        if (!index) return; // 예외처리

       
            var idx = -1;
            for (var i = 0; i < $scope.boardlist.length; i++) {
                if ($scope.boardlist[i].index === index) {
                    idx = i;
                    break;
                }
            }

            if (idx === -1) return; // 예외처리

            $scope.boardlist.splice(idx, 1); // JavaScript에서의 array의 삭제
        });
    };

    $scope.redirectToUpdate = function(index) {
        var updateUrl = '/board/update/' + index;
        $window.location.href = updateUrl; // 페이지 리디렉션
        if (current !== undefined) {
        $scope.redirectToUpdate(current);
    }
    };

 
});
