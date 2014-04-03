/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphiti;


import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Utility methods for the diagram layout manager for Graphiti based editors.
 * 
 * @author jayant
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class KimlGraphitiUtil {

    /**
     * Hidden constructor to avoid instantiation.
     */
    private KimlGraphitiUtil() {
    }

    /**
     * Returns an end point for an anchor.
     * 
     * @param node
     *            the node that owns the anchor
     * @param port
     *            the port that represents the anchor, or {@code null}
     * @param referenceNode
     *            the parent node to which edge points are relative, or {@code null}
     * @return the position of the anchor, relative to the reference node
     */
    public static KVector calculateAnchorEnds(final KNode node, final KPort port,
            final KNode referenceNode) {
        KVector pos = new KVector();
        if (port != null) {
            // the anchor end is represented by a port (box-relative anchor or fix-point anchor)
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            pos.x = portLayout.getXpos() + portLayout.getWidth() / 2;
            pos.y = portLayout.getYpos() + portLayout.getHeight() / 2;
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            pos.x += nodeLayout.getXpos();
            pos.y += nodeLayout.getYpos();
        } else {
            // the anchor end is determined by a chopbox anchor
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            pos.x = nodeLayout.getWidth() / 2 + nodeLayout.getXpos();
            pos.y = nodeLayout.getHeight() / 2 + nodeLayout.getYpos();
        }
        KimlUtil.toAbsolute(pos, node.getParent());
        if (referenceNode != null) {
            KimlUtil.toRelative(pos, referenceNode);
        }
        return pos;
    }

    /**
     * Given a graphics algorithm, find the first child that is not invisible. If the GA itself is
     * visible, it is returned.
     * 
     * @param graphicsAlgorithm
     *            the parent graphics algorithm
     * @return a visible graphics algorithm
     */
    public static GraphicsAlgorithm findVisibleGa(final GraphicsAlgorithm graphicsAlgorithm) {
        if (graphicsAlgorithm != null) {
            if (graphicsAlgorithm.getLineVisible() || graphicsAlgorithm.getFilled()) {
                return graphicsAlgorithm;
            }
            for (GraphicsAlgorithm ga : graphicsAlgorithm.getGraphicsAlgorithmChildren()) {
                GraphicsAlgorithm result = findVisibleGa(ga);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

}
