/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
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
import com.google.gson.GsonBuilder
import de.cau.cs.kieler.klighd.kgraph.dsp.gson_utils.KGraphTypeAdapterUtil

/**
 * Handles the server side layout of KGraphs.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLayoutEngine.xtend">
 *      YangLayoutEngine</a>
 */
public class KGraphLayoutEngine extends ElkLayoutEngine {
        
    @Inject KGraphDiagramState diagramState
	
	public static val LOG = Logger.getLogger(KGraphLayoutEngine)
	
	override layout(SModelRoot root) {
	    if (root instanceof SGraph) {
	        
            val kGraphContext = diagramState.getKGraphContext(root.id)
            
            // layout of KGraph
            // TODO: use Layout configuration described by user, not hard coded
            val lightDiagramLayoutConfig = new LightDiagramLayoutConfig(kGraphContext)
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
            
//            println("Layout started!")
//            var startTime = System.currentTimeMillis
            lightDiagramLayoutConfig.performLayout
//            var endTime = System.currentTimeMillis
//            println("Layout finished after " + (endTime - startTime) + "ms.")
            
//            println("Micro Layout started!")
//            startTime = System.currentTimeMillis
            MicroLayoutUtil.calculateAbsoluteBounds(kGraphContext.viewModel)
//            endTime = System.currentTimeMillis
//            println("Micro Layout finished after " + (endTime - startTime) + "ms.")
            
            // map layouted KGraph to SGraph
//            println("Mapping started!")
//            startTime = System.currentTimeMillis
            KGraphMappingUtil.mapLayout(diagramState.getKGraphToSModelElementMap(root.id))
//            endTime = System.currentTimeMillis
//            println("Mapping finished after " + (endTime - startTime) + "ms.")
            
            // additional code for timing and testing the serialization of the final SGraph model
//            val gsonBuilder = new GsonBuilder
//            KGraphTypeAdapterUtil.configureGson(gsonBuilder)
//            val gson = gsonBuilder.create
//            for (var i = 0; i < 10; i++) {
//                println("Starting toGson of the Graph!")
//                startTime = System.currentTimeMillis
//                val json = gson.toJson(root)
//                
//                endTime = System.currentTimeMillis
//                println("toGson finished after " + (endTime - startTime) + "ms.")
//                println("The json is " + json.length + " characters long")
//            }
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