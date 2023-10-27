app.controller("BoardUpdate", function($scope, BoardFactory, $location, $routeParams) {

   let index = $routeParams.index;

   $scope.board = {
      title: null,
      text: null
   };

   /**
    * @function getDataByIndex 게시판 번호에 맞는 데이터를 불러오는 함수
    * 
    * @param index 해당 게시판 번호
    * 
    * @author 황상필
    * @since 2023. 09. 18.
    */
   let getDataByIndex = function() {
      BoardFactory.readBoard({ index: index }, function(response) {
         $scope.board = response;
      })
   }

   getDataByIndex();

   /**
    * @function update 게시판 번호에 맞는 데이터를 수정하는 함수
    * 
    * @param index 해당 게시판 번호
    * 
    * @author 황상필
    * @since 2023. 09. 18.
    */
   $scope.update = function(index) {
      BoardFactory.updateBoard({ index: index }, $scope.board, function() {
         $location.path('/board/read/' + index);
      })
   }

   /**
    * @function redirectToRead general_read.html로 이동하는 함수
    * 
    * @author 황상필
    * @since 2023. 09. 18.
    */
   $scope.redirectToRead = function(index) {
      $location.path('/board/read/' + index);
   }
})