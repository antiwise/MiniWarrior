package com.editor {

	import org.junkbyte.console.Cc;
	import com.editor.ui.commands.RegisterMaterialCommand;
	import com.editor.ui.commands.RegisterModelCommand;
	import com.editor.ui.commands.UpdateAGALCommand;
	import com.editor.ui.commands.UpdateConstantCommand;
	import com.editor.ui.models.MaterialModel;
	import com.editor.ui.models.ModelModel;
	import com.editor.signals.notifications.MaterialSetSignal;
	import com.editor.signals.notifications.ModelSetSignal;
	import com.editor.signals.requests.RequestAgalUpdateSignal;
	import com.editor.signals.requests.RequestMaterialRegistrationSignal;
	import com.editor.signals.requests.RequestConstantUpdateSignal;
	import com.editor.signals.requests.RequestModelRegistrationSignal;
	import com.editor.ui.MainView;
	import com.editor.ui.views.modelview.ModelView;
	import com.editor.ui.views.modelview.ModelViewMediator;
	import com.editor.ui.views.materialview.MaterialView;
	import com.editor.ui.views.materialview.MaterialViewMediator;
	import com.editor.ui.views.renderview.RenderView;
	import com.editor.ui.views.renderview.RenderViewMediator;

	import flash.display.Sprite;
	import flash.events.Event;

	import org.robotlegs.mvcs.SignalContext;

	public class MainContext extends SignalContext {

		public function MainContext( contextView:Sprite ) {
			super( contextView, true );
		}

		override public function startup():void {

			// init debugging console
			if( ModelEditorConstants.debugModeActive )
			{
				var stageResizeHandler:Function = function( event:Event ):void
				{
					Cc.width = contextView.stage.stageWidth;
					Cc.height = contextView.stage.stageHeight;
				};
				contextView.stage.addEventListener( Event.RESIZE, stageResizeHandler );
				Cc.config.style.backgroundAlpha = 0.75;
				Cc.config.tracing = true;
				//Cc.config.showLineNumber = true;
				//Cc.config.showTimestamp = true;
				Cc.startOnStage( contextView, "`" );
				stageResizeHandler( null );
			}
			Cc.info( "MainContext: " + ModelEditorConstants.appNameAndVersion );

			// bootstrap
			mapMediators();
			mapModels();
			mapSignals();
			mapCommands();

			// init UI
			contextView.addChild( new MainView() );
		}

		private function mapCommands(  ):void {
			signalCommandMap.mapSignalClass( RequestMaterialRegistrationSignal, RegisterMaterialCommand );
			signalCommandMap.mapSignalClass( RequestAgalUpdateSignal, UpdateAGALCommand );
			signalCommandMap.mapSignalClass( RequestConstantUpdateSignal, UpdateConstantCommand );
			signalCommandMap.mapSignalClass( RequestModelRegistrationSignal, RegisterModelCommand );
		}

		private function mapMediators():void {
			mediatorMap.mapView( RenderView, RenderViewMediator );
			mediatorMap.mapView( MaterialView, MaterialViewMediator );
			mediatorMap.mapView( ModelView, ModelViewMediator );
		}

		private function mapModels(  ):void {
			injector.mapSingleton( MaterialModel );
			injector.mapSingleton( ModelModel );
		}

		private function mapSignals(  ):void {
			injector.mapSingleton( MaterialSetSignal );
			injector.mapSingleton( ModelSetSignal );
		}
	}
}
