package com.editor.assets
{
	import flash.events.*;
	import flash.filesystem.File;
	import flash.net.FileFilter;
	import away3d.library.assets.IAsset;
	import away3d.loaders.Loader3D;
	import away3d.loaders.misc.AssetLoaderContext;
	import away3d.loaders.parsers.OBJParser;
	import away3d.events.LoaderEvent;
	import away3d.events.AssetEvent;
	import away3d.library.AssetLibrary;
	import away3d.library.assets.AssetType;
	
	import org.osflash.signals.Signal;

	import flash.net.URLRequest;
	
	public class MeshPrompt
	{
		private var _file:File;
		private var _loader3D:Loader3D;

		public var completeSignal:Signal;

		public function MeshPrompt() {

			try 
			{
				_file = new File();
				var fileFilter:FileFilter = new FileFilter( "Obj Files", "*.obj;" );
				_file.browseForOpen("Open", [fileFilter]);
				_file.addEventListener( Event.SELECT, selectHandler );
			}
			catch (error:Error)
			{
				trace("Failed:", error.message);
			}

			completeSignal = new Signal( IAsset );
		}

		private function selectHandler( event:Event ):void {
			AssetLibrary.enableParser(OBJParser);
			AssetLibrary.addEventListener(AssetEvent.ASSET_COMPLETE, completeHandler);
			AssetLibrary.load(new URLRequest(_file.nativePath));
		}

		private function completeHandler( event:AssetEvent ):void {
			completeSignal.dispatch( event.asset );
		}
	}
}