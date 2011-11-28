package org.game.dao.impl;

import java.util.List;

import org.game.dao.GoodsDao;
import org.game.model.Goods;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GoodsDaoHibernateImpl extends HibernateDaoSupport implements GoodsDao{
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( Goods )getHibernateTemplate().get( Goods.class, id );
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
		return getHibernateTemplate().find("from Goods");
	}
}
