var app = angular.module('app', []); // "app" 모듈 생성
app.controller('controller', function($scope, $filter) { // "controller" 컨트롤러 정의
	$scope.clickfunction = function() { // 버튼 클릭 시 실행되는 함수
		var time = $filter('date')(new Date(), 'yyyy-MM-dd HH:mm:ss Z'); // 현재 시간을 포맷에 맞게 변환
		$scope.welcome = "Time = " + time; // "welcome" 변수에 시간을 저장하여 출력
	}
});