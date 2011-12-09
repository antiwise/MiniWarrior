package com.editor.utils
{

	import com.editor.components.JRegisterPopUp;

	import flash.display.Stage;
	import flash.utils.Dictionary;

	import org.aswing.event.FrameEvent;
	import org.aswing.geom.IntPoint;

	public class RegisterPopUpManager
	{
		private var _popUps:Dictionary;

		public function RegisterPopUpManager() {

			_popUps = new Dictionary();
		}

		public function requestPopUp( register:AGALRegister, popUpClass:Class ):JRegisterPopUp {

			if( !_popUps[ register ] ) {

				var popUp:JRegisterPopUp = new popUpClass( register );
				popUp.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler, false, 0, true );

				if( popUp.packs )
					popUp.pack();
					popUp.show();

				_popUps[ register ] = popUp;

				return popUp;
			}
			return null;
		}

		private function popUpClosedHandler( event:FrameEvent ):void {
			_popUps[ JRegisterPopUp( event.target ).register ] = null;
		}
	}
}
