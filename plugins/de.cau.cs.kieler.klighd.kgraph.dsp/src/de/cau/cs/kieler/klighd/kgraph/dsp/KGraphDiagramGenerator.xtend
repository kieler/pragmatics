// which of these is now needed? both? just one of them?
/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
/*
 * Copyright (C) 2017 TypeFox and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KText
import io.typefox.sprotty.api.Dimension
import io.typefox.sprotty.api.IDiagramState
import io.typefox.sprotty.api.SButton
import io.typefox.sprotty.api.SEdge
import io.typefox.sprotty.api.SGraph
import io.typefox.sprotty.api.SLabel
import io.typefox.sprotty.api.SModelElement
import io.typefox.sprotty.api.SNode
import io.typefox.sprotty.api.SPort
import io.typefox.sprotty.server.xtext.IDiagramGenerator
import io.typefox.sprotty.server.xtext.tracing.ITraceProvider
import io.typefox.sprotty.server.xtext.tracing.Traceable
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Random
import org.apache.log4j.Logger
import org.eclipse.elk.core.util.Pair
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.util.CancelIndicator

/**
 * A Diagram Generator that can create Sprotty SGraphs from any EObject, that has a registered view synthesis to KGraph
 * for KLighD. 
 * During translation some artifacts for mapping between the source and target element types are generated as well as
 * artifacts for all contained Texts in the Model.
 * @author stu114054
 */
class KGraphDiagramGenerator implements IDiagramGenerator {
	static val LOG = Logger.getLogger(KGraphDiagramGenerator)
    
    /**
     * A map that contains a key-value pair for each KGraphElement and its translated SModelElement counterpart.
     * Convenient for finding a specific key KGraphElement faster.
     */
    @Accessors(PUBLIC_GETTER) var Map<KGraphElement, SModelElement> kGraphToSModelElementMap
    
    /**
     * An iterable List containing each KGraphElement and its translated SModelElement counterpart as a Pair.
     * Convenient for iterating over all element pairs.
     */
    @Accessors(PUBLIC_GETTER) var ArrayList<Pair<KGraphElement, SModelElement>> elementMappingList
    /**
     * A list containing all Texts from the source KGraph in Sprotty Labels
     */
    @Accessors(PUBLIC_GETTER) var ArrayList<SKLabel> modelLabels
    /**
     * A Map containing all KTexts from the source KGraph under the key of their id.
     */
    @Accessors(PUBLIC_GETTER) var Map<String, KText> textMapping

    /**
     * The root node of the translated SGraph
     */
	var SGraph diagramRoot
	
	@Inject ITraceProvider traceProvider
    
    /**
     * A random offset is used to give elements without identifier an id that different objects at the same position 
     * can not get the same id over multiple updates (prevents morphing of not matching objects into each other).
     * Unnamed elements therefore cannot morph on updated models. Name your elements!
     */
    int randomOffset
    
    /**
     * Counting how many Labels and Texts are in the graph
     */
    int numLabels
    
    KGraphElementIDGenerator idGen

    /**
     * Creates a ViewContext containing the KGraph model for any EObject model with a registered
     * transformation in KLighD. 
     */
    static def ViewContext translateModel(EObject model) {
        return LightDiagramServices.translateModel2(model, null)
    }

	
	/**
	 * Generates an SGraph from the resource, if its content is a KNode.
	 */
	override generate(Resource resource, IDiagramState state, CancelIndicator cancelIndicator) {
		val content = resource.contents.head
		if (content instanceof KNode) {
			generate(resource.URI.toString, content as KNode, cancelIndicator)
		}
	}
	
