app.controller("BoardUpdate", function($scope, BoardFactory, $location, $routeParams, Upload) {

	// 라우팅으로 받아오는 게시글 번호
	let index = $routeParams.index;
	// 업로드되는 파일의 총 크기
	let totalSize = 0;
	// 삭제된 파일 리스트
	let deleteFiles = [];
	// 추가된 파일 리스트
	let addFiles = [];

	// 수정할 게시글
	$scope.board = {
		title: "",
		text: ""
	};

	// 업로드되는 파일 이름
	$scope.fileNames = [];
	// 업로드되는 파일 데이터
	$scope.selectedFiles = [];

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

			// 서버에서 가져온 파일 개수에 따라 각 변수에 파일 데이터 할당
			for (let i = 0; i < response.file.length; i++) {
				$scope.fileNames[i] = response.file[i].fileName;
				$scope.selectedFiles[i].size = response.file[i].fileSize
				totalSize += response.file[i].fileSize;
			}

		},
			function(error) {
				alert("게시물 데이터 불러오기 실패");
				console.error("게시물 데이터 불러오기 실패", error);
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
	 * @function uuidv4 파일의 uuid를 생성하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	let uuidv4 = function() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			var r = (Math.random() * 16) | 0,
				v = c == 'x' ? r : (r & 0x3) | 0x8;
			return v.toString(16);
		});
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
			}

			// 선택된 파일의 크기가 100MB를 초과하지 않으면 각 변수에 파일 데이터 할당 
			else {
				file.fileId = uuidv4();
				$scope.selectedFiles.push(file);
				$scope.fileNames.push(file.name);
				totalSize += file.size;
				addFiles.push(file);
			}
		})

		// 선택된 파일의 크기가 100MB를 초과하면 알림창 표시
		if (exceedSizeFile) {
			alert("선택한 파일의 용량이 100MB를 초과합니다.");
		}

	};

	/**
	 * @function redirectToRead general_read.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	$scope.redirectToRead = function() {
		$location.path('/board/read/' + index);
	};

	/**
	 * @function excludeFile 해당 파일을 리스트에서 삭제하는 함수
	 * 
	 * @param index 삭제할 파일 index
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	$scope.excludeFile = function(index) {
		let confirmDelete = confirm("파일을 삭제하시겠습니까?");

		// 파일을 삭제하는 동작
		if (confirmDelete) {
			totalSize -= $scope.selectedFiles[index].size;
			$scope.fileNames.splice(index, 1);

			// 삭제된 파일 UUID
			let deleteFileId = $scope.selectedFiles[index].fileId;
			$scope.selectedFiles.splice(index, 1);

			// 추가된 파일의 파일 UUID가 deleteFileId와 일치할 때의 index
			let addFilesIndex = addFiles.findIndex(file => file.fileId === deleteFileId);

			// 추가된 파일의 파일 UUID가 deleteFileId와 일치하면 addFiles 배열에서 삭제 
			if (addFiles.some(file => file.fileId === deleteFileId)) {
				addFiles.splice(addFilesIndex, 1);
			}

			// 추가된 파일의 파일 UUID가 deleteFileId와 일치하지 않으면 deleteFiles 배열에 추가
			else {
				deleteFiles.push(deleteFileId);
			}
		}
	};

	/**
	 * @function update 수정된 파일 내용을 서버로 전달하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 01.
	 */
	$scope.update = function() {
		Upload.upload({
			url: '/boards/' + $scope.board.index,
			method: 'PATCH',
			params: {},
			data: {		// 요청된 경로의 Controller로 전달할 데이터
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
