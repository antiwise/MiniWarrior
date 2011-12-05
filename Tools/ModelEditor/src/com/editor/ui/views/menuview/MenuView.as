package com.editor.ui.views.menuview
{

	import com.editor.ModelEditorConstants;
	import com.editor.assets.DefaultAssetStore;
	import com.editor.assets.MeshPrompt;
	
	import away3d.library.assets.IAsset;
	
	import org.aswing.ASColor;
	import org.aswing.BorderLayout;
	import org.aswing.JLabel;
	import org.aswing.JMenu;
	import org.aswing.JMenuBar;
	import org.aswing.JMenuItem;
	import org.aswing.JPanel;
	import org.aswing.event.AWEvent;
	
	import flash.utils.ByteArray;

	public class MenuView extends JPanel
	{
		public function MenuView() {

			super();

			// TODO: add shortcuts or "accelerators" - other parts of the IDE could have shortcuts as well

			setLayout( new BorderLayout() );

			setOpaque( true );
			setBackground( new ASColor( 0xFFFFFF ) );

			var menuBar:JMenuBar = new JMenuBar();
			menuBar.append( createFileMenu() );
			menuBar.append( new HelpMenu() );

			append( menuBar, BorderLayout.WEST );
			append( new JLabel( ModelEditorConstants.appNameAndVersion ), BorderLayout.EAST );
		}
		
		private function onObjDataFetched( data:IAsset ):void {
			DefaultAssetStore.instance.getLoadedModel( data );
		}
		private function createFileMenu():JMenu {

			var menu:JMenu = new JMenu( "File" );
			
			var file0:JMenuItem = new JMenuItem( "Import Model" );
			menu.append( file0 );
	
			file0.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var prompt:MeshPrompt = new MeshPrompt();
				prompt.completeSignal.addOnce( onObjDataFetched );
			});
			
			return menu;
		}
	}
}
