package com.editor.ui.views.renderview
{
	import com.editor.engine.vo.SceneGeometryVO;
	import away3d.materials.MaterialBase;
	import away3d.animators.VertexAnimator;

	import org.robotlegs.mvcs.Mediator;

	import com.editor.signals.requests.*
	import com.editor.engine.AssetsStore;

	public class RenderViewMediator extends Mediator
	{
		[Inject]
		public var view:RenderView;

		[Inject]
		public var requestMaterialRegisterSignal:RequestMaterialRegistrationSignal;


		[Inject]
		public var requestModelRegistrationSignal:RequestModelRegistrationSignal;
		
		[Inject]
		public var requestAnimatorRegistrationSignal:RequestAnimatorRegistrationSignal;

		override public function onRegister():void {
			// outgoing
			AssetsStore.instance.materialRequestedSignal.add( onMaterialInitialized );
			AssetsStore.instance.modelRequestedSignal.add( onModelInitialized );
			AssetsStore.instance.animatorRequestedSignal.add( onAnimatorInitialized );
		}

		private function onModelInitialized( model:SceneGeometryVO ):void {
			requestModelRegistrationSignal.dispatch( model );
		}

		private function onMaterialInitialized( material:MaterialBase ):void {
			requestMaterialRegisterSignal.dispatch( material );
		}
		
		private function onAnimatorInitialized( anim:VertexAnimator ):void {
			requestAnimatorRegistrationSignal.dispatch( anim );
		}
	}
}
