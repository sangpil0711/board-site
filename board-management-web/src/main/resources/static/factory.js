//var app = angular.module("myApp", ['ngResource']);	// 'myApp' AngularJS 모듈 생성

app.factory('BoardFactory', function($resource) {	// $resource를 매개변수로 한 'BoardFactory' 팩토리 생성
	return $resource('/board', null, { 				// '/board' 경로로 요청을 보낼 리소스 객체 생성 
		query: {										// 'query' 메소드에 대한 옵션 설정
			method: 'GET', 							// http 요청 메소드를 GET으로 설정
        	isarray: true,
         							
         }
      });
   });