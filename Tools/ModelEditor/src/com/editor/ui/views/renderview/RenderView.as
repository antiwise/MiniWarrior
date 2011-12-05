package com.editor.ui.views.renderview
{

	import away3d.entities.Mesh;
	import away3d.containers.View3D;
	import away3d.primitives.WireframePlane;
	import away3d.debug.AwayStats;
	
	import com.editor.Get3D;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.utils.setTimeout;
	
	import org.aswing.JPanel;
	import org.aswing.event.MovedEvent;
	import org.aswing.event.ResizedEvent;
	import org.aswing.geom.IntPoint;
	import com.editor.assets.DefaultAssetStore;

	public class RenderView extends JPanel
	{
		private var _view : View3D;
		private var _camController : ObitCamera;
		
		public function RenderView() {

			super();

			addEventListener( Event.ADDED_TO_STAGE, stageInitHandler );
		}

		private function stageInitHandler( event:Event ):void {

			removeEventListener( Event.ADDED_TO_STAGE, stageInitHandler );

			_view = new View3D();
			_view.camera.lens.far = 10000;
			_view.camera.lens.near = 0.01;
			_view.antiAlias = 4;
			_camController = new ObitCamera(_view.camera);
			Get3D.instance.view = _view;
			addChild(_view);
			addChild(new AwayStats(_view));
			_view.scene.addChild(new WireframePlane(1000,1000,10,10,0x555555,1,"xz"));
			
			_view.addEventListener( MouseEvent.MOUSE_DOWN, viewMouseDownHandler );
			_view.addEventListener( MouseEvent.MOUSE_UP, viewMouseUpHandler );
			_view.addEventListener( MouseEvent.MOUSE_OUT, viewMouseUpHandler );
			_view.addEventListener( MouseEvent.MOUSE_UP, viewMouseUpHandler );
			_view.addEventListener( MouseEvent.MOUSE_MOVE, viewMouseMoveHandler );
			_view.addEventListener( MouseEvent.MOUSE_WHEEL, viewMouseWheelHandler );

			addEventListener( Event.ENTER_FRAME, enterframeHandler );

			DefaultAssetStore.instance.modelRequestedSignal.add( onModelRequested );

			addEventListener( ResizedEvent.RESIZED, contentResizedHandler );
		}

		public function onModelRequested(child : Mesh) : void
		{
			_view.scene.addChild(child);
		}
		
		private function contentResizedHandler( event:ResizedEvent ):void {

			if( width > 0 && height > 0 ) {
				_view.width = width;
				_view.height = height;
			}
		}

		private function enterframeHandler( evt:Event ):void {
			_view.render();
			_camController.update();
		}

		private function viewMouseWheelHandler( evt:MouseEvent ):void {
			_camController.onMouseWheel( evt );
		}

		private function viewMouseMoveHandler( evt:MouseEvent ):void {
			_camController.mouseMove( mouseX, mouseY );
		}

		private function viewMouseDownHandler( evt:MouseEvent ):void {
			_camController.onMouseDown(mouseX, mouseY);
		}

		private function viewMouseUpHandler( evt:MouseEvent ):void {
			_camController.onMouseUp(evt);
		}
	}
}
