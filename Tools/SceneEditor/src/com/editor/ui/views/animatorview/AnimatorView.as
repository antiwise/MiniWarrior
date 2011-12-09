package com.editor.ui.views.animatorview
{
	import away3d.animators.VertexAnimator;
	import away3d.animators.data.VertexAnimationSequence;
	import away3d.tools.MeshDebugger;
	
	import com.editor.Get3D;
	import com.editor.components.ColorSetterPopUp;
	import com.editor.components.JTitledPanel;
	import com.editor.components.utils.IconListCell;
	import org.aswing.border.*;
	import com.editor.components.*;

	import flash.geom.Point;
	import flash.utils.ByteArray;
	

	import org.aswing.*
	import org.aswing.border.TitledBorder;
	import org.aswing.event.AWEvent;
	import org.aswing.event.FrameEvent;
	import org.aswing.geom.IntPoint;
	import org.junkbyte.console.Cc;
	import org.aswing.event.ListItemEvent;

	public class AnimatorView extends JPanel
	{
		private var _animator:VertexAnimator;
		private var _animlistPanel:JPanel;
		private var _list:JList;
		private var _listData:Array;
		private var _activeAnimationName:String;
		
		private var _options:JPanel;
		private var _animscale:JLabeledSlider;
		private var _loopChk:JCheckBox;

		public function AnimatorView() {

			super( new BorderLayout() );

			setName( "Animator" );

			_list = new JList( null,new GeneralListCellFactory(IconListCell, false, false) );
			_list.addEventListener( ListItemEvent.ITEM_CLICK, listItemClickedHandler );
			_list.setBorder(new LineBorder(null, new ASColor(0x555555), 3));
			_animlistPanel = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			_animlistPanel.setBorder( new TitledBorder( null, "Animtion" ) );
			_animlistPanel.append( _list );
			
			_options = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS) );
			_options.setBorder( new TitledBorder( null, "Animation Options" ) );
			_loopChk = new JCheckBox( "Loop" );
			_loopChk.setSelected( true );
			_loopChk.addEventListener( AWEvent.ACT, animtionLoopChkClicked );
			_options.append( _loopChk );
	
			_animlistPanel.visible = false;
			_options.visible = false;
			
			setLayout( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			append( _animlistPanel );
			append( _options );

		}

		public function set animator( anim:VertexAnimator ):void {
			_animator = anim;
			if( _animator ){
				buildPlayList();		
				
				if(_animscale)	remove(_animscale);
				_animscale = new JLabeledSlider( "Time Scale","timeScale", _animator,new Point( 0, 10 ));
				append( _animscale );
				
				_animlistPanel.visible = true;
				_options.visible = true;
			}
		}
		private function listItemClickedHandler( event:ListItemEvent ):void {
			if( _animator ){
				_activeAnimationName = event.getValue();
				_animator.play(_activeAnimationName);
			}			
		}
		private function animtionLoopChkClicked( event:AWEvent ):void {
			if(_animator && _activeAnimationName){
				_animator.getSequence(_activeAnimationName).looping = _loopChk.isSelected();
			}
		}
		public function buildPlayList():void{
			_listData = [];
			for each(var seq:VertexAnimationSequence in _animator.sequences){
				_listData.push(seq.name);
			}
			_list.setListData(_listData);
		}
	}
}
