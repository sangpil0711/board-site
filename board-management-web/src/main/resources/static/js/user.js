app.controller("BoardUser", function($scope, UserManageFactory) {

	// 게시판 현재 페이지
	$scope.currentPage = 1;
	// 페이지당 시스템 수	
	$scope.itemsPerPage = 5;
	// 화면에 표시되는 최대 페이지 수
	$scope.maxSize = 5;
	// 선택할 수 있는 페이지당 시스템 수 
	$scope.options = [
		{ name: '5개씩 보기', value: 5 },
		{ name: '10개씩 보기', value: 10 },
		{ name: '15개씩 보기', value: 15 },
		{ name: '20개씩 보기', value: 20 }
	];

	$scope.gradeOptions = [
		{ name: '사용자', value: 1 },
		{ name: '관리자', value: 0 }
	];

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
		});
	};
	getUserInfo();

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


	$scope.showUpdateBox = function(user) {
		user.updateBox = true;
		changeOtherState($scope.userList, user, false);
	};

	let changeOtherState = function(userList, selectItem, updateBox) {
		userList.forEach(function(user) {
			if (user !== selectItem) {
				user.updateBox = updateBox;
			}
		})
	};

	$scope.cancelUpdateBox = function(user) {
		user.updateBox = false;
		getUserInfo();
	};
	
	$scope.updatePage = function() {
		$scope.addSystemBox = false;
		getUserInfo();
	};

});