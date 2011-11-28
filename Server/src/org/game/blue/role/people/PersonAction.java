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
	 * 接受当前用户发生的动作请求，然后把该方法名广播给所在区的所有用户
	 * @param scene:当前用户所在的场景
	 * @param userName:当前用户名
	 * @param method：当前用户发生动作的方法名称
	 */
	public void userAction(String scene,String userName,String method)
	{
		System.out.println("PersonAction-> scene:" + scene);
		System.out.println("PersonAction-> userName:" + userName);
		System.out.println("PersonAction-> method:" + method);
		Client user = context.getClient(userName);
		Map<String,Client> temp = context.getsceneMap(scene);
		//call其他用户
		if(temp != null)
		{
			for(Iterator<Client> it = temp.values().iterator(); it.hasNext();)
			{
				Client client = it.next();
				//除去自己的其他用户
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
	 * 用户登陆成功，同时把信息反馈给当前的所有用户
	 * @param userName
	 */
	public void loginSuccess(String scene, String userName, String userSculpt)
	{
		System.out.println("loginSuccess-> scene:" + scene);
		System.out.println("loginSuccess-> userName:" + userName);
		//呼叫其他用户，通知该用户登陆事件
		System.out.println("通知成功!");
		Map<String,Client> tempMap = context.getsceneMap(scene);
		//向同个场景的用户发送当前登陆用户的信息
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
		//把当前用户添进当前场景里
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
	 * 当有用户改变场景时调用该方法
	 * @param oldScene
	 * @param sceneName
	 * @param userName
	 * @param userSculpt
	 */
	public void changeScene(String oldScene,String sceneName,String userName, String userSculpt)
	{
		System.out.println("changeScene-> scene:" + oldScene);
		//在其他客户端退出该用户
		Map<String,Client> tempMap = context.getsceneMap(oldScene);
		//删除当前用户
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
		//呼叫同现在场景的玩家接受现在用户的信息
		loginSuccess(sceneName,userName,userSculpt);
	}
	/**
	 * 接受用户的聊天信息
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
	 * 接受客户的说话内容和参数
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
				//防止发送给自己
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
	 * 当用户说话时，该方法被客户端呼叫
	 */
	public void userChat(String userName,String face,String toUser,String msg,boolean flag)
	{
		Map map = context.getUserMap();
		int len = map.size();
		if(len > 1)
		{
			if(flag)
			{
				msg = userName + "偷偷地对你说：" + msg;
				Client temp = (Client)map.get(toUser);
				//呼叫特定客户端更新聊天信息的方法
				temp.call("PersonAction","updateInfo");
				temp.setParam(msg);
				temp.send();
			}
			else
			{
				msg = userName + face + "地对" + toUser + "说：" + msg;
				Client client = context.getClient(userName);
				for(Iterator it = map.values().iterator(); it.hasNext();)
				{
					Client temp = (Client)it.next();
					//禁止给自己再次发信息
					if(client != temp)
					{
						//呼叫所有客户端更新聊天信息的方法
						temp.call("PersonAction","updateInfo");
						temp.setParam(msg);
						temp.send();
					}
				}
			}
		}
	}
}
