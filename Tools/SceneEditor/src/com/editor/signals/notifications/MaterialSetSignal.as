package com.editor.signals.notifications
{

	import away3d.materials.MaterialBase;


	import org.osflash.signals.Signal;

	public class MaterialSetSignal extends Signal
	{
		public function MaterialSetSignal() {
			super( MaterialBase );
		}
	}
}
