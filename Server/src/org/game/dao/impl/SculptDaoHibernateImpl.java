package org.game.dao.impl;

import java.util.List;

import org.game.dao.*;
import org.game.model.Sculpt;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SculptDaoHibernateImpl extends HibernateDaoSupport implements SculptDao {
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( Sculpt )getHibernateTemplate().get( Sculpt.class, id );
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
		return getHibernateTemplate().find("from Sculpt");
	}
}
