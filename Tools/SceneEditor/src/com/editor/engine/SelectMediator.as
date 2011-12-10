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
	
	import com.editor.engine.vo.SceneGeometryVO;
	import com.editor.utils.Log;
	
	import flash.display.BitmapData;
	import flash.geom.Point;
	import flash.net.URLRequest;
	import flash.utils.setTimeout;
	import flash.events.EventDispatcher;

	import org.osflash.signals.Signal;

	public class SelectMediator extends EventDispatcher
	{
		private var _material:MaterialBase;
		private var _model:SceneGeometryVO; // todo
		private var _animator:VertexAnimator;

		public var materialRequestedSignal:Signal;
		public var modelRequestedSignal:Signal;
		public var animatorRequestedSignal:Signal;

		public function SelectMediator() {

			_material = new BitmapMaterial();
			materialRequestedSignal = new Signal( MaterialBase );
			modelRequestedSignal = new Signal( SceneGeometryVO );
			animatorRequestedSignal = new Signal( VertexAnimator );
		}

		public function get model():SceneGeometryVO {
			return _model;
		}
		
		// ---------------------------------------------------------------------
		// Models
		// ---------------------------------------------------------------------
		public function importSceneGeometryVO ( gvo : SceneGeometryVO ) : void
		{
			dispatchSignal(gvo);
			
			Log.info("Import Model:",_model.id);
		}
		public function selectModel( gvo:SceneGeometryVO ):void {

			dispatchSignal(gvo);
			
			Log.info("Select Model:",_model.id);
		}	
		
		private function dispatchSignal( gvo:SceneGeometryVO ):void{
			_model = gvo;
			if(_model && _model.mesh){
				_model.mesh.showBounds = !_model.mesh.showBounds;
				_material = _model.mesh.material;
				modelRequestedSignal.dispatch( _model );
				materialRequestedSignal.dispatch( _material );
			}
			if(_model && _model.animator){
				_animator = _model.animator;
				animatorRequestedSignal.dispatch( _animator );
			}
		}

	}
}
