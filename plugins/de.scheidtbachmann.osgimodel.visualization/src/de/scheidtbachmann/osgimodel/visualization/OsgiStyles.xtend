package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRectangle
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle
import de.cau.cs.kieler.klighd.krendering.KText
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.actions.FocusAction
import de.scheidtbachmann.osgimodel.visualization.actions.ReferencedSynthesisExpandAction
import de.scheidtbachmann.osgimodel.visualization.actions.RevealRequiredBundlesAction
import de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedByBundlesAction

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

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
    @Inject extension KPolylineExtensions
    @Inject extension KRenderingExtensions
    extension KRenderingFactory = KRenderingFactory.eINSTANCE
    
    /** The roundness of visualized rounded rectangles. */
    val ROUNDNESS = 4
    
    /**
     * Adds a simple rendering for a {@link Product} to the given node.
     */
    def KRoundedRectangle addProductRendering(KNode node, String name) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            setShadow("black".color, 4, 4)
            addSimpleLabel(name)
            // Styles of the surrounding rectangle
        ]
    }
    
    /**
     * Adds a rendering for a {@link Bundle} to the given node.
     * Contains The name of the bundle, a button to focus this bundle and text for the ID and description of this bundle.
     * 
     * @param node The KNode this rendering should be attached to.
     * @param b The bundle this rendering represents.
     * @param context The view context used in the synthesis.
     * 
     * @return The entire rendering for a bundle.
     */
    def KRoundedRectangle addBundleRendering(KNode node, Bundle b, ViewContext context) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            setGridPlacement(1)
            addRectangle => [
                setGridPlacement(2)
                invisible = true
                addSimpleLabel(b.descriptiveName)
                addButton("Focus", FocusAction::ID)
            ]
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                invisible = true
                addSimpleLabel("ID: " + b.uniqueId)
            ]
            addRectangle => [
                invisible = true
                addSimpleLabel("Description: " + descriptionLabel(b, context))
            ]
            setShadow("black".color, 4, 4)
            addRectangle => [
                setGridPlacementData => [
                    minCellHeight = 20
                    minCellWidth = 20
                ]
                invisible = true
                addChildArea
            ]
        ]
    }
    
    /**
     * Returns the descriptive text of a bundle shortened by the {@link OsgiOptions#DESCRIPTION_LENGTH} option.
     */
    private def String descriptionLabel(Bundle b, ViewContext context) {
        val descriptionLabel = b.about
        val threshold = context.getOptionValue(DESCRIPTION_LENGTH) as Number
        if (descriptionLabel === null) {
            return ""
        }
        if (descriptionLabel.length <= threshold.intValue) {
            return descriptionLabel
        }
        return descriptionLabel.substring(0, threshold.intValue) + " ..."
    }
    
    /**
     * Adds a simple rendering for a {@link Bundle} to the given node that can be expanded to call the
     * {link ReferencedSynthesisExpandAction} to dynamically call the bundle synthesis for the given bundle.
     */
    def addBundleInOverviewRendering(KNode node, Bundle b, String label) {
        // Expanded
        node.addRectangle => [
            setAsExpandedView
            invisible = true
            addChildArea
            addButton("-", ReferencedSynthesisExpandAction::ID)
        ]
        
        // Collapsed
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsCollapsedView
            setGridPlacement(2)
            addSimpleLabel(label)
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            addButton("+", ReferencedSynthesisExpandAction::ID)
            setShadow("black".color, 4, 4)
            tooltip = b.uniqueId
            setPointPlacementData => [
                minHeight = 20
                minWidth = 20
            ]
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
            addSimpleLabel(text)
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                setGridPlacementData => [
                    minCellHeight = 20
                    minCellWidth = 20
                ]
                invisible = true
                addChildArea
                val actionId = CollapseExpandAction.ID
                addButton("-", actionId)
                addSingleOrMultiClickAction(actionId)
            ]
            setShadow("black".color, 4, 4)
        ]
        
        // Collapsed
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsCollapsedView
            setGridPlacement(1)
            addSimpleLabel(text)
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                setGridPlacementData => [
                    minCellHeight = 20
                    minCellWidth = 20
                ]
                invisible = true
                val actionId = CollapseExpandAction.ID
                addButton("+", actionId)
                addSingleOrMultiClickAction(actionId)
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
                setPointPlacementData(RIGHT, 3f, 0, TOP, 0, 0, H_RIGHT, V_TOP, 0, 0, size.width, size.height)
                addSingleOrMultiClickAction(actionId)
            ]
        ]
    }
    
    /**
     * The rendering of a port that connects the bundles used by this component. Issues the
     * {@link RevealUsedByBundlesAction} if clicked.
     */
    def KRectangle addUsedByBundlesPortRendering(KPort port) {
        return port.addRectangle => [
            background = "gray".color
            tooltip = "Show bundles that require this bundle."
            addSingleClickAction(RevealUsedByBundlesAction::ID)
        ]
    }
    
    /**
     * The rendering of a port that connects the bundles required by this component. Issues the
     * {@link RevealRequiredBundlesAction} if clicked.
     */
    def KRectangle addRequiredBundlesPortRendering(KPort port) {
        return port.addRectangle => [
            background = "gray".color
            tooltip = "Show required bundles."
            addSingleClickAction(RevealRequiredBundlesAction::ID)
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
}