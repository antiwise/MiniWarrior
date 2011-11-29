package
{
	import flash.display.Sprite;
	
	//导入ConnectJavaServer类
	import org.blue.net.ConnectJavaServer;
	import org.blue.soda.util.IMap;
	
	public class MiniWarrior extends Sprite
	{
		public var netConnect:ConnectJavaServer = ConnectJavaServer.getConnectJavaServer();
		public var receice:Object = new Object();
		public function MiniWarrior()
		{
			//客户端连接成功或者失败后，会调用该事件
			netConnect.onConnect = function(msg:String):void
			{
				//trace("msg:" + msg);
				if(msg == "connect success")
				{
					trace("连接成功");
					//呼叫服务端方法
					callServerMethod();
				}
				else
				{
					trace("连接失败");
				}
			}
			
			
			receice.onResult = function(num:Number):void
			{
				trace("num:" + num);
			}
			//远程调用方法失败时响应该方法
			receice.onFault = function(msg:String):void
			{
				trace("fail:" + msg);
			}
			//连接服务器
			netConnect.connect("localhost",5000);
			
			
			//被服务器呼叫的方法
			netConnect.clientMethod = function(name:String,age:Number,obj:Object,arry:Array,map:IMap):void
			{
				trace("默认对象");
				trace("name：" + name);
				trace("age：" + age);
				trace("arry:" + arry);
				trace("obj[info]:" + obj["info"]);
				trace("obj[msg]:" + obj["msg"]);
				trace("obj[num]:" + obj["num"]);
				trace("map:" + map);
				var map2:IMap = IMap(map.get("map2"));
				trace("map2:" + map2);
				trace("map2.get(address):" + map2.get("address"));
				trace("map2.get(main):" + map2.get("main"));
			}
			//支持自定义对象的方法被服务器调用
			var clientObj:Object = new Object();
			//注册到网络连接类里去
			netConnect.addServerCall("client",clientObj);
			clientObj.clientMethod = function(name:String,age:Number,obj:Object,arry:Array,map:IMap):void
			{
				trace("自定义对象");
				trace("name：" + name);
				trace("age：" + age);
				trace("arry:" + arry);
				trace("obj[info]:" + obj["info"]);
				trace("obj[msg]:" + obj["msg"]);
				trace("obj[num]:" + obj["num"]);
				trace("map:" + map);
				var map2:IMap = IMap(map.get("map2"));
				trace("map2:" + map2);
				trace("map2.get(address):" + map2.get("address"));
				trace("map2.get(main):" + map2.get("main"));
			}
		}
		
		//呼叫服务器的方法
		public function callServerMethod(): void
		{
			//对象
			var obj:Object = new Object();
			obj.name = "objName";
			obj.num = 50;
			//数组
			var ary:Array = [1,4,7,16,98,100];
			netConnect.call("services","callParams",receice);
		}
	}
}