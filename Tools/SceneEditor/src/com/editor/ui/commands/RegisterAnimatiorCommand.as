package com.editor.ui.commands
{

	import away3d.animators.VertexAnimator;

	import org.robotlegs.mvcs.SignalCommand;

	import com.editor.ui.models.AnimatorModel

	public class RegisterAnimatiorCommand extends SignalCommand
	{
		[Inject]
		public var animator:VertexAnimator;


		[Inject]
		public var animatorModel:AnimatorModel;

		public function RegisterAnimatiorCommand() {
			super();
		}

		override public function execute():void
		{
			animatorModel.animator = animator;
		}
	}
}
