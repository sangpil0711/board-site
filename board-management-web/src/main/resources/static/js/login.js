/**
 * angular module을 생성하여 app에 할당
 * 
 * $resource 함수를 사용하는 factory	생성
 * 
 * @author 황상필
 * @since 2023. 12. 28.
 */
var app = angular.module("myApp", ['ngResource']);

app.controller("BoardLogin", function($scope, $window) {

	/**
	 * @function redirectToSignup "/signup" 경로로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 02.
	 */
	$scope.redirectToSignup = function() {
		$window.location.href = "/signup";
	}

});