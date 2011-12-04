package com.editor.ui.views.materialview
{

	import com.editor.signals.notifications.MaterialSetSignal;
	
    import away3d.materials.MaterialBase;

	import org.robotlegs.mvcs.Mediator;

	public class MaterialViewMediator extends Mediator
	{
		[Inject]
		public var view:MaterialView;

		[Inject]
		public var materialSetSignal:MaterialSetSignal;

		override public function onRegister():void
		{
			// incoming
			materialSetSignal.add( onMaterialSet );
		}

		private function onMaterialSet( material:MaterialBase ):void {
			//view.vertConstants.material = material;
			//view.fragConstants.material = material;
		}
	}
}
