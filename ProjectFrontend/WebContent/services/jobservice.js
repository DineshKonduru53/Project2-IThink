/**
 * JobService
 */

app.factory('JobService',function($http){
	var jobService={}
	
	jobService.saveJob=function(job){
	  return  $http.post("http://localhost:8080/ProjectMiddleware/savejob",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("http://localhost:8080/ProjectMiddleware/getalljobs")
	}
	
	jobService.getJobDetails=function(id){
		return $http.get("http://localhost:8080/ProjectMiddleware/getjobbyid/"+id)
	}
	jobService.deleteJob=function(id){
		return $http.get("http://localhost:8080/ProjectMiddleware/deletejob/"+id)
	}
	
	return jobService;
})