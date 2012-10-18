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
 * 
 * @author pkl
 */
public class RectShapeEdgeProperties {

    /** Uses this to identify a non set front, null is not suitable in that case.*/
    public static final PEdge EMPTY_FRONT = new PEdge();

    /** Corner is the next node of a edge while walking around the face edges. */
    private PNode corner = null;

    /** Turn of an edge during the rectangular shape processor. */
    private int turn = 0;

    /** Front edge of a edge. */
    private PEdge front = null;

    /** Previous edge  of front edge of a edge. */
    private PEdge previousFront = null;
    
    /** A edge that is predecessor of the current edge. */
    private PEdge previousEdge = null;

    /** Face side index; 0 left, 1 top, 2 right, 3 bottom. */
    private int sideIndex = -1;

    /** Path length to the front of an edge. */
    private int pathLength = 0;

    /** Next edge of an edge connected by the corner of the currentEdge. */
    private PEdge next = null;
    
    /**
     * @return the corner
     */
    public PNode getCorner() {
        return corner;
    }

    /**
     * @param corner
     *            the corner to set
     */
    public void setCorner(final PNode corner) {
        this.corner = corner;
    }

    /**
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @param turn
     *            the turn to set
     */
    public void setTurn(final int turn) {
        this.turn = turn;
    }

    /**
     * @return the front
     */
    public PEdge getFront() {
        return front;
    }

    /**
     * @param front
     *            the front to set
     */
    public void setFront(final PEdge front) {
        this.front = front;
    }

    /**
     * @return the previousEdge
     */
    public PEdge getPreviousEdge() {
        return previousEdge;
    }

    /**
     * @param previousEdge
     *            the previousEdge to set
     */
    public void setPreviousEdge(final PEdge previousEdge) {
        this.previousEdge = previousEdge;
    }

    /**
     * @return the sideIndex
     */
    public int getSideIndex() {
        return sideIndex;
    }

    /**
     * @param sideIndex
     *            the sideIndex to set
     */
    public void setSideIndex(final int sideIndex) {
        this.sideIndex = sideIndex;
    }

    /**
     * @return the pathLength
     */
    public int getPathLength() {
        return pathLength;
    }

    /**
     * @param pathLength
     *            the pathLength to set
     */
    public void setPathLength(final int pathLength) {
        this.pathLength = pathLength;
    }

    /**
     * @return the next
     */
    public PEdge getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(PEdge next) {
        this.next = next;
    }

    /**
     * @return the previousFront
     */
    public PEdge getPreviousFront() {
        return previousFront;
    }

    /**
     * @param previousFront the previousFront to set
     */
    public void setPreviousFront(PEdge previousFront) {
        this.previousFront = previousFront;
    }

}
