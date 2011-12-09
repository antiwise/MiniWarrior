package com.editor.ui.commands
{

	import org.junkbyte.console.Cc;
	import com.editor.ui.models.MaterialModel;
	
	import away3d.materials.MaterialBase;

	import org.robotlegs.mvcs.SignalCommand;

	public class RegisterMaterialCommand extends SignalCommand
	{
		[Inject]
		public var material:MaterialBase;

		[Inject]
		public var materialModel:MaterialModel;

		public function RegisterMaterialCommand() {
			super();
		}

		override public function execute():void {
			Cc.info( "RegisterMaterialCommand" );
			materialModel.material = material;
		}
	}
}