/**
 * @(#)Application.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.blue.remote.FlashObject;
import org.blue.global.Application;
import org.blue.global.SystemCommon;
import org.blue.remote.RemoteFactory;
import org.blue.remote.Client;
import org.blue.response.ClientResponse;
import org.blue.util.CheckObjectType;

/**
 * 服务启动主类，启动服务器的socket服务
 * 响应用户的socket连接
 */
public class Server 
{
	private ClientResponse response;
	//默认端口是5000
	private int port = 5000;
	private SystemCommon system;
	
	public Server()
	{
		response = ClientResponse.getClientResponse();
		system = SystemCommon.getSystemCommon();
	}
	
	public Server(int port)
	{
		this.port = port;
		response = ClientResponse.getClientResponse();
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	
	public void startServer()
	{
		PrintWriter os = null;
		BufferedReader br = null;
		try
		{
			ServerSocket ss = new ServerSocket(port);
			System.out.println("启动服务器了!");
			int i = 1;
			while(true)
			{
				Socket socket = ss.accept();
				String id = "flashSysncServer1.0 Bate" + i;
				//接受
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//发送
				os = new PrintWriter(socket.getOutputStream());
				//等待客户端的第一条信息（检验该用户是否可以成功登陆的信息）
				String xml = br.readLine();
				//System.out.println("xml:" + xml);
				Client client = RemoteFactory.getRemoteFactory().getClient();
				client.setPrintWriter(os);
				//标记，是否启动一个新的线程,默认是启动的
				boolean flag = true;
				//是否注册响应连接断开类
				if(system.getStartEndObject() != null)
				{
					response.onConect(client,xml);
					//如果结果不为空，则执行对应的结果
					if(system.getStart())
					{
						//通知客户端连接成功
						os.print(system.getMsg() + "\0");
						os.flush();
					}
					else
					{
						os.print(system.getMsg() + "\0");
						os.flush();
						flag = false;
					}
				}
				if(flag)
				{
					new ServerThread(id,br,os,client).start();
				}
				else
				{
					//回收资源
					br.close();
					os.close();
					client.clear();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ServerSocket出现异常了！！" + e);
		}
	}
}
