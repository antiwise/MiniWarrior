/**
 * @(#).java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.net;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import org.blue.remote.Client;
import org.blue.global.Application;
import org.blue.global.SystemCommon;
import org.blue.response.MethodResponse;
import org.blue.exception.ResponseException;

/**
 * 为每一个用户服务的线程
 */

public class ServerThread extends Thread
{
	private String id;
	private Client client;
	private MethodResponse response;
	private PrintWriter os;
	private BufferedReader br;
	private SystemCommon system;
	
	/**
	 * 线程的构造函数
	 * @param id 标识
	 * @param socket
	 * @param client
	 */
	public ServerThread(String id,BufferedReader br,PrintWriter os,Client client)
	{
		this.id = id;
		this.client = client;
		this.br = br;
		this.os = os;
		response = new MethodResponse();
		system = SystemCommon.getSystemCommon();
	}
	public void run()
	{
		System.out.println("启动多线程了");
		try
		{
			while(true)
			{
				String xml = br.readLine();
				//System.out.println("accept xml:" + xml);
				response.response(client,xml);
			}
		}
		catch (Exception e)
		{
			//如果意外监听类不为空，则调用该方法
			if(system.getStartEndObject() != null)
			{
				Object obj = system.getStartEndObject();
				Class cl = obj.getClass();
				try
				{
					Method method = cl.getMethod("thunderboltCut", Client.class);
					method.invoke(obj, client);
				}
				catch(Exception es)
				{
					System.out.println("用户意外断开事件失败:" + es);
				}
			}
			System.out.println("e:" + e);
			//关闭回收资源
			try
			{
				br.close();
				os.close();
				//回收客户端资源
				client.clear();
			}
			catch (Exception ee)
			{
				System.out.println("e:" + ee);
			}
		}
	}
}