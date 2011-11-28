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
 * ���󷽷���װbean������һ�����������в������ͺͲ���
 */
public class ParamsBean
{
	private List params;
	private List paramsTypes; 
	
	/**
	 * �������еĲ���.
	 * @param params ��������.
	 */
	public void setParams(List params)
	{
		this.params = params;
	}
	
	/**
	 * �������еĲ������ͼ���
	 * @param paramsTypes ����
	 */
	public void setParamsTypes(List paramsTypes)
	{
		this.paramsTypes = paramsTypes;
	}
	
	/**
	 * �������еĲ���.
	 * @return ���ز�������.
	 */
	public List getParams()
	{
		return params;
	}
	
	/**
	 * �������еĲ�������.
	 * @return ���ز������ͼ���.
	 */
	public List getParamsTypes()
	{
		return paramsTypes;
	}
	
	/**
	 * ����������ȡ����Ĳ���.
	 * @param index �����������������ĵ�N��������.
	 * @return ���ز�������array.
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
			throw new ParamCastExcpetion("����ת���쳣��");
		}
	}
	
	/**
	 * ����������ȡ�ַ����Ĳ���.
	 * @param index �����������������ĵ�N��������.
	 * @return �����ַ���.
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
			throw new ParamCastExcpetion("�ַ���ת���쳣��");
		}
	}
	
	/**
	 * ����������ȡ���͵Ĳ���
	 * @param index �����������������ĵ�N��������.
	 * @return ����int.
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
			throw new ParamCastExcpetion("���ͻ��쳣��");
		}
	}
	
	/**
	 * ����������ȡ˫���ȵĲ���.
	 * @param index �����������������ĵ�N��������.
	 * @return ����˫����.
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
			throw new ParamCastExcpetion("˫���Ȼ��쳣��");
		}
	}
	
	/**
	 * ����������ȡ�����Ĳ���.
	 * @param index �����������������ĵ�N��������.
	 * @return ���ز���ֵ.
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
			throw new ParamCastExcpetion("���ͻ��쳣��");
		}
	}
	
	/**
	 * ����������ȡFlashObject�Ĳ���.
	 * @param index �����������������ĵ�N��������.
	 * @return ����FlashObjectʵ��.
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
			throw new ParamCastExcpetion("FlashObject���쳣��");
		}
	}
	
	/**
	 * ����������ݣ���������ͺͲ�������Ϊnull��
	 */
	public void clear()
	{
		params = null;
		paramsTypes = null; 
	}
}

