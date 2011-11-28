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
 * 把flash对象转换对应的java对象。其实只是针对falsh的object对象的属性进行封装和
 * 转换，不包括其中的方法的转换。
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
	 * 根据key取出字符串
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
