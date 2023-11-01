app.controller("BoardUpdate", function($scope, BoardFactory, $location, $routeParams, Upload) {

	let index = $routeParams.index; 	// 라우팅으로 받아오는 게시글 번호
	let totalSize = 0;		// 업로드되는 파일의 총 크기
	let deleteFiles = [];
	let addFiles = [];

	$scope.board = { 	// 수정될 제목과 내용
		title: "",
		text: ""
	};

	$scope.fileNames = [];		// 업로드되는 파일 이름
	$scope.selectedFiles = [];		// 업로드되는 파일 데이터

	/**
	 * @function getDataByIndex 게시판 번호에 맞는 데이터를 불러오는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	let getDataByIndex = function() {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
			$scope.selectedFiles = response.file;

			for (let i = 0; i < response.file.length; i++) {
				$scope.fileNames[i] = response.file[i].fileName;
				$scope.selectedFiles[i].size = response.file[i].fileSize
				totalSize += response.file[i].fileSize;
			}

		})
	};

	getDataByIndex();

	/**
	 * @function selectFile 파일탐색기가 실행되어서 파일을 선택할 수 있는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	$scope.selectFile = function() {
		document.getElementById("fileInput").click();
	};

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
				addFiles.push(file);
			}
		})

		if (exceedSizeFile) {
			alert("선택한 파일의 용량이 100MB를 초과합니다.");
		}

	};

	/**
	 * @function update 게시판 번호에 맞는 데이터를 수정하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	$scope.update = function() {
		BoardFactory.updateBoard({ index: index }, $scope.board, function() {
			$location.path('/board/read/' + index);
		})
	};

	/**
	 * @function redirectToRead general_read.html로 이동하는 함수
	 * 
	 * @param index 해당 게시글 번호
	 * 
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	$scope.redirectToRead = function() {
		$location.path('/board/read/' + index);
	};

	$scope.excludeFile = function(index) {
		let confirmDelete = confirm("파일을 삭제하시겠습니까?");
		if (confirmDelete) {
			totalSize -= $scope.selectedFiles[index].size;
			$scope.fileNames.splice(index, 1);
			let deleteFile = $scope.selectedFiles[index].fileId;
			let addFilesIndex = addFiles.findIndex(file => file.name === deleteFile.name);
			if (!addFiles.some(file => file.name === deleteFile.name)) {
				deleteFiles.push(deleteFile);
			} else {
				addFiles.splice(addFilesIndex, 1);
			}
		}
		console.log(deleteFiles);
	};

	$scope.update = function() {
		Upload.upload({
			url: '/boards/' + $scope.board.index,
			method: 'PATCH',
			params: {},
			data: {
				index: $scope.board.index,
				title: $scope.board.title,
				text: $scope.board.text,
				userId: $scope.board.userId,
				category: 0,
				createDate: $scope.board.createDate,
				addFiles: addFiles,
				deleteFiles: deleteFiles
			},
		}).success(function() {
			$scope.redirectToRead();
		}).error(function() {
			alert('파일 데이터 전송 실패');
		})
	};

});
