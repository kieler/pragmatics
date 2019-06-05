package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product
import java.util.List

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

/**
 * Util class that contains some static methods commonly used for the Osgi synthesis.
 * 
 * @author nre
 */
final class SynthesisUtils {
    /**
     * Utils class can not be instanciated.
     */
     private new() {}
    
    /**
     * Returns the identifying string for the synthesis used by any supported object of the OSGi model.
     * @param model The model that should be synthesized.
     */
    def static String requiredSynthesis(Object model) {
        switch model {
            case model instanceof OsgiProject: {
                return "de.scheidtbachmann.osgimodel.visualization.OsgiDiagramSynthesis"
            }
            case model instanceof Bundle: {
                return "de.scheidtbachmann.osgimodel.visualization.BundleSynthesis"
            }
            case model instanceof Product: {
                return "de.scheidtbachmann.osgimodel.visualization.ProductSynthesis"
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
    def static String getId(String id, ViewContext usedContext) {
        val prefix = "de.scheidtbachmann."
        if (usedContext.getOptionValue(SHORTEN_BY_DE_SCHEIDTBACHMANN) as Boolean) {
            if (id.startsWith(prefix)) {
                return id.substring(prefix.length)
            }
        }
        return id
    }
    
    /**
     * Filters the list of given bundles by the filter options of the diagram options.
     * 
     * @param bundles The unfiltered list of all bundles.
     * @param usedContext The ViewContext used to display the diagram these bundles are shown in.
     * @return An Iterable of the bundles filtered by the diagram options.
     */
    def static Iterable<Bundle> filteredBundles(List<Bundle> bundles, ViewContext usedContext) {
        val prefix = "de.scheidtbachmann"
        if (usedContext.getOptionValue(FILTER_BY_DE_SCHEIDTBACHMANN) as Boolean) {
            bundles.filter[ it.uniqueId.startsWith(prefix) ]
        } else {
            bundles
        }
    }
    
    /**
     * Returns the descriptive text of a label shortened by the {@link OsgiOptions#DESCRIPTION_LENGTH} option.
     * @param text The text that should be shortened.
     * @param context The view context used to display the diagram.
     * @return The given string shortened by the description length option.
     */
    def static String descriptionLabel(String text, ViewContext context) {
        val threshold = context.getOptionValue(DESCRIPTION_LENGTH) as Number
        if (text === null) {
            return ""
        }
        if (text.length <= threshold.intValue) {
            return text
        }
        return text.substring(0, threshold.intValue) + " ..."
    }
    
    
    /**
     * Turns any string into an int roughly matching its alphabetical ordering.
     * It does that by taking the first 6 alphabetical characters as lowercase characters and maps them to ascending
     * numbers based on those first characters.
     * 
     * @param label The string that should be converted.
     * @return A number for the string that is higher for lexicographic earlier strings and lower for later elements.
     */
    def static int priorityOf(String label) {
        // Take the first 6 alphabetical chars as lowercase chars to limit them to one of 26 values each.
        // 6 chars are taken, because 26^6 < 2^32 < 26^7, so 6 chars is the limit of what can be represented as a unique
        // orderable int, which can take 2^32 different values.
        val alphabeticalChars = label.chars.filter [
            Character.isAlphabetic(it)
        ].map [
            Character.toLowerCase(it)
        ].limit(6).toArray.toList
        val numChars = alphabeticalChars.size
        
        // Convert the remaining string into a number as if the string would be a base-26 number with 'a' as the lowest
        // and 'z' as the highest digit for that number. 
        val number = alphabeticalChars.fold(0, [ prevValue, nextChar |
            return prevValue * 26 + nextChar - 'a' + 1
        ])
        // Always have the *26 happen 6 times, so do the remaining ones here if the fold exited earlier.
        // Also return the negative value to have the ordering going a->z and not z->a.
        return -number * Math.toIntExact(Math.round(Math.pow(26, 6 - numChars)))
    }
    
}