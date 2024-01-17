app.controller("BoardHead", function($scope, $location, $route, $http) {
		
	// 현재 로그인한 아이디의 권한정보	
	$http.get('/user/authority')
		.then(function(response) {
			$scope.userRole = response.data;
			if($scope.userRole == 'ROLE_ADMIN') {
				$scope.adminMenu = true;
			} else {
				$scope.adminMenu = false;
			}
		})
		.catch(function(error) {
			console.error('현재 로그인된 아이디의 권한정보를 가져올 수 없습니다.', error);
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
      if ($scope.userRole == 'ROLE_ADMIN') {
         $location.path('/file');
         $route.reload();
      } else {
         alert('접근 권한이 없습니다.');
      }
   };

	/**
    * @function redirectToChart chart.html로 이동 후 새로고침하는 함수
    * 
    * @author 황상필
    * @since 2024. 01. 09.
    */
   $scope.redirectToChart = function() {
      $location.path('/chart');
      $route.reload();
   };
   
   /**
    * @function redirectToInfo info_update.html로 이동 후 새로고침하는 함수
    * 
    * @author 황상필
    * @since 2024. 01. 15.
    */
   $scope.redirectToInfo = function() {
      $location.path('/info');
      $route.reload();
   };
   
   /**
    * @function redirectToSystemManagement system.html로 이동 후 새로고침하는 함수
    * 
    * @author 황상필
    * @since 2024. 01. 16.
    */
   $scope.redirectToSystemManagement = function() {
      $location.path('/system');
      $route.reload();
   };
   
   /**
    * @function redirectToUserManagement info_update.html로 이동 후 새로고침하는 함수
    * 
    * @author 황상필
    * @since 2024. 01. 16.
    */
   $scope.redirectToUserManagement = function() {
      $location.path('/user');
      $route.reload();
   };

});