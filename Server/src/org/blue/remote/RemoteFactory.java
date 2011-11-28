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
 *  Ϊremote�����������ʵ���Ĺ���
 * @author soda
 *
 */

public class RemoteFactory 
{
	//client�ĳ�
	private List<Client> clientPool;
	private static RemoteFactory factory;
	
	private RemoteFactory()
	{
		clientPool = new ArrayList<Client>();
	}
	
	/**
	 * ��ȡһ��RemoteFactory��ʵ��
	 * @return RemoteFactoryʵ��
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
	 * ��ȡ�ͻ�����Ϣ��װ��
	 * @return Clientʵ��
	 */
	public Client getClient()
	{
		//�Գص���Դ���б���
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
