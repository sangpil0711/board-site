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
var app = angular.module("myApp", ['ngResource', 'ngRoute', 'ui.bootstrap', 'ngFileUpload']);

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
		});
});

app.factory('BoardFactory', function($resource) {
	return $resource('/boards/:index', null, {

		readBoard: {
			method: 'GET',
			headers: {
				"Content-Type": 'application/json'
			},
		},

		createBoard: {
			method: 'POST',
			headers: {
				"Content-Type": 'application/json'
			},
		},
			
		updateBoard: {
			method: 'PATCH',
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

	})
})

app.factory('FileFactory', function($resource) {
	return $resource('/files/:fileName', null, {

		downloadFile: {
			method: 'GET'
		},

		uploadFile: {
			method: 'POST',
			transformRequest: angular.identity,
			headers: {
                "Content-Type": undefined
            },
		},

	})
})


	/**
	 * Method : 해당 게시글에 댓글을 추가,수정,삭제,조회 하는 함수
	 * 
	 * @author 박상현
	 * @since 2023.10.11
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


