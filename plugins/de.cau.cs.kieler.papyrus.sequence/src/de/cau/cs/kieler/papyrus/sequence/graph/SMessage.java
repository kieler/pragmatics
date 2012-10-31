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

public class SMessage extends SGraphElement {
    private static final long serialVersionUID = 6326794211792613083L;
    private SLifeline source;
    private SLifeline target;
    private Float sourceYPos;
    private Float targetYPos;
    private Float yPos;

    public SMessage(SLifeline source, SLifeline target) {
        this.source = source;
        this.target = target;
        this.yPos = -1.0f;
        source.getOutgoingMessages().add(this);
        target.getIncomingMessages().add(this);
    }

    public SLifeline getSource() {
        return source;
    }

    public void setSource(SLifeline source) {
        this.source.getOutgoingMessages().remove(this);
        this.source = source;
        this.source.getOutgoingMessages().add(this);
    }

    public SLifeline getTarget() {
        return target;
    }

    public void setTarget(SLifeline target) {
        this.target.getIncomingMessages().remove(this);
        this.target = target;
        this.target.getIncomingMessages().add(this);
    }

    public Float getYPos() {
        return yPos;
    }

    public void setYPos(Float yPos) {
        this.yPos = yPos;
        this.sourceYPos = yPos;
        this.targetYPos = yPos;
        if (this.source.getGraph().getSizeY() < yPos) {
            this.source.getGraph().setSizeY(yPos);
        }
    }

    public Float getSourceYPos() {
        return sourceYPos;
    }

    public void setSourceYPos(Float sourceYPos) {
        this.sourceYPos = sourceYPos;
        if (this.source.getGraph().getSizeY() < sourceYPos) {
            this.source.getGraph().setSizeY(sourceYPos);
        }
    }

    public Float getTargetYPos() {
        return targetYPos;
    }

    public void setTargetYPos(Float targetYPos) {
        this.targetYPos = targetYPos;
        if (this.source.getGraph().getSizeY() < targetYPos) {
            this.source.getGraph().setSizeY(targetYPos);
        }
    }
}
