package com.niit.Dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {
	void saveOrUpdateProfilePicture(ProfilePicture profilePicture);
	
	ProfilePicture getProfilePicture(String username);

}
