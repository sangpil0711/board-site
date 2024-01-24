app.controller("BoardUser", function($scope, UserManageFactory) {

	// 사용자 관리 현재 페이지
	$scope.currentPage = 1;
	// 페이지당 사용자 수	
	$scope.itemsPerPage = 5;
	// 화면에 표시되는 최대 페이지 수
	$scope.maxSize = 5;
	// 선택할 수 있는 페이지당 사용자 수 
	$scope.options = [
		{ name: '5개씩 보기', value: 5 },
		{ name: '10개씩 보기', value: 10 },
		{ name: '15개씩 보기', value: 15 },
		{ name: '20개씩 보기', value: 20 }
	];

	// 권한 선택 옵션
	$scope.gradeOptions = [
		{ name: '사용자', value: 1 },
		{ name: '관리자', value: 0 }
	];

	/**
	 * @function getUserInfo 사용자와 관리자 리스트와 총 인원 수 를 가져오는 함수
	 * 
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	let getUserInfo = function() {
		UserManageFactory.getUserInfo({ pageNumber: $scope.currentPage, itemSize: $scope.itemsPerPage }, function(response) {
			$scope.userList = response.userList;
			$scope.totalPage = Math.ceil(response.totalCount / $scope.itemsPerPage);
			$scope.totalItems = response.totalCount;

			for (let i = 0; i < $scope.userList.length; i++) {
				let user = $scope.userList[i];
				
				user.selectedGrade = user.gradeId;

				if (user.gradeId == 0) {
					user.gradeId = "관리자";
				} else {
					user.gradeId = "사용자";
				}
				
			}

		}, function(error) {
			alert("사용자 데이터 불러오기 실패");
			console.error("사용자 데이터 불러오기 실패", error);
		})
	};
	
	getUserInfo();

	/**
	 * @function deleteUser 사용자를 삭제하는 함수
	 * 
	 * @param id 아이디
	 * 
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	$scope.deleteUser = function(id) {
		if (confirm("사용자를 삭제하시겠습니까?")) {
			UserManageFactory.deleteUser({ id: id }, function() {
				alert("삭제가 완료되었습니다.");
				getUserInfo();
			}, function(error) {
				alert("사용자 삭제 실패");
				console.error("사용자 삭제 실패", error);
			})
		}
	};

	/**
	 * @function updateGrade 사용자 권한 번호를 수정하는 함수
	 * 
	 * @param id 아이디
	 * @param gradeId 권한 번호
	 * 
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	$scope.updateGrade = function(id, gradeId) {

		const updateGrade = {
			id: id,
			gradeId: gradeId
		};

		UserManageFactory.updateGrade({}, updateGrade, function() {
			alert("권한 수정이 완료되었습니다.");
			getUserInfo();
		}, function(error) {
			alert("권한 수정 실패");
			console.error("권한 수정 실패", error);
		})
	};

	/**
	 * @function showUpdateBox 시스템 수정 박스를 표시하는 함수
	 * 
	 * @param user 해당 유저
	 * 
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	$scope.showUpdateBox = function(user) {
		user.updateBox = true;
		changeOtherState($scope.userList, user, false);
	};

	/**
	 * @function changeOtherState 선택된 사용자를 제외한 다른 사용자들의 상태를 변경하는 함수
	 * 
	 * @param userList 유저 리스트
	 * @param selectUser 선택된 사용자
	 * @param updateBox 수정 박스 상태
	 * 
	 * @author 박상현			
	 * @since 2024. 01. 18.
	 */
	let changeOtherState = function(userList, selectUser, updateBox) {
		userList.forEach(function(user) {
			if (user !== selectUser) {
				user.updateBox = updateBox;
			}
		})
	};

	/**
	 * @function cancelUpdateBox 사용자 수정 박스를 취소하는 함수
	 * 
	 * @param user 해당 사용자
	 * 
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	$scope.cancelUpdateBox = function(user) {
		user.updateBox = false;
		getUserInfo();
	};
	
	/**
	 * @function updatePage 페이지 번호 변경 함수
	 * 
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	$scope.updatePage = function() {
		$scope.addSystemBox = false;
		getUserInfo();
	};

});