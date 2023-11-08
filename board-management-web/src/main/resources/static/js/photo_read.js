app.controller("PhotoRead", function($scope, $location, $routeParams, PhotoBoardFactory) {
	
	
	 //라우팅으로 받아오는 게시글 번호
    let index = $routeParams.index;   

	$scope.photoBoard = [];
	/**
	 * @function searchByPhotoIndex 게시판 번호에 맞는 데이터를 불러오는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    let searchByPhotoIndex = function() {
        PhotoBoardFactory.readPhotoBoard({ index: index }, function(response) {
            $scope.photoBoard = response;
            $scope.photoBoard.imagePaths = [];
            
             for(let i = 0;i<response.file.length;i++){
             $scope.photoBoard.imagePaths.push("/files/" + response.file[i].fileId + "?fileName=" + response.file[i].fileName);
             }
		},
        	function(error) {
				alert("게시물 데이터 불러오기 실패");
				console.error("게시물 데이터 불러오기 실패", error);
			})
    };
    searchByPhotoIndex();

	/**
	 * @function redirectToPhotoUpdate photo_update.html로 이동하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.redirectToPhotoUpdate = function() {
        $location.path('/photo/update/' + index);
    }

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
	 * @function remove 게시판 번호에 맞는 데이터를 삭제하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.removePhoto = function() {
        let confirmDelete = confirm("게시물을 삭제하시겠습니까?");
        if (confirmDelete) {
            PhotoBoardFactory.deletePhotoBoard({ index: index }, function() {
                $location.path('/photo');
            },
            function(error){
				alert("게시물 추가 실패!");
				console.error("게시물 추가 실패",error);
				
			}
            );
        }else{
			alert("삭제 실패!");
		}
    }
});
