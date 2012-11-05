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

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.papyrus.sequence.SeqProperties;

public class SComment extends SGraphElement {
    private static final long serialVersionUID = 2543686433908319587L;
    private KNode origin;
    private KEdge connection;
    private List<SGraphElement> attachedTo;
    private SMessage message;
    private SLifeline lifeline;
    private float xPos, yPos;
    private float width, height;

    public SComment(KNode origin) {
        this.origin = origin;
        attachedTo = new LinkedList<SGraphElement>();
    }

    public KNode getOrigin() {
        return origin;
    }

    public List<SGraphElement> getAttachedTo() {
        return attachedTo;
    }

    public KEdge getConnection() {
        return connection;
    }

    public void setConnection(KEdge connection) {
        this.connection = connection;
    }

    public SMessage getMessage() {
        return message;
    }

    public void setMessage(SMessage message) {
        // Delete comment from the old message's comments list
        if (this.message != null) {
            List<SComment> oldComments = this.message.getProperty(SeqProperties.COMMENTS);
            if (oldComments != null) {
                oldComments.remove(this);
            }
        }

        this.message = message;

        // Add comment to the new message's comments list
        if (message != null) {
            List<SComment> comments = message.getProperty(SeqProperties.COMMENTS);
            if (comments != null) {
                if (!comments.contains(this)) {
                    comments.add(this);
                }
            } else {
                comments = new LinkedList<SComment>();
                comments.add(this);
            }
        }
    }

    public SLifeline getLifeline() {
        return lifeline;
    }

    public void setLifeline(SLifeline lifeline) {
        // Delete comment from the old lifeline's comments list
        if (this.lifeline != null) {
            List<SComment> oldComments = this.lifeline.getProperty(SeqProperties.COMMENTS);
            if (oldComments != null) {
                oldComments.remove(this);
            }
        }

        this.lifeline = lifeline;

        // Add comment to the new lifeline's comments list
        if (lifeline != null) {
            List<SComment> comments = lifeline.getProperty(SeqProperties.COMMENTS);
            if (comments != null) {
                if (!comments.contains(this)) {
                    comments.add(this);
                }
            } else {
                comments = new LinkedList<SComment>();
                comments.add(this);
            }
        }
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
