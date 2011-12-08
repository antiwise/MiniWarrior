package com.editor.components.utils
{
	import org.aswing.DefaultListCell;
	import org.aswing.*;

	public class IconListCell extends DefaultListCell
	{
		public function IconListCell()
		{
			super();
		}
		
		override public function setCellValue(value:*) : void {
			if(this.value != value){
				this.value = value;
				getJLabel().setText(value.toString());
				getJLabel().setIcon(new ColorIcon(new ASColor(0xFF0000), 10, 10));
			}
		}
		
	}
}