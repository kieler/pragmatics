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
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
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
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.util.CancelIndicator

class KGraphDiagramGenerator implements IDiagramGenerator {
	static val LOG = Logger.getLogger(KGraphDiagramGenerator)

    @Accessors(PUBLIC_GETTER)
    var Map<KGraphElement, SModelElement> elementIndex
    @Accessors(PUBLIC_GETTER)
    var ArrayList<Pair<KGraphElement, SModelElement>> mapping
    @Accessors(PUBLIC_GETTER)
    var ArrayList<SKLabel> modelLabels
    @Accessors(PUBLIC_GETTER)
    var Map<String, KText> textMapping

	var SGraph diagramRoot
	
	@Inject ITraceProvider traceProvider
	
	IDiagramState state

    KNode parentNode
    
    /*
     * A random offset is used to give elements without identifier an id that different objects at the same position 
     * can not get the same id over multiple updates (prevents morphing of not matching objects into each other).
     * Unnamed elements therefore cannot morph on updated models. Name your elements!
     */
    int randomOffset
    
    /*
     * Counting how many Labels and Texts are in the graph
     */
    int numLabels
    
    Map<KGraphElement, String> idMap

    /*
     * Creates a ViewContext containing the KGraph model for any EObject model with a registered
     * transformation in KLighD.
     */
    def ViewContext translateModel(EObject model) {
        val ViewContext kgraphContext = LightDiagramServices.translateModel2(model, null)
        
        return kgraphContext
    }

	
	/*
	 * Generates a SGraph from the resource, if its content is a KNode.
	 */
	override generate(Resource resource, IDiagramState state, CancelIndicator cancelIndicator) {
		val content = resource.contents.head
		if (content instanceof KNode) {
			generate(resource, content as KNode, state, cancelIndicator)
		}
	}
	
	/*
	 * Generates a SGraph from a KNode pnode that is based on the resource's content. 
	 */
	def generate(Resource resource, KNode pnode, IDiagramState state, CancelIndicator cancelIndicator) {
	    
        LOG.info("Generating diagram for input: '" + resource.URI.lastSegment + "'")
	    this.state = state
	    
        val r = new Random
        r.seed = System.currentTimeMillis
        randomOffset = r.nextInt
        numLabels = 0
        elementIndex = new HashMap
        mapping = new ArrayList
        textMapping = new HashMap<String, KText>
        modelLabels = new ArrayList
        
        // KGraphUtil.persistDataElements(pnode)
        val diagram = generateDiagram(pnode, cancelIndicator,resource.URI.toString)
        // findAllTexts(diagram)
        return diagram
	}
    
