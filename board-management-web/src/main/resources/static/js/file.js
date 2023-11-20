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
      })
   };

   fileExplorer(null, null, 0);

   $scope.folderEvent = function(index, path, name, depth) {
      $scope.folderOpen[index] = !$scope.folderOpen[index];
      if ($scope.folderOpen[index]) {
         fileExplorer(path, name, depth + 1);
      } else {
         folderClose(depth, path);
      }
   };

   let folderClose = function(depth, path) {
      $scope.files = $scope.files.filter(function(file) {
         return file.depth <= depth && file.path.includes(path);
      })
   };

});