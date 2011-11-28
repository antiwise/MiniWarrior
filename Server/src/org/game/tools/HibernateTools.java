/**
 * @(#)HibernateTools.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-5-4
 */
package org.game.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTools 
{
	private static HibernateTools tools;
	private Session session;
	private Transaction tx;
	
	private HibernateTools()
	{
		Configuration conf = new Configuration().configure();
        SessionFactory sf = conf.buildSessionFactory();
        session = sf.openSession();
	}
	
	/**
	 * 获取HibernateTools实例
	 * @return
	 */
	public static HibernateTools getHibernateTools()
	{
		if(tools == null)
		{
			tools = new HibernateTools();
		}
		return tools;
	}
	
	/**
	 * 获取一个seesion
	 * @return
	 */
	public Session getSession()
	{
		tx = session.beginTransaction();
		return session;
	}
	
	/**
	 * 提交事务
	 */
	public void commit()
	{
		tx.commit();
	}
}
