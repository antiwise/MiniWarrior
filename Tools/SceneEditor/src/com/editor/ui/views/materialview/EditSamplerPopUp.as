package com.editor.ui.views.materialview
{
	import flash.display.BitmapData;
	
	import com.editor.components.JRegisterPopUp;
	import com.editor.utils.*;
	
	import org.aswing.JFrame;
	import org.aswing.JCheckBox;
	import org.aswing.event.AWEvent;
	import org.aswing.geom.IntDimension;
	import org.osflash.signals.Signal;

	public class EditSamplerPopUp extends JFrame
	{
		public var imageChangedSignal:Signal;
		
		public function EditSamplerPopUp( sampler:BitmapData ) {
			super( null,"sampler" );	
			
			imageChangedSignal = new Signal( BitmapData );
			var editor:SamplerBmdEditor = new SamplerBmdEditor( sampler );
					
			editor.imageChangedSignal.add( onEditorImageChanged );
			setContentPane( editor );
		
			onEditorImageChanged(editor._sampler);
			
		//	setResizable( false );
			
			pack();
			show();
		}

		private function mipChkChangedHandler( event:AWEvent ):void {
		}

		private function onEditorImageChanged( image:BitmapData):void {
			setSize( new IntDimension( image.width, image.height+50 ) );
			imageChangedSignal.dispatch( image);
		}
	}
}
