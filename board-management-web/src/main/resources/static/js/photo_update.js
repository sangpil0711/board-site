app.controller("PhotoUpdate", function($scope, PhotoBoardFactory, $location, $routeParams) {
	
    let index = $routeParams.index;

    $scope.photoBoard = {
        title: null,
        text: null
    };

   /**
    * @function searchByPhotoIndex 게시판 번호에 맞는 데이터를 불러오는 함수
    * 
    * @param index 해당 게시판 번호
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
    let searchByPhotoIndex = function() {
        PhotoBoardFactory.readPhotoBoard({ index: index }, function(response) {
            $scope.photoBoard = response;
        });
    }
    searchByPhotoIndex();

   /**
    * @function updatePhoto 게시판 번호에 맞는 데이터를 수정하는 함수
    * 
    * @param index 해당 게시판 번호
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
    $scope.updatePhoto = function(index) {
        PhotoBoardFactory.updatePhotoBoard({ index: index }, $scope.photoBoard, function() {
            $location.path('/photo/read/' + index);
        });
    }
   /**
    * @function redirectToPhotoRead photo_read.html로 이동하는 함수
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
    $scope.redirectToPhotoRead = function(index) {
        $location.path('/photo/read/' + index);
    }
});
