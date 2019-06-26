package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRectangle
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.krendering.LineStyle
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.ServiceInterface
import de.scheidtbachmann.osgimodel.visualization.actions.ContextCollapseExpandAction
import de.scheidtbachmann.osgimodel.visualization.actions.FocusAction
import de.scheidtbachmann.osgimodel.visualization.actions.RevealRequiredBundlesAction
import de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedByBundlesAction
import de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedPackagesAction
import java.util.List

import static extension de.cau.cs.kieler.klighd.microlayout.PlacementUtil.*
import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * The renderings and styles of OsgiModels.
 * 
 * @author nre
 */
@ViewSynthesisShared
class OsgiStyles {
    @Inject extension KColorExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KLabelExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KRenderingExtensions
    
    /** The roundness of visualized rounded rectangles. */
    val ROUNDNESS = 4
    
    // ------------------------------------- Generic renderings -------------------------------------
    
    /**
     * Adds a simple rendering for any named OSGi element to the given node.
     */
    def KRoundedRectangle addGenericRendering(KNode node, String name) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setShadow("black".color, 4, 4)
            addSimpleLabel(name)
            // Styles of the surrounding rectangle
        ]
    }
    
    /**
     * Adds a rendering allowing a container rendering for any context with the given text as its headline.
     */
    def void addOverviewRendering(KNode node, String text) {
        // Expanded
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsExpandedView
            setGridPlacement(1)
            addDoubleClickAction(CollapseExpandAction.ID)
            addRectangle => [
                setGridPlacement(5)
                invisible = true
                addRectangle => [
                    invisible = true
                    addSimpleLabel(text)
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("Focus", FocusAction::ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("-", CollapseExpandAction.ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
            ]
            addHorizontalSeperatorLine(1, 0)
            addChildArea
            setShadow("black".color, 4, 4)
        ]
        
        // Collapsed
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsCollapsedView
            setGridPlacement(1)
            addDoubleClickAction(CollapseExpandAction.ID)
            addRectangle => [
                setGridPlacement(5)
                invisible = true
                addRectangle => [
                    invisible = true
                    addSimpleLabel(text)
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("Focus", FocusAction::ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("+", CollapseExpandAction.ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
            ]
            setShadow("black".color, 4, 4)
        ]
    }
    
    /**
     * Adds a button in the top right corner of any container rendering that will cause an action to be called.
     * 
     * @param container The parent rendering this button should be added to.
     * @param text The text that should be displayed on the button.
     * @param actionId The id of the action that should be called if the button is clicked.
     */
    def KRectangle addButton(KContainerRendering container, String text, String actionId) {
        return container.addRectangle => [
            setPointPlacementData(RIGHT, 0, 0,  TOP, 0, 0, H_RIGHT, V_TOP, 0, 0, 15, 15)
            addSingleOrMultiClickAction(actionId)
            addText(text) => [
                suppressSelectability
                fontSize = 8
                fontBold = true
                val size = estimateTextSize
                setPointPlacementData(RIGHT, 0, 0.5f, TOP, 0, 0.5f, H_CENTRAL, V_CENTRAL, 4f, 4f, size.width, size.height)
                addSingleOrMultiClickAction(actionId)
            ]
        ]
    }
    
    /**
     * Highlight the port that all elements that should be connected to this port are in displayed.
     * @param port The port that should be highlighted.
     */
    def void highlightAllShown(KPort port) {
        port.data.filter(KRendering).forEach [
            background = "white".color
            selectionBackground = "white".color
        ]
    }
    
    /**
     * Remove the highlighting color that showed that all elements that should be connected to this port were displayed.
     * @param port The port that should get its original look.
     */
    def void unHighlightAllShown(KPort port) {
        port.data.filter(KRendering).forEach [
            background = "black".color
            selectionBackground = "black".color
        ]
    }
    
    /**
     * Adds a simple text label to any rendering with some surrounding space for better readability.
     */
    def KText addSimpleLabel(KContainerRendering rendering, String text) {
        rendering.addText(text) => [
            // Add surrounding space
            setGridPlacementData().from(LEFT, 10, 0, TOP, 8, 0).to(RIGHT, 10, 0, BOTTOM, 8, 0)
            // Remove the default bold property on selected texts.
            selectionFontBold = false
            selectionBackground = KlighdConstants.DEFAULT_SELECTION_HIGHLIGHTING_BACKGROUND_COLOR
        ]
    }
    
    // ------------------------------------- Product renderings -------------------------------------
    
    /**
     * Adds a simple rendering for a {@link Product} to the given node that can be expanded to call the
     * {link ReferencedSynthesisExpandAction} to dynamically call the product synthesis for the given product.
     */
    def KRoundedRectangle addProductInOverviewRendering(KNode node, Product p, String name) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setGridPlacement(3)
            addRectangle => [
                invisible = true
                addSimpleLabel(name)
            ]
            addVerticalLine(RIGHT, 0, 1) => [
                setGridPlacementData => [
                    flexibleWidth = false
                ]
            ]
            addButton("+", ContextCollapseExpandAction::ID) => [
                setGridPlacementData => [
                    flexibleWidth = false
                ]
                lineWidth = 0
            ]
            setBackgroundGradient("#FFEAE0".color, "FFD3BF".color, 90)
            addDoubleClickAction(ContextCollapseExpandAction::ID)
            setShadow("black".color, 4, 4)
            tooltip = p.uniqueId
        ]
    }
    
    /**
     * Adds a rendering for a {@link Product} to the given node.
     * Contains the name of the product, a button to focus this product and text for the ID and description of this product.
     * 
     * @param node The KNode this rendering should be attached to.
     * @param p The product this rendering represents.
     * @param context The view context used in the synthesis.
     * 
     * @return The entire rendering for a product.
     */
    def KRoundedRectangle addProductRendering(KNode node, Product p, ViewContext context) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("#FFEAE0".color, "#FFD3BF".color, 90)
            setGridPlacement(1)
            addRectangle => [
                setGridPlacement(3)
                invisible = true
                addRectangle => [
                    invisible = true
                    addSimpleLabel(p.descriptiveName)
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("-", ContextCollapseExpandAction::ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
            ]
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                invisible = true
                addSimpleLabel("ID: " + SynthesisUtils.getId(p.uniqueId, context))
            ]
            addRectangle => [
                invisible = true
                addSimpleLabel("Description: " + SynthesisUtils.descriptionLabel(p.about, context))
            ]
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                setGridPlacementData => [
                    minCellHeight = 20
                    minCellWidth = 20
                ]
                invisible = true
                addChildArea
            ]
            addDoubleClickAction(ContextCollapseExpandAction::ID)
            setShadow("black".color, 4, 4)
        ]
    }
    
    // ------------------------------------- Bundle renderings -------------------------------------
    
    /**
     * Adds a simple rendering for a {@link Bundle} to the given node that can be expanded to call the
     * {link ReferencedSynthesisExpandAction} to dynamically call the bundle synthesis for the given bundle.
     */
    def addBundleInOverviewRendering(KNode node, Bundle b, String label) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setGridPlacement(3)
            addRectangle => [
                invisible = true
                addSimpleLabel(label)
            ]
            addVerticalLine(RIGHT, 0, 1) => [
                setGridPlacementData => [
                    flexibleWidth = false
                ]
            ]
            addButton("+", ContextCollapseExpandAction::ID) => [
                setGridPlacementData => [
                    flexibleWidth = false
                ]
                lineWidth = 0
            ]
            setBackgroundGradient("#E0F7FF".color, "#BFEFFF".color, 90)
            addDoubleClickAction(ContextCollapseExpandAction::ID)
            setShadow("black".color, 4, 4)
            tooltip = b.uniqueId
        ]
    }
    
    /**
     * Adds a rendering for a {@link Bundle} to the given node.
     * Contains the name of the bundle, a button to focus this bundle and text for the ID and description of this bundle.
     * 
     * @param node The KNode this rendering should be attached to.
     * @param b The bundle this rendering represents.
     * @param context The view context used in the synthesis.
     * 
     * @return The entire rendering for a bundle.
     */
    def KRoundedRectangle addBundleRendering(KNode node, Bundle b, ViewContext context) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("#E0F7FF".color, "#BFEFFF".color, 90)
            setGridPlacement(1)
            addRectangle => [
                setGridPlacement(3)
                invisible = true
                addRectangle => [
                    invisible = true
                    addSimpleLabel(b.descriptiveName)
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("-", ContextCollapseExpandAction::ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
            ]
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                invisible = true
                addSimpleLabel("ID: " + SynthesisUtils.getId(b.uniqueId, context))
            ]
            addRectangle => [
                invisible = true
                addSimpleLabel("Description: " + SynthesisUtils.descriptionLabel(b.about, context))
            ]
            addDoubleClickAction(ContextCollapseExpandAction::ID)
            setShadow("black".color, 4, 4)
        ]
    }
    
    /**
     * The rendering of a port that connects the bundles used by this component. Issues the
     * {@link RevealUsedByBundlesAction} if clicked.
     */
    def KRectangle addUsedByBundlesPortRendering(KPort port, int numUsedByBundles, boolean allShown) {
        return port.addRectangle => [
            background = if (allShown) "white".color else "black".color
            val tooltipText = "Show bundles that require this bundle (" + numUsedByBundles + " total)."
            tooltip = tooltipText
            addSingleClickAction(RevealUsedByBundlesAction::ID)
        ]
    }
    
    /**
     * The rendering of a port that connects the bundles required by this component. Issues the
     * {@link RevealRequiredBundlesAction} if clicked.
     */
    def KRectangle addRequiredBundlesPortRendering(KPort port, int numReqBundles, boolean allShown) {
        return port.addRectangle => [
            background = if (allShown) "white".color else "black".color
            val tooltipText = "Show required bundles (" + numReqBundles + " total)."
            tooltip = tooltipText
            addSingleClickAction(RevealRequiredBundlesAction::ID)
        ]
    }
    
    /**
     * Adds the rendering for an edge showing a bundle requirement.
     */
    def addRequiredBundleEdgeRendering(KEdge edge) {
        edge.addPolyline => [
            lineWidth = 2
            addHeadArrowDecorator => [
                lineWidth = 1
                background = "black".color
                foreground = "black".color
            ]
            lineStyle = LineStyle.DASH
        ]
    }
    
    /**
     * Adds the rendering of a port that connects the bundle to the bundles providing its used packages.
     */
    def addUsedPackagesPortRendering(KPort port, boolean allShown) {
        port.addEllipse => [
            background = if (allShown) "white".color else "black".color
            val tooltipText = "Show the used packages."
            tooltip = tooltipText
            addSingleClickAction(RevealUsedPackagesAction::ID)
        ]
    }
    
    // ------------------------------------- Package renderings -------------------------------------
    
    /**
     * Adds the rendering for an edge showing a bundle requirement.
     */
    def addInternalUsedPackagesBundleEdgeRendering(KEdge edge, List<PackageObject> packages, Product product, ViewContext context) {
        val tooltipText = "Packages\n" + packages.map [ SynthesisUtils.getId(it.uniqueId, context) + "\n" ] 
            + " for product " + SynthesisUtils.getId(product.uniqueId, context)
        edge.addPolyline => [
            addHeadArrowDecorator => [
                lineWidth = 1
                background = "black".color
                foreground = "black".color
            ]
            lineStyle = LineStyle.DASH
            tooltip = tooltipText
        ]
        edge.createLabel => [
            configureCenterEdgeLabel(SynthesisUtils.getId(product.uniqueId, context)
                + " (" + packages.size + " packages)")
            tooltip = tooltipText
        ]
    }
    
    // ------------------------------------- ServiceInterface renderings -------------------------------------
    
    /**
     * Adds a simple rendering for a {@link ServiceInterface} to the given node that can be expanded to call the
     * {link ReferencedSynthesisExpandAction} to dynamically call the service interface synthesis for the given bundle.
     */
    def addServiceInterfaceInOverviewRendering(KNode node, ServiceInterface s, String name) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setGridPlacement(3)
            addRectangle => [
                invisible = true
                addSimpleLabel(name)
            ]
            addVerticalLine(RIGHT, 0, 1) => [
                setGridPlacementData => [
                    flexibleWidth = false
                ]
            ]
            addButton("+", ContextCollapseExpandAction::ID) => [
                setGridPlacementData => [
                    flexibleWidth = false
                ]
                lineWidth = 0
            ]
            setBackgroundGradient("#FFE0E0".color, "FFBFBF".color, 90)
            addDoubleClickAction(ContextCollapseExpandAction::ID)
            setShadow("black".color, 4, 4)
            tooltip = s.name
        ]
    }
    
    /**
     * Adds a rendering for a {@link ServiceInterface} to the given node.
     * 
     * @param node The KNode this rendering should be attached to.
     * @param si The service interface this rendering represents.
     * @param context The view context used in the synthesis.
     * 
     * @return The entire rendering for a service interface.
     */
    def KRoundedRectangle addServiceInterfaceRendering(KNode node, ServiceInterface si, ViewContext context) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("#FFE0E0".color, "#FFBFBF".color, 90)
            setGridPlacement(1)
            addRectangle => [
                setGridPlacement(3)
                invisible = true
                addRectangle => [
                    invisible = true
                    addSimpleLabel(si.name)
                ]
                addVerticalLine(RIGHT, 0, 1) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                ]
                addButton("-", ContextCollapseExpandAction::ID) => [
                    setGridPlacementData => [
                        flexibleWidth = false
                    ]
                    lineWidth = 0
                ]
            ]
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                invisible = true
                addSimpleLabel("Description: " + SynthesisUtils.descriptionLabel(si.about, context))
            ]
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                setGridPlacementData => [
                    minCellHeight = 20
                    minCellWidth = 20
                ]
                invisible = true
                addChildArea
            ]
            addDoubleClickAction(ContextCollapseExpandAction::ID)
            setShadow("black".color, 4, 4)
        ]
    }
    
}