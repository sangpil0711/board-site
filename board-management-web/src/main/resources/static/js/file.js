
app.controller("BoardFile", function($scope, ExplorerFactory, Upload) {

	// 해당 폴더 파일 리스트
	$scope.files = [];
	// 업로드되는 파일 데이터
	const selectedFiles = [];
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
	const exploreFile = function(path, name) {
		ExplorerFactory.exploreFile({ path: path, name: name }, function(response) {
			$scope.files = response.fileList;
			$scope.firstPath = response.firstPath;
			setDepthFile($scope.files, 0);
		}, function(error) {
			console.error("파일 리스트 불러오기 실패", error);
		});
	};

	exploreFile(null, null);

	/**
	 * @function setDepthFile 파일에 depth를 설정해주는 함수
	 * 
	 * @param files 파일 리스트
	 * @param depth 파일 깊이
	 * 
	 * @author 황상필
	 * @since 2023. 11. 28.
	 */
	const setDepthFile = function(files, depth) {
		files.forEach(function(file) {
			file.depth = depth;

			if (file.child && file.child.length > 0) {
				setDepthFile(file.child, depth + 1);
			}
		})
	};

	/**
	 * @function openFolder 폴더 펼치기, 닫기를 수행하는 함수
	 * 
	 * @param folder 해당 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.openFolder = function(folder) {
		folder.folderState = !folder.folderState;
	};

	/**
	 * @function uploadSelectFiles 선택한 파일을 업로드하는 함수
	 * 
	 * @param files 선택된 파일
	 * @param folder 파일을 업로드할 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.uploadSelectFiles = function(files, folder) {
		files.forEach(function(file) {
			selectedFiles.push(file);
		});

		if (selectedFiles && selectedFiles.length > 0) {
			insertFile(folder);
			selectedFiles = [];
		}
	};

	/**
	 * @function insertFile 파일 정보를 서버로 전달하는 함수
	 * 
	 * @param folder 파일을 업로드할 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	const insertFile = function(folder) {
		const data = {
			files: selectedFiles
		};

		if (folder != null) {
			data.path = folder.path;
			data.name = folder.name;
		}

		Upload.upload({
			url: '/fileExplorer',
			method: 'POST',
			params: {},
			data: data,
			transformResponse: [
				function(data) {
					return data;
				}
			]
		}).then(function(message) {
			const dataMessage = JSON.parse(message.data)
			alert(`성공: ${dataMessage.successCount}, 실패: ${dataMessage.failCount}`);
			dataMessage.successFileNames.forEach(function(file) {
				
				// 배열에 추가될파일 정보
				const newFile = {
					name: file,
					isDirectory: false,
					path: folder == undefined ? $scope.firstPath : folder.path + "\\" + folder.name,
					depth: folder == undefined ? 0 : folder.depth + 1
				};

				// 최상위 폴더일 경우 동작
				if (folder == undefined) {
					$scope.files.push(newFile);
				}
				// 최상위 폴더가 아닐 경우 동작 
				else {
					folder.child.push(newFile);
				}
			})

		}).catch(function(error) {
			alert('파일 업로드 실패', error);
		})

	};

	/**
	 * @function downloadFile 파일을 다운로드하는 함수
	 * 
	 * @param file 다운로드할 파일 
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.downloadFile = function(file) {
		file.clickItem = true;
		ExplorerFactory.downloadFile({ name: file.name, path: file.path }, function() {
			window.location.href = '/fileExplorer/' + encodeURIComponent(file.name) + '?path=' + encodeURIComponent(file.path);
		}, function(error) {
			console.error("파일 다운로드 실패", error);
		});
	}

	/**
	 * @function deleteFile 파일을 삭제하는 함수
	 * 
	 * @param file 삭제할 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.deleteFile = function(file) {
		if (confirm("파일을 삭제하시겠습니까?")) {
			ExplorerFactory.deleteFile({ name: file.name, path: file.path }, function() {
				alert("파일 삭제 성공");
				deleteFileFromArray($scope.files, file.name, file.path);
			}, function(error) {
				alert("파일 삭제 실패");
				console.error("파일 삭제 실패", error);
			})
		}
	};

	/**
	 * @function deleteFileFromArray 배열에서 파일을 삭제하는 함수
	 * 
	 * @param files 파일리스트
	 * @param fileName 삭제할 파일 이름
	 * @param filePath 삭제할 파일 경로
	 * 
	 * @author 황상필
	 * @since 2023. 12. 05.
	 */
	const deleteFileFromArray = function(files, fileName, filePath) {
		files.forEach(function(file, index) {
			// 파일 이름과 경로가 일치하는 경우 배열에서 파일 삭제
			if (file.name === fileName && file.path === filePath) {
				files.splice(index, 1);
			}
			// 하위 파일이 존재할 경우 하위 파일 검사
			if (file.child && file.child.length > 0) {
				deleteFileFromArray(file.child, fileName, filePath);
			}
		});
	};

	/**
	 * @function showChildFolderBox 폴더 생성 박스를 표시하는 함수
	 * 
	 * @param folder 폴더를 생성할 상위 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 24.
	 */
	$scope.showChildFolderBox = function(folder) {

		// 최상위 폴더에 폴더를 생성할 경우 동작
		if (folder == null) {
			$scope.childFolderBox = true;
			changeOtherFileState($scope.files, folder, false, false);
		}
		// 최상위 폴더가 아닌 폴더에 폴더를 생성할 경우 동작
		else {
			$scope.childFolderBox = false;
			folder.childFolderBox = true;
			folder.folderState = true;
			changeOtherFileState($scope.files, folder, false, false);
		}

	};

	/**
	 * @function cancelChildFolderBox 폴더 생성 박스를 취소하는 함수
	 * 
	 * @param folder 폴더 생성을 취소할 상위 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 11. 24.
	 */
	$scope.cancelChildFolderBox = function(folder) {

		// 최상위 폴더의 폴더생성을 취소할 경우 동작
		if (folder == undefined) {
			$scope.childFolderBox = false;
		}
		// 최상위 폴더가 아닌 폴더의 폴더생성을 취소할 경우 동작
		else {
			folder.childFolderBox = false;
		}
	};

	/**
	 * @function makeFolder 폴더를 생성하는 함수
	 * 
	 * @param folder 폴더를 생성할 상위폴더
	 * @param newFolderName 생성한 폴더 이름
	 * 
	 * @author 황상필
	 * @since 2023. 11. 24.
	 */
	$scope.makeFolder = function(folder, newFolderName) {
		// 폴더 생성 시 필요한 정보      
		const folderData = {
			path: folder == undefined ? null : folder.path,
			newFolderName: newFolderName
		};

		// 파일 이름이 입력되지 않았을 경우 동작
		if (newFolderName == '') {
			alert("파일 이름을 입력해주세요.");
		}
		else {
			ExplorerFactory.createFolder({ name: folder == undefined ? null : folder.name }, folderData, function() {
				alert("폴더 생성 성공");

				// 배열에 폴더 추가 시 필요한 정보
				const newFolder = {
					name: newFolderName,
					isDirectory: true,
					child: [],
					path: folder == undefined ? $scope.firstPath : folder.path + "\\" + folder.name,
					depth: folder == undefined ? 0 : folder.depth + 1
				};

				// 최상위 폴더일 경우 동작
				if (folder == undefined) {
					$scope.files.push(newFolder);
					$scope.childFolderBox = false;
				}
				// 최상위 폴더가 아닐 경우 동작
				else {
					folder.child.push(newFolder);
					folder.childFolderBox = false;
				}

			}, function(error) {
				alert("폴더 생성 실패");
				console.error("폴더 생성 실패", error);
			});
		}
	};

	/**
	 * @function showUpdateFileBox 파일 이름 수정 박스를 표시하는 함수
	 * 
	 * @param file 수정할 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 27.
	 */
	$scope.showUpdateFileBox = function(file) {
		file.updateFileBox = true;
		file.childFolderBox = false;
		$scope.childFolderBox = false;
		changeOtherFileState($scope.files, file, false, false, false);
	};

	/**
	 * @function changeOtherFileState 다른 파일의 상태를 변경하는 함수
	 * 
	 * @param files 파일 리스트
	 * @param selectFile 선택한 파일
	 * @param updateFileBox 파일 수정 상태
	 * @param childFolderBox 폴더 생성 상태
	 * @param clickItem 파일 클릭 상태
	 * 
	 * @author 황상필
	 * @since 2023. 11. 28.
	 */
	const changeOtherFileState = function(files, selectFile, updateFileBox, childFolderBox, clickItem) {

		// 파일 전체를 반복하면서 동작 수행 
		files.forEach(function(item) {

			// 선택한 파일이 아니면 updateFileBox, childFolderBox와 변경
			if (item !== selectFile) {
				item.updateFileBox = updateFileBox;
				item.childFolderBox = childFolderBox;
				item.clickItem = clickItem;
			}

			// child가 존재하면 child를 돌면서 updateFileBox와 childFolderBox를 변경
			if (item.child && item.child.length > 0) {
				changeOtherFileState(item.child, selectFile, updateFileBox, childFolderBox, clickItem);
			}
		});
	}

	/**
	 * @function cancelUpdateFileBox 파일 이름 수정 박스를 취소하는 함수
	 * 
	 * @param file 수정할 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 27.
	 */
	$scope.cancelUpdateFileBox = function(file) {
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
		}

		// 파일 이름 변경 시 필요한 데이터
		const fileData = {
			name: file.name,
			path: file.path,
			newFileName: newFileName == undefined ? file.name : newFileName
		}

		ExplorerFactory.updateFile(fileData, function() {
			alert("파일 이름 변경 성공");
			file.name = fileData.newFileName;
			file.updateFileBox = false;
		}, function(error) {
			alert("파일 이름 변경 실패");
			console.error("파일 이름 변경 실패", error);
		});

	};

	/**
	 * @function clickFile 파일을 클릭하면 동작하는 함수
	 * 
	 * @param file 클릭한 파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 29.
	 */
	$scope.clickFile = function(file) {
		file.clickItem = !file.clickItem;
		changeOtherClickState($scope.files, file, false);
	};

	/**
	 * @function changeOtherClickState 다른 파일의 클릭 상태를 변경하는 함수
	 * 
	 * @param files 파일 리스트
	 * @param selectFile 선택한 파일
	 * @param clickItem 파일 클릭 상태
	 * 
	 * @author 황상필
	 * @since 2023. 12. 04.
	 */
	const changeOtherClickState = function(files, selectFile, clickItem) {

		// 파일 전체를 반복하면서 동작 수행 
		files.forEach(function(item) {

			// 선택한 파일이 아니면 updateFileBox, childFolderBox와 변경
			if (item !== selectFile) {
				item.clickItem = clickItem;
			}

			// child가 존재하면 child를 돌면서 updateFileBox와 childFolderBox를 변경
			if (item.child && item.child.length > 0) {
				changeOtherClickState(item.child, selectFile, clickItem);
			}
		});
	};

	/**
	 * @function dragFile 드래그한 파일정보를 가져오는 함수
	 * 
	 * @param file 드래그한 파일
	 * 
	 * @author 황상필
	 * @since 2023. 12. 07.
	 */
	$scope.dragFile = function(file) {
		$scope.dragItem = file;
	};

	/**
	 * @function dropFile 파일이 들어간 폴더 정보를 가져오는 함수
	 * 
	 * @param targetFolder 파일이 들어간 폴더
	 * 
	 * @author 황상필
	 * @since 2023. 12. 07.
	 */
	$scope.dropFile = function(targetFolder) {
		
		console.log(targetFolder);
		
		if (targetFolder != undefined) {

			// 파일 이동 시 필요한 파일 정보
			const moveFileData = {
				fileName: $scope.dragItem.name,
				folderName: targetFolder.name,
				oldPath: $scope.dragItem.path,
				newPath: targetFolder.path
			}

			deleteFileFromArray($scope.files, $scope.dragItem.name, $scope.dragItem.path);

			// 배열에 추가될 파일 정보
			const newFile = {
				name: $scope.dragItem.name,
				isDirectory: $scope.dragItem.isDirectory,
				path: targetFolder == undefined ? $scope.firstPath : targetFolder.path + "\\" + targetFolder.name,
				depth: targetFolder == undefined ? 0 : targetFolder.depth + 1
			};

			// 최상위 폴더일 경우 동작
			if (targetFolder == undefined) {
				$scope.files.push(newFile);
			}
			// 최상위 폴더가 아닐 경우 동작 
			else {
				targetFolder.child.push(newFile);
			}

			ExplorerFactory.moveFile(moveFileData, function() {
				alert("파일 이동 성공");
			}, function(error) {
				alert("파일 이동 실패");
				console.error("파일 이동 실패", error);
			});
		}

	};

});