package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.FriendDao;
import com.niit.Dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Friend;
import com.niit.model.User;

@Controller
public class FriendController {
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<User> suggestuser=friendDao.getListOfSuggestedUsers(username);
		return new ResponseEntity<List<User>>(suggestuser,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/friendRequest{toid}",method=RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@PathVariable String toid,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		friendDao.addFriendRequest(username, toid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/pendingrequest",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequest(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> pendingRequest=friendDao.getPendingRequests(username);
		return new ResponseEntity<List<Friend>>(pendingRequest,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getuserdetails{fromid}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(@PathVariable String fromid,HttpSession session){
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUserByUsername(fromid);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="updatependingrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody Friend pendingRequest,HttpSession session){
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		friendDao.updatePendingRequest(pendingRequest);
		return new ResponseEntity<Friend>(pendingRequest,HttpStatus.OK);
	}
	@RequestMapping(value="/friendslist",method=RequestMethod.GET)
	public ResponseEntity<?> friendList(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> friends=friendDao.listOfFriends(username);
		return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	}
	

}
