package com.editor.engine.interfaces
{
	import com.editor.engine.vo.SceneCameraVO;
	import com.editor.engine.vo.SceneGeometryVO;
	import com.editor.engine.vo.SceneSectionVO;
	
	
	
	public interface ISceneContainer
	{
		function getCameras ( ) : Array
		function getGeometry ( ) : Array
		function getSections ( ) : Array
		function getSectionById ( id : String ) : SceneSectionVO
		function getCameraById ( id : String ) : SceneCameraVO
		function getGeometryById ( id : String ) : SceneGeometryVO
	}
}