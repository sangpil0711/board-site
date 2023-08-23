var app = angular.module("myApp", ['ngResource']);

		app.factory('BoardFactory', function($resource) {
			return $resource('/board', null, {
				query: {
					method: 'GET',
					isArray: true,
					transformResponse: function(data, headersGetter) {
						var items = angular.fromJson(data);
						console.log(items);
						return items;
					}
				}
			});
		});