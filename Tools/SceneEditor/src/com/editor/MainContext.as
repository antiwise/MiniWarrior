package com.editor {

	import org.junkbyte.console.Cc;
	import com.editor.ui.commands.*;
	import com.editor.ui.models.*;
	import com.editor.signals.notifications.*;
	import com.editor.signals.requests.*;	
	import com.editor.ui.views.modelview.*;
	import com.editor.ui.views.materialview.*;
	import com.editor.ui.views.renderview.*;
	import com.editor.ui.views.animatorview.*;
	import com.editor.ui.MainView;
	
	import flash.display.Sprite;
	import flash.events.Event;

	import org.robotlegs.mvcs.SignalContext;

	public class MainContext extends SignalContext {

		public function MainContext( contextView:Sprite ) {
			super( contextView, true );
		}

		override public function startup():void {

			// init debugging console
			if( SceneEditorConstants.debugModeActive )
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
			Cc.info( "MainContext: " + SceneEditorConstants.appNameAndVersion );

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
			signalCommandMap.mapSignalClass( RequestAnimatorRegistrationSignal, RegisterAnimatiorCommand );
			signalCommandMap.mapSignalClass( RequestModelRegistrationSignal, RegisterModelCommand );
		}

		private function mapMediators():void {
			mediatorMap.mapView( RenderView, RenderViewMediator );
			mediatorMap.mapView( MaterialView, MaterialViewMediator );
			mediatorMap.mapView( AnimatorView, AnimatorViewMediator );
			mediatorMap.mapView( ModelView, ModelViewMediator );

		}

		private function mapModels(  ):void {
			injector.mapSingleton( MaterialModel );
			injector.mapSingleton( ModelModel );
			injector.mapSingleton( AnimatorModel );
		}

		private function mapSignals(  ):void {
			injector.mapSingleton( MaterialSetSignal );
			injector.mapSingleton( ModelSetSignal );
			injector.mapSingleton( AnimatorSetSignal );
		}
	}
}
