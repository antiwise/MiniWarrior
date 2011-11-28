package org.game.dao.impl;

import java.util.List;

import org.game.dao.GoodsItemDao;
import org.game.model.GoodsItem;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GoodsItemDaoHibernateImpl extends HibernateDaoSupport implements GoodsItemDao{
	
	public GoodsItem findByName(String name )
	{
		System.out.println(name+"==");
		List list = (List)getHibernateTemplate().find("from GoodsItem g where g.goodsItemName ='"+name+"'");
		return (GoodsItem)list.get(0);
	}
	
	public void jdbcDelete(Integer id)
	{		
	}
	/*
	 * get Object
	 */
	public Object get(Integer id)
	{
		return ( GoodsItem )getHibernateTemplate().get( GoodsItem.class, id );
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
		getHibernateTemplate().delete( getHibernateTemplate().get( GoodsItem.class, id ) );
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
		return getHibernateTemplate().find("from GoodsItem");
	}
	
}
