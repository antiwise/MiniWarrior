package com.editor.signals.notifications
{

	import com.editor.engine.vo.SceneGeometryVO;

	import org.osflash.signals.Signal;

	public class ModelSetSignal extends Signal
	{
		public function ModelSetSignal() {
			super( SceneGeometryVO );
		}
	}
}
