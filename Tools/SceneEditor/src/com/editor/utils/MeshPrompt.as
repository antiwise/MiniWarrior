package com.editor.utils
{
	import away3d.events.AssetEvent;
	import away3d.events.LoaderEvent;
	import away3d.library.AssetLibrary;
	import away3d.library.assets.AssetType;
	import away3d.library.assets.IAsset;
	import away3d.loaders.Loader3D;
	import away3d.loaders.misc.AssetLoaderContext;
	import away3d.loaders.parsers.MD2Parser;
	import away3d.loaders.parsers.OBJParser;
	import away3d.entities.Mesh;
	
	import com.editor.engine.vo.SceneGeometryVO;
	
	import flash.events.*;
	import flash.filesystem.File;
	import flash.net.FileFilter;
	import flash.net.URLRequest;
	
	import org.osflash.signals.Signal;
	
	public class MeshPrompt
	{
		private var _file:File;
		private var _loader3D:Loader3D;

		public var completeSignal:Signal;

		public function MeshPrompt() {

			try 
			{
				_file = new File();
				var fileFilter:FileFilter = new FileFilter( "Mesh Files *.obj,*.md2,*.md5mesh ", "*.obj;*.md2;*.md5mesh;" );

				_file.browseForOpen("Open", [fileFilter]);
				_file.addEventListener( Event.SELECT, selectHandler );
			}
			catch (error:Error)
			{
				trace("Failed:", error.message);
			}

			completeSignal = new Signal( SceneGeometryVO );
		}
		
		private function getExT():String {
			return  _file.nativePath.substring( 
				_file.nativePath.lastIndexOf(".")+1,
				_file.nativePath.length
			);
		}
		
		private function selectHandler( event:Event ):void {
			switch(getExT()){
				case "obj":
					loadOBJ();
					break;
				case "md2":
					loadMD2();
					break;
				default:
					break;
			}
		}
		
		private function loadOBJ():void {
			AssetLibrary.enableParser(OBJParser);
			AssetLibrary.addEventListener(AssetEvent.ASSET_COMPLETE, completeHandler);
			AssetLibrary.load(new URLRequest(_file.nativePath));		
		}
		private function loadMD2():void {
			AssetLibrary.enableParser(MD2Parser);
			AssetLibrary.addEventListener(AssetEvent.ASSET_COMPLETE, completeHandler);
			AssetLibrary.load(new URLRequest(_file.nativePath));		
		}
		private function completeHandler( event:AssetEvent ):void {
			var vo:SceneGeometryVO = new SceneGeometryVO;
			vo.mesh = event.asset as Mesh;
			vo.assetFile = _file.nativePath;
			vo.geometryType = "obj";
			vo.materialType = "BitmapMaterial";
			completeSignal.dispatch( vo);
		}
	}
}