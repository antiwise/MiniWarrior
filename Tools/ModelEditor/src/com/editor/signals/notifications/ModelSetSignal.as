package com.editor.signals.notifications
{

	import away3d.entities.Mesh;

	import org.osflash.signals.Signal;

	public class ModelSetSignal extends Signal
	{
		public function ModelSetSignal() {
			super( Mesh );
		}
	}
}
