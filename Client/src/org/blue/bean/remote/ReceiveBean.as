package org.blue.bean.remote
{
	public class ReceiveBean
	{
		private var serverName:String;
		private var method:String;
		private var redName:String;
		private var redMethod:String;
		private var params:Array;
		
		//////////////////////////////////////////////////////////
		public function setServerName(serverName:String):void
		{
			this.serverName = serverName;
		}
		
		public function getServerName():String
		{
			return serverName;
		}
		
		public function setMethod(method:String):void
		{
			this.method = method;
		}
		
		public function getMethod():String
		{
			return method;
		}
		
		public function setRedName(redName:String):void
		{
			this.redName = redName;
		}
		
		public function getRedName():String
		{
			return redName;
		}
		
		public function setRedMethod(redMethod:String):void
		{
			this.redMethod = redMethod;
		}
		
		public function getRedMethod():String
		{
			return redMethod;
		}
		
		public function setParams(params:Array):void
		{
			this.params = params;
		}
		
		public function getParams():Array
		{
			return params;
		}
		//////////////////////////////////////////////////////////
		
		public function getString(index:Number):String
		{
			return String(params[index]);
		}
		
		public function getNumber(index:Number):Number
		{
			return Number(params[index]);
		}
		
		public function getBoolean(index:Number):Boolean
		{
			return Boolean(params[index]);
		} 
		
		
	}
}