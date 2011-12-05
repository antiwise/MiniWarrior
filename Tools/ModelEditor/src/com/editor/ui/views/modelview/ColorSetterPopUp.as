package com.editor.ui.views.modelview
{
	import com.editor.components.JColorPanel;
	import away3d.entities.Mesh;

	import flash.geom.Point;

	import org.aswing.JFrame;
	import org.aswing.JPanel;
	import org.aswing.event.AWEvent;
	import org.aswing.SoftBoxLayout;

	public class ColorSetterPopUp extends JFrame
	{
		public static const COLOR:String = "Color";

		public var type:String;
		public var colorpanel:JColorPanel;

		public function ColorSetterPopUp( type:String, model:Mesh ) {

			super( null, type );

			this.type = type;

			var content:JPanel = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			content.append( colorpanel = new JColorPanel( "Color",  model ) );
			setContentPane( content );
			setResizable( false );

			colorpanel.getOkButton().addActionListener(onOK);
			colorpanel.getCancelButton().addActionListener(onCancel);
			
			pack();
			show();
		}
		
		private function onOK(e:AWEvent):void{
			this.tryToClose();
			trace("onOK");
		}
		
		private function onCancel(e:AWEvent):void{
			this.tryToClose();
			trace("onCancel");
		}
	}
}
