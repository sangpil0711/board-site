/**
 * $scope, $window 객체와 BoardFactory 서비스를 사용하는 controller 생성
 * general_write.html에 필요한 메서드 작성
 * 
 * 작성일 : 2023.09.15
 * 작성자 : 황상필
 */
app.controller("BoardWrite", function($scope, $location, BoardFactory, FileFactory) {

   let totalSize = 0;

   $scope.fileNames = [];
   $scope.selectedFiles = [];

   $scope.selectFile = function() {
      document.getElementById("fileInput").click();
   }

   $scope.onFileSelect = function($files) {

      let exceedSizeFile = null;

      for (var i = 0; i < $files.length; i++) {
         if ($scope.selectedFiles.some(selectedFile => selectedFile.name === $files[i].name)) {
            alert("파일 '" + $files[i].name + "'이(가) 이미 선택되었습니다.");
         } else if (totalSize + $files[i].size > 100 * 1024 * 1024) {
            exceedSizeFile = $files[i];
            break;
         } else {
            $scope.selectedFiles.push($files[i]);
            $scope.fileNames.push($files[i].name);
            totalSize += $files[i].size;
         }
      }

      if (exceedSizeFile) {
         alert("선택한 파일의 용량이 100MB를 초과합니다.");
      }

   }

   $scope.resetFile = function() {
      $scope.selectedFiles = [];
      $scope.fileNames = [];
      totalSize = 0;
   }

   $scope.insert = function() {
      BoardFactory.createBoard($scope.board, function() {
         $scope.redirectToBoard();
      })
   }

   $scope.redirectToBoard = function() {
      $location.path('/board');
   }
   
   

   $scope.saveFile = function() {
      console.log($scope.selectedFiles);
      FileFactory.uploadFile({fileNames: $scope.fileNames}, $scope.selectedFiles, function() {
         
      })
   }
});