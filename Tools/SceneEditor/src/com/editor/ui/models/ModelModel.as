package com.editor.ui.models
{
	import com.editor.engine.vo.SceneGeometryVO;

	import com.editor.signals.notifications.ModelSetSignal

	public class ModelModel
	{

		private var _model:SceneGeometryVO;

		[Inject]
		public var modelSetSignal:ModelSetSignal;

		public function ModelModel() {
		}

		public function set model( value:SceneGeometryVO ):void {
			_model = value;
			modelSetSignal.dispatch( _model );
		}
	}
}
