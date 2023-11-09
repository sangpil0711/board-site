app.controller("BoardMain", function($scope, $location, MainFactory) {

	/**
	 * @function bestBoard 추천 수가 많은 게시글을 가져오는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	MainFactory.bestBoard(function(response) {
		$scope.board = response;
	},
		function(error) {
			alert("게시물 불러오기 실패");
			console.error("게시물 불러오기 실패", error);
		});

	/**
	 * @function thumbnail 사진게시판 게시글의 사진을 미리보는 함수
	 * 
	 * @param file 해당 게시글의 파일 정보
	 * 
	 * @author 황상필
	 * @since 2023. 11. 08.
	 */
	$scope.thumbnail = function(file) {
		if (file !== undefined && file.fileSize > 0) {
			return '/files/' + file.fileId + '?fileName=' + file.fileName;
		} else {
			return '/static/image/No_img.png';
		}
	}

	/**
	 * @function redirectToReadGeneralBoard general_read.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	$scope.redirectToReadGeneralBoard = function(index) {
		$location.path('/board/read/' + index);
	};

	/**
	 * @function redirectToReadPhotoBoard photo_read.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	$scope.redirectToReadPhotoBoard = function(index) {
		$location.path('/photo/read/' + index);
	};

});
