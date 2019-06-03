package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

/**
 * Util class that contains some static methods commonly used for the Osgi synthesis.
 * 
 * @author nre
 */
class SynthesisUtils {
    /**
     * Returns the identifying string for the synthesis used by any supported object of the Osgi model.
     */
    def static String requiredSynthesis(Object model) {
        switch model {
            case model instanceof OsgiProject: {
                return "de.scheidtbachmann.osgimodel.visualization.OsgiDiagramSynthesis"
            }
            case model instanceof Bundle: {
                return "de.scheidtbachmann.osgimodel.visualization.BundleSynthesis"
            }
            // TODO: etc.
        }
    }
    
    /**
     * Returns the domain element the clicked KNode given its action context.
     * Uses a fallback mechanism to work around the SourceModelTrackingAdapter that seems bugged for this synthesis.
     * FIXME: find out why the usual way does not work all the time and remove the need for this method!
     */
    def static Object getDomainElement(ActionContext context) {
        getDomainElement(context, context.KNode)
    }
    
    /**
     * Returns the domain element for any KNode given its action context.
     * Uses a fallback mechanism to work around the SourceModelTrackingAdapter that seems bugged for this synthesis.
     * FIXME: find out why the usual way does not work all the time and remove the need for this method!
     */
    def static Object getDomainElement(ActionContext context, KNode kNode) {
        // Default case, how it should work all the time.
        var element = context.getDomainElement(kNode)
        
        // Fallback by using the KLighD internal property set during the synthesis.
        if (element === null) {
            element = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        }
        return element
    }
    
    
    /**
     * If the id should be truncated by the prefix 'de.scheidtbachmann', this returns a truncated version of the id,
     * otherwise the id itself.
     * 
     * @param id The id that should possibly be truncated.
     * @return The possibly truncated id.
     */
    def static String getId(ViewContext usedContext, String id) {
        val prefix = "de.scheidtbachmann."
        if (usedContext.getOptionValue(SHORTEN_BY_DE_SCHEIDTBACHMANN) as Boolean) {
            if (id.startsWith(prefix)) {
                return id.substring(prefix.length)
            }
        }
        return id
    }
}