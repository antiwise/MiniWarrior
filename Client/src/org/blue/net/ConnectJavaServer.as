package org.blue.net
{
	/**
	 * $Id: ConnecJavaServer.as $ 
	 *
	 * @author  soda  E-mail:junaisy@163.com
	 * @version  1.0
	 * <br>Copyright 2006-2007 The SDK-I Studio
	 * <br>This program is protected by copyright laws.
	 * <br>Program Name:FlashSyncServer
	 * <br>Date:2007.1
	 */
	
	import org.blue.soda.util.IMap;
	import org.blue.soda.util.HashMap;
	import org.blue.xml.SwitchXml;
	import org.blue.xml.ParseXml;
	import org.blue.bean.remote.ReceiveBean;
	
	import flash.net.XMLSocket;
	
	/**
	 * 
	 */
	public class ConnectJavaServer
	{
		private static var connec:ConnectJavaServer;
		private var listenters:IMap;
		private var id:Number;
		private var swtichXml:SwitchXml;
		private var params:Array;
		private var parseXml:ParseXml;
		private var bean:ReceiveBean;
		private var socket:XMLSocket;
		
		/** �ͻ������ӳɹ�����ʧ�ܺ󣬻���ø��¼� **/
		public var onData:Function;
		public var onConnect:Function;
		public var clientMethod:Function;
		//public var onLost:Function;
		
		/**
		 * ˽�й�����,��ʼ�����
		 */
		public function ConnectJavaServer()
		{
			socket = new XMLSocket();
			listenters = new HashMap();
			id = 1;
			swtichXml = new SwitchXml();
			parseXml = new ParseXml();
			params = new Array();
			waitServer();
			serverMessage();
		}
		
		/**
		 * @return ConnectJavaServerʵ��
		 */
		public static function getConnectJavaServer():ConnectJavaServer
		{
			if(connec == undefined || connec == null)
			{
				connec = new ConnectJavaServer();
			}
			return connec;
		}
		
		/**
		 * ���ܷ���������������Ϣ�������з�����ִ�ж�Ӧ������
		 */
		private function serverMessage():void
		{
			var par = this;
			par.onData = function(msg:String)
			{
				//trace("msg:" + msg);
				var flag:Boolean = true;
				//������Ϣ�Ƿ����xml�ļ�����������Ӧ���¼�
				if(msg.indexOf("<send") == -1)
				{
					par["onConnect"](msg);
					flag = false;
				}
				if(flag)
				{
					par.bean = par.parseXml.parseXmlString(msg);
					var method:String = par.bean.getMethod();
					//trace("method:" + method);
					//trace("par.bean.getServerName():" + par.bean.getServerName());
					var server:String = par.bean.getServerName();
					//��ݷ���������������Ϣ�����ö�Ӧ����Ķ�Ӧ����
					//Ԥ�����������ɵĵ���
					try
					{
						if(server == "null")
						{
							//����д�ڷ��������
							par[method].apply(null,par.bean.getParams());
						}
						else
						{
							//trace("par.listenters.get(server)[method]:" + par.listenters.get(server)[method]);
							//par.listenters.get(server)[method]("soda");
							//�Զ���һ�����󱻷���������
							par.listenters.get(server)[method]
								.apply(par.listenters.get(server),par.bean.getParams());
						}
					}
					catch(error:Error)
					{
						trace(error.toString());
						trace("���÷���ʧ�ܡ���");
					}
				}
			};
		}
		
		/**
		 * ���û���һ�ε�½���ȴ������������Ϣ
		 */
		private function waitServer():void
		{
			var par = this;
			par.onConnect = function(success:Boolean)
			{
				if(success)
				{
					//�������б?Ϊ�գ����ʾ�û��������Ĳ���
					if(par.params[0] != undefined)
					{
						var len:Number = par.params.length;
						for(var i = 0; i< len; i++)
						{
							arguments[i + 3] = par.params[i];
						}
						//�Ѳ���ת����xml�ַ�
						var msg:String = par.swtichXml.switchXmlString("this",arguments);
						socket.send(msg + "\n");
					}
					else
					{
						socket.send("noting\n");
					}
				}
				else
				{
					//����ʧ��
					par.debug("connect fail");
				}
			};
		}
		
		/**
		 * ���з���˶�Ӧ�ķ���
		 */
		public function call(serverObj:String,serverMethod:String,redObject:Object):void
		{
			var par = this;
			var msg:String = par.swtichXml.switchXmlString("obj"+id,arguments);
			//���ؽ��ܶ���Ϊ��ʱ��������
			if(redObject != null)
			{
				//���￼�ǵ����ܵ����⣬���ԶԶ�����м�飬�Ƿ��Ѿ����ڣ������ڣ��򷵻���ֵ�ȵ�
				listenters.put("obj" + id,redObject);
				id++;
			}
			//debug(msg);
			socket.send(msg + "\n");
		}
		
		/**
		 * ���ӵ�������
		 */
		public function connect(url:String, port:Number):Boolean
		{
			var len = arguments.length;
			//�Ѳ�����ӵ���Ӧ��������
			for(var i = 2; i < len; i++)
			{				
				params.push(arguments[i]);
			}
			return socket.connect(url,port);
		}
		
		/**
		 * ���ò���
		 */
		public function setParams():void
		{
			var len = arguments.length;
			for(var i = 2; i < len; i++)
			{
				params.push(arguments[i]);
			}
		}
		
		public function addServerCall(key:String,obj:Object):void
		{
			listenters.put(key,obj);
		}
		
		/**
		 * ���������Ϣ
		 */
		private function debug(msg:String):void
		{
			trace("ConnecJavaServer:" + msg);
		}
		
		
	}
}