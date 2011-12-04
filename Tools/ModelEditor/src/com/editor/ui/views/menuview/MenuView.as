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
			
			var file1:JMenu = new JMenu( "New Shader" );

			var file1_1:JMenuItem = new JMenuItem( "Empty Shader" );
			var file1_2:JMenuItem = new JMenuItem( "Basic Shader" );
			var file1_3:JMenuItem = new JMenuItem( "Bitmap Shader" );
			var file1_4:JMenuItem = new JMenuItem( "Color Phong Shader" );
			var file1_5:JMenuItem = new JMenuItem( "Bitmap Phong Shader" );
			var file1_6:JMenuItem = new JMenuItem( "Advanced Bitmap Phong Shader" );
			var file1_7:JMenuItem = new JMenuItem( "Enviro Spherical Shader" );
			var file1_8:JMenuItem = new JMenuItem( "Vertex Color Shader" );
			var file1_9:JMenuItem = new JMenuItem( "Exploder Shader" );
			var file1_10:JMenuItem = new JMenuItem( "Wireframe Shader" );

			file1_1.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getEmptyMaterial();
			});

			file1_2.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getSimplestMaterial();
			});

			file1_3.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getBitmapMaterial();
			});

			file1_4.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getPhongColorMaterial();
			});

			file1_5.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getBasicPhongBitmapMaterial();
			});

			file1_6.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getAdvancedPhongBitmapMaterial();
			});

			file1_7.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getEnviroSphericalMaterial();
			});

			file1_8.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getVertexColorMaterial();
			});

			file1_9.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getExploderMaterial();
			});

			file1_10.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
				DefaultAssetStore.instance.getWireframeMaterial();
			});

			file1.append( file1_1 );
			file1.append( file1_2 );
			file1.append( file1_3 );
			file1.append( file1_4 );
			file1.append( file1_5 );
			file1.append( file1_6 );
			file1.append( file1_7 );
			file1.append( file1_8 );
			file1.append( file1_9 );
			file1.append( file1_10 );

			//menu.append( file1 );

			return menu;
		}
	}
}
