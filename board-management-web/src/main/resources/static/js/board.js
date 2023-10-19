/**
 * $scope 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * BoardFactory의 query 메소드를 이용하여 게시글 목록 조회 및 페이지네이션 구현
 * 
 * 작성일 : 2023.09.01
 * 작성자 : 황상필
 */
app.controller("BoardCtrl", function($scope, BoardFactory, $window) {

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

	let findPage = function() {
		BoardFactory.query({ pageNumber: $scope.currentPage, pageSize: $scope.itemsPerPage, searchType: $scope.searchType, keyword: $scope.keyword }, function(response) {
			$scope.boardlist = response.boardList;
			$scope.totalItems = response.pageList[0].totalCount;
		});
	}

	$scope.search = function() {
		if ($scope.keyword != '') {
			findPage();
		} else {
			$window.alert("검색어를 입력해주세요.")
		}
	}

	$scope.changeSearchType = function(selectType) {
		$scope.searchType = selectType;
	};

	$scope.setItemsPerPage = function() {
		findPage();
	}

	$scope.changePage = function() {
		findPage();
	}

	$scope.redirectToWrite = function() {
		$window.location.href = '#!/board/write';
	}

	$scope.redirectToRead = function(index) {
		$window.location.href = '#!/board/read/' + index;
	}
	
//		$scope.redirectToWrite = function() {
//		$window.location.href = '#!/photo/write';
//	}
//
//	$scope.redirectToRead = function(index) {
//		$window.location.href = '#!/photo/read/' + index;
//	}

	findPage();

});
