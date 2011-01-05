/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.importer.util;

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.Port;

/**
 * A utility class that provides functionality that can be accessed by xtend
 * transformations.
 * 
 * @author mri
 */
public final class XtendUtil {
    /**
     * A private constructor to make the class not instantiable.
     */
    private XtendUtil() {
        // do nothing
    }

    /**
     * Transfers the data from a KNode to a KEG Node.
     * 
     * @param node
     *            the KNode
     * @param kegNode
     *            the KEG Node
     */
    public static void transferKGraphNode(final KNode node, final Node kegNode) {
        kegNode.setNodeLabel(node.getLabel().getText());
        // copy layout data
        kegNode.getData().addAll(EcoreUtil.copyAll(node.getData()));
    }

    /**
     * Transfers the data from a KEdge to a KEG Edge.
     * 
     * @param edge
     *            the KEdge
     * @param kegEdge
     *            the KEG Edge
     */
    public static void transferKGraphEdge(final KEdge edge, final Edge kegEdge) {
        // copy layout data
        kegEdge.getData().addAll(EcoreUtil.copyAll(edge.getData()));
    }

    /**
     * Transfers the data from a KPort to a KEG Port.
     * 
     * @param port
     *            the KPort
     * @param kegPort
     *            the KEG Port
     */
    public static void transferKGraphPort(final KPort port, final Port kegPort) {
        // copy layout data
        kegPort.getData().addAll(EcoreUtil.copyAll(port.getData()));
    }
}
