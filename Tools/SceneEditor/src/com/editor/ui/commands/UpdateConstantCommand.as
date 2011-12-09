package com.editor.ui.commands
{

	import org.junkbyte.console.Cc;
	import com.editor.ui.models.MaterialModel;

	public class UpdateConstantCommand
	{
		[Inject]
		public var materialModel:MaterialModel;

		public function execute():void {
			Cc.info( "UpdateConstantCommand" );

		}
	}
}