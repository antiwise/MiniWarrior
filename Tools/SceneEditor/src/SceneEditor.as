package 
{
	import com.editor.*;
	import flash.display.*;
	
	public class SceneEditor extends Sprite
	{
		
		public function SceneEditor()
		{
			stage.scaleMode = StageScaleMode.NO_SCALE;
			stage.align = StageAlign.TOP_LEFT;
			stage.frameRate = 60;
			new MainContext(this);
			
		}// end function
		
	}
}