app.controller("BoardCtrl", function($scope, BoardFactory, $location, $route) {

	$scope.currentPage = 1;		// 게시판 현재 페이지
	$scope.itemsPerPage = 5;	 // 페이지당 게시글 수
	$scope.maxSize = 5; 	// 화면에 표시되는 최대 페이지 수
	$scope.options = [	 // 선택할 수 있는 페이지당 게시글 수 
		{ name: '5개씩 보기', value: 5 },
		{ name: '10개씩 보기', value: 10 },
		{ name: '15개씩 보기', value: 15 },
		{ name: '20개씩 보기', value: 20 }
	];
	$scope.searchType = 'title';	 // 검색 유형 기본 값
	$scope.keyword = '';	 // 검색어 기본 값
	$scope.types = [	 // 선택할 수 있는 검색 유형
		{ name: '제목', value: 'title' },
		{ name: '내용', value: 'content' },
		{ name: '작성자', value: 'user_id' }
	];


		$scope.boardlist = [];
		$scope.totalItems = [];
		const CATEGORY = 0;
	/**
	 * @function findPage 요청에 따른 게시판 목록과 게시판 총 게시글 수를 반환하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	let findBoardList = function() {
		BoardFactory.readBoard({
			category : CATEGORY,
			pageNumber: $scope.currentPage,
			itemSize: $scope.itemsPerPage,
			searchType: $scope.searchType,
			keyword: $scope.keyword
		},
			function(response) {
				$scope.boardlist = response.boardList;
				$scope.totalItems = response.totalCount;
			})
	};

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
	};

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
	};

	/**
	 * @function updatePage 페이지 번호 변경 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	$scope.updatePage = function() {
		findBoardList();
	};

	/**
	 * @function redirectToWrite general_write.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToWrite = function() {
		$location.path('/board/write');
	};

	/**
	 * @function searchReset general_board.html로 새로고침하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 20.
	 */
	$scope.searchReset = function() {
		$route.reload();
	};

	/**
	 * @function redirectToRead general_read.html로 이동하는 함수
	 * 
	 * @param index 해당 게시판 번호 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToRead = function(index) {
		$location.path('/board/read/' + index);
	};

	findBoardList();

});
