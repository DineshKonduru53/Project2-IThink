package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.Dao.ProfilePictureDao;
import com.niit.model.ErrorClazz;
import com.niit.model.ProfilePicture;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setUsername(username);
		profilePicture.setImage(image.getBytes());
		try {
			profilePictureDao.saveOrUpdateProfilePicture(profilePicture);
			return new ResponseEntity<ProfilePicture>(profilePicture, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ErrorClazz error = new ErrorClazz(6, "unable to upload");
			System.out.println(e);
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/getimage/{username}", method = RequestMethod.GET)
	public @ResponseBody byte[] getprofilepic(@PathVariable String username, HttpSession session) {
		String user = (String) session.getAttribute("username");
		if (user == null) {
			return null;
		}
		ProfilePicture profile = profilePictureDao.getProfilePicture(username);
		if (profile == null)
			return null;
		else
			return profile.getImage();
	}

}
