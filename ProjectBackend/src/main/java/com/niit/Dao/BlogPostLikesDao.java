package com.niit.Dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;
import com.niit.model.User;

public interface BlogPostLikesDao {
	//select * from blogpostlikes where blogpost_id=? and user_username=?
		//if user already liked the post, 1 object
		//if user has not yet liked the post, null object
	BlogPostLikes userLikes(BlogPost blogPost,User user);

	//increment / decrement the number of likes
	//insert into blogpostlikes / delete from blogpostlikes 
	BlogPost updateLikes(BlogPost blogPost, User user);

}
