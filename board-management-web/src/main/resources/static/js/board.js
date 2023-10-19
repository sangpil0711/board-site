app.controller("BoardCtrl", function($scope, BoardFactory, $window, $location) {

	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	$scope.maxSize = 5;
	$scope.options = [
		{ name: '5개씩 보기', value: 5 },
		{ name: '10개씩 보기', value: 10 },
		{ name: '15개씩 보기', value: 15 },
		{ name: '20개씩 보기', value: 20 }
	];
	$scope.searchType = 'title';
	$scope.keyword = '';
	$scope.types = [
		{ name: '제목', value: 'title' },
		{ name: '내용', value: 'content' },
		{ name: '작성자', value: 'user_id' }
	];

	/**
	 * @function findPage 요청에 따른 게시판 목록과 게시판 총 게시글 수를 반환하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	let findBoardList = function() {
		BoardFactory.readBoard({
			pageNumber: $scope.currentPage,
			itemSize: $scope.itemsPerPage,
			searchType: $scope.searchType,
			keyword: $scope.keyword
		},
			function(response) {
				$scope.boardlist = response.boardList;
				$scope.totalItems = response.totalCount;
			})
	}

	/**
	 * @function search 검색어 입력에 따른 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	$scope.search = function() {
		if ($scope.keyword != '') {
			findBoardList();
		} else {
			alert("검색어를 입력해주세요.");
		}
	}

	/**
	 * @function changeSearchType 검색 유형 변경 함수
	 * 
	 * @param selectType 선택된 게시판 검색 유형
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	$scope.changeSearchType = function(selectType) {
		$scope.searchType = selectType;
	}

	/**
	 * @function updatePage 페이지 번호 변경 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	$scope.updatePage = function() {
		findBoardList();
	}

	/**
	 * @function redirectToWrite general_write.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToWrite = function() {
		$location.path('/board/write');
	}

	/**
	 * @function redirectToRead general_read.html로 이동하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToRead = function(index) {
		$location.path('/board/read/' + index);
	}
	
//		$scope.redirectToWrite = function() {
//		$window.location.href = '#!/photo/write';
//	}
//
//	$scope.redirectToRead = function(index) {
//		$window.location.href = '#!/photo/read/' + index;
//	}

	findBoardList();

})
