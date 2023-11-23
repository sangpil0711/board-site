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

   $scope.openFolder = function(index) {
      $scope.folderState[index] = !$scope.folderState[index];
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
	
	    $scope.redirectToFileExplorer = function() {
        $location.path('/fileExplorer');
    }
	
	
   
});