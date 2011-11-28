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
 * �����������࣬������������socket����
 * ��Ӧ�û���socket����
 */
public class Server 
{
	private ClientResponse response;
	//Ĭ�϶˿���5000
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
			System.out.println("������������!");
			int i = 1;
			while(true)
			{
				Socket socket = ss.accept();
				String id = "flashSysncServer1.0 Bate" + i;
				//����
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//����
				os = new PrintWriter(socket.getOutputStream());
				//�ȴ��ͻ��˵ĵ�һ����Ϣ��������û��Ƿ���Գɹ���½����Ϣ��
				String xml = br.readLine();
				//System.out.println("xml:" + xml);
				Client client = RemoteFactory.getRemoteFactory().getClient();
				client.setPrintWriter(os);
				//��ǣ��Ƿ�����һ���µ��߳�,Ĭ����������
				boolean flag = true;
				//�Ƿ�ע����Ӧ���ӶϿ���
				if(system.getStartEndObject() != null)
				{
					response.onConect(client,xml);
					//��������Ϊ�գ���ִ�ж�Ӧ�Ľ��
					if(system.getStart())
					{
						//֪ͨ�ͻ������ӳɹ�
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
					//������Դ
					br.close();
					os.close();
					client.clear();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ServerSocket�����쳣�ˣ���" + e);
		}
	}
}
