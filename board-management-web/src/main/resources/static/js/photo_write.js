app.controller("PhotoWrite", function($scope, $location, Upload) {
	
	// 업로드되는 파일 크기의 합
	let totalSize = 0;
	// 업로드되는 파일 크기의 합
	$scope.fileNames = [];
	// 업로드되는 파일 데이터
	$scope.selectedFiles = [];
	
	/**
	 * @function onFileSelect 선택된 파일을 변수에 할당하고 크기를 제한하는 함수
	 * 
	 * @param $files 선택된 파일
	 * 
	 * @author 박상현
	 * @since 2023. 11. 06.
	 */
	$scope.onFileSelect = function($files) {

		let exceedSizeFile = false;

		$files.forEach(function(file) {
			if (totalSize + file.size > 100 * 1024 * 1024) {
				exceedSizeFile = true;
			} 
			else {
				$scope.selectedFiles.push(file);
				$scope.fileNames.push(file.name);
				totalSize += file.size;
			}
		})
		if (exceedSizeFile) {
			alert("선택한 파일의 용량이 100MB를 초과합니다.");
		}
	};
	
	/**
	 * @function redirectToPhotoBoard photo_board.html로 이동하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
    $scope.redirectToPhotoBoard = function() {
        $location.path('/photo');
    }

	/**
	 * @function insertPhoto 사진게시판에 게시물을 추가하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	$scope.insertPhoto = function() {
			Upload.upload({
				url: '/photos',
				method: 'POST',
				params: {},
				data: { 	// 요청된 경로의 Controller로 전달할 데이터
					index: $scope.photoBoard.index,
					title: $scope.photoBoard.title,
					text: $scope.photoBoard.text,
					userId: $scope.photoBoard.userId,
					category: 1,
					createDate: $scope.photoBoard.createDate,
					files: $scope.selectedFiles,
				},
			}).success(function() {
				$scope.redirectToPhotoBoard();
			}).error(function() {
				alert('파일 업로드 실패');
			})
		};
	
	/**
	 * @function excludeFile 해당 파일을 리스트에서 삭제하는 함수
	 * 
	 * @param index 해당 파일 index
	 * 
	 * @author 박상현
	 * @since 2023. 11. 06.
	 */
		$scope.excludeFile = function(index) {
		if (confirm("파일을 삭제하시겠습니까?")) {
			totalSize -= $scope.selectedFiles[index].size;
			$scope.fileNames.splice(index, 1);
			$scope.selectedFiles.splice(index, 1);
		}
	}
	
	
});
