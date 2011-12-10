package com.editor.utils
{
	import flash.display.BitmapData;
	import flash.events.*;
	import flash.filesystem.File;
	import flash.net.FileFilter;
	import flash.net.URLLoader;
	import flash.net.URLLoaderDataFormat;
	import flash.net.URLRequest;
	import flash.utils.ByteArray;

	import away3d.loaders.parsers.utils.ParserUtil;
	
	import org.osflash.signals.Signal;
	
	public class ScenePrompt
	{
		private var _file:File;

		public var completeSignal:Signal;

		public function ScenePrompt() {

			try 
			{
				_file = new File();
				var fileFilter:FileFilter = new FileFilter( "Scene Files","*.xml" );

				_file.browseForOpen("Open", [fileFilter]);
				_file.addEventListener( Event.SELECT, selectHandler );
			}
			catch (error:Error)
			{
				trace("Failed:", error.message);
			}

			completeSignal = new Signal( String );
		}

		private function selectHandler( event:Event ):void {
			var urlLoader:URLLoader = new URLLoader();
			urlLoader.dataFormat = URLLoaderDataFormat.TEXT;
			urlLoader.addEventListener(Event.COMPLETE, completeHandler);
			urlLoader.load(new URLRequest(_file.nativePath));
		}

		private function completeHandler( event : Event):void {
			var urlLoader : URLLoader = URLLoader(event.currentTarget);
			urlLoader.removeEventListener(Event.COMPLETE, completeHandler);

			completeSignal.dispatch( ParserUtil.toString(urlLoader.data) );
		}

	 }
}