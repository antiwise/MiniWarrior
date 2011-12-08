package com.editor.signals.requests
{

	import com.editor.ui.models.vos.AgalVO;

	import org.osflash.signals.Signal;

	public class RequestAgalUpdateSignal extends Signal
	{
		public function RequestAgalUpdateSignal() {
			super( AgalVO );
		}
	}
}
