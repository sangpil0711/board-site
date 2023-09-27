/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * general_read.html에 필요한 메서드 작성
 * 
 * 작성일 : 2023.09.15
 * 작성자 : 황상필
 */
app.controller("BoardRead", function($scope, $window, BoardFactory, $routeParams, $sce) {

	var index = $routeParams.index;

	$scope.getDataByIndex = function(index) {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		});
	};

//	$scope.formatText = function(text) {
//		var text = document.getElementById("contents").value;
//		text = text.replace(/(?:\r\n|\r|\n)/g, '<br/>');
//		document.getElementById("contents").value = text;
//	};

	$scope.getDataByIndex(index);

	 $scope.remove = function(index) {
        var confirmDelete = confirm("게시물을 삭제하시겠습니까?");
        if (confirmDelete) {
            BoardFactory.deleteBoard({ index: index }, function() {
                $window.location.href = '#!/board';
            });
        }
    };

	$scope.redirectToUpdate = function(index) {
		$window.location.href = '#!/board/update/' + index;
	}

	$scope.redirectToBoard = function() {
		$window.location.href = '#!/board';
	}

});
