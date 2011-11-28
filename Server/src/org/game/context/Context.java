/**
 * @(#)Context.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-24
 */
package org.game.context;

import java.util.HashMap;
import java.util.Map;

import org.blue.remote.Client;
/**
 * 上下文类，在服务器的任何一个类都可使用
 * @author soda
 *
 */
public class Context 
{
	private static Context context;
	private Map<String,Client> userMap;
	private Map<String,Map> sceneMap;
	
	private Context()
	{
		userMap = new HashMap<String,Client>();
		sceneMap = new HashMap<String,Map>();
	}
	/**
	 * 获取上下文实例
	 * @return context
	 */
	public static Context getContext()
	{
		if(context == null)
		{
			context = new Context();
		}
		return context;
	}
	
	/**
	 * 把用户名跟client捆绑在一起
	 * @param scene：用户所在的位置（地图）
	 * @param uerName：用户名
	 * @param client：客户端连接对象
	 */
	public void setClient(String userName, Client client)
	{
		userMap.put(userName, client);
	}
	
	public void removeClient(Client client)
	{
		String userName = client.getId();
		userMap.remove(userName);
		String scene = client.getName();
		Map<String,Client> temp = sceneMap.get(scene);
		if(temp != null)
		{
			temp.remove(userName);
		}
	}
	
	/**
	 * 根据用户返回该用户所对应的Client对象
	 * @param uerName:用户名
	 * @return Client对象
	 */
	public Client getClient(String uerName)
	{
		return userMap.get(uerName);
	}
	
	/**
	 * 获取所有用户的集合
	 * @return userMap
	 */
	public Map<String,Client> getUserMap()
	{
		return userMap;
	}
	
	/**
	 * 根据位置获取出该位置的Map（该位置的所有用户的集合）
	 * @param sceneName：场景名称
	 * @return 一个场景的用户集合
	 */
	public Map<String,Client> getsceneMap(String sceneName)
	{
		//判断该场景是否已经存在
		Map<String,Client> temp = sceneMap.get(sceneName);
		if(temp == null)
		{
			temp = new HashMap<String,Client>();
			sceneMap.put(sceneName, temp);
		}
		return temp;
	}
}
