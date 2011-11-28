package org.game.dao.impl;

import java.util.List;

import org.game.dao.ShopGoodsDao;
import org.game.model.ShopGoods;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ShopGoodsDaoHibernateImpl extends HibernateDaoSupport implements ShopGoodsDao {
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( ShopGoods )getHibernateTemplate().get( ShopGoods.class, id );
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
		return getHibernateTemplate().find("from ShopGoods");
	}
}
