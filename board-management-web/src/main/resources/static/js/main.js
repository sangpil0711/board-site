app.controller("BoardMain", function($scope, $location, $route) {

	/**
	 * @function redirectToMain main_display.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToMain = function() {
		$location.path('/');
	}

	/**
	 * @function redirectToBoard general_board.html로 이동 후 새로고침하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToBoard = function() {
		$location.path('/board');
		$route.reload();
	}
	$scope.redirectToGeneralBoard = function() {
		$window.location.href = '#!/board';
		$window.location.reload();
	};
	
	$scope.redirectToPhotoBoard = function() {
		$window.location.href = '#!/photo';
		$window.location.reload();
	};
	
	

})