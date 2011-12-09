package com.editor.components
{
	import away3d.entities.Mesh;
	
	import flash.geom.Point;

	import org.aswing.*;
	import org.aswing.event.InteractiveEvent;

	public class JLabeledSlider extends JPanel
	{
		private var _range:Number;
		private var _min:Number;
		private var _model:*;
		private var _target:String;
		private var _slider:JSlider;
		private var _valueStepper:JStepper;

		public function JLabeledSlider( title:String, target:String, model:*, range:Point ) {

			super();

			_target = target;
			_model = model;

			_range = range.y - range.x;
			_min = range.x;

			_slider = new JSlider();
			_valueStepper = new JStepper();
			
			_valueStepper.setModel( new DefaultBoundedRangeModel( _model[ _target ], 0, range.x, range.y ) );
			
			append( _slider );
			append( _valueStepper );
			append( new JLabel( title ) );	

			updateSliderFromValue();

			_slider.addEventListener( InteractiveEvent.STATE_CHANGED, sliderStateChangedHandler );
			_valueStepper.addEventListener( InteractiveEvent.STATE_CHANGED, stepperStateChangedHandler );
		}

		private function sliderStateChangedHandler( event:InteractiveEvent ):void {
			var real:Number = ( _slider.getValue() / 100 ) * _range + _min;
			_valueStepper.setValue( real );
			updateValueFromSlider();
		}
		
		private function stepperStateChangedHandler( event:InteractiveEvent ):void {
			var percent:Number = ( 100 / _range ) * ( _valueStepper.getValue() - _min );
			_slider.setValue( percent );
			updateValueFromSlider();
		}

		private function updateSliderFromValue():void {
			var real:Number = _model[ _target ];
			var percent:Number = ( 100 / _range ) * ( real - _min );
			_slider.setValue( percent );
			_valueStepper.setValue( real );
		}

		private function updateValueFromSlider():void {
			var real:Number = _valueStepper.getValue();		
			_model[ _target ] = real;
		}
	}
}
