package com.editor.ui.views.modelview
{
	import com.editor.components.JTitledPanel;
	import com.editor.assets.DefaultAssetStore;
	import com.editor.Get3D;
	
	import away3d.entities.Mesh;
	
	import flash.utils.ByteArray;

	import org.junkbyte.console.Cc;
	import org.aswing.JButton;
	import org.aswing.JCheckBox;
	import org.aswing.JComboBox;
	import org.aswing.JFrame;
	import org.aswing.JPanel;
	import org.aswing.SoftBoxLayout;
	import org.aswing.border.TitledBorder;
	import org.aswing.event.AWEvent;
	import org.aswing.event.FrameEvent;
	import org.aswing.geom.IntPoint;

	public class ModelView extends JTitledPanel
	{
		private var _model:Mesh;
		private var _positionBtn:JButton;
		private var _rotationBtn:JButton;
		private var _scaleBtn:JButton;
		private var _colorBtn:JButton;
		private var _bothsidesChk:JCheckBox;

		public function ModelView() {

			super( "model atrributes" );

			setName( "Model" );

			_positionBtn = new JButton( "Position" );
			_rotationBtn = new JButton( "Rotation" );
			_scaleBtn = new JButton( "Scale" );
			_colorBtn = new JButton( "Color" );

			var transform:JPanel = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			transform.setBorder( new TitledBorder( null, "Transform" ) );
			transform.append( _positionBtn );
			transform.append( _rotationBtn );
			transform.append( _scaleBtn );
			transform.append( _colorBtn );

			var options:JPanel = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			options.setBorder( new TitledBorder( null, "Mesh Options" ) );
			_bothsidesChk = new JCheckBox( "bothsides" );
			_bothsidesChk.setSelected( true );
			_bothsidesChk.addEventListener( AWEvent.ACT, bothsidesChkClicked );
			options.append( _bothsidesChk );

			contentPanel.setLayout( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			contentPanel.append( transform );
			contentPanel.append( options );

			_positionBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:XYZSetterPopUp = new XYZSetterPopUp( XYZSetterPopUp.POSITION, _model );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				_positionBtn.setEnabled( false );
				centerPopUp( pop );
			});

			_rotationBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:XYZSetterPopUp = new XYZSetterPopUp( XYZSetterPopUp.ROTATION, _model );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				_rotationBtn.setEnabled( false );
				centerPopUp( pop );
			});

			_scaleBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:XYZSetterPopUp = new XYZSetterPopUp( XYZSetterPopUp.SCALE, _model );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				_scaleBtn.setEnabled( false );
				centerPopUp( pop );
			});
			
			_colorBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:ColorSetterPopUp = new ColorSetterPopUp( "Color", _model );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				//_colorBtn.setEnabled( false );
				centerPopUp( pop );
			});
			
		}

		private function bothsidesChkClicked( event:AWEvent ):void {
			_model.material.bothSides = _bothsidesChk.isSelected();
		}

		private function popUpClosedHandler( event:FrameEvent ):void {
			var target:XYZSetterPopUp = event.target as XYZSetterPopUp;
			switch( target.type ) {
				case XYZSetterPopUp.POSITION: {
					_positionBtn.setEnabled( true );
					break;
				}
				case XYZSetterPopUp.ROTATION: {
					_rotationBtn.setEnabled( true );
					break;
				}
				case XYZSetterPopUp.SCALE: {
					_scaleBtn.setEnabled( true );
					break;
				}
				case ColorSetterPopUp.COLOR: {
					_colorBtn.setEnabled( true );
					break;
				}
			}
		}

		private function centerPopUp( popUp:JFrame ):void {
			popUp.setLocation( new IntPoint(
					stage.stageWidth / 2 - popUp.width / 2,
					stage.stageHeight / 2 - popUp.height / 2
			) );
		}

		public function set model( model:Mesh ):void {
			_model = model;
			_model.material.bothSides = _bothsidesChk.isSelected();
		}
	}
}
