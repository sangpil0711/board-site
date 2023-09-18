app.controller("BoardUpdate", function($scope, BoardFactory, $window) {
    
    // 현재 페이지의 데이터를 가져오는 함수
    $scope.getDataByIndex = function(index) {
        // BoardFactory를 이용하여 서버에서 해당 인덱스(index)에 해당하는 게시글 데이터를 읽어옵니다.
        BoardFactory.readBoard({ index: index }, function(response) {
            // 응답 데이터의 첫 번째 항목을 $scope.board 변수에 저장합니다.
            $scope.board = response[0];
        });
    };

    // current 변수가 정의되어 있다면 해당 인덱스로 데이터를 가져오는 함수 호출
    if (current != undefined) {
        let index = current;
        $scope.getDataByIndex(index);
    }

    // 게시글을 업데이트하는 함수
    $scope.update = function(index) {
        // BoardFactory를 이용하여 서버에 업데이트 요청을 보냅니다.
        BoardFactory.updateBoard({}, function() {
            // 만약 id가 없다면 함수를 종료합니다.
            if (!index) return;

            var idx = -1;

            // $scope.boardlist 배열을 순회하며 id와 일치하는 항목을 찾습니다.
            for (var i = 0; i < $scope.boardlist.length; i++) {
                if ($scope.boardlist[i].index === index) {
                    idx = i;
                    break;
                }
            }

            // 일치하는 항목을 찾지 못한 경우 함수를 종료합니다.
            if (idx === -1) return;

            // 업데이트된 게시글 데이터를 생성합니다.
            var newItem = {
                index: index, // 게시글의 ID
                text: $scope.boardForm.text // 새로운 제목
            };

            // 기존 항목을 새로운 항목으로 교체합니다.
            $scope.boardlist.splice(idx, 1, newItem);
            console.log(newItem);
        });
        
        //페이지 리디렉션을 수행합니다.
        var updateUrl = '/board/' + index;
        $window.location.href = updateUrl;
    }
    
    $scope.redirectToUpdate = function(index) {
		var updateUrl = '/board/' + index;
		// 페이지 리디렉션을 수행합니다.
		$window.location.href = updateUrl;
	};
});
