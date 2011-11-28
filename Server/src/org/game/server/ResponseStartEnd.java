/**
 * @(#)ResponseStartEnd.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-21
 */
package org.game.server;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.blue.remote.Client;
import org.blue.global.Application;
import org.blue.response.StartEndListenterAdapter;

import org.game.context.Context;
import org.game.model.Sculpt;
import org.game.model.User;
import org.game.tools.HibernateUtil;
import org.hibernate.Session;
/**
 * �û��ڿ�ʼ��½�������˳���ʱ����Ӧ����ķ���
 * 
 * @author soda
 */
public class ResponseStartEnd extends StartEndListenterAdapter
{
	private Context context;
	
	public ResponseStartEnd()
	{
		context = Context.getContext();
	}
	/**
	 * �û����ӷ�����ʱ��Ӧ�÷���
	 * @param client:�ͻ��˷�װ����
	 * @param name:�û���
	 * @param pass���û�����
	 */
	public void onConnet(Client client,String name, String pass)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			List list = session.createQuery("from User as u where u.userName = :userName")
						.setString("userName", name)
						.list();
			User user = null;
			if(list.size() > 0)
			{
				user = (User)list.get(0);
				if(user.getUserPass().equals(pass))
				{
					Application.setStart(true,"������������!");
					//System.out.println("֪ͨ�ɹ�!");
					client.setId(name);
					client.setName("welcomeScene");
					context.setClient(name, client);
				}
				else
				{
					Application.setStart(false,"�������!");
				}
			}
			else
			{
				Application.setStart(false,"������������!");
			}
		}
		catch(Exception e)
		{
			System.out.println("����hibernate����:" + e);
		}
	}
	
	/**
	 * �û�������������˿�ʱ��Ӧ�÷���
	 * @Client client:�ͻ��˷�װ����
	 */
	public void thunderboltCut(Client client)
	{
		System.out.println("����������ر�ʱ����!û�в�����");
		String userName = client.getId();
		String scene = client.getName();
		//ȫ��ɾ����λ�õ��û�
		context.removeClient(client);
		System.out.println("userName:" + userName);
		System.out.println("scene:" + scene);
		Map<String,Client> temp = context.getsceneMap(scene);
		//�㲥���е��û���ǰ�û������˳�
		for(Iterator<Client> it = temp.values().iterator(); it.hasNext();)
		{
			Client cl = it.next();
			cl.call("PersonAction","userExit");
			cl.setParam(userName);
			cl.send();
		}
	}
}
