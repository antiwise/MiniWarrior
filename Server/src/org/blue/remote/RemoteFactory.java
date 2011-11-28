/**
 * @(#)RemoteFactory.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.remote;
import java.util.List;
import java.util.ArrayList;
/**
 *  为remote包里的类生成实例的工厂
 * @author soda
 *
 */

public class RemoteFactory 
{
	//client的池
	private List<Client> clientPool;
	private static RemoteFactory factory;
	
	private RemoteFactory()
	{
		clientPool = new ArrayList<Client>();
	}
	
	/**
	 * 获取一个RemoteFactory的实例
	 * @return RemoteFactory实例
	 */
	public static RemoteFactory getRemoteFactory()
	{
		if(factory == null)
		{
			factory = new RemoteFactory();
		}
		return factory;
	}
	
	/**
	 * 获取客户端信息封装类
	 * @return Client实例
	 */
	public Client getClient()
	{
		//对池的资源进行遍历
		int len = clientPool.size();
		for(int i = 0; i < len; i++)
		{
			Client client = clientPool.get(i);
			if(client.getFlag())
			{
				client.setFlag(false);
				return client;
			}
		}
		Client client = new ClientImpl();
		clientPool.add(client);
		return client;
	}
}
