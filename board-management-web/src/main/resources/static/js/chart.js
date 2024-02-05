app.controller("BoardChart", function($scope, ChartFactory) {

	// 현재 페이지
	$scope.currentPage = 1;
	// 페이지당 유저 수	
	$scope.itemsPerPage = 5;
	// 화면에 표시되는 최대 페이지 수
	$scope.maxSize = 5;
	// 일주일 날짜
	let labels = [];
	// 방문자 수 차트
	const visitorChart = document.getElementById('visitorChart');
	// 게시글 수 차트
	const postChart = document.getElementById('postChart');

	// 일주일 동안의 날짜를 labels 배열에 추가
	for (var i = 6; i >= 0; i--) {
		let date = new Date();
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

		// 방문자 수 차트 설정
		new Chart(visitorChart, {
			type: 'line',
			data: {
				labels: labels,
				datasets: [{
					label: '방문자 수',
					data: response.visitorData,
					borderColor: 'green'
				}]
			},
			options: {
				responsive: false,
				scales: {
					y: {
						beginAtZero: true,
						ticks: {
							stepSize: 1
						}
					}
				},

				interaction: {
					intersect: false,
					mode: 'index',
				},

				plugins: {
					tooltip: {
						position: 'nearest'
					},
					legend: {
						display: true,
						position: 'right',
						labels: {
							fontSize: 16
						}
					},
				},

				onHover: function(event, elements) {

					if (elements.length > 0) {
						let index = elements[0].index;
						let dateParts = labels[index].split("-");
						let date = dateParts[0] + dateParts[1] + dateParts[2];
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
				}
			}
		});

		// 게시글 수 차트 설정
		new Chart(postChart, {
			type: 'line',
			data: {
				labels: labels,
				datasets: [{
					label: '게시글 수',
					data: response.postData,
					borderColor: 'orange'
				}]
			},
			options: {
				responsive: false,
				scales: {
					y: {
						beginAtZero: true,
						ticks: {
							stepSize: 1
						}
					}
				},

				interaction: {
					intersect: false,
					mode: 'index',
				},

				plugins: {
					tooltip: {
						position: 'nearest'
					},
					legend: {
						display: true,
						position: 'right',
						labels: {
							fontSize: 16
						}
					},
				},

				onHover: function(event, elements) {

					if (elements.length > 0) {
						let index = elements[0].index;
						let dateParts = labels[index].split("-");
						let date = dateParts[0] + dateParts[1] + dateParts[2];

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
		});
	});
})