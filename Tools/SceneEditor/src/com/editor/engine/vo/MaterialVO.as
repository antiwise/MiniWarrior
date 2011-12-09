package com.editor.engine.vo
{
	import com.editor.engine.interfaces.IValueObject;
	
	
	
	public class MaterialVO implements IValueObject
	{
		public var id : String ;
		public var name : String ;
		public var properties : Array = [ ] ;
	}
}