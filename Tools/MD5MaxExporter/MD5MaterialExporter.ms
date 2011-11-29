--------------------------------
-- return the length of an array
--------------------------------
function arrayLength a = 
(
	local i ;
	i = 1 ;
	while (a[i] != undefined) do
		i = i + 1 ;	
	i-1 ;
)

-- write into "outfile" the selected MD5Material
-- precondition: iskindof material MD5MAterial == true.
-- Use export MD5Material.
function writeMD5Material material outFile =
(
	local t, matname, mat;

	mat = material ;
	
	if (mat != undefined) then 
	(
		matname = material.name ;	
		format "material %\n" matname to:outFile ;
		format("Ka % % %\n") (mat.ambient.r/255.0) (mat.ambient.g/255.0) (mat.ambient.b/255.0) to:outFile ;
		if ((mat.diffusemap != undefined) and (iskindof mat.diffusemap Color_Correction)) then(
			format("Kd % % %\n") (mat.diffusemap.color.r/255.0) (mat.diffusemap.color.g/255.0) (mat.diffusemap.color.b/255.0) to:outFile ;
		)
		else(
			format("Kd % % %\n") (mat.diffuse.r/255.0) (mat.diffuse.g/255.0) (mat.diffuse.b/255.0) to:outFile ;
		)	  
    format("Ks % % % %\n") (mat.specular.r/255.0) (mat.specular.g/255.0) (mat.specular.b/255.0) (mat.specularLevel*100.0) to:outFile ;
		--format("emissive % % %\n") (mat.selfIllumColor.r/255.0) (mat.selfIllumColor.g/255.0) (mat.selfIllumColor.b/255.0) to:outFile ;
	)
	if ((mat.diffusemap != undefined) and (iskindof mat.diffusemap Bitmaptexture)) then
	(	
		t = filterstring mat.diffusemap.filename "\\" ;
		format "map_Kd % \n" t[arrayLength t] to:outFile ;
	)
)

function exportMD5Material material outName =
(
    local outFile ;
	if (classof material == MD5Material or classof material == MD5Material) then
	(
	    outFile = createfile outName ;
		writeMD5Material material outFile ;
		close outFile ;
		messageBox "Exporting material successful !" ;
	)
	else
	(
		messageBox "You have to choose an MD5Material." ;
	)
)

---------------------------------------------------------------
-- Writes a standard material in a material script
-- prefix will be before material name. (prefix+name)
-- used for multi material
---------------------------------------------------------------
function writeStandardMaterial material prefix outFile =
(
	writeMD5Material material outFile
)

---------------------------------------------------------------
-- Exports a standard material
-- PRE: material is a standard material
-- Use exportMaterial function.
---------------------------------------------------------------
function exportStandardMaterial material outFile =
(
	local sub,i,matname ;
	matname = material.name ;
	writeStandardMaterial material "" outFile ;
)


---------------------------------------------------------------
-- Exports a multi material
-- PRE: material is a multi material
-- Use exportMaterial function.
---------------------------------------------------------------
function exportMultiMaterial material outFile=
(
	local sub,i,matname ;

	matname = material.name ;
	
	i=0 ;
	for sub in material.materiallist do (
		i+=1 ;
		writeStandardMaterial sub (material.name+"/") outFile ;
		format "\n" to:outFile ;
	)
)



---------------------------------------------------------------
-- Exports a material
-- call the fonction linked to the material type
---------------------------------------------------------------
function exportMaterial material outname =
(
	local outFile ;
	
	outFile = createfile outName ;
		
	if (material!=undefined and classof material == MultiMaterial) then
	(
		exportMultiMaterial material outFile ;
	)
	else 
	(
		if (material!=undefined and classof material == StandardMaterial) then
		(
			exportStandardMaterial material outFile ;
		)
		else
		(
			messageBox "You have to choose a Standard Material or a MultiMaterial." ;
			return 0;
		)
	)
	
	close outFile ;
	
	messageBox "Exporting material successful !" ;
)


---------------------------------------------------------------
-- Exports select materials used in the scene
---------------------------------------------------------------
function exportSelectMaterials mtls outname =
(
	local outFile ;
	
	outFile = createfile outName ;
	
	for material in mtls do
	(			
		if (material!=undefined and classof material == MultiMaterial) then
		(
			exportMultiMaterial material outFile ;		
		)
		else (
			if (material!=undefined and classof material == StandardMaterial) then
			(
				exportStandardMaterial material outFile ;
			)
		)
		format "\n" to:outFile ;
	)		

	close outFile ;

	messageBox "Exporting all materials in the scene successful !" ;
)

