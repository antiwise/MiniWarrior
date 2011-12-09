package com.editor.ui.views.renderview
{

	import com.editor.signals.requests.RequestMaterialRegistrationSignal;
	
	import away3d.entities.Mesh;
	import away3d.materials.MaterialBase;
	
	import org.robotlegs.mvcs.Mediator;

	import com.editor.signals.requests.RequestModelRegistrationSignal
	import com.editor.engine.AssetsStore;

	public class RenderViewMediator extends Mediator
	{
		[Inject]
		public var view:RenderView;

		[Inject]
		public var requestMaterialRegisterSignal:RequestMaterialRegistrationSignal;


		[Inject]
		public var requestModelRegistrationSignal:RequestModelRegistrationSignal;

		override public function onRegister():void {

			// incoming

			// outgoing
			AssetsStore.instance.materialRequestedSignal.add( onMaterialInitialized );
			AssetsStore.instance.modelRequestedSignal.add( onModelInitialized );
		}

		private function onModelInitialized( model:Mesh ):void {
			requestModelRegistrationSignal.dispatch( model );
		}

		private function onMaterialInitialized( material:MaterialBase ):void {
			requestMaterialRegisterSignal.dispatch( material );
		}
	}
}
