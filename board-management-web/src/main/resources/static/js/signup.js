var app = angular.module("myApp", ['ngResource']);

app.factory('UserFactory', function($resource) {
	return $resource('/', null, {

		checkUserId: {
			method: 'POST',
			url: '/user/checkId',
			headers: {
				"Content-Type": 'application/json'
			}
		},

		saveUser: {
			method: 'POST',
			url: '/user/register',
			headers: {
				"Content-Type": 'application/json'
			}
		},

	});
});

app.controller("BoardSignup", function($scope, $window, UserFactory) {

	$scope.redirectToLogin = function() {
		$window.location.href = "/login";
	};

	$scope.checkId = function(id) {
		UserFactory.checkUserId({}, id, function() {
			$scope.userId = id;
			alert("사용가능한 아이디입니다.")
		}, function() {
			alert("이미 생성된 아이디입니다.");
		});
	};

	$scope.signup = function(id, password, password_check, name, email) {

		const signupData = {
			id: $scope.userId,
			password: password,
			username: name,
			email: email
		};
		
		if (id == null || password == null || password_check == null || name == null){
			alert("필수입력사항을 확인해주세요.")
		} else if (id !== $scope.userId) {
			alert("아이디 중복확인을 해주세요.");
		} else if (password !== password_check) {
			alert("입력한 비밀번호와 비밀번호확인이 일치하지 않습니다.")
		} else {
			UserFactory.saveUser({}, signupData, function() {
				alert("회원가입이 정상적으로 완료되었습니다.")
				$window.location.href = "/login";
			})
		}
	}

	$scope.password = "";

    $scope.checkPassword = function () {
        // 비밀번호 유효성 검사
        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;

        if (!passwordPattern.test($scope.password)) {
            alert("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
            $scope.password = "";
        }
    };



});
