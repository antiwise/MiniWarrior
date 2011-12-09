package com.editor.engine.camera
{
	import com.editor.engine.vo.DynamicAttributeVO;
	import com.editor.engine.vo.SceneCameraVO;
	
	
	
	public class CameraPropertyFactory
	{
		public function CameraPropertyFactory ( )
		{
		}
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		//
		// Public Methods
		//
		////////////////////////////////////////////////////////////////////////////////
		
		
		
		public function build ( vo : SceneCameraVO ) : SceneCameraVO
		{
			for each ( var attribute : DynamicAttributeVO in vo.extras )
			{
				switch ( attribute.key )
				{
					case CameraAttributes.TRANSITION_TIME :
					{
						vo.transitionTime = Number ( attribute.value ) ;
						break ;
					}
					case CameraAttributes.TRANSITION_TYPE :
					{
						vo.transitionType = attribute.value ;
						break ;
					}
				}
			}
			
			return vo ;
		}
	}
}