	/**
	 * Generates an SGraph from a KNode pNode that is based on the resource's content. 
	 * @param identifier 
	 */
	def SGraph generate(String identifier, KNode pNode, CancelIndicator cancelIndicator) {
	    
        LOG.info("Generating diagram for input: '" + identifier + "'")
	    
        val r = new Random
        r.seed = System.currentTimeMillis
        randomOffset = r.nextInt
        numLabels = 0
        kGraphToSModelElementMap = new HashMap
        elementMappingList = new ArrayList
        textMapping = new HashMap<String, KText>
        modelLabels = new ArrayList
        idGen = new KGraphElementIDGenerator
        
        
        // generate a SGraph root element around the translation of the parent KNode
        diagramRoot = new SKGraph => [
            type = 'graph'
            id = identifier
            children = new ArrayList<SModelElement>
        ]
        
        val rootChildren = createNodesAndEdges(#[pNode])
        diagramRoot.children.addAll(rootChildren)
        
        return if (cancelIndicator.canceled) null else diagramRoot
        // TODO: incorporate the cancelIndicator on more places?
	}

    /**
     * Translates all nodes and their outgoing edges to SModelElements. Also handles tracing and mapping between
     * KGraphElements and SModelElements
     * The edges are translated together with the nodes, because KNodes contain KEdges in the field 'outgoingEdges' 
     * as children, whereas outgoing SEdges are siblings of their originating SNodes.
     */
    protected def List<SModelElement> createNodesAndEdges(List<KNode> nodes) {
        val nodeAndEdgeElements = new ArrayList()
        // add all node children
        for (node : nodes) {
            val SNode nodeElement = generateNode(node)
            if (nodeElement !== null) {
                kGraphToSModelElementMap.put(node, nodeElement)
                elementMappingList.add(new Pair(node, nodeElement))
                nodeAndEdgeElements.add(nodeElement)
                nodeElement.trace(node)
            } else {
                LOG.error("generateElement did not create an element for node " + node)
            }
        }
        
        // after all nodes have been generated, generate their edges as children
        for (node : nodes) {
            for (edge: node.outgoingEdges) {
                val SEdge edgeElement = generateEdge(edge)
                if (edgeElement !== null) {
                    kGraphToSModelElementMap.put(edge, edgeElement)
                    elementMappingList.add(new Pair(edge, edgeElement))
                    nodeAndEdgeElements.add(edgeElement)
                    edgeElement.trace(edge)
                } else {
                    LOG.error("generateElement did not create an element for edge " + edge)
                }
            }
        }
        return nodeAndEdgeElements
    }
    
    /**
     * Translates all ports to SModelElements. Also handles tracing and mapping between
     * KGraphElements and SModelElements
     */
    protected def List<SPort> createPorts(List<KPort> ports) {
        val List<SPort> portElements = new ArrayList
        for (port : ports) {
            val SPort portElement = generatePort(port)
            if (portElement !== null) {
                kGraphToSModelElementMap.put(port, portElement)
                elementMappingList.add(new Pair(port, portElement))
                portElements.add(portElement)
                portElement.trace(port)
            } else {
                LOG.error("generateElement did not create an element for port " + port)
            }
        }
        return portElements
    }
    
    /**
     * Translates all labels to SModelElements. Also handles tracing and mapping between
     * KGraphElements and SModelElements
     */
    protected def List<SLabel> createLabels(List<KLabel> labels) {
        val List<SLabel> labelElements = new ArrayList
        for (label : labels) {
            val SLabel labelElement = generateLabel(label, true)
            if (labelElement !== null) {
                kGraphToSModelElementMap.put(label, labelElement)
                elementMappingList.add(new Pair(label, labelElement))
                labelElements.add(labelElement)
                labelElement.trace(label)
            } else {
                LOG.error("generateElement did not create element for label " + label)
            }
        }
        return labelElements
    }
    
    /**
     * Generates a trace for the kElement's source EObject on the sElement. 
     * The kElement must be synthesized by a KLighD synthesis before and must have its source EObject stored in the 
     * KlighdInternalProperties.MODEL_ELEMEMT property.
     */
    protected def void trace(SModelElement sElement, KGraphElement kElement) {
        if (sElement instanceof Traceable) {
            // the real model element that can be traced is the EObject that got synthesized in the translateModel
            // function. That model element must be stored in the properties during the synthesis. Otherwise the tracing
            // will can not work
            val modelKElement = kElement.properties.get(KlighdInternalProperties.MODEL_ELEMEMT)
            if (modelKElement instanceof EObject) {
                traceProvider.trace(sElement, modelKElement)    
            } else {
                LOG.error("the given KGraphElement does not come from a valid synthesis that stores its model element.")
            }
        }
    }
    
    /**
     * Creates a Sprotty Node corresponding to the given KNode.
     */
    protected def SKNode generateNode(KNode node) {

        val id = idGen.getId(node, randomOffset)
        val nodeElement = configSElement(SKNode, id.toString)
        
        nodeElement.size = new Dimension(node.width, node.height)
        nodeElement.insets = node.insets
        
        nodeElement.data = node.data
        
        modelLabels.addAll(findTextsAndLabels(node.data))
        
        
        val children = createNodesAndEdges(node.children)
        if (!children.isEmpty) {
            nodeElement.children.addAll(children)
        }
        
        val ports = createPorts(node.ports)
        if (!ports.isEmpty) {
            nodeElement.children.addAll(ports)
        }
        
        val labels = createLabels(node.labels)
        if (!labels.isEmpty) {
            nodeElement.children.addAll(labels)
        }
        return nodeElement 
    }
    
    /**
     * Creates a Sprotty Edge corresponding to the given KEdge.
     */
    protected def SKEdge generateEdge(KEdge edge) {
        val fromElementId = if (edge.sourcePort !== null) {
            kGraphToSModelElementMap.get(edge.sourcePort).id
        } else {
            kGraphToSModelElementMap.get(edge.source).id
        }
        val toElementId = if (edge.targetPort !== null) {
            kGraphToSModelElementMap.get(edge.targetPort).id
        } else {
            kGraphToSModelElementMap.get(edge.target).id
        }
        val edgeId = idGen.getId(edge, randomOffset) 
        val SKEdge edgeElement = configSElement(SKEdge, edgeId)
        
        edgeElement.sourceId = fromElementId
        edgeElement.targetId = toElementId
        
        edgeElement.data = edge.data
        
        modelLabels.addAll(findTextsAndLabels(edge.data))
        
        
        val labels = createLabels(edge.labels)
        if (!labels.isEmpty) {
            edgeElement.children.addAll(labels)
        }
        return edgeElement
    }
    
    /**
     * Creates a Sprotty Port corresponding to the given KPort.
     */
    protected def SKPort generatePort(KPort port) {
        val portId = idGen.getId(port, randomOffset)
        val SKPort portElement = configSElement(SKPort, portId)
        portElement.insets = port.insets
        
        portElement.data = port.data
        
        modelLabels.addAll(findTextsAndLabels(port.data))
        
        val labels = createLabels(port.labels)
        if (!labels.isEmpty) {
            portElement.children.addAll(labels)
        }
        return portElement
    }
    
    /**
     * Creates a Sprotty Label corresponding to the given KLabel.
     * @param main describes, if this is called during main Diagram generation
     */
    protected def SKLabel generateLabel(KLabel label, boolean main) {
        val labelId = idGen.getId(label, randomOffset)
        val SKLabel labelElement = configSElement(SKLabel, labelId)
        labelElement.text = label.text
        labelElement.insets = label.insets
        
        labelElement.data = label.data
        
        
        if (main) {
            // remember KLabel element for later size estimation
            modelLabels.addAll(findTextsAndLabels(label.data))
        }
        return labelElement
    }

    /**
     * generates a generic SModelElement with some defaults set.
     */
    protected def <E extends SModelElement> E configSElement(Class<E> elementClass, String idStr) {
        elementClass.constructor.newInstance => [
            id = idStr
            type = findType(it)
            children = new ArrayList<SModelElement>
        ]
    }
    
    /**
     * Finds all KText and KLabel Elements within the renderings in dataList and returns them as new Labels.
     * Also remembers the mapping to the KText elements from the source model in the textMapping field.
     */
    protected def List<SKLabel> findTextsAndLabels(List<KGraphData> dataList) {
        val dataTexts = new ArrayList
        for (data : dataList) {
            dataTexts.addAll(findTextsAndLabels(data))
        }
        return dataTexts
    }
    
    protected def List<SKLabel> findTextsAndLabels(KGraphData data) {
        val dataLabels = new ArrayList
        if (data instanceof KText) {
            // create a new Label with data as its text
            val label = KGraphFactory.eINSTANCE.createKLabel()
            // KTexts in Labels have their texts stored inside their parent KLabel, not in the KText itself
            if (data.eContainer instanceof KLabel) {
                label.text = (data.eContainer as KLabel).text
            } else {
                label.text = data.text
            }
            // need to put a copy of the text inside the new label because otherwise inserting it into the label will
            // modify the eContainer feature of the Text, which should not be changed
            label.data += EcoreUtil.copy(data)
            
            // generate a new Label as if it would belong to the main model
            val sKLabel = generateLabel(label, false)
            sKLabel.id = diagramRoot.id + KGraphElementIDGenerator.ID_SEPARATOR + "texts-only" + 
                KGraphElementIDGenerator.ID_SEPARATOR + KGraphElementIDGenerator.LABEL_SEPARATOR 
                + (numLabels + randomOffset)
            textMapping.put(sKLabel.id, data)
            numLabels++
            
            
            dataLabels += sKLabel
        } else if (data instanceof KContainerRendering) {
            for (childData: data.children) {
                dataLabels.addAll(findTextsAndLabels(childData))
            }
        } else if (data instanceof KRenderingLibrary) {
            // TODO: add all texts in the KRenderingLibrary and don't add any from KRenderingRefs
            // I don't have an example of used texts in KTenderingLibraries
        }
        return dataLabels
    }
    
    /**
     * returns a String describing the type of the SModelElement.
     */
    protected def String findType(SModelElement element) {
        switch element {
            SNode: 'node'
            SLabel: 'label'
            SEdge: 'edge'
            SButton: 'button'
            SPort: 'port'
            default: 'dontknow'
        }
    }
    
    /**
     * Generates a simple text-only SGraph for a Graph with only the given labels.
     * @param labels a List of all Labels, this SGraph should contain
     * @param parentId the id of the Graph containing all these labels
     */
    static def SGraph generateTextDiagram(List<SKLabel> labels, String parentId) {
        
        // equivalent for the SRootElement
        val root = new SKGraph => [
            type = 'graph'
            id = parentId + KGraphElementIDGenerator.ID_SEPARATOR + "texts-only"
            children = new ArrayList<SModelElement>
        ]
        
        root.children = new ArrayList
        root.children += labels
        return root
    }
    
}