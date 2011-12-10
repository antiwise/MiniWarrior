package com.editor.utils
{
	import away3d.materials.BitmapMaterial;
	
	import com.editor.engine.scene.SceneBuilder;
	
	import flash.display.*;
	import flash.events.*;
	import flash.filesystem.*;
	import flash.filesystem.File;
	import flash.net.FileFilter;
	import flash.utils.*;
	
	import org.osflash.signals.Signal;
	
    final public class SceneWriter extends Object
    {
		private var _file:File;
	
        public function SceneWriter()
        {
			_file = new File();				
			_file.addEventListener( Event.SELECT, saveHandler );
			try 
			{
				_file.browseForSave("Save ");
			}
			catch (error:Error)
			{
				trace("Failed:", error.message);
			}

            return;
        }// end function 

		private function saveHandler( event:Event ):void {
			var filestream:FileStream = new FileStream();
			filestream.open(_file, FileMode.WRITE);
			filestream.writeUTF(getString());
			filestream.close();
		}

		private function getString():String {
			return String(SceneBuilder.instance.encodeValue());
		}
    }
}
