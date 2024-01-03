var app = angular.module("myApp", ['ngResource']);

app.controller("BoardSignup", function($scope, $window) {

	$scope.redirectToLogin = function() {
		$window.location.href = "/login";
	}

});