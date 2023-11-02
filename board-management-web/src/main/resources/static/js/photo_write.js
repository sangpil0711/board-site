app.controller("PhotoWrite", function($scope, $location, PhotoBoardFactory) {
	
	/**
	 * @function redirectToPhotoBoard photo_board.html로 이동하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.redirectToPhotoBoard = function() {
        $location.path('/photo');
    }

	/**
	 * @function insertPhoto 사진게시판에 게시물을 추가하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.insertPhoto = function() {
        $scope.photoBoard.category = 1;
        PhotoBoardFactory.createPhotoBoard($scope.photoBoard, 
        function() {
            $scope.redirectToPhotoBoard();
        },
		function(error){
			alert("게시물 추가 실패!");
			console.error("게시물 추가 실패 ",error);
		})
    };
});
