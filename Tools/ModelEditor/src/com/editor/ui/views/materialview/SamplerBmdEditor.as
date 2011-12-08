package com.editor.ui.views.materialview
{
	import com.editor.utils.*;
	import flash.display.Bitmap;
	import flash.display.BitmapData;

	import org.aswing.BorderLayout;
	import org.aswing.JButton;
	import org.aswing.JPanel;
	import org.aswing.event.AWEvent;
	import org.aswing.geom.IntDimension;
	import org.osflash.signals.Signal;

	public class SamplerBmdEditor extends JPanel
	{
		private var _bitmap:Bitmap;
		public var _sampler:BitmapData;
		public var imageChangedSignal:Signal;

		public function SamplerBmdEditor( sampler:BitmapData = null ) {
			
			var changeBtn:JButton = new JButton( "change image" );
			changeBtn.addEventListener( AWEvent.ACT, changeBtnClickedHandler );
			_bitmap = new Bitmap( sampler );
			
			initSampler();
			// change btn
			_bitmap.bitmapData = sampler;
			var bmdHolder:JPanel = new JPanel();
			bmdHolder.setSize( new IntDimension( 256, 256 ) );
			setLayout( new BorderLayout() );
			bmdHolder.addChild( _bitmap );
			append( bmdHolder, BorderLayout.CENTER );
			append( changeBtn, BorderLayout.NORTH );			
		}
		public function initSampler( sampler:BitmapData = null ):void {
			_sampler = sampler;
			// image
			if( _sampler == null ){
				_sampler = new BitmapData( 256, 256, false, 0 );
				_sampler.perlinNoise( 256, 256, 8, 0, false, true, 7, true );
			}
			imageChangedSignal = new Signal( BitmapData );
			_bitmap.bitmapData = sampler;
			fitBitmap();
			
		}
		private function changeBtnClickedHandler( event:AWEvent ):void {

			var prompt:BitmapDataPrompt = new BitmapDataPrompt();
			prompt.completeSignal.addOnce( onBitmapDataFetched );

		}

		private function onBitmapDataFetched( bmd:BitmapData ):void {
			_bitmap.bitmapData = _sampler = bmd;
			fitBitmap();
		}

		private function fitBitmap():void {
			imageChangedSignal.dispatch( _sampler);
		}
	}
}
