app.controller("BoardHead", function($scope, $location, $route, $http) {

	// 현재 로그인한 아이디
	$http.get('/loginId')
		.then(function(response) {
			$scope.loginId = response.data;
		})
		.catch(function(error) {
			console.error('현재 로그인된 아이디를 가져올 수 없습니다.', error);
		});

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
		if ($scope.loginId === 'admin') {
			$location.path('/file');
			$route.reload();
		} else {
			alert('접근 권한이 없습니다.');
		}
	};



});
