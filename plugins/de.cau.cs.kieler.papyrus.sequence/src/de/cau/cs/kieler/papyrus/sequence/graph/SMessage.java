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
 * 
 */
public class SMessage extends SGraphElement {
    private static final long serialVersionUID = 6326794211792613083L;
    private SLifeline source;
    private SLifeline target;
    private Float sourceYPos;
    private Float targetYPos;
    private Float yPos;

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
        this.yPos = -1.0f;
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
     * Get the vertical position of the message.
     * 
     * @return the vertical position
     */
    public Float getYPos() {
        return yPos;
    }

    /**
     * Set the vertical position of the message.
     * 
     * @param yPosition
     *            the new vertical position
     */
    public void setYPos(final Float yPosition) {
        this.yPos = yPosition;
        this.sourceYPos = yPosition;
        this.targetYPos = yPosition;
        if (this.source.getGraph().getSizeY() < yPosition) {
            this.source.getGraph().setSizeY(yPosition);
        }
    }

    /**
     * Get the vertical position at the source lifeline of the message.
     * 
     * @return the vertical position at the source lifeline
     */
    public Float getSourceYPos() {
        return sourceYPos;
    }

    /**
     * Set the vertical position at the source lifeline of the message.
     * 
     * @param sourceYPos
     *            the new vertical position at the source lifeline
     */
    public void setSourceYPos(final Float sourceYPos) {
        this.sourceYPos = sourceYPos;
        if (this.source.getGraph().getSizeY() < sourceYPos) {
            this.source.getGraph().setSizeY(sourceYPos);
        }
    }

    /**
     * Get the vertical position at the target lifeline of the message.
     * 
     * @return the vertical position at the target lifeline
     */
    public Float getTargetYPos() {
        return targetYPos;
    }
    /**
     * Set the vertical position at the target lifeline of the message.
     * 
     * @param targetYPos
     *            the new vertical position at the target lifeline
     */
    public void setTargetYPos(final Float targetYPos) {
        this.targetYPos = targetYPos;
        if (this.target.getGraph().getSizeY() < targetYPos) {
            this.target.getGraph().setSizeY(targetYPos);
        }
    }
}
