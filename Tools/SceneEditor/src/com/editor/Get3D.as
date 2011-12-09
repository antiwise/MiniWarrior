package com.editor
{
	import com.editor.engine.RenderSystem;
	/*
	A singleton class that holds global elements of the engine.
	*/
	public class Get3D
	{
		private static var _instance:Get3D;
		
		private var _renderSystem:RenderSystem;

		public function Get3D()
		{
		}
		
		public static function get instance():Get3D
		{
			if(!_instance)
				_instance = new Get3D();
			return _instance;
		}
		
		
		public function get renderSystem():RenderSystem
		{
			return _renderSystem;
		}
		
		public function set renderSystem(rs:RenderSystem):void
		{
			_renderSystem = rs;
		}
	}
}
