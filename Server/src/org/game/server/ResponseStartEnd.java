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
 * 用户在开始登陆和意外退出的时候，响应该类的方法
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
	 * 用户连接服务器时响应该方法
	 * @param client:客户端封装对象
	 * @param name:用户名
	 * @param pass：用户密码
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
					Application.setStart(true,"服务允许连接!");
					//System.out.println("通知成功!");
					client.setId(name);
					client.setName("welcomeScene");
					context.setClient(name, client);
				}
				else
				{
					Application.setStart(false,"密码错误!");
				}
			}
			else
			{
				Application.setStart(false,"服务不允许连接!");
			}
		}
		catch(Exception e)
		{
			System.out.println("调用hibernate错误！:" + e);
		}
	}
	
	/**
	 * 用户意外与服务器端开时响应该方法
	 * @Client client:客户端封装对象
	 */
	public void thunderboltCut(Client client)
	{
		System.out.println("服务器意外关闭时调用!没有参数的");
		String userName = client.getId();
		String scene = client.getName();
		//全局删除该位置的用户
		context.removeClient(client);
		System.out.println("userName:" + userName);
		System.out.println("scene:" + scene);
		Map<String,Client> temp = context.getsceneMap(scene);
		//广播所有的用户当前用户意外退出
		for(Iterator<Client> it = temp.values().iterator(); it.hasNext();)
		{
			Client cl = it.next();
			cl.call("PersonAction","userExit");
			cl.setParam(userName);
			cl.send();
		}
	}
}
