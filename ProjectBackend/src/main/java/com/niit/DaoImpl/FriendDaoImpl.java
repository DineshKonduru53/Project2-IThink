package com.niit.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.FriendDao;
import com.niit.model.Friend;
import com.niit.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getListOfSuggestedUsers(String username) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from DUser where username in (select username from DUser where username!=? minus (select fromid from Friend where toid=? and status!='D' union select toid from Friend where fromid=? and status!='D'))";
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> user=query.list();
		
		return user;
	}

	public void addFriendRequest(String username, String toid) {
		Session session=sessionFactory.getCurrentSession();
		Friend friend=new Friend();
		friend.setFromid(username);
		friend.setToid(toid);
		friend.setStatus('P');
		session.save(friend);
		
	}

	public List<Friend> getPendingRequests(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toid=? and status=?");
		query.setString(0, username);
		query.setCharacter(1, 'P');
		List<Friend> pendingRequest=query.list();
		return pendingRequest;
	}

	public void updatePendingRequest(Friend pendingRequest) {
		Session session=sessionFactory.getCurrentSession();
		if(pendingRequest.getStatus()=='D'){
			session.delete(pendingRequest);
		}else
		{
			session.update(pendingRequest);
		}	
	}

	public List<Friend> listOfFriends(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query  query=session.createQuery("from Friend where (fromid=? or toid=?) and status=?");
		query.setString(0, username);
		query.setString(1, username);
		query.setCharacter(2, 'A');
		return query.list();
	}
	

}
