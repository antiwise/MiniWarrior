package org.blue.util
{
	/**
	 * $Id: ConversionXML.as $ 
	 *
	 * @author  soda  E-mail:junaisy@163.com
	 * @version  1.0
	 * <br>Copyright 2006-2007 The SDK-I Studio
	 * <br>This program is protected by copyright laws.
	 * <br>Program Name:FlashSyncServer
	 * <br>Date:2007.1
	 */
	import org.blue.xml.ParseXml;
	
	public class ConversionXML
	{
		
		/**
		 * 把参数转换成xml字符串
		 */
		public static function callParamToXml(id:String):String
		{
			arguments = arguments[1];
			var len = arguments.length;
			var str:String = "<send type=\"call\">";
			//添加服务端被呼叫的对象
			str = str + "<property type=\"thing\">";
			str = str + "<server-name>" + arguments[0] + "</server-name>";
			str = str + "<method>" + arguments[1] + "</method>";
			str = str + "<return-object>" + id + "</return-object>";
			str = str + "</property><content>";
			//暂时只传字符串，数字和逻辑值
			for(var i = 3; i < len; i++)
			{
				var tempString:String = typeof(arguments[i]);
				str = str + "<parameter type=\"" + tempString +  "\" ";
				str = str + "value=\"" + arguments[i] + "\" />"
			}
			str = str + "</content></send>";
			var par = new ParseXml();
			par.parseXmlString(str);
			return str;
		}
	}
}