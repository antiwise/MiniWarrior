package com.editor.ui.views.modelview
{
	import com.editor.components.JTitledPanel;
	import com.editor.Get3D;
	import com.editor.engine.vo.SceneGeometryVO;
	
	import away3d.tools.MeshDebugger;
	
	import flash.utils.ByteArray;

	import org.junkbyte.console.Cc;
	import org.aswing.*;
	import org.aswing.SoftBoxLayout;
	import org.aswing.border.TitledBorder;
	import org.aswing.event.AWEvent;
	import org.aswing.event.FrameEvent;
	import org.aswing.geom.IntPoint;
	import com.editor.components.ColorSetterPopUp;

	public class ModelView extends JPanel
	{
		private var _model:SceneGeometryVO;
		private var _transform:JPanel;
		private var _positionBtn:JButton;
		private var _rotationBtn:JButton;
		private var _scaleBtn:JButton;
		
		private var _options:JPanel;		
		private var _bothsidesChk:JCheckBox;
		private var _showNomalChk:JCheckBox;

		public function ModelView() {

			super( new BorderLayout() );

			setName( "Model" );

			_positionBtn = new JButton( "Position" );
			_rotationBtn = new JButton( "Rotation" );
			_scaleBtn = new JButton( "Scale" );

			_transform = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			_transform.setBorder( new TitledBorder( null, "Transform" ) );
			_transform.visible = false;
			
			_options = new JPanel( new SoftBoxLayout( SoftBoxLayout.LEFT ) );
			_options.setBorder( new TitledBorder( null, "Mesh Options" ) );
			_bothsidesChk = new JCheckBox( "bothsides" );
			_bothsidesChk.setSelected( true );
			_bothsidesChk.addEventListener( AWEvent.ACT, bothsidesChkClicked );
			_options.append( _bothsidesChk );
			_showNomalChk = new JCheckBox( "show normal" );
			_showNomalChk.setSelected( false );
			_showNomalChk.addEventListener( AWEvent.ACT, shownormalChkClicked );
			_options.append( _showNomalChk );
			_options.visible = false;
			
			setLayout( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			append( _transform );
			append( _options );

		}

		private function bothsidesChkClicked( event:AWEvent ):void {
			_model.mesh.material.bothSides = _bothsidesChk.isSelected();
		}
		
		private function shownormalChkClicked( event:AWEvent ):void {
			if(_showNomalChk.isSelected()){
				Get3D.instance.renderSystem.meshDebugger.debug(
					_model.mesh,
					Get3D.instance.renderSystem.view.scene,
					false, true, false);
			}
			else{
				Get3D.instance.renderSystem.meshDebugger.removeDebug(_model.mesh);
			}		
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
			}
		}

		private function centerPopUp( popUp:JFrame ):void {
			popUp.setLocation( new IntPoint(
					stage.stageWidth / 2 - popUp.width / 2,
					stage.stageHeight / 2 - popUp.height / 2
			) );
		}

		public function set model( model:SceneGeometryVO ):void {
			_model = model;
			
			_model.mesh.material.bothSides = _bothsidesChk.isSelected();
			
			if(_positionBtn)_transform.remove(_positionBtn);
			if(_rotationBtn)_transform.remove(_rotationBtn);
			if(_scaleBtn)_transform.remove(_scaleBtn);
			
			_positionBtn = new JButton( "Position" );
			_rotationBtn = new JButton( "Rotation" );
			_scaleBtn = new JButton( "Scale" );
		
			_positionBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:XYZSetterPopUp = new XYZSetterPopUp( XYZSetterPopUp.POSITION, _model.mesh );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				_positionBtn.setEnabled( false );
				centerPopUp( pop );
			});
			
			_rotationBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:XYZSetterPopUp = new XYZSetterPopUp( XYZSetterPopUp.ROTATION, _model.mesh );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				_rotationBtn.setEnabled( false );
				centerPopUp( pop );
			});
			
			_scaleBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var pop:XYZSetterPopUp = new XYZSetterPopUp( XYZSetterPopUp.SCALE, _model.mesh );
				pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
				_scaleBtn.setEnabled( false );
				centerPopUp( pop );
			});
			_transform.append( _positionBtn );
			_transform.append( _rotationBtn );
			_transform.append( _scaleBtn );
			_transform.visible = true;
			_options.visible = true;

		}
	}
}
