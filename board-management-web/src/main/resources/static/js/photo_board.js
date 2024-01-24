app.controller("PhotoCtrl", function($scope, PhotoBoardFactory, $location, $route) {

	// 게시판 현재 페이지
	$scope.currentPage = 1;
	// 검색 유형 기본 값
	$scope.searchType = 'title';
	// 검색어 기본 값 
	$scope.keyword = '';
	// 선택 가능한 검색 유형 
	$scope.types = [
		{ name: '제목', value: 'title' },
		{ name: '내용', value: 'content' },
		{ name: '작성자', value: 'user_id' }
	];

	// category를 1로 설정
	const CATEGORY = 1;
	
	PhotoBoardFactory.getPageValue(function(response) {
		$scope.itemsPerPage = response.postPerPage.split(',')[0];
	});

	/**
	 * @function findPhotoBoardList 요청에 따른 게시판 목록과 게시판 총 게시글 수를 반환하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	let findPhotoBoardList = function() {

		PhotoBoardFactory.getPageValue(function(response) {
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

			PhotoBoardFactory.readPhotoBoards({
				category: CATEGORY,
				pageNumber: $scope.currentPage,
				itemSize: $scope.itemsPerPage,
				searchType: $scope.searchType,
				keyword: $scope.keyword
			},
				function(response) {
					$scope.photoBoardlist = [];
					for (let i = 0; i < response.photoBoardList.length / 6; i++) {
						let list = response.photoBoardList.slice(0 + (i * 6), 6 + (i * 6))
						$scope.photoBoardlist.push(list);
					}
					$scope.totalItems = response.totalCount;
				},
				function(error) {
					alert("게시판 불러오기 실패");
					console.error("게시판 불러오기 실패", error);
				})
				
		});
	};

	/**
	 * @function thumbnail 
	 * 사진게시판 이미지 파일을 미리 보여주는 함수
	 * @author 박상현
	 * @since 2023. 11. 07.
	 */
	$scope.thumbnail = function(file) {
		if (file !== undefined && file.fileSize > 0) {
			return '/files/' + file.fileId + '?fileName=' + file.fileName;
		} else {
			return '/static/image/No_img.png';
		}
	}

	/**
	 * @function search 
	 * 검색어 입력에 따른 함수
	 * @author 박상현
	 * @since 2023. 11. 01.
	 */
	$scope.search = function() {
		if ($scope.keyword !== '') {
			findPhotoBoardList();
		} else {
			alert("검색어를 입력해주세요.");
		}
	}

	/**
	 * @function changeSearchType 
	 * 검색 유형 변경 함수
	 * @param selectType 선택된 게시판 검색 유형
	 * @author 박상현
	 * @since 2023. 11. 01.
	 */
	$scope.changeSearchType = function(selectType) {
		$scope.searchType = selectType;
	}

	/**
	 * @function updatePage 
	 * 페이지 번호 변경 함수
	 * @author 박상현
	 * @since 2023. 11. 01.
	 */
	$scope.updatePage = function() {
		findPhotoBoardList();
	}

	/**
	 * @function searchReset 
	 * general_board.html로 새로고침하는 함수
	 * @author 황상필
	 * @since 2023. 10. 20.
	 */
	$scope.searchReset = function() {
		$route.reload();
	};

	/**
	 * @function redirectToPhotoWrite 
	 * photo_write.html로 이동하는 함수
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	$scope.redirectToPhotoWrite = function() {
		$location.path('/photo/write');
	}

	/**
	 * @function redirectToPhotoRead 
	 * photo_read.html로 이동하는 함수
	 * @param index 해당 게시판 번호
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	$scope.redirectToPhotoRead = function(index) {
		$location.path('/photo/read/' + index);
	}

	findPhotoBoardList();
});