    def generateDiagram(KNode pnode, CancelIndicator cancelIndicator, String identifier) {
        idMap = new HashMap
        parentNode = pnode
        diagramRoot = new SKGraph => [
            type = 'graph'
            id = identifier
            children = new ArrayList<SModelElement>
        ]
        
        val rootChildren = createChildElements(#[pnode])
        diagramRoot.children.addAll(rootChildren)
        return diagramRoot
    }

    protected def List<SModelElement> createChildElements(List<KNode> nodes) {
        val rootChildren = new ArrayList()
        // add all node children
        for (node : nodes) {
            val SModelElement element = generateElement(node)
            if (element !== null) {
                elementIndex.put(node, element)
                mapping.add(new Pair(node, element))
                rootChildren.add(element)
                element.trace(node)
            } else {
                LOG.error("generateElement did not create an element for node " + node)
            }
        }
        
        // after all nodes and ports have been generated, generate their edges as children
        for (node : nodes) {
            for (edge: node.outgoingEdges) {
                val SModelElement element = generateElement(edge)
                if (element !== null) {
                    elementIndex.put(edge, element)
                    mapping.add(new Pair(edge, element))
                    rootChildren.add(element)
                } else {
                    LOG.error("generateElement did not create an element for edge " + edge)
                }
            }
        }
        return rootChildren
    }
    
    protected def List<SModelElement> createPorts(EList<KPort> ports) {
        val List<SModelElement> portElements = new ArrayList
        for (port : ports) {
            val SModelElement element = generateElement(port)
            if (element !== null) {
                elementIndex.put(port, element)
                mapping.add(new Pair(port, element))
                portElements.add(element)
            } else {
                LOG.error("generateElement did not create an element for port " + port)
            }
        }
        return portElements
    }
    
    protected def List<SModelElement> createLabels(EList<KLabel> labels) {
        val List<SModelElement> labelElements = new ArrayList
        for (label : labels) {
            val SModelElement element = generateElement(label)
            if (element !== null) {
                elementIndex.put(label, element)
                mapping.add(new Pair(label, element))
                labelElements.add(element)
            } else {
                LOG.error("generateElement did not create element for label " + label)
            }
        }
        return labelElements
    }
    
    protected def void trace(SModelElement element, KNode statement) {
        if (element instanceof Traceable) 
            traceProvider.trace(element, statement)
    }
    
    protected def dispatch SModelElement generateElement(KNode node) {
        val SKNode skNode = createNode(node)    
        
        val children = createChildElements(node.children)
        if (!children.isEmpty) {
            skNode.children.addAll(children)
        }
        
        val ports = createPorts(node.ports)
        if (!ports.isEmpty) {
            skNode.children.addAll(ports)
        }
        
        val labels = createLabels(node.labels)
        if (!labels.isEmpty) {
            skNode.children.addAll(labels)
        }
        return skNode 
    }
    
    protected def dispatch SModelElement generateElement(KEdge edge) {
        val SKEdge skEdge = createEdge(edge)
        
        val labels = createLabels(edge.labels)
        if (!labels.isEmpty) {
            skEdge.children.addAll(labels)
        }
        return skEdge
    }
    
    protected def dispatch SModelElement generateElement(KPort port) {
        val SKPort skPort = createPort(port)
        
        val labels = createLabels(port.labels)
        if (!labels.isEmpty) {
            skPort.children.addAll(labels)
        }
        return skPort
    }
    
    protected def dispatch SModelElement generateElement(KLabel label) {
        return createLabel(label, true)
    }

    protected def <E extends SModelElement> E configSElement(Class<E> elementClass, String idStr) {
        elementClass.constructor.newInstance => [
            id = idStr
            type = findType(it)
            children = new ArrayList<SModelElement>
        ]
    }
    
    protected def SKNode createNode(KNode node) {
        val id = getId(node)
        val nodeElement = configSElement(SKNode, id.toString)
        
        nodeElement.layout = 'vbox'
        nodeElement.size = new Dimension(node.width, node.height)
        nodeElement.insets = node.insets
        
        nodeElement.data = node.data        
        
        modelLabels.addAll(findLabels(node.data))
        
        return nodeElement
    }
    
    protected def SKEdge createEdge(KEdge edge) {
        val fromElementId = if (edge.sourcePort !== null) {
            elementIndex.get(edge.sourcePort).id
        } else {
            elementIndex.get(edge.source).id
        }
        val toElementId = if (edge.targetPort !== null) {
            elementIndex.get(edge.targetPort).id
        } else {
            elementIndex.get(edge.target).id
        }
        val edgeId = getId(edge) 
        val SKEdge edgeElement = configSElement(SKEdge, edgeId)
        
        edgeElement.sourceId = fromElementId
        edgeElement.targetId = toElementId
        
        edgeElement.data = edge.data
        
        modelLabels.addAll(findLabels(edge.data))

        return edgeElement
    }
    
    protected def SKPort createPort(KPort port) {
        val portId = getId(port)
        val SKPort portElement = configSElement(SKPort, portId)
        portElement.insets = port.insets
        
        portElement.data = port.data
        
        modelLabels.addAll(findLabels(port.data))
        
        return portElement
    }
    
    /**
     * Creates a Sprotty Label corresponding to the given KLabel.
     * @param main describes, if this is called during main Diagram generation (default = true)
     */
    protected def SKLabel createLabel(KLabel label, boolean main) {
        val labelId = getId(label)
        val SKLabel labelElement = configSElement(SKLabel, labelId)
        labelElement.text = label.text
        labelElement.insets = label.insets
        
        labelElement.data = label.data
        
        
        if (main) {
            // remember KLabel element for later size estimation
            modelLabels.addAll(findLabels(label.data))
        }
        return labelElement
    }
    
    def List<SKLabel> findLabels(List<KGraphData> dataList) {
        val dataTexts = new ArrayList
        for (data : dataList) {
            dataTexts.addAll(findLabels(data))
        }
        return dataTexts
    }
    
    def List<SKLabel> findLabels(KGraphData data) {
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
            
            
            // create a new Label as if it would belong to the main model
            val sKLabel = createLabel(label, false)
            sKLabel.id = diagramRoot.id + ID_SEPARATOR + "texts-only" + ID_SEPARATOR + LABEL_SEPARATOR 
                + (numLabels + randomOffset)
            // sKLabels.add(sKLabel)
            textMapping.put(sKLabel.id, data)
            numLabels++
            
            
            dataLabels += sKLabel
            //textMapping.add(new Pair(data, data.id)) // TODO: what is this ID? can we use it?
        } else if (data instanceof KContainerRendering) {
            for (childData: data.children) {
                dataLabels.addAll(findLabels(childData))
            }
        } else if (data instanceof KRenderingLibrary) {
            // TODO: add all texts in the KRenderingLibrary and don't add any from KRenderingRefs
        }
        return dataLabels
    }
    
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
    
    
    // ************** CODE FOR GENERATRING IDS ************** //
    
    
    final String ID_SEPARATOR = '$'
    final String NODE_SEPARATOR = 'N'
    final String EDGE_SEPARATOR = 'E'
    final String PORT_SEPARATOR = 'P'
    final String LABEL_SEPARATOR = 'L'
    
