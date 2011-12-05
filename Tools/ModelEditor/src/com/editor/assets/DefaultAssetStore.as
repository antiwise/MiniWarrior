package com.editor.assets
{

	import away3d.entities.Mesh;
	import away3d.library.assets.IAsset;
	import away3d.loaders.parsers.data.DefaultBitmapData;
	import away3d.materials.BitmapMaterial;
	import away3d.materials.MaterialBase;
	import away3d.primitives.*;
	
	import com.editor.Get3D;
	
	import flash.display.BitmapData;
	import flash.geom.Point;
	import flash.utils.setTimeout;
	
	import org.osflash.signals.Signal;

	public class DefaultAssetStore
	{
		private var _material:MaterialBase;
		private var _model:Mesh; // todo

		private static var _instance:DefaultAssetStore;

		public var materialRequestedSignal:Signal;
		public var modelRequestedSignal:Signal;

		public function DefaultAssetStore() {

			_material = new BitmapMaterial();
			
			materialRequestedSignal = new Signal( MaterialBase );
			modelRequestedSignal = new Signal( Mesh );

			setTimeout( function():void {
				getHeadModel();

			}, 200 );
	
		}

		public static function get instance():DefaultAssetStore {
			if( !_instance )
				_instance = new DefaultAssetStore();
			return _instance;
		}

		// ---------------------------------------------------------------------
		// Models
		// ---------------------------------------------------------------------

		public function getLoadedModel( modelData:IAsset ):Mesh {
			if(_model)
				Get3D.instance.view.scene.removeChild(_model);
			_model = modelData as Mesh;
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
			return _model;
		}

		public function getHeadModel():Mesh {
			_material = new BitmapMaterial();
			_model = new Cube(_material);
			
			modelRequestedSignal.dispatch( _model );
			materialRequestedSignal.dispatch( _material );
			return _model;
		}
	}
}
