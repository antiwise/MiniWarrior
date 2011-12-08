package  com.editor.utils
{
	public class RegisterConstant extends AGALRegister
	{
		public var mapping:RegisterMapping;
		public function RegisterConstant( name:String, value:*, mapping:* = null ) {
			this.mapping = mapping;
			super( name, value );
		}
	}
}
