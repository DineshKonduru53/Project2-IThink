/**
 * HomeService
 */

app.factory('HomeService',function($http){
	var homeService={}
	var BASE_URL="http://localhost:8080/ProjectMiddleware"
		homeService.getNotificationNotViewed=function(){
		//select * from notification where username=? and viewed=0
		return $http.get(BASE_URL+"/getnotification/"+0)
	}
	homeService.getNotificationViewed=function(){
		//select * from notification where username=? and viewed=0
		return $http.get(BASE_URL+"/getnotification/"+1)
	}
	return homeService;
})