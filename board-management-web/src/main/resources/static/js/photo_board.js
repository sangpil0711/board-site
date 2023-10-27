app.controller("PhotoCtrl", function($scope, PhotoBoardFactory, $location) {
	
//    $scope.currentPage = 1;
//    $scope.itemsPerPage = 5;
//    $scope.maxSize = 5;
//    $scope.options = [
//        { name: '5개씩 보기', value: 5 },
//        { name: '10개씩 보기', value: 10 },
//        { name: '15개씩 보기', value: 15 },
//        { name: '20개씩 보기', value: 20 }
//    ];
//    $scope.searchType = 'title';
//    $scope.keyword = '';
//    $scope.types = [
//        { name: '제목', value: 'title' },
//        { name: '내용', value: 'content' },
//        { name: '작성자', value: 'user_id' }
//    ];
    
    const CATEGORY = 1; //category 를 1로 설정
    $scope.photoBoardlist = []; //초기화
    
    	/**
	 * @function findPhotoBoardList 요청에 따른 게시판 목록과 게시판 총 게시글 수를 반환하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    let findPhotoBoardList = function() {
        PhotoBoardFactory.readPhotoBoards({category: CATEGORY}, function(response) {
			//$scope.photoBoardlist.push(response);
            $scope.photoBoardlist = response;
        });
    }

//    $scope.search = function() {
//        if ($scope.keyword !== '') {
//            findPhotoBoardList();
//        } else {
//            alert("검색어를 입력해주세요.");
//        }
//    }
//
//    $scope.changeSearchType = function(selectType) {
//        $scope.searchType = selectType;
//    }
//
//
//    $scope.updatePage = function() {
//        findBoardList();
//    }

	/**
	 * @function redirectToPhotoWrite photo_write.html로 이동하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.redirectToPhotoWrite = function() {
        $location.path('/photo/write');
    }

	/**
	 * @function redirectToPhotoRead photo_read.html로 이동하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.redirectToPhotoRead = function(index) {
        $location.path('/photo/read/' + index);
    }

    findPhotoBoardList();
});
