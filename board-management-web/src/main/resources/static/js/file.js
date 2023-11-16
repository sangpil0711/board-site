app.controller("BoardFile", function($scope) {

	$scope.files = [
		{ name: 'innerFolder', subFiles: [{ name: 'folder', subFiles:[{ name: 'folder2', subFiles:[] }, { name: 'file.txt'} ,{ name: 'folder3', subFiles: [] }]}, { name: 'filetxt', subFiles:[{ name: 'folder2', subFiles: [] }] }] },
		{ name: '새 폴더', subFiles: [{ name: 'folder'}, { name: 'filetxt' }] },
		{ name: '새 텍스트 문서.txt' },
		{ name: '새 한컴오피스 한글 표준 문서.hwpx' }
	];

	$scope.folderState = $scope.files.map(function() {
		return { imgSrc: "/static/image/caret-right-solid.svg", extend: false };
	});

	$scope.folder = function(index, parentIndex) {

		console.log("Clicked index:", index, "Parent index:", parentIndex);

		if (parentIndex === undefined) {
			$scope.folderState[index].extend = !$scope.folderState[index].extend;
			updateImage(index);
			console.log($scope.folderState[index].extend);
		} else {
			$scope.files[parentIndex].subFiles[index].extend = !$scope.files[parentIndex].subFiles[index].extend;
			updateSubFolderImage(index, parentIndex);
			console.log($scope.files[parentIndex].subFiles[index].extend);
		}
	};

	let updateImage = function(index) {
		$scope.folderState[index].imgSrc = $scope.folderState[index].extend
			? "/static/image/caret-down-solid.svg"
			: "/static/image/caret-right-solid.svg";
	};

	let updateSubFolderImage = function(index, parentIndex) {
		$scope.folderState[parentIndex].imgSrc = $scope.files[parentIndex].subFiles[index].extend
			? "/static/image/caret-down-solid.svg"
			: "/static/image/caret-right-solid.svg";
	};

});