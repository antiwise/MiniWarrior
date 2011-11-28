/**
 * @(#)ParseXml.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.xml;

import java.util.List;
import java.util.ArrayList;
import org.blue.bean.SendBean;
import org.blue.remote.FlashObject;


public class ParseXml
{
	private SendBean bean;
	
	public ParseXml()
	{
		bean = new SendBean();
	}
	
	/**
	 * 接受客户端传过来的呼叫xml字符串，对其进行分解
	 * @param xml xml格式数据
	 * @return SendBean实例
	 */
	public SendBean parseSendXml(String xml)
	{
		bean.clear();
		int index = xml.indexOf("\"");
		int lastIndex = xml.indexOf("\"", index + 1);
		String sendType = xml.substring(index + 1,lastIndex);
		if(sendType.equals("call"))
		{
			index = xml.indexOf("<server-name>",lastIndex);
			lastIndex =  xml.indexOf("</server-name>",index + 1);
			String serverName = xml.substring(index + 13,lastIndex);
			bean.setServerName(serverName);
			index = xml.indexOf("<method>",lastIndex);
			lastIndex =  xml.indexOf("</method>",index + 1);
			String method = xml.substring(index + 8,lastIndex);
			bean.setMethod(method);;
			index = xml.indexOf("<return-object>",lastIndex);
			lastIndex =  xml.indexOf("</return-object>",index + 1);
			String returnObject = xml.substring(index + 15,lastIndex);
			bean.setReturnName(returnObject);
			bean.setMethod(method);
			List params = new ArrayList();
			List types = new ArrayList();
			while(true)
			{
				index = xml.indexOf("parameter type=",lastIndex);
				if(index == -1)
				{
					break;
				}
				index =  xml.indexOf("\"",index);
				lastIndex =  xml.indexOf("\"",index + 1);
				String type = xml.substring(index + 1,lastIndex);
				if(type.equals("object"))
				{
					types.add(type);
					FlashObject object = new FlashObject();
					while(true)
					{
						index = xml.indexOf("list type=",lastIndex);
						if(index == -1)
						{
							break;
						}
						index =  xml.indexOf("\"",index + 1);
						lastIndex =  xml.indexOf("\"",index + 1);
						type = xml.substring(index + 1,lastIndex);
						index =  xml.indexOf("\"",lastIndex + 1);
						lastIndex =  xml.indexOf("\"",index + 1);
						String key = xml.substring(index + 1,lastIndex);
						index =  xml.indexOf("\"",lastIndex + 1);
						lastIndex =  xml.indexOf("\"",index + 1);
						String value = xml.substring(index + 1,lastIndex);
						System.out.println("value:" + value);
						if(type.equals("string"))
						{
							object.put(key,value);
						}
						else if(type.equals("number"))
						{
							object.put(key,new Double(value));
						}
						else
						{
							object.put(key,new Boolean(value));
						}
					}
					params.add(object);
				}
				else if(type.equals("array"))
				{
//					对数组进行封装
					index =  xml.indexOf("value=",index + 1);
					index =  xml.indexOf("\"",index + 1);
					lastIndex =  xml.indexOf("\"",index + 1);
					type = xml.substring(index + 1,lastIndex);
					//System.out.println("tpye===========:" + type);
					int temp = index;
					int lastTemp = lastIndex;
					int count = 0;
					//算出数组的个数
					while(true)
					{
						index = xml.indexOf("list value=",lastIndex);
						if(index == -1)
						{
							break;
						}
						lastIndex = index + 1;
						//System.out.println("count:" + count);
						count++;
					}
					//System.out.println("count===========:" + count);
					String[] strings = new String[count];
					double[] doubles = new double[count];
					index = temp;
					lastIndex = lastTemp;
					for(int i = 0; i < count; i++)
					{
						index = xml.indexOf("list value=",lastIndex);
						index =  xml.indexOf("\"",lastIndex + 1);
						lastIndex =  xml.indexOf("\"",index + 1);
						String value = xml.substring(index + 1,lastIndex);
						if(type.equals("string"))
						{
							strings[i] = value;
						}
						else if(type.equals("number"))
						{
							doubles[i] = new Double(value);
						}
					}
					if(type.equals("string"))
					{
						//System.out.println("============111111");
						types.add("string[]");
						params.add(strings);
					}
					else if(type.equals("number"))
					{
						//System.out.println("============2222222222222");
						types.add("double[]");
						params.add(doubles);
					}
				}
				else
				{
					types.add(type);
					//单个参数
					index =  xml.indexOf("\"",lastIndex + 1);
					lastIndex =  xml.indexOf("\"",index + 1);
					String param = xml.substring(index + 1,lastIndex);
					if(type.equals("number"))
					{
						params.add(new Double(param));
					}
					else if(type.equals("boolean"))
					{
						params.add(new Boolean(param));
					}
					else
					{
						params.add(param);
					}
				}
			}
			bean.setParams(params);
			bean.setParamsTypes(types);
			//System.out.println("==================end======================");
			
		}
		return bean;
	}
	private void parseList(String xml,int index, int lastIndex,List params,List types)
	{
		
	}
	/**
	 * 接受客户端传过来的连接xml字符串，对其进行分解
	 * @param xml xml格式数据
	 * @return SendBean实例
	 */
	public SendBean parseStartEndXml(String xml)
	{
		bean.clear();
		List params = new ArrayList();
		List types = new ArrayList();
		int index = 0;
		int lastIndex = 0;
		while(true)
		{
			index = xml.indexOf("parameter type=",lastIndex);
			if(index == -1)
			{
				break;
			}
			index =  xml.indexOf("\"",index);
			lastIndex =  xml.indexOf("\"",index + 1);
			String type = xml.substring(index + 1,lastIndex);
			//System.out.println("type:" + type);
			types.add(type);
			index =  xml.indexOf("\"",lastIndex + 1);
			lastIndex =  xml.indexOf("\"",index + 1);
			String param = xml.substring(index + 1,lastIndex);
			if(type.equals("number"))
			{
				params.add(new Double(param));
			}
			else
			{
				params.add(param);
			}
		}
		bean.setParams(params);
		bean.setParamsTypes(types);
		return bean;
	}
}