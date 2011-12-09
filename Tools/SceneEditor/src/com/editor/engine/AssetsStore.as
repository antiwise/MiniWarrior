package com.editor.engine 
{

	import away3d.animators.VertexAnimator;
	import away3d.animators.data.SkeletonAnimation;
	import away3d.animators.data.VertexAnimation;
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
	import com.editor.engine.vo.SceneGeometryVO;
	import com.editor.utils.Log;
	
	import flash.display.BitmapData;
	import flash.geom.Point;
	import flash.net.URLRequest;
	import flash.utils.setTimeout;
	
	import org.osflash.signals.Signal;

	public class AssetsStore
	{
		private var _material:MaterialBase;
		private var _model:SceneGeometryVO; // todo
		private var _animController:VertexAnimator;
		private var _helperLoader:Loader3D; // todo

		private static var _instance:AssetsStore;

		public var materialRequestedSignal:Signal;
		public var modelRequestedSignal:Signal;
		public var animatorRequestedSignal:Signal;

		public function AssetsStore() {

			_material = new BitmapMaterial();
			materialRequestedSignal = new Signal( MaterialBase );
			modelRequestedSignal = new Signal( SceneGeometryVO );
			animatorRequestedSignal = new Signal( VertexAnimator );

			setTimeout( function():void {
				//getDefaultModel();
			}, 200 );
	
		}

		public static function get instance():AssetsStore {
			if( !_instance )
				_instance = new AssetsStore();
			return _instance;
		}

		public function get model():SceneGeometryVO {
			return _model;
		}
		
		// ---------------------------------------------------------------------
		// Models
		// ---------------------------------------------------------------------
		public function selectModel( model:SceneGeometryVO ):void {

			if(_model){
				_model.mesh.showBounds = false;
			}
			if(_model && _model.mesh.animation){
				if(_model.mesh.animation is VertexAnimation){
					
				}else if(_model.mesh.animation is SkeletonAnimation){					
				}
			}
			_model = model;
			_model.mesh.showBounds = true;
			_material = _model.mesh.material;
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
			
			Log.info("Select Model:",_model.name);
		}
		public function getLoadedModel( asset:IAsset ):SceneGeometryVO {
			
			_model = new SceneGeometryVO();
			
			if (asset.assetType == AssetType.MESH) {	
				_model.mesh = asset as Mesh;
				completeModel();
			}
			else if (asset.assetType == AssetType.ANIMATOR) {
				_animController = VertexAnimator(asset);
				animatorRequestedSignal.dispatch(_animController);
			}
			
			return _model;
		}
		private function completeModel():void {
						
			_material = new BitmapMaterial();
			_material.lights = Get3D.instance.renderSystem.lights;
			BitmapMaterial(_material).gloss = 20;
			BitmapMaterial(_material).specular = 2;
			BitmapMaterial(_material).ambientColor = 0x202030;
			BitmapMaterial(_material).ambient = 1;			
			_model.material = _material;
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
		}
		
		public function getHeadModel():SceneGeometryVO {
			_material = new BitmapMaterial();
			_material.lights = Get3D.instance.renderSystem.lights;
			_model.mesh = new Sphere(_material,10,10,10);
			
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
			return _model;
		}
		
		private function getDefaultModel():void {
			AssetLibrary.enableParser(OBJParser);
			AssetLibrary.addEventListener(AssetEvent.ASSET_COMPLETE, completeHandler);
			AssetLibrary.load(new URLRequest("E:\\MiniWarrior\\Tools\\SceneEditor\\assets\\model\\head.obj"));
		}
		
		public function getHelperModel():void {
			Loader3D.enableParser(OBJParser);
			
			_helperLoader = new Loader3D();
			_helperLoader.addEventListener(LoaderEvent.RESOURCE_COMPLETE, completeHelperHandler);
			_helperLoader.load(new URLRequest("E:\\MiniWarrior\\Tools\\SceneEditor\\assets\\model\\axis.obj"),new AssetLoaderContext(false));
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
