package com.editor.engine.utils
{
	public class NameGeneration
	{
		public static function uuid():String {
			var specialChars:Array = new Array('8', '9', 'A', 'B');
			return createRandomIdentifier(8, 15) + '-' + createRandomIdentifier(4, 15) + '-4' + createRandomIdentifier(3, 15) + '-' + specialChars[randomIntegerWithinRange(0, 3)] + createRandomIdentifier(3, 15) + '-' + createRandomIdentifier(12, 15);
		}// ActionScript file
		
		private static function createRandomIdentifier(length:uint, radix:uint = 61):String {
			var characters:Array = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
			var id:Array  = new Array();
			radix = (radix > 61) ? 61 : radix;
			while (length--) {
				id.push(characters[randomIntegerWithinRange(0, radix)]);
			}
			
			return id.join('');
		}

		private static function randomIntegerWithinRange(min:int, max:int):int {
			return Math.floor(Math.random() * (1 + max - min) + min);
		}
		
	}
}
