package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.elk.graph.properties.MapPropertyHolder

/**
 * Utility class to provide methods used by actions that reveal new elements to some pre-existing visualization.
 */
final class GenericRevealActionUtil {
    /**
     * Make the default constructor private. This class can not be instanciated.
     */
    private new() {}
    
    /**
     * Calls a new synthesis for each of the elements and adds them into the given containing node.
     * Nodes for elements that are already contained in the containing node are not added a second time to the
     * containing node, but still included in the returned list for further processing.
     * 
     * @param elements The elements that should be synthesized into the containing node.
     * @param context The action context of the triggered action.
     * @param containingNode The KNode into which the elements should be synthesized.
     * @return A list of all representations of the given elements in the containing node. 
     */
    static def <E> Map<E, KNode> revealElements(List<E> elements, ActionContext context, KNode containingNode) {
        val Map<E, KNode> revealedElements = new HashMap
        elements.forEach[ element |
            // Only add this element to the context if it has not been added before.
            val oldElementNode = containingNode.children.findFirst[ alreadySynthesizedNodes |
//                context.getDomainElement(alreadySynthesizedNodes) === usedByBundle
                 SynthesisUtils.getDomainElement(context, alreadySynthesizedNodes) === element
            ]
            if (oldElementNode === null) {
                val requiredSynthesis = SynthesisUtils.requiredSynthesis(element)
                val newBundleContainer = LightDiagramServices.translateModel(
                    element,
                    context.viewContext,
                    new MapPropertyHolder => [
                        setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, requiredSynthesis)
                    ]
                )
                // Every synthesis capable of being revealed has an additional surrounding node that should be ignored
                // and thrown away here.
                val newElementNode = newBundleContainer.children.head.children.head
                containingNode.children += newElementNode
                revealedElements.put(element, newElementNode)
            } else {
                // Otherwise, put the old node in the revealed elements as something on that node should also be changed.
                revealedElements.put(element, oldElementNode)
            }
        ]
        
        return revealedElements
    }
    
    /**
     * Determines whether all KNode representations of the given {@code elements} are contained within the
     * {@code containingNode} and, if they exist, if they are connected to the {@code sourceTargetNode} via an edge.
     * 
     * @param elements The list of elements that should be checked.
     * @param sourceTargetNode The node for that should be checked, if all element representations are connected to it.
     * @param isSource If the {@code sourceTargetNode} should be checked if it is the source or the target in the
     * potential connection.
     * @param context The action context of the triggered action.
     * @param containingNode The parent KNode in which the connection status should be checked.
     */
    def static boolean allConnected(List<? extends Object> elements, KNode sourceTargetNode, boolean isSource, 
        ActionContext context, KNode containingNode) {
        // The KNodes represented by the elements. May contain nulls for elements not represented in the containingNode.
        val List<KNode> elementNodes = elements.map[ element |
            containingNode.children.findFirst [childNode | 
                SynthesisUtils.getDomainElement(context, childNode) === element
            ]
        ]
        // If any element does not have a representation yet, not all elements are connected.
        if (elementNodes.contains(null)) {
            return false
        }
        
        // If all elements are represented and there are no unconnected nodes, then all nodes are connected.
        val allConnected = elementNodes.findFirst[ node |
            if (isSource) {
                !KGraphUtil.hasEdge(sourceTargetNode, node)
            } else {
                !KGraphUtil.hasEdge(node, sourceTargetNode)
            }
        ] === null
        return allConnected
    }
    
}