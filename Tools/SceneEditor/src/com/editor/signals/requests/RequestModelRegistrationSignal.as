package com.editor.signals.requests
{

	import com.editor.engine.vo.SceneGeometryVO;

	import org.osflash.signals.Signal;

	public class RequestModelRegistrationSignal extends Signal
	{
		public function RequestModelRegistrationSignal() {
			super( SceneGeometryVO );
		}
	}
}