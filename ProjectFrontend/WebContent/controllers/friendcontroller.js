/**
 * FriendController
 */

app.controller('FriendController',function($scope,$location,FriendService){
	
	function listOfSuggestedUsers(){
		FriendService.listOfSuggestedUsers().then(function(response){
			$scope.suggestuser=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
	
	function listOfPendingRequests(){
		FriendService.listOfPendingRequests().then(function(response){
			$scope.pendingRequest=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)	
		})
		
	}
	
	
	
	$scope.friendRequest=function(toid){
		FriendService.friendRequest(toid).then(function(response){
			alert("Request sent")
			listOfSuggestedUsers();
		$location.path('/suggesteduserslist')
	},function(response){
		if(response.status==401)
			$location.path('/login')
			console.log(response.status)
	})
	
	}
	
	$scope.updateRequest=function(pendingRequest,status){
		/*pendingRequest.status='P'
		 * pendingRequest.status->assign A/D
		 */
		console.log(pendingRequest.status)
		pendingRequest.status=status
		/*pendingRequest is an object of type Friend
		 * id,fromId,toId and status(A/D)
		 */
		FriendService.updateRequest(pendingRequest).then(function(response){
			if(status='A'){
				alert('accepted request')
				$location.path('/pendingfriendrequests')
			}else{
				alert('denied request')
				$location.path('/suggesteduserslist')
			}
				
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
		
	}
	
	function listOfFriends(){
		FriendService.listOfFriends().then(function(response){
			$scope.friends=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)	
		})
		
	}
	
	
	
	
	
	listOfSuggestedUsers()
	listOfPendingRequests()
	listOfFriends()
})