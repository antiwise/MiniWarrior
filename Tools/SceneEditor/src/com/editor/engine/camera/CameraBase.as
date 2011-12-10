package com.editor.engine.camera
{
	import away3d.cameras.Camera3D;
	
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.geom.Vector3D;
	
	public class CameraBase
	{	
	
		protected var _camera : Camera3D;
				
		public function get camera() : Camera3D
		{
			return _camera;
		}

		public function CameraBase(cam : Camera3D) 
		{
			_camera = cam;
		}
		
		public function lookFlow() : void
		{
		}
		
		public function lookFront() : void
		{
		}
		
		public function lookRight() : void
		{
		}
		
		public function get target() : Vector3D
		{
			return new Vector3D;
		}
		
		public function set target(value : Vector3D) : void
		{
		}
		
		public function reset(target:Vector3D = null):void
		{
		}
		
		public function onKeyDown(event : KeyboardEvent) : void
		{
		}
		
		public function onKeyUp(event : KeyboardEvent) : void
		{
		}

		/**
		 * If dragging, update the target position's spherical coordinates
		 */
		public function mouseMove(x:Number,y:Number) : void
		{
		}
		
		/**
		 * Start dragging
		 */
		public function onMouseDown(x:Number,y:Number) : void
		{
		}
		
		/**
		 * Stop dragging
		 */
		public function onMouseUp(event : MouseEvent) : void
		{
		}
		
		/**
		 * Updates camera distance
		 */
		public function onMouseWheel(event:MouseEvent) : void
		{
		}
		
		public function update(delta : Number) : void
		{
		}
	}	
}