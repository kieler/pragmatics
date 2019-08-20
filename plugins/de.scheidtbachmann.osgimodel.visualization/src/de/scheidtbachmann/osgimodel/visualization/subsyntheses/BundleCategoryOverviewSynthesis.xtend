package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import de.scheidtbachmann.osgimodel.visualization.context.BundleCategoryContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleCategoryOverviewContext
import java.util.EnumSet
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.Direction
import org.eclipse.elk.core.options.SizeConstraint

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all bundle categories in the given list of bundle categories.
 * 
 * @author nre
 */
class BundleCategoryOverviewSynthesis extends AbstractSubSynthesis<BundleCategoryOverviewContext, KNode> {
    @Inject extension KEdgeExtensions
    @Inject extension KNodeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension OsgiStyles
    @Inject SimpleBundleCategorySynthesis simpleBundleCategorySynthesis
    
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    override transform(BundleCategoryOverviewContext bundleCategoryOverviewContext) {
        return #[
            createNode => [
                associateWith(bundleCategoryOverviewContext)
                data += createKIdentifier => [ it.id = bundleCategoryOverviewContext.hashCode.toString ]
                if (bundleCategoryOverviewContext.expanded) {
                    initiallyExpand
                } else {
                    initiallyCollapse
                }
                setLayoutOption(it, CoreOptions::ALGORITHM, "org.eclipse.elk.layered")
                setLayoutOption(it, CoreOptions::DIRECTION, Direction.DOWN)
                setLayoutOption(CoreOptions::NODE_SIZE_CONSTRAINTS, EnumSet.of(SizeConstraint.MINIMUM_SIZE))
                addOverviewRendering("Bundle Categories", bundleCategoryOverviewContext.overviewText)
                
                // remove the padding of the invisible container.
                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0))
                
                // Add all simple bundle category renderings in a first subgraph (top)
                val collapsedOverviewNode = transformCollapsedBundleCategoriesOverview(bundleCategoryOverviewContext)
                children += collapsedOverviewNode
                
                // Add all detailed bundle category renderings and their connections in a second subgraph (bottom)
                val detailedOverviewNode = transformDetailedBundleCategoriesOverview(bundleCategoryOverviewContext)
                children += detailedOverviewNode
                
                // Put an invisible edge between the collapsed and detailed overviews to guarantee the simple renderings
                // are above the detailed ones.
                collapsedOverviewNode.outgoingEdges += createEdge => [
                    addPolyline => [
                        invisible = true
                    ]
                    target = detailedOverviewNode
                ]
            ]
        ]
    }
    
    /**
     * The top part of the bundle category overview rendering containing all collapsed bundle category renderings in a
     * box layout.
     * 
     * @param bundleCategoryOverviewContext The overview context for all bundle categories in this subsynthesis.
     */
    private def KNode transformCollapsedBundleCategoriesOverview(
        BundleCategoryOverviewContext bundleCategoryOverviewContext) {
        val filteredCollapsedBundleCategoryContexts = SynthesisUtils.filteredElementContexts(
            bundleCategoryOverviewContext.collapsedElements, usedContext)
        createNode => [
            associateWith(bundleCategoryOverviewContext)
            configureBoxLayout
            addInvisibleContainerRendering
            tooltip = bundleCategoryOverviewContext.overviewText
            
            filteredCollapsedBundleCategoryContexts.sortBy [
                modelElement.categoryName
            ].forEach [ collapsedBundleCategoryContext, index |
                children += simpleBundleCategorySynthesis.transform(
                    collapsedBundleCategoryContext as BundleCategoryContext, -index)
            ]
        ]
    }
    
    /**
     * The bottom part of the bundle category overview rendering containing all detailed bundle category renderings and
     * their connections in a layered layout.
     * 
     * @param bundleCategoryOverviewContext The overview context for all bundle categories in this subsynthesis.
     */
    private def KNode transformDetailedBundleCategoriesOverview(
        BundleCategoryOverviewContext bundleCategoryOverviewContext) {
        val filteredDetailedBundleCategoryContexts = SynthesisUtils.filteredElementContexts(
            bundleCategoryOverviewContext.detailedElements, usedContext)
        createNode => [
            associateWith(bundleCategoryOverviewContext)
            configureOverviewLayout
            addInvisibleContainerRendering
            tooltip = bundleCategoryOverviewContext.overviewText
            
            children += filteredDetailedBundleCategoryContexts.flatMap [
                return simpleBundleCategorySynthesis.transform(it as BundleCategoryContext) // TODO: make a specific synthesis for expanded bundle categories!
            ]
        ]
    }
    
}