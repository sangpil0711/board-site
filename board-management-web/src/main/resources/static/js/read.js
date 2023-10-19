app.controller("BoardRead", function($scope, $location, BoardFactory, $routeParams) {

	let index = $routeParams.index;

	/**
	 * @function getDataByIndex 게시판 번호에 맞는 데이터를 불러오는 함수
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	let getDataByIndex = function() {
		BoardFactory.readBoard({ index: index }, function(response) {
			$scope.board = response;
		})
	}

	getDataByIndex();

	/**
	 * @function remove 게시판 번호에 맞는 데이터를 삭제하는 함수
	 * 
	 * @param index 해당 게시판 번호
	 * 
	 * @author 황상필
	 * @since 2023. 09. 15.
	 */
	 $scope.remove = function(index) {
        let confirmDelete = confirm("게시물을 삭제하시겠습니까?");
        if (confirmDelete) {
            BoardFactory.deleteBoard({ index: index }, function() {
                $location.path('/board');
            })
        }
    }

    $scope.redirectToUpdate = function(index) {
        $window.location.href = '#!/board/update/' + index;
    }

    $scope.redirectToBoard = function() {
        $window.location.href = '#!/board';
    }

    /**
     * Method: 해당 게시글과 관련된 댓글 목록을 가져오는 함수
     * author: 박상현
     * since: 2023.10.11
     */
    let findComment = function() {
        CommentFactory.getComment({
            boardIndex: index
        },
        function(response) {
            $scope.commentlist = response;

            let commentNewlist = [];

            $scope.commentlist.forEach(function(comment) {
                commentNewlist.push(comment);
                comment.childCommentBox = false;
                if (comment.childs !== null) {
                    comment.childs.forEach(function(childComment) {
                        commentNewlist.push(childComment);
                    });
                }
            });

            $scope.commentlist = commentNewlist;
        },
        function(res) {
            console.error("error: ", res);
        });
    }
    findComment();

    /**
     * Method: 해당 게시글에 댓글,답글을 등록하는 함수
     * author: 박상현
     * since: 2023.10.12
     */
    $scope.insertComment = function(parentIndex, newChildComment) {
        let newComment = {
            index: null,
            boardIndex: index,
            text: parentIndex === null ? $scope.newComment : newChildComment,
            parentIndex: parentIndex,
            userId: null,
            createDate: null
        }

        $scope.newComment;
        CommentFactory.insertComment({ boardIndex: index }, newComment, function(response) {
            findComment();
            $scope.newComment = "";
        });
    };

    $scope.insertChildComment = function(comment) {
        comment.childCommentBox = true;
    };

    $scope.cancelChildComment = function(comment) {
        comment.childCommentBox = false;
    }

    /**
     * Method: 해당 게시글에 댓글을 수정 하는 함수
     * author: 박상현
     * since: 2023.10.18
     */	
    $scope.updateComment = function (comment) {
        comment.update = true;
        comment.updatedText = comment.text; 
    };

    $scope.saveComment = function (comment) {
        comment.text = comment.updatedText; 
        comment.update = false;
     };
     
    $scope.cancelUpdate = function (comment) {
        comment.update = false; 
    };

    /**
     * Method: 해당 게시글에 댓글을 삭제하는 함수
     * author: 박상현
     * since: 2023.10.13
     */
    $scope.deleteComment = function(commentIndex) {
        let confirmDelete = confirm("댓글을 삭제하시겠습니까?");
        if (confirmDelete) {
            CommentFactory.deleteComment({ index: commentIndex, boardIndex: index }, function() {
                findComment();
            });
        }
    };
});
