
app.controller("BoardFile", function($scope, ExplorerFactory, Upload, $location) {

   $scope.files = [];
   $scope.folderState = [];

   let exploreFile = function(path, name) {
      ExplorerFactory.exploreFile({
         parentPath: path,
         directoryName: name
      }, function(response) {
         $scope.files = response;
      })
   };

   exploreFile(null, null);

   let childFileState = function(item, folderState) {
	item.folderState = folderState;
	if (item.child && item.child.length > 0) {
		item.child.forEach(function(childItem) {
			childFileState(childItem, folderState);
		});
	}
}

   $scope.openFolder = function(file) {
	file.folderState = !file.folderState;
	if (!file.folderState) {
		childFileState(file, false);
	}
};
   
   $scope.selectFile = function() {
		document.getElementById("fileInput").click();
	}
	
	$scope.onFileSelect = function($files) {

		$files.forEach(function(file) {
			
				$scope.selectedFiles.push(file);
		})
		insertFile();
	};
   
   	let insertFile = function() {
		Upload.upload({
			url: '/fileExplorer',
			method: 'POST',
			params: {},
			data: {
				files: $scope.selectedFiles,
			},
		}).success(function() {
			$scope.redirectToFileExplorer();
		}).error(function() {
			alert('파일 업로드 실패');
		})
	};

	$scope.downloadFile = function(name, path) {
		ExplorerFactory.downloadFile({ Name: name, Path: path }, function() {
			window.location.href = '/fileExplorer/' + encodeURIComponent(name) + '?Path=' + encodeURIComponent(path);
		})
	}

	
	    $scope.redirectToFileExplorer = function() {
        $location.path('/fileExplorer');
    }
	
	
   
});