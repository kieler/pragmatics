package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.IAction.ActionContext
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses
import de.scheidtbachmann.osgimodel.BasicOsgiObject
import de.scheidtbachmann.osgimodel.ServiceInterface
import de.scheidtbachmann.osgimodel.visualization.context.IOverviewVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import java.util.List
import org.eclipse.elk.core.options.CoreOptions

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

//import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

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
     * If the id should be truncated by the prefix of the {@link OsgiOptions#SHORTEN_BY} option, this returns a
     * truncated version of the id, otherwise the id itself.
     * 
     * @param id The id that should possibly be truncated.
     * @return The possibly truncated id.
     */
    def static String getId(String id, ViewContext usedContext) {
        val prefix = usedContext.getOptionValue(SHORTEN_BY) as String
        if (!prefix.empty && id.startsWith(prefix)) {
            return "..." + id.substring(prefix.length)
        }
        return id
    }
    
    /**
     * Filters the list of given bundles by the filter options of the diagram options and the overview context they are
     * shown in.
     * 
     * @param elements The unfiltered list of all elements.
     * @param moc The element overview context showing which of the given elements are relevant
     * @param usedContext The ViewContext used to display the diagram these elements are shown in.
     * @return An Iterable of the elements filtered by the diagram options.
     */
    def static <M extends BasicOsgiObject> Iterable<M> filteredElements(List<M> elements, IOverviewVisualizationContext<M> moc,
        ViewContext usedContext) {
        val elementsInContext = elements.filter [
            moc.modelElement.contains(it)
        ]
        val prefix = usedContext.getOptionValue(FILTER_BY) as String
        if (prefix !== "") {
            return elementsInContext.filter[ it.uniqueId.startsWith(prefix) ]
        } else {
            return elementsInContext
        }
    }
    
    /**
     * Filters the list of given visualization contexts by the filter options of the diagram options.
     * 
     * @param visualizationContexts The unfiltered list of all visualization contexts.
     * @param usedContext The ViewContext used to display the diagram these visualizations are shown in.
     * @return An Iterable of the visualization contexts filtered by the diagram options.
     */
    def static <M extends BasicOsgiObject> Iterable<? extends IVisualizationContext<M>>
    filteredBasicOsgiObjectContexts(List<? extends IVisualizationContext<M>> visualizationContexts, ViewContext usedContext) {
        val prefix = usedContext.getOptionValue(FILTER_BY) as String
        if (prefix !== "") {
            return visualizationContexts.filter[ it.modelElement.uniqueId.startsWith(prefix) ]
        } else {
            return visualizationContexts
        }
    }
    
    /**
     * Basically the same as {@link #filteredBasicOsgiObjectContexts(List, ViewContext)},
     * just for the non-BasicOsgiObject of {@link ServiceInterface}s.
     * 
     * @see #filteredBasicOsgiObjectContexts(List, ViewContext)
     */
    def static Iterable<? extends IVisualizationContext<ServiceInterface>> filteredServiceInterfaceContexts(
        List<? extends IVisualizationContext<ServiceInterface>> visualizationContexts, ViewContext usedContext) {
        val prefix = usedContext.getOptionValue(FILTER_BY) as String
        if (prefix !== "") {
            return visualizationContexts.filter[ it.modelElement.name.startsWith(prefix) ]
        } else {
            return visualizationContexts
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
    
    /**
     * Configures the layout of any overview node. Configures the box layout algorithm of elk.
     */
    def static void configureBoxLayout(KNode node) {
        node => [
            DiagramSyntheses.setLayoutOption(node, CoreOptions::ALGORITHM, "org.eclipse.elk.box")
//            setLayoutOption(CoreOptions::EXPAND_NODES, true)
        ]
    }
    
}