
app.controller("BoardFile", function($scope, ExplorerFactory, Upload, $location, $route) {

	// 해당 폴더 파일 리스트
	$scope.files = [];
	// 업로드되는 파일 데이터
	$scope.selectedFiles = [];

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
		ExplorerFactory.exploreFile({ parentPath: path, directoryName: name }, function(response) {
			$scope.files = response;
		})
	};

	exploreFile(null, null);

	/**
	 * @function childFileState 해당 폴더의 하위 파일 folderState를 변경하는 함수
	 * 
	 * @param item 상위 폴더
	 * @param folderState 폴더 상태
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	let childFileState = function(item, folderState) {
		item.folderState = folderState;

		// child가 존재하면 child를 돌면서 folderState 변경
		if (item.child && item.child.length > 0) {
			item.child.forEach(function(childItem) {
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
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.onFileSelect = function($files, item) {
		$files.forEach(function(file) {
			$scope.selectedFiles.push(file);
		})

		if ($scope.selectedFiles && $scope.selectedFiles.length > 0) {
			insertFile(item);
			$scope.selectedFiles = [];
		}

	};
	
	$scope.deleteFile = function(file) {
      if (confirm("파일을 삭제하시겠습니까?")) {
         ExplorerFactory.deleteFile({ Name: file.name, Path: file.path }, function() {
            redirectToFileExplorer();
         })
      }
   }

	/**
	 * @function insertFile 파일 정보를 서버로 전달하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	let insertFile = function(item) {
		Upload.upload({
			url: '/fileExplorer',
			method: 'POST',
			params: {},
			data: {
				path: item.path,
				name: item.name,
				files: $scope.selectedFiles
			},
		}).success(function() {
			redirectToFileExplorer();
		}).error(function() {
			alert('파일 업로드 실패');
		})
	};

	/**
	 * @function redirectToFileExplorer file_explorer.html 화면으로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	let redirectToFileExplorer = function() {
		$location.path('/file');
		$route.reload();
	}

	/**
	 * @function downloadFile 파일을 다운로드하는 함수
	 * 
	 * @param name 다운로드되는 파일 이름
	 * @param path 다운로드되는 파일 경로
	 * 
	 * @author 황상필
	 * @since 2023. 11. 23.
	 */
	$scope.downloadFile = function(name, path) {
		ExplorerFactory.downloadFile({ Name: name, Path: path }, function() {
			window.location.href = '/fileExplorer/' + encodeURIComponent(name) + '?Path=' + encodeURIComponent(path);
		})
	}

});