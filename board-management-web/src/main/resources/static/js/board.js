app.controller("BoardCtrl", function($scope, BoardFactory, $location, $route) {
	
	// 게시판 현재 페이지
	$scope.currentPage = 1;
	// 검색 유형 기본 값
	$scope.searchType = 'title';
	// 검색어 기본 값
	$scope.keyword = '';
	// 선택할 수 있는 검색 유형
	$scope.types = [
		{ name: '제목', value: 'title' },
		{ name: '내용', value: 'content' },
		{ name: '작성자', value: 'user_id' }
	];

	// 게시판 리스트 초기화
	$scope.boardlist = [];
	// 게시판 게시글 수 초기화
	$scope.totalItems = [];
	// 게시판 카테고리
	const CATEGORY = 0;

	BoardFactory.getPageValue(function(response) {
		$scope.itemsPerPage = response.postPerPage.split(',')[0];
	});

	/**
	 * @function findBoardList 요청에 따른 게시판 목록과 게시판 총 게시글 수를 반환하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	let findBoardList = function() {

		BoardFactory.getPageValue(function(response) {
			let postPerPage = response.postPerPage.split(',');
			$scope.firstPage = postPerPage[0];
			$scope.secondPage = postPerPage[1];
			$scope.thirdPage = postPerPage[2];
			$scope.fourthPage = postPerPage[3];


			// 화면에 표시되는 최대 페이지 수
			$scope.maxSize = response.maxPage;
			// 선택할 수 있는 페이지당 게시글 수 
			$scope.options = [
				{ name: $scope.firstPage + '개씩 보기', value: $scope.firstPage },
				{ name: $scope.secondPage + '개씩 보기', value: $scope.secondPage },
				{ name: $scope.thirdPage + '개씩 보기', value: $scope.thirdPage },
				{ name: $scope.fourthPage + '개씩 보기', value: $scope.fourthPage }
			];

			BoardFactory.readBoard({
				category: CATEGORY,
				pageNumber: $scope.currentPage,
				itemSize: $scope.itemsPerPage,
				searchType: $scope.searchType,
				keyword: $scope.keyword
			},
				function(response) {
					$scope.boardlist = response.boardList;
					$scope.totalItems = response.totalCount;
				},
				function(error) {
					alert("게시판 불러오기 실패");
					console.error("게시판 불러오기 실패", error);
				})

		});
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
		$location.path('/board/write')
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
