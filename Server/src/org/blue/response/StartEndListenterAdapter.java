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
 * ʵ����StartEndListenter�ӿڵ��﷽��������ȫ�����ǿ�ʵ�֣����ڱ��̳�
 * @author soda
 *
 */

public class StartEndListenterAdapter implements StartEndListenter
{
	/**
	 * �ͻ��˸����ӳɹ�ʱ���ø÷���
	 * (Ĭ�ϴ�һ����������)
	 * @param client �����ͻ�����Ϣ��Clientʵ��
	 */
	public void onConnet(Client client)
	{
	}
	
	/**
	 * �ͻ���������������Ͽ�����ʱ��Ӧ�÷���
	 * (Ĭ�ϴ�һ����������)
	 * @param client
	 */
	public void thunderboltCut(Client client)
	{
	}
}
