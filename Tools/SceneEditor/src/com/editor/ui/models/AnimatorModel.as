package com.editor.ui.models
{

	import away3d.animators.VertexAnimator;

	import com.editor.signals.notifications.AnimatorSetSignal

	public class AnimatorModel
	{

		private var _animatior:VertexAnimator;

		[Inject]
		public var animatiorSetSignal:AnimatorSetSignal;

		public function AnimatorModel() {
		}

		public function set animator( value:VertexAnimator ):void {
			_animatior = value;
			animatiorSetSignal.dispatch( _animatior );
		}
	}
}
