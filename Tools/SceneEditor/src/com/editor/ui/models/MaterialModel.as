package com.editor.ui.models
{

	import com.editor.ui.models.vos.AgalVO;
	import com.editor.signals.notifications.MaterialSetSignal;

	import away3d.materials.MaterialBase;

	import flash.display3D.Context3DProgramType;

	import flash.display3D.Program3D;

	public class MaterialModel
	{
		[Inject]
		public var materialRegisteredSignal:MaterialSetSignal;

		private var _material:MaterialBase;

		public function MaterialModel() {
		}

		public function updateAGAL( agalVO:AgalVO ):void {
			//if( agalVO.programType == Context3DProgramType.VERTEX )
			//	_material.vertexAGAL = agalVO.agalCode;
			//else
			//	_material.fragmentAGAL = agalVO.agalCode;
		}

		public function set material( value:MaterialBase ):void {

			_material = value;
			materialRegisteredSignal.dispatch( _material );
		}
	}
}