    /**
     * generates a new unique ID for any KGraphElement.
     */
    protected def String getId(KGraphElement element) {
        var String id = null
        
        // if the ID was already calculated, use that
        if (idMap.get(element) !== null) {
            return idMap.get(element)
        }
        
        // the root node is just called $root
        val parent = element.eContainer as KGraphElement
        var String parentId = null
        if (parent !== null) {
            parentId = getId(parent)
        } else {
            return ID_SEPARATOR + 'root'
            
        }
        
        // use a prefix depending on the class of the element + the identifier as id if an identifier is defined
        // otherwise make up a new id based on the position in the model hierarchy with a Separator not appearing in real identifiers ($)
        var String elementId = null
        
        val identifier = element.data.filter(KIdentifier)
        
        switch (element) {
            KNode: {
                if (identifier.empty) {
                    val parentOffset = (parent as KNode).children.indexOf(element)
                    elementId = ID_SEPARATOR + NODE_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = NODE_SEPARATOR + identifier.head.id
                }
            }
            KEdge: {
                if (identifier.empty) {
                    val parentOffset = (parent as KNode).outgoingEdges.indexOf(element)
                    elementId = ID_SEPARATOR + EDGE_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = EDGE_SEPARATOR + identifier.head.id
                }
            }
            KLabel: {
                if (identifier.empty) {
                    val parentOffset = (parent as KLabeledGraphElement).labels.indexOf(element)
                    elementId = ID_SEPARATOR + LABEL_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = LABEL_SEPARATOR + identifier.head.id
                }
            }
            KPort: {
                if (identifier.empty) {
                    val parentOffset = (parent as KNode).ports.indexOf(element)
                    elementId = ID_SEPARATOR + PORT_SEPARATOR + (parentOffset + randomOffset)
                } else {
                    elementId = PORT_SEPARATOR + identifier.head.id
                }
            }
            default: {
                throw new IllegalArgumentException("Can not generate an id for element of type " + element.class)
            }
        }
        
        id = parentId + ID_SEPARATOR + elementId
        idMap.put(element, id)
        return id
    }
    
    
//    @Inject extension KRenderingExtensions
//    @Inject extension KNodeExtensions
    
    def SGraph generateTextDiagram(List<SKLabel> labels, String parentId) {
//        idMap = new HashMap
//        
//        val r = new Random
//        r.seed = System.currentTimeMillis
//        randomOffset = r.nextInt
        
        // equivalent for the SRootElement
        val root = new SKGraph => [
            type = 'graph'
            id = parentId + ID_SEPARATOR + "texts-only"
            children = new ArrayList<SModelElement>
        ]

//        val sKLabels = new ArrayList
//        var parentOffset = 0
//        for (label : labels) {
//            val sKLabel = createLabel(label, false)
//            sKLabel.id = root.id + ID_SEPARATOR + LABEL_SEPARATOR + (parentOffset + randomOffset)
//            sKLabels.add(sKLabel)
//            // textMapping.add(new Pair(label, ))
//            parentOffset++
//        }
//        
        root.children = new ArrayList
//        root.children += sKLabels
        root.children += labels
        return root
    }
    
}