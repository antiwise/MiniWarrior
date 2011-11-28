package org.blue.xml
{
	import org.blue.soda.util.IMap;
	import org.blue.soda.util.HashMap;
	import org.blue.bean.remote.ReceiveBean;
	
	public class ParseXml
	{
		private var xmlObj:XML;
		private var bean:ReceiveBean;
		private var params:Array;
		
		public function ParseXml()
		{
			xmlObj = new XML();
			bean = new ReceiveBean();
		}
		
		public function parseXmlString(xml:String):ReceiveBean
		{
			//trace("==========xml=========" + xml);
			params = new Array();
			xmlObj.parseXML(xml);
			//��ȡxml�ַ��е�property����
			var temp:String = String(xmlObj.childNodes[0].childNodes[0].childNodes[0].childNodes[0]);
			bean.setServerName(temp);
			temp = String(xmlObj.childNodes[0].childNodes[0].childNodes[1].childNodes[0]);
			bean.setMethod(temp);
			temp = String(xmlObj.childNodes[0].childNodes[0].childNodes[2].childNodes[0]);
			bean.setRedName(temp);
			temp = String(xmlObj.childNodes[0].childNodes[0].childNodes[3].childNodes[0]);
			bean.setRedMethod(temp);
			var ary:Array = xmlObj.childNodes[0].childNodes[1].childNodes;
			var len:Number = ary.length;
			for(var i = 0; i < len; i++)
			{
				var obj:Object = ary[i].attributes;
				if(obj["type"] == "object")
				{
					//ת������
					//trace("1111111111===========");
					var arry:Array = xmlObj.childNodes[0].childNodes[1].childNodes[i].childNodes;
					var len2:Number = arry.length;
					var map:Object = new Object();
					//trace("len2:" + len2);
					
					for(var j = 0; j < len2; j++)
					{
						var objs:Object = arry[j].attributes;
						var tempObj:Object = setParam(objs);
						//trace("tempObj:" + tempObj);
						map[objs["key"]] = tempObj["value"];
						//trace("objs:" + tempObj["value"]);
					}
					params.push(map);
				}
				else if(obj["type"] == "map")
				{
					//trace("~~~~+===~~~++:" + obj);
					var arry:Array = xmlObj.childNodes[0].childNodes[1].childNodes[i].childNodes;
					var len2:Number = arry.length;
					var m:IMap = new HashMap();
					for(var j = 0; j < len2; j++)
					{
						//��ȡ��������
						var objs:Object = arry[j].attributes;
						var listArray:Array = arry[j].childNodes;
						var listLen:Number = listArray.length;
						var tempMap:IMap = new HashMap();
						//trace("objs[key]:" + objs["key"]);
						for(var k:Number = 0; k < listLen; k++)
						{
							var objss:Object = listArray[k].attributes;
							//var tempObj:Object = setParam(objs);
							//trace("=====objss[\"key\"]====:" + objss["key"]);
							tempMap.put(objss["key"],objss["value"]); 
						}
						//trace("tempMap.values():" + String(objs["key"]);
						//trace("j:" + j);
						m.put(objs["key"],tempMap);
						//trace(map.values());
						///trace(map.size());
						//trace("--------------------------");
					}
					//trace("======================================");
					//trace(map.values());
					params.push(m);
				}
				else if(obj["type"] == "array")
				{
					//ת������
					var typeValue:String = obj["value"];
					var arry:Array = xmlObj.childNodes[0].childNodes[1].childNodes[i].childNodes;
					var len2:Number = arry.length;
					var dataArray:Array = new Array();
					//trace("arry:" + arry + "11111");
					for(var j = 0; j < len2; j++)
					{
						var objs:Object = arry[j].attributes;
						if(typeValue == "string")
						{
							dataArray.push(String(objs["value"]));
						}
						else if(typeValue == "number")
						{
							dataArray.push(Number(objs["value"]));
						}
						else
						{
							dataArray.push(Boolean(objs["value"]));
						}
						//trace("dataArray:" + dataArray);
					}
					params.push(dataArray);
				}
				else
				{
					//trace("��ͨ���.................");
					setParam(obj);
				}
			}
			bean.setParams(params);
			return bean;
		}
		
		/**
		 * ��ݵ�����ת��
		 */
		private function setParam(obj:Object):Object
		{
			var objs:Object = new Object();
			if(obj["type"] == "string")
			{
				if(obj["key"] == undefined)
				{
					params.push(String(obj["value"]));
				}
				objs["value"] = String(obj["value"]);
				return objs;
			}
			else if(obj["type"] == "number")
			{
				if(obj["key"] == undefined)
				{
					params.push(Number(obj["value"]));
				}
				objs["value"] = Number(obj["value"]);
				return objs;
			}
			else if(obj["type"] == "boolean")
			{
				if(obj["key"] == undefined)
				{
					params.push(Boolean(obj["value"]));
				}
				objs["value"] = Boolean(obj["value"]);
				return objs;
			}
			return null;
		}
	}
}