app.controller("BoardRead", function($scope, $location, BoardFactory, CommentFactory, $routeParams) {

	// 해당 게시글 번호
	let index = $routeParams.index;

	// 조회한 게시글
	$scope.board = {
		title: "",
		text: ""
	};
	// 게시글 추천 수
	$scope.likeCount = 0;

	/**
	 * @function getDataByIndex 게시판 번호에 맞는 데이터를 불러오는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	let getDataByIndex = function() {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		},
			function(error) {
				alert("게시물 데이터 불러오기 실패");
				console.error("게시물 데이터 불러오기 실패", error);
			})
	};

	getDataByIndex();

	/**
	 * @function remove 게시판 번호에 맞는 데이터를 삭제하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	$scope.remove = function() {
		let confirmDelete = confirm("게시물을 삭제하시겠습니까?");

		// 게시물 삭제 시 알림창을 띄우고 확인을 누르면 게시물 삭제
		if (confirmDelete) {
			BoardFactory.deleteBoard({ index: index }, function() {
				$location.path('/board');
			},
				function(error) {
					alert("게시물 삭제 실패");
					console.error("게시물 삭제 실패", error);
				})
		}
	};

	/**
	 * @function redirectToUpdate general_update.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	$scope.redirectToUpdate = function() {
		$location.path('/board/update/' + index);
	};

	/**
	 * @function redirectToBoard general_board.html로 이동하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	$scope.redirectToBoard = function() {
		$location.path('/board');
	}

	/**
	 * @function findComment 댓글과 답글을 조회하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 19.
	 */
	let findComment = function() {
		CommentFactory.getComment({
			boardIndex: index
		},
			function(response) {
				$scope.commentlist = response;
				let commentNewlist = [];
				// 배열을 순회하면서 댓글과 대댓글을 리스트에 추가
				$scope.commentlist.forEach(function(comment) {
					commentNewlist.push(comment);
					comment.childCommentBox = false;
					if (comment.childs !== null) {
						comment.childs.forEach(function(childComment) {
							commentNewlist.push(childComment);
						})
					}
				})

				$scope.commentlist = commentNewlist;
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
	 * @author 박상현
	 * @since 2023. 10. 19.
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
	 * @author 박상현
	 * @since 2023. 10. 19.
	 */
	$scope.insertChildComment = function(comment) {
		comment.childCommentBox = true;
	};

	/**
	 * @function cancelChildComment 취소 버튼을 눌렀을 시 답글 입력 텍스트를 사라지게 하는 함수
	 * 
	 * @param comment 해당 댓글
	 * 
	 * @author 박상현
	 * @since 2023. 10. 19.
	 */
	$scope.cancelChildComment = function(comment) {
		comment.childCommentBox = false;
	}

	/**
	 * @function updateComment 댓글을 수정하는 함수
	 * 
	 * @param comment 해당 댓글
	 * 
	 * @author 박상현
	 * @since 2023. 10. 19.
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
	 * @author 박상현
	 * @since 2023. 10. 19.
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
	* @author 박상현
	* @since 2023. 10. 19.
	*/
	$scope.cancelUpdate = function(comment) {
		comment.update = false;
		comment.updatedText = comment.text;
	};


	/**
	 * @function deleteComment 해당 게시글에 댓글을 삭제하는 함수
	 * 
	 * @author 박상현
	 * @since 2023. 10. 13.
	 */
	$scope.deleteComment = function(commentIndex) {
		let confirmDelete = confirm("댓글을 삭제하시겠습니까?");
		if (confirmDelete) {
			CommentFactory.deleteComment({ index: commentIndex, boardIndex: index }, function() {
				findComment();
			})
		}
	};
	
	/**
	 * @function likeAdd 추천 수가 1씩 증가하는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	$scope.likeAdd = function() {
		$scope.board.likeCount++
		BoardFactory.boardLike({ index: index, likeCount: $scope.board.likeCount }, function() {
			
		})
	};

});
