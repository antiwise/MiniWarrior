package com.editor.engine.interfaces
{
	public interface IPropertySerializer
	{
		
		/**
		 * @param valueXML this param should never be null
		 * @param pro the value of the pro model
		 */
		function decodeValue(valueXML:XML):void;
		
		/**
		 * @param value this param should never be null unless this type accept null value
		 * @param pro the value of the pro model
		 */
		function encodeValue():XML;
		
	}
}