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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.papyrus.sequence.SequenceDiagramProperties;

/**
 * Comment representation for SGraphs.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SComment extends SGraphElement {
    private static final long serialVersionUID = 2543686433908319587L;
    private KNode origin;
    private KEdge connection;
    private List<SGraphElement> attachedTo;
    private SMessage message;
    private SLifeline lifeline;
    private KVector size;
    private KVector position;

    /**
     * Constructor that takes the origin which is the KNode of the comment as argument.
     * 
     * @param origin
     *            the origin
     */
    public SComment(final KNode origin) {
        this.origin = origin;
        attachedTo = new LinkedList<SGraphElement>();
        size = new KVector();
        position = new KVector();
    }

    /**
     * Get the size of the comment.
     * 
     * @return the KVector with the size
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Get the position of the comment.
     * 
     * @return the KVector with the position
     */
    public KVector getPosition() {
        return position;
    }

    /**
     * Get the origin which is the KNode of the comment.
     * 
     * @return the origin
     */
    public KNode getOrigin() {
        return origin;
    }

    /**
     * Get the SGraphElement to which the comment is attached to.
     * 
     * @return the connected element
     */
    public List<SGraphElement> getAttachedTo() {
        return attachedTo;
    }

    /**
     * Get the original connection of the comment which is a KEdge.
     * 
     * @return the connection
     */
    public KEdge getConnection() {
        return connection;
    }

    /**
     * Set the connection of the comment.
     * 
     * @param connection
     *            the new connection
     */
    public void setConnection(final KEdge connection) {
        this.connection = connection;
    }

    /**
     * Get the message near to which the comment will be drawn if existing.
     * 
     * @return the SMessage near to the comment or null if not existing
     */
    public SMessage getMessage() {
        return message;
    }

    /**
     * Set the message near to which the comment will be drawn.
     * 
     * @param message
     *            the new message
     */
    public void setMessage(final SMessage message) {
        // Delete comment from the old message's comments list
        if (this.message != null) {
            List<SComment> oldComments = this.message
                    .getProperty(SequenceDiagramProperties.COMMENTS);
            if (oldComments != null) {
                oldComments.remove(this);
            }
        }

        this.message = message;

        // Add comment to the new message's comments list
        if (message != null) {
            List<SComment> comments = message.getProperty(SequenceDiagramProperties.COMMENTS);
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

    /**
     * Get the lifeline near to which the comment will be drawn if existing.
     * 
     * @return the SLifeline near to the comment or null if not existing
     */
    public SLifeline getLifeline() {
        return lifeline;
    }

    /**
     * Set the lifeline near to which the comment will be drawn.
     * 
     * @param lifeline
     *            the new lifeline
     */
    public void setLifeline(final SLifeline lifeline) {
        // Delete comment from the old lifeline's comments list
        if (this.lifeline != null) {
            List<SComment> oldComments = this.lifeline
                    .getProperty(SequenceDiagramProperties.COMMENTS);
            if (oldComments != null) {
                oldComments.remove(this);
            }
        }

        this.lifeline = lifeline;

        // Add comment to the new lifeline's comments list
        if (lifeline != null) {
            List<SComment> comments = lifeline.getProperty(SequenceDiagramProperties.COMMENTS);
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
}
