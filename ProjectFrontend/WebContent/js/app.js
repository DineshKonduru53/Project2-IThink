/**
 * Angular Js Module
 */
var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/register',{
		templateUrl:'views/registrationform.html',
		controller:'UserController'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'UserController'
	})
	
	.when('/editprofile',{
		templateUrl:'views/updateprofile.html',
		controller:'UserController'
	})
	
	.when('/savejob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
	})
	.when('/getalljobs',{
		templateUrl:'views/jobtitles.html',
		controller:'JobController'
	})
	
	
	.when('/addblog',{
		templateUrl:'views/blogform.html', //V to Controller
		controller:'BlogController'
	})
	.when('/getblogs',{
		templateUrl:'views/blogslist.html',//Controller to V
		controller:'BlogController'
	})
	.when('/admin/getblog/:id',{
		templateUrl:'views/approvalform.html',
		controller:'BlogPostDetailsController'
	})
	.when('/getblog/:id',{
		templateUrl:'views/blogdetails.html',
		controller:'BlogPostDetailsController'
	})
	.when('/profile',{
		templateUrl: 'views/profilepicture.html'
	})
	.when('/suggesteduserslist',{
		templateUrl:'views/suggestedusers.html',
		controller:'FriendController'
	})
	.when('/pendingfriendrequests',{
		templateUrl:'views/pendingrequest.html',
		controller:'FriendController'
	})
	.when('/getUserDetails/:fromid',{
		templateUrl:'views/userdetails.html',
		controller:'FriendDetailController'
	})
	.when('/friendslist',{
		templateUrl:'views/listoffriends.html',
		controller:'FriendController'
	})
	
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller: 'ChatController'
	})
	
	.otherwise({templateUrl:'views/home.html'})
})
app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
		
	$rootScope.logout=function(){
		/*
		 * Call middleware logout url -> Middleware - remove HttpSession attribute,update online status to false
		 * on success - in frontend , remove cookieStore attribute currentUser, delete $rootScope.
		 */
		UserService.logout().then(function(response){
			delete $rootScope.currentUser;
			$cookieStore.remove('currentUser')
			$location.path('/login')
			
		},function(response){
			console.log(response.status)
			$location.path('/login')
		})
	}
})
