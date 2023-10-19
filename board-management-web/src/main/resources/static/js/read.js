app.controller("BoardRead", function($scope, $location, BoardFactory, $routeParams) {

	let index = $routeParams.index;

	/**
	 * @function getDataByIndex 게시판 번호에 맞는 데이터를 불러오는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	let getDataByIndex = function() {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		})
	}

	getDataByIndex();

	/**
	 * @function remove 게시판 번호에 맞는 데이터를 삭제하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	 $scope.remove = function(index) {
        let confirmDelete = confirm("게시물을 삭제하시겠습니까?");
        if (confirmDelete) {
            BoardFactory.deleteBoard({ index: index }, function() {
                $location.path('/board');
            })
        }
    }

	/**
	 * @function redirectToUpdate general_update.html로 이동하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	$scope.redirectToUpdate = function(index) {
		$location.path('/board/update/' + index);
	}

	/**
	 * @function redirectToBoard general_board.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	$scope.redirectToBoard = function() {
		$location.path('/board');
	}

})
