/**
 * @(#)HandleXml.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:JavaSyncServer
 * <br>Date:2007.1
 */
package org.blue.xml;
import java.util.List;
import java.util.Map;

import org.blue.remote.FlashObject;
import org.blue.bean.SendBean;
/**
 * 操作flash与java之间以xml格式传输的数据
 * 把该字符串转换成功对应的对象.
 * 该接口有以下实现:
 * @see HandleXmlStringImpl
 * 
 * @author soda
 *
 */

public interface HandleXml 
{
	/**
	 *  接受客户端传过来的连接xml字符串，对其进行分解
	 * @param xml xml字符串数据
	 * @return SendBean实例
	 */
	public SendBean parseCallXml(String xml);
	
	/**
	 * 接受客户端传过来的连接xml字符串，对其进行分解
	 * @param xml xml格式数据
	 * @return SendBean实例
	 */
	public SendBean parseStartEndXml(String xml);
	
	/**
	 * 设置发送数据的发送类型
	 * @param type
	 */
	public void setType(String type);
	
	/**
	 * 把呼叫客户端方法的各种属性转换成对应的xml字符串
	 * @param serverName 需要远程调用的对象的标识
	 * @param method 需要远程调用的对象的方法
	 * @param resultObj 如果远程方法有返回值，则在这个对象里的方法响应
	 * @param redMethod 远程信息返回调用该方法
	 */
	public void setProperty(String serverName,String method,String returnName,String redMethod);
	
	/**
	 * 设置该xml字符串的property属性的值为noting.
	 */
	public void setProperty();
	
	/**
	 * 设置字符串参数
	 * @param value
	 */
	public void setParam(String value);
	
	/**
	 * 设置整型参数
	 * @param value
	 */
	public void setParam(int value);
	
	/**
	 * 设置双精度参数
	 * @param value
	 */
	public void setParam(double value);
	
	/**
	 * 设置布尔型参数
	 * @param value
	 */
	public void setParam(boolean value);
	
	/**
	 * 设置flash对象参数
	 * @param value
	 */
	public void setParam(FlashObject value);
	
	/**
	 * 设置flash对象参数
	 * @param value
	 */
	public void setParam(Map value);
	
	/**
	 * 设置对象数组
	 * @param values
	 */
	public void setParam(List values);
	
	/**
	 * 设置字符串数组
	 * @param values
	 */
	public void setParams(String[] value);
	
	/**
	 * 设置整型数组
	 * @param values
	 */
	public void setParams(int[] value);
	
	/**
	 * 设置双精度数组
	 * @param values
	 */
	public void setParams(double[] value);
	
	/**
	 * 获取生成规定格式的xml文件
	 * @return xml格式的字符串
	 */
	public String getXml();
}
