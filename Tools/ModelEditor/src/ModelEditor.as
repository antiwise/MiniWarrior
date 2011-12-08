package 
{
	import com.editor.*;
	import flash.display.*;
	
	public class ModelEditor extends Sprite
	{
		
		public function ModelEditor()
		{
			stage.scaleMode = StageScaleMode.NO_SCALE;
			stage.align = StageAlign.TOP_LEFT;
			stage.frameRate = 60;
			new MainContext(this);
			
		}// end function
		
	}
}