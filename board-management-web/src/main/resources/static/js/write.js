/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * general_write.html에 필요한 메서드 작성
 * 
 * 작성일 : 2023.09.15
 * 작성자 : 황상필
 */
app.controller("BoardWrite", function($scope, $location, Upload) {

	let totalSize = 0;		// 업로드되는 파일 크기의 합

	$scope.fileNames = [];		// 업로드되는 파일 이름
	$scope.selectedFiles = [];		// 업로드되는 파일 데이터

	/**
	 * @function selectFile 파일탐색기가 실행되어서 파일을 선택할 수 있는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	$scope.selectFile = function() {
		document.getElementById("fileInput").click();
	}

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
			if (totalSize + file.size > 100 * 1024 * 1024) {
				exceedSizeFile = true;
			} else {
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
	 * @function insert 게시글을 등록하는 함수
	 * 
	 * 
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	$scope.insert = function() {
		Upload.upload({
			url: '/boards',
			method: 'POST',
			params: {},
			data: {
				index: $scope.board.index,
				title: $scope.board.title,
				text: $scope.board.text,
				userId: $scope.board.userId,
				category: 0,
				createDate: $scope.board.createDate,
				files: $scope.selectedFiles,
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

	$scope.excludeFile = function(index) {

		totalSize -= $scope.selectedFiles[index].size;
		$scope.fileNames.splice(index, 1);
		$scope.selectedFiles.splice(index, 1);

		console.log($scope.selectedFiles);

	}

});
