/**
 * @(#)FlashObject.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-20
 */
package org.blue.remote;
/**
 * @(#)FlashObject.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:JavaSyncServer
 * <br>Date:2007.1
 */
import java.util.Map;
import java.util.HashMap;
/**
 * ��flash����ת����Ӧ��java������ʵֻ�����falsh��object��������Խ��з�װ��
 * ת�������������еķ�����ת����
 * @author soda
 *
 */
public class FlashObject 
{
	private Map map;
	private int length;
	
	public FlashObject()
	{
		length = 0;
		map = new HashMap();
	}
	
	public void put(String key, Object value)
	{
		//System.out.println("value:" + value);
		Object temp = map.get(key);
		if(temp == null)
		{
			map.put(key,value);
			length++;
		}
	}
	
	public Object get(String key)
	{
		return map.get(key);
	}
	
	public Map getMap()
	{
		return map;
	}
	
	/**
	 * ����keyȡ���ַ���
	 * @param key 
	 * @return
	 */
	public String getString(String key)
	{
		return (String)map.get(key);
	}
	
	public double getDouble(String key)
	{
		return ((Double)map.get(key)).doubleValue();
	}
	
	public boolean getBoolean(String key)
	{
		return ((Boolean)map.get(key)).booleanValue();
	}
	
	public void clear()
	{
		map.clear();
	}
	
	public int length()
	{
		return length;
	}
}
