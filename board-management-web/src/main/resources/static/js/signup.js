var app = angular.module("myApp", ['ngResource']);

app.controller("BoardSignup", function($scope, $window) {

	$scope.redirectToLogin = function() {
		$window.location.href = "/login";
	}

$scope.password = "";
    $scope.confirmPassword = "";

    $scope.checkPassword = function () {
        // 비밀번호 유효성 검사
        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;

        if (!passwordPattern.test($scope.password)) {
            alert("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
            $scope.password = "";
        }
    };

    $scope.checkPasswordMatch = function () {
        // 비밀번호 확인 일치 여부 확인
        if ($scope.password !== $scope.confirmPassword) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            $scope.confirmPassword = "";
        }
    };

});
