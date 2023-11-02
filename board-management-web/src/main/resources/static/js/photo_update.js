app.controller("PhotoUpdate", function($scope, PhotoBoardFactory, $location, $routeParams) {
	
    let index = $routeParams.index;  //라우팅으로 받아오는 게시글 번호

    $scope.photoBoard = {  //수정되는 제목과 내용
        title: null,
        text: null
    };

		$scope.photoBoard = [];
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
        if(response = 1){
            $location.path('/photo/read/' + index);
            }else{
				alert("게시물 수정 실패!");
			}
        });
    }
   /**
    * @function redirectToPhotoRead photo_read.html로 이동하는 함수
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
    $scope.redirectToPhotoRead = function() {
        $location.path('/photo/read/' + index);
    }
});
