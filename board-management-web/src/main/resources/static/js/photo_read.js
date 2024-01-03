app.controller("PhotoRead", function($scope, $location, $routeParams, PhotoBoardFactory, CommentFactory, BoardFactory, $http) {


	// 라우팅으로 받아오는 게시글 번호
	let index = $routeParams.index;

	$scope.photoBoard = [];
	// 사용자 게시글 추천 유무
	$scope.userLike = false;

	// 현재 로그인한 아이디
	$http.get('/loginId')
		.then(function(response) {
			$scope.loginId = response.data;
		})
		.catch(function(error) {
			console.error('현재 로그인된 아이디를 가져올 수 없습니다.', error);
		});

	/**
	 * @function searchByPhotoIndex 게시판 번호에 맞는 데이터를 불러오는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	let searchByPhotoIndex = function() {
		PhotoBoardFactory.readPhotoBoard({ index: index }, function(response) {
			$scope.photoBoard = response;
			if ($scope.photoBoard.userLike == 1) {
				$scope.userLike = true;
			} else {
				$scope.userLike = false;
			}
			$scope.thumbnail = function(file) {
				if (file !== undefined && file.fileSize > 0) {
					return '/files/' + file.fileId + '?fileName=' + file.fileName;
				}
			}
		},
			function(error) {
				alert("게시물 데이터 불러오기 실패");
				console.error("게시물 데이터 불러오기 실패", error);
			})
	};
	searchByPhotoIndex();



	/**
	 * @function redirectToPhotoUpdate photo_update.html로 이동하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	$scope.redirectToPhotoUpdate = function() {
		$location.path('/photo/update/' + index);
	}

	/**
	 * @function redirectToPhotoBoard photo_board.html로 이동하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	$scope.redirectToPhotoBoard = function() {
		$location.path('/photo');
	}

	/**
	 * @function remove 게시판 번호에 맞는 데이터를 삭제하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 박상현
	 * @since 2023. 10. 26.
	 */
	$scope.removePhoto = function() {
		if (confirm("게시물을 삭제하시겠습니까?")) {
			PhotoBoardFactory.deletePhotoBoard({ index: index }, function() {
				$location.path('/photo');
			},
				function(error) {
					alert("게시물 추가 실패!");
					console.error("게시물 추가 실패", error);

				}
			);
		} else {
			alert("삭제 실패!");
		}
	}

	$scope.commentlist = [];
	let findComment = function() {
		CommentFactory.getComment(
			{
				boardIndex: index
			},
			function(response) {
				$scope.commentlist = response;
			},
			function(error) {
				console.error("댓글 정보 불러오기 실패", error);

			});
	};
	findComment();

	/**
	 * @function insertComment 댓글,답글을 입력하는 함수
	 * 
	 * @param parentIndex 부모댓글
	 * @param newChildComment 답글
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.insertComment = function(parentIndex, newChildComment) {
		// 게시글에 생성되는 댓글 데이터
		let newComment = {
			index: null,
			boardIndex: index,
			text: parentIndex === null ? $scope.newComment : newChildComment,
			parentIndex: parentIndex,
			userId: null,
			createDate: null
		}

		CommentFactory.insertComment({ boardIndex: index }, newComment, function() {
			findComment();
			$scope.newComment = "";

		}, function(error) {
			console.error("댓글 등록 실패", error);
		});
	};

	/**
	 * @function insertChildComment 답글 달기 버튼을 누르면 답글 입력 텍스트가 뜨게하는 함수
	 * 
	 * @param comment 해당 댓글
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.insertChildComment = function(comment) {
		comment.childCommentBox = true;
	};

	/**
	 * @function cancelChildComment 취소 버튼을 눌렀을 시 답글 입력 텍스트를 사라지게 하는 함수
	 * 
	 * @param comment 해당 댓글
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.cancelChildComment = function(comment) {
		comment.childCommentBox = false;
	}

	/**
	 * @function updateComment 댓글을 수정하는 함수
	 * 
	 * @param comment 해당 댓글
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.updateComment = function(comment) {
		comment.update = true;
		comment.updatedText = comment.text;
	};

	/**
	 * @function saveComment 댓글 내용을 수정하면 저장기능을 하는 함수
	 * 
	 * @param comment 해당 댓글
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.saveComment = function(comment) {
		comment.text = comment.updatedText;
		comment.update = false;
		CommentFactory.updateComment({ index: index, boardIndex: index }, comment, function() {

		}, function(error) {
			console.error("댓글 수정 실패", error);
		});
	};

	/**
	* @function cancelUpdate 댓글내용을 수정하는 도중 취소기능을 가지는 함수
	* 
	* @param comment 해당 댓글
	* 
	 * @author 황상필
	 * @since 2023. 11. 03.
	*/
	$scope.cancelUpdate = function(comment) {
		comment.update = false;
		comment.updatedText = comment.text;
	};


	/**
	 * @function deleteComment 해당 게시글에 댓글을 삭제하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.deleteComment = function(commentIndex) {
		if (confirm("댓글을 삭제하시겠습니까?")) {
			CommentFactory.deleteComment({ index: commentIndex, boardIndex: index }, function() {
				findComment();
			})
		}
	};

	/**
	 * @function updateLike 게시글 추천 수를 업데이트하는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 03.
	 */
	$scope.updateLike = function() {
		BoardFactory.boardLike({ index: index }, function() {
			searchByPhotoIndex();
		},
			function(error) {
				alert("게시글 추천 오류");
				console.error("게시글 추천 오류", error);
			})
	};

});
