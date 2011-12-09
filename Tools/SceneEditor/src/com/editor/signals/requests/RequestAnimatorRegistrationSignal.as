package com.editor.signals.requests
{

	import away3d.animators.VertexAnimator;

	import org.osflash.signals.Signal;

	public class RequestAnimatorRegistrationSignal extends Signal
	{
		public function RequestAnimatorRegistrationSignal() {
			super( VertexAnimator );
		}
	}
}