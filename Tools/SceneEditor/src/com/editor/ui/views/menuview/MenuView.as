package com.editor.ui.views.menuview
{

	import away3d.library.assets.IAsset;
	
	import com.editor.SceneEditorConstants;
	import com.editor.engine.AssetsStore;
	import com.editor.utils.MeshPrompt;
	import com.editor.utils.MeshWriter;
	
	import flash.utils.ByteArray;
	
	import org.aswing.ASColor;
	import org.aswing.BorderLayout;
	import org.aswing.JLabel;
	import org.aswing.JMenu;
	import org.aswing.JMenuBar;
	import org.aswing.JMenuItem;
	import org.aswing.JPanel;
	import org.aswing.event.AWEvent;

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
			append( new JLabel( SceneEditorConstants.appNameAndVersion ), BorderLayout.EAST );
		}
		
		private function onObjDataFetched( data:IAsset ):void {
			AssetsStore.instance.getLoadedModel( data );
		}
		private function createFileMenu():JMenu {

			var menu:JMenu = new JMenu( "File" );
			
			var file0:JMenuItem = new JMenuItem( "Import Model" );
			menu.append( file0 );
	
			file0.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var prompt:MeshPrompt = new MeshPrompt();
				prompt.completeSignal.addOnce( onObjDataFetched );
			});
			
			var file1:JMenuItem = new JMenuItem( "Export Model" );
			menu.append( file1 );
			
			file1.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				var prompt:MeshWriter = new MeshWriter(AssetsStore.instance.model);
			});
			
			return menu;
		}
	}
}
