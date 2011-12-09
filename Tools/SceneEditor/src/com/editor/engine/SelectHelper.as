package com.editor.engine
{
	import away3d.containers.Scene3D;
	import away3d.containers.View3D;
	import away3d.debug.AwayStats;
	import away3d.entities.Mesh;
	import away3d.library.assets.IAsset;
	import away3d.lights.DirectionalLight;
	import away3d.lights.LightBase;
	import away3d.lights.PointLight;
	import away3d.primitives.WireframePlane;
	import away3d.tools.MeshDebugger;
	import away3d.materials.ColorMaterial;
	import away3d.events.MouseEvent3D;
	import away3d.loaders.Loader3D;
	
	import com.editor.Get3D;
	import com.editor.SceneEditorConstants;
	
	import flash.geom.Vector3D;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.utils.setTimeout;
	import flash.display.BlendMode;
	
	public class SelectHelper
	{
		private var _model:Loader3D;
		private var _axis:String;
		
		public function SelectHelper() {
		}
		
		public function get axis():String
		{
			return _axis;
		}
		
		public function set visible(val:Boolean):void
		{
			if(_model){
				_model.visible = val;
			}
		}
		
		public function set position(pos:Vector3D):void
		{
			if(_model){
				_model.position = pos;
			}
		}
		
		public function isme(name:String):Boolean
		{
			if(SceneEditorConstants.axis_x == name
				|| SceneEditorConstants.axis_y == name
				|| SceneEditorConstants.axis_z == name
				|| SceneEditorConstants.axis_o == name)
			{
				return true;
			}
			return false;
		}
		
		public function addHelper( asset:Loader3D ):void 
		{
			_model = asset;
			var mesh : Mesh;
			for (var i : int = 0; i < asset.numChildren; ++i) {
				mesh = Mesh(asset.getChildAt(i));
				mesh.mouseEnabled = true;
				mesh.mouseDetails = true;
				mesh.addEventListener(MouseEvent3D.MOUSE_DOWN, onClick);
				mesh.addEventListener(MouseEvent3D.MOUSE_OVER, onMouseMove);
				mesh.addEventListener(MouseEvent3D.MOUSE_OUT,  onMouseOut);
				if(i == 1){
					mesh.material = new ColorMaterial(0xaa0000);
					mesh.name = SceneEditorConstants.axis_x;
				}				
				if(i == 2){
					mesh.material = new ColorMaterial(0x00aa00);
					mesh.name = SceneEditorConstants.axis_y;
				}
				if(i == 3){
					mesh.material = new ColorMaterial(0x0000aa);
					mesh.name = SceneEditorConstants.axis_z;
				}
				if(i == 0){
					mesh.material = new ColorMaterial(0x999999);
					mesh.name = SceneEditorConstants.axis_o;
				}
				mesh.material.blendMode = BlendMode.ADD;
			}
			//visible = false;
		}
		
		private function onClick(event : MouseEvent3D) : void
		{
			var mesh : Mesh = Mesh(event.target);
			ColorMaterial (mesh.material).color *=2;
			_axis = mesh.name;
		}
		private function onMouseMove(event : MouseEvent3D) : void
		{
			var mesh : Mesh = Mesh(event.target);
			ColorMaterial (mesh.material).color *=2 
		}
		private function onMouseOut(event : MouseEvent3D) : void
		{
			var mesh : Mesh = Mesh(event.target);
			ColorMaterial (mesh.material).color /=2 
			_axis = SceneEditorConstants.axis_n;
		}
		
	}
}// ActionScript file