/**
 * @(#)NpcEvents.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-5-9
 */
package org.game.blue.role.people;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.blue.remote.FlashObject;
import org.game.context.Context;
import org.game.model.Goods;
import org.game.model.GoodsItem;
import org.game.model.UserGoods;
import org.game.tools.HibernateUtil;
import org.hibernate.Session;

/**
 * npc发生的事件交互
 * @author soda
 *
 */
public class NpcEvents 
{
	private Context context;
	
	public void NpcEvents()
	{
		context = Context.getContext();
	}
	
	/**
	 * 根据物品类型名称显示物品
	 * @param shopType:物品类型名称
	 * @return 一个包含该类型的所有物品集合
	 */
	public Map shopGoods(String shopType)
	{
		Map map = new HashMap();
		try
		{
			List list = null;
			Session session = HibernateUtil.currentSession();
			if(shopType.equals("default"))
			{
				list = session.createQuery("from Goods").list();
				int i = 0;
				for(Iterator<Goods> it = list.iterator(); it.hasNext();i++)
				{
					Goods goods = it.next();
					setGoods(map,goods,i);
					System.out.println("goodsName:" + goods.getGoodsName());
				}
				System.out.println("default shopGoods -> list:" + list);
			}
			else
			{
				list = session.createQuery("from GoodsItem as g where g.goodsItemName = :goodsItemName")
							.setString("goodsItemName", shopType)
							.list();
				System.out.println("shopGoods -> list:" + list);
				Set<Goods> set = ((GoodsItem)list.get(0)).getGoods();
				if(set.size() > 0)
				{
					int i = 0;
					for(Iterator<Goods> it = set.iterator(); it.hasNext();i++)
					{
						Goods goods = it.next();
						setGoods(map,goods,i);
						System.out.println("goodsName:" + goods.getGoodsName());
					}
					System.out.println("i:" + i);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("hibernate error:" + e);
		}
		return map;
	}
	private void setGoods(Map map,Goods goods,int i)
	{
		Map goodsMap = new HashMap();
		goodsMap.put("goodsName", goods.getGoodsName());
		goodsMap.put("goodsProperty", goods.getGoodsProperty());
		goodsMap.put("goodsPrice", goods.getGoodsValue());
		goodsMap.put("goodsImage", goods.getGoodsImage());
		map.put("key" + i, goodsMap);
	}
}
