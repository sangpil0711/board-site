var app = angular.module("myApp", ['ngResource']);

app.factory('BoardFactory', function($resource) {	// $resource를 매개변수로 한 'BoardFactory' 팩토리 생성
	return $resource('/board', null, { 				// '/board' 경로로 요청을 보낼 리소스 객체 생성 
		readBoard: {								
			method: 'GET', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},
		
		createBoard: {								
			method: 'POST', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},
		
		updateBoard: {								
			method: 'PUT', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},
		
		deleteBoard: {									
			method: 'DELETE', 							// http 요청 메소드를 GET으로 설정
			headers: {
				"Content- Type": 'application/json', // application/json 타입 선언
			},
		},

	});
});