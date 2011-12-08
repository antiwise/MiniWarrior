package com.editor.signals.requests
{

	import com.editor.utils.RegisterConstant;

	import org.osflash.signals.Signal;

	public class RequestConstantUpdateSignal extends Signal
	{
		public function RequestConstantUpdateSignal() {
			super( RegisterConstant );
		}
	}
}