app.controller("BoardInfo", function($scope, $location, $route, InfoFactory, $window) {

	/**
	 * @function userInfo 개인정보수정 시 필요한 유저 데이터를 가져오는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	InfoFactory.userInfo({}, function(response) {
		$scope.userInfo = response;
		
		// 유저의 이메일이 존재할 경우 동작
		if ($scope.userInfo.email) {
			let email = $scope.userInfo.email.split('@');
			$scope.beforeEmail = email[0];
			$scope.afterEmail = email[1];
		}
	}, function(error) {
		alert("유저 정보 불러오기 실패");
		console.error("유저 정보 불러오기 실패", error);
	});

   /**
    * @function updateUserInfo 유저 데이터를 업데이트하는 함수
    * 
    * @param currentPassword 현재 비밀번호
    * @param newPassword 새 비밀번호
    * @param newPasswordCheck 새 비밀번호 확인
    * @param name 이름
    * @param beforeEmail 이메일 앞부분
    * @param afterEmail 이메일 뒷부분
    * 
    * @author 황상필
    * @since 2024. 01. 16.
    */
   $scope.updateUserInfo = function(currentPassword, newPassword, newPasswordCheck, name, beforeEmail, afterEmail) {

		// 수정 시 필요한 데이터
		const updateData = {
			currentPassword: currentPassword,
			newPassword: newPassword,
			newPasswordCheck: newPasswordCheck,
			username: name == undefined ? $scope.userInfo.username : name
		};

		// 이메일이 입력되었을 경우 동작
		if (beforeEmail) {
			updateData.email = beforeEmail + '@' + afterEmail;
		}

		// 새 비밀번호가 입력되어있고 새 비밀번호와 새 비밀번호확인이 일치하지 않을 경우 동작
		if (newPassword && newPassword != newPasswordCheck) {
			alert("입력한 비밀번호와 비밀번호확인이 일치하지 않습니다.");
		} 
		// 현재비밀번호와 새 비밀번호가 입력되어있고 현재비밀번호와 새 비밀번호가 일치할 경우 동작
		else if (currentPassword && newPassword && (currentPassword == newPassword)) {
			alert("현재비밀번호와 새 비밀번호가 일치합니다.");
		} 
		// 이메일 앞부분과 이메일 뒷부분 중 하나만 입력되었을 경우 동작
		else if ((!beforeEmail && afterEmail) || (beforeEmail && !afterEmail)) {
			alert("이메일을 입력해주세요.");
		} 
		// 이름이 입력되지 않았을 경우 동작
		else if (name == "") {
			alert("이름을 입력해주세요.");
		} 
		// 현재비밀번호가 입력되어있고 새 비밀번호가 입력되지 않았을 경우 동작
		else if (currentPassword && !newPassword) {
			alert("새 비밀번호를 입력해주세요.");
		} 
		// 현재비밀번호가 입력되지 않고 새 비밀번호가 입력되었을 경우 동작
		else if (!currentPassword && newPassword) {
			alert("현재비밀번호를 입력해주세요.");
		} 
		else {
			InfoFactory.userUpdate({}, updateData, function() {
				alert("개인정보수정이 정상적으로 완료되었습니다.");
				$window.location.href = "/";
			}, function(error) {
				alert("현재비밀번호가 일치하지 않습니다.");
				console.error("현재비밀번호가 일치하지 않습니다.", error);
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

	/**
	 * @function checkPassword 비밀번호 조건을 검사하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	$scope.checkPassword = function() {
		const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;

		if (!passwordPattern.test($scope.newPassword)) {
			alert("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
		}
	};


});