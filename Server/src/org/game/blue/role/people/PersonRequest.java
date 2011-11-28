/**
 * @(#)PersonRequest.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.blue.role.people;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.blue.remote.FlashObject;

import org.game.context.Context;
import org.game.model.Friend;
import org.game.model.Goods;
import org.game.model.Sculpt;
import org.game.model.User;
import org.game.model.UserGoods;
import org.game.tools.HibernateUtil;
import org.hibernate.Session;
/**
 * 用户查看自己基本信息，物品栏，仓库，好友的请求，返回对应的数据
 * @author soda
 */
public class PersonRequest 
{
	private Context context;
	
	public PersonRequest()
	{
		context = Context.getContext();
	}
	
	/**
	 * 根据用户id获取当前用户基本信息
	 * @param userId：当前用户标识
	 * id,sculpt
	 * @return object:一个flash对象，用户的基本信息
	 */
	public FlashObject userInfo(String userName)
	{
		System.out.println("userInfo-> userName:" + userName);
		Session session = HibernateUtil.currentSession();
		List list = session.createQuery("from User as u where u.userName = :userName")
					.setString("userName", userName)
					.list();
		User user = (User)list.get(0);
		Sculpt sculpt = user.getSculpt();
		System.out.println("sculpt:" + sculpt);
		FlashObject object = new FlashObject();
		object.put("id", user.getUserId());
		object.put("name", user.getUserName());
		object.put("money", user.getUserMoney());
		object.put("sculpt", sculpt.getSculptName());
		return object;
	}
	
	/**
	 * 根据用户id获取当前用户物品栏的所有物品
	 * @param userId：当前用户标识
	 */
	public Map goodColumn(String userName)
	{
		System.out.println("userInfo-> userId:" + userName);
		Map map = new HashMap();
		try
		{
			Session session = HibernateUtil.currentSession();
			List list = session.createQuery("from User as u where u.userName = :userName")
						.setString("userName", userName)
						.list();
			User user = (User)list.get(0);
			Set<UserGoods> set = user.getUserGoods();
			
			if(set.size() > 0)
			{
				int i = 0;
				for(Iterator<UserGoods> it = set.iterator(); it.hasNext();i++)
				{
					UserGoods userGoods = it.next();
					Goods goods = userGoods.getGoods();
					Map goodsMap = new HashMap();
					goodsMap.put("goodsName", goods.getGoodsName());
					goodsMap.put("goodsProperty", goods.getGoodsProperty());
					goodsMap.put("goodsImage", goods.getGoodsImage());
					map.put("key" + i, goodsMap);
					System.out.println("goodsName:" + goods.getGoodsName());
				}
				System.out.println("i:" + i);
			}
		}
		catch(Exception e)
		{
			System.out.println("hibernate error:" + e);
		}
		return map;
	}
	
	/**
	 * 根据用户id获取当前用户仓库的所有物品
	 * @param userId：当前用户标识
	 */
	public Map goodDepot(String userName)
	{
		System.out.println("goodDepot-> userId:" + userName);
		Map map = new HashMap();
		
		Map map1 = new HashMap();
		map1.put("goodsName", "drug");
		Map map2 = new HashMap();
		map2.put("goodsName", "gem");
		Map map3 = new HashMap();
		map3.put("goodsName", "diamond");
		Map map4 = new HashMap();
		map4.put("goodsName", "creese");
		map.put("map1", map1);
		map.put("map2", map2);
		map.put("map3", map3);
		map.put("map4", map4);
		return map;
	}
	
	/**
	 * 根据用户id获取当前用户页数的好友
	 * @param userId：当前用户标识
	 * @param page：当前好友页数
	 */
	public Map firends(String userName,double page)
	{
		System.out.println("firends-> userId:" + userName);
		System.out.println("firends-> page:" + page);
		Map map = new HashMap();
		try
		{
			Session session = HibernateUtil.currentSession();
			//包含分页
	        List list = session.createQuery("from Friend as f where f.user.userName = :uesrName")
	                          .setString("uesrName" , userName)
	                          .setFirstResult((int)page - 1)
	                          .setMaxResults(5)
	                          .list();
	       if(list.size() > 0)
	       {
	    	   Map allUser = context.getUserMap();
	    	   int i = 0;
	    	   for(Iterator<Friend> it = list.iterator(); it.hasNext();i++)
	    	   {
	    		   Friend friend = it.next();
	    		   System.out.println("friend:" + friend);
	    		   User userFriend = friend.getFriend();
	    		   Sculpt sculpt = userFriend.getSculpt();
	    		   System.out.println("userFriend:" + userFriend);
	    		   System.out.println("userFriend.getUserName:" + userFriend.getUserName());
	    		   Map friendMap = new HashMap();
	    		   //判断该好友是否在线
	    		   String esate = "离线";
	    		   if(allUser.get(userFriend.getUserName()) != null)
	    		   {
	    			   esate = "在线";
	    		   }
	    		   friendMap.put("friendEsate", esate);
	    		   friendMap.put("friendName", userFriend.getUserName());
	    		   friendMap.put("friendSculpt", sculpt.getSculptName());
	    		   map.put("friend" + i, friendMap);
	    	   }
	       }
		}
		catch(Exception e)
		{
			System.out.println("hibernate error:" + e);
		};
		
		return map;
	}
}

