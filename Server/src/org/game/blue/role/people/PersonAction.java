/**
 * @(#)PersonAction.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.blue.role.people;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import org.blue.remote.Client;
import org.game.context.Context;

public class PersonAction 
{
	private Context context;
	
	public PersonAction()
	{
		context = Context.getContext();
	}
	/**
	 * ���ܵ�ǰ�û������Ķ�������Ȼ��Ѹ÷������㲥���������������û�
	 * @param scene:��ǰ�û����ڵĳ���
	 * @param userName:��ǰ�û���
	 * @param method����ǰ�û����������ķ�������
	 */
	public void userAction(String scene,String userName,String method)
	{
		System.out.println("PersonAction-> scene:" + scene);
		System.out.println("PersonAction-> userName:" + userName);
		System.out.println("PersonAction-> method:" + method);
		Client user = context.getClient(userName);
		Map<String,Client> temp = context.getsceneMap(scene);
		//call�����û�
		if(temp != null)
		{
			for(Iterator<Client> it = temp.values().iterator(); it.hasNext();)
			{
				Client client = it.next();
				//��ȥ�Լ��������û�
				if(user != client)
				{
					client.call("PersonAction","userAction");
					client.setParam(userName);
					client.setParam(method);
					client.send();
				}
			}
		}
	}
	/**
	 * �û���½�ɹ���ͬʱ����Ϣ��������ǰ�������û�
	 * @param userName
	 */
	public void loginSuccess(String scene, String userName, String userSculpt)
	{
		System.out.println("loginSuccess-> scene:" + scene);
		System.out.println("loginSuccess-> userName:" + userName);
		//���������û���֪ͨ���û���½�¼�
		System.out.println("֪ͨ�ɹ�!");
		Map<String,Client> tempMap = context.getsceneMap(scene);
		//��ͬ���������û����͵�ǰ��½�û�����Ϣ
		if(tempMap.size() > 0)
		{
			for(Iterator<Client> it = tempMap.values().iterator(); it.hasNext();)
			{
				Client temp = it.next();
				System.out.println("loginSuccess-> client:" + temp);
				temp.call("PersonAction","userLogin");
				temp.setParam(userName);
				temp.setParam(userSculpt);
				temp.send();
			}
		}
		//�ѵ�ǰ�û������ǰ������
		Client client = context.getClient(userName);
		tempMap.put(userName, client);
	}
	
	public void userCome(String callName,String userName, String userSculpt, double x, double y)
	{
		Client client = context.getClient(callName);
		client.call("PersonAction", "userCome");
		client.setParam(userName);
		client.setParam(userSculpt);
		client.setParam(x);
		client.setParam(y);
		client.send();
	}
	
	/**
	 * �����û��ı䳡��ʱ���ø÷���
	 * @param oldScene
	 * @param sceneName
	 * @param userName
	 * @param userSculpt
	 */
	public void changeScene(String oldScene,String sceneName,String userName, String userSculpt)
	{
		System.out.println("changeScene-> scene:" + oldScene);
		//�������ͻ����˳����û�
		Map<String,Client> tempMap = context.getsceneMap(oldScene);
		//ɾ����ǰ�û�
		tempMap.remove(userName);
		if(tempMap.size() > 0)
		{
			for(Iterator<Client> it = tempMap.values().iterator(); it.hasNext();)
			{
				Client temp = it.next();
				System.out.println("changeScene-> client:" + temp);
				temp.call("PersonAction","userExit");
				temp.setParam(userName);
				temp.send();
			}
		}
		//����ͬ���ڳ�������ҽ��������û�����Ϣ
		loginSuccess(sceneName,userName,userSculpt);
	}
	/**
	 * �����û���������Ϣ
	 * @param userName
	 * @param fiendName
	 * @param msg
	 */
	public void friendSpeak(String userName,String fiendName,String msg)
	{
		Client client = context.getClient(fiendName);
		client.call("PersonAction", "friendSpeak");
		client.setParam(userName);
		client.setParam(msg);
		client.send();
	}
	
	/**
	 * ���ܿͻ���˵�����ݺͲ���
	 * @param sceneName
	 * @param userName
	 * @param msg
	 */
	public void userSpeak(String sceneName,String userName,String msg)
	{
		Map<String,Client> tempMap = context.getsceneMap(sceneName);
		if(tempMap.size() > 1)
		{
			Client client = context.getClient(userName);
			for(Iterator<Client> it = tempMap.values().iterator(); it.hasNext();)
			{
				Client temp = it.next();
				//��ֹ���͸��Լ�
				if(temp != client)
				{
					//System.out.println("changeScene-> client:" + temp);
					temp.call("PersonAction","userSpeak");
					temp.setParam(userName);
					temp.setParam(msg);
					temp.send();
				}
			}
		}
	}
	/**
	 * ���û�˵��ʱ���÷������ͻ��˺���
	 */
	public void userChat(String userName,String face,String toUser,String msg,boolean flag)
	{
		Map map = context.getUserMap();
		int len = map.size();
		if(len > 1)
		{
			if(flag)
			{
				msg = userName + "͵͵�ض���˵��" + msg;
				Client temp = (Client)map.get(toUser);
				//�����ض��ͻ��˸���������Ϣ�ķ���
				temp.call("PersonAction","updateInfo");
				temp.setParam(msg);
				temp.send();
			}
			else
			{
				msg = userName + face + "�ض�" + toUser + "˵��" + msg;
				Client client = context.getClient(userName);
				for(Iterator it = map.values().iterator(); it.hasNext();)
				{
					Client temp = (Client)it.next();
					//��ֹ���Լ��ٴη���Ϣ
					if(client != temp)
					{
						//�������пͻ��˸���������Ϣ�ķ���
						temp.call("PersonAction","updateInfo");
						temp.setParam(msg);
						temp.send();
					}
				}
			}
		}
	}
}
