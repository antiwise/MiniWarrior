package org.game.dao.impl;

import java.util.List;

import org.game.model.Depot;
import org.game.model.User;
import org.game.dao.*;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DepotDaoHibernateImpl extends HibernateDaoSupport implements DepotDao {
	public List viewDepot(String user)
	{
		List list = (List)getHibernateTemplate().find("from User u where u.userName='"+user+"'");
		//int in = ((User)list.get(0)).getUserId();
		User us = (User)list.get(0);
		return (List)getHibernateTemplate().find("from Depot d where d.user=?",us);
	}
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( Depot )getHibernateTemplate().get( Depot.class, id );
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
		return getHibernateTemplate().find("from Depot");
	}
}
