app.controller("BoardChart", function($scope) {

	$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
	$scope.series = ['Series A'];
	$scope.data = [
		[65, 59, 80, 81, 56, 55, 40]
	];
	$scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
	$scope.options = {
		responsive: false,
		scales: {
			yAxes: [
				{
					id: 'y-axis-1',
					type: 'linear',
					display: true,
					position: 'left'
				},
			]
		}
	};

})