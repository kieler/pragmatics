package de.scheidtbachmann.osgimodel.visualization.actions

import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import java.util.ArrayList
import java.util.List
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
    static def List<KNode> revealElements(List<? extends Object> elements, ActionContext context, KNode containingNode) {
        val List<KNode> revealedElements = new ArrayList
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
                val newElementNodes = new ArrayList<KNode>
                newElementNodes.addAll(newBundleContainer.children.head?.children)
                containingNode.children += newElementNodes
                revealedElements += newElementNodes
            } else {
                // Otherwise, put the old node in the revealed elements as something on that node should also be changed.
                revealedElements += oldElementNode
            }
        ]
        
        return revealedElements
    }
    
}