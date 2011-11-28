package org.game.dao.impl;

import java.util.List;


import org.game.dao.*;
import org.game.model.Friend;
import org.game.model.User;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FriendDaoHibernateImpl extends HibernateDaoSupport implements FriendDao{
	/**
	 * finder firend by userNameIds
	 * @param id userNameId
	 * @return List for friendName
	 */
	public List findFriend(String user)
	{
		List list = (List)getHibernateTemplate().find("from User u where u.userName='"+user+"'");
		//int in = ((User)list.get(0)).getUserId();
		User us = (User)list.get(0);
		//Set set = us.getFriends();
		//System.out.println("ssss"+in);
		//System.out.println("set:" + set);
		
		return (List)getHibernateTemplate().find("from Friend f where f.user=?",us);
		//System.out.print("==+=="+list2);
	
		//return set;
	}
	
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( Friend )getHibernateTemplate().get( Friend.class, id );
	}
	
	/*
	 * save Object 
	 */
	public void save(Object user)
	{
		getHibernateTemplate().save( user );
	}
	
	/*
	 * update Object
	 */
	public void update(Object user)
	{
		getHibernateTemplate().update(user);
		
	}
	
	/*
	 * by id delete Object
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate().delete( this.get(id) );
	}
	
	/*
	 * delete Object
	 */
	public void delete(Object user)
	{
		getHibernateTemplate().delete(user);
	}
	
	/*
	 * @return Object List
	 */
	public List findAll()
	{
		return getHibernateTemplate().find("from Friend");
	}
}
