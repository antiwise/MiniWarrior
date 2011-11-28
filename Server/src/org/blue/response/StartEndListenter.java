/**
 * @(#)StartEndListenter.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:JavaSyncServer
 * <br>Date:2007.1
 */
package org.blue.response;
import org.blue.remote.Client;

/**
 * <p>如果需要捕捉客户端连接服务器或者已连接的客户端因意外而与服务器连接时，
 * 则需要实现该接口，然后在Application类中的addStartEndListenter方法
 * 注册该接口实现类，则会自动响应该接口所实现的方法。</p>
 * 如果需要附带参数，则应该把该接口的方法多态化，则服务器会根据客户端的参数而
 * 调用相应参数的方法
 * @see StartEndListenterAdapter
 * @author soda
 */
public interface StartEndListenter 
{
	/**
	 * 客户端刚连接成功时调用该方法
	 * (默认带一个参数方法)
	 * @param client 包含客户端信息的Client实例
	 */
	public void onConnet(Client client);
	
	/**
	 * 客户端意外与服务器断开连接时响应该方法
	 * (默认带一个参数方法)
	 * @param client
	 */
	public void thunderboltCut(Client client);
}
