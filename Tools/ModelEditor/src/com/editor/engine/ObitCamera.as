package com.editor.engine
{
    import away3d.cameras.Camera3D;
    
    import flash.display.Stage;
    import flash.events.Event;
    import flash.events.MouseEvent;
    import flash.geom.Vector3D;

	import com.editor.ModelEditorConstants;
	/**
	 * Makes the camera rotate around a target on drag. Hardly a proper scalable implementation, but this is just to support the simple demo.
	 *
	 * @author David Lenaerts
	 */
	public class ObitCamera
	{
		private var _target : Vector3D;
		private var _camera : Camera3D;
		private var _radius : Number = 100;
		private var _speed : Number = 0.01;
		private var _wheelspeed : Number = 0.5;
		private var _dragSmoothing : Number = 0.1;
		private var _drag : Boolean;
		private var _referenceX : Number = 0;
		private var _referenceY : Number = 0;
		private var _xRad : Number = 0;
		private var _yRad : Number = 0;
		private var _targetXRad : Number = 0;
		private var _targetYRad : Number = .5;
        private var _targetRadius : Number = 100;

		/**
		 * Creates a HoverDragController object
		 * @param camera The camera to control
		 * @param stage The stage that will be receiving mouse events
		 */
		public function ObitCamera(camera : Camera3D)
		{
			_target = new Vector3D();
			_camera = camera;
		}

		/**
		 * The distance of the camera to the target
		 */
		public function get radius() : Number
		{
			return _targetRadius;
		}

		public function set radius(value : Number) : void
		{
			_targetRadius = value;
		}

		/**
		 * The amount by which the camera be moved relative to the mouse movement
		 */
		public function get speed() : Number
		{
			return _speed;
		}

		public function set speed(value : Number) : void
		{
			_speed = value;
		}

        /**
		 * The center of attention for the camera
		 */
		public function get target() : Vector3D
		{
			return _target;
		}

		public function set target(value : Vector3D) : void
		{
			_target = value;
		}

		/**
		 * Update cam movement towards its target position
		 */
		public function update() : void
		{
			 _radius = _radius + (_targetRadius - _radius)*_dragSmoothing;
			_xRad = _xRad + (_targetXRad - _xRad)*_dragSmoothing;
			_yRad = _yRad + (_targetYRad - _yRad)*_dragSmoothing;

			// simple spherical position based on spherical coordinates
			var cy : Number = Math.cos(_yRad)*_radius;
			_camera.x = _target.x - Math.sin(_xRad)*cy;
			_camera.y = _target.y + Math.sin(_yRad)*_radius;
			_camera.z = _target.z - Math.cos(_xRad)*cy;
			_camera.lookAt(_target);
		}

		/**
		 * If dragging, update the target position's spherical coordinates
		 */
		public function mouseMove(x:Number,y:Number) : void
		{
			if (!_drag) return;
			var mouseX : Number = x;
			var mouseY : Number = y;
			var dx : Number = mouseX - _referenceX;
			var dy : Number = mouseY - _referenceY;
			var bound : Number = Math.PI * .5 - .05;

			_referenceX = mouseX;
			_referenceY = mouseY;
			_targetXRad += dx * _speed;
			_targetYRad += dy * _speed;
			if (_targetYRad > bound) _targetYRad = bound;
			else if (_targetYRad < -bound) _targetYRad = -bound;
		}

		/**
		 * Start dragging
		 */
		public function onMouseDown(x:Number,y:Number) : void
		{
			_drag = true;
			_referenceX = x;
			_referenceY = y;
		}

		/**
		 * Stop dragging
		 */
		public function onMouseUp(event : MouseEvent) : void
		{
			_drag = false;
		}

        /**
         * Updates camera distance
         */
		public function onMouseWheel(event:MouseEvent) : void
        {
            _targetRadius -= event.delta*_wheelspeed;
        }


	}
}
