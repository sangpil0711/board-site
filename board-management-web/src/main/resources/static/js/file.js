
app.controller("BoardFile", function($scope, ExplorerFactory, Upload) {

	// 해당 폴더 파일 리스트
	$scope.files = [];
	// 업로드되는 파일 데이터
	$scope.selectedFiles = [];
	// 생성되는 폴더 이름
	$scope.newFolderName = '';

	/**
	 * @function exploreFile 해당 폴더의 파일 리스트를 가져오는 함수
	 * 
	 * @param path 파일 경로
	 * @param name 파일 이름
	 * 
	 * @author 황상필
	 * @since 2023. 11. 17.
	 */
	let exploreFile = function(path, name) {
		ExplorerFactory.exploreFile({ Path: path, Name: name }, function(response) {
			$scope.files = response;
			fileDepth($scope.files, 0);
		}, function(error) {
			console.error("파일 리스트 불러오기 실패", error);
		});
	};

	exploreFile(null, null);

	let fileDepth = function(files, depth) {
		files.forEach(function(file) {
			file.depth = depth;

			if (file.child && file.child.length > 0) {
				fileDepth(file.child, depth + 1);
			}
		})
	};

	/**
	 * @function childFileState 해당 폴더의 하위 파일 folderState를 변경하는 함수
	 * 
	 * @param item 상위 폴더
	 * @param folderState 폴더 상태
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	let childFileState = function(file, folderState) {
		file.folderState = folderState;

		// child가 존재하면 child를 돌면서 folderState 변경
		if (file.child && file.child.length > 0) {
			file.child.forEach(function(childItem) {
				childFileState(childItem, folderState);
			});
		}
	}

	/**
	 * @function openFolder 폴더 펼치기, 닫기를 수행하는 함수
	 * 
	 * @param file 해당 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.openFolder = function(file) {
		file.folderState = !file.folderState;
		if (!file.folderState) {
			childFileState(file, false);
		}
	};

	/**
	 * @function onFileSelect 선택한 파일을 변수에 할당하는 함수
	 * 
	 * @param $files 선택된 파일
	 * @param folder 파일을 업로드할 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.onFileSelect = function($files, folder) {

		$files.forEach(function(file) {
			$scope.selectedFiles.push(file);
		})

		insertFile(folder);
		$scope.selectedFiles = [];
	};

	/**
	 * @function insertFile 파일 정보를 서버로 전달하는 함수
	 * 
	 * @param folder 파일을 업로드할 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	let insertFile = function(folder) {
		Upload.upload({
			url: '/fileExplorer',
			method: 'POST',
			params: {},
			data: {
				path: folder === null ? null : folder.path,
				name: folder === null ? null : folder.name,
				files: $scope.selectedFiles
			},
		}).success(function() {
			exploreFile(null, null);
		}).error(function() {
			alert('파일 업로드 실패');
		})
	};

	/**
	 * @function downloadFile 파일을 다운로드하는 함수
	 * 
	 * @param name 다운로드되는 파일 이름
	 * @param path 다운로드되는 파일 경로
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.downloadFile = function(file) {
		ExplorerFactory.downloadFile({ Name: file.name, Path: file.path }, function() {
			window.location.href = '/fileExplorer/' + encodeURIComponent(file.name) + '?Path=' + encodeURIComponent(file.path);
		}, function(error) {
			console.error("파일 다운로드 실패", error);
		});
	}

	/**
	 * @function deleteFile 파일을 다운로드하는 함수
	 * 
	 * @param file 삭제할 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.deleteFile = function(file) {
		if (confirm("파일을 삭제하시겠습니까?")) {
			ExplorerFactory.deleteFile({ Name: file.name, Path: file.path }, function() {
				exploreFile(null, null);
			}, function(error) {
				console.error("파일 삭제 실패", error);
			})
		}
	};

	/**
	 * @function makeChildFolder 폴더 생성 박스를 표시하는 함수
	 * 
	 * @param folder 폴더를 생성할 상위폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 24.
	 */
	$scope.makeChildFolder = function(folder) {
		if (folder == null) {
			$scope.childFolderBox = true;
			otherFileState($scope.files, folder, false, false);
		} else {
			$scope.childFolderBox = false;
			folder.childFolderBox = true;
			folder.folderState = true;
			otherFileState($scope.files, folder, false, false);
		}
	};

	/**
	 * @function cancelChildFolder 폴더 생성 박스를 취소하는 함수
	 * 
	 * @param folder 폴더 생성을 취소할 상위폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 24.
	 */
	$scope.cancelChildFolder = function(folder) {
		if (folder == null) {
			$scope.childFolderBox = false;
		} else {
			folder.childFolderBox = false;
		}
	};

	/**
	 * @function makeFolder 폴더를 생성하는 함수
	 * 
	 * @param folder 폴더를 생성할 상위폴더
	 * @param newFolderName 생성한 폴더의 이름
	 * 
	 * @author 황상필
	 * @since 2023. 11. 24.
	 */
	$scope.makeFolder = function(folder, newFolderName) {

		// 폴더 생성 시 필요한 정보		
		let folderData = {
			path: folder == null ? null : folder.path,
			newFolderName: newFolderName
		};

		if (newFolderName == '') {
			alert("파일 이름을 입력해주세요.")
		} else {
			ExplorerFactory.createFolder({ Name: folder === null ? "null" : folder.name }, folderData, function() {
				exploreFile(null, null);
			}, function(error) {
				console.error("폴더 생성 실패", error);
			});
		}
	};

	/**
	 * @function updateFile 파일 이름 수정 박스를 표시하는 함수
	 * 
	 * @param file 수정할 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 27.
	 */
	$scope.updateFile = function(file) {
		file.updateFileBox = true;
		file.childFolderBox = false;
		$scope.childFolderBox = false;
		otherFileState($scope.files, file, false, false);
	};

	/**
	 * @function otherFileState 다른 파일의 updateFileBox와 childFolderBox 상태를 변경하는 함수
	 * 
	 * @param files 파일 리스트
	 * @param selectFile 선택한 파일
	 * @param updateFileBox 파일 수정 상태
	 * @param childFolderBox 폴더 생성 상태
	 * 
	 * @author 황상필
	 * @since 2023. 11. 28.
	 */
	let otherFileState = function(files, selectFile, updateFileBox, childFolderBox) {

		// 파일 전체를 반복하면서 동작 수행 
		files.forEach(function(item) {

			// 선택한 파일이 아니면 updateFileBox와 childFolderBox를 변경
			if (item !== selectFile) {
				item.updateFileBox = updateFileBox;
				item.childFolderBox = childFolderBox;
			}

			// child가 존재하면 child를 돌면서 updateFileBox와 childFolderBox를 변경
			if (item.child && item.child.length > 0) {
				otherFileState(item.child, selectFile, updateFileBox, childFolderBox);
			}
		});
	}

	/**
	 * @function cancelUpdateFile 파일 이름 수정 박스를 취소하는 함수
	 * 
	 * @param file 수정할 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 27.
	 */
	$scope.cancelUpdateFile = function(file) {
		file.updateFileBox = false;
	};

	/**
	 * @function update 파일 이름을 수정하는 함수
	 * 
	 * @param file 수정할 파일
	 * @param newFileName 변경한 파일 이름
	 * 
	 * @author 황상필
	 * @since 2023. 11. 27.
	 */
	$scope.update = function(file, newFileName) {
		if (newFileName == '') {
			alert("파일 이름을 입력해주세요");
		} else {
			// 파일 이름 변경 시 필요한 데이터
			let fileData = {
				name: file.name,
				path: file.path,
				newFileName: newFileName == undefined ? file.name : newFileName
			}
			ExplorerFactory.updateFile(fileData, function() {
				exploreFile(null, null);
			}, function(error) {
				console.error("파일 이름 변경 실패", error);
			});
		}
	};

	$scope.clickFile = function(file) {
		$scope.clickItem = file;
	}

	$scope.moveFile = function(file) {
		$scope.dragFile = file;
		console.log(file);
	}

	$scope.onFileDrop = function(folder) {
		console.log(folder);
		Upload.upload({
			url: '/fileExplorer',
			method: 'PATCH',
			params: {},
			data: { 	// 요청된 경로의 Controller로 전달할 데이터
				fileName: $scope.dragFile.name,
				folderName: folder.name,
				oldPath: $scope.dragFile.path,
				newPath: folder.path
			},
		}).success(function() {
			exploreFile(null, null);
		}).error(function() {
			alert('파일 업로드 실패');
		})
	}

});