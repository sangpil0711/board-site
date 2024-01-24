app.controller("BoardSystem", function($scope, SystemFactory) {

	// 현재 페이지
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
	// 시스템 생성 박스
	$scope.addSystemBox = false;

	/**
	 * @function showAddSystemBox 시스템 생성 박스를 표시하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.showAddSystemBox = function() {
		$scope.addSystemBox = true;
		changeOtherState($scope.systemList, null, false);
	};

	/**
	 * @function cancelAddSystemBox 시스템 생성 박스를 취소하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.cancelAddSystemBox = function() {
		$scope.addSystemBox = false;
	};

	/**
	 * @function findPage 시스템 리스트와 총 시스템 수를 가져오는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	let findPage = function() {
		SystemFactory.findPage({ pageNumber: $scope.currentPage, itemSize: $scope.itemsPerPage }, function(response) {
			$scope.systemList = response.systemList;
			$scope.totalPage = Math.ceil(response.totalCount / $scope.itemsPerPage);
			$scope.totalItems = response.totalCount;
		}, function(error) {
			alert("시스템 불러오기 실패");
			console.error("시스템 불러오기 실패", error);
		})
	};

	findPage();

	/**
	 * @function updatePage 페이지 번호 변경 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 18.
	 */
	$scope.updatePage = function() {
		$scope.addSystemBox = false;
		findPage();
	};

	/**
	 * @function create 시스템을 생성하는 함수
	 * 
	 * @param key 시스템 키
	 * @param value 시스템 키에 대한 값
	 * @param description 시스템 키에 대한 설명
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.create = function(key, value, description) {

		const systemData = {
			key: key,
			value: value,
			description: description
		}
		
		// key, value, description 중 하나라도 입력되지 않으면 동작
		if (!key || !value || !description) {
			alert("입력하지 않은 항목이 있습니다.");
		}
		else {
			SystemFactory.createSystem({}, systemData, function() {
				alert("생성이 완료되었습니다.");
				$scope.addSystemBox = false;
				findPage();
			}, function(error) {
				alert("시스템 프로퍼티 키 값이 중복되었습니다.");
				console.error("시스템 프로퍼티 키 값이 중복되었습니다.", error);
			});
		}
	};

	/**
	 * @function delete 시스템을 삭제하는 함수
	 * 
	 * @param key 시스템 키
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.delete = function(key) {
		if (confirm("시스템을 삭제하시겠습니까?")) {
			SystemFactory.deleteSystem({ key: key }, function() {
				alert("삭제가 완료되었습니다.");
				findPage();
			}, function(error) {
				alert("시스템 삭제 실패");
				console.error("시스템 삭제 실패", error);
			})
		}
	};

	/**
	 * @function showUpdateBox 시스템 수정 박스를 표시하는 함수
	 * 
	 * @param item 해당 시스템
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.showUpdateBox = function(system) {
		system.updateBox = true;
		changeOtherState($scope.systemList, system, false);
		$scope.addSystemBox = false;
	};

	/**
	 * @function cancelUpdateBox 시스템 수정 박스를 취소하는 함수
	 * 
	 * @param system 해당 시스템
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.cancelUpdateBox = function(system) {
		system.updateBox = false;
		findPage();
	};

	/**
	 * @function changeOtherState 선택된 시스템을 제외한 다른 시스템들의 상태를 변경하는 함수
	 * 
	 * @param systemList 시스템 리스트
	 * @param selectItem 선택된 시스템
	 * @param updateBox 수정 박스 상태
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	let changeOtherState = function(systemList, selectItem, updateBox) {
		systemList.forEach(function(system) {
			if (system !== selectItem) {
				system.updateBox = updateBox;
			}
		})
	};

	/**
	 * @function update 시스템을 수정하는 함수
	 * 
	 * @param key 시스템 키
	 * @param value 시스템 키에 대한 값
	 * @param description 시스템 키에 대한 설명
	 * 
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	$scope.update = function(key, value, description) {

		const updateData = {
			key: key,
			value: value,
			description: description
		};
		
		// value와 description 중 하나라도 입력되지 않았을 경우 동작
		if (!value || !description) {
			alert("입력하지 않은 항목이 있습니다.");
		} 
		else {
			SystemFactory.updateSystem({}, updateData, function() {
				alert("수장이 완료되었습니다.");
				findPage();
			}, function(error) {
				alert("시스템 수정 실패");
				console.error("시스템 수정 실패", error);
			})
		}
	};

});