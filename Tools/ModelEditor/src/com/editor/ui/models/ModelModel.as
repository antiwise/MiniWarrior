package com.editor.ui.models
{

	import away3d.entities.Mesh;

	import com.editor.signals.notifications.ModelSetSignal

	public class ModelModel
	{

		private var _model:Mesh;

		[Inject]
		public var modelSetSignal:ModelSetSignal;

		public function ModelModel() {
		}

		public function set model( value:Mesh ):void {
			_model = value;
			modelSetSignal.dispatch( _model );
		}
	}
}
