package com.editor.utils
{
	import away3d.entities.Mesh;	
	import flash.display.BitmapData;
	import away3d.library.assets.IAsset;
	import away3d.animators.VertexAnimator;

	public class AssetVO
	{	
		public var asset : IAsset ;
		public var animator : VertexAnimator;
		public var image : BitmapData;
		public var assetType : String;
		public var assetFile : String;
		
		public function AssetVO (
			asset : IAsset = null, 
			animator : VertexAnimator = null, 
			image : BitmapData = null,	
			assettype : String = null,
			assetFile : String = null)
		{
			this.asset = asset;
			this.animator = animator;
			this.image = image;
			this.assetType = assetType;
			this.assetFile = assetFile;
		}
	}
}