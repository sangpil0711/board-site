app.controller("BoardChart", function($scope, ChartFactory) {

	ChartFactory.todayVisitor(function(response) {
		$scope.todayVisitor = response.countVisitor;
		$scope.todayPost = response.countPost;
		$scope.visitorData = response.visitorData;
		$scope.postData = response.postData
	});

	$scope.visitorLabels = ["6일전", "5일전", "4일전", "3일전", "2일전", "1일전", "오늘"];
	$scope.visitorSeries = ['방문자 수'];

	$scope.postLabels = ["6일전", "5일전", "4일전", "3일전", "2일전", "1일전", "오늘"];
	$scope.postSeries = ['게시글 수'];

	$scope.options = {
		responsive: false,
		scales: {
			x: {
				beginAtZero: true
			},
			y: {
				beginAtZero: true
			}
		},

		elements: {
			line: {
				tension: 0 // 0은 직선, 1에 가까울수록 곡선
			},
		}

	};

})