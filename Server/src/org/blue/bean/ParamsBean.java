/**
 * @(#)ParamsBean.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.bean;
import java.util.List;
import org.blue.remote.FlashObject;
import org.blue.exception.ParamCastExcpetion;

/**
 * 对象方法封装bean，包含一个方法的所有参数类型和参数
 */
public class ParamsBean
{
	private List params;
	private List paramsTypes; 
	
	/**
	 * 设置所有的参数.
	 * @param params 参数集合.
	 */
	public void setParams(List params)
	{
		this.params = params;
	}
	
	/**
	 * 设置所有的参数类型集合
	 * @param paramsTypes 集合
	 */
	public void setParamsTypes(List paramsTypes)
	{
		this.paramsTypes = paramsTypes;
	}
	
	/**
	 * 返回所有的参数.
	 * @return 返回参数集合.
	 */
	public List getParams()
	{
		return params;
	}
	
	/**
	 * 返回所有的参数类型.
	 * @return 返回参数类型集合.
	 */
	public List getParamsTypes()
	{
		return paramsTypes;
	}
	
	/**
	 * 根据索引获取数组的参数.
	 * @param index 参数的索引（方法的第N个参数）.
	 * @return 返回参数数组array.
	 * @throws ParamCastExcpetion
	 */
	public List getArray(int index) throws ParamCastExcpetion
	{
		try
		{
			List array = (List)params.get(index - 1);
			return array;
		}
		catch(Exception cs)
		{
			throw new ParamCastExcpetion("数组转换异常！");
		}
	}
	
	/**
	 * 根据索引获取字符串的参数.
	 * @param index 参数的索引（方法的第N个参数）.
	 * @return 返回字符串.
	 * @throws ParamCastExcpetion
	 */
	public String getString(int index) throws ParamCastExcpetion
	{
		try
		{
			String temp = (String)params.get(index - 1);
			return temp;
		}
		catch(Exception cs)
		{
			throw new ParamCastExcpetion("字符串转换异常！");
		}
	}
	
	/**
	 * 根据索引获取整型的参数
	 * @param index 参数的索引（方法的第N个参数）.
	 * @return 返回int.
	 * @throws ParamCastExcpetion
	 */
	public int getInt(int index) throws ParamCastExcpetion
	{
		try
		{
			Integer temp = (Integer)params.get(index - 1);
			return temp.intValue();
		}
		catch(Exception cs)
		{
			throw new ParamCastExcpetion("整型换异常！");
		}
	}
	
	/**
	 * 根据索引获取双精度的参数.
	 * @param index 参数的索引（方法的第N个参数）.
	 * @return 返回双精度.
	 * @throws ParamCastExcpetion
	 */
	public double getDouble(int index) throws ParamCastExcpetion
	{
		try
		{
			Double temp = (Double)params.get(index - 1);
			return temp.doubleValue();
		}
		catch(Exception cs)
		{
			throw new ParamCastExcpetion("双精度换异常！");
		}
	}
	
	/**
	 * 根据索引获取布尔的参数.
	 * @param index 参数的索引（方法的第N个参数）.
	 * @return 返回布尔值.
	 * @throws ParamCastExcpetion
	 */
	public boolean getBoolean(int index) throws ParamCastExcpetion
	{
		try
		{
			Boolean temp = (Boolean)params.get(index - 1);
			return temp.booleanValue();
		}
		catch(Exception cs)
		{
			throw new ParamCastExcpetion("整型换异常！");
		}
	}
	
	/**
	 * 根据索引获取FlashObject的参数.
	 * @param index 参数的索引（方法的第N个参数）.
	 * @return 返回FlashObject实例.
	 * @throws ParamCastExcpetion
	 */
	public FlashObject getFlashObject(int index) throws ParamCastExcpetion
	{
		try
		{
			FlashObject temp = (FlashObject)params.get(index - 1);
			return temp;
		}
		catch(Exception cs)
		{
			throw new ParamCastExcpetion("FlashObject换异常！");
		}
	}
	
	/**
	 * 清空所有数据（设参数类型和参数集合为null）
	 */
	public void clear()
	{
		params = null;
		paramsTypes = null; 
	}
}

