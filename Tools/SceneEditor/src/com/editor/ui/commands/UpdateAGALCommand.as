package com.editor.ui.commands
{

	import org.junkbyte.console.Cc;
	import com.editor.ui.models.vos.AgalVO;

	import org.robotlegs.mvcs.SignalCommand;

	import com.editor.ui.models.MaterialModel

	public class UpdateAGALCommand extends SignalCommand
	{
		[Inject]
		public var agalVO:AgalVO;

		[Inject]
		public var materialModel:MaterialModel;

		public function UpdateAGALCommand() {
			super();
		}

		override public function execute():void
		{
			Cc.info( "UpdateProgram3DCommand" );

			materialModel.updateAGAL( agalVO );
		}
	}
}
