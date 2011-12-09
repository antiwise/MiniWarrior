package com.editor.ui.commands
{

	import com.editor.engine.vo.SceneGeometryVO;

	import org.robotlegs.mvcs.SignalCommand;

	import com.editor.ui.models.ModelModel

	public class RegisterModelCommand extends SignalCommand
	{
		[Inject]
		public var model:SceneGeometryVO;


		[Inject]
		public var modelModel:ModelModel;

		public function RegisterModelCommand() {
			super();
		}

		override public function execute():void
		{
			modelModel.model = model;
		}
	}
}
