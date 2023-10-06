/**
 * angular module을 생성하여 app에 할당
 * $resource 함수를 사용하는 factory	생성
 * "/boards/:index" 경로에 따른 'GET', 'POST', 'PATCH', 'DELETE' 메소드 생성
 * 
 * 작성일 : 2023.09.01
 * 작성자 : 황상필
 */
var app = angular.module("myApp", ['ngResource', 'ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "static/templates/main_display.html"
		})
		.when("/board", {
			templateUrl: "static/templates/general_board.html",
		})
		.when("/board/write", {
			templateUrl: "static/templates/general_write.html",
		})
		.when("/board/read/:index", {
			templateUrl: "static/templates/general_read.html",
		})
		.when("/board/update/:index", {
			templateUrl: "static/templates/general_update.html"
		});
});

app.factory('BoardFactory', function($resource) {
	return $resource('/boards/:index', null, {

		query: {
			method: 'GET',
			isArray: false,
			headers: {
				"Content-Type": 'application/json',
			},
		},

		readBoard: {
			method: 'GET',
			isArray: false,
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
			isArray: false,
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

