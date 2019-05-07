package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.actions.CollapseExpandAction
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.scheidtbachmann.osgimodel.Product
import org.eclipse.elk.core.options.CoreOptions

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
    @Inject extension KPolylineExtensions
    @Inject extension KRenderingExtensions
    
    val ROUNDNESS = 4
    
    /**
     * Adds a rendering for a {@link Product} to the given node.
     */
    def KRoundedRectangle addProductRendering(KNode node) {
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            // Styles of the surrounding rectangle
        ]
    }
    
    /**
     * Adds a rendering allowing a container rendering for any context with the given text as its headline.
     */
    def KRoundedRectangle addOverviewRendering(KNode node, String text) {
        // TODO: make the minimum height so that the corner of the button does not stick out
        // Expanded
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsExpandedView
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            setGridPlacement(1)
            addText(text)
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                invisible = true
                addChildArea
                val actionId = CollapseExpandAction.ID
                addExpandedButton(actionId)
                addDoubleClickAction(actionId)
            ]
        ]
        
        // Collapsed
        node.addRoundedRectangle(ROUNDNESS, ROUNDNESS) => [
            setAsCollapsedView
            setBackgroundGradient("LightBlue1".color, "LightBlue2".color, 90)
            setGridPlacement(1)
            addText(text)
            addHorizontalSeperatorLine(1, 0)
            addRectangle => [
                invisible = true
                val actionId = CollapseExpandAction.ID
                addCollapsedButton(actionId)
                addDoubleClickAction(actionId)
            ]
        ]
    }
    
    def KRendering addExpandedButton(KContainerRendering container, String actionId) {
        return container.addButton("-", actionId)
    }
    
    def KRendering addCollapsedButton(KContainerRendering container, String actionId) {
        return container.addButton("+", actionId)
    }
    
    def KRendering addButton(KContainerRendering container, String text, String actionId) {
        val button = container.addPolygon => [
            addKPosition(RIGHT, 0.5f, 0, TOP, -0.5f, 0)
            addKPosition(RIGHT, 0.5f, 0, TOP, 19, 0)
            addKPosition(RIGHT, 18, 0, TOP, -0.5f, 0)
            addDoubleClickAction(actionId)
        ]
        container.addText(text) => [
            fontSize = 8
            fontBold = true
            val size = estimateTextSize
            setPointPlacementData(RIGHT, 3f, 0, TOP, 0, 0, H_RIGHT, V_TOP, 0, 0, size.width, size.height)
            addDoubleClickAction(actionId)
        ]
        return button
    }
}