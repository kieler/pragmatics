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
package de.cau.cs.kieler.papyrus.sequence.graph;

/**
 * Message representation for SGraphs.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SMessage extends SGraphElement {
    private static final long serialVersionUID = 6326794211792613083L;
    private SLifeline source;
    private SLifeline target;
    private double sourceYPos;
    private double targetYPos;
    private double layerYPos;

    /**
     * Constructor that takes the source and target lifelines as arguments.
     * 
     * @param source
     *            the source lifeline
     * @param target
     *            the target lifeline
     */
    public SMessage(final SLifeline source, final SLifeline target) {
        this.source = source;
        this.target = target;
        this.layerYPos = -1.0f;
        source.getOutgoingMessages().add(this);
        target.getIncomingMessages().add(this);
    }

    /**
     * Get the source lifeline of the message.
     * 
     * @return the source lifeline
     */
    public SLifeline getSource() {
        return source;
    }

    /**
     * Set the source lifeline of the message.
     * 
     * @param source
     *            the new source lifeline
     */
    public void setSource(final SLifeline source) {
        this.source.getOutgoingMessages().remove(this);
        this.source = source;
        this.source.getOutgoingMessages().add(this);
    }

    /**
     * Get the target lifeline of the message.
     * 
     * @return the target lifeline
     */
    public SLifeline getTarget() {
        return target;
    }

    /**
     * Set the target lifeline of the message.
     * 
     * @param target
     *            the new target lifeline
     */
    public void setTarget(final SLifeline target) {
        this.target.getIncomingMessages().remove(this);
        this.target = target;
        this.target.getIncomingMessages().add(this);
    }

    /**
     * Get the vertical position of the layer of the message.
     * 
     * @return the vertical position
     */
    public double getLayerYPos() {
        return layerYPos;
    }

    /**
     * Set the vertical position of the layer of the message.
     * 
     * @param yPosition
     *            the new vertical position
     */
    public void setLayerYPos(final double yPosition) {
        layerYPos = yPosition;
        sourceYPos = yPosition;
        targetYPos = yPosition;
        if (source.getGraph().getSize().y < yPosition) {
            source.getGraph().getSize().y = yPosition;
        }
    }

    /**
     * Get the vertical position at the source lifeline of the message.
     * 
     * @return the vertical position at the source lifeline
     */
    public double getSourceYPos() {
        return sourceYPos;
    }

    /**
     * Set the vertical position at the source lifeline of the message.
     * 
     * @param sourceYPos
     *            the new vertical position at the source lifeline
     */
    public void setSourceYPos(final double sourceYPos) {
        this.sourceYPos = sourceYPos;
        if (source.getGraph().getSize().y < sourceYPos) {
            source.getGraph().getSize().y = sourceYPos;
        }
    }

    /**
     * Get the vertical position at the target lifeline of the message.
     * 
     * @return the vertical position at the target lifeline
     */
    public double getTargetYPos() {
        return targetYPos;
    }
    /**
     * Set the vertical position at the target lifeline of the message.
     * 
     * @param targetYPos
     *            the new vertical position at the target lifeline
     */
    public void setTargetYPos(final double targetYPos) {
        this.targetYPos = targetYPos;
        if (target.getGraph().getSize().y < targetYPos) {
            target.getGraph().getSize().y = targetYPos;
        }
    }
}
