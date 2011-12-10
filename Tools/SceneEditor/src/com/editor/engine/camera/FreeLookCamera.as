package com.editor.engine.camera
{
	import away3d.cameras.Camera3D;
	
	import flash.display.Stage;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.geom.Vector3D;
	import flash.ui.Keyboard;
	
	public class FreeLookCamera extends CameraBase
	{		
		private var _dragSpeed : Number = .005;
		private var _smoothing : Number = .3;
		private var _drag : Boolean;
		private var _referenceX : Number = 0;
		private var _referenceY : Number = 0;
		private var _xRad : Number = 0;
		private var _yRad : Number = 0;
		private var _targetXRad : Number = 0;
		private var _targetYRad : Number = .5;
		private var _moveSpeed : Number = 1;
		private var _xSpeed : Number = 0;
		private var _zSpeed : Number = 0;
		private var _targetXSpeed : Number = 0;
		private var _targetZSpeed : Number = 0;
		private var _runMult : Number = 1;

		/**
		 * Creates a FreeLookCamera object
		 * @param camera The camera to control
		 * @param stage The stage that will be receiving mouse events
		 */
		public function FreeLookCamera(camera : Camera3D)
		{
			super(camera);
		}

		/**
		 * Amount of "lag" the camera has
		 */
		public function get smoothing() : Number
		{
			return _smoothing;
		}
		
		public function set smoothing(value : Number) : void
		{
			_smoothing = value;
		}
		
		/**
		 * The speed by which the camera rotates
		 */
		public function get dragSpeed() : Number
		{
			return _dragSpeed;
		}
		
		public function set dragSpeed(value : Number) : void
		{
			_dragSpeed = value;
		}
		
		/**
		 * The speed by which the camera moves
		 */
		public function get moveSpeed() : Number
		{
			return _moveSpeed;
		}
		
		public function set moveSpeed(value : Number) : void
		{
			_moveSpeed = value;
		}
		override public function lookFlow() : void
		{
			_camera.x = 0;
			_camera.y = 100;
			_camera.z = 0;
			
			_xRad = 0;
			_yRad = 0;
			_zSpeed = 0;
			_xSpeed = 0;
			_targetXSpeed = 0;
			_targetZSpeed = 0;
			_targetYRad = Math.PI * 0.5;
			_targetXRad = 0;
			_camera.lookAt(new Vector3D(0,0,0));
		}
		
		override public function lookFront() : void
		{
			_camera.x = -100;
			_camera.y = 0;
			_camera.z = 0;
			
			_xRad = 0;
			_yRad = 0;
			_zSpeed = 0;
			_xSpeed = 0;
			_targetXSpeed = 0;
			_targetZSpeed = 0;
			_targetYRad = 0;
			_targetXRad = Math.PI * 0.5;
			_camera.lookAt(new Vector3D(0,0,0));
			
		}
		
		override public function lookRight() : void
		{
			_camera.x = 0;
			_camera.y = 0;
			_camera.z = -100;
			
			_xRad = 0;
			_yRad = 0;
			_targetXSpeed = 0;
			_targetZSpeed = 0;
			_zSpeed = 0;
			_xSpeed = 0;
			_targetYRad = 0;
			_targetXRad = 0;
			_camera.lookAt(new Vector3D(0,0,0));

		}
		
		override public function reset(target:Vector3D = null):void
		{
			var raduis : Number = _camera.position.subtract(target).length;
			var cy : Number = Math.cos(_yRad)*raduis;
			_camera.x = target.x - Math.sin(_xRad)*cy;
			_camera.y = target.y + raduis * 0.5;
			_camera.z = target.z - Math.cos(_xRad)*cy;
					
			if(target){
				_camera.lookAt(target);
			}
			else{
				_camera.lookAt(new Vector3D(0,0,0));
			}
		}
		/**
		 * Update cam movement towards its target position
		 */
		override public function update(delta : Number) : void
		{
			_xSpeed += (_targetXSpeed*_runMult - _xSpeed)*_smoothing;
			_zSpeed += (_targetZSpeed*_runMult - _zSpeed)*_smoothing;
			_xRad += (_targetXRad - _xRad)*_smoothing;
			_yRad += (_targetYRad - _yRad)*_smoothing;
			
			_camera.rotationY = _xRad*180/Math.PI;
			_camera.rotationX = _yRad*180/Math.PI;
			_camera.moveForward(_zSpeed);
			_camera.moveRight(_xSpeed);		
		}
		
		override public function onKeyDown(event : KeyboardEvent) : void
		{
			switch (event.keyCode) {
				case Keyboard.UP:
				case Keyboard.W:
					_targetZSpeed = _moveSpeed;
					break;
				case Keyboard.DOWN:
				case Keyboard.S:
					_targetZSpeed = -_moveSpeed;
					break;
				case Keyboard.LEFT:
				case Keyboard.A:
					_targetXSpeed = -_moveSpeed;
					break;
				case Keyboard.RIGHT:
				case Keyboard.D:
					_targetXSpeed = _moveSpeed;
					break;
				case Keyboard.SHIFT:
					_runMult = 2;
					break;
			}
		}
		
		override public function onKeyUp(event : KeyboardEvent) : void
		{
			switch (event.keyCode) {
				case Keyboard.UP:
				case Keyboard.DOWN:
				case Keyboard.W:
				case Keyboard.S:
					_targetZSpeed = 0;
					break;
				case Keyboard.LEFT:
				case Keyboard.RIGHT:
				case Keyboard.A:
				case Keyboard.D:
					_targetXSpeed = 0;
					break;
				case Keyboard.SHIFT:
					_runMult = 1;
					break;
			}
		}
		
		/**
		 * If dragging, update the target position's spherical coordinates
		 */
		override public function mouseMove(x:Number,y:Number) : void
		{
			if (_drag) {
				var mouseX : Number = x;
				var mouseY : Number = y;
				var dx : Number = mouseX - _referenceX;
				var dy : Number = mouseY - _referenceY;
				var bound : Number = Math.PI * .5 - .05;
				
				_referenceX = mouseX;
				_referenceY = mouseY;
				_targetXRad += dx * _dragSpeed;
				_targetYRad -= dy * _dragSpeed;
				if (_targetYRad > bound) _targetYRad = bound;
				else if (_targetYRad < -bound) _targetYRad = -bound;
			}			
		}
		
		/**
		 * Start dragging
		 */
		override public function onMouseDown(x:Number,y:Number) : void
		{
			_drag = true;
			_referenceX = x;
			_referenceY = y;
		}
		
		/**
		 * Stop dragging
		 */
		override public function onMouseUp(event : MouseEvent) : void
		{
			_drag = false;
		}

	}	
}