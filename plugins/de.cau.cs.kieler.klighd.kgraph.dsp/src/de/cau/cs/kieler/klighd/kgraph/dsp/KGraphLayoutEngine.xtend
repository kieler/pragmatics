/*
 * Copyright (C) 2017 TypeFox and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig
import io.typefox.sprotty.api.SGraph
import io.typefox.sprotty.api.SModelRoot
import io.typefox.sprotty.layout.ElkLayoutEngine
import java.io.ByteArrayOutputStream
import java.util.ArrayList
import org.apache.log4j.Logger
import org.eclipse.elk.core.LayoutConfigurator
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class KGraphLayoutEngine extends ElkLayoutEngine {
        
    @Inject KGraphDiagramState diagramState
	
	static val LOG = Logger.getLogger(KGraphLayoutEngine)
	
	override layout(SModelRoot root) {
	    if (root instanceof SGraph) {
	        
            val kgraphContext = diagramState.getKGraphContext(root.id)
            
            // layout of KGraph
            // TODO use Layout configuration described by user, not hard coded
            val lightDiagramLayoutConfig = new LightDiagramLayoutConfig(kgraphContext)
            val configurator = new LayoutConfigurator
//            configurator.configure(ElkGraphElement)
//                .setProperty(CoreOptions.DIRECTION, Direction.RIGHT)
//                .setProperty(CoreOptions.SPACING_NODE_NODE, 100.0)
//                .setProperty(CoreOptions.SPACING_EDGE_NODE, 30.0)
//                .setProperty(CoreOptions.SPACING_EDGE_EDGE, 15.0)
//                .setProperty(LayeredOptions.SPACING_EDGE_NODE_BETWEEN_LAYERS, 30.0)
//                .setProperty(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, 100.0)
//                .setProperty(CoreOptions.PADDING, new ElkPadding(50))
            
            var configurators = new ArrayList
            configurators.add(configurator)
            lightDiagramLayoutConfig.options(configurators)
            
            
            lightDiagramLayoutConfig.performLayout
            
            // TODO: calculate absolute positions for KRenderings with GridLayout and similar structures. Add them as properties
            
            // map layouted KGraph to SGraph
            KGraphMappingUtil.mapLayout(diagramState.getElementMapping(root.id))
	        
	    }
	}
    
    
	
	override protected applyEngine(ElkNode elkGraph) {
		if (LOG.isTraceEnabled)
			LOG.info(elkGraph.toXMI)
		super.applyEngine(elkGraph)
	}
	
	private def toXMI(ElkNode elkGraph) {
		val resourceSet = new ResourceSetImpl
		val resource = resourceSet.createResource(URI.createFileURI('output.elkg'))
		resource.contents += elkGraph
		val outputStream = new ByteArrayOutputStream
		resource.save(outputStream, emptyMap)
		return outputStream.toString
	}
	
}