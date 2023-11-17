app.controller("BoardFile", function($scope, ExplorerFactory) {

	$scope.files = [];
	$scope.folderOpen = [];

	let fileExplorer = function(path, name, depth) {
		ExplorerFactory.fileExplorer({
			path: path,
			name: name,
			depth: depth
		}, function(response) {
			$scope.files = $scope.files.concat(response);
			console.log($scope.files);
		})
	};

	fileExplorer(null, null, 0);

	$scope.folderEvent = function(index, path, name, depth) {
		$scope.folderOpen[index] = !$scope.folderOpen[index];
		if ($scope.folderOpen[index]){
			fileExplorer(path, name, depth + 1);
		} else {
			fileExplorer(path.substr(0, path.lastIndexOf('\\')), '', depth - 1);
		}
	};

});
