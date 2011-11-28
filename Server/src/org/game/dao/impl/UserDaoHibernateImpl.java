package org.game.dao.impl;

import java.util.List;


import org.game.dao.*;
import org.game.model.User;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class UserDaoHibernateImpl  extends HibernateDaoSupport implements UserDao{

	/**
	 * ??????????
	 * @param name ???
	 * @param pass ??
	 * @return ???List
	 */
	
	public List findByNameAndPass(String name , String pass)
	{
		String[] args = {name , pass};
		
		return (List) getHibernateTemplate().find("from User u where u.userName = ? and u.userPass = ?" , args);
	}
	
	/**
	 * ???????
	 * @return ???????
	 */
	
	public List findAllUser()
	{
		return (List)getHibernateTemplate().find("from User u where u.userLevel = 1");
	}
	/**
	 * ???????????
	 * @param user ???
	 */
	public User findByName(String user)
	{
		List list = (List)getHibernateTemplate().find("from User u where u.userName ='"+user+"'");
		return (User)list.get(0);
	}


	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( User )getHibernateTemplate().get( User.class, id );
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
		return getHibernateTemplate().find("from User");
	}
}
