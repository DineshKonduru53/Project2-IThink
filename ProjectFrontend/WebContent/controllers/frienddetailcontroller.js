/**
 * FriendDetailController
 */

app.controller('FriendDetailController',function($scope,$location,FriendService,$routeParams){
	
	var fromid=$routeParams.fromid

	
		FriendService.getUserDetails(fromid).then(function(response){
			$scope.user=response.data;
		
		},function(response){
			console.log(response.status)
			if(response.status==401)
				
				$location.path('/login')
				console.log(response.status)
			})
	})