app.controller("BoardInfo", function($scope, $location, $route, InfoFactory, $window) {

	InfoFactory.userInfo({}, function(response) {
		$scope.userInfo = response;
		if ($scope.userInfo.email) {
			let email = $scope.userInfo.email.split('@');
			$scope.beforeEmail = email[0];
			$scope.afterEmail = email[1];
		}
	}, function(error) {
		alert("유저 정보 불러오기 실패");
		console.error("유저 정보 불러오기 실패", error);
	});

	$scope.updateUserInfo = function(currentPassword, newPassword, newPasswordCheck, name, beforeEmail, afterEmail) {

		const updateData = {
			currentPassword: currentPassword,
			newPassword: newPassword,
			newPasswordCheck: newPasswordCheck,
			username: name == undefined ? $scope.userInfo.username : name
		};

		if (beforeEmail) {
			updateData.email = beforeEmail + '@' + afterEmail;
		}

		if (newPassword != newPasswordCheck) {
			alert("입력한 비밀번호와 비밀번호확인이 일치하지 않습니다.");
		} else if (currentPassword && newPassword && (currentPassword == newPassword)) {
			alert("현재비밀번호와 새 비밀번호가 일치합니다.");
		} else if ((!beforeEmail && afterEmail) || (beforeEmail && !afterEmail)) {
			alert("이메일을 입력해주세요.");
		} else if (name == "") {
			alert("이름을 입력해주세요.");
		} else if (currentPassword && !newPassword) {
			alert("새 비밀번호를 입력해주세요.");
		} else if (!currentPassword && newPassword) {
			alert("현재비밀번호를 입력해주세요.");
		} else {
			InfoFactory.userUpdate({}, updateData, function() {
				alert("개인정보수정이 정상적으로 완료되었습니다.");
				$window.location.href = "/";
			})
		}
	};

	/**
	 * @function redirectToMain main.html로 이동 후 새로고침하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	$scope.redirectToMain = function() {
		$location.path('/');
		$route.reload();
	};

	$scope.newPassword = "";
	$scope.checkPassword = function() {
		// 비밀번호 유효성 검사
		const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;

		if (!passwordPattern.test($scope.newPassword)) {
			alert("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
			$scope.newPassword = "";
		}
	};


});