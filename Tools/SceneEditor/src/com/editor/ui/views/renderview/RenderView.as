package com.editor.ui.views.renderview
{
	import away3d.entities.Mesh;
	
	import com.editor.Get3D;
	import com.editor.engine.ObitCamera;
	import com.editor.engine.RenderSystem;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.utils.setTimeout;
	
	import org.aswing.JPanel;
	import org.aswing.event.MovedEvent;
	import org.aswing.event.ResizedEvent;
	import org.aswing.geom.IntPoint;
	import com.editor.engine.AssetsStore;

	public class RenderView extends JPanel
	{
		private var renderSys:RenderSystem;
		
		public function RenderView() {

			super();

			addEventListener( Event.ADDED_TO_STAGE, stageInitHandler );
		}

		private function stageInitHandler( event:Event ):void {

			removeEventListener( Event.ADDED_TO_STAGE, stageInitHandler );

			renderSys = new RenderSystem;

			addChild(renderSys.view);
			addChild(renderSys.stats);		
			
			renderSys.view.addEventListener( MouseEvent.MOUSE_DOWN, viewMouseDownHandler );
			renderSys.view.addEventListener( MouseEvent.MOUSE_UP, viewMouseUpHandler );
			renderSys.view.addEventListener( MouseEvent.MOUSE_OUT, viewMouseUpHandler );
			renderSys.view.addEventListener( MouseEvent.MOUSE_UP, viewMouseUpHandler );
			renderSys.view.addEventListener( MouseEvent.MOUSE_MOVE, viewMouseMoveHandler );
			renderSys.view.addEventListener( MouseEvent.MOUSE_WHEEL, viewMouseWheelHandler );

			addEventListener( Event.ENTER_FRAME, enterframeHandler );

			AssetsStore.instance.modelRequestedSignal.add( onModelRequested );

			addEventListener( ResizedEvent.RESIZED, contentResizedHandler );
		}

		public function onModelRequested(child : Mesh) : void
		{
			renderSys.addChild(child);
		}
		
		private function contentResizedHandler( event:ResizedEvent ):void {

			if( width > 0 && height > 0 ) {
				renderSys.setViewSize(width, height);
			}
		}

		private function enterframeHandler( evt:Event ):void {
			renderSys.view.render();
			renderSys.camController.update();
		}

		private function viewMouseWheelHandler( evt:MouseEvent ):void {
			renderSys.camController.onMouseWheel( evt );
		}

		private function viewMouseMoveHandler( evt:MouseEvent ):void {
			renderSys.camController.mouseMove( mouseX, mouseY );
		}

		private function viewMouseDownHandler( evt:MouseEvent ):void {
			renderSys.camController.onMouseDown(mouseX, mouseY);
		}

		private function viewMouseUpHandler( evt:MouseEvent ):void {
			renderSys.camController.onMouseUp(evt);
		}
	}
}
