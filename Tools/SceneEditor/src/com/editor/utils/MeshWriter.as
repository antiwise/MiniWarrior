package com.editor.utils
{
	import away3d.entities.Mesh;
	import away3d.materials.BitmapMaterial;
	
	import flash.display.*;
	import flash.events.*;
	import flash.filesystem.*;
	import flash.filesystem.File;
	import flash.net.FileFilter;
	import flash.utils.*;
	
	import org.osflash.signals.Signal;
	
    final public class MeshWriter extends Object
    {
		private var _file:File;
		private var _data:Mesh;
	
        public function MeshWriter(data:Mesh)
        {
			_data = data;
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
			filestream.writeUTF(buildMtl());
			filestream.close();
		}

		private function buildMtl():String {
			if(_data){
				if(_data.material is BitmapMaterial){
					var mtl:BitmapMaterial = BitmapMaterial(_data.material);
					var mtlText:String = "newmtl " + mtl.name +"\n";
					mtlText += "type bitmap" + "\n";
					mtlText += "d " + mtl.alpha + "\n";
					mtlText += "ka " + mtl.ambientColor + "\n";
					if(mtl.diffuseMethod){
						mtlText += "kd " + mtl.diffuseMethod.diffuseColor + "\n";
					}	
					if(mtl.specularMethod){
						mtlText += "ks " + mtl.specularMethod.specularColor + "\n";
					}
				}				
			}			
			return mtlText;
		}
    }
}
