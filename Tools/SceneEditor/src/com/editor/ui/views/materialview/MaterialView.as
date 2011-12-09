package com.editor.ui.views.materialview
{
	import away3d.lights.PointLight;
	import away3d.lights.DirectionalLight;
	import away3d.materials.BitmapMaterial;
	import away3d.materials.MaterialBase;
	
	import com.editor.components.*;
	import com.editor.components.utils.ColorIcon;
	import com.editor.utils.AGALRegister;
	import com.editor.utils.VectorRegisterConstant;
	
	import flash.display.BitmapData;
	import flash.geom.Point;
	
	import org.aswing.*;
	import org.aswing.border.TitledBorder;
	import org.aswing.event.AWEvent;
	import org.aswing.event.FrameEvent;
	import org.aswing.event.TreeSelectionEvent;
	import org.aswing.geom.IntPoint;
	import org.aswing.tree.*;
	
	public class MaterialView extends JTitledPanel
	{
		private var _image:JPanel;
		private var _diffuseBtn:JButton;
		private var _normalBtn:JButton;
		private var _speculerBtn:JButton;
		
		private var _light:JPanel;
		private var _lightBtn:JButton;
		
		private var _atrributes:JPanel;
		private var _gloss:JLabeledSlider;
		private var _specular:JLabeledSlider;
		private var _ambient:JLabeledSlider;
		private var _ambientColorBtn:JButton;
		
		protected var _material:MaterialBase;
		
		public function MaterialView() {
			
			super( "Material");
			setName( "Material" );
			
			_image = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			_image.setBorder( new TitledBorder( null, "Images" ) );
			
			_light = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			_light.setBorder( new TitledBorder( null, "Lights" ) );
			
			_atrributes = new JPanel( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			_atrributes.setBorder( new TitledBorder( null, "Atrributes" ) );
			
			contentPanel.setLayout( new SoftBoxLayout( SoftBoxLayout.Y_AXIS ) );
			contentPanel.append(_image);
			contentPanel.append(_light);
			contentPanel.append(_atrributes);

		}
		
		public function set material( value:MaterialBase ):void {
			
			_material = value;		
			initMaterialTree();
		}
		
		private function initMaterialTree():void{

			if(_material is BitmapMaterial){
				
				if(_diffuseBtn)_image.remove(_diffuseBtn);
				if(_normalBtn)_image.remove(_normalBtn);
				if(_speculerBtn)_image.remove(_speculerBtn);
			
				_diffuseBtn = new JButton( "diffuse method");
				_normalBtn = new JButton( "normal method" );
				_speculerBtn = new JButton( "speculer method" );
			
				_image.append( _diffuseBtn );
				_image.append( _normalBtn );
				_image.append( _speculerBtn );
				
				var mat:BitmapMaterial = BitmapMaterial(_material);
				_diffuseBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
					var pop:EditSamplerPopUp = new EditSamplerPopUp( mat.diffuseMethod.bitmapData );
					pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
					pop.imageChangedSignal.add(onDiffuseImageChanged);
					centerPopUp( pop );
				});	
				
				_normalBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
					var pop:EditSamplerPopUp = new EditSamplerPopUp( mat.normalMap );
					pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
					pop.imageChangedSignal.add(onNormalImageChanged);
					centerPopUp( pop );
				});
				
				_speculerBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
					var pop:EditSamplerPopUp = new EditSamplerPopUp( mat.specularMap );
					pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
					pop.imageChangedSignal.add(onSpeculerImageChanged);
					centerPopUp( pop );
				});
				
				if(_lightBtn)_light.remove(_lightBtn);
				_lightBtn = new JButton( "Light" );
				_light.append( _lightBtn );
				
				_lightBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
					var pop:ColorSetterPopUp = new ColorSetterPopUp("LightColor");
					pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
					pop.colorpanel.colorChangedSignal.add(onColorChanged);
					centerPopUp( pop );
				});
				
				if(_gloss)_atrributes.remove(_gloss);
				if(_specular)_atrributes.remove(_specular);
				if(_ambient)_atrributes.remove(_ambient);
				if(_ambientColorBtn)_atrributes.remove(_ambientColorBtn);
				
				_gloss = new JLabeledSlider( "gloss","gloss", mat,new Point( 0, 100 ));
				_specular = new JLabeledSlider( "specular","specular", mat,new Point( 0, 2 ));
				_ambient = new JLabeledSlider( "ambient","ambient", mat,new Point( 0, 10 ));

				_ambientColorBtn = new JButton( "Ambient Color" );			
				
				_atrributes.append( _gloss );
				_atrributes.append( _specular );
				_atrributes.append( _ambient );
				_atrributes.append( _ambientColorBtn );
				
				_ambientColorBtn.addEventListener( AWEvent.ACT, function( event:AWEvent ):void {
					var pop:ColorSetterPopUp = new ColorSetterPopUp("Ambient Color");
					pop.addEventListener( FrameEvent.FRAME_CLOSING, popUpClosedHandler );
					pop.colorpanel.colorChangedSignal.add(onAmbientColorChanged);
					centerPopUp( pop );
				});				
			}
		}
		
		private function onAmbientColorChanged( image:uint):void {	
			if(_material is BitmapMaterial){
				var mat:BitmapMaterial = BitmapMaterial(_material);
				mat.ambientColor = image;
			}
		}
		
		private function onColorChanged( image:uint):void {	
			if(_material && _material.lights){
				if(_material.lights[0] is DirectionalLight){
					DirectionalLight(_material.lights[0]).color = image;					
				}
				else if(_material.lights[0] is PointLight){
					PointLight(_material.lights[0]).color = image;					
				}
			}
		}
		
		private function onDiffuseImageChanged( image:BitmapData):void {
			if(_material is BitmapMaterial){
				var mat:BitmapMaterial = BitmapMaterial(_material);
				mat.diffuseMethod.bitmapData = image;
			}
		}
		
		private function onNormalImageChanged( image:BitmapData):void {
			if(_material is BitmapMaterial){
				var mat:BitmapMaterial = BitmapMaterial(_material);
				mat.normalMap = image;
			}
		}
		
		private function onSpeculerImageChanged( image:BitmapData):void {
			if(_material is BitmapMaterial){
				var mat:BitmapMaterial = BitmapMaterial(_material);
				mat.specularMap = image;
				mat.specularMethod.invalidateBitmapData();
				mat.specularMethod.invalidateShaderProgram();
			}
		}
		private function popUpClosedHandler( event:FrameEvent ):void {
			var target:EditSamplerPopUp = event.target as EditSamplerPopUp;
		}
		
		private function centerPopUp( popUp:JFrame ):void {
			popUp.setLocation( new IntPoint(
				stage.stageWidth / 2 - popUp.width / 2,
				stage.stageHeight / 2 - popUp.height / 2
			) );
		}
	}
}