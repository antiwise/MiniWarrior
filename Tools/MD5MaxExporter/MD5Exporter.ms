-- Developed by Jonathan "BeRSeRKeR" Garcia

fileIn "MD5MeshExporter.ms"		-- The mesh exporter
fileIn "MD5AnimExporter.ms"		-- The animation exporter
fileIn "MD5CameraExporter.ms"	-- The camera exporter

global gAnimations = #()
struct AnimS
(
	name,
	start,
	end,
	step
)
rollout roMD5MeshAnim "Mesh & Animation" width:160 height:584
(
	-- Order of the rollout components matter! --
	
	spinner spnBaseFrame "Frame Step" range:[1,200,1] fieldwidth:40 type:#integer	
	spinner spnStartFrame range:[0,1000,0] type:#integer fieldwidth:40 across:2 align:#left
	spinner spnEndFrame "to" range:[0,1000,40] type:#integer fieldwidth:40
	edittext 	frameName "Frame Name:" labelOnTop:true
	button btnAddFrame "Add" pos:[12,90] width:64 height:18
	button btnRemoveFrame "Remove" pos:[80,90] width:64 height:18
	listbox animNodes "Frame to export:" pos:[8,120] width:140 height:8
	
  listbox lbxNodes "Nodes to export:" pos:[8,280] width:144 height:8
	-- "Add" button filter function
	fn filterFn node =
	(
		-- Skip objects already in the list of nodes and non geometric objects
		-- Particle nodes are geometric objects so we discard them here!
		return
		(
			((findItem lbxNodes.items node.name) == 0) and (superClassOf node == GeometryClass) and
			(classOf node != BoneGeometry) and (classOf node != Biped_Object) and
			(classOf node != Blizzard) and (classOf node != PF_Source) and (classOf node != Spray) and
			(classOf node != PCloud) and (classOf node != Snow) and
			(classOf node != PArray) and (classOf node != SuperSpray)
		)
	)
	pickbutton btnPickNode "Add" pos:[16,260] width:64 height:18 enabled:true message:"" filter:filterFn toolTip:""
	button btnRemove "Remove" pos:[80,260] width:64 height:18

	radiobuttons rdoOptions "" pos:[16,420] width:127 height:48 labels:#("Export MD5Mesh only", "Export MD5Anim only", "Export both")
	button btnExport "---> Export <---" pos:[32,480] width:96 height:18


	
	-- animationRange callback function
	fn roMD5MeshAnim_updateFrames_cb =
	(
		-- New animation ranges
		local start = animationRange.start.frame as Integer
		local end = animationRange.end.frame as Integer

		-- When the spinners range changes, the values are reset to 0
		-- so save the current spinners values...
		local baseFrame = spnBaseFrame.value
		local startFrame = spnStartFrame.value
		local endFrame = spnEndFrame.value

		-- Update spinners ranges
		spnBaseFrame.range = spnStartFrame.range = spnEndFrame.range = [start, end, 0]

		-- Restore the previous values
		spnBaseFrame.value = baseFrame
		spnStartFrame.value = startFrame
		spnEndFrame.value = endFrame
	)

	on roMD5MeshAnim open do
	(
		local start = animationRange.start.frame as Integer
		local end = animationRange.end.frame as Integer
	
		spnBaseFrame.range = spnStartFrame.range = spnEndFrame.range = [start, end, 0]
	
		spnBaseFrame.value = start
		spnStartFrame.value = start + 1
		spnEndFrame.value = end

		rdoOptions.state = 3
		
		-- Register animationRange callback function
		registerTimeCallback roMD5MeshAnim_updateFrames_cb
	)

	on roMD5MeshAnim close do
	(
		-- Unregister animationRange callback function
		unRegisterTimeCallback roMD5MeshAnim_updateFrames_cb
	)

	on btnPickNode picked node do
	(
		lbxNodes.items += #(node.name)
	)

	on btnRemove pressed do
	(
		if (lbxNodes.items.count > 0) and (lbxNodes.selection > 0) do
		(
			deleteItem lbxNodes.items lbxNodes.selection
			lbxNodes.items = lbxNodes.items
		)
	)
	
	on btnAddFrame pressed do
	(
		local fname
	
		if (frameName.text == "" or frameName.text == undefined) then
		(
			Messagebox "Please give a frame name !"
		)
		else
		(
			fname = frameName.text
		)
				
		if((findItem animNodes.items fname) == 0) then
		(
			anim = AnimS()
			anim.name=fname
			anim.start=spnStartFrame.value 
			anim.end=spnEndFrame.value
			anim.step = spnBaseFrame.value
			
			append gAnimations anim
			animNodes.items += #(fname)
		)
		else
		(
			Messagebox "the frame has the same name !"
		)
	)
	
	on btnRemoveFrame pressed do
	(
		if (animNodes.items.count > 0) and (animNodes.selection > 0) do
		(
			local i ;		
			i = 1 ;	
			for anim in gAnimations do
			(
				if (anim.name == animNodes.items[animNodes.selection]) then
				(			
						deleteItem gAnimations i
				)
				i = i + 1
			)
			
			deleteItem animNodes.items animNodes.selection
			animNodes.items = animNodes.items
		)
	)
	
	on btnExport pressed do
	(
		local bUpdate = false
	
		-- Check that base frame doesn't exceed the animation range
		if (spnBaseFrame.value < animationRange.start.frame) or (spnBaseFrame.value > animationRange.end.frame) do
		(
			messageBox "Base frame out of range!" title: "Warning"
			bUpdate = true
		)
	
		-- Check that start frame doesn't exceed the animation range
		if (spnStartFrame.value < animationRange.start.frame) or (spnStartFrame.value > animationRange.end.frame) do
		(
			messageBox "Start frame out of range!" title: "Warning"
			bUpdate = true
		)
	
		-- Check that end frame doesn't exceed the animation range
		if (spnEndFrame.value < animationRange.start.frame) or (spnEndFrame.value > animationRange.end.frame) do
		(
			messageBox "End frame out of range!" title: "Warning"
			bUpdate = true
		)
		
		-- If MAX animation range changes we have to update our sliders
		if bUpdate == true do
		(
			local start = animationRange.start.frame as Integer
			local end = animationRange.end.frame as Integer
	
			spnBaseFrame.range = spnStartFrame.range = spnEndFrame.range = [start, end, 0]
	
			spnBaseFrame.value = start
			spnStartFrame.value = start + 1
			spnEndFrame.value = end

			messageBox "Frame range configuration updated" title: "Info"
	
			return undefined
		)
	
		-- Is there some node to export?
		if lbxNodes.items.count <= 0 do
		(
			messageBox "Nothing to export!" title: "Warning"
			return undefined
		)
	
		-- Collect selected nodes
		local nodeList = #()
		for nodeName in lbxNodes.items do
		(
			node = getNodeByName nodeName
			append nodeList node
		)
	
		-- Export options
		case rdoOptions.state of
		(
			1:
			(
				-- Let the user select the md5mesh output file
				local meshFileName = getSaveFileName caption:"Save Doom III MD5Mesh" types:"Doom III MD5Mesh (*.md5mesh)|*.md5mesh"
	
				if meshFileName != undefined do
				(
					md5Exp = MD5MeshExporter()
					local bOk = md5Exp.doExport nodeList meshFileName
	
					if bOk == true do
						messageBox "MD5Mesh exported successfully!" title: ".::MD5Exporter::."
				)
			)
			2:
			(

				local animFileName = getSavePath() 
				md5Exp = MD5AnimExporter()
				if animFileName != undefined do
				(
					for anim in gAnimations do
					(
						local filename		
						filename = copy animFileName
						append filename "\\"
						append filename anim.name
						append filename ".md5anim"
					
						print "--------------------------------"
						print filename
						print "--------------------------------"
						
						local astart = anim.start
						local aend = anim.end
						
						local nBaseFrame = anim.step
						local animRange = interval astart aend
		
						-- Create an ObjectSet
						clearSelection()
						for node in nodeList do
						(
							-- Increment the selection
							selectMore node
						)	
						
						local bOk = md5Exp.doExport $selection nBaseFrame animRange filename
		
						if bOk == false do
							messageBox "MD5Anim exported fail!" title: ".::MD5Exporter::."
		
						clearSelection()	
					)			
				)

				messageBox "MD5Anim exported successfully!" title: ".::MD5Exporter::."
			)
			3:
			(
				local bOk = false
	
				-- Let the user select the md5mesh output file
				local meshFileName = getSaveFileName caption:"Save Doom III MD5Mesh" types:"Doom III MD5Mesh (*.md5mesh)|*.md5mesh"
				
				if meshFileName != undefined do
				(
					-- Export!
					md5Exp = MD5MeshExporter()
					bOk = md5Exp.doExport nodeList meshFileName
	
					if bOk == false do
						messageBox "MD5Mesh export failed!" title: ".::MD5Exporter::."
				)
	
				-- If MD5Mesh was exported, go for MD5Anim
				if bOk == true do
				(		
					local animFileName = getSavePath() 
					md5Exp = MD5AnimExporter()
					if animFileName != undefined do
					(
						for anim in gAnimations do
						(
							local filename		
							filename = copy animFileName
							append filename "\\"
							append filename anim.name
							append filename ".md5anim"
						
							print "--------------------------------"
							print filename
							print "--------------------------------"
							
							local astart = anim.start
							local aend = anim.end
							
							local nBaseFrame = anim.step
							local animRange = interval astart aend
			
							-- Create an ObjectSet
							clearSelection()
							for node in nodeList do
							(
								-- Increment the selection
								selectMore node
							)	
							
							local bOk = md5Exp.doExport $selection nBaseFrame animRange filename
			
							if bOk == false do
								messageBox "MD5Anim exported fail!" title: ".::MD5Exporter::."
			
							clearSelection()	
						)			
					)
	
					messageBox "MD5Anim exported successfully!" title: ".::MD5Exporter::."
				)
			)
		)
	)
)
-- Let the show begins! :P --

if md5Exporter != undefined do
(
	closeRolloutFloater md5Exporter
)
md5Exporter = newRolloutFloater "MD5 Exporter" 172 580
addRollout roMD5MeshAnim md5Exporter
