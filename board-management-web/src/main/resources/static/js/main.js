app.controller("BoardMain", function($scope, $location, MainFactory) {

	/**
	 * @function bestBoard 추천 수가 많은 게시글을 가져오는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	MainFactory.bestBoard(function(response) {
		$scope.board = response;
	});

	$scope.thumbnail = function(file) {
		if (file !== undefined && file.fileSize > 0) {
			return '/files/' + file.fileId + '?fileName=' + file.fileName;
		} else {
			return '/static/image/No_img.png';
		}
	}

	/**
	 * @function redirectToReadBoard general_read.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	$scope.redirectToReadGeneralBoard = function(index) {
		$location.path('/board/read/' + index);
	};
	
	$scope.redirectToReadPhotoBoard = function(index) {
		$location.path('/photo/read/' + index);
	};

});
