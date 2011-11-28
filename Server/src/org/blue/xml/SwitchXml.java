/**
 * @(#)SwitchXml.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.xml;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import org.blue.remote.FlashObject;
/**
 * 把数据转生成xml格式的字符串
 */

public class SwitchXml 
{
	private String xml;
	
	public void setType(String type)
	{
		xml = "<send type=\"" + type + "\" >";
	}
	
	/**
	 * 把呼叫客户端方法的各种属性转换成对应的xml字符串
	 * @param serverName 需要远程调用的对象的标识
	 * @param method 需要远程调用的对象的方法
	 * @param resultObj 如果远程方法有返回值，则在这个对象里的方法响应
	 * @param redMethod 远程信息返回调用该方法
	 */
	public void setProperty(String serverName,String method,String returnName,String redMethod)
	{
		xml = "<send type=\"" + "call" + "\" >";
		xml = xml + "<property type=\"thing\">";
		xml = xml + "<server-name>" + serverName + "</server-name>";
		xml = xml + "<method>" + method + "</method>";
		xml = xml + "<result-object>" + returnName + "</result-object>";
		xml = xml + "<red-method>" + redMethod + "</red-method>";
		xml = xml + "</property><content>";
	}
	
	/**
	 * 设置该xml字符串的property属性的值为noting.
	 */
	public void setProperty()
	{
		xml = xml + "<property type=\"nothing\" /><content>";
	}
	public void setParam(String value)
	{
		xml = xml + "<param type=\"string\" value=\"" + value + "\" />";
	}
	public void setParam(int value)
	{
		xml = xml + "<param type=\"number\" value=\"" + value + "\" />";
	}
	public void setParam(double value)
	{
		xml = xml + "<param type=\"number\" value=\"" + value + "\" />";
	}
	public void setParam(boolean value)
	{
		xml = xml + "<param type=\"boolean\" value=\"" + value+ "\" />";
	}
	/**
	 * 设置对象数组
	 * @param value
	 */
	public void setParam(List value)
	{
		Object obj = value.get(0);
		if(obj != null)
		{
			xml = xml + "<param type=\"array\" value=\"\">";
			for(int i = 0; i < value.size(); i++)
			{
				xml = xml + "<value>" + value.get(i).toString() + "</value>";
			}
		}
		xml = xml + "</param>";
	}
	/**
	 * 设置字符串数组
	 * @param value
	 */
	public void setParam(String[] value)
	{
		int len = value.length;
		xml = xml + "<param type=\"array\" value=\"string\">";
		for(int i = 0; i < len; i++)
		{
			xml = xml + "<list value =\"" + value[i] + "\" />";
		}
		xml = xml + "</param>";
	}
	
	/**
	 * 设置整型数组
	 * @param value
	 */
	public void setParam(int[] value)
	{
		int len = value.length;
		xml = xml + "<param type=\"array\" value=\"number\">";
		for(int i = 0; i < len; i++)
		{
			xml = xml + "<list value =\"" + value[i] + "\" />";
		}
		xml = xml + "</param>";
	}
	
	/**
	 * 设置双精度数组
	 * @param value
	 */
	public void setParam(double[] value)
	{
		int len = value.length;
		xml = xml + "<param type=\"array\" value=\"number\">";
		for(int i = 0; i < len; i++)
		{
			xml = xml + "<list value =\"" + value[i] + "\" />";
		}
		xml = xml + "</param>";
	}
	/**
	 * 当参数为Map时
	 * @param value
	 */
	public void setParam(Map value)
	{
		xml = xml + "<param type=\"map\" >";
		Iterator itKey = value.keySet().iterator();
		for(Iterator itValue = value.values().iterator(); itValue.hasNext();)
		{
			String key = (String)itKey.next();
			Map map = (Map)itValue.next();
			xml = xml + "<list type=\"map\" key=\"" + key + "\">";
			Iterator itKey2 = map.keySet().iterator();
			for(Iterator itValue2 = map.values().iterator(); itValue2.hasNext();)
			{
				key = (String)itKey2.next();
				Object obj = itValue2.next();
				if(obj.getClass() == String.class)
				{
					xml = xml + "<list-list type=\"string\" key=\"" + key + "\" value=\"" + obj + "\" />";
				}
				else if(obj.getClass() == Double.class)
				{
					xml = xml + "<list-list type=\"number\" key=\"" + key + "\" value=\"" + obj + "\" />";
				}
				else if(obj.getClass() == Integer.class)
				{
					xml = xml + "<list-list type=\"number\" key=\"" + key + "\" value=\"" + obj + "\" />";
				}
				else if(obj.getClass() == Boolean.class)
				{
					xml = xml + "<list-list type=\"boolean\" key=\"" + key + "\" value=\"" + obj + "\" />";
				}
			}
			xml = xml + "</list>";
		}
		xml = xml + "</param>";
		//System.out.println("Map->xml:" + xml);
	}
	
	/**
	 * 设置FlashObject对象参数
	 * @param value
	 */
	public void setParam(FlashObject value)
	{
		xml = xml + "<param type=\"object\" >";
		Map map =  value.getMap();
		String[] key = new String[map.size()];
		
		int i = 0;
		//把key存进一个数组里
		for(Iterator it = map.keySet().iterator(); it.hasNext();)
		{
			key[i] = (String)it.next();
			i++;
		}
		i = 0;
		for(Iterator it = map.values().iterator(); it.hasNext();)
		{
			Object obj = it.next();
			if(obj.getClass() == String.class)
			{
				xml = setList(xml,"string",key[i],obj);
			}
			else if(obj.getClass() == Double.class)
			{
				xml = setList(xml,"string",key[i],obj);
			}
			else if(obj.getClass() == Integer.class)
			{
				xml = setList(xml,"number",key[i],obj);
			}
			else if(obj.getClass() == Boolean.class)
			{
				xml = setList(xml,"boolean",key[i],obj);
			}
			i++;
		}
		xml = xml + "</param>";
	}
	
	/**
	 * 设置多参数字符串list的参数值
	 * @param str
	 * @param type
	 * @param key
	 * @param obj
	 * @return 字符串
	 */
	private String setList(String str,String type,String key,Object obj)
	{
		str = str + "<list type =\"" + type + "\" ";
		str = str + "key=\"" + key + "\" value=\"" + obj + "\"/>";
		return str;
	}
	/**
	 * 设置数组（还没实现）
	 * @param value
	 */
	public void setParams(List values)
	{
		for(int i = 0; i < values.size(); i++)
		{
		}
	}
	
	public String getXml()
	{
		//System.out.println("send xml:" + xml);
		return xml + "</content></send>";
	}
}
