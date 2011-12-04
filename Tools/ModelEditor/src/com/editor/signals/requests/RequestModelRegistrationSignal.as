package com.editor.signals.requests
{

	import away3d.entities.Mesh;

	import org.osflash.signals.Signal;

	public class RequestModelRegistrationSignal extends Signal
	{
		public function RequestModelRegistrationSignal() {
			super( Mesh );
		}
	}
}