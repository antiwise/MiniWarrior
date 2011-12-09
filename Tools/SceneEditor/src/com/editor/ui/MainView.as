package com.editor.ui
{

	import com.editor.ui.views.materialview.MaterialView;
	import com.editor.ui.views.logview.LogView;
	import com.editor.ui.views.menuview.MenuView;
	import com.editor.ui.views.modelview.ModelView;
	import com.editor.ui.views.renderview.RenderView;
	import com.editor.ui.views.animatorview.AnimatorView;

	import flash.events.Event;
	import flash.utils.setTimeout;

	import org.aswing.AsWingConstants;
	import org.aswing.AsWingManager;
	import org.aswing.BorderLayout;
	import org.aswing.JPanel;
	import org.aswing.JSplitPane;
	import org.aswing.JTabbedPane;
	import org.aswing.LookAndFeel;
	import org.aswing.UIManager;
	import org.aswing.geom.IntDimension;
	
	import org.aswing.paling.PalingLAF;	
	import org.aswing.skinbuilder.SkinBuilderLAF;
	import org.aswing.skinbuilder.orange.OrangeLookAndFeel;	
	import cn.harryxu.tenerLaf.TenerLookAndFeel;
	import com.alvasun.laf.chrome.ChromeLAF;
	import com.alvasun.laf.blue.BluenessLAF;
	import aeon.AeonLAF;
	
	public class MainView extends JPanel
	{
		public var renderView:RenderView;
		public var logView:LogView;
		public var modelView:ModelView;
		public var animatorView:AnimatorView;	
		public var materialView:MaterialView;

		// fonts
		[Embed(source="../../../../assets/fonts/inconsolata.ttf", embedAsCFF="false", fontName="Inconsolata", mimeType="application/x-font")]
		public var InconsolataFont:Class;

		public function MainView() {

			super();
			addEventListener( Event.ADDED_TO_STAGE, stageInitHandler );
		}

		private function stageInitHandler( event:Event ):void {

			AsWingManager.initAsStandard(this);
			UIManager.setLookAndFeel(new SkinBuilderLAF());
			
			setLayout( new BorderLayout() );
			setSize( new IntDimension( stage.stageWidth, stage.stageHeight ) );

			// menu
			append( new MenuView(), BorderLayout.NORTH );

			// wrap left/right
			var mainSplitter:JSplitPane = new JSplitPane();
			mainSplitter.setConstraints( BorderLayout.CENTER );
			mainSplitter.setDividerLocation( stage.stageWidth / 2, true );
			append( mainSplitter, BorderLayout.CENTER );

			// left
			var leftPanel:JSplitPane = new JSplitPane();
			leftPanel.setConstraints( BorderLayout.CENTER );
			leftPanel.setOrientation( AsWingConstants.VERTICAL );
			mainSplitter.append( leftPanel );
			leftPanel.append( renderView = new RenderView() );
			leftPanel.append( logView = new LogView() );
			setTimeout( function():void {
				leftPanel.setDividerLocation( stage.stageHeight - 140, true );
				logView.visible = true;
			}, 50); // TODO: left panel's divider position doesn't seem to be updated otherwise

			// right
			var rightPanel:JTabbedPane = new JTabbedPane();
			mainSplitter.append( rightPanel );
			rightPanel.append( modelView = new ModelView() );
			//rightPanel.append( shadersView = new ShadersView() );
			rightPanel.append( materialView = new MaterialView() );
			rightPanel.append( animatorView = new AnimatorView() );
			//rightPanel.append( samplersView = new SamplersView() );
			

			stage.addEventListener( Event.RESIZE, stageResizeHandler );
		}

		private function stageResizeHandler( event:Event ):void {

			setSize( new IntDimension( stage.stageWidth, stage.stageHeight ) );
			validate();
		}
	}
}
