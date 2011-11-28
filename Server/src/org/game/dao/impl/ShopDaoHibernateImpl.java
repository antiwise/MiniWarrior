package org.game.dao.impl;

import java.util.List;

import org.game.dao.ShopDao;
import org.game.model.Shop;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ShopDaoHibernateImpl extends HibernateDaoSupport implements ShopDao {
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( Shop )getHibernateTemplate().get( Shop.class, id );
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
		return getHibernateTemplate().find("from Shop");
	}
}
