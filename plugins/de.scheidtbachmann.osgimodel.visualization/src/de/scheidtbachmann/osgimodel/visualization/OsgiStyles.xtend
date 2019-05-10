package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
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
    
    val ROUNDNESS = 4
    
    /**
     * Adds a rendering for a {@link Product} to the given node.
     */
    def KRoundedRectangle addProductRendering(KNode node, String name) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            setShadow("black".color, 4, 4)
            addSimpleLabel(name)
            // Styles of the surrounding rectangle
        ]
    }
    
    def KRoundedRectangle addBundleRendering(KNode node, Bundle b) {
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
                addText("Description: " + b.about?.substring(0, Math.min(b.about.length, 30)) + "...")
            ]
            addRectangle => [
                invisible = true
                addText("ID: " + b.uniqueId)
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
    
    def KRectangle addExpandedBundleRendering(KNode node, Bundle b) {
        node.addRectangle => [
            setAsExpandedView
            invisible = true
            addChildArea
            addButton("-", ReferencedSynthesisExpandAction::ID)
        ]
    }
    
    def KRoundedRectangle addCollapsedBundleRendering(KNode node, Bundle b) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsCollapsedView
            setGridPlacement(2)
            addSimpleLabel(b.descriptiveName)
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            addButton("+", ReferencedSynthesisExpandAction::ID)
            setShadow("black".color, 4, 4)
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
//            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
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
//            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
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
    
    def KRectangle addButton(KContainerRendering container, String text, String actionId) {
        return container.addRectangle => [
            setPointPlacementData(RIGHT, 0, 0,  TOP, 0, 0, H_RIGHT, V_TOP, 0, 0, 15, 15)
            addSingleOrMultiClickAction(actionId)
            addText(text) => [
                fontSize = 8
                fontBold = true
                val size = estimateTextSize
                setPointPlacementData(RIGHT, 3f, 0, TOP, 0, 0, H_RIGHT, V_TOP, 0, 0, size.width, size.height)
                addSingleOrMultiClickAction(actionId)
            ]
        ]
    }
    
    def KRectangle addUsedByBundlesPortRendering(KPort port) {
        return port.addRectangle => [
            background = "gray".color
            tooltip = "Show bundles used by this bundle."
            addSingleClickAction(RevealUsedByBundlesAction::ID)
        ]
    }
    
    def KRectangle addRequiredBundlesPortRendering(KPort port) {
        return port.addRectangle => [
            background = "gray".color
            tooltip = "Show required bundles."
            addSingleClickAction(RevealRequiredBundlesAction::ID)
        ]
    }
    
    def KText addSimpleLabel(KContainerRendering rendering, String text) {
        rendering.addText(text) => [
            // Add surrounding space
            setGridPlacementData().from(LEFT, 10, 0, TOP, 8, 0).to(RIGHT, 10, 0, BOTTOM, 8, 0)
        ]
    }
}