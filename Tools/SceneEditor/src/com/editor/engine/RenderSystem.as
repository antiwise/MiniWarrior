package com.editor.engine
{
	import away3d.containers.ObjectContainer3D;
	import away3d.containers.Scene3D;
	import away3d.containers.View3D;
	import away3d.debug.AwayStats;
	import away3d.entities.Mesh;
	import away3d.events.MouseEvent3D;
	import away3d.lights.DirectionalLight;
	import away3d.lights.LightBase;
	import away3d.lights.PointLight;
	import away3d.loaders.Loader3D;
	import away3d.materials.ColorMaterial;
	import away3d.materials.BitmapMaterial;
	import away3d.primitives.WireframePlane;
	import away3d.tools.MeshDebugger;
	
	import com.editor.Get3D;
	import com.editor.SceneEditorConstants;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.utils.setTimeout;
	
	public class RenderSystem
	{		
		private static var selectTermpColor : uint = 0xff000000;
		private var selectOrgColor : uint;
		private var _view : View3D;
		private var _stats :AwayStats;
		private var _camController : ObitCamera;
		private var _lights : Array;
		private var _meshDebugger:MeshDebugger;
		private var _selectHelper:SelectHelper;
		
		public function RenderSystem() {
			init();
		}
		
		protected function init():void{
			_view = new View3D();
			view.camera.lens.far = 10000;
			view.camera.lens.near = 0.01;
			view.antiAlias = 4;
			_camController = new ObitCamera(view.camera);
			_stats = new AwayStats(view);
			
			addChild(new WireframePlane(
				SceneEditorConstants.wireplane_size,
				SceneEditorConstants.wireplane_size,
				SceneEditorConstants.wireplane_segmentsize,
				SceneEditorConstants.wireplane_segmentsize,
				0x555555,1,"xz"));
			
			var light:DirectionalLight = new DirectionalLight();
			light.color = 0xffffff;			
			_lights = [light];
			
			_meshDebugger = new MeshDebugger();
			_meshDebugger.lengthNormals = 1;
			_meshDebugger.lengthVertexNormals = 1;
			_meshDebugger.lengthTangents = 1;
			
			_selectHelper = new SelectHelper;			
			
			addChild(light);
			
			Get3D.instance.renderSystem = this;
			
			//AssetsStore.instance.getHelperModel();
		}
			
		
		public function get view():View3D
		{
			return _view;
		}
		
		public function get scene():Scene3D
		{
			return _view.scene;
		}
		
		public function get stats():AwayStats
		{
			return _stats;
		}
		
		public function get camController():ObitCamera
		{
			return _camController;
		}
		
		public function get lights():Array
		{
			return _lights;
		}
		
		public function get meshDebugger():MeshDebugger
		{
			return _meshDebugger;
		}
		
		public function get selectHelper():SelectHelper
		{
			return _selectHelper;
		}
		
		public function setViewSize(x:Number,y:Number):void
		{
			if( x > 0 && y > 0 ) {
				_view.width = x;
				_view.height = y;
			}
		}
		/**
		 * Removes a child from the scene's root.
		 * @param child The child to be removed from the scene.
		 */
		public function removeChild(child : ObjectContainer3D) : void
		{
			scene.removeChild(child);
		}

		public function addChild(child : ObjectContainer3D) : ObjectContainer3D
		{
			if(child is Mesh){
				var mesh:Mesh = Mesh(child);
				mesh.mouseEnabled = true;
				mesh.mouseDetails = true;
				mesh.addEventListener(MouseEvent3D.MOUSE_DOWN, onMouseDown);
				mesh.addEventListener(MouseEvent3D.MOUSE_UP,  onMouseUp);
			}
			return scene.addChild(child);
		}
		
		private function onMouseDown(event : MouseEvent3D) : void
		{
			var mesh : Mesh = Mesh(event.target);
			//selectOrgColor = BitmapMaterial(mesh.material).ambientColor;
			//BitmapMaterial(mesh.material).ambientColor = selectTermpColor;
			AssetsStore.instance.selectModel(mesh);
		}

		private function onMouseUp(event : MouseEvent3D) : void
		{
			var mesh : Mesh = Mesh(event.target);
			//BitmapMaterial(mesh.material).ambientColor = selectOrgColor;
		}
		
	}
}