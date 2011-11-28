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
 * �������࣬�ڷ��������κ�һ���඼��ʹ��
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
	 * ��ȡ������ʵ��
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
	 * ���û�����client������һ��
	 * @param scene���û����ڵ�λ�ã���ͼ��
	 * @param uerName���û���
	 * @param client���ͻ������Ӷ���
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
	 * �����û����ظ��û�����Ӧ��Client����
	 * @param uerName:�û���
	 * @return Client����
	 */
	public Client getClient(String uerName)
	{
		return userMap.get(uerName);
	}
	
	/**
	 * ��ȡ�����û��ļ���
	 * @return userMap
	 */
	public Map<String,Client> getUserMap()
	{
		return userMap;
	}
	
	/**
	 * ����λ�û�ȡ����λ�õ�Map����λ�õ������û��ļ��ϣ�
	 * @param sceneName����������
	 * @return һ���������û�����
	 */
	public Map<String,Client> getsceneMap(String sceneName)
	{
		//�жϸó����Ƿ��Ѿ�����
		Map<String,Client> temp = sceneMap.get(sceneName);
		if(temp == null)
		{
			temp = new HashMap<String,Client>();
			sceneMap.put(sceneName, temp);
		}
		return temp;
	}
}
