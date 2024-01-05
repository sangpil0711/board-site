/**
 * angular module을 생성하여 app에 할당
 * 
 * $resource 함수를 사용하는 factory	생성
 * 
 * @author 황상필
 * @since 2024. 01. 04.
 */
var app = angular.module("myApp", ['ngResource']);

/**
 * @Method : 회원가입 시 필요한 함수
 * 
 * @author 황상필
 * @since 2024. 01. 04.
 */
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

	/**
	 * @function redirectToLogin "/login" 경로로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 03.
	 */
	$scope.redirectToLogin = function() {
		$window.location.href = "/login";
	};

	/**
	 * @function checkId 입력한 아이디가 이미 생성된 아이디인지 확인하는 함수
	 * 
	 * @param id 입력한 아이디
	 * 
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	$scope.checkId = function(id) {
		UserFactory.checkUserId({}, id, function() {
			$scope.userId = id;
			alert("사용가능한 아이디입니다.")
		}, function() {
			alert("이미 생성된 아이디입니다.");
		});
	};

	/**
	 * @function checkId 입력한 아이디가 이미 생성된 아이디인지 확인하는 함수
	 * 
	 * @param id 입력한 아이디
	 * @param password 입력한 비밀번호
	 * @param password_check 입력한 비밀번호확인
	 * @param name 입력한 이름
	 * @param email 입력한 이메일
	 * 
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	$scope.signup = function(id, password, password_check, name, email) {

		// 회원가입 시 필요한 데이터
		const signupData = {
			id: $scope.userId,
			password: password,
			username: name,
			email: email
		};

		// 필수입력항목이 입력되지 않았을 경우 동작
		if (id == null || password == null || password_check == null || name == null) {
			alert("필수입력사항을 확인해주세요.")
		}
		// 입력한 아이디가 중복확인한 아이디와 일치하지 않을 경우 동작
		else if (id !== $scope.userId) {
			alert("아이디 중복확인을 해주세요.");
		}
		// 비밀번호와 비밀번호확인이 일치하지 않을 경우 동작
		else if (password !== password_check) {
			alert("입력한 비밀번호와 비밀번호확인이 일치하지 않습니다.")
		}
		else {
			UserFactory.saveUser({}, signupData, function() {
				alert("회원가입이 정상적으로 완료되었습니다.")
				$window.location.href = "/login";
			})
		}
	}

	$scope.password = "";

	$scope.checkPassword = function() {
		// 비밀번호 유효성 검사
		const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;

		if (!passwordPattern.test($scope.password)) {
			alert("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
			$scope.password = "";
		}
	};


});
