/**
 * FriendService
 */

app.factory('FriendService',function($http){
	var friendService={}
	
	
	friendService.listOfSuggestedUsers=function(){
		return $http.get("http://localhost:8080/ProjectMiddleware/suggestedusers")
	}
	friendService.friendRequest=function(toid){
		return $http.post("http://localhost:8080/ProjectMiddleware/friendRequest"+toid)
	}
	friendService.listOfPendingRequests=function(){
		return $http.get("http://localhost:8080/ProjectMiddleware/pendingrequest")
	}
	friendService.getUserDetails=function(fromid){
		return $http.get("http://localhost:8080/ProjectMiddleware/getuserdetails"+fromid)
	}
	friendService.updateRequest=function(pendingRequest){
		return $http.put("http://localhost:8080/ProjectMiddleware/updatependingrequest",pendingRequest)
	}
	friendService.listOfFriends=function(){
		return $http.get("http://localhost:8080/ProjectMiddleware/friendslist")
	}
	return friendService;
	
})
