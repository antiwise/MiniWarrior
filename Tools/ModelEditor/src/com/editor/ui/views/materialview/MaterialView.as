package com.editor.ui.views.materialview
{
	
	import away3d.materials.MaterialBase;
	import away3d.materials.BitmapMaterial;;
	
	import com.editor.components.JRegisterPanel;
	import com.editor.utils.AGALRegister;
	import com.editor.utils.VectorRegisterConstant;
	
	import org.aswing.*;
	import org.aswing.event.TreeSelectionEvent;
	import org.aswing.tree.*;
	import org.aswing.border.TitledBorder;
	
	public class MaterialView extends JRegisterPanel
	{
		private var _tree:JTree;
		
		public function MaterialView() {
			
			super( "Material", "----Material Attribute----", EditAttributePopUp );
			addRegister( new VectorRegisterConstant("AA"));
				
			_tree = new JTree();
			var treeborder:JPanel = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			treeborder.setBorder( new TitledBorder( null, "Material" ) );
			treeborder.append(new JScrollPane(_tree), BorderLayout.CENTER);
			contentPanel.append(treeborder);
		}
		
		override public function set material( value:MaterialBase ):void {
			
			_material = value;		
			initMaterialTree();
			removeAllRegisters();
		}
		
		override protected function createRegister():AGALRegister {
			var attribute:VectorRegisterConstant= new VectorRegisterConstant( "new attribute");
			return attribute;
		}
		
		override protected function removeRegister( register:AGALRegister ):void {
			//_material.removeVertexAttribute( register as VertexAttribute );
		}
		
		private function initMaterialTree():void{

			if(_material is BitmapMaterial){
				var mat:BitmapMaterial = BitmapMaterial(_material);
				var root:DefaultMutableTreeNode = new DefaultMutableTreeNode(createItem(_material.name));
				var parent:DefaultMutableTreeNode;
				var mothednode:DefaultMutableTreeNode = new DefaultMutableTreeNode(createItem("Mathods"));
				root.append(mothednode);
				if(mat.ambientMethod)
					mothednode.append(new DefaultMutableTreeNode(createItem("ambientMethod")));
				if(mat.diffuseMethod)
					mothednode.append(new DefaultMutableTreeNode(createItem("diffuseMethod")));
				if(mat.specularMethod)
					mothednode.append(new DefaultMutableTreeNode(createItem("specularMethod")));
				if(mat.shadowMethod)
					mothednode.append(new DefaultMutableTreeNode(createItem("shadowMethod")));
				
				for(var i:uint=0; i<mat.numMethods; i++){
					mothednode.append(new DefaultMutableTreeNode(createItem(String(mat.getMethodAt(i)))));
				}
				if(mat.lights){
					var lightnode:DefaultMutableTreeNode = new DefaultMutableTreeNode(createItem("Mathods"));
					root.append(lightnode);
					for(i=0; i<mat.lights.length; i++){
						lightnode.append(new DefaultMutableTreeNode(createItem(String(mat.lights[i]))));
					}
				}			
				
				parent = new DefaultMutableTreeNode(createItem("BothSides"));
				root.append(parent);
				parent = new DefaultMutableTreeNode(createItem("Blending"));
				root.append(parent);
				parent = new DefaultMutableTreeNode(createItem("BlendMode"));
				root.append(parent);
				parent = new DefaultMutableTreeNode(createItem("Repeat"));
				
				root.append(parent);
				var model:DefaultTreeModel = new DefaultTreeModel(root);	
			}
			
			_tree.setName("Test");
			_tree.setModel(model);
			_tree.setFixedCellWidth(100);			
			_tree.addEventListener(TreeSelectionEvent.TREE_SELECTION_CHANGED, Selected);
		}
		
		private function createItem(value:String):*{
			return value;
		}
		private function Selected(e:TreeSelectionEvent):void{
			trace(e.getNewLeadSelectionPath());
		}
	}
}