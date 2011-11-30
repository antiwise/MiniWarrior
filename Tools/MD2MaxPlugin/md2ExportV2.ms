-------------------------------------------------------------------------
--MD2 Exporter V2.0
--Version 2.0 for 3ds Max 2012
--Adam Barton (adam@add3d.co.uk)
--Updated by Ruiner (http://www.netdoo.com/wodx)
--New: Added Export Support for multiple object/mesh selections
-------------------------------------------------------------------------
fileIn "MD5MaterialExporter.ms"	-- MD5MaterialExporter functions

global gmaterials = #();
global gframename = "idle..........."
global gAnimations = #()

global md2lastfile=""
global MD2Export

fn addcheck cb=
(
	if classof cb==string do messagebox cb title:"MD2 Export Error"
)

struct MD2_Tex
(
	s,t
)
	
struct MD2_Vertex
(
	pos, lightNormalIndex
)

struct MD2_Frame
(
	scale, translate, name,
	vList	
)

struct MD2_Tri
(
	v1,v2,v3,				--vertIndices
	t1,t2,t3				--texCoordIndices
)

struct MD2_model
(
	skinlist,
	texlist,
	trilist,
	framelist
)

struct AnimS
(
	name,
	start,
	end,
	step
)

struct SkinS
(
	mat,
	vstart,
	count
)

fn hasMaterial ms m =
(
	for mtl in ms do
	(
		if m == mtl do return true
	)
	return false
)

fn roundNearZeroF f =
(
	if (f > 0.0) and (f <= 0.00009999999) then
		do f *= 10 while f <= 0.00009999999
	else if (f >= -0.0001111111) and (f < 0.0) do
		do f *= 10 while (f >= -0.0001111111)
	
	return f
)
	
fn makemd2 object=
(	
	
	md2=md2_model skinlist:#() texlist:#() trilist:#() framelist:#()

	------------------------------------
	-- build skin material names list
	------------------------------------  
	local vnum
	vnum = 0
	for k = 1 to $selection.count do 
	(
		obj =	$selection[k]			
		
		local num = obj.numverts
		skn = SkinS()		
		local isok = hasMaterial gmaterials obj.material
		if isok == false do
		(
			append gmaterials obj.material
		)
		skn.mat = obj.material
		skn.vstart = vnum
		skn.count = num;
		vnum = vnum + num
		append md2.skinlist skn	
	)
	
	------------------------------------
	-- build texture vert list
	------------------------------------  
	
	for k = 1 to $selection.count do 
	(
		obj=	$selection[k]
		
		for i=1 to getnumtverts obj do
		(
			s=roundNearZeroF ( (getTVert obj i).x )
			t=roundNearZeroF ( 1-(getTVert obj i).y )
			
			append md2.texlist (md2_tex s:s t:t)
		)
	)

	------------------------------------
	-- build triangle list
	------------------------------------
	
	total = 0
	total2 = 0

	for k = 1 to $selection.count do 
	(
		obj=	$selection[k]
		temp = 0
		temp2 = 0
		
		for i=1 to obj.numfaces do
		(
			v1=((getFace obj i).z)-1
			v2=((getFace obj i).y)-1
			v3=((getFace obj i).x)-1
			
			if v1 > temp do
			(
				temp = v1
			)	
			if v2 > temp do
			(
				temp = v2
			)
			if v3 > temp do
			(
				temp = v3
			)
			
			v1 = v1 +total
			v2 = v2 +total
			v3 = v3 +total
			
			t1 = 0
			t2 = 0
			t3 = 0
			
			try
			(
				t1=((getTVFace obj i).z)-1
				t2=((getTVface obj i).y)-1
				t3=((getTVface obj i).x)-1
			)
			catch
			(
				print "------------------------"
				print obj.name
				print "------------------------"
			)

			if t1 > temp2 do
			(
				temp2 = t1
			)
			if t2 > temp2 do
			(
				temp2 = t2
			)
			if t3 > temp2 do
			(
				temp2 = t3
			)

			t1 = t1 + total2
			t2 = t2 + total2
			t3 = t3 + total2
			

			append md2.trilist (md2_Tri v1 v2 v3 t1 t2 t3)

			if i == obj.numfaces do
			(
				total = total + temp + 1
				total2 = total2 + temp2 + 1
			)
		)
	)


	-------------------------------------
	-- build frame list
	-------------------------------------
	for anim in gAnimations do
	(
		astart = anim.start
		aend = anim.end
		for f=astart to aend by anim.step do 
		(
			local vertlist=#()
			frame=MD2_frame scale:[1,1,1] translate:[0,0,0] name:anim.name
			vmin=[0,0,0]
			vmax=[0,0,0]
				
			for k = 1 to $selection.count do 
			(
				obj=	$selection[k]	
				for i=1 to obj.numverts do at time f
				(
					local v=in coordsys world(getvert obj i)
					if v.x<vmin.x do vmin.x=v.x
					if v.y<vmin.y do vmin.y=v.y
					if v.z<vmin.z do vmin.z=v.z
					if v.x>vmax.x do vmax.x=v.x
					if v.y>vmax.y do vmax.y=v.y
					if v.z>vmax.z do vmax.z=v.z	
				)
			)
			frame.translate=vmin
			frame.scale=(vmax-vmin)/[65535,65535,65535]
		
			for k = 1 to $selection.count do 
			(
				obj=	$selection[k]
				for i=1 to obj.numverts do at time f
				(
					local v=in coordsys world (getvert obj i)
					v-=frame.translate
					v/=frame.scale
					append vertlist v
				)
			)
			frame.vList=vertlist
			append md2.framelist frame
		)
	)
		
	md2
)


fn writemd2 obj md2file=
(
	if obj==undefined do return"No object"
	
	for k = 1 to $selection.count do 
	(
		obj=	$selection[k]
		if (classof obj)!=editable_mesh do return "Object is not a mesh"
	)
	
	model=makemd2 obj


	f=fopen md2file "wb"
	
	----------------------------------------
	-- write the header
	----------------------------------------
	
	magic=844121161
	version=8
	skinw=256
	skinh=256
	framenum=model.framelist.count
	skinnum=model.skinlist.count
	vertnum=model.framelist[1].vlist.count
	texnum=model.texlist.count
	trinum=model.trilist.count
	glnum=0
	
	texsize=8
	trisize=12
	framesize=40+(6*vertnum)
	
	headersize=68				-- bytes in header
	offsetskins=headersize
	offsettexcoords=offsetskins+(skinnum*(64 + 4))
	offsettris=offsettexcoords+(texnum*texsize)
	offsetframes=offsettris+(trinum*trisize)
	offsetgl=offsetframes+(framenum*framesize)
	offsetend=offsetgl+(glnum*4)
	
	writelong f magic
	writelong f version
	writelong f skinw				--(blitz dont care)
	writelong f skinh				--(blitz dont care)
	writelong f framesize
	writelong f skinnum
	writelong f vertnum
	writelong f texnum
	writelong f trinum
	writelong f glnum
	writelong f framenum
	writelong f offsetskins		--offset to skins
	writelong f offsettexcoords	--offset to texture coords
	writelong f offsettris		--offset to triangles
	writelong f offsetframes		--offset to frames
	writelong f offsetgl			--offset to gl commands
	writelong f offsetend			--offset to end

	-----------------------------------------------------
	-- end of header
	-----------------------------------------------------
	
	-----------------------------------------------------
	-- write out the skin names
	-----------------------------------------------------
	
	for i in model.skinlist do
	(
		skinname=i.mat.name
		while skinname.count<63 do skinname+=":"
		writestring f skinname
		writeshort f i.vstart #unsigned
		writeshort f i.count  #unsigned
	)

	-----------------------------------------------------
	-- write texture coordinate list
	-----------------------------------------------------
	
	for i in model.texlist do
	(
		writefloat f i.s
		writefloat f i.t
	)
	
	-----------------------------------------------------
	--write triangles
	-----------------------------------------------------	

	for i in model.trilist do
	(
		writeshort f i.v1 #unsigned
		writeshort f i.v2 #unsigned
		writeshort f i.v3 #unsigned
		writeshort f i.t1 #unsigned
		writeshort f i.t2 #unsigned
		writeshort f i.t3 #unsigned
	)

	-----------------------------------------------------
	-- write frames
	-----------------------------------------------------
	
	for i in model.framelist do
	(
		writefloat f i.scale.x
		writefloat f i.scale.y
		writefloat f i.scale.z
		writefloat f i.translate.x
		writefloat f i.translate.y
		writefloat f i.translate.z
		writestring f i.name
		k=0
		for j=1 to i.vlist.count do
		(
			writeshort f i.vlist[j].x #unsigned
			writeshort f i.vlist[j].y #unsigned
			writeshort f i.vlist[j].z #unsigned
		)
	)
	------------------------------------------------------
	-- end of file writing
	------------------------------------------------------
	
	fclose f
)


utility MD2Export "MD2 Exporter V3.0"
(
	group "Export"
	(
		checkbox oneframe "Save Animation" checked:true align:#left
		spinner stepframe "Frame Step" range:[1,200,1] fieldwidth:40 type:#integer
		spinner animstart range:[0,1000,0] type:#integer fieldwidth:40 across:2 align:#left
		spinner animend "to" range:[0,1000,40] type:#integer fieldwidth:40
	
		edittext 	frameName "Frame Name:" labelOnTop:true
		
		button btnAddFrame "Add" pos:[12,140] width:56 height:18
		button btnRemoveFrame "Remove" pos:[80,140] width:64 height:18
		
		listbox lbxNodes "Frame to export:" pos:[8,170] width:144 height:8
		
		
		button bexport "     Export    "
	)
	
	group "About"
	(
		label titleLabel	"MD2 Exporter v3.0"
	)
	
	on btnAddFrame pressed do
	(
		local fname = "..............."
	
		if (frameName.text == "" or frameName.text == undefined) then
		(
			Messagebox "Please give a frame name !"
		)
		else
		(
			local i ;		
			i = 1 ;		
			while (frameName.text[i] != undefined) do 
			(
				fname[i] = frameName.text[i]	
				i = i + 1
			)
		)
				
		if((findItem lbxNodes.items fname) == 0) then
		(
			anim = AnimS()
			anim.name=fname
		
			if (oneframe.state) then 
			(
				anim.start=animstart.value 
				anim.end=animend.value
			)
			else
			(
				anim.start=1
				anim.end=1				
			)
		
			anim.step = stepframe.value
			append gAnimations anim
			lbxNodes.items += #(fname)
		)
		else
		(
			Messagebox "the frame has the same name !"
		)
	)
	
	on btnRemoveFrame pressed do
	(
		if (lbxNodes.items.count > 0) and (lbxNodes.selection > 0) do
		(
			local i ;		
			i = 1 ;	
			for anim in gAnimations do
			(
				if (anim.name == lbxNodes.items[lbxNodes.selection]) then
				(			
						deleteItem gAnimations i
				)
				i = i + 1
			)
			
			deleteItem lbxNodes.items lbxNodes.selection
			lbxNodes.items = lbxNodes.items
		)
	)
	
	on bexport pressed do
	( 
		if not(oneframe.state) do 
		(
			anim = AnimS()
			anim.name=gframename
			anim.start=1
			anim.end=1
			anim.step = stepframe.value
			append gAnimations anim
		)
			
		md2filename=getsavefilename caption:"Save MD2" filename:md2lastfile types:"Blitz MD2 (*.md2)|*.md2|All Files (*.*)|*.*|"
			
		if md2filename!=undefined do 
		(				
			addcheck (writemd2 $ md2filename)
			md2lastfile=md2filename
			-- export material
			local mtlfullfile = md2filename
			append mtlfullfile ".mtl"
			exportSelectMaterials gmaterials mtlfullfile
		)
	)
)