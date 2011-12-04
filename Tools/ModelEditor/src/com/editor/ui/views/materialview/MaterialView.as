package com.editor.ui.views.materialview
{
	
	import com.editor.components.JRegisterPanel;
	import com.editor.utils.AGALRegister;
	import com.editor.utils.VectorRegisterConstant;
	
	import away3d.materials.MaterialBase;
	
	public class MaterialView extends JRegisterPanel
	{
		public function MaterialView() {
			
			super( "Material", "----Material Attribute----", EditAttributePopUp );
			addRegister( new VectorRegisterConstant("AA"));
			addRegister( new VectorRegisterConstant("BB"));
			addRegister( new VectorRegisterConstant("CC"));
		}
		
		override public function set material( value:MaterialBase ):void {
			
			_material = value;
			
			removeAllRegisters();
		}
		
		override protected function createRegister():AGALRegister {
			var attribute:VectorRegisterConstant= new VectorRegisterConstant( "new attribute");
			return attribute;
		}
		
		override protected function removeRegister( register:AGALRegister ):void {
			//_material.removeVertexAttribute( register as VertexAttribute );
		}
	}
}