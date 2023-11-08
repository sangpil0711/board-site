app.controller("PhotoUpdate", function($scope, PhotoBoardFactory, $location, $routeParams, Upload) {
	
	//라우팅으로 받아오는 게시글 번호
    let index = $routeParams.index;  
    
    let totalSize = 0;
    
    let deleteFiles = [];
    
    let addFiles = [];

	//수정되는 제목과 내용
	$scope.photoBoard = {
	    title: "",
	    text: ""
	};


		$scope.fileNames = [];
		
		$scope.selectedFiles = [];
		
		$scope.photoBoard = [];
   /**
    * @function searchByPhotoIndex 게시판 번호에 맞는 데이터를 불러오는 함수
    * 
    * @param index 해당 게시판 번호
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
    let searchByPhotoIndex = function() {
        PhotoBoardFactory.readPhotoBoard({ index: index }, function(response) {
            $scope.photoBoard = response;
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
    
    searchByPhotoIndex();





	$scope.selectFile = function() {
		document.getElementById("fileInput").click();
	};





	$scope.onFileSelect = function($files) {

		let exceedSizeFile = false;

		$files.forEach(function(file) {
			if (totalSize + file.size > 100 * 1024 * 1024) {
				exceedSizeFile = true;
			}
			
			// 선택된 파일의 크기가 100MB를 초과하지 않으면 각 변수에 파일 데이터 할당 
			else {
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





	$scope.excludeFile = function(index) {
		let confirmDelete = confirm("파일을 삭제하시겠습니까?");
		
		// 파일을 삭제하는 동작
		if (confirmDelete) {
			totalSize -= $scope.selectedFiles[index].size;
			$scope.fileNames.splice(index, 1);
			
			// 삭제된 파일 id
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
    * @function updatePhoto 게시판 번호에 맞는 데이터를 수정하는 함수
    * 
    * @param index 해당 게시판 번호
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
	$scope.updatePhoto = function() {
		Upload.upload({
			url: '/photos/' + $scope.photoBoard.index,
			method: 'PATCH',
			params: {},
			data: {		// 요청된 경로의 Controller로 전달할 데이터
				index: $scope.photoBoard.index,
				title: $scope.photoBoard.title,
				text: $scope.photoBoard.text,
				userId: $scope.photoBoard.userId,
				category: 1,
				createDate: $scope.photoBoard.createDate,
				addFiles: addFiles,
				deleteFiles: deleteFiles
			},
		}).success(function() {
			$scope.redirectToPhotoRead();
		}).error(function() {
			alert('파일 데이터 전송 실패');
		})
	};
   /**
    * @function redirectToPhotoRead photo_read.html로 이동하는 함수
    * 
    * @author 박상현
    * @since 2023. 10. 26.
    */
    $scope.redirectToPhotoRead = function() {
        $location.path('/photo/read/' + index);
    }
});
