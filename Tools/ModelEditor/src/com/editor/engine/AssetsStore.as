package com.editor.engine 
{

	import away3d.entities.Mesh;
	import away3d.events.AssetEvent;
	import away3d.events.LoaderEvent;
	import away3d.library.AssetLibrary;
	import away3d.library.assets.AssetType;
	import away3d.library.assets.IAsset;
	import away3d.loaders.Loader3D;
	import away3d.loaders.misc.AssetLoaderContext;
	import away3d.loaders.parsers.OBJParser;
	import away3d.loaders.parsers.data.DefaultBitmapData;
	import away3d.materials.BitmapMaterial;
	import away3d.materials.MaterialBase;
	import away3d.primitives.Sphere;
	
	import com.editor.Get3D;
	
	import flash.display.BitmapData;
	import flash.geom.Point;
	import flash.net.URLRequest;
	import flash.utils.setTimeout;
	
	import org.osflash.signals.Signal;

	public class AssetsStore
	{
		private var _material:MaterialBase;
		private var _model:Mesh; // todo
		private var _helperLoader:Loader3D; // todo

		private static var _instance:AssetsStore;

		public var materialRequestedSignal:Signal;
		public var modelRequestedSignal:Signal;

		public function AssetsStore() {

			_material = new BitmapMaterial();
			
			materialRequestedSignal = new Signal( MaterialBase );
			modelRequestedSignal = new Signal( Mesh );

			setTimeout( function():void {
				getDefaultModel();
			}, 200 );
	
		}

		public static function get instance():AssetsStore {
			if( !_instance )
				_instance = new AssetsStore();
			return _instance;
		}

		public function get model():Mesh {
			return _model;
		}
		
		// ---------------------------------------------------------------------
		// Models
		// ---------------------------------------------------------------------
		public function selectModel( model:Mesh ):void {
			_model = model;
			_material = _model.material;
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
		}
		public function getLoadedModel( modelData:IAsset ):Mesh {
			//if(_model)
				//Get3D.instance.renderSystem.removeChild(_model);
			_model = modelData as Mesh;
			_material = new BitmapMaterial();
			_material.lights = Get3D.instance.renderSystem.lights;
			BitmapMaterial(_material).gloss = 20;
			BitmapMaterial(_material).specular = 2;
			BitmapMaterial(_material).ambientColor = 0x202030;
			BitmapMaterial(_material).ambient = 1;
			
			_model.material = _material;
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );

			return _model;
		}

		public function getHeadModel():Mesh {
			_material = new BitmapMaterial();
			_material.lights = Get3D.instance.renderSystem.lights;
			_model = new Sphere(_material,10,10,10);
			
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
			return _model;
		}
		
		private function getDefaultModel():void {
			AssetLibrary.enableParser(OBJParser);
			AssetLibrary.addEventListener(AssetEvent.ASSET_COMPLETE, completeHandler);
			AssetLibrary.load(new URLRequest("E:\\Mywork\\MiniWarrior\\Tools\\ModelEditor\\assets\\model\\head.obj"));
		}
		
		public function getHelperModel():void {
			Loader3D.enableParser(OBJParser);
			
			_helperLoader = new Loader3D();
			_helperLoader.addEventListener(LoaderEvent.RESOURCE_COMPLETE, completeHelperHandler);
			_helperLoader.load(new URLRequest("E:\\Mywork\\MiniWarrior\\Tools\\ModelEditor\\assets\\model\\axis.obj"),new AssetLoaderContext(false));
			Get3D.instance.renderSystem.addChild(_helperLoader);
		}
		private function completeHelperHandler( event:LoaderEvent ):void {
			Get3D.instance.renderSystem.selectHelper.addHelper(_helperLoader);			
		}
		
		private function completeHandler( event:AssetEvent ):void {
			getLoadedModel(event.asset);
		}
	}
}
