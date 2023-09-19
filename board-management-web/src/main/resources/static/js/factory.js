/**
 * 'ngResource'와 'ngRoute'를 의존하는 angular module을 생성하여 app에 할당
 * $resource 함수를 사용하는 factory	생성
 * "/boards/:index" 경로에 따른 'GET', 'POST', 'PATCH', 'DELETE' 메소드 생성
 * 
 * 작성일 : 2023.09.01
 * 작성자 : 황상필
 */
var app = angular.module("myApp", ['ngResource', 'ngRoute', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);

app.factory('BoardFactory', function($resource) {
	return $resource('/boards/:index', { index: '@index' }, { 

		query: {
			method: 'GET',
			isArray: true,
		},

		readBoard: {
			method: 'GET', 						
			isArray: true,
			headers: {
				"Content-Type": 'application/json',
			},
		},

		createBoard: {
			method: 'POST',
			headers: {
				"Content-Type": 'application/json',
			},
		},

		updateBoard: {
			method: 'PATCH',
			headers: {
				"Content-Type": 'application/json',
			},
		},

		deleteBoard: {
			method: 'DELETE',
			headers: {
				"Content-Type": 'application/json',
			},
		},

	});
});

