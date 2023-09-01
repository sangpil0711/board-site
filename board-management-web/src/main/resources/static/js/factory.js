var app = angular.module("myApp", ['ngResource']);	// 'myApp' AngularJS 모듈 생성

app.factory('BoardFactory', function($resource) {	// $resource를 매개변수로 한 'BoardFactory' 팩토리 생성
	return $resource('/board', null, { 				// '/board' 경로로 요청을 보낼 리소스 객체 생성 
		readBoard: {										// 'query' 메소드에 대한 옵션 설정
			method: 'GET', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},
		
		createBoard: {										// 'query' 메소드에 대한 옵션 설정
			method: 'POST', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},
		
		updateBoard: {										// 'query' 메소드에 대한 옵션 설정
			method: 'PUT', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},
		
		deleteBoard: {										// 'query' 메소드에 대한 옵션 설정
			method: 'DELETE', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},

	});
});