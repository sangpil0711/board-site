app.controller("BoardHead", function($scope, $location, $route) {

	/**
	 * @function redirectToMain main_display.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToMain = function() {
		$location.path('/');
		$route.reload();
	};
	
	/**
	 * @function redirectToGeneralBoard general_board.html로 이동 후 새로고침하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 01.
	 */
	$scope.redirectToGeneralBoard = function() {
		$location.path('/board');
		$route.reload();
	};
	
	/**
	 * @function redirectToPhotoBoard photo_board.html로 이동 후 새로고침하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	$scope.redirectToPhotoBoard = function() {
		$location.path('/photo');
		$route.reload();
	};
	
	/**
	 * @function redirectToReadFileExplorer file_explorer.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 14.
	 */
	$scope.redirectToFileExplorer = function() {
		$location.path('/file');
		$route.reload();
	};
	
	
	
});
