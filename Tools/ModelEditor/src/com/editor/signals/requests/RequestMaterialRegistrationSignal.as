package com.editor.signals.requests
{

	import away3d.materials.MaterialBase;

	import org.osflash.signals.Signal;

	public class RequestMaterialRegistrationSignal extends Signal
	{
		public function RequestMaterialRegistrationSignal() {
			super( MaterialBase );
		}
	}
}