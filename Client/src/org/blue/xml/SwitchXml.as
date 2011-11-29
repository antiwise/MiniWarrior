package org.blue.xml
{
	/**
	 * $Id: SwitchXml.as $ 
	 *
	 * @author  soda  E-mail:junaisy@163.com
	 * @version  1.0
	 * <br>Copyright 2006-2007 The SDK-I Studio
	 * <br>This program is protected by copyright laws.
	 * <br>Program Name:FlashSyncServer
	 * <br>Date:2007.1
	 */
	public class SwitchXml
	{
		
		/**
		 * �Ѳ���ת����xml�ַ�
		 */
		public function switchXmlString(id:String):String
		{
			arguments = arguments[1];
			var len:uint = arguments.length;
			var str:String = "<send type=\"call\">";
			//��ӷ���˱����еĶ���
			str = str + "<property type=\"thing\">";
			str = str + "<server-name>" + arguments[0] + "</server-name>";
			str = str + "<method>" + arguments[1] + "</method>";
			str = str + "<return-object>" + id + "</return-object>";
			str = str + "<return-method>" + arguments[2] + "</return-method>";
			str = str + "</property><content>";
			//��ʱֻ���ַ����ֺ��߼�ֵ
			for(var i:uint = 3; i < len; i++)
			{
				var tempString:String = typeof(arguments[i]);
				//���object
				if(tempString == "object")
				{
					//����Ľ���
					if(arguments[i][0] != undefined)
					{
						str = str + "<parameter type=\"array\" value=\"" + typeof(arguments[i][0]) + "\">";
						var lenAry:Number = arguments[i].length;
						for(var j:uint = 0; j <lenAry; j++)
						{
							str = str + "<list value=\"" + arguments[i][j] + "\" />";
						}
					}
					else
					{
						//object����Ľ���
						str = str + "<parameter type=\"" + tempString +  "\" value=\"object\">";
						for(var obj in arguments[i])
						{
							var typeString:String = typeof(arguments[i][obj]);
							//trace("typeString:" + typeString);
							str = str + "<list type=\"" + typeString + "\" key=\"" + obj;
							str = str + "\" value=\"" + arguments[i][obj] + "\" />";
						}
					}
					str = str + "</parameter>";
				}
				else
				{
					str = str + "<parameter type=\"" + tempString +  "\" ";
					str = str + "value=\"" + arguments[i] + "\" />";
				}
			}
			str = str + "</content></send>";
			//trace("send xml:" + str);
			return str;
		}
	}
}