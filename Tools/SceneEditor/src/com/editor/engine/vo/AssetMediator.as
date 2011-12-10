package com.editor.engine.vo
{
	import away3d.entities.Mesh;
	
	import flash.display.BitmapData;
	
	public class AssetMediator
	{
		public var section : SceneSectionVO ;
		public var geometry : SceneGeometryVO ;	
		public var mesh : Mesh ;
		public var image : BitmapData;
		public var imageType : String;
		
		public function AssetMediator (
			section : SceneSectionVO = null, 
			geometry : SceneGeometryVO = null,
			mesh : Mesh = null, 
			image : BitmapData = null,
			imageType : String = null)
		{
			this.section = section;
			this.geometry = geometry;
			this.mesh = mesh;
			this.image = image;
			this.imageType = imageType;
		}
	}
}