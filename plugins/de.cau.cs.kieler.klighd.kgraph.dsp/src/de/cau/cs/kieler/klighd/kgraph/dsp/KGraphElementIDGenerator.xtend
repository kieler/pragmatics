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
package de.cau.cs.kieler.klighd.kgraph.dsp

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KStyleHolder
import java.util.HashMap
import java.util.Map

/**
 * @author stu114054
 * class for generating ids for any KGraphElement. Use a single instance of this and call getId() for all the elements
 * you need ids for.
 */
class KGraphElementIDGenerator {
    
    Map<KGraphElement, String> idMap
    
    public static final String ID_SEPARATOR = '$'
    public static final String NODE_SEPARATOR = 'N'
    public static final String EDGE_SEPARATOR = 'E'
    public static final String PORT_SEPARATOR = 'P'
    public static final String LABEL_SEPARATOR = 'L'
    
    new() {
        idMap = new HashMap
    }
    
    /**
     * generates a new unique ID for any KGraphElement.
     */
    def String getId(KGraphElement element, int randomOffset) {
        var String id = null
        
        // if the ID was already calculated, use that
        if (idMap.get(element) !== null) {
            return idMap.get(element)
        }
        
        // the root node is just called $root
        val parent = element.eContainer as KGraphElement
        var String parentId = null
        if (parent !== null) {
            parentId = getId(parent, randomOffset)
        } else {
            return ID_SEPARATOR + 'root'
            
        }
        
        // use a prefix depending on the class of the element + the identifier as id if an identifier is defined
        // otherwise make up a new id based on the position in the model hierarchy with a Separator not appearing in 
        // real identifiers ($)
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
}

class KRenderingIDGenerator {
    
    public static final String ID_SEPARATOR = '$'
    public static final String RENDERING_SEPERATOR = 'R'
    public static final String JUNCTION_POINT_SEPARATOR = 'J'
    
    /**
     * generates a new unique ID for all child elements of this rendering (if any) and writes it in its ID field
     * Assumes, that the given rendering already has a unique id not containing the character '$'.
     */
    static def void generateIdsRecursive(KStyleHolder rendering) {
        if (rendering !== null) {
            generateIdsRecursive(rendering, null)
        }
    }
    
    static def void generateIdsRecursive(KStyleHolder rendering, int renderingNumber) {
        if (rendering !== null) {
            rendering.id = "rendering" + ID_SEPARATOR + RENDERING_SEPERATOR + renderingNumber
            generateIdsRecursive(rendering, null)
        }
    }
    
    
    
    private static def void generateIdsRecursive(KStyleHolder rendering, KContainerRendering parentRendering) {
        if (rendering === null) {
            return
        }
        if (parentRendering !== null && !rendering.equals(parentRendering)) {
            // generate a new ID based on the parent rendering's ID and the index of the rendering inside its parent
            rendering.id = parentRendering.id 
                + ID_SEPARATOR + RENDERING_SEPERATOR 
                + parentRendering.children.indexOf(rendering)
        }
        if (rendering instanceof KPolyline) {
            // Special case for KPolyline: It has a junctionPointRendering that also needs an ID.
            // use a new separator and think of this as a new rendering hierarchy with possible children
            val junctionPointRendering = rendering.junctionPointRendering
            if (junctionPointRendering !== null) {
                junctionPointRendering.id = rendering.id
                + ID_SEPARATOR + JUNCTION_POINT_SEPARATOR
            } 
        }
        if (rendering instanceof KContainerRendering) {
            // each KContainerRendering has child Renderings that also need new IDs
            for (childRendering : rendering.children) {
                generateIdsRecursive(childRendering, rendering)
            }
        }
    }
    
    
}