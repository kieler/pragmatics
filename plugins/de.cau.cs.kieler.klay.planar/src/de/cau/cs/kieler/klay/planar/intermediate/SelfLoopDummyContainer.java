/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * Stores the the dummies created by the {@link SelfLoopDummyProcessor} to avoid self loops.
 * 
 * @author pkl
 */
public class SelfLoopDummyContainer {

    private PNode dummyNode1;
    private PEdge dummyEdge1;

    private PNode dummyNode2;
    private PEdge dummyEdge2;

    /**
     * Creates a new self loop dummy container with two dummy nodes and the corresponding dummy
     * edges.
     * 
     * @param dummyNode1
     *            first dummy node
     * @param dummyNode2
     *            second dummy node
     * @param dummyEdge1
     *            dummy edge produced by the connection to the first dummy node
     * @param dummyEdge2
     *            dummy edge produced by the connection to the second dummy node
     */
    public SelfLoopDummyContainer(final PNode dummyNode1, final PNode dummyNode2,
            final PEdge dummyEdge1, final PEdge dummyEdge2) {
        this.dummyNode1 = dummyNode1;
        this.dummyNode2 = dummyNode2;
        this.dummyEdge1 = dummyEdge1;
        this.dummyEdge2 = dummyEdge2;
    }

    /**
     * @return the dummyEdge1
     */
    public PEdge getDummyEdge1() {
        return dummyEdge1;
    }

    /**
     * @return the dummyEdge2
     */
    public PEdge getDummyEdge2() {
        return dummyEdge2;
    }

    /**
     * @return the dummyNode1
     */
    public PNode getDummyNode1() {
        return dummyNode1;
    }

    /**
     * @return the dummyNode2
     */
    public PNode getDummyNode2() {
        return dummyNode2;
    }

}
