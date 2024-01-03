var app = angular.module("myApp", ['ngResource']);

app.controller("BoardLogin", function($scope, $window) {

$scope.redirectToSignup = function() {
	$window.location.href = "/signup";
}

});