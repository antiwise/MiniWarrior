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
 * <p>�����Ҫ��׽�ͻ������ӷ��������������ӵĿͻ���������������������ʱ��
 * ����Ҫʵ�ָýӿڣ�Ȼ����Application���е�addStartEndListenter����
 * ע��ýӿ�ʵ���࣬����Զ���Ӧ�ýӿ���ʵ�ֵķ�����</p>
 * �����Ҫ������������Ӧ�ðѸýӿڵķ�����̬���������������ݿͻ��˵Ĳ�����
 * ������Ӧ�����ķ���
 * @see StartEndListenterAdapter
 * @author soda
 */
public interface StartEndListenter 
{
	/**
	 * �ͻ��˸����ӳɹ�ʱ���ø÷���
	 * (Ĭ�ϴ�һ����������)
	 * @param client �����ͻ�����Ϣ��Clientʵ��
	 */
	public void onConnet(Client client);
	
	/**
	 * �ͻ���������������Ͽ�����ʱ��Ӧ�÷���
	 * (Ĭ�ϴ�һ����������)
	 * @param client
	 */
	public void thunderboltCut(Client client);
}
