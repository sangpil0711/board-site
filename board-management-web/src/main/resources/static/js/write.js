app.controller("BoardWrite", function($scope, $location, Upload, BoardFactory) {

	// 업로드되는 파일 크기의 합
	$scope.totalSize = 0;
	// 업로드되는 파일 이름
	$scope.fileNames = [];
	// 업로드되는 파일 데이터
	$scope.selectedFiles = [];

	/**
	 * @function onFileSelect 선택된 파일을 변수에 할당하고 크기를 제한하는 함수
	 * 
	 * @param $files 선택된 파일
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	$scope.onFileSelect = function($files) {

		let exceedSizeFile = false;

		$files.forEach(function(file) {
			if ($scope.totalSize + file.size > $scope.fileMaxSize * 1024 * 1024) {
				exceedSizeFile = true;
			} 
			// 선택된 파일의 크기가 허용된 최대 용량을 초과하지 않으면 각 변수에 파일 데이터 할당 
			else {
				$scope.selectedFiles.push(file);
				$scope.fileNames.push(file.name);
				$scope.totalSize += file.size;
			}
		})

		// 선택된 파일의 크기가 허용된 최대 용량을 초과하면 알림창 표시
		if (exceedSizeFile) {
			alert("선택한 파일의 용량이 " + $scope.fileMaxSize + "MB를 초과합니다.");
		}
	};

	/**
	 * @function insert 게시글을 등록하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	$scope.insert = function() {
		Upload.upload({
			url: '/boards',
			method: 'POST',
			params: {},
			data: { 	// 요청된 경로의 Controller로 전달할 데이터
				index: $scope.board.index,
				title: $scope.board.title,
				text: $scope.board.text,
				userId: $scope.board.userId,
				category: 0,
				createDate: $scope.board.createDate,
				files: $scope.selectedFiles,
				totalSize: $scope.totalSize
			},
		}).success(function() {
			$scope.redirectToBoard();
		}).error(function() {
			alert('파일 업로드 실패');
		})
	};

	/**
	 * @function redirectToBoard /board 경로로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	$scope.redirectToBoard = function() {
		$location.path('/board');
	};

    /**
	 * @function excludeFile 해당 파일을 리스트에서 삭제하는 함수
	 * 
	 * @param index 해당 파일 index
	 * 
	 * @author 황상필
	 * @since 2023. 10. 31.
	 */
	$scope.excludeFile = function(index) {
		if (confirm("파일을 삭제하시겠습니까?")) {
			$scope.totalSize -= $scope.selectedFiles[index].size;
			$scope.fileNames.splice(index, 1);
			$scope.selectedFiles.splice(index, 1);
		}
	};
	
	/**
	 * @function getFileSet 파일 설정 정보를 가져오는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 25.
	 */
	BoardFactory.getFileSet(function(response) {
		$scope.fileType = response.fileType;
		$scope.fileMaxSize = response.fileMaxSize;
	});
});
