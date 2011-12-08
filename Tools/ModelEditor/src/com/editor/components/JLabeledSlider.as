package com.editor.components
{
	import away3d.entities.Mesh;
	
	import flash.geom.Point;

	import org.aswing.JLabel;
	import org.aswing.JPanel;
	import org.aswing.JSlider;
	import org.aswing.JStepper;
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

			append( new JLabel( title ) );			
			append( _slider );
			append( _valueStepper );

			updateSliderFromValue();

			_slider.addEventListener( InteractiveEvent.STATE_CHANGED, sliderStateChangedHandler );
			_valueStepper.addEventListener( InteractiveEvent.STATE_CHANGED, stepperStateChangedHandler );
		}

		private function sliderStateChangedHandler( event:InteractiveEvent ):void {
			_valueStepper.setValue( _slider.getValue() );
			updateValueFromSlider();
		}
		
		private function stepperStateChangedHandler( event:InteractiveEvent ):void {
			_slider.setValue( _valueStepper.getValue() );
			updateValueFromSlider();
		}

		private function updateSliderFromValue():void {
			var real:Number = _model[ _target ];
			var percent:int = ( 100 / _range ) * ( real - _min );
			_slider.setValue( real );
			_valueStepper.setValue( real );
		}

		private function updateValueFromSlider():void {
			var real:Number = ( _slider.getValue() / 100 ) * _range + _min;
			_model[ _target ] = real;
		}
	}
}
