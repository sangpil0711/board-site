app.controller("BoardChart", function($scope, ChartFactory) {

	// 현재 페이지
	$scope.currentPage = 1;
	// 페이지당 유저 수	
	$scope.itemsPerPage = 5;
	// 화면에 표시되는 최대 페이지 수
	$scope.maxSize = 5;
	// 일주일 날짜
	let labels = [];
	// 차트 가로축 날짜
	$scope.chartDate = labels;
	// 오늘 날짜
	let date = new Date();
	// 방문자 수 차트 
	$scope.visitorSeries = ['방문자 수'];
	// 게시글 수 차트
	$scope.postSeries = ['게시글 수'];

	// 일주일 동안의 날짜를 labels 배열에 추가
	for (var i = 6; i >= 0; i--) {
		date.setDate(new Date().getDate() - i);
		labels.push(date.toLocaleDateString('en-CA'));
	};

	/**
	 * @function chartData 차트에 필요한 데이터를 가져오는 함수
	 * 
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	ChartFactory.chartData(function(response) {
		$scope.todayVisitor = response.countVisitor;
		$scope.todayPost = response.countPost;
		$scope.visitorData = [response.visitorData];
		$scope.postData = [response.postData];
	});

	// 차트 설정
	$scope.options = {
		responsive: false,
		scales: {
			xAxes: [{
				beginAtZero: true,
				ticks: {
					fontSize: 12
				}
			}],
			yAxes: [{
				beginAtZero: true,
				ticks: {
					stepSize: 1,
					fontSize: 13
				}
			}]
		},

		elements: {
			line: {
				tension: 0 // 0은 직선, 1에 가까울수록 곡선
			}
		},

		legend: {
			display: true,
			position: 'right',
			labels: {
				fontSize: 16
			}
		},

		onClick: function(event, elements) {
			if (elements.length > 0) {
				let index = elements[0]._index;
				let dateParts = $scope.chartDate[index].split("-");
				let date = dateParts[0] + dateParts[1] + dateParts[2];

				// 방문자 수 차트를 클릭했을 때 동작
				if (event.currentTarget.classList.contains('visitor_chart')) {
					ChartFactory.visitUserData({ visitDate: date }, function(response) {
						$scope.userList = response.userList;
						for (let i = 0; i < $scope.userList.length; i++) {
							let user = $scope.userList[i];

							if (user.gradeId == 0) {
								user.gradeId = "관리자";
							} else {
								user.gradeId = "사용자";
							}
						}
						$scope.visitorChart = true;
						$scope.postChart = false;
					})
				}
				// 게시글 수 차트를 클릭했을 때 동작
				else if (event.currentTarget.classList.contains('post_chart')) {
					ChartFactory.postUserData({ postDate: date }, function(response) {
						$scope.userList = response.userList;
						for (let i = 0; i < $scope.userList.length; i++) {
							let user = $scope.userList[i];

							if (user.gradeId == 0) {
								user.gradeId = "관리자";
							} else {
								user.gradeId = "사용자";
							}
						}
						$scope.postChart = true;
						$scope.visitorChart = false;
					})
				}
			}
		}

	};

	// 방문자 수 차트 설정
	$scope.visitorDataset = [
		{
			fill: false,
			borderColor: 'green'
		}
	];

	// 게시글 수 차트 설정
	$scope.postDataset = [
		{
			fill: false,
			borderColor: 'orange'
		}
	];

})