package com.editor.ui.commands
{

	import away3d.entities.Mesh;

	import org.robotlegs.mvcs.SignalCommand;

	import com.editor.ui.models.ModelModel

	public class RegisterModelCommand extends SignalCommand
	{
		[Inject]
		public var model:Mesh;


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
