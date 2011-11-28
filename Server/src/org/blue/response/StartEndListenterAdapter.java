/**
 * @(#)StartEndListenterAdapter.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.response;
import org.blue.remote.Client;
/**
 * 实现了StartEndListenter接口的里方法，不过全部都是空实现，用于被继承
 * @author soda
 *
 */

public class StartEndListenterAdapter implements StartEndListenter
{
	/**
	 * 客户端刚连接成功时调用该方法
	 * (默认带一个参数方法)
	 * @param client 包含客户端信息的Client实例
	 */
	public void onConnet(Client client)
	{
	}
	
	/**
	 * 客户端意外与服务器断开连接时响应该方法
	 * (默认带一个参数方法)
	 * @param client
	 */
	public void thunderboltCut(Client client)
	{
	}
}
