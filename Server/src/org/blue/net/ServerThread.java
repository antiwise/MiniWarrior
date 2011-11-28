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
 * Ϊÿһ���û�������߳�
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
	 * �̵߳Ĺ��캯��
	 * @param id ��ʶ
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
		System.out.println("�������߳���");
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
			//�����������಻Ϊ�գ�����ø÷���
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
					System.out.println("�û�����Ͽ��¼�ʧ��:" + es);
				}
			}
			System.out.println("e:" + e);
			//�رջ�����Դ
			try
			{
				br.close();
				os.close();
				//���տͻ�����Դ
				client.clear();
			}
			catch (Exception ee)
			{
				System.out.println("e:" + ee);
			}
		}
	}
}