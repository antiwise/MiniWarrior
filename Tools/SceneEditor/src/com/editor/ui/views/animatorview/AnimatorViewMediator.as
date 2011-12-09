package com.editor.ui.views.animatorview
{

	import away3d.animators.VertexAnimator;

	import org.robotlegs.mvcs.Mediator;

	import com.editor.signals.notifications.AnimatorSetSignal

	public class AnimatorViewMediator extends Mediator
	{
		[Inject]
		public var view:AnimatorView;

		[Inject]
		public var animatorSetSignal:AnimatorSetSignal;

		override public function onRegister():void
		{
			// incoming
			animatorSetSignal.add( onAnimatorSet );
		}

		private function onAnimatorSet( anim:VertexAnimator ):void {
			view.animator = anim;
		}
	}
}
