/**
 * angular module을 생성하여 app에 할당
 * 
 * $routeProvider 함수를 사용하는 config 생성
 * 각 경로에 따른 라우팅 설정
 * 
 * $resource 함수를 사용하는 factory	생성
 * "/boards/:index" 경로에 따른 'GET', 'POST', 'PATCH', 'DELETE' 메소드 생성
 * 
 * @author 황상필
 * @since 2023. 09. 01.
 */
var app = angular.module("myApp", ['ngResource', 'ngRoute', 'ui.bootstrap', 'ngFileUpload', 'dndLists']);

app.config(function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "static/templates/main_display.html"
		})
		.when("/board", {
			templateUrl: "static/templates/general_board.html",
		})
		.when("/board/write", {
			templateUrl: "static/templates/general_write.html",
		})
		.when("/board/read/:index", {
			templateUrl: "static/templates/general_read.html",
		})
		.when("/board/update/:index", {
			templateUrl: "static/templates/general_update.html"
		})
		.when("/photo", {
			templateUrl: "static/templates/photo_board.html"
		})
		.when("/photo/write", {
			templateUrl: "static/templates/photo_write.html"
		})
		.when("/photo/read/:index", {
			templateUrl: "static/templates/photo_read.html"
		})
		.when("/photo/update/:index", {
			templateUrl: "static/templates/photo_update.html"
		})
		.when("/file", {
			templateUrl: "static/templates/file_explorer.html"
		});
});

/**
 * @Method : 메인화면 게시글 리스트를 조회하는 함수
 * 
 * @author 황상필
 * @since 2023. 11. 06.
 */
app.factory('MainFactory', function($resource) {
	return $resource('/boards/best', null, {

		bestBoard: {
			method: 'GET',
			isArray: true,
			headers: {
				"Content-Type": 'application/json'
			},
		}

	})
});

/**
 * @Method : 파일탐색기 동작을 수행하는 함수
 * 
 * @author 황상필
 * @since 2023. 11. 15.
 */
app.factory('ExplorerFactory', function($resource) {
	return $resource('/fileExplorer/:name', null, {

		exploreFile: {
			method: 'GET',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		downloadFile: {
			method: 'GET',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		deleteFile: {
			method: 'DELETE',
			headers: {
				"Content-Type": 'application/json'
			},
			transformResponse: 
			function (data) {
                return data;
            }
		},

		createFolder: {
			method: 'POST',
			url: '/fileExplorer/directory',
			headers: {
				"Content-Type": 'application/json'
			},
			transformResponse: 
			function (data) {
                return data;
            }
		},

		updateFile: {
			method: 'PATCH',
			headers: {
				"Content-Type": 'application/json'
			},
			transformResponse: 
			function (data) {
                return data;
            }
		},
		
		moveFile: {
			method: 'PATCH',
			url: '/fileExplorer/move',
			headers: {
				"Content-Type": 'application/json'
			},
			transformResponse: 
			function (data) {
                return data;
            }
		}

	})
});

/**
 * @Method : 게시글을 저장, 조회, 수정, 삭제하는 함수
 * 
 * @author 황상필
 * @since 2023. 09. 11.
 */
app.factory('BoardFactory', function($resource) {
	return $resource('/boards/:index/:likeCount', { index: '@index', likeCount: '@likeCount' }, {

		readBoard: {
			method: 'GET',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		deleteBoard: {
			method: 'DELETE',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		boardLike: {
			method: 'PATCH',
			headers: {
				"Content-Type": 'application/json'
			},
		},

	})
});

/**
 * @Method : 해당 게시글에 댓글을 추가,수정,삭제,조회 하는 함수
 * 
 * @author 박상현
 * @since 2023. 10. 11.
 */
app.factory('CommentFactory', function($resource) {
	return $resource('/boards/:boardIndex/comments/:index', null, {

		getComment: {
			method: 'GET',
			isArray: true,
			headers: {
				"Content-Type": 'application/json',
			},
		},

		insertComment: {
			method: 'POST',
			headers: {
				"Content-Type": 'application/json',
			},
		},

		updateComment: {
			method: 'PATCH',
			headers: {
				"Content-Type": 'application/json',
			},
		},

		deleteComment: {
			method: 'DELETE',
			headers: {
				"Content-Type": 'application/json',
			},
		},

	});
});

/**
 * @Method : 사진 게시물에 게시물을 추가,수정,삭제,조회 하는 함수
 * 
 * @author 박상현
 * @since 2023. 10. 24.
 */
app.factory('PhotoBoardFactory', function($resource) {
	return $resource('/photos/:index/:likeCount', { index: '@index', likeCount: '@likeCount' }, {

		readPhotoBoards: {
			method: 'GET',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		readPhotoBoard: {
			method: 'GET',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		createPhotoBoard: {
			method: 'POST',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		updatePhotoBoard: {
			method: 'PATCH',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		deletePhotoBoard: {
			method: 'DELETE',
			headers: {
				"Content-Type": 'application/json'
			},
		},

	})
})



