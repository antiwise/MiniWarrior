package com.editor.engine.material
{
	import away3d.materials.AnimatedBitmapMaterial;	
	import away3d.materials.ColorMaterial;
	import away3d.materials.SkyBoxMaterial;
	import away3d.materials.VideoMaterial;
	import away3d.materials.BitmapFileMaterial;
	import away3d.materials.BitmapMaterial;
	import away3d.materials.SegmentMaterial;
	
	import com.editor.engine.vo.DynamicAttributeVO;
	import com.editor.engine.vo.SceneGeometryVO;
	
	
	
	public class MaterialFactory
	{
		protected var propertyFactory : MaterialPropertyFactory ;
		
		
		
		public function MaterialFactory ( )
		{
			this.propertyFactory = new MaterialPropertyFactory ( ) ;
		}
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		//
		// Public Methods
		//
		////////////////////////////////////////////////////////////////////////////////
		
		
		
		public function build ( attribute : DynamicAttributeVO , vo : SceneGeometryVO ) : SceneGeometryVO
		{
			switch ( attribute.value )
			{
				case MaterialType.COLOR_MATERIAL :
				{
					vo.material = new ColorMaterial ( ) ;
					break ;
				}
				case MaterialType.ANIM_BITMAP_MATERIAL :
				{
					vo.material = new AnimatedBitmapMaterial () ;
					break ;
				}
				case MaterialType.SEGMENT_MATERIAL :
				{
					vo.material = new SegmentMaterial ( ) ;
					break ;
				}
				case MaterialType.VIDEO_MATERIAL :
				{
					vo.material = new VideoMaterial ( ) ;
					break ;
				}
				case MaterialType.SKY_BOX_MATERIAL :
				{
					vo.material = new SkyBoxMaterial ( null ) ;
					break ;
				}
		
				default :
				{
					// TODO: Confirm that this behaviour works correctly
					vo = this.buildDefault ( vo ) ;
				}
			}
			
			vo = this.propertyFactory.build ( vo ) ;
			vo.materialType = attribute.value ;
			
			return vo ;
		}
		
		
		
		public function buildDefault ( vo : SceneGeometryVO ) : SceneGeometryVO
		{
			var wireColorMaterial : ColorMaterial = new ColorMaterial ( ) ;
			
			wireColorMaterial.color = 0x00FF00 ;
			vo.material = wireColorMaterial ;
			vo.materialType = MaterialType.COLOR_MATERIAL ;
			
			return vo ;
		}
	}
}