package com.editor.components
{
	import away3d.entities.Mesh;
	
	import org.aswing.ASColor;
	import org.aswing.BorderLayout;
	import org.aswing.BoxLayout;
	import org.aswing.JButton;
	import org.aswing.JColorChooser;
	import org.aswing.JFrame;
	import org.aswing.JLabel;
	import org.aswing.JPanel;
	import org.aswing.SoftBoxLayout;
	import org.aswing.colorchooser.*;
	import org.aswing.event.AWEvent;
	import org.aswing.event.ColorChooserEvent;
	import org.aswing.event.InteractiveEvent;

	public class JColorPanel extends JPanel
	{
		private var okButton:JButton;
		private var cancelButton:JButton;
		
		private var _model:Mesh;

		public function JColorPanel( name:String, model:Mesh, type:String = "Mixer") {

			super( new BorderLayout() );
			_model = model;
			
			setLayout(new SoftBoxLayout( SoftBoxLayout.Y_AXIS ));
	
			if(type == "Mixer"){
				var cm:JColorMixer = new JColorMixer();
				cm.addEventListener(InteractiveEvent.STATE_CHANGED, colorChanged);
				append(cm, BorderLayout.CENTER);	
			}
			else if("Swatches"){
				var cs:JColorSwatches = new JColorSwatches();
				cs.addEventListener(InteractiveEvent.STATE_CHANGED, colorChanged);
				cs.setNoColorSectionVisible(true);
				append(cs, BorderLayout.CENTER);
			}	
			append( okButton = new JButton("OK") );
			append( cancelButton = new JButton("Cancel") );

		}
		/**
		 * Returns the ok button to finish the choosing.
		 * @return the ok button
		 */
		public function getOkButton():JButton{
			return okButton;
		}
		
		/**
		 * Returns the cancel button which use to cancel the choosing.
		 * @return the cancel button
		 */
		public function getCancelButton():JButton{
			return cancelButton;
		}
		protected function colorChanged(e:InteractiveEvent):void{
			trace("colorChanged");
		}

	}
	
	
}
