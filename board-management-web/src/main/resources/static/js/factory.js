var app = angular.module("myApp", ['ngResource', 'ngRoute']);

app.factory('BoardFactory', function($resource) {	// $resource를 매개변수로 한 'BoardFactory' 팩토리 생성
	return $resource('/boards/:index', {index: '@index'}, { 				// '/board' 경로로 요청을 보낼 리소스 객체 생성 

		readBoard: {								
			method: 'GET', 							// http 요청 메소드를 GET으로 설정
			isArray: true,
			headers: {
				"Content-Type": 'application/json', // application/json 타입 선언
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